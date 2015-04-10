package pictureProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Vector;

import javax.swing.JFileChooser;

public class StartInfo {
	public Vector<byte[]> pics = new Vector<byte[]>();
	public volatile int option;
	public volatile float budget;
	private volatile Option optGUI = new Option();
	
	
	public StartInfo() {}
	
	public void getStartInfo() throws IOException {
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		fc.setCurrentDirectory(new File("../"));
		fc.showDialog(null, "Select Pictures");
		File[] files = fc.getSelectedFiles();
		
		for(File file : files){
			System.out.println(file);
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			pics.add(buffer);		
		}
		
		optGUI.getOption();
		
		while(!optGUI.getOB().isSet()){}
	
		this.option = optGUI.getOB().getOption();
		this.budget = optGUI.getOB().getBudget();
		System.out.println(optGUI.getOB().getBudget() + "  " + optGUI.getOB().getOption());
		System.out.println("Finished");
	}
	
	public void sendInfo(Socket client) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		for(byte[] pic : pics){
			oos.writeObject(pic);
			System.out.println("Sending an image to stream..");
		}

		System.out.println("OPTION: " + option + " BUDGET: " + budget);
		oos.writeObject(this.option);
		oos.writeObject(this.budget);
		
		//3
	//	oos.close();
	}
	
	public void readInfo(Socket server, Client c) throws IOException, ClassNotFoundException {
		System.out.println("readPics");

		ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
		
		for(int i = 0; i < 24; i++){
			byte[] buffer = (byte[])ois.readObject();
			new File("./downloaded/").mkdir();
			FileOutputStream fos = new FileOutputStream(new File("./downloaded/picture"+(i+1)+".jpg"));
			fos.write(buffer);
			System.out.println("Read an image from stream..");
		}
		int opt = (Integer)ois.readObject();
		c.setOption(opt);

		float budget = (Float)ois.readObject();
		c.setBudget(budget);
	//	ois.close();
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Pictures picts = new Pictures();
		Socket socket = new Socket("localhost",3333);
		picts.readPics(socket);
	}
}
