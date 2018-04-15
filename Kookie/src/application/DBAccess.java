package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javafx.util.Callback;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DBAccess {

	private Connection conn = null;

	public DBAccess() {
		openConnection("krusty-db.sqlite");

	}

	public boolean openConnection(String filename) {
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + filename);
			conn.prepareStatement("PRAGMA foreign_keys = ON;").execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void closeConnection() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean isConnected() {
		return conn != null;
	}

	/*
	 * Blocks a recipe from startDate to endDate.
	 * 
	 * @return true, if the recipe was successfully blocked, false if not.
	 */

	public boolean blockRecipe(String recipe, String startDate, String endDate) {
		String query = "INSERT INTO blocked(rec_name, start_date, end_date) VALUES (?, ?, ?);";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ps.executeUpdate();
			blockPallets(recipe, startDate, endDate);
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	private void blockPallets(String recipe, String startDate, String endDate) {
		String query = "UPDATE pallets SET status = 2 WHERE rec_name = ? AND prod_date BETWEEN ? AND ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ps.executeUpdate();
		} catch (SQLException e) {

		}
	}

	/**
	 * Searches for a pallet with id palletID.
	 *
	 * @param palletID
	 */
	public ArrayList<Pallet> searchPalletID(String ID) {
		int palletID = 0;
		ArrayList<Pallet> ret = new ArrayList<Pallet>();
		try {
			palletID = Integer.parseInt(ID);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return null;
		}

		String query = "SELECT * FROM pallets JOIN orders USING (order_id) JOIN customers USING (c_name) WHERE pallet_id = ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, palletID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(new Pallet(palletID, rs.getInt("status"), rs.getString("prod_date"), rs.getString("rec_name"),
						rs.getString("c_name")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;

	}

	/**
	 * Search for the pallets produced of a certain recipe between the dates
	 * startDate and endDate.
	 * 
	 * @param recipe
	 * @param startDate
	 * @param endDate
	 */
	public ArrayList<Pallet> searchRecipesBetween(String recipe, String startDate, String endDate) {
		String query = "SELECT pallet_ID, status, prod_date, rec_name, c_name FROM pallets JOIN orders USING (order_id) JOIN customers USING (c_name) WHERE rec_name = ? AND prod_date BETWEEN ? AND ?;";
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pallets.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name"), rs.getString("c_name")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pallets;
	}

	/**
	 * Search for pallets produced between startDate and endDate.
	 * 
	 * @param startDate
	 * @param endDate
	 */
	public ArrayList<Pallet> searchBetween(String startDate, String endDate) {
		String query = "SELECT pallet_ID, status, prod_date, rec_name, c_name FROM pallets JOIN orders USING (order_id) JOIN customers USING (c_name) WHERE prod_date BETWEEN ? AND ?;";
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pallets.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name"), rs.getString("c_name")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pallets;
	}

	/**
	 * Adds a newly produced pallet in pallets table after updating warehouse.
	 * 
	 * @param recipe
	 * @param order_id
	 * @param amount
	 * @return true if successfully added else false.
	 */
	public boolean producePallet(String recipe, String order_id, String amount) {
		int am = 0;
		int oid = 0;
		try {
			am = Integer.parseInt(amount);
			oid = Integer.parseInt(order_id);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (checkWarehouse(recipe, amount)) {
			String query = "INSERT INTO pallets(pallet_id, status, prod_date, rec_name, order_id) VALUES(NULL, 0, ?, ?, ?);";
			try (PreparedStatement ps = conn.prepareStatement(query)) {
				for (int i = 0; i < am; i++) {
					System.out.println(sdf.format(System.currentTimeMillis()));
					ps.setString(1, sdf.format(System.currentTimeMillis()).toString());
					ps.setString(2, recipe);
					ps.setInt(3, oid);
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * Checks if there are sufficient with ingredients to fullfill order,
	 * returns true or false depending on availability.
	 * 
	 * @param recipe
	 * @param amount
	 * @return true if there are sufficient with ingredients otherwise false
	 */
	public boolean checkWarehouse(String recipe, String amount) {
		int am = 0;
		try {
			am = Integer.parseInt(amount);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		int rowsRec = 0;
		int rowsAvail = 0;
		String query = "SELECT count() as rowsRec FROM rec_ing WHERE rec_name = ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ResultSet rs = ps.executeQuery();
			rowsRec = rs.getInt("rowsRec");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String query2 = "SELECT count() as rowsAvail FROM rec_ing JOIN ingredients USING (ing_name) WHERE rec_name = ? AND rec_ing.amount * 36 * ? <= ingredients.amount;";
		try (PreparedStatement ps2 = conn.prepareStatement(query2)) {
			ps2.setString(1, recipe);
			ps2.setInt(2, am);
			ResultSet rs2 = ps2.executeQuery();
			rowsAvail = rs2.getInt("rowsAvail");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rowsAvail < rowsRec) {
			return false;
		}

		return updateWarehouse(recipe, am);
	}

	/**
	 * Private method that updates warehouse storage of ingredients.
	 * 
	 * @param recipe
	 * @param amount
	 * @return boolean true if successfully updated false if not
	 */
	private boolean updateWarehouse(String recipe, int amount) {
		String query = "SELECT ing_name, amount FROM rec_ing WHERE rec_name = ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ResultSet ingredients = ps.executeQuery();
			while (ingredients.next()) {
				String update = "UPDATE ingredients SET amount = amount - 36*?*? WHERE ing_name = ?;";
				PreparedStatement ps2 = conn.prepareStatement(update);
				ps2.setInt(1, amount);
				ps2.setInt(2, ingredients.getInt("amount"));
				ps2.setString(3, ingredients.getString("ing_name"));
				ps2.executeUpdate();
			}

			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * Returns an array of pallets for a specific recipe in database
	 * 
	 * @param recipe
	 * @return ArrayList<Pallet>, pallets of a specific recipe
	 */
	public ArrayList<Pallet> searchRecipe(String recipe) {
		ArrayList<Pallet> ret = new ArrayList<Pallet>();
		String query = "SELECT * FROM pallets JOIN orders USING (order_id) JOIN customers USING (c_name) WHERE rec_name = ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name"), rs.getString("c_name")));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

	/**
	 * Checks if a recipe is currently blocked.
	 * 
	 * @param recipe
	 * @return true if recipe is blocked, false if it is not blocked.
	 */
	public boolean checkIfBlocked(String recipe) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String query = "SELECT count() AS cnt FROM blocked WHERE rec_name = ? AND ? BETWEEN start_date AND end_date;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ps.setString(2, sdf.format(System.currentTimeMillis()).toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int i = rs.getInt("cnt");
				System.out.println(i);
				if (i == 0) {
					return false;
				}
			}
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public ArrayList<Pallet> showAllBlocked() {
		ArrayList<Pallet> ret = new ArrayList<Pallet>();
		String query = "SELECT * FROM pallets JOIN orders USING (order_id) JOIN customers USING (c_name) WHERE status = 2;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name"), rs.getString("c_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return ret;
	}

	public ArrayList<Pallet> searchRecipesBetweenWithRecipient(String rec_name, String startDate, String endDate,
			String recipient) {
		ArrayList<Pallet> ret = new ArrayList<Pallet>();
		String query = "SELECT * FROM pallets JOIN orders USING (order_id) JOIN customers USING (c_name) WHERE rec_name = ? AND c_name = ? AND prod_date BETWEEN ? AND ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, rec_name);
			ps.setString(2, recipient);
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ret.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name"), rs.getString("c_name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ret;
	}

	public ArrayList<Pallet> searchRecipient(String recipient) {
		ArrayList<Pallet> ret = new ArrayList<Pallet>();
		String query = "SELECT * FROM pallets JOIN orders USING (order_id) JOIN customers USING (c_name) WHERE c_name = ?";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, recipient);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				ret.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name"), rs.getString("c_name")));
			}
		}catch(SQLException e){
			e.printStackTrace();
			
		}
		return ret;
	}

}
