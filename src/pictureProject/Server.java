package pictureProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server {
	
	public static final int MAX_CLIENTS = 50;

	ServerSocket server;
	StartInfo si = new StartInfo();
	Pictures pics = new Pictures();
	int option;
	float budget;
	ServerGUI gui;
	ObjectInputStream[] fromclient;
	ObjectOutputStream[] toclient;

	Socket[] clientAddress;
	int clientCount;

	 String clientIDs[] = new String[MAX_CLIENTS];
	 String clientStatuses[] = new String[MAX_CLIENTS];
	 Float clientAmts[][] = new Float[MAX_CLIENTS][24];
	 Float clientSingleAmts[] = new Float[MAX_CLIENTS];
	 Long clientTimes[] = new Long[MAX_CLIENTS];
	 Long clientOpt3Times[][] = new Long [MAX_CLIENTS][24];
	 Integer clientChosenPics[] = new Integer[MAX_CLIENTS];
	 Integer clientChosenDecision[] = new Integer[MAX_CLIENTS];
	 ArrayList<ArrayList<Integer>> clientDecisionTypes = new ArrayList<ArrayList<Integer>>(MAX_CLIENTS);
	 ArrayList<ArrayList<Long>> clientDecisionTimes = new ArrayList<ArrayList<Long>>(MAX_CLIENTS);

	
	public Server() throws IOException, ClassNotFoundException{
		server = new ServerSocket(4443);
		clientAddress = new Socket[MAX_CLIENTS];
		fromclient = new ObjectInputStream[MAX_CLIENTS];
		toclient = new ObjectOutputStream[MAX_CLIENTS];
		si.getStartInfo();
		option = si.option;
		budget = si.budget;
		for(int i = 0; i < MAX_CLIENTS; i++)
		{
			clientDecisionTypes.add(i, null);
			clientDecisionTimes.add(i, null);
		}
		gui = new ServerGUI(this);
		//gui.initialize();
		for(int i = 0; i < MAX_CLIENTS; i++){
			clientStatuses[i] = "WORKING";
		}
		for(int i = 0; i < MAX_CLIENTS; i ++){
	
			clientAddress[i] = server.accept();
			si.sendInfo(clientAddress[i]);

			fromclient[i] = new ObjectInputStream(clientAddress[i].getInputStream());
		
			GetClientData gcd = new GetClientData(fromclient[i], this, i);
			gcd.start();

		}	
	}
	

	public static void main(String[] args)  throws IOException, ClassNotFoundException{

		new Server();

	}


	public void readID(Socket client, int index) throws IOException, ClassNotFoundException{
			ObjectInputStream ois = new ObjectInputStream(client.getInputStream());
			String id = (String)ois.readObject();
			System.out.println(id);
			clientIDs[index] = id;
	}

	public void update(){
		
		String[] clients = new String[MAX_CLIENTS];
		for(int i = 0; i < MAX_CLIENTS; i++){
			if(clientIDs[i] != null){
				System.out.println("ID:" + clientIDs[i] + "   STATUS: " + clientStatuses[i] );
				clients[Integer.parseInt(clientIDs[i])] = "ID:" + clientIDs[i] + "   STATUS: " + clientStatuses[i];
				for(int j = 0; j < 24; j++){
					System.out.print(  "  AMOUNTS: "   +    clientAmts[i][j] + ", ");
				}
				System.out.println("");
			}
		}


		gui.updateStatus(clients);
	}
	

}
