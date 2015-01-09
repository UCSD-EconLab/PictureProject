
package pictureProject;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
class GetClientData extends Thread {

	ObjectInputStream in;
	Server server;
	int index;

	public GetClientData(ObjectInputStream in, Server server, int count) {
		this.in = in;
		this.server = server;
		index = count;
	}

	public void run() {

		Object temp = null;

		try {
			if(server == null){
				System.out.println("PROBLEM");
			}
			// start listening
			while (null != (temp = in.readObject())) {

				if(Float[].class.isInstance(temp)){
					Float[] amts = (Float[]) temp;
					for(int i =0; i < 24; i++){
						server.clientAmts[index][i] = amts[i];
					}	
					server.clientStatuses[index] = "FINISHED";
				}else if(String.class.isInstance(temp)){
					server.clientIDs[index] = (String)temp;
				}else if(Long.class.isInstance(temp)){
					server.clientTimes[index] = (Long)temp;
				}else if(Float.class.isInstance(temp)){
					server.clientSingleAmts[index] = (Float) temp;
					server.clientStatuses[index] = "FINISHED";
				}else if(Long[].class.isInstance(temp)){
					Long[] times = (Long[]) temp;
					for(int i =0; i < 24; i++){
						server.clientOpt3Times[index][i] = times[i];
					}
				}else if(Integer.class.isInstance(temp)){
					if(server.option == 1)
						server.clientChosenPics[index] = (Integer) temp;
					else
						server.clientChosenDecision[index] = (Integer) temp;
				}
				
				else if(temp instanceof ArrayList<?>){
					ArrayList<?> temparray = (ArrayList<?>) temp;
					if( temparray.get(0) instanceof Integer)
					{
						(server.clientDecisionTypes).set(index, (ArrayList<Integer>)temparray );
						System.out.println("Client "+index+"'s decisioninfo transferred!");
					}
					else if(temparray.get(0) instanceof Long)
					{
						(server.clientDecisionTimes).set(index, (ArrayList<Long>)temparray );	
					}
				}
				
				server.update();
			}
		} catch (ClassNotFoundException e) {
			System.err.println(index);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println(index);
			e.printStackTrace();
		}
	}
}