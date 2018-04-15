package application;

public class Pallet {
	private int id;
	private int status;
	private String prodDate;
	private String recName;
	private String recipient;

	/**
	 * Creates a pallet with pallet_id, status, prod_date and recName
	 * 
	 * @param id, unique pallet_id with max 8 integers
	 * @param status, 0 = produced, 1 = freezer, 2 = blocked, 3 = delivered
	 * @param prodDate, date of production
	 * @param recName, name of recipe
	 */
	public Pallet(int id, int status,  String prodDate, String recName, String recipient){
		this.id = id;
		this.status = status;
		this.prodDate = prodDate;
		this.recName = recName;
		this.recipient = recipient;
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
	
	/**
	 * Returns rec_name of pallet.
	 *
	 * @return recName
	 */
	public String getRecName(){
		return recName;
	}
	
	/**
	 * Returns a string of attributes of a pallet.
	 *
	 * @return String pallet info
	 */
	public String toString(){
		String stat = null;
		switch (status){
			case 0: 
				stat = "Produced";
				break;
			case 1:
				stat = "Freezer";
				break;
			case 2:
				stat = "Blocked";
				break;
			default:
				stat = "Delivered";
				break;
		}
			return id + " | " + stat + " | " + prodDate + " | " + recName + " | " + recipient; 
	}
}
