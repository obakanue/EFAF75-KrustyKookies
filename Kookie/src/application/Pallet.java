package application;

public class Pallet {
	private int id;
	private int status;
	private String prodDate;
	private String recName;

	/**
	 * Creates a pallet with pallet_id, status and prod_date
	 * 
	 * @param id, unique pallet_id with max 8 integers
	 * @param status, 0 = produced, 1 = freezer, 2 = blocked, 3 = delivered
	 * @param prodDate, date of production
	 */
	public Pallet(int id, int status,  String prodDate, String recName){
		this.id = id;
		this.status = status;
		this.prodDate = prodDate;
		this.recName = recName;
	}
	
	/**
	 * Returns pallet_id of pallet.
	 *
	 * @return id
	 */
	public int getId(){
		return id;
	}
	
	/**
	 * Returns status of pallet.
	 *
	 * @return status
	 */
	public int getStatus(){
		return status;
	}
	
	/**
	 * Returns prod_date of pallet.
	 *
	 * @return prodDate
	 */
	public String getProdDate(){
		return prodDate;
	}
	
	public String getRecName(){
		return recName;
	}
	
	public String toString(){
		String stat = null;
		switch (status){
			case 0: 
				stat = "produced";
				break;
			case 1:
				stat = "freezer";
				break;
			case 2:
				stat = "blocked";
			default:
				stat = "delivered";
				break;
		}
			return id + " | " + stat + " | " + prodDate + " | " + recName; 
	}
}
