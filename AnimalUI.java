import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.text.DefaultCaret;


public class AnimalUI {
	private int player = 0;
    ButtonGroup playerRadioButtonGroup = new ButtonGroup();
	JButton loadButton = new JButton("Load");
	JButton saveButton = new JButton("Save");
	JButton animalButton1 = new JButton("Aardvark");
	JButton animalButton2 = new JButton("Narwhal");
	JButton animalButton3 = new JButton("Ocelot");
	JButton animalButton4 = new JButton("Scimitar Oryx");
	JButton animalButton5 = new JButton("Three Toed Sloth");
	JButton animalButton6 = new JButton("Placeholder");
	JButton animalButton7 = new JButton("Placeholder");
	JButton animalButton8 = new JButton("Placeholder");
	JButton animalButton9 = new JButton("Placeholder");
	private boolean p1sel;
	private boolean p2sel;
	String toPrint = "";
	JTextArea combatLog = new JTextArea(10,10);
	JTextField winnerTextField = new JTextField();
	private boolean turn = true;
	JTextArea player1Info = new JTextArea(10,5);
	JTextArea player2Info = new JTextArea(10,5);
	final JButton p1Move1Button = new JButton("1");
	final JButton p1Move2Button = new JButton("2");
	final JButton p1Move3Button = new JButton("3");
	final JButton p1Move4Button = new JButton("4");

	//P2
	final JButton p2Move1Button = new JButton("1");
	final JButton p2Move2Button = new JButton("2");
	final JButton p2Move3Button = new JButton("3");
	final JButton p2Move4Button = new JButton("4");
	private String pln1;
	private String pln2;

    final JRadioButton player1RadioButton = new JRadioButton(pln1);
    final JRadioButton player2RadioButton = new JRadioButton(pln2);
    
	Animal pl1 = new Animal(pln1);
	Animal pl2 = new Animal(pln2);

  public AnimalUI(String playerName1, String playerName2){
	  	pln1 = playerName1;
	  	pln2 = playerName2;
	  	player1RadioButton.setText(pln1);
	  	player2RadioButton.setText(pln2);
	  	
	  	final JFrame frameMain = new JFrame("Animal Wars");
		GridBagLayout layout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		frameMain.setSize(400, 750);
		frameMain.setVisible(true);
		frameMain.setLayout(layout);
		frameMain.addWindowListener(new WindowAdapter(){
			 public void windowClosing(WindowEvent e){
				 System.exit(0);
			 }
		});

		//Player1 label
		JLabel p1Label = new JLabel(playerName1, SwingConstants.CENTER);
		c.gridx=0;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
		frameMain.add(p1Label, c);

		//Player1 move 1 button
		p1Move1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (turn){
					setStats();
					toPrint = pl1.offAbilOne(pl2);
					combatLog.append(toPrint + "\n");
					turn = false;
				} else
					winnerTextField.setText("Not " + pln1 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 0;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p1Move1Button, c);

		//Player1 move 2 button
		p1Move2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (turn){
					setStats();
					toPrint = pl1.offAbilTwo(pl2);
					combatLog.append(toPrint + "\n");
					turn = false;
				} else
					winnerTextField.setText("Not " + pln1 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 0;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p1Move2Button, c);	

		//Player1 move 3 button
		p1Move3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (turn){
					setStats();
					toPrint = pl1.defAbilOne(pl2);
					combatLog.append(toPrint + "\n");
					turn = false;
				} else
					winnerTextField.setText("Not " + pln1 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 0;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p1Move3Button, c);

		//Player1 move 4 button
		p1Move4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (turn){
					setStats();
					toPrint = pl1.defAbilTwo(pl2);
					combatLog.append(toPrint + "\n");
					turn = false;
				} else
					winnerTextField.setText("Not " + pln1 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 0;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p1Move4Button, c);

		//Winner label and text field
		JLabel winnerLabel = new JLabel("--------Error Return--------Error Return--------Error Return--------", SwingConstants.CENTER);
		c.gridx=1;
		c.gridy=1;
		frameMain.add(winnerLabel, c);
		winnerTextField.setEditable(false);
		c.gridx=1;
		c.gridy=2;
		frameMain.add(winnerTextField, c);

		//Compute winner button
		JButton winnerButton = new JButton("Engage");
		winnerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setStats();
			}
		});
		c.gridx = 1;
		c.gridy = 3;
		c.fill = GridBagConstraints.CENTER;
		frameMain.add(winnerButton, c);

		//Player2 label
		JLabel p2Label = new JLabel(playerName2, SwingConstants.CENTER);
		c.gridx=2;
		c.gridy=0;
		c.fill = GridBagConstraints.HORIZONTAL;
		frameMain.add(p2Label, c);

		//Player1 move 1 button
		p2Move1Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!turn){
					setStats();
					toPrint = pl2.offAbilOne(pl1);
					combatLog.append(toPrint + "\n");
					turn = true;

				} else
					winnerTextField.setText("Not " + pln2 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 2;
		c.gridy = 1;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p2Move1Button, c);

		//Player1 move 2 button
		p2Move2Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!turn){
					setStats();
					toPrint = pl2.offAbilTwo(pl1);
					combatLog.append(toPrint + "\n");
					turn = true;
				} else
					winnerTextField.setText("Not " + pln2 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 2;
		c.gridy = 2;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p2Move2Button, c);

		//Player1 move 3 button
		p2Move3Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!turn){
					setStats();
					toPrint = pl2.defAbilOne(pl1);
					combatLog.append(toPrint + "\n");
					turn = true;
				} else
					winnerTextField.setText("Not " + pln2 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 2;
		c.gridy = 3;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p2Move3Button, c);

		//Player1 move 4 button
		p2Move4Button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!turn){
					setStats();
					toPrint = pl2.defAbilTwo(pl1);
					combatLog.append(toPrint + "\n");
					turn = true;setStats();
				} else
					winnerTextField.setText("Not " + pln2 + "'s turn!");
				setStats();
			}
		});
		c.gridx = 2;
		c.gridy = 4;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(p2Move4Button, c);

		//Player Animal selected textfield
		final JTextField player1SelectedTextField = new JTextField();
		player1SelectedTextField.setEditable(false);
		c.gridx = 0;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		frameMain.add(player1SelectedTextField, c);
		final JTextField player2SelectedTextField = new JTextField();
		player2SelectedTextField.setEditable(false);
		c.gridx = 2;
		c.gridy = 5;
		c.fill = GridBagConstraints.HORIZONTAL;
		frameMain.add(player2SelectedTextField, c);

		//Animal selected label
		JLabel selectedLabel = new JLabel("<=    Selected    =>", SwingConstants.CENTER);
		c.gridx=1;
		c.gridy=5;
		frameMain.add(selectedLabel, c);

		//Player Animal selection radio button
        player1RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	player = 1;
            	
            }
        });
        player2RadioButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	player = 2;

            }
        });
        c.gridx = 0;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		frameMain.add(player1RadioButton, c);
		playerRadioButtonGroup.add(player1RadioButton);
		c.gridx = 2;
		c.gridy = 6;
		c.fill = GridBagConstraints.HORIZONTAL;
		frameMain.add(player2RadioButton, c);
		playerRadioButtonGroup.add(player2RadioButton);

		//Combat Log Label
		JLabel combatLogLabel = new JLabel("Combat Log", SwingConstants.CENTER);
		c.gridx=1;
		c.gridy=6;
		frameMain.add(combatLogLabel, c);

		//Combat Log
		combatLog.setEditable(false);
		combatLog.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.BLACK));
		DefaultCaret caret = (DefaultCaret) combatLog.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		c.gridx=1;
		c.gridy=7;
		JScrollPane combatScrollPane = new JScrollPane(combatLog);
		combatScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		frameMain.add(combatScrollPane, c);

		//Player 1 Info
		player1Info.setEditable(false);
		player1Info.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
		c.gridx=0;
		c.gridy=7;
		frameMain.add(player1Info, c);

		//Player 2 Info
		player2Info.setEditable(false);
		player2Info.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, Color.RED));
		c.gridx=2;
		c.gridy=7;
		frameMain.add(player2Info, c);

		//Animal Label
		JLabel pokemonLabel = new JLabel("Animal", SwingConstants.CENTER);
		c.gridx=1;
		c.gridy=8;
		frameMain.add(pokemonLabel, c);

		// --- LIST OF POKEMON ---

		//Animal Placeholder Button
		animalButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player == 1){
					pl1 = new Aardvark(pln1);
					player1ES();
					player1SelectedTextField.setText(pl1.getType());
					engageP1Buttons(pl1);
				}
				if (player == 2){
					pl2 = new Aardvark(pln2);
					player2ES();
					player2SelectedTextField.setText(pl2.getType());
					engageP2Buttons(pl2);
				}
			}
		});
		c.gridx = 0;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton1, c);

		//Animal Placeholder Button
		animalButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player == 1){
					pl1 = new Narwhal(pln1);
					player1ES();
					player1SelectedTextField.setText(pl1.getType());
					engageP1Buttons(pl1);
				}
				if (player == 2){
					pl2 = new Narwhal(pln2);
					player2ES();
					player2SelectedTextField.setText(pl2.getType());
					engageP2Buttons(pl2);
				}
			}
		});
		c.gridx = 1;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton2, c);

		//Animal Placeholder Button
		animalButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player == 1){
					pl1 = new Ocelot(pln1);
					player1ES();
					player1SelectedTextField.setText(pl1.getType());
					engageP1Buttons(pl1);
				}
				if (player == 2){
					pl2 = new Ocelot(pln2);
					player2ES();
					player2SelectedTextField.setText(pl2.getType());
					engageP2Buttons(pl2);
				}
			}
		});
		c.gridx = 2;
		c.gridy = 9;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton3, c);

		//Animal Placeholder Button
		animalButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player == 1){
					pl1 = new ScimitarOryx(pln1);
					player1ES();
					player1SelectedTextField.setText(pl1.getType());
					engageP1Buttons(pl1);
				}
				if (player == 2){
					pl2 = new ScimitarOryx(pln2);
					player2ES();
					player2SelectedTextField.setText(pl2.getType());
					engageP2Buttons(pl2);
				}
			}
		});
		c.gridx = 0;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton4, c);

		//Animal Placeholder Button
		animalButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (player == 1){
					pl1 = new ThreeToedSloth(pln1);
					player1ES();
					player1SelectedTextField.setText(pl1.getType());
					engageP1Buttons(pl1);
				}
				if (player == 2){
					pl2 = new ThreeToedSloth(pln2);
					player2ES();
					player2SelectedTextField.setText(pl2.getType());
					engageP2Buttons(pl2);
				}
			}
		});
		c.gridx = 1;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton5, c);

		//Animal Placeholder Button
		animalButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		c.gridx = 2;
		c.gridy = 10;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton6, c);

		//Animal Placeholder Button
		animalButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		c.gridx = 0;
		c.gridy = 11;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton7, c);

		//Animal Placeholder Button
		animalButton8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		c.gridx = 1;
		c.gridy = 11;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton8, c);

		//Animal Placeholder Button
		animalButton9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		c.gridx = 2;
		c.gridy = 11;
		c.fill = GridBagConstraints.HORIZONTAL; 
		frameMain.add(animalButton9, c);
		
		
		//File Location
			final JTextField fileLocationTextField = new JTextField("Example File Path C:\\\\myfile.awz = C:\\myfile.awz");
			fileLocationTextField.setEditable(true);
			c.gridx = 1;
			c.gridy = 12;
			c.fill = GridBagConstraints.HORIZONTAL;
			frameMain.add(fileLocationTextField, c);				
		
	
		//Load Placeholder Button
				loadButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (player == 1){
							FileSaveLoad pl1load = new FileSaveLoad(fileLocationTextField.getText(), pln1);
							pl1 = pl1load.load();

							player1RadioButton.setEnabled(false);
							player1SelectedTextField.setText(pl1.getType());
							engageP1Buttons(pl1);
						}
						if (player == 2){
							FileSaveLoad pl2load = new FileSaveLoad(fileLocationTextField.getText(), pln2);
							pl2 = pl2load.load();
							player2RadioButton.setEnabled(false);
							player2SelectedTextField.setText(pl2.getType());
							engageP2Buttons(pl2);
						}
					}
				});
				c.gridx = 0;
				c.gridy = 12;
				c.fill = GridBagConstraints.HORIZONTAL; 
				frameMain.add(loadButton, c);
				
		
		//Save Placeholder Button
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						if (player == 1){
							FileSaveLoad pl1load = new FileSaveLoad(fileLocationTextField.getText(), pln1);
							
							try {
								pl1load.printStats(pl1);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

							player1RadioButton.setEnabled(false);
						}
						if (player == 2){
							FileSaveLoad pl2load = new FileSaveLoad(fileLocationTextField.getText(), pln2);
							
							try {
								pl2load.printStats(pl2);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}


							player2RadioButton.setEnabled(false);
						}
					}
				});
				c.gridx = 2;
				c.gridy = 12;
				c.fill = GridBagConstraints.HORIZONTAL; 
				frameMain.add(saveButton, c);		

		frameMain.setSize(400,750);
		frameMain.setSize(450,750);
	}
  
  	public void setStats(){
  		player1Info.setText(pl1.listStatus());
  		player2Info.setText(pl2.listStatus());
  		if(check())
  			getWinner();
  	}
  	public void endGame(){
  		//P1
  		p1Move1Button.setEnabled(false);
  		p1Move2Button.setEnabled(false);
  		p1Move3Button.setEnabled(false);
  		p1Move4Button.setEnabled(false);
  		//P2
  		p2Move1Button.setEnabled(false);
  		p2Move2Button.setEnabled(false);
  		p2Move3Button.setEnabled(false);
  		p2Move4Button.setEnabled(false);

  	}
  	public boolean check(){
  		if (pl1.getHealth() > 0 && pl2.getHealth() > 0)
  			return false;
  		return true;
  	}
  	public void getWinner(){
  		if (check() && pl1.getHealth() < 0){
  			endGame();
  			winnerTextField.setText(pln1 + " Dies. " + pln2 + " Wins!");
  			player = 0;
  			combatLog.append(pln2 + " absorbs " + (int)(0.1*pl1.getRhealth()) + " health (10%) from " + pln1);

  			pl2.health += (int)(0.1*pl1.getRhealth());
			player2RadioButton.setEnabled(true);
	  		saveButton.setEnabled(true);
	  		loadButton.setEnabled(true);
	  		player1Info.setText(pl1.listStatus());
	  		player2Info.setText(pl2.listStatus());

  			//buybackP2();
  		} else if (check() && pl2.getHealth() < 0){
  			endGame();
  			winnerTextField.setText(pln2 + " Dies. " + pln1 + " Wins!");
  			player = 0;
  			combatLog.append(pln1 + " absorbs " + (int)(0.1*pl2.getRhealth()) + " health (10%) from " + pln2);

  			pl1.health += (int)(0.1*pl2.getRhealth());
			player1RadioButton.setEnabled(true);
	  		saveButton.setEnabled(true);
	  		loadButton.setEnabled(true);
	  		player1Info.setText(pl1.listStatus());
	  		player2Info.setText(pl2.listStatus());
	  		
	  		//buybackP1();
  		}

  	}
  	public void disableSelection(){
  		setStats();
  		saveButton.setEnabled(false);
  		loadButton.setEnabled(false);
  		animalButton1.setEnabled(false);
  		animalButton2.setEnabled(false);
  		animalButton3.setEnabled(false);
  		animalButton4.setEnabled(false);
  		animalButton5.setEnabled(false);
  		animalButton6.setEnabled(false);
  		animalButton7.setEnabled(false);
  		animalButton8.setEnabled(false);
  		animalButton9.setEnabled(false);

  	}
  	public void buybackP2(){
  		
		player2RadioButton.setEnabled(true);
		
  	}
  	public void buybackP1(){
  		
		player1RadioButton.setEnabled(true);

  	}
  	public void player1ES(){
		player1RadioButton.setEnabled(false);
		playerRadioButtonGroup.clearSelection();
		player = 0;
  	}
  	public void player2ES(){
		player2RadioButton.setEnabled(false);
		playerRadioButtonGroup.clearSelection();
		player = 0;
  	}
  	public void engageP1Buttons(Animal animal){
  		p1sel = true;
  		if (p1sel && p2sel)
  			disableSelection();
  
  		p1Move1Button.setText(animal.listAbil(0));
  		p1Move2Button.setText(animal.listAbil(1));
  		p1Move3Button.setText(animal.listAbil(2));
  		p1Move4Button.setText(animal.listAbil(3));
  	}
  	public void engageP2Buttons(Animal animal){
  		p2sel = true;
  		if (p1sel && p2sel)
  			disableSelection();
  		
  		p2Move1Button.setText(animal.listAbil(0));
  		p2Move2Button.setText(animal.listAbil(1));
  		p2Move3Button.setText(animal.listAbil(2));
  		p2Move4Button.setText(animal.listAbil(3));
  	}
}