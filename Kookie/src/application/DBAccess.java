package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.time.LocalDate;

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
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, recipe);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			} catch(SQLException e){
				return false;
			}
		}
		return true;
	}

	/**
	 * Searches for a pallet with id palletID.
	 *
	 * @param palletID
	 */
	public Pallet searchPalletID(String palletID) {
		String query = "SELECT * FROM pallets WHERE palletID = ?;"
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, palletID);
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			return null;
		}
		return new Pallet(palletID, rs.getString("status"), rs.getString("prod_date"));
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
		String query = "SELECT * FROM pallets WHERE recipe = ? AND prod_date BETWEEN ? AND ?;";
		ArrayList<Pallet> pallets = new ArrayList<Pallet>();
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, recipe);
			ps.setString(2, startDate);
			ps.setString(3, endDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pallets.add(new Pallet(rs.getString("pallet_id"), rs.getString("status"), rs.getString("prod_date")));
			}
		} catch (SQLException e){
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
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, startDate);
			ps.setString(2, endDateDate);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pallets.add(new Pallet(rs.getString("pallet_id"), rs.getString("status"), rs.getString("prod_date")));
			}
		} catch (SQLException e){
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
	public boolean producePallet(String rec_name, String order_id){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.now();
		String query = "INSERT INTO pallets(pallet_id, status, prod_date, rec_name, order_id) VALUES('', '', ?, ?, ?);";
		try(PreparedStatement ps = conn.prepareStatement(query)){
			ps.setString(1, localDate);
			ps.setString(2, rec_name);
			ps.setString(3, order_id);
			ps.executeUpdate();
		} catch (SQLException e){
			return false;
		}
		return true;
	}
}
