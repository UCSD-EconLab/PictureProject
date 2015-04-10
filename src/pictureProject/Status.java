package pictureProject;

import java.io.Serializable;

/**
 * This class stores the client status meant to be send through ObjectOutputStream
 * @author Alex Gian
 */
public class Status implements Serializable{

	/**
	 * Serial Version UID for Status
	 */
	private static final long serialVersionUID = 8451693364934543448L;
	private String status;
	private int num = 0;
	private Long decTime;
	
	public static enum Page { 
		FIRST, SECOND, THIRD, FOURTH, FIFTH, SIXTH, DEFAULT
	}
	
	public static final int MAX_PAGES = 4;
	
	
	/**
	 * Status Constructor
	 */
	public Status() {
		status = "Initial";	
	}
	
	/**
	 * @param st
	 */
	public Status(String st) {
		setStatus(st);
	}
	
	/**
	 * @param st
	 */
	public void setStatus(String st) {
		status = st;
	}
	
	/**
	 * @return
	 */
	public String getStatus() {	
		return status;
	}	

	/**
	 * @return
	 */
	public Long getDecTime() {
		return decTime;
	}

	/**
	 * @param decTime
	 */
	public void setDecTime(Long decTime) {
		this.decTime = decTime;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Status: " + getStatus() + " Time: " + getDecTime();
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
	public String incrementStatus() {		
		return selectStatus(++num);
	}
	

	/**
	 * Selects a status and returns it.
	 * @param i
	 * @return
	 */
	public String selectStatus(int i) {
		setNum(i);
		
		String tmp ="";
		switch(i){
		case 0:
			tmp = printStatus(Page.FIRST);
			break;
		case 1:
			tmp = printStatus(Page.SECOND);
			break;
		case 2:
			tmp = printStatus(Page.THIRD);
			break;
		case 3: 
			tmp = printStatus(Page.FOURTH);
			break;	
		case 4: 
			tmp = printStatus(Page.FIFTH);
			break;	
		case 5: 
			tmp = printStatus(Page.SIXTH);
			break;	
		default: 
			tmp = printStatus(Page.DEFAULT);
			break;
		}
		return tmp;
	}
	
	/**
	 * Get a page
	 * @return
	 */
	public Page getPage() {
		switch(getNum()){
		case 0:
			return Page.FIRST;			
		case 1:
			return Page.SECOND;			
		case 2:
			return Page.THIRD;			
		case 3: 
			return Page.FOURTH;		
		case 4: 
			return Page.FIFTH;		
		case 5: 
			return Page.SIXTH;		
		default: 
			return Page.DEFAULT;			
		}
	}
	
	/**
	 * Prints the status
	 * @param pg
	 * @return
	 */
	public String printStatus(Page pg) {
		switch(pg){
		case FIRST:
			setStatus("Page 1 - Begin");	
			setNum(0);
			break;
		case SECOND:
			setStatus("Page 2 - Charity Intro");
			setNum(1);
			break;
		case THIRD:
			setStatus("Page 3 - Third Page - Charity Credits");
			setNum(2);
			break;
		case FOURTH:
			setStatus("Page 4 - Selecte a Donatee");
			setNum(3);
			break;
		case FIFTH:
			setStatus("Page 5");
			setNum(4);
			break;
		case SIXTH:
			setStatus("Page 6");
			setNum(5);
			break;
		default:
			setStatus("User Control");
			break;
		}
		return getStatus();
	}

}
