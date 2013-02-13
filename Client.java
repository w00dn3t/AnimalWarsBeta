

import java.lang.*;
import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

public class Client implements Runnable {
   // Connect status constants
   public final static int NULL = 0;
   public final static int DISCONNECTED = 1;
   public final static int DISCONNECTING = 2;
   public final static int BEGIN_CONNECT = 3;
   public final static int CONNECTED = 4;
   
   public static int currentTurn = 1;
   public static int turn = 3;

   // Game Vital Components
   public static String[] animals = {"Aardvark", "Ocelot"
   };
   public static String myAni;
   public static String myUsername;
   public static String enemyAni;
   public static String enemyUsername;
   public static Animal myAnimal;
   public static Animal enemyAnimal;
   public static boolean setupGameRecieve = true;
   public static boolean setupGameSend = true;
   public static String attackNum1;
   public static String randNum1;
   public static String attackNum2;
   public static String randNum2;

   
   // Other constants
   public final static String statusMessages[] = {
      " Error! Could not connect!", " Disconnected",
      " Disconnecting...", " Connecting...", " Connected"
   };
   public final static Client tcpObj = new Client();
   public final static String END_CHAT_SESSION =
      new Character((char)0).toString(); // Indicates the end of a session

   // Connection atate info
   public static String hostIP = "localhost";
   public static int port = 1234;
   public static int connectionStatus = DISCONNECTED;
   public static boolean isHost = true;
   public static String statusString = statusMessages[connectionStatus];
   public static StringBuffer toAppend = new StringBuffer("");
   public static StringBuffer toSend = new StringBuffer("");

   // Various GUI components and info
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
   
   // Login Options
   public static JTextField loginField = null;
   public static JTextField passwordField = null;
   public static JComboBox animalCombo = null;

   // Combat GUI
   public static JButton abil1Button = null;
   public static JButton abil2Button = null;
   public static JButton abil3Button = null;
   public static JButton abil4Button = null;
   public static JTextArea stat1Text = null;
   public static JTextArea stat2Text = null;
   public static JLabel stat1Label = null;
   public static JLabel stat2Label = null;
   
   // TCP Components
   public static ServerSocket hostServer = null;
   public static Socket socket = null;
   public static BufferedReader in = null;
   public static PrintWriter out = null;

   /////////////////////////////////////////////////////////////////
   private static JPanel initAbilityPane() {
	      // Ability Buttons
	      abil1Button = new JButton("Ability 1");
	      abil1Button.addActionListener(new ActionAdapter() {
	          public void actionPerformed(ActionEvent e) {
	             String s = "0" + Animal.rdzr();
	             if (!s.equals("") && currentTurn == turn) {
	                // Send the string
	        		attackNum1 = s.toString().substring(0, 1);
	        		randNum1 = s.toString().substring(1, 2);

	                sendString(s);                 
	             }
	          }
	      });
	      abil2Button = new JButton("Ability 2");
	      abil2Button.addActionListener(new ActionAdapter() {
	          public void actionPerformed(ActionEvent e) {
	        	  String s = "1" + Animal.rdzr();
		             if (currentTurn == turn) {
		                // Send the string
		        		attackNum1 = s.toString().substring(0, 1);
		        		randNum1 = s.toString().substring(1, 2);

		                sendString(s);                 
		             }
	          }
	      });
	      abil3Button = new JButton("Ability 3");
	      abil3Button.addActionListener(new ActionAdapter() {
	          public void actionPerformed(ActionEvent e) {
	        	  String s = "2" + Animal.rdzr();
		             if (currentTurn == turn) {
		                // Send the string
		        		attackNum1 = s.toString().substring(0, 1);
		        		randNum1 = s.toString().substring(1, 2);

		                sendString(s);                 
		             }
	          }
	      });
	      abil4Button = new JButton("Ability 4");
	      abil4Button.addActionListener(new ActionAdapter() {
	          public void actionPerformed(ActionEvent e) {
	        	  String s = "3" + Animal.rdzr();
		             if (currentTurn == turn) {
		                // Send the string
		        		attackNum1 = s.toString().substring(0, 1);
		        		randNum1 = s.toString().substring(1, 2);

		                sendString(s);                 
		             }
	          }
	      });
	      abil1Button.setEnabled(false);
	      abil2Button.setEnabled(false);
	      abil3Button.setEnabled(false);
	      abil4Button.setEnabled(false);
	      JPanel buttonPane = new JPanel(new GridLayout(4, 1));

	      buttonPane.add(abil1Button);
	      buttonPane.add(abil2Button);
	      buttonPane.add(abil3Button);
	      buttonPane.add(abil4Button);
	      return buttonPane;

   }
   private static JPanel initOptionsPane() {
      JPanel pane = null;
      ActionAdapter buttonListener = null;

      // Create an options pane
      JPanel optionsPane = new JPanel(new GridLayout(9, 1));
      
      // Create Login Menu
      pane = new JPanel(new GridLayout(2, 1));
      pane.add(new JLabel("Username:"));
      loginField = new JTextField(10);
      pane.add(loginField);
      optionsPane.add(pane);
      
      pane = new JPanel(new GridLayout(2, 1));
      pane.add(new JLabel("Password:"));
      passwordField = new JTextField(10);
      pane.add(passwordField);
      optionsPane.add(pane);

      pane = new JPanel(new GridLayout(2, 1));
      pane.add(new JLabel("Animal:"));
      animalCombo = new JComboBox(animals);
      pane.add(animalCombo);
      optionsPane.add(pane);

      // IP address input
      pane = new JPanel(new GridLayout(2, 1));
      pane.add(new JLabel("Host IP:"));
      ipField = new JTextField(10); ipField.setText(hostIP);
      ipField.setEnabled(false);
      ipField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
               ipField.selectAll();
               // Should be editable only when disconnected
               if (connectionStatus != DISCONNECTED) {
                  changeStatusNTS(NULL, true);
               }
               else {
                  hostIP = ipField.getText();
               }
            }
         });
      pane.add(ipField);
      optionsPane.add(pane);

      // Port input
      pane = new JPanel(new GridLayout(2, 1));
      pane.add(new JLabel("Port:"));
      portField = new JTextField(10); portField.setEditable(true);
      portField.setText((new Integer(port)).toString());
      portField.addFocusListener(new FocusAdapter() {
            public void focusLost(FocusEvent e) {
               // should be editable only when disconnected
               if (connectionStatus != DISCONNECTED) {
                  changeStatusNTS(NULL, true);
               }
               else {
                  int temp;
                  try {
                     temp = Integer.parseInt(portField.getText());
                     port = temp;
                  }
                  catch (NumberFormatException nfe) {
                     portField.setText((new Integer(port)).toString());
                     mainFrame.repaint();
                  }
               }
            }
         });
      pane.add(portField);
      
      optionsPane.add(pane);

      // Host/guest option
      buttonListener = new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
               if (connectionStatus != DISCONNECTED) {
                  changeStatusNTS(NULL, true);
               }
               else {
                  isHost = e.getActionCommand().equals("host");

                  // Cannot supply host IP if host option is chosen
                  if (isHost) {
                     ipField.setEnabled(false);
                     ipField.setText("localhost");
                     hostIP = "localhost";
                  }
                  else {
                     ipField.setEnabled(true);
                  }
               }
            }
         };
      ButtonGroup bg = new ButtonGroup();
      hostOption = new JRadioButton("Host", true);
      hostOption.setMnemonic(KeyEvent.VK_H);
      hostOption.setActionCommand("host");
      hostOption.addActionListener(buttonListener);
      guestOption = new JRadioButton("Guest", false);
      guestOption.setMnemonic(KeyEvent.VK_G);
      guestOption.setActionCommand("guest");
      guestOption.addActionListener(buttonListener);
      bg.add(hostOption);
      bg.add(guestOption);
      pane = new JPanel(new GridLayout(1, 2));
      pane.add(hostOption);
      pane.add(guestOption);
      optionsPane.add(pane);

      // Connect/disconnect buttons
      JPanel buttonPane = new JPanel(new GridLayout(1, 2));
      buttonListener = new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
               // Request a connection initiation
               if (e.getActionCommand().equals("connect")) {
                  changeStatusNTS(BEGIN_CONNECT, true);
               }
               // Disconnect
               else {
                  changeStatusNTS(DISCONNECTING, true);
               }
            }
         };
      connectButton = new JButton("Connect");
      connectButton.setMnemonic(KeyEvent.VK_C);
      connectButton.setActionCommand("connect");
      connectButton.addActionListener(buttonListener);
      connectButton.setEnabled(true);
      disconnectButton = new JButton("Disconnect");
      disconnectButton.setMnemonic(KeyEvent.VK_D);
      disconnectButton.setActionCommand("disconnect");
      disconnectButton.addActionListener(buttonListener);
      disconnectButton.setEnabled(false);
      buttonPane.add(connectButton);
      buttonPane.add(disconnectButton);
      optionsPane.add(buttonPane);

      
      return optionsPane;
   }

   /////////////////////////////////////////////////////////////////

   // Initialize all the GUI components and display the frame
   private static void initGUI() {
      // Set up the status bar
	   JPanel mainPane = new JPanel(new BorderLayout());
      statusField = new JLabel();
      statusField.setText(statusMessages[DISCONNECTED]);
      statusColor = new JTextField(1);
      statusColor.setBackground(Color.red);
      statusColor.setEditable(false);
      statusBar = new JPanel(new BorderLayout());
      statusBar.add(statusColor, BorderLayout.WEST);
      statusBar.add(statusField, BorderLayout.CENTER);

      // Set up the options pane
      JPanel optionsPane = initOptionsPane();
      JPanel buttonPane = initAbilityPane();
      // Set up the chat pane
      JPanel chatPane = new JPanel(new BorderLayout());
      chatText = new JTextArea(10, 20);
      chatText.setLineWrap(true);
      chatText.setEditable(false);
      chatText.setForeground(Color.blue);
      
      JScrollPane chatTextPane = new JScrollPane(chatText,
         JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      chatLine = new JTextField();
      chatLine.setEnabled(false);
      chatLine.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
               String s = chatLine.getText();
               if (!s.equals("")) {
                  appendToChatBox("OUTGOING: " + s + "\n");
                  chatLine.setText("");
                  // Send the string
                  sendString(s);
               }
            }
         });
      chatPane.add(chatLine, BorderLayout.SOUTH);
      chatPane.add(chatTextPane, BorderLayout.CENTER);
      chatPane.setPreferredSize(new Dimension(200, 300));
      JPanel statPane = new JPanel(new BorderLayout());
      statPane.setPreferredSize(new Dimension(400, 425));
      statPane.add(chatPane, BorderLayout.CENTER);
      stat1Text = new JTextArea(6, 0);
      JScrollPane stat1ScrollPane = new JScrollPane(stat1Text,
    	         JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    	         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      stat1Text.setLineWrap(true);
      stat1Text.setEditable(false);
      stat1Text.setForeground(Color.green);
      
      stat2Text = new JTextArea(6, 0);
      JScrollPane stat2ScrollPane = new JScrollPane(stat2Text,
    	         JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
    	         JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      stat2Text.setLineWrap(true);
      stat2Text.setEditable(false);
      stat2Text.setForeground(Color.red);
      statPane.add(chatPane, BorderLayout.NORTH);
      JPanel statTextPane = new JPanel(new GridLayout(1, 2));
      JPanel stat1Pane = new JPanel(new BorderLayout());
      stat1Pane.add(stat1Label = new JLabel("Client 1 Stats"), BorderLayout.NORTH);
      stat1Pane.add(stat1ScrollPane, BorderLayout.CENTER);
      
      JPanel stat2Pane = new JPanel(new BorderLayout());
      stat2Pane.add(stat2Label = new JLabel("Client 2 Stats"), BorderLayout.NORTH);
      stat2Pane.add(stat2ScrollPane, BorderLayout.CENTER);
      stat1Label.setHorizontalAlignment(SwingConstants.CENTER); stat1Label.setForeground(Color.green);
      stat2Label.setHorizontalAlignment(SwingConstants.CENTER); stat2Label.setForeground(Color.red);

      statTextPane.add(stat1Pane);      
      statTextPane.add(stat2Pane);
      statPane.add(statTextPane, BorderLayout.SOUTH);
      // Set up the main pane
      mainPane.add(statusBar, BorderLayout.SOUTH);
      mainPane.add(optionsPane, BorderLayout.WEST);
      mainPane.add(buttonPane, BorderLayout.EAST);
      mainPane.add(statPane, BorderLayout.CENTER);

      // Set up the main frame
      mainFrame = new JFrame("Animal Wars");
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setContentPane(mainPane);
      mainFrame.setSize(mainFrame.getPreferredSize());
      mainFrame.setLocation(200, 200);
      mainFrame.pack();
      mainFrame.setVisible(true);
      mainFrame.setResizable(false);
   }

   /////////////////////////////////////////////////////////////////

   // The thread-safe way to change the GUI components while
   // changing state
   private static void changeStatusTS(int newConnectStatus, boolean noError) {
      // Change state if valid state
      if (newConnectStatus != NULL) {
         connectionStatus = newConnectStatus;
      }

      // If there is no error, display the appropriate status message
      if (noError) {
         statusString = statusMessages[connectionStatus];
      }
      // Otherwise, display error message
      else {
         statusString = statusMessages[NULL];
      }

      // Call the run() routine (Runnable interface) on the
      // error-handling and GUI-update thread
      SwingUtilities.invokeLater(tcpObj);
   }

   /////////////////////////////////////////////////////////////////

   // The non-thread-safe way to change the GUI components while
   // changing state
   private static void changeStatusNTS(int newConnectStatus, boolean noError) {
      // Change state if valid state
      if (newConnectStatus != NULL) {
         connectionStatus = newConnectStatus;
      }

      // If there is no error, display the appropriate status message
      if (noError) {
         statusString = statusMessages[connectionStatus];
      }
      // Otherwise, display error message
      else {
         statusString = statusMessages[NULL];
      }

      // Call the run() routine (Runnable interface) on the
      // current thread
      tcpObj.run();
   }

   /////////////////////////////////////////////////////////////////

   // Thread-safe way to append to the chat box
   private static void appendToChatBox(String s) {
      synchronized (toAppend) {
         toAppend.append(s);
      }
   }

   /////////////////////////////////////////////////////////////////

   // Add text to send-buffer
   private static void sendString(String s) {
      synchronized (toSend) {
         toSend.append(s + "\n");
      }
   }

   /////////////////////////////////////////////////////////////////

   // Cleanup for disconnect
   private static void cleanUp() {
      try {
         if (hostServer != null) {
            hostServer.close();
            hostServer = null;
         }
      }
      catch (IOException e) { hostServer = null; }

      try {
         if (socket != null) {
            socket.close();
            socket = null;
         }
      }
      catch (IOException e) { socket = null; }

      try {
         if (in != null) {
            in.close();
            in = null;
         }
      }
      catch (IOException e) { in = null; }

      if (out != null) {
         out.close();
         out = null;
      }
   }

   /////////////////////////////////////////////////////////////////

   // Checks the current state and sets the enables/disables
   // accordingly
   public void run() {
      switch (connectionStatus) {
      case DISCONNECTED:
         connectButton.setEnabled(true);
         disconnectButton.setEnabled(false);
         ipField.setEnabled(true);
         portField.setEnabled(true);
         hostOption.setEnabled(true);
         guestOption.setEnabled(true);
         chatLine.setText(""); chatLine.setEnabled(false);
         statusColor.setBackground(Color.red);
         break;

      case DISCONNECTING:
         connectButton.setEnabled(false);
         disconnectButton.setEnabled(false);
         abil1Button.setEnabled(false);
         abil2Button.setEnabled(false);
         abil3Button.setEnabled(false);
         abil4Button.setEnabled(false);

         ipField.setEnabled(false);
         portField.setEnabled(false);
         hostOption.setEnabled(false);
         guestOption.setEnabled(false);
         chatLine.setEnabled(false);
         statusColor.setBackground(Color.orange);
         break;

      case CONNECTED:
         connectButton.setEnabled(false);
         disconnectButton.setEnabled(true);
         abil1Button.setEnabled(true);
         abil2Button.setEnabled(true);
         abil3Button.setEnabled(true);
         abil4Button.setEnabled(true);

         ipField.setEnabled(false);
         portField.setEnabled(false);
         hostOption.setEnabled(false);
         guestOption.setEnabled(false);
         chatLine.setEnabled(true);
         statusColor.setBackground(Color.green);
         break;

      case BEGIN_CONNECT:
         connectButton.setEnabled(false);
         disconnectButton.setEnabled(false);
         ipField.setEnabled(false);
         portField.setEnabled(false);
         hostOption.setEnabled(false);
         guestOption.setEnabled(false);
         chatLine.setEnabled(false);
         chatLine.grabFocus();
         statusColor.setBackground(Color.orange);
         break;
      }

      // Make sure that the button/text field states are consistent
      // with the internal states
      ipField.setText(hostIP);
      portField.setText((new Integer(port)).toString());
      hostOption.setSelected(isHost);
      guestOption.setSelected(!isHost);
      statusField.setText(statusString);
      chatText.append(toAppend.toString());
      toAppend.setLength(0);

      mainFrame.repaint();
   }

   /////////////////////////////////////////////////////////////////
   public static void engageButtons(){
		abil1Button.setText(myAnimal.listAbil(0));
		abil2Button.setText(myAnimal.listAbil(1));
		abil3Button.setText(myAnimal.listAbil(2));
		abil4Button.setText(myAnimal.listAbil(3));
	}
   // The main procedure
   public static void main(String args[]) {
      String s;

      initGUI();

      while (true) {
         try { // Poll every ~10 ms
            Thread.sleep(10);
         }
         catch (InterruptedException e) {}

         switch (connectionStatus) {
         case BEGIN_CONNECT:
            try {
               // Try to set up a server if host
               if (isHost) {
                  hostServer = new ServerSocket(port);
                  socket = hostServer.accept();
               }

               // If guest, try to connect to the server
               else {
                  socket = new Socket(hostIP, port);
               }

               in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
               out = new PrintWriter(socket.getOutputStream(), true);
               
               //Create you animal then set it and send it.

               myAnimal = GameEngine.parseAnimal(myAni = (String) animalCombo.getSelectedItem(), myUsername = loginField.getText());
               appendToChatBox(myAnimal.toString() + ":" + myUsername + "\n");
               stat1Label.setText(myUsername + " Stats");
               changeStatusTS(CONNECTED, true);

               
            }
            // If error, clean up and output an error message
            catch (IOException e) {
               cleanUp();
               changeStatusTS(DISCONNECTED, false);
            }
            break;

         case CONNECTED:
            try {

               // Send data
            	if (setupGameSend){
            	  out.println(myAni);
            	  out.println(myUsername);
                  changeStatusTS(NULL, true);
                  engageButtons();
            	  setupGameSend = false;
	          	  changeStatusTS(NULL, true);
            	} else if (currentTurn == turn){
            		try { // Poll every ~10 ms
                        Thread.sleep(10);
                    } catch (InterruptedException e) {}
            		if (attackNum1 == null || randNum1 == null){
            			passwordField.setText("Choose an attack!");
            		} else if ((attackNum1.equals("0") || attackNum1.equals("1") || attackNum1.equals("2") || attackNum1.equals("3"))){

            			appendToChatBox("You attempt to " + myAnimal.listAbil(Integer.parseInt(attackNum1)) + " " + currentTurn + "\n"); 
            			GameEngine.evaluate(myAnimal, enemyAnimal, attackNum1, randNum1);
            			stat1Text.setText(myAnimal.listStatus());
            			stat2Text.setText(enemyAnimal.listStatus());
            			out.println(toSend);
            			out.flush();
            			toSend.setLength(0);
            			currentTurn = getOtherPlayer(turn);
            			attackNum1 = null;
            			randNum1 = null;
            		} else {
            			System.out.println(currentTurn);
            		}
            		changeStatusTS(NULL, true);

            	} else if (toSend.length() != 0) {
                  out.print(toSend); out.flush();
                  toSend.setLength(0);
                  changeStatusTS(NULL, true);
            	}
            	stat1Text.setText(myAnimal.listStatus());
               // Receive data
            	if (setupGameRecieve && in.ready()){
                  enemyAni = in.readLine();
                  enemyUsername = in.readLine();
                  turn = Integer.parseInt(in.readLine());   
                  stat1Label.setText(myUsername + " Stats " + turn);
                  enemyAnimal = GameEngine.parseAnimal(enemyAni, enemyUsername);
                  appendToChatBox(enemyAnimal.toString() + ":" + enemyUsername + "\n");
                  stat2Text.setText(enemyAnimal.listStatus());
                  stat2Label.setText(enemyUsername + " Stats");
                  changeStatusTS(NULL, true);
                  setupGameRecieve = false;
            	} else if (in.ready()) {
                  s = in.readLine();

                  if ((s != null) &&  (s.length() != 0)) {
                     // Check if it is the end of a trasmission

                	 if (s.equals(END_CHAT_SESSION)) {
                        changeStatusTS(DISCONNECTING, true);
                     }

                     // Otherwise, receive what text
                     else {
                    	 attackNum2 = s.substring(0,1);
                    	 randNum2 = s.substring(1,2);
                     if (attackNum2 == null || randNum2 == null){
             			passwordField.setText("Waiting for opponent!");
             			break;
                     }else if ((attackNum2.equals("0") || attackNum2.equals("1") || attackNum2.equals("2") || attackNum2.equals("3"))){
                    		
        	                appendToChatBox(enemyUsername + " attempts to " + enemyAnimal.listAbil(Integer.parseInt(attackNum2)) + "\n");
        	                GameEngine.evaluate(enemyAnimal, myAnimal, attackNum2, randNum2);
                            stat1Text.setText(myAnimal.listStatus());   
                            stat2Text.setText(enemyAnimal.listStatus());               

//                            currentTurn = getOtherPlayer(turn);

                    	} else if (s.equals("STAT")){
                    		appendToChatBox(currentTurn + ":" +   turn + "\n");
                    	} 
                    	else {
                    		 appendToChatBox("INCOMING: " + s + "\n");
                    	}
                        changeStatusTS(NULL, true);

                     }
                  }
               }
            }
            catch (IOException e) {
               cleanUp();
               changeStatusTS(DISCONNECTED, false);
            }
            break;

         case DISCONNECTING:
            // Tell other chatter to disconnect as well
            out.print(END_CHAT_SESSION); out.flush();

            // Clean up (close all streams/sockets)
            cleanUp();
            changeStatusTS(DISCONNECTED, true);
            break;

         default: break; // do nothing
         }
      }
   }
   private static int getOtherPlayer(int i){
   	   if (i == 1){
   		   return 2;
   	   }
   	   return 1;
      }
}

////////////////////////////////////////////////////////////////////

// Action adapter for easy event-listener coding
class ActionAdapter implements ActionListener {
   public void actionPerformed(ActionEvent e) {}
}

////////////////////////////////////////////////////////////////////