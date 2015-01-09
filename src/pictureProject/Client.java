package pictureProject;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JOptionPane;



//import Survey.*;

public class Client {
	String id;
	Socket server = null;
	StartInfo si = new StartInfo();
	int option;
	ClientGUI clientGUI;
	Long[] individual_times = new Long[24];
	ArrayList<Integer> decision_types = new ArrayList<Integer>();
	ArrayList<Long> decision_times = new ArrayList<Long>();
	Integer chosenDecision;
	Long begin;
	Long end;
	Integer chosenPic; 
	float budget;
	Float chosenPayment;
	ObjectOutputStream serverOut;
	Float amts[] = new Float[24];

	/**
	 * @return the chosenPayment
	 */
	public Float getChosenPayment() {
		return chosenPayment;
	}

	/**
	 * @param chosenPayment the chosenPayment to set
	 */
	public void setChosenPayment(float chosenPayment) {
		this.chosenPayment = chosenPayment;
	}
	
	
	public Client(Socket s) throws ClassNotFoundException, IOException {
		server = s;

		id = (String) JOptionPane.showInputDialog(null,
					"Please enter your ID number: ", "Enter ID",
					JOptionPane.INFORMATION_MESSAGE, null, null, null);

		for (int i = 0; i < amts.length; i++){
			amts[i] = new Float(0.0);
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		// the ip address given for the server
		InetAddress ip = null;
		Socket server = null;
		Client client;

		String warning = "";
		// connect to server
		while (ip == null) {
			try {
				String result = (String) JOptionPane.showInputDialog(null,
						warning + "Please enter IP of Server: ",
						"Enter Server IP Address:",
						JOptionPane.INFORMATION_MESSAGE, null, null, null);
				if (result == null) {
					System.exit(0);
				}

				ip = InetAddress.getByName(result);
				server = new Socket(ip, 4443);
			} catch (Exception ex) {
				ip = null;
				warning = "Invalid IP \n";
			}
		}
		client = new Client(server);
		client.serverOut = new ObjectOutputStream(server.getOutputStream());
		client.si.readInfo(client.server, client);

		client.sendID(client.server);
		client.clientGUI = new ClientGUI(client);
	}


	public void setChosenPic(int c) {
		chosenPic = c;
	}
	
	public int getChosenPic(){
		return chosenPic;
	}
	
	public void setOption(int option){
		this.option = option;
	}

	
	public int getOption(){
		return this.option;
	}

	/**
	 * @return the budget
	 */
	public float getBudget() {
		return budget;
	}

	/**
	 * @param budget the budget to set
	 */
	public void setBudget(float budget) {
		this.budget = budget;
	}

	public void sendData(int option) {

		try{
			System.out.println("Sending info");
			if(option == 1){
				this.serverOut.writeObject(chosenPic);
				this.serverOut.writeObject(chosenPayment);
			}else if(option == 2){
				this.serverOut.writeObject(chosenPayment);
			}else if(option == 3){
				this.serverOut.writeObject(individual_times);
				this.serverOut.writeObject(amts);
			}else if(option >= 4 && option <= 7){
				this.serverOut.writeObject(decision_types);
				this.serverOut.writeObject(decision_times);
				this.serverOut.writeObject(chosenPayment);
				this.serverOut.writeObject(chosenDecision);
			}
			this.serverOut.writeObject((end-begin));
		}
		catch(IOException e){

		}

	}

	public void sendID(Socket server) {
			try{
			this.serverOut.writeObject(id);
		}
		catch(IOException e){

		}
	}
	
}