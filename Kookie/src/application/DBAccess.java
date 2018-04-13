package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
		} catch (SQLException e) {
			return false;
		}
		return true;
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

		String query = "SELECT * FROM pallets WHERE pallet_id = ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setInt(1, palletID);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(new Pallet(palletID, rs.getInt("status"), rs.getString("prod_date"), rs.getString("rec_name")));
				
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
		String query = "SELECT * FROM pallets WHERE rec_name = ? AND prod_date BETWEEN ? AND ?;";
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pallets.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name")));
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
		String query = "SELECT * FROM pallets WHERE prod_date BETWEEN ? AND ?;";
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, startDate);
			ps.setString(2, endDate);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pallets.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name")));
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pallets;
	}

	/**
	 * Adds a newly produced pallet in pallets table.
	 * 
	 * @param rec_name
	 * @param order_id
	 * @return true if succesfully added else false.
	 */
	public boolean producePallet(String rec_name, String order_id) {
		// Måste kolla att råvarorna räcker till innan pallarna lagts till.
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String query = "INSERT INTO pallets(pallet_id, status, prod_date, rec_name, order_id) VALUES('', '', ?, ?, ?);";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, dtf.format(localDate));
			ps.setString(2, rec_name);
			ps.setString(3, order_id);
			ps.executeUpdate();
		} catch (SQLException e) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param recipe
	 * @param amount
	 * @return String in the format "X pallets of /recipe/ has been produced. or
	 *         "amount is not an integer" or "insufficient materials for the X
	 *         pallets to be produced"
	 */
	public String producePallets(String recipe, String amount) {
		try {
			int amnt = Integer.parseInt(amount);
		} catch (NumberFormatException e) {
			return "amount is not an integer";
		}
		return amount + " pallets of " + recipe + "has been produced";
	}

	public ArrayList<Pallet> searchRecipe(String recipe) {
		ArrayList<Pallet> ret = new ArrayList<Pallet>();
		String query = "SELECT * FROM pallets WHERE rec_name = ?;";
		try (PreparedStatement ps = conn.prepareStatement(query)) {
			ps.setString(1, recipe);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ret.add(new Pallet(rs.getInt("pallet_id"), rs.getInt("status"), rs.getString("prod_date"),
						rs.getString("rec_name")));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
