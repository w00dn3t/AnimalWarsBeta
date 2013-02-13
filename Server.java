
 
import java.net.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.io.*;

import javax.swing.*;
 
public class Server {
		public final static String END_CHAT_SESSION = new Character((char)0).toString(); 
		public final static	int MAXConnect = 2;
		public static int CURRENTConnect = 0;
		private static Socket client1Socket = null;
		private static Socket client2Socket = null;
		public static JFrame mainFrame = null;
		public static JTextArea chatText = null;
		public static JTextField chatLine = null;
		public static JPanel statusBar = null;
		public static JLabel statusField = null;
		public static JTextField statusColor = null;
		public static JTextField ipField = null;
		public static JTextField portField = null;
		public static JRadioButton hostOption = null;
		public static JRadioButton guestOption = null;
		public static JButton connectButton = null;
		public static JButton disconnectButton = null;
		public static boolean connected = false;
		public static String attackNum;
		public static String randNum;

		//Animal and user creation
		public static Animal client1Animal;
		public static String client1Ani;
		public static Animal client2Animal;
		public static String client2Ani;
		public static String client1Username;
		public static String client2Username;
		public static boolean setupGame1 = true;
		public static boolean setupGame2 = true;
		public static int turn = 0;

		private static void initGUI() {
	      // Set up the status bar
	      statusField = new JLabel();
	      statusField.setText("");
	      statusColor = new JTextField(1);
	      statusColor.setBackground(Color.red);
	      statusColor.setEditable(false);
	      statusBar = new JPanel(new BorderLayout());
	      statusBar.add(statusColor, BorderLayout.WEST);
	      statusBar.add(statusField, BorderLayout.CENTER);

	      // Set up the chat pane
	      JPanel chatPane = new JPanel(new BorderLayout());
	      chatText = new JTextArea(10, 20);
	      chatText.setLineWrap(true);
	      chatText.setEditable(false);
	      chatText.setForeground(Color.blue);
	      JScrollPane chatTextPane = new JScrollPane(chatText,
	         JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
	         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	     
	      chatPane.add(chatTextPane, BorderLayout.CENTER);
	      chatPane.setPreferredSize(new Dimension(200, 200));

	      // Set up the main pane
	      JPanel mainPane = new JPanel(new BorderLayout());
	      mainPane.add(statusBar, BorderLayout.SOUTH);
	      mainPane.add(chatPane, BorderLayout.CENTER);

	      // Set up the main frame
	      mainFrame = new JFrame("Simple TCP Chat");
	      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      mainFrame.setContentPane(mainPane);
	      mainFrame.setSize(mainFrame.getPreferredSize());
	      mainFrame.setLocation(200, 200);
	      mainFrame.pack();
	      mainFrame.setVisible(true);
	   }
	   public static void appendTextArea(String s){
		   chatText.append(s + "\n");
	   }
	   public static void setStatus(String s, Color clr){
		      statusField.setText(s);
		      statusColor.setBackground(clr);
	   }
	   public static void main(String[] args) throws IOException {
	   initGUI();

        ServerSocket serverSocket1 = null;
        ServerSocket serverSocket2 = null;

        try {
            serverSocket1 = new ServerSocket(7777);
            serverSocket2 = new ServerSocket(7778);
        	appendTextArea("Launching chat server, welcome to Animal Wars.");

        } catch (IOException e) {
        	setStatus("Listen failed!", Color.red);
        	chatText.setForeground(Color.red);
        	appendTextArea("Check if another server is running.");
            appendTextArea("Could not listen on port: 7777 or 7778.");
            System.exit(1);
        }

     
        
        
        

        try {
        	while (CURRENTConnect < MAXConnect){
        		try {
        		Thread.sleep(10);
        		} catch (InterruptedException e){}
        		
        		client1Socket = serverSocket1.accept();
        		setStatus("Accept 1 complete!", Color.yellow);
        		chatText.setForeground(Color.yellow);
        		appendTextArea("Socket: " + serverSocket1 + " has connected");
        		CURRENTConnect++;
        		
        		client2Socket = serverSocket2.accept();
        		setStatus("Accept 2 compleye!", Color.yellow);
        		appendTextArea("Socket: " + serverSocket2 + " has connected");
        		CURRENTConnect++;
        	
        	}

        } catch (IOException e) {
        	chatText.setForeground(Color.red);
        	setStatus("Accept failed!", Color.red);
        	appendTextArea("Accept 1 or 2 failed.");
            System.exit(1);
        }
    	setStatus("Connection Established!", Color.green);
    	appendTextArea("Connection Established!");
    	chatText.setForeground(Color.green);

        PrintWriter out1 = new PrintWriter(client1Socket.getOutputStream(), true);
        PrintWriter out2 = new PrintWriter(client2Socket.getOutputStream(), true);

        BufferedReader in1 = new BufferedReader(new InputStreamReader(client1Socket.getInputStream()));
        BufferedReader in2 = new BufferedReader(new InputStreamReader(client2Socket.getInputStream()));

        //In and out typing
        String inputLine, outputLine1, outputLine2;
        
        //Creation of Animals, user1 and user2, related Strings and objects.
        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        
    

       
 boolean jeff = true;
        while (jeff){
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				   out1.close();
			        in1.close();
			        client1Socket.close();
			        serverSocket1.close();
			        out2.close();
			        in2.close();
			        client2Socket.close();
			        serverSocket2.close();
			}
        	outputLine1 = null; outputLine2 = null;
            if (in1.ready()) {
            	if (setupGame1){
            	    client1Ani = in1.readLine();
                    client1Username = in1.readLine();
                    appendTextArea(client1Username + ": Animal: " + client1Ani + " Username: " + client1Username);
            		out2.println(client1Ani);
            		out2.println(client1Username);
            		out2.println("1");
            		setupGame1 = false;
            	} else if (((inputLine = in1.readLine()) != null) &&  (inputLine.length() != 0) && !setupGame1 && !setupGame2) {
                	if (inputLine.equals(END_CHAT_SESSION))
                	{
                		endTransmission(1);
            		}
                	else 
                	{
                		appendTextArea("Client1: " + inputLine); 
                		outputLine1 = inputLine;
                		out2.println(outputLine1);
                	}
            	}
            }
            
            if (in2.ready()) {
            	if (setupGame2){
            		client2Ani = in2.readLine();
            	    client2Username = in2.readLine();
            		out1.println(client2Ani);
            		out1.println(client2Username);
            		out1.println("2");
            	    appendTextArea(client2Username + ": Animal: " + client2Ani + " Username: " + client2Username);
            		setupGame2 = false;
            	} else if (((inputLine = in2.readLine()) != null) &&  (inputLine.length() != 0) && !setupGame1 && !setupGame2) {
                	if (inputLine.equals(END_CHAT_SESSION))
                	{
                		endTransmission(0);
            		}
                	else if ((attackNum = inputLine.substring(0,1)).equals("0") | attackNum.equals("1") | attackNum.equals("2") | attackNum.equals("3"))
                	{
                		randNum = inputLine.substring(1,2);
                	}
                	else
                	{
                		appendTextArea("Client2: " + inputLine);
            			outputLine2 = inputLine;
            			out1.println(outputLine2);

            		}
                }
            }
            
        }

    }
	   public static void endTransmission(int i){
		   chatText.setForeground(Color.red);
		   setStatus("Disconnected||Transmission Over", Color.red);
		   if (i == 1)
		   appendTextArea(client1Socket + "(Client 1) has been disconnected.");
		   if (i==0)
		appendTextArea(client2Socket + "(Client 2) has been disconnected.");
 
	   }
}
