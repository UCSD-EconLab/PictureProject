package pictureProject;

import java.io.Serializable;

/*
 * This class stores the client status
 */
public class Status implements Serializable{

	/**
	 * Status Class
	 */
	private static final long serialVersionUID = 8451693364934543448L;
	private String status;
	
	public Status() {
		status = "Initial";
	}
	
	public Status(String st) {
		setStatus(st);
	}
	
	public void setStatus(String st) {
		status = st;
	}
	
	public String getStatus() {	
		return status;
	}	
	
	public String toString() {
		return getStatus();
	}
}
