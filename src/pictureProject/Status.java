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
	private Long decTime;
	
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

}
