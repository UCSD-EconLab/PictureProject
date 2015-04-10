package pictureProject;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.sql.Timestamp;
import java.util.Date;
import java.net.URL;



public class ClientGUI extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 1366;
	public static final int HEIGHT = 768;
	public static final int MAX_PAGES = 4;
	
	private Vector<JButton> pics = new Vector<JButton>();	
	private Client client;
	private float budget;
	private int counter = 0;
	private float sum = 0;
//	private float payment = 0;
	
	//main
	JPanel fifthpage;
	JPanel fourthpage;
	JPanel firstpage;
	JPanel secondpage;
	JPanel thirdpage;
	JPanel pPics;
	JPanel pText;
	JPanel pText2;
	JPanel moneypanel;
	JPanel[] pages = new JPanel[MAX_PAGES];
	
	//for text
	private JLabel pTextQ;
	private JLabel pRemain;
	private JComboBox pTextCB;
	private JComboBox pTextCB2;
	private JButton pTextOKButton;
	private JButton pTextNextButton;
	private JButton pTextBeginButton;
	private JCheckBox CBoptionA;
	private JCheckBox CBoptionB;
	private ButtonGroup btnGroup;

	
//	public enum Page { 
//		FIRST, SECOND, THIRD, FOURTH
//	}
	
	public ClientGUI(Client c)
	{
		System.out.println(c.getBudget());
		this.budget = c.getBudget();
		initUI(c);	
		
	}

	
	public final void initUI(Client c){
		
		client = c;
		System.out.println("THIS IS THE BUDGET : " + client.getBudget());
		//setSize(new Dimension(WIDTH,HEIGHT));
		//GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		setBackground(Color.WHITE);
    	this.setExtendedState(this.getExtendedState() | this.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
	
		/*
		 * Construct First Page
		 */
		client.sendStatus(new Status("First Page - Begin"));//Update Status on server
		firstpage = new JPanel();
		pages[0] = firstpage;// add first page to the pages array
		
		add(firstpage);
		firstpage.setLayout(new BoxLayout(firstpage, BoxLayout.Y_AXIS));
		firstpage.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		firstpage.setBackground(Color.WHITE);
		pack();
		
		pTextQ = new JLabel("Please wait for instructions.");// before you press \"Begin\"");
		firstpage.add(Box.createVerticalStrut(HEIGHT/2));
		firstpage.add(pTextQ);
		pTextQ.setAlignmentX(CENTER_ALIGNMENT);
		pTextBeginButton = new JButton("Begin");		
		//firstpage.add(pTextBeginButton);//REMOVE COMPLETELY
		pTextBeginButton.setAlignmentX(CENTER_ALIGNMENT);

		//firstpage.setVisible(true);// show the first page
		//TODO: remove
		pTextBeginButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				client.sendStatus(new Status("Second Page - Charity Intro"));//Update Status on server
//				firstpage.setVisible(false);
//				secondpage.setVisible(true);
				selectPage(Status.Page.SECOND);
			}
		});
		
		
		
		
		/*
		 * Construct Second Page
		 */
		secondpage = new JPanel();
		pages[1] = secondpage;
		add(secondpage);
		secondpage.setLayout(new BoxLayout(secondpage, BoxLayout.Y_AXIS));
		secondpage.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		secondpage.setBackground(Color.WHITE);
		pack();
		
		JLabel gdscreenshot = new JLabel(new ImageIcon(getImage("img/GDScreenShot.png")));
		secondpage.add(Box.createVerticalStrut(HEIGHT/10));
		secondpage.add( gdscreenshot );
		gdscreenshot.setAlignmentX(CENTER_ALIGNMENT);
		
		pTextQ = new JLabel("Please wait");
		secondpage.add(pTextQ);
		pTextQ.setAlignmentX(CENTER_ALIGNMENT);
		
		
		pTextBeginButton = new JButton("Next");
		//secondpage.add(pTextBeginButton);//REMOVE COMPLETELY
		pTextBeginButton.setAlignmentX(CENTER_ALIGNMENT);
		
		//TODO: remove
		pTextBeginButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
//				client.sendStatus(new Status("Third Page - Charity Credits"));//Update Status on server
//				secondpage.setVisible(false);
//				thirdpage.setVisible(true);
				selectPage(Status.Page.THIRD);
			}
		});
		secondpage.setVisible(false);
		
		
		/*
		 * Construct Third Page
		 */
		thirdpage = new JPanel();
		pages[2] = thirdpage;
		add(thirdpage);
		thirdpage.setLayout(new BoxLayout(thirdpage, BoxLayout.Y_AXIS));
		thirdpage.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		thirdpage.setBackground(Color.WHITE);
		pack();
		
		JLabel gdpaul = new JLabel(new ImageIcon(getImage("img/Paul.png")));
		thirdpage.add(Box.createVerticalStrut(HEIGHT/8));
		thirdpage.add( gdpaul );
		gdpaul.setAlignmentX(CENTER_ALIGNMENT);
		
		pTextQ = new JLabel("Please wait");
		thirdpage.add(pTextQ);
		pTextQ.setAlignmentX(CENTER_ALIGNMENT);
		
		
		pTextBeginButton = new JButton("Next");
		//thirdpage.add(pTextBeginButton);//REMOVE COMPLETELY
		pTextBeginButton.setAlignmentX(CENTER_ALIGNMENT);
		
		//TODO: remove
		pTextBeginButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
//				client.sendStatus(new Status("Fourth Page - Selecte a Donatee"));//Update Status on server
//				thirdpage.setVisible(false);
//				fourthpage.setVisible(true);
//				Date date = new Date();
//				client.begin= (new Timestamp(date.getTime())).getTime();
				selectPage(Status.Page.FOURTH);
			}
		});
		thirdpage.setVisible(false);

		
		/*
		 * Construct Forth Page
		 */
		fourthpage = new JPanel();
		pages[3] = fourthpage;
		add(fourthpage);
		fourthpage.setLayout(new BoxLayout(fourthpage, BoxLayout.Y_AXIS));
		fourthpage.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		fourthpage.setBackground(Color.WHITE);
		pack();
		
		pPics = new JPanel(); //top panel Grid Layout for pictures
		fourthpage.add(pPics);
		pPics.setBackground(Color.WHITE);
		pPics.setLayout(new GridLayout(3,8,5,5));
		pPics.setBorder(new EmptyBorder(new Insets(5,5,5,5)));
		pPics.setPreferredSize(new Dimension(WIDTH,600));
		pack();
		
		pText = new JPanel(); //bottom panel for Money and stuff
		//pText.setBackground(Color.GREEN.darker().darker());
		fourthpage.add(pText);
		pText.setLayout(new BoxLayout(pText, BoxLayout.Y_AXIS));
		pText.setPreferredSize(new Dimension(WIDTH,168));
		pText.setBackground(Color.WHITE);
		pack();
		
	  
	    for(int i = 1; i < 25; i++){
	    	JLabel picLabel = new JLabel("" + i);
	    	picLabel.setFont(new Font("Serif", Font.BOLD, 20));

	    	ImageIcon icon = new ImageIcon("./downloaded/picture" + i + ".jpg");
	    	final ImageIcon oldImg = new ImageIcon("./downloaded/picture" + i + ".jpg");
	    	Image img = icon.getImage();  
	    	Image newimg = img.getScaledInstance( 165, 190,  java.awt.Image.SCALE_SMOOTH ) ;  
	    	icon = new ImageIcon( newimg );
		    //final JButton pic = new JButton((new ImageIcon("./downloaded/picture" + i + ".jpg")));
	    	final JButton pic = new JButton(icon);
	    	pic.setDisabledIcon(icon);
	    	pic.setEnabled(false);
		    pic.setName(""+i);
		    //System.out.println(pic.getName());
		    picLabel.setAlignmentY(TOP_ALIGNMENT);
		    picLabel.setForeground(Color.GREEN);
		    pic.add(picLabel);
		    
		    
		   if(client.getOption() == 1){  //else if option 1, ask how much donation to one picture
/*
		    	pic.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
//						Image img = oldImg.getImage();  
//						Image newimg = img.getScaledInstance( 165*2, 190*2,  java.awt.Image.SCALE_SMOOTH );						
//						pic.setDisabledIcon(new ImageIcon( newimg ));
						 
						pic.setEnabled(false);
						pText2 = new JPanel(); //bottom panel for Money and stuff
						pText2.setLayout(new BoxLayout(pText2, BoxLayout.Y_AXIS));
						pText2.setPreferredSize(new Dimension(WIDTH,168));
						pText2.setBackground(Color.WHITE);
						pack();

						JLabel strtStmt = new JLabel("Please Wait");
						pText2.add(Box.createVerticalGlue());
						pText2.add(strtStmt);
						strtStmt.setVisible(true);
						strtStmt.setAlignmentX(CENTER_ALIGNMENT);
					
						JButton strtbtn = new JButton("Next");
						pText2.add(strtbtn);
						pText2.add(Box.createVerticalGlue());
						strtbtn.setAlignmentX(CENTER_ALIGNMENT);
						strtbtn.addActionListener(new ActionListener() {

							public void actionPerformed(ActionEvent e) {
								System.err.println("Timer Begins");
								fourthpage.remove(pText2);
								fourthpage.add(pText);
								fourthpage.revalidate();
								fourthpage.repaint();
								pText.setVisible(true);
								Date date = new Date();
								client.begin= (new Timestamp(date.getTime())).getTime();
							}});

						//Date date = new Date();
						//client.begin = (new Timestamp(date.getTime())).getTime();
						for(int k = 0; k < pics.size(); k++){
							if (!pics.get(k).getName().equals(pic.getName()) ){
								if(client.getOption() == 1){
									pics.get(k).setVisible(false);
								}
							}
						}
						Vector<Object> v = new Vector<Object>();
						v.add(Integer.parseInt(pic.getName()));
						client.setChosenPic((Integer.parseInt(pic.getName())));
						
						pText.removeAll();
						pText.setBackground(Color.WHITE);
						String q_text = "How much would you like to donate to " + client.getChosenPic() + "?";
						pTextQ = new JLabel(q_text);
						pTextQ.setVisible(true);
						pText.add(Box.createVerticalStrut(168/3));
						pText.add(pTextQ);
						pTextQ.setAlignmentX(CENTER_ALIGNMENT);
						
//						ArrayList<String> dollarList = new ArrayList<String>();
//						int temp = (int)client.getBudget();
//						for(int i = 0; i <= temp; i++){
//							dollarList.add((new Integer(i)).toString());
//						}
//
//						ArrayList<String> centList = new ArrayList<String>();
//						for(int i = 0; i <= 9; i ++){
//							centList.add((new Integer(i)).toString() + "0");
//						}
//
//				  		pTextCB =  new JComboBox(dollarList.toArray());
//				  		pTextCB2 = new JComboBox(centList.toArray());
//				  		
				  		moneypanel = new JPanel();	
				  		moneypanel.setBackground(Color.WHITE);
				  		moneypanel.setAlignmentX(CENTER_ALIGNMENT);
//				  		JLabel dollarsign = new JLabel("$");
//				  		JLabel decimal = new JLabel(".");
//				  		
//				  		moneypanel.add(dollarsign);
//				  		moneypanel.add(pTextCB);
//				  		moneypanel.add(decimal);
//				  		moneypanel.add(pTextCB2);
						
				  		btnGroup = new ButtonGroup();					
	
//						ArrayList<String> dollarList = new ArrayList<String>();
				  		
				  		//Setup the Radio Buttons - Alex Gian
						int temp = (int)client.getBudget();
						for(int i = 0; i <= temp; i++){
							JRadioButton tmpButton = new JRadioButton('$' + (new Integer(i)).toString());
							tmpButton.setActionCommand((new Integer(i)).toString());
							tmpButton.setVerticalTextPosition(JRadioButton.TOP);
							tmpButton.setHorizontalTextPosition(JRadioButton.CENTER);
							
							btnGroup.add(tmpButton);
							moneypanel.add(tmpButton);							
						}
						
				  		pTextOKButton = new JButton("OK");
				  		pTextOKButton.addActionListener(new ActionListener() {				  			
							

							@Override
							public void actionPerformed(ActionEvent e) {								
								Float payment = (float) 0;
//								client.setChosenPayment(Float.parseFloat(((String) pTextCB.getSelectedItem()).replace("$","")) + Float.parseFloat("."+(String) pTextCB2.getSelectedItem()));
 							    
								//Check for the selected button and get its value
								if(btnGroup.getSelection() != null){
 							    	System.out.println("getSelected(): " + btnGroup.getSelection().getActionCommand());
 							    	payment = Float.parseFloat(btnGroup.getSelection().getActionCommand());
 							    }
								
								client.setChosenPayment(payment);
								if(client.getChosenPayment() > budget){
									JOptionPane.showMessageDialog(fourthpage, "You have exeeeded the budget of $"+budget+".", "Try Again", JOptionPane.INFORMATION_MESSAGE);
									return;
								}
								client.amts[client.getChosenPic()-1] = client.getChosenPayment();
								JLabel pTextTy = new JLabel("Thank you for your payment!");

								Date date = new Date();
								client.end = (new Timestamp(date.getTime())).getTime();

								pPics.setVisible(false);
								pText.removeAll();
								pText.setBackground(Color.WHITE);
								pText.add(Box.createVerticalStrut(HEIGHT/2));
								pTextTy.setAlignmentX(CENTER_ALIGNMENT);
								pText.add(pTextTy);
								pText.revalidate();
								repaint();
								client.sendData(1);
							}

							private Object selection() {
								// TODO Auto-generated method stub
								return null;
							}});
				  		
				  		pText.add(Box.createVerticalGlue());
				  		pText.add(moneypanel);
				  		pText.add(pTextOKButton);
				  		pText.add(Box.createVerticalGlue());
				  		
				  		pTextOKButton.setAlignmentX(CENTER_ALIGNMENT);

						fourthpage.remove(pText);
						fourthpage.add(pText2);
						fourthpage.revalidate();
						fourthpage.repaint();

				  		pText2.setVisible(true);
					}
				});	
		    	
		    	
*/	    	
		    }else if(client.getOption() == 2){ // if option 2, ask how much donation to everyone
		    	
		    	//text box taken care of at the bottom of the page
		    	
		    }else if(client.getOption() == 3){ //if option 3, ask how much one by one
		    	    	
		    	pic.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						pic.setEnabled(false);
						showOne(0);
						Date date = new Date();
						client.end = (new Timestamp(date.getTime())).getTime();
					}
		
				});	
		    	
		    }
			pics.add(pic);
	    }
	   
		//add all pictures to grid for options 1 and 2 
 		for (int i =1; i < 25; i++){
		    pPics.add("h",pics.get(i-1));
		}
 		
 		
 		//set up text box accordingly
 		if(client.getOption() == 1){

 			String q_text = "The family with your number will receive your donation.";
 			pText.setBackground(Color.WHITE);
	    	pTextQ = new JLabel(q_text);
			pTextQ.setVisible(true);
			pText.add(Box.createVerticalGlue());
			pText.add(pTextQ);
			pText.add(Box.createVerticalGlue());
			pTextQ.setAlignmentX(CENTER_ALIGNMENT);
 			
 		}else if(client.getOption() == 2){

			pText2 = new JPanel(); //bottom panel for Money and stuff
			pText2.setLayout(new BoxLayout(pText2, BoxLayout.Y_AXIS));
			pText2.setPreferredSize(new Dimension(WIDTH,168));
			pText2.setBackground(Color.WHITE);
			pack();

			JLabel strtStmt = new JLabel("Please Wait");
			pText2.add(Box.createVerticalGlue());
			pText2.add(strtStmt);
			strtStmt.setVisible(true);
			strtStmt.setAlignmentX(CENTER_ALIGNMENT);
		
			JButton strtbtn = new JButton("Next");
			pText2.add(strtbtn);
			pText2.add(Box.createVerticalGlue());
			strtbtn.setAlignmentX(CENTER_ALIGNMENT);
			strtbtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					fourthpage.remove(pText2);
					fourthpage.add(pText);
					fourthpage.revalidate();
					fourthpage.repaint();
					pText.setVisible(true);
					Date date = new Date();
					client.begin= (new Timestamp(date.getTime())).getTime();
				}});

 			String q_text = "How much would you like to donate?";
	    	pTextQ = new JLabel(q_text);
			pTextQ.setVisible(true);
			pText.add(Box.createVerticalGlue());
			pText.add(pTextQ);
			pTextQ.setAlignmentX(CENTER_ALIGNMENT);
			
			ArrayList<String> dollarList = new ArrayList<String>();
			int temp = (int)client.getBudget();
			for(int i = 0; i <= temp; i++){
				dollarList.add((new Integer(i)).toString());
			}

			ArrayList<String> centList = new ArrayList<String>();
			for(int i = 0; i <= 9; i ++){
				centList.add((new Integer(i)).toString() + "0");
			}

	  		pTextCB =  new JComboBox(dollarList.toArray());
	  		pTextCB2 = new JComboBox(centList.toArray());
	  		
	  		moneypanel = new JPanel();
	  		moneypanel.setBackground(Color.WHITE);
	  		moneypanel.setAlignmentX(CENTER_ALIGNMENT);
	  		JLabel dollarsign = new JLabel("$");
	  		JLabel decimal = new JLabel(".");
	  		
	  		moneypanel.add(dollarsign);
	  		moneypanel.add(pTextCB);
	  		moneypanel.add(decimal);
	  		moneypanel.add(pTextCB2);
	  		
	  		pTextOKButton = new JButton("OK");
	  		pTextOKButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					client.chosenPayment = (Float.parseFloat(((String) pTextCB.getSelectedItem()).replace("$","")) + Float.parseFloat("." + (String) pTextCB2.getSelectedItem()));
					if(client.chosenPayment > budget){
						JOptionPane.showMessageDialog(fourthpage, "You have exeeeded the budget of $"+budget+".", "Try Again", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					JLabel pTextTy = new JLabel("Thank you for your payment!");
					Date date = new Date();
					client.end = (new Timestamp(date.getTime())).getTime();
					//TODO: Image Show Center
					pPics.setVisible(false);
					pText.removeAll();
					pText.setBackground(Color.WHITE);
					pText.add(Box.createVerticalStrut(HEIGHT/2));
					pTextTy.setAlignmentX(CENTER_ALIGNMENT);
					pText.add(pTextTy);
					pText.revalidate();
					repaint();
					client.sendData(2);
 			
				}});
	  		
	  		pText.add(moneypanel);
	  		pText.add(pTextOKButton);
	  		pText.add(Box.createVerticalGlue());
	  		pTextOKButton.setAlignmentX(CENTER_ALIGNMENT);

			fourthpage.remove(pText);
			fourthpage.add(pText2);
			fourthpage.revalidate();
			fourthpage.repaint();
	  		pText2.setVisible(true);
 			
 		}else if(client.getOption() == 3){

 			String q_text = "Please Press any picture to Begin";
	    	pTextQ = new JLabel(q_text);
			pTextQ.setVisible(true);
			pText.add(Box.createVerticalGlue());
			pText.add(pTextQ);
			pText.add(Box.createVerticalGlue());
			pTextQ.setAlignmentX(CENTER_ALIGNMENT);
	
		  		
 		}else{
 			
 			String q_text = "Please wait";
 			pTextQ = new JLabel(q_text);
 			pTextNextButton = new JButton("Next");
 			pTextQ.setAlignmentX(CENTER_ALIGNMENT);
 			pTextNextButton.setAlignmentX(CENTER_ALIGNMENT);
 			pText.add(Box.createVerticalGlue());
 			pText.add(pTextQ);
 			pText.add(pTextNextButton);
 			pText.add(Box.createVerticalGlue());
 			
 			pTextNextButton.addActionListener(new ActionListener() {

 				@Override
 				public void actionPerformed(ActionEvent e) {
 					fourthpage.setVisible(false);
 					fifthpage.setVisible(true);
 					Date date = new Date();
					client.begin = (new Timestamp(date.getTime())).getTime();
					System.out.println("Begin Time: " + elapsedTime());
 				}});
 			
 		}
 	
 		fourthpage.setVisible(false);
		
		///////////////////////////////////////////////construct fifth page for options 4 and 5///////////////////////////
 		if(client.getOption() >= 4 && client.getOption() <= 7)
 		{
			fifthpage = new JPanel();
			add(fifthpage);
			fifthpage.setLayout(new BoxLayout(fifthpage, BoxLayout.Y_AXIS));
			fifthpage.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			fifthpage.setBackground(Color.WHITE);
			pack();
			
			CBoptionA = new JCheckBox();
			CBoptionB = new JCheckBox();
			CBoptionA.setBackground(Color.WHITE);
			CBoptionB.setBackground(Color.WHITE);
			
			if(client.getOption() == 4){
				
				CBoptionA.setText("<html>Give to all 24:<br>" +
						"Your donation will be divided equally among the 24 recipient.<br>" +
						"You will see photos of all 24 recipients when making your decision.</html>");
				CBoptionB.setText("<html>Give to 1:<br>" +
						"Before your decision, a single recipient will be assigned just to you and no one else.<br>" +
						"You will see only the photo of the one recipient assigned to you when making your decision.</html>");
			}else if(client.getOption() == 5){
				CBoptionA.setText("<html>Give to 1, Assigned <u>after</u> you decide:<br>" +
						"After you leave today, a single recipient will be assigned just to you and no one else.<br>" +
						"You will see photos of all 24 possible recipients when making your decision.</html>");
				CBoptionB.setText("<html>Give to 1, Assigned <u>before</u> you decide:<br>" +
						"Before your decision, a single recipient will be assigned just to you and no one else.<br>" +
						"You will see only the photo of the one recipient assigned to you when making your decision.");	
			}else if(client.getOption() == 6){
				CBoptionB.setText("<html>Give to all 24:<br>" +
						"Your donation will be divided equally among the 24 recipient.<br>" +
						"You will see photos of all 24 recipients when making your decision.</html>");
				CBoptionA.setText("<html>Give to 1:<br>" +
						"Before your decision, a single recipient will be assigned just to you and no one else.<br>" +
						"You will see only the photo of the one recipient assigned to you when making your decision.</html>");
			}else{
				CBoptionB.setText("<html>Give to 1, Assigned <u>after</u> you decide:<br>" +
						"After you leave today, a single recipient will be assigned just to you and no one else.<br>" +
						"You will see photos of all 24 possible recipients when making your decision.</html>");
				CBoptionA.setText("<html>Give to 1, Assigned <u>before</u> you decide:<br>" +
						"Before your decision, a single recipient will be assigned just to you and no one else.<br>" +
						"You will see only the photo of the one recipient assigned to you when making your decision.");	
			}
			
			
			class CheckBoxListener implements ActionListener {
			
			    public void actionPerformed(ActionEvent e) {
			        Object source = e.getSource();
			        
			        if(source == CBoptionA)
			        {
			        	if(CBoptionA.isSelected())
			        	{
			        		CBoptionB.setSelected(false);
			        		(client.decision_types).add(0);
			        		(client.decision_times).add(elapsedTime());
				        	System.out.println("Time: " + elapsedTime() + " Checked Box 1");
			        	}
			        	else
			        	{
			        		(client.decision_types).add(1);
			        		(client.decision_times).add(elapsedTime());
				        	System.out.println("Time: " + elapsedTime() + " Unchecked Box 1" );
			        	}
			        }else{
			        	
			        	if(CBoptionB.isSelected())
			        	{
			        		CBoptionA.setSelected(false);
			        		(client.decision_types).add(2);
			        		(client.decision_times).add(elapsedTime());
				        	System.out.println("Time: " + elapsedTime()+ " Checked Box 2");
			        	}
			        	else
			        	{
			        		(client.decision_types).add(3);
			        		(client.decision_times).add(elapsedTime());
				        	System.out.println("Time: " + elapsedTime()+ " Unchecked Box 2");
			        	}
			  
			        }
			        
			    }
			}
			
			CheckBoxListener myListener = new CheckBoxListener();
			CBoptionA.addActionListener(myListener);
			CBoptionB.addActionListener(myListener);
			
			JPanel choicepanel = new JPanel();
			choicepanel.setMaximumSize(CBoptionA.getWidth() > CBoptionB.getWidth() ? CBoptionA.getPreferredSize() : CBoptionB.getPreferredSize());
			choicepanel.setLayout(new BoxLayout(choicepanel, BoxLayout.Y_AXIS));
			choicepanel.setBackground(Color.WHITE);
			choicepanel.add(CBoptionA);
			choicepanel.add(Box.createRigidArea(new Dimension(0, 20)));
			CBoptionA.setAlignmentX(CENTER_ALIGNMENT);
			choicepanel.add(CBoptionB);
			CBoptionB.setAlignmentX(CENTER_ALIGNMENT);
			choicepanel.setAlignmentX(CENTER_ALIGNMENT);
			
	
			pTextNextButton = new JButton("Make your decision");
			pTextNextButton.setAlignmentX(Component.CENTER_ALIGNMENT);
	
			pTextNextButton.addActionListener(new ActionListener() {
	
				@Override
				public void actionPerformed(ActionEvent e) {
					
					if( !CBoptionA.isSelected() && !CBoptionB.isSelected()){
						JOptionPane.showMessageDialog(fifthpage, "You must choose a donation type!", "Try Again", JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					
					fifthpage.setVisible(false);
					
					(client.decision_types).add(4);
					(client.decision_times).add(elapsedTime());
					System.out.println("Decision Time: " + elapsedTime());
					Date date = new Date();
					client.begin = (new Timestamp(date.getTime())).getTime();
					
					int opttype;
					if(CBoptionA.isSelected())
					{
						opttype = 1;
						client.chosenDecision = 0;
					}
					else
					{
						opttype = 2;
						client.chosenDecision = 1;
					}
					
					fourthpage = new JPanel();
					add(fourthpage);
					fourthpage.setLayout(new BoxLayout(fourthpage, BoxLayout.Y_AXIS));
					fourthpage.setPreferredSize(new Dimension(WIDTH,HEIGHT));
					fourthpage.setBackground(Color.WHITE);
					pack();
					
					
					pPics = new JPanel(); //top panel Grid Layout for pictures
					fourthpage.add(pPics);
					pPics.setBackground(Color.WHITE);
					pPics.setLayout(new GridLayout(3,8,5,5));
					pPics.setBorder(new EmptyBorder(new Insets(5,5,5,5)));
					pPics.setPreferredSize(new Dimension(WIDTH,600));
					pack();
					
					pText = new JPanel(); //bottom panel for Money and stuff
					//pText.setBackground(Color.GREEN.darker().darker());
					fourthpage.add(pText);
					pText.setLayout(new BoxLayout(pText, BoxLayout.Y_AXIS));
					pText.setPreferredSize(new Dimension(WIDTH,168));
					pText.setBackground(Color.WHITE);
					pack();
				   
					//add all pictures to grid for options 1 and 2 
	
			 		for (int i =1; i < 25; i++){
					    pPics.add("h",pics.get(i-1));
					}
			 		
			 		if(opttype == 2 && (client.getOption() == 4 || client.getOption() == 5) )
			 		{
			 			for(int i =1; i < 25; i++)
			 			{
			 				if(i != Integer.parseInt(client.id))
			 					pics.get(i-1).setVisible(false);
			 			}
			 		}else if(opttype == 1 && (client.getOption() == 6 || client.getOption() == 7))
			 		{
			 			for(int i =1; i < 25; i++)
			 			{
			 				if(i != Integer.parseInt(client.id))
			 					pics.get(i-1).setVisible(false);
			 			}
			 		}
					
			 		//set up text box 
			 		
					//client.begin= (new Timestamp(date.getTime())).getTime();
		 			
			 		String q_text = "How much would you like to donate?";
			    	pTextQ = new JLabel(q_text);
					pTextQ.setVisible(true);
					pText.add(Box.createVerticalGlue());
					pText.add(pTextQ);
					pTextQ.setAlignmentX(CENTER_ALIGNMENT);
					
					ArrayList<String> dollarList = new ArrayList<String>();
					int temp = (int)client.getBudget();
					for(int i = 0; i <= temp; i++){
						dollarList.add((new Integer(i)).toString());
					}
	
					ArrayList<String> centList = new ArrayList<String>();
					for(int i = 0; i <= 9; i ++){
						centList.add((new Integer(i)).toString() + "0");
					}
	
			  		pTextCB =  new JComboBox(dollarList.toArray());
			  		pTextCB2 = new JComboBox(centList.toArray());
			  		
			  		moneypanel = new JPanel();
			  		moneypanel.setBackground(Color.WHITE);
			  		moneypanel.setAlignmentX(CENTER_ALIGNMENT);
			  		JLabel dollarsign = new JLabel("$");
			  		JLabel decimal = new JLabel(".");
			  		
			  		moneypanel.add(dollarsign);
			  		moneypanel.add(pTextCB);
			  		moneypanel.add(decimal);
			  		moneypanel.add(pTextCB2);
			  		
			  		pTextOKButton = new JButton("OK");
			  		pTextOKButton.addActionListener(new ActionListener() {
	
						@Override
						public void actionPerformed(ActionEvent e) {
							client.chosenPayment = (Float.parseFloat(((String) pTextCB.getSelectedItem()).replace("$","")) + Float.parseFloat("." + (String) pTextCB2.getSelectedItem()));
							if(client.chosenPayment > budget){
								JOptionPane.showMessageDialog(fourthpage, "You have exeeeded the budget of $"+budget+".", "Try Again", JOptionPane.INFORMATION_MESSAGE);
								return;
							}
							
							JLabel pTextTy = new JLabel("Thank you for your payment!");
							Date date = new Date();
							client.end = (new Timestamp(date.getTime())).getTime();
							(client.decision_types).add(5);
							(client.decision_times).add(elapsedTime());
			
							pPics.setVisible(false);
							pText.removeAll();
							pText.setBackground(Color.WHITE);
							pText.add(Box.createVerticalStrut(HEIGHT/2));
							pTextTy.setAlignmentX(CENTER_ALIGNMENT);
							pText.add(pTextTy);
							pText.revalidate();
							repaint();
							System.out.println(client.decision_types);
							client.sendData(client.getOption());
		 			
						}});
			  		
			  		pText.add(moneypanel);
			  		pText.add(pTextOKButton);
			  		pText.add(Box.createVerticalGlue());
			  		pTextOKButton.setAlignmentX(CENTER_ALIGNMENT);
	
			  		pText.setVisible(true);
			 			
			 	}
			 					
			});
			
			fifthpage.add(Box.createVerticalGlue());
			fifthpage.add(choicepanel);
			fifthpage.add(Box.createRigidArea(new Dimension(0, 20)));
			fifthpage.add(pTextNextButton);
			fifthpage.add(Box.createVerticalGlue());
			
			fifthpage.setVisible(false);
 		}
		
		setVisible(true);
		
	}
	
	/**
	 * Used to select the page to display to the user
	 * @param pageNumber
	 */
	public void selectPage(Status.Page page) {
		switch(page) {
			case FIRST:
				setPageVisible(0);
				client.sendStatus(new Status("First Page - Begin"));//Update Status on server
				break;
			case SECOND:
				setPageVisible(1);
				client.sendStatus(new Status("Second Page - Charity Intro"));//Update Status on server
				break;
			case THIRD:
				setPageVisible(2);
				client.sendStatus(new Status("Third Page - Charity Credits"));//Update Status on server
				break;
			case FOURTH:
				setPageVisible(3);
				client.sendStatus(new Status("Fourth Page - Families"));//Update Status on server
				Date date = new Date();
				//client.begin= (new Timestamp(date.getTime())).getTime();
				break;
			case FIFTH:
				System.out.println("FIFTH PAGE!");
				client.sendStatus(new Status("Fifth Page - Donatee Selected, Waiting..."));//Update Status on server
				selectFamily();
				break;
			case SIXTH:
				client.sendStatus(new Status("Sixth Page - Choose Donation"));//Update Status on server
				if(client.getOption() == 1) {					
					System.err.println("Timer Begins");
					fourthpage.remove(pText2);
					fourthpage.add(pText);
					fourthpage.revalidate();
					fourthpage.repaint();
					pText.setVisible(true);
					Date date1 = new Date();
					client.begin= (new Timestamp(date1.getTime())).getTime();
				}
				break;
		default:
			break;
		}
	}
	
	private void setPageVisible(int pNum){
		for (int i = 0; i < MAX_PAGES; i ++) {
			if( i == pNum ) {
				pages[pNum].setVisible(true);
			} else {
				pages[i].setVisible(false);
			}
		}
	}
	
	public Image getImage(String path){
		URL urlImage = getClass().getResource(path);
		Image img = Toolkit.getDefaultToolkit().getImage(urlImage);

		return img;
	}
	
	public void showOne(final int index){
		
		if(index == 24)
		{
			Date date = new Date();
			client.end = (new Timestamp(date.getTime())).getTime();
			client.sendData(3);
			JLabel pTextTy = new JLabel("Thank you for your payment!");
			//pPics.removeAll();
			//frame.remove(pPics);
			//pPics.add(pTextTy);
			//pPics.setBackground(Color.WHITE);
			//pPics.revalidate();
			//frame.repaint();
			pPics.setVisible(false);
			//pText.setVisible(false);
			pText.removeAll();
			pText.setBackground(Color.WHITE);
			pText.add(Box.createVerticalStrut(HEIGHT/2));
			pTextTy.setAlignmentX(CENTER_ALIGNMENT);
			pText.add(pTextTy);
			pText.revalidate();
			pText.setVisible(true);
			repaint();
			return;
		}
		
		for(int k = 0; k < pics.size(); k++){
			if (!pics.get(k).getName().equals(pics.get(index).getName())){
				pics.get(k).setVisible(false);
			}
			else {
				pics.get(k).setVisible(true);
			}
			pics.get(k).setEnabled(false);
		}
		
		pText.removeAll();
		pText.setBackground(Color.WHITE);

		Date date = new Date();
		client.individual_times[index] = (new Timestamp(date.getTime())).getTime();

		String q_text = "How much would you like to donate to " + (++counter) + "?";
		DecimalFormat form = new DecimalFormat("0.00");
		System.out.println("CLIENTGUI: " + budget);
		String remain = "(Remaining Amount: $"+ form.format(this.budget-sum) +")";
		System.out.println(remain);
		pTextQ = new JLabel(q_text);
		pTextQ.setVisible(true);
		pText.add(Box.createVerticalGlue());
		pText.add(pTextQ);
		pTextQ.setAlignmentX(CENTER_ALIGNMENT);
		pRemain = new JLabel(remain);
		pRemain.setForeground(Color.GREEN.darker().darker());
		pRemain.setVisible(true);
		pText.add(pRemain);
		pRemain.setAlignmentX(CENTER_ALIGNMENT);
		ArrayList<String> dollarList = new ArrayList<String>();
		int temp = (int)client.getBudget();
		for(int i = 0; i <= temp; i++){			
			dollarList.add((new Integer(i)).toString());
		}

		ArrayList<String> centList = new ArrayList<String>();
		for(int i = 0; i <= 9; i ++){
			centList.add((new Integer(i)).toString() + "0");
		}
  		pTextCB =  new JComboBox(dollarList.toArray());
  		pTextCB2 = new JComboBox(centList.toArray());
  		
  		moneypanel = new JPanel();
  		moneypanel.setBackground(Color.WHITE);
  		moneypanel.setAlignmentX(CENTER_ALIGNMENT);
  		JLabel dollarsign = new JLabel("$");
  		JLabel decimal = new JLabel(".");
  		
  		moneypanel.add(dollarsign);
  		moneypanel.add(pTextCB);
  		moneypanel.add(decimal);
  		moneypanel.add(pTextCB2);
  		
  		pTextOKButton = new JButton("Next");
  		pTextOKButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(sum + (Float.parseFloat(((String) pTextCB.getSelectedItem()).replace("$","")) + Float.parseFloat("."+(String) pTextCB2.getSelectedItem())) > budget){
					DecimalFormat form = new DecimalFormat("0.00");
					JOptionPane.showMessageDialog(fourthpage, "You have exeeeded the budget of $"+ form.format(budget), "Try Again", JOptionPane.INFORMATION_MESSAGE);
					return;
				}

				Date date = new Date();
				Long individual_pic_end = (new Timestamp(date.getTime())).getTime();
				System.out.println("TIME " + individual_pic_end);
				client.individual_times[index] = individual_pic_end - client.individual_times[index];
				client.amts[index] = (Float.parseFloat(((String) pTextCB.getSelectedItem()).replace("$","")) + Float.parseFloat("."+(String) pTextCB2.getSelectedItem()));
				sum = sum + (Float.parseFloat(((String) pTextCB.getSelectedItem()).replace("$","")) + Float.parseFloat("."+(String) pTextCB2.getSelectedItem()));
				pText.setVisible(false);
				showOne(index+1);
			}});
  	
  		pText.add(moneypanel);
  		pText.add(pTextOKButton);
  		pText.add(Box.createVerticalGlue());
  		pTextOKButton.setAlignmentX(CENTER_ALIGNMENT);
  		
  		
  		pText.setVisible(true);
	}
	
	public Long elapsedTime()
	{
		Date date = new Date();
		return new Timestamp(date.getTime()).getTime() - client.begin;
	}
	
	/**
	 * Select Family
	 */
	public void selectFamily(){
//		pic.setEnabled(false);
		pText2 = new JPanel(); //bottom panel for Money and stuff
		pText2.setLayout(new BoxLayout(pText2, BoxLayout.Y_AXIS));
		pText2.setPreferredSize(new Dimension(WIDTH,168));
		pText2.setBackground(Color.WHITE);
		pack();

		JLabel strtStmt = new JLabel("Please Wait");
		pText2.add(Box.createVerticalGlue());
		pText2.add(strtStmt);
		strtStmt.setVisible(true);
		strtStmt.setAlignmentX(CENTER_ALIGNMENT);
        
		
		// TODO:Remove listener and below
		// Go to the donation choice screen and start timer
		JButton strtbtn = new JButton("Next");
		//pText2.add(strtbtn);
		pText2.add(Box.createVerticalGlue());
		strtbtn.setAlignmentX(CENTER_ALIGNMENT);
		strtbtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.err.println("Timer Begins");
				fourthpage.remove(pText2);
				fourthpage.add(pText);
				fourthpage.revalidate();
				fourthpage.repaint();
				pText.setVisible(true);
				Date date = new Date();
				client.begin= (new Timestamp(date.getTime())).getTime();
			}});
		// TODO:REMOVE
		
		
		//Date date = new Date();
		//client.begin = (new Timestamp(date.getTime())).getTime();
		System.err.println("SIZE: " + pics.size());
		for(int k = 0; k < pics.size(); k++){
			if (!pics.get(k).getName().equals(client.id)){
				System.out.println("Pic Not Equal: "+pics.get(k).getName());
				if(client.getOption() == 1){
					pics.get(k).setVisible(false);
				}
			}
		}
		Vector<Object> v = new Vector<Object>();
		v.add(Integer.parseInt(client.id));
		client.setChosenPic(Integer.parseInt(client.id));

		pText.removeAll();
		pText.setBackground(Color.WHITE);
		String q_text = "How much would you like to donate to family " + client.getChosenPic() + "?";
		pTextQ = new JLabel(q_text);
		pTextQ.setVisible(true);
		pText.add(Box.createVerticalStrut(168/3));
		pText.add(pTextQ);
		pTextQ.setAlignmentX(CENTER_ALIGNMENT);

		moneypanel = new JPanel();	
		moneypanel.setBackground(Color.WHITE);
		moneypanel.setAlignmentX(CENTER_ALIGNMENT);


		btnGroup = new ButtonGroup();					

		int temp = (int)client.getBudget();
		for(int i = 0; i <= temp; i++){
			JRadioButton tmpButton = new JRadioButton('$' + (new Integer(i)).toString());
			tmpButton.setActionCommand((new Integer(i)).toString());
			tmpButton.setVerticalTextPosition(JRadioButton.TOP);
			tmpButton.setHorizontalTextPosition(JRadioButton.CENTER);

			btnGroup.add(tmpButton);
			moneypanel.add(tmpButton);							
		}

		pTextOKButton = new JButton("OK");
		pTextOKButton.addActionListener(new ActionListener() {				  			


			@Override
			public void actionPerformed(ActionEvent e) {								
				Float payment = (float) 0;
				//						client.setChosenPayment(Float.parseFloat(((String) pTextCB.getSelectedItem()).replace("$","")) + Float.parseFloat("."+(String) pTextCB2.getSelectedItem()));

				//Check for the selected button and get its value
				if(btnGroup.getSelection() != null){
					System.out.println("getSelected(): " + btnGroup.getSelection().getActionCommand());
					payment = Float.parseFloat(btnGroup.getSelection().getActionCommand());
				}

				client.setChosenPayment(payment);
				if(client.getChosenPayment() > budget){
					JOptionPane.showMessageDialog(fourthpage, "You have exeeeded the budget of $"+budget+".", "Try Again", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				client.amts[client.getChosenPic()-1] = client.getChosenPayment();
				JLabel pTextTy = new JLabel("Thank you for your payment!");

				Date date = new Date();
				client.end = (new Timestamp(date.getTime())).getTime();

				pPics.setVisible(false);
				pText.removeAll();
				pText.setBackground(Color.WHITE);
				pText.add(Box.createVerticalStrut(HEIGHT/2));
				pTextTy.setAlignmentX(CENTER_ALIGNMENT);
				pText.add(pTextTy);
				pText.revalidate();
				repaint();
				client.sendData(1);
			}

			private Object selection() {
				// TODO Auto-generated method stub
				return null;
			}});

		pText.add(Box.createVerticalGlue());
		pText.add(moneypanel);
		pText.add(pTextOKButton);
		pText.add(Box.createVerticalGlue());

		pTextOKButton.setAlignmentX(CENTER_ALIGNMENT);

		fourthpage.remove(pText);
		fourthpage.add(pText2);
		fourthpage.revalidate();
		fourthpage.repaint();

		pText2.setVisible(true);
	}
	
	
	
}


