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

public class Pictures {
	//private String path_to_pics;
	public Vector<File> pics = new Vector<File>();
	
	public Pictures() {}
	
	public void getPics() throws IOException {
		JFileChooser fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
		fc.setCurrentDirectory(null);
		fc.showDialog(null, "Select Pictures");
		File[] files = fc.getSelectedFiles();
		
		for(File file : files){
			pics.add(file);		
		}
	}
	
	public void sendPics(Socket client) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		for(File pic : pics){
			FileInputStream fis = new FileInputStream(pic);
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			//v.add(buffer);
			//SendData sd = new SendData(oos, v);
			//sd.start();
			oos.writeObject(buffer);
			System.out.println("Sending an image to stream..");
		}
		oos.close();
	}
	
	public void readPics(Socket server) throws IOException, ClassNotFoundException {
		System.out.println("readPics");

		ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
		
		for(int i = 0; i < 24; i++){
			byte[] buffer = (byte[])ois.readObject();
			new File("./downloaded/").mkdir();
			FileOutputStream fos = new FileOutputStream(new File("./downloaded/picture"+(i+1)+".jpg"));
			fos.write(buffer);
			System.out.println("Read an image from stream..");
		}
		ois.close();
		
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Pictures picts = new Pictures();
		Socket socket = new Socket("localhost",3333);
		picts.readPics(socket);
	}
}
