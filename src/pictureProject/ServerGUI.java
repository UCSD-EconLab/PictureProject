package pictureProject;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

public class ServerGUI extends JFrame{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int MAX_CLIENTS = 50;
	public static final String[] DECISION_TYPES = {"Checked Box 1", "Unchecked Box 1", "Checked Box 2", 
		"Unchecked Box 2", "Clicked Make your donation Decision", "Completed Donation"};
	public static final String[] DECISION_TYPE_NAMES_OPT4 = {"All", "1"};
	public static final String[] DECISION_TYPE_NAMES_OPT5 = {"Random", "1"};
	public static final String[] DECISION_TYPE_NAMES_OPT6 = {"1", "All"};
	public static final String[] DECISION_TYPE_NAMES_OPT7 = {"1", "Random"};
	
	private Server thisServer;
	private int currentPage;
	
	JPanel mainpanel;
	JList list;
	JScrollPane pane;
	JButton export;
	JButton next;
	Status myStatus;
	private JLabel pageStatusLabel;
	
	public ServerGUI(Server server){

		super();
		myStatus = new Status();
		myStatus.selectStatus(0);
		
		thisServer = server;		
     
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainpanel = new JPanel();
		BoxLayout bl = new BoxLayout(mainpanel, BoxLayout.Y_AXIS);
		mainpanel.setLayout(bl);
		add(mainpanel);
			
		Calendar date = Calendar.getInstance();
		SimpleDateFormat dateformatter = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
		dateformatter.format(date.getTime());
		
		JLabel dateline = new JLabel("Start Time: " + dateformatter.format(date.getTime()));		
		String od = "";
					if(server.option == 1)
					{
						od = "1. Give to one";
					}else if(server.option == 2)
					{
						od = "2. Give to all equally shared";
					}else if(server.option == 3){
						od = "3. Give to all one by one";
					}else if(server.option == 4){
						od = "4. Participants decide - All vs 1";
					}else if(server.option == 5){
						od = "5. Participants decide - Random vs 1";
					}else if(server.option == 6){
						od = "6. Participants decide - 1 vs All";
					}else if(server.option == 7){
						od = "7. Participants decide - 1 vs Random";
					}
		JLabel opt = new JLabel("<html>Option: " + od + "<br>" + "Budget: $" +server.budget +"</html>" );
		
		
		mainpanel.add(opt);
		mainpanel.add(dateline);
		
		list = new JList ();
		pane = new JScrollPane();
        pane.getViewport().add(list);
        pane.setPreferredSize(new Dimension(800, 600));
        mainpanel.add(pane);
        
        export = new JButton("Export to CSV");
        next = new JButton("Next Screen");
  
        
        export.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					
					/*
					for(int i = 0; i < thisServer.clientDecisionTypes.size(); i++)
					{
						System.out.println(thisServer.clientDecisionTypes.get(i));
					}
					*/
					
					Calendar date = Calendar.getInstance();
					SimpleDateFormat dateformatter = new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
					dateformatter.format(date.getTime());	
 
					StringBuilder content = new StringBuilder();
					content.append("UCSD EconLab: Picture Project Experiment");

					content.append("\n\n\n");
					String od = "";
					if(thisServer.option == 1)
					{
						od = "1. Give to one";
					}else if(thisServer.option == 2)
					{
						od = "2. Give to all equally shared";
					}else if(thisServer.option == 3){
						od = "3. Give to all one by one";
					}else if(thisServer.option == 4){
						od = "4. Participants decide - All vs 1";
					}else if(thisServer.option == 5){
						od = "5. Participants decide - Random vs 1";
					}else if(thisServer.option == 6){
						od = "6. Participants decide - 1 vs All";
					}else if(thisServer.option == 7){
						od = "7. Participants decide - 1 vs Random";
					}
					DecimalFormat form = new DecimalFormat("0.00");
					content.append("Option:, " + od);
					content.append("\n");
					content.append("Budget:, $" +  form.format(thisServer.budget));
					content.append("\n");
					content.append("Date:, " + dateformatter.format(date.getTime()));
					content.append("\n\n");
					
					//index to insert summary block
					int summaryi = content.length();
					//summary array for opt3
					float[] sumarray = new float[thisServer.clientIDs.length];
					long[] timearray = new long[thisServer.clientIDs.length];

					if(thisServer.option == 3)
						content.append("Client ID, To Whom, Gave, Time(in milliseconds)");
					else if(thisServer.option >= 4 && thisServer.option <= 7)
						content.append("Client ID, Time (in milliseconds), Action");
					else
						content.append("Client ID, To Whom, Gave, Kept, Time(in milliseconds)");
					content.append("\n");

					for(int i = 0; i < thisServer.clientIDs.length; i++){
						if(thisServer.clientIDs[i] != null){
							content.append(thisServer.clientIDs[i]);
							
							
							if(thisServer.option == 3){
								content.append("\n");
								float individualsum = 0;
								long timesum = 0;

								for(int j = 0; j < thisServer.clientAmts[0].length; j++){
									content.append(thisServer.clientIDs[i]);
									content.append(",");
									if(thisServer.clientAmts[i][j] == null)
										content.append((j+1)+" ," + "null");
									else
										content.append((j+1)+" ," + "$"+ form.format(thisServer.clientAmts[i][j]));
									content.append(",");
									content.append(thisServer.clientOpt3Times[i][j]);
									content.append("\n");
									if(thisServer.clientAmts[i][j] == null)
										individualsum = individualsum;
									else
										individualsum += thisServer.clientAmts[i][j];
									if(thisServer.clientOpt3Times[i][j] == null)
										timesum = timesum;
									else
										timesum += thisServer.clientOpt3Times[i][j];
								}
								sumarray[i] = individualsum;
								timearray[i] = timesum;

							}else if(thisServer.option == 2){
								content.append(",");
								if(thisServer.clientSingleAmts[i] == null)
									content.append("All," + "null");
								else
									content.append("All," + "$" + form.format(thisServer.clientSingleAmts[i]));
							}else if(thisServer.option ==1){
								content.append(",");
								content.append(thisServer.clientChosenPics[i] + ",");
								if(thisServer.clientSingleAmts[i] == null)
									content.append("All,"+ "null");
								else
								content.append("$" + form.format(thisServer.clientSingleAmts[i]));
							}else if(thisServer.option >= 4 && thisServer.option <= 7){
								
								if( (thisServer.clientDecisionTimes).get(i) == null && (thisServer.clientDecisionTypes).get(i) == null)
								{
									content.append(",null, null\n");
								}
								else
								{
									content.append(",");
									for(int j = 0; j < ((thisServer.clientDecisionTimes).get(i)).size(); j++)
									{
										content.append(((thisServer.clientDecisionTimes).get(i)).get(j) + ",");
										content.append(DECISION_TYPES[((thisServer.clientDecisionTypes).get(i)).get(j)] + ",");
										content.append("\n,");
									}
									
								}
								
							}
							
							
							if(thisServer.option == 1 || thisServer.option == 2){
								if(thisServer.clientSingleAmts[i] == null)
									content.append(","+ "null");
								else
									content.append("," + "$" + form.format(thisServer.budget - thisServer.clientSingleAmts[i]));
								content.append("," + thisServer.clientTimes[i]);
							}
							content.append("\n");
						}
					}

					//summary for option 3
					//
					if(thisServer.option == 3)
					{
						String summary;
						summary = "Client ID, To Whom, Gave, Kept, Time(in milliseconds)";
						summary += "\n";
						for(int i = 0; i < thisServer.clientIDs.length; i++)
						{
							if(thisServer.clientIDs[i] != null)
							{
								summary += thisServer.clientIDs[i] + ",";
								summary += "All,";
								sumarray[i] *= 100;
								sumarray[i] = Math.round(sumarray[i]);
								sumarray[i] /= 100;
								summary += "$" + form.format(sumarray[i]) + ",";
								summary += "$" + form.format((thisServer.budget - sumarray[i])) + ",";
								summary += timearray[i];
								summary += "\n";
							}
						}
						summary += "\n";
						content.insert(summaryi, summary);
					}else if(thisServer.option >= 4 && thisServer.option <= 7){
						
						String summary;
						summary = "Client ID, Donation Type, Gave, Kept";
						summary += "\n";
						for(int i = 0; i < thisServer.clientIDs.length; i++)
						{
							if(thisServer.clientIDs[i] != null)
							{
								if(thisServer.clientChosenDecision[i] != null)
								{
									summary += thisServer.clientIDs[i] + ",";
									if(thisServer.option == 4)
										summary += DECISION_TYPE_NAMES_OPT4[thisServer.clientChosenDecision[i]] + ",";
									else if(thisServer.option == 5)
										summary += DECISION_TYPE_NAMES_OPT5[thisServer.clientChosenDecision[i]] + ",";
									else if(thisServer.option == 6)
										summary += DECISION_TYPE_NAMES_OPT6[thisServer.clientChosenDecision[i]] + ",";
									else
										summary += DECISION_TYPE_NAMES_OPT7[thisServer.clientChosenDecision[i]] + ",";
									summary += "$" + form.format(thisServer.clientSingleAmts[i]) + ",";
									summary += "$" + form.format((thisServer.budget - thisServer.clientSingleAmts[i]));
									summary += "\n";
								}
							}
						}
						summary += "\n";
						content.insert(summaryi, summary);
					
					}


				
				SimpleDateFormat filename = new SimpleDateFormat ("yyyyMMddhhmm");
				File file = new File("PictureProject_"+filename.format(date.getTime()) + ".csv");
 
 
				// if file doesnt exists, then create it
				file.createNewFile();
 
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content.toString());
				bw.close();
  
				} catch (IOException c) {
					c.printStackTrace();
				}	
			}});
        
        
        next.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					myStatus.selectStatus(++currentPage);
					System.out.println("myStatus.getStatus(): " + myStatus.getStatus());
					thisServer.notifyAllStatus(myStatus);
					pageStatusLabel.setText(myStatus.getStatus());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
        });
        
        pageStatusLabel = new JLabel(myStatus.getStatus());
        
        Box bh = Box.createHorizontalBox();
        bh.add(export);
        bh.add(Box.createHorizontalGlue());        
        bh.add(pageStatusLabel);
        bh.add(Box.createHorizontalGlue());        
        bh.add(next);
        
        mainpanel.add(bh);
        
        pack();
		
		mainpanel.setVisible(true);
		setVisible(true);
	}
	
	
	public void updateStatus(String[] status){
		
		list = new JList(status);
		pane.getViewport().add(list);
		pack();
		
	}


}
