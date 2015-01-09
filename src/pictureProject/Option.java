	package pictureProject;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.*;

public class Option{
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;
	private int opt;
	private float budget;
	JFrame jf = new JFrame();
	JPanel j;
	private JComboBox optList;
	private JButton okButton = new JButton("OK");
	private JLabel optLabel = new JLabel("Experiment Type");

	private JLabel budgetLabel = new JLabel("Budget");
	private JTextField budgetfield = new JTextField();
	private OptionBudget ob = new OptionBudget();
	
	
	public void getOption(){
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	
		int x = (dim.width-200)/2;
		int y = (dim.height-100)/2;
		
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.setSize(200, 144);
		jf.setLocation(x, y);
		j = new JPanel();
		j.setLayout(new BoxLayout(j, BoxLayout.Y_AXIS));
		
	

		String[] opts = {"<Choose option>", "1. Choose donatee", "2. To all donatees", "3. One by one", 
				"4. Participants decide - All vs 1", "5. Participants decide - Random vs 1",
				"6. Participants decide - 1 vs All", "7. Participants decide - 1 vs Random"};
		optList = new JComboBox(opts);
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				opt = optList.getSelectedIndex();
				budget = Float.parseFloat(budgetfield.getText());

				ob.setValues(opt, budget);
				if(ob.isSet()){
					jf.setVisible(false);
				}
			}
		});

		optLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		okButton.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		optList.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		budgetLabel.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
		budgetfield.setAlignmentX(java.awt.Component.CENTER_ALIGNMENT);
				
		j.add(optLabel);
		j.add(optList);

		j.add(budgetLabel);
		j.add(budgetfield);

		j.add(okButton);
		jf.add(j);
		optLabel.setVisible(true);
		optList.setVisible(true);
		okButton.setVisible(true);
		jf.setVisible(true);
	}
	
	public int getOpt(){
		return opt;
	}

	public OptionBudget getOB(){
		return this.ob;
	}

	public float getBudget(){
		return budget;
	}

	public void sendOptions(Socket client) throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(client.getOutputStream());
		oos.write(opt);
	}
	
	public void readOptions(Socket server) throws IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(server.getInputStream());
		opt = ois.read();
	}
}
