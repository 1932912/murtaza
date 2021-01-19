import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import cell.Cell;
import javax.swing.JButton;
import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;

public class ConnectFive extends JFrame {
	int size_row, size_column; // Row size, Column size
	int playerOrder; // Who will start the game
	int count1 = 0, count2 = 0, Score_count1 = 0, Score_count2 = 0;// To count the winning score and turned played by a
					mutdsfhdshjfgsh												// player.
	int GameMode; // Player number
	static int Occupied_buttons = 0; // Number of living cells
	public static int Winner, Toss_input, resetplayer, Input_Colour;
	String Player_Turn;
	// GUI Requirements
	JFrame Game_Frame, Resultframe, Pause_Game, Start_Game, Coin_Toss, Toss_winner; // Frame
	JPanel Game_Panel, Status_Panel, ScorePanel, Toss_Panel, Coin_Panel;
	static JLabel turn1, turn2, statusLabel, score1, score2, winner_label, CoinImage, Head, Tail;
	JTextField Coin_Select;
	static JButton[][] Game_Button; // Buttons
	JButton Reset, Exit, Toss_Coin;
	static Cell Game_Board[][]; // Game Board
	GridLayout Game_Grid; // GridLayout
	JMenu Main_Menu, Additional_Menu,helpMenu;
	JMenuItem howToPlayItem,aboutGame;
sahfgdsbfhghjgdhgfdbvchdsgvuhsdvs
sabfbas murtaza 
	FileWriter Save_Game;// To write input into a .txt file
	ArrayList<String> Save_Location = new ArrayList<String>();// To Save Game Input.

	// Button icons
	ImageIcon empty = new ImageIcon(getClass().getResource("/resources/emptycell.png"));
	ImageIcon Red = new ImageIcon(getClass().getResource("/resources/Red.png"));
	ImageIcon Blue = new ImageIcon(getClass().getResource("/resources/Blue.png"));
	// Toss Images
	ImageIcon toss = new ImageIcon(getClass().getResource("/resources/toss.png"));
	ImageIcon head = new ImageIcon(getClass().getResource("/resources/head.png"));
	ImageIcon tail = new ImageIcon(getClass().getResource("/resources/tail.png"));

	public static void main(String[] args) {
		ConnectFive game = new ConnectFive();// From here the Game Application will start.
	}

	// To Toss Again.
	public class Toss_again implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Toss_winner.setVisible(false);// To hide the Toss_Winner
			cointoss();
		}
	}

	public class NewGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game_Frame.setVisible(false);
			ConnectFive game = new ConnectFive();
		}
	}
murtaza magar
	public class Starting implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			cointoss();
		}
	}

	public class Begin_Game implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			playerNumberAndBoardSize(); // Get the game parameters
			Game_GUI();
		}
	}

	public class Coin_Toss implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Coin_Toss.setVisible(false);// To hide Coin_toss window.
			Toss_winner = new JFrame();// Creating a new frame for toss winner.
			Toss_winner.setSize(250, 330);// Setting the size of the frame.
			Toss_input = Integer.parseInt(Coin_Select.getText());// Converting String input from user as Integer to find the winner.
			Double ans = Math.random() * 2;//To get random value as 1 or 2 to find the winner.
			int Toss_Win_Value = (int) Math.ceil(ans);//converting double random value as an integer.
			if (Toss_input == 1) {//If user Select  Head.
				JLabel Winner_toss = new JLabel();//To show the toss results.
				JPanel TopPanel = new JPanel();//To add the toss result image.
				JPanel ButtonPanel = new JPanel();//To add the winning status and start game and toss again button.
				JButton Start = new JButton("Start Game");//Creating a start game button.
				JButton Again = new JButton("Toss Again");//Creating a toss again button.
				Start.addActionListener(new Begin_Game());//Taking action from the start button.
				Again.addActionListener(new Toss_again());//taking action from the Toss Again button.
				Toss_Panel.setVisible(false);//hiding initial button panel for toss.
				Coin_Panel.setVisible(false);//hiding the intial toss image.
				if (Toss_Win_Value == 1) {   // If Player 1 wins the toss.
					Player_Turn = "    ||  Player 1 turn!!  ||    ";//Setting the player turn status
					CoinImage.setIcon(head);//setting new toss win image.
					Winner_toss.setText("        You Won the toss!!");//setting new toss win status.
					Winner_toss.setFont(new Font("Arial", Font.BOLD, 15));//setting the font type,size,style.
					playerOrder = 0;//Initializing that winner starts the game. 
					resetplayer = 0;
				} else {   //If Player losses the Toss.
					Player_Turn = "    ||  Player 2 turn!!  ||    ";//Setting the player turn status.
					CoinImage.setIcon(tail);//setting new toss loss image.
					Winner_toss.setText("        You Loss the toss!!");//setting new toss loss status.
					Winner_toss.setFont(new Font("Arial", Font.BOLD, 15));//setting the font type,size,style.
					playerOrder = 1;//Initializing that loser starting position in the game. 
					resetplayer = 1;
				}
				TopPanel.add(CoinImage);//Adding coin image to the panel.
				ButtonPanel.add(Winner_toss);//adding the toss winning status to the bottom panel.
				ButtonPanel.add(Start);//Adding start game button to bottom panel.
				ButtonPanel.add(Again);//Adding toss again button to bottom panel.
				Toss_winner.add(TopPanel, BorderLayout.NORTH);//Adding top panel to the toss frame.
				Toss_winner.add(ButtonPanel, BorderLayout.CENTER);//Adding bottom panel to the toss frame.
				Toss_winner.setResizable(false);//Setting that the frame can't be resized.
				Toss_winner.setLocationRelativeTo(null); // Toss frame will be center of the screen
				Toss_winner.setVisible(true); // Show toss frame
				Toss_winner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
			}
			sjasjfhjsahf
			if (Toss_input == 2) {//If user Select  Head.
				JLabel Winner_toss = new JLabel();//To show the toss results.
				JPanel TopPanel = new JPanel();//To add the toss result image.
				JPanel ButtonPanel = new JPanel();//To add the winning status and start game and toss again button.
				JButton Start = new JButton("Start Game");//Creating a start game button.
				JButton Again = new JButton("Toss Again");//Creating a toss again button.
				Start.addActionListener(new Begin_Game());//Taking action from the start button.
				Again.addActionListener(new Starting());//taking action from the Toss Again button.
				Toss_Panel.setVisible(false);//hiding initial button panel for toss.
				Coin_Panel.setVisible(false);//hiding the initial toss image.
				if (Toss_Win_Value == 2) {	// If Player 2 wins the toss.
					Player_Turn = "    ||  Player 1 turn!!  ||    ";//Setting the player turn status
					CoinImage.setIcon(tail);//setting new toss win image.
					Winner_toss.setText("        You Won the toss!!");//setting new toss win status.
					Winner_toss.setFont(new Font("Arial", Font.BOLD, 15));//setting the font type,size,style.
					playerOrder = 0;//Initializing that winner starts the game. 
					resetplayer = 0;
				} else {
					Player_Turn = "    ||  Player 2 turn!!  ||    ";//Setting the player turn status
					CoinImage.setIcon(head);//setting new toss loss image.
					Winner_toss.setText("        You Loss the toss!!");//setting new toss loss status.
					Winner_toss.setFont(new Font("Arial", Font.BOLD, 15));//setting the font type,size,style.
					playerOrder = 1;//Initializing that loser starting position in the game.
					resetplayer = 1;
				}
				TopPanel.add(CoinImage);//Adding coin image to the panel.
				ButtonPanel.add(Winner_toss);//adding the toss winning status to the bottom panel.
				ButtonPanel.add(Start);//Adding start game button to bottom panel.
				ButtonPanel.add(Again);//Adding toss again button to bottom panel.
				Toss_winner.add(TopPanel, BorderLayout.NORTH);//Adding top panel to the toss frame.
				Toss_winner.add(ButtonPanel, BorderLayout.CENTER);//Adding bottom panel to the toss frame.
				Toss_winner.setResizable(false);//Setting that the frame can't be resized.
				Toss_winner.setLocationRelativeTo(null); // Toss frame will be center of the screen
				Toss_winner.setVisible(true); // Show toss frame
				Toss_winner.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
			}

		}
	}

	public void cointoss() {
		Coin_Toss = new JFrame();//Creating new coin toss frame.
		Coin_Toss.setSize(250, 330);//Setting the size of the frame.
		Toss_Panel = new JPanel();//Creating a new toss input,status,button panel.
		Coin_Panel = new JPanel();//creating a new toss image panel.
		CoinImage = new JLabel();//Creating a new toss image label.
		Head = new JLabel("Input 1. For Heads");//Asking the user to press 1 to select head.
		Tail = new JLabel("Input 2. For Tails");//Asking the user to press 2 to select tail.
		Coin_Select = new JTextField(20);//Creating a new text field for the user input.
		Toss_Coin = new JButton("Toss Coin");//Creating a new button to toss the coin.
		Toss_Coin.addActionListener(new Coin_Toss());//taking aaction from the toss coin button.
		CoinImage.setIcon(toss);//setting the image for toss.
		Coin_Panel.add(CoinImage);//adding image to the panel.
		Toss_Panel.add(Head);//adding select head status to the panel
		Toss_Panel.add(Tail);//Adding select tails status to the panel.
		Toss_Panel.add(Coin_Select);//Adding text field to the panel.
		Toss_Panel.add(Toss_Coin);//Adding toss coin button to the panel.
		Coin_Toss.add(Coin_Panel, BorderLayout.NORTH);//adding coin image panel to the frame.
		Coin_Toss.add(Toss_Panel, BorderLayout.CENTER);//adding the status , textfield, button panel to the frame.
		Coin_Toss.setResizable(true);//Setting that the frame can't be resized.
		Coin_Toss.setLocationRelativeTo(null); // Toss frame will be center of the screen
		Coin_Toss.setVisible(true); // Show frame
		Coin_Toss.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
	}

	public ConnectFive() {
		Start_Game = new JFrame("Connect Five Game");//Creating a new start game frame.
		ImageIcon starting = new ImageIcon(getClass().getResource("/resources/final.png"));//creating new image icon for the frame. 
		JPanel bottom_Panel = new JPanel();//creating new bottom panel for buttons.
		JLabel StartImage = new JLabel();//creating new panel to add image.
		JButton Start = new JButton("Start Game");//creating new start game button.
		Exit = new JButton("Exit Game");//Creating new exit game button.
		Start.addActionListener(new Starting());//adding action to the start game button.
		Exit.addActionListener(new Exit());//adding action to the exit game button.
		StartImage.setIcon(starting);//setting the main frame image.
		bottom_Panel.add(Start, BorderLayout.SOUTH);//adding start button to the bottom panel.
		bottom_Panel.add(Exit, BorderLayout.SOUTH);//adding exit button to the bottom panel.
		Start_Game.add(StartImage, BorderLayout.CENTER);//adding Game Start image to the frame.
		Start_Game.add(bottom_Panel, BorderLayout.SOUTH);//Adding bottom panel to the frame.
		Start_Game.pack();//Causes this Window to be sized to fit the preferred sizeand layouts of its subcomponents.
		Start_Game.setResizable(false); //Setting that the frame can't be resized.
		Start_Game.setLocationRelativeTo(null); // Toss frame will be center of the screen
		Start_Game.setVisible(true); // Show frame
		Start_Game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
	}
	//To take screen shot to the game.
	public class ScreenShot implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				Thread.sleep(100);//Causes the currently executing thread to sleep
				Robot r = new Robot();//creating  an object to get the coordinate system of the primary screen.
				// It saves screenshot to desired path
				String path = "C://Users//Murtaza's//eclipse-workspace//cs1002_lab7//src//resources// Shot.GIF";
				// Used to get ScreenSize and capture image
				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
				Rectangle captureRect = new Rectangle(0, 28, screenSize.width, screenSize.height - 70);
				BufferedImage screenFullImage = r.createScreenCapture(captureRect);//Creates an image containing pixels read from the screen.
				ImageIO.write(screenFullImage, "GIF", new File(path));//Writes an image using an arbitrary ImageWriterthat supports the given format to a File
				JOptionPane.showMessageDialog(null, "Screen Shot Taken");//Brings up an information-message "Game Saved".
			} catch (AWTException | IOException | InterruptedException ex) {//to catch different exceptions that may occur.
			}
		}
	}
	//To save game.
	public class SaveGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				printBoard(); //calling printboard() to get output from the game.
				Formatter outfile = new Formatter("m:\\lab7.txt");//An interpreter for printf-style format strings.
				for (int k = 0; k < Save_Location.size(); k++) {
					outfile.format("%s", Save_Location.get(k));//Saving the data from the game to a txt file.
				}
				outfile.close();//Closing the formatter.
				JOptionPane.showMessageDialog(null, "Game Saved");//Brings up an information-message "Game Saved".
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	//To Reload the saved game.
	public void reload() throws IOException {
		FileReader readhandle = new FileReader("m:\\lab7.txt");//Reads text from character files 
		BufferedReader br = new BufferedReader(readhandle);//Creates a buffering character-input stream that uses a default-sizedinput buffer.
		int Line_Number = 0;
		String line;
		int Column = 0,i;
		while ((line = br.readLine()) != null) {//while lopp to read line until the last line.
			String Button_Value[] = line.split(" ");//Splits this string around matches of the given regular expression. 
			if (Column == Line_Number) {
				for (i = 0; i < Button_Value.length; i++) {//for loop to get the number of elements in a line.
					if (Button_Value[i].contains("R")) {//To find if element at 'i' position is "R".
						Game_Button[Column][i].setIcon(Red);//If element is "R" set button at that position as Red.
						Game_Board[Column][i].setAllPosition('X', i); // Set cell parameters
						Game_Board[Column][i].setCellState(1);
					} else if (Button_Value[i].contains("B")) {//To find if element at 'i' position is "B".
						Game_Button[Column][i].setIcon(Blue);//If element is "B" set button at that position as Blue.
						Game_Board[Column][i].setAllPosition('O', i); // Set cell parameters
						Game_Board[Column][i].setCellState(2);
					} else if (Button_Value[i].contains("_")) {//To find if element at 'i' position is "_".
						Game_Button[Column][i].setIcon(empty);//If element is "_" set button at that position as empty.
						Game_Board[Column][i].setCellState(0);
					}
				}
				
			}
			Line_Number++;//To increment line number.
			Column++;//To increment column number.
		}
		br.close();
		JOptionPane.showMessageDialog(null, "Game Reloaded");//Brings up an information-message "Game Reloaded".
	}
	//To do game reload.
	public class ReloadGame implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			try {
				reload();//calling reload().
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	//Creating the main game GUI
	public void Game_GUI() {
		
		JMenuItem exit, reset, pause, screenshot, NewGame, Save, Reload;//Creating new menu items.
		Game_Frame = new JFrame("Connect Five Game");//Creating the Main Game Frame.
		// Main Menu Construction
		JMenuBar menubar = new JMenuBar();//Creating  a new menu bar.
		Game_Frame.setJMenuBar(menubar);//Adding menubar to the main frame.
		Main_Menu = new JMenu("Game");//Creating a new main menu.
		menubar.add(Main_Menu);//Adding main menu to the menu bar.
		NewGame = new JMenuItem("New Game");////Creating new menu item New game.
		Main_Menu.add(NewGame);//addig new game to main menu.
		pause = new JMenuItem("Pause");//creating new menuitem Pause.
		Main_Menu.add(pause);//adding pause to main menu.
		reset = new JMenuItem("Reset");//creating new menuitem Reset.
		Main_Menu.add(reset);//adding reset to main menu.
		exit = new JMenuItem("Exit");//creating new menuitem Exit.
		Main_Menu.add(exit);//adding exit to main menu.
		//Additional Menu constructor.
		Additional_Menu = new JMenu("Additional");//creating a new Additional menu.
		menubar.add(Additional_Menu);//adding additonal menu to menubar.
		screenshot = new JMenuItem("Screenshot");//creating new menuitem screenshot.
		Additional_Menu.add(screenshot);//adding screenshot to additonal menu.
		Save = new JMenuItem("Save Game");//creating new menuiten save game.
		Additional_Menu.add(Save);//adding save game to additional menu
		Reload = new JMenuItem("Reload Game");//creating new menuitem reload game.
		Additional_Menu.add(Reload);//adding reload to additional menu. 
		helpMenu = new JMenu("Help");//Creating a new help menu.
		menubar.add(helpMenu);//Adding help menu to the menu bar.
		howToPlayItem = new JMenuItem("How to Play");//creating new menu item how to play.
		helpMenu.add(howToPlayItem);//adding howtoplay in help menu.
		aboutGame = new JMenuItem("About");//creating a new menuitem about.
		helpMenu.add(aboutGame);//adding about to help menu.
		NewGame.addActionListener(new NewGame());//adding action to the button new game.
		pause.addActionListener(new Pause());//adding action to the button pause game.
		reset.addActionListener(new Reset());//adding action to the button reset game.
		screenshot.addActionListener(new ScreenShot());//adding action to the button screenshot game.
		Save.addActionListener(new SaveGame());//adding action to the button save game.
		Reload.addActionListener(new ReloadGame());//adding action to the button reaload game.
		exit.addActionListener(new Exit());//adding action to the button exit game.
		howToPlayItem.addActionListener(new How_To_Play());//adding action to the button how to play game.
		aboutGame.addActionListener(new About_Game());//adding action to the button about game game.
		Game_Panel = new JPanel();//creating a new game panel.
		dynamicAllocation(); // Create 2D dynamic Cell array
		Game_Button = new JButton[getBoardRowSize()][getBoardColumnSize()]; // Create button array
		Game_Grid = new GridLayout(getBoardRowSize(), getBoardColumnSize()); // Create GridLayout
		Game_Panel.setLayout(Game_Grid);//setting game panel as per the size of grid.
		// Initialization board
		initialBoard();
		Status_Panel = new JPanel();//creating status panel for current game status.
		ScorePanel = new JPanel();//creating status panel for player win score.
		score1 = new JLabel("Score: Player 1: 0 || ");//For player 1 win score
		score1.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		score2 = new JLabel("Player 2: " + Score_count2);//For player 1 win score
		score2.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		statusLabel = new JLabel(Player_Turn);//For current player turn.
		statusLabel.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		turn1 = new JLabel("Player 1 played: 0");//Number of turn player 1 played.
		turn1.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		turn2 = new JLabel("Player 2 played: " + count2);//Number of turns player 2 played.
		turn2.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		ScorePanel.add(score1);//Adding player 1 score in scorePanel.
		ScorePanel.add(score2);//Adding player 2 score in scorePanel.
		Status_Panel.add(turn1);//Adding turn played by Player 1 in statusPanel.
		Status_Panel.add(statusLabel);//Adding which player turn statusPanel.
		Status_Panel.add(turn2);//Adding turn played by Player 1 in statuspanel.
		Game_Frame.add(Game_Panel, BorderLayout.CENTER);//Addind game panel in frame.
		Game_Frame.add(Status_Panel, BorderLayout.NORTH);//Adding game current status in frame.
		Game_Frame.add(ScorePanel, BorderLayout.SOUTH);//Adding current win score in frame.
		Game_Frame.pack(); // Automatic sizing of the window based on the added swing components
		Game_Frame.setExtendedState(Game_Frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);//This state frame is fully maximized(that is both horizontally and vertically).
		Game_Frame.setVisible(true); // Show frame
		Game_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
	}
	//How To play Game.
	public class How_To_Play implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"Click on the buttons to insert a new checker."
							+ "\nTo win you must place 5 checkers in an row, horizontally, vertically or diagonally.",
					"How to Play", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//About Game.
	public class About_Game implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(null,
					"� Created by: Murtaza Magar \nVersion 1.0" ,
					"About", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	//Pause Button.
	public class Pause implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game_Frame.setEnabled(false);//Disabling Main Game Frame to pause the game.
			Pause_Game = new JFrame();//Creating pause game frame.
			JPanel pausepanel = new JPanel();//creating panel for pause frame.
			pausepanel.setSize(300, 300);//setting the size of the panel.
			JLabel pauselabel = new JLabel();//Creating pause game label.
			JButton Resume_Game = new JButton("Resume Game");//Creating a new resume game button
			Resume_Game.setPreferredSize(new Dimension(100, 50));//setting the size of the button
			JButton exit = new JButton("Exit");//Creating a exit game button.
			exit.setPreferredSize(new Dimension(100, 50));//setting the size of the exit button.
			pauselabel.setText("Game Paused!!");//Setting the text of pause label.
			pauselabel.setFont(new Font("Arial", Font.BOLD, 24));//setting the font,style,size of the text.
			pausepanel.add(pauselabel);//adding pause label to the pause panel.
			Pause_Game.add(pausepanel, BorderLayout.NORTH);//adding pausepanel to the pause frame.
			Pause_Game.add(Resume_Game, BorderLayout.WEST);//adding resume button to pause frame
			Pause_Game.add(exit, BorderLayout.EAST);//Adding exit to pause frame.
			Resume_Game.addActionListener(new Resume_Game());//Adding action to the start button.
			exit.addActionListener(new Exit());//Adding action to the exit button.
			Pause_Game.pack(); // Automatic sizing of the window based on the added swing components
			Pause_Game.setResizable(false); // No resize to game board
			Pause_Game.setLocationRelativeTo(null); // Game board will be center of the screen
			Pause_Game.setVisible(true); // Show frame
		}
	}
	//Resume Game
	public class Resume_Game implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Game_Frame.setEnabled(true);//Enable the Game Frame.
			Pause_Game.setVisible(false);//hiding pause frame.
		}
	}
	//Reset game.
	public void Reset_Game() {
		Game_Frame.setEnabled(true);//enable The game Frame.
		playerOrder = resetplayer;
		if (playerOrder == 0) {
			Player_Turn = "    ||  Player 1 turn!!  ||    ";//Setting the player turn status
		} else {
			Player_Turn = "    ||  Player 2 turn!!  ||    ";//Setting the player turn status
		}
		count1 = 0;
		count2 = 0;
		Status_Panel.setVisible(false);//hiding previous status panel.
		Game_Panel.setVisible(false); // invisible previous game board
		Game_Panel = new JPanel();//creating new game panel.
		dynamicAllocation(); // Create 2D dynamic Cell array
		Game_Button = new JButton[getBoardRowSize()][getBoardColumnSize()]; // Create button array
		Game_Grid = new GridLayout(getBoardRowSize(), getBoardColumnSize()); // Create GridLayout
		Game_Panel.setLayout(Game_Grid);
		// Initialization board
		initialBoard();
		Status_Panel = new JPanel(new FlowLayout());//Creating new Status panel.
		ScorePanel = new JPanel(new FlowLayout());//Creating new score panel.
		turn1 = new JLabel("Player 1 played: 0");//Number of turn player 1 played.
		turn1.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		statusLabel = new JLabel(Player_Turn);//For current player turn.
		statusLabel.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		turn2 = new JLabel("Player 2 played: 0");//Number of turn player 2 played.
		turn2.setFont(new Font("Arial", Font.BOLD, 20));//setting the font,style,size of the text.
		Status_Panel.add(turn1);//adding number of turns played by player 1.
		Status_Panel.add(statusLabel);//adding who's turn it is to panel.
		Status_Panel.add(turn2);//Adding number of turns played by player 2.
		Game_Frame.add(Game_Panel, BorderLayout.CENTER);//adding game panel to game again.
		Game_Frame.add(Status_Panel, BorderLayout.NORTH);//adding status_Panel to game.
		Game_Frame.pack(); // Automatic sizing of the window based on the added swing components
		Game_Frame.setExtendedState(Game_Frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);//This state frame is fully maximized(that is both horizontally and vertically).
		Game_Frame.setVisible(true); // Show frame
		Game_Frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
	}
	//Reset Game.
	public class Reset implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Reset_Game();
		}// End ActionPerformed
	}// End Reset Class
	//Exit Game.
	public static class Exit implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}// End ActionPerformed
	}// End ExitClass
	//To set game board Size.
	public void setBoardSize(int newRowSize, int newColumnSize) {
		size_row = newRowSize;
		size_column = newColumnSize;
	}
	//to get game board row size.
	public int getBoardRowSize() {
		return size_row;
	}
	//To get game board column size.
	public int getBoardColumnSize() {
		return size_column;
	}
	//To get area of game board i.e. Number of rows X Number of Columns.
	public int getBoardRow_ColumnSize() {
		return size_row * size_column;
	}
	//Getting Total number of Checkers in game.
	public static int numberOfLivingCells() {
		return Occupied_buttons;
	}

	//Taking input of Game mode, Color, number of row, number of columns.
	public void playerNumberAndBoardSize() {
		Toss_winner.setVisible(false);//Hiding toss winner frame.
		// User inputs from input dialogs
		Coin_Toss.setVisible(false);//Hiding coin toss frame.
		JFrame frameInputError = new JFrame();//Creating new Wrong input error frame.
		String playerNumber = JOptionPane
				.showInputDialog("Input 1: For Human vs Computer.\nInput 2: For Human vs Human");
		GameMode = Integer.parseInt(playerNumber);//Taking the input from user for game Mode.
		while(GameMode != 1 && GameMode != 2) {//If input is wrong this will pop up an error box.
			JOptionPane.showMessageDialog(frameInputError, "Input a correct option!!", "Wrong Input",
					JOptionPane.ERROR_MESSAGE);
			playerNumber = JOptionPane
					.showInputDialog("Input 1: For Human vs Computer.\nInput 2: For Human vs Human");
			GameMode = Integer.parseInt(playerNumber);//Taking the input from user for game Mode.
		}
		String playercolor = JOptionPane.showInputDialog("Input 1: For Red Color.\nInput 2: For Blue Color.");
		Input_Colour = Integer.parseInt(playercolor);//Taking the input from user for color.
		while(Input_Colour != 1 && Input_Colour != 2) {//If input is wrong this will pop up an error box.
			JOptionPane.showMessageDialog(frameInputError, "Input a correct option!!", "Wrong Input",
					JOptionPane.ERROR_MESSAGE);
			playercolor = JOptionPane.showInputDialog("Input 1: For Red Color.\nInput 2: For Blue Color.");
			Input_Colour = Integer.parseInt(playercolor);//Taking the input from user for color.
		}
		String rows = JOptionPane.showInputDialog("Number of Rows (5-8 only)");
		int sizeofrows = Integer.parseInt(rows);//Taking the input from user for number of rows.
		while(sizeofrows < 5) {//If input is wrong this will pop up an error box.
			JOptionPane.showMessageDialog(frameInputError, "Size of rows must be greater than 5!!", "Rows Size Error",
					JOptionPane.ERROR_MESSAGE);
			rows = JOptionPane.showInputDialog("Number of Rows (5-8 only)");
			sizeofrows = Integer.parseInt(rows);//Taking the input from user for number of rows.
		}
		while(sizeofrows > 8) {//If input is wrong this will pop up an error box.
			JOptionPane.showMessageDialog(frameInputError, "Size of rows must be less then equal to 8!!",
					"Rows Size Error", JOptionPane.ERROR_MESSAGE);
			rows = JOptionPane.showInputDialog("Number of Rows (5-8 only)");
			sizeofrows = Integer.parseInt(rows);//Taking the input from user for number of rows.
		}
		String columns = JOptionPane.showInputDialog("Number of columns (5-8 only)");
		int sizeofcolumns = Integer.parseInt(columns);//Taking the input from user for number of columns.
		while(sizeofcolumns < 5) {//If input is wrong this will pop up an error box.
			JOptionPane.showMessageDialog(frameInputError, "Size of columns must be greater than 5!!",
					"Columns Size Error", JOptionPane.ERROR_MESSAGE);
			columns = JOptionPane.showInputDialog("Number of columns (5-8 only)");
			sizeofcolumns = Integer.parseInt(columns);//Taking the input from user for number of columns.
		}
		while(sizeofcolumns > 8) {//If input is wrong this will pop up an error box.
			JOptionPane.showMessageDialog(frameInputError, "Size of columns must be less then equal to 8!!",
					"Columns Size Error", JOptionPane.ERROR_MESSAGE);
			columns = JOptionPane.showInputDialog("Number of columns (5-8 only)");
			sizeofcolumns = Integer.parseInt(columns);//Taking the input from user for number of columns.
		}
		setBoardSize(sizeofrows, sizeofcolumns); // Set to dynamic board size
		Start_Game.setVisible(false);
	}
	//To save game played.
	public void printBoard() throws IOException {
		char space = ' ';
		for (int r = 0; r <= size_row - 1; r++) {
			for (int c = 0; c < size_column; c++) {
				space = '_';//For empty slots.
				if (Game_Button[r][c].getIcon() == Red) {
					space = 'R';//For Slots with red checker.
				} else if (Game_Button[r][c].getIcon() == Blue) {
					space = 'B';//For slots with blue checker.
				}
				Save_Location.add(space + " ");//Adding the checkers to the arraw list.
			}

			Save_Location.add("\n");//Adding new line after every column.
		}
	}
	//To Find Draw
	public Boolean full() {
		Boolean Draw;
		for (int i = 0; i < size_row; i++) {
			if (Game_Button[0][i].getIcon() == empty) {
				Draw=false;
			}
		}
		Draw=true;
		return Draw;
	}

	  //Create 2D dynamic Cell array
	public void dynamicAllocation() {
		// Create dynamic Cell array to game board
		Game_Board = new Cell[getBoardRowSize()][getBoardColumnSize()];
		for (int i = 0; i < getBoardRowSize(); i++) {
			for (int j = 0; j < getBoardColumnSize(); j++) {
				Game_Board[i][j] = new Cell();
			}
		}
	}

	 //Add buttons to game board
	public void addButtonsToBoard() {
		for (int i = 0; i < getBoardRowSize(); ++i) {
			for (int j = 0; j < getBoardColumnSize(); ++j) {
				Game_Button[i][j] = new JButton(empty); // Empty button
				if (GameMode == 1) // For Computer vs Human Game.
				{
					Game_Button[i][j].addActionListener(new Human_Vs_Computer());//going to Human v/s computer mode of game
				}
				if (GameMode == 2) // For Human vs Human.
				{
					Game_Button[i][j].addActionListener(new Human_VS_Human());//going to Human v/s Human mode of game.
				}
				Game_Panel.add(Game_Button[i][j]); // Add buttons to panel
			}
		}
	}

	//Initial all cells to empty
	public void initialBoard() {
		for (int i = getBoardRowSize() - 2; i >= 0; --i) {
			for (int j = getBoardColumnSize() - 1; j >= 0; --j) {
				Game_Board[i][j].setCellState(-99);
			}
		}
		addButtonsToBoard(); // Add buttons.
	}

	 //Game winning state If the four cell is same, user 1 will win the game
	 //winner integer If the player 1 is equal to 1, otherwise 2
	 /* @throws IOException
	 */
	public void winnerPlayer(int winner) throws IOException {
		for (int i = 0; i < getBoardRowSize(); ++i) {
			for (int j = 0; j < getBoardColumnSize(); ++j) {
				if (Game_Board[i][j].getCellState() == winner) {
					// CHECK UP TO DOWN POSITIONS
					if (i + 4 < getBoardRowSize()) {
						if (Game_Board[i + 1][j].getCellState() == winner
								&& Game_Board[i + 2][j].getCellState() == winner
								&& Game_Board[i + 3][j].getCellState() == winner
								&& Game_Board[i + 4][j].getCellState() == winner) {
							if (winner == 1) {//If winner is player 1.
								showResult(1);//It will show the result. 
							} else if (winner == 2) {//If winner is player 2.
								showResult(2);//It will show the result.
							} else
								showResult(0);
						}
					}
					// CHECK LEFT TO RIGHT POSITION
					if (j + 4 < getBoardColumnSize()) {
						if (Game_Board[i][j + 1].getCellState() == winner
								&& Game_Board[i][j + 2].getCellState() == winner
								&& Game_Board[i][j + 3].getCellState() == winner
								&& Game_Board[i][j + 4].getCellState() == winner) {
							if (winner == 1) {//If winner is player 1.
								showResult(1);//It will show the result. 
							} else if (winner == 2) {//If winner is player 2.
								showResult(2);//It will show the result.
							} else
								showResult(0);
						}
					}
					// CHECK DIAGONAL LEFT TO RIGHT POSITION
					if (i < getBoardRowSize() - 4 && j < getBoardColumnSize() - 4) {
						if (Game_Board[i + 1][j + 1].getCellState() == winner
								&& Game_Board[i + 2][j + 2].getCellState() == winner
								&& Game_Board[i + 3][j + 3].getCellState() == winner
								&& Game_Board[i + 4][j + 4].getCellState() == winner) {
							if (winner == 1) {//If winner is player 1.
								showResult(1);//It will show the result. 
							} else if (winner == 2) {//If winner is player 2.
								showResult(2);//It will show the result.
							} else
								showResult(0);
						}
					}
					// CHECK DIAGONAL RIGHT TO LEFT POSITION
					if (i < getBoardRowSize() - 4 && j - 4 >= 0) {
						if (Game_Board[i + 1][j - 1].getCellState() == winner
								&& Game_Board[i + 2][j - 2].getCellState() == winner
								&& Game_Board[i + 3][j - 3].getCellState() == winner
								&& Game_Board[i + 4][j - 4].getCellState() == winner) {
							if (winner == 1) {//If winner is player 1.
								showResult(1);//It will show the result. 
							} else if (winner == 2) {//If winner is player 2.
								showResult(2);//It will show the result.
							} else
								showResult(0);
						}
					}
				}
			}
		}
	} // End winnerPlayer function

	 //Show winner player on the new frame
	 //winnerPlayer integer if the parameter is equal to 1,player 1 is winner.Otherwise, player 2
	 /* @throws IOException
	 */
	public void showResult(int winnerPlayer) throws IOException {
		Resultframe = new JFrame("Result");//Creating a new Result frame.
		JPanel Display = new JPanel();//Creating a new Diaply result panel.
		Display.setSize(390, 200);//setting the size of the panel.
		JPanel Status = new JPanel();//creating a new Status panel.
		winner_label = new JLabel();//Creating a new winning label.
		Winner = winnerPlayer;
		if (winnerPlayer == 1) {//If winner if player 1 it will show the result.
			Score_count1++;
			score1.setText("Score: Player 1: " + Score_count1 + " || ");//It will increment the number of win by player 1.
			winner_label.setForeground(Color.BLACK);//setting the color of winning result.
			winner_label.setText("Congratulations! Player 1 Wins!");//setting the text of the result frame.
		} else if (winnerPlayer == 2) {//If winner if player 2 it will show the result.
			Score_count2++;
			score2.setText("Player 2: " + Score_count2);//It will increment the number of win by player 2.
			winner_label.setForeground(Color.BLACK);//setting the color of winning result.
			winner_label.setText("Congratulations! Player 2 Wins!");//setting the text of the result frame.
		} else if (full()==true) {//If it's a draw it will show the result.
			winner_label.setForeground(Color.BLACK);//setting the color of winning result.
			winner_label.setText("draw!");//setting the text of the result frame.
		}
		Game_Frame.setEnabled(false);
		winner_label.setFont(new Font("Arial", Font.BOLD, 24));
		Reset = new JButton("Reset");//creating a new reset button after win.
		Reset.setPreferredSize(new Dimension(200, 50));//setting the size of the reset button.
		Reset.addActionListener(new Reset());//adding action to the reset button.
		Exit = new JButton("Exit");//creating a new exit button.
		Exit.setPreferredSize(new Dimension(200, 50));//setting the size of the exit button.
		Exit.addActionListener(new Exit());//adding action to the reset button.
		Display.add(winner_label);//adding label to the display.
		Status.add(Reset);//adding reset button to the winning frame.
		Status.add(Exit);//adding exit button to the winning frame.
		Resultframe.add(Display, BorderLayout.CENTER);//Adding display panel to the frame.
		Resultframe.add(Status, BorderLayout.SOUTH);//Adding status panel to the frame.
		Resultframe.pack(); // Automatic sizing of the window based on the added swing components
		Resultframe.setResizable(false); // No resize to game board
		Resultframe.setLocationRelativeTo(null); // Game board will be center of the screen
		Resultframe.setVisible(true); // Show frame
		Resultframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the frame
	}

	 //After the game ends, start from the beginning again
	public void startAgain() {
		for (int i = 0; i < getBoardRowSize(); ++i) {
			for (int j = 0; j < getBoardColumnSize(); ++j) {
				Game_Board[i][j].setCellState(-99); // Initial Value
				Game_Button[i][j].setIcon(empty); // Put the empty cell icon
			}
		}
		Game_Frame.setVisible(false); // invisible previous game board
		ConnectFive newGame = new ConnectFive(); // New Game Object and starting the game again.
	}

	//Action listener to game button Computer vs Human
	private class Human_Vs_Computer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				for (int i = getBoardRowSize() - 1; i >= 0; --i) // Check the buttons up to down position
				{
					for (int j = 0; j <= getBoardColumnSize() - 1; ++j) {
						// Get the button component that was clicked
						if (Game_Button[i][j] == e.getSource()) {
							if (0 == playerOrder % 2) // Player 1 operations
							{
								for (int k = 0; k <= getBoardRow_ColumnSize(); ++i) {
									// Player 1 Operations
									// Fill the board from down to up
									if (Input_Colour == 1) {
										if (Game_Board[i - k][j].getCellState() == 0 && playerOrder % 2 == 0) {
											Game_Button[i - k][j].setIcon(Red); // Set new icon to player 1
											Game_Board[i - k][j].setAllPosition('X', i); // Set cell parameters
											Game_Board[i - k][j].setCellState(1);
											++Occupied_buttons; // Increase living cell number
											winnerPlayer(1); // Check player 1 winning state
											break;
										}
									}
									if (Input_Colour == 2) {
										if (Game_Board[i - k][j].getCellState() == 0 && playerOrder % 2 == 0) {
											Game_Button[i - k][j].setIcon(Blue); // Set new icon to player 1
											Game_Board[i - k][j].setAllPosition('X', i); // Set cell parameters
											Game_Board[i - k][j].setCellState(1);
											++Occupied_buttons; // Increase living cell number
											winnerPlayer(1); // Check player 1 winning state
											break;
										}
									}
								}
								setUpperCellToEmpty(i, j); // Set the upper cells to empty cell to listen button
								Player_Turn = "    ||  Player 2 turn!!  ||    ";
								statusLabel.setText(Player_Turn);
								count1++;
								turn1.setText("Player 1 played: " + count1);
								++playerOrder; // Change order from player 1 to player 2
								break;
							}
							// Computer Operations
							// Basic idea is filling the cells left to right
							if (1 == playerOrder % 2) {
								Player_Turn = "    ||  Player 1 turn!!  ||    ";
								statusLabel.setText(Player_Turn);
								count2++;
								turn2.setText("Player 2 played: " + count2);
								moveComputer(i);

								++playerOrder;
								break;
							} else {
								warningMessage();
							}
						} // END EVENT SOURCE
					} // END SECOND FOR LOOP
				} // END FIRST FOR LOOP
			} // END TRY
			catch (Exception ex) {
				warningMessage();
			}
		} // END ACTION PERFORMED
	} // END listenButtonOnePlayer CLASS

	public void warningMessage() {
		JFrame frameWarning = new JFrame();
		JOptionPane.showMessageDialog(frameWarning, "Invalid Movement !!\nThe cell is not empty.", "Warning",
				JOptionPane.WARNING_MESSAGE);
	}

	 //Set the upper cells to empty cell to listen button
	public void setUpperCellToEmpty(int rowPos, int columnPos) {
		try {
			Game_Board[rowPos - 1][columnPos].setCellState(0);
		} catch (Exception ex) {
		}
	}

	 //Computer's logic fills cells from left to right
	public void moveComputer(int rowPosition) throws IOException {
		int l, m;
		boolean flag = false;

		for (l = getBoardRowSize() - 1; (l >= 0) && !flag; --l) {
			for (m = 0; (m < getBoardColumnSize()) && !flag; ++m) {
				if (Input_Colour == 2) {
					if (Game_Board[l][m].getCellState() == 0) {
						Game_Button[l][m].setIcon(Red); // Set new button icon
						Game_Board[l][m].setAllPosition('O', rowPosition); // Set cell parameters
						Game_Board[l][m].setCellState(2); // Set cell state
						++Occupied_buttons;
						winnerPlayer(2); // Check the computer winning state
						flag = true;
						setUpperCellToEmpty(l, m);
					}
				} else {
					if (Game_Board[l][m].getCellState() == 0) {
						Game_Button[l][m].setIcon(Blue); // Set new button icon
						Game_Board[l][m].setAllPosition('O', rowPosition); // Set cell parameters
						Game_Board[l][m].setCellState(2); // Set cell state
						++Occupied_buttons;
						winnerPlayer(2); // Check the computer winning state
						flag = true;
						setUpperCellToEmpty(l, m);
					}
				}
			}
		}
	}

	 //Action listener to game button Player 1 vs Player 2
	private class Human_VS_Human implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int eventFlag = 0;
				int flagPlayerOrder = 0;
				for (int i = getBoardRowSize() - 1; i >= 0; --i) {
					for (int j = 0; j <= getBoardColumnSize() - 1; ++j) {
						if (eventFlag == 0 && Game_Button[i][j] == e.getSource()) // Get the button component that was
						{
							if (flagPlayerOrder == 0 && playerOrder % 2 == 0) {
								// Player 1 Operations
								// Fill the board from down to up
								for (int k = 0; k <= getBoardRow_ColumnSize(); ++i) {
									if (Input_Colour == 1) {
										if (Game_Board[i - k][j].getCellState() == 0 && playerOrder % 2 == 0) {
											Game_Button[i - k][j].setIcon(Red); // Set new icon to player 1
											Game_Board[i - k][j].setAllPosition('X', i); // Set cell parameters
											Game_Board[i - k][j].setCellState(1);
											++Occupied_buttons; // Increase living cell number
											winnerPlayer(1); // Check player 1 winning state
											flagPlayerOrder = 1;
											eventFlag = 1;
											break;
										}
									}
									else if (Input_Colour == 2) {
										if (Game_Board[i - k][j].getCellState() == 0 && playerOrder % 2 == 0) {
											Game_Button[i - k][j].setIcon(Blue); // Set new icon to player 1
											Game_Board[i - k][j].setAllPosition('X', i); // Set cell parameters
											Game_Board[i - k][j].setCellState(1);
											++Occupied_buttons; // Increase living cell number
											winnerPlayer(1); // Check player 1 winning state
											flagPlayerOrder = 1;
											eventFlag = 1;
											break;
										}
									}
								}
								setUpperCellToEmpty(i, j); // Set upper cell to empty
								Player_Turn = "    ||  Player 2 turn!!  ||    ";
								statusLabel.setText(Player_Turn);
								count1++;
								turn1.setText("Player 1 played: " + count1);
								++playerOrder; // Change order from player 1 to player 2
								break;
							}
							// Player 2 operations
							if (flagPlayerOrder == 0 && playerOrder % 2 == 1) {
								for (int k = 0; k <= getBoardRow_ColumnSize(); ++i) {
									if (Input_Colour == 2) {
										if (Game_Board[i - k][j].getCellState() == 0 && playerOrder % 2 == 1) {
											Game_Button[i - k][j].setIcon(Red); // Set new icon to player 2
											Game_Board[i - k][j].setAllPosition('O', i); // Set cell parameters
											Game_Board[i - k][j].setCellState(2); // Set cell state
											++Occupied_buttons;
											winnerPlayer(2);
											flagPlayerOrder = 1;
											eventFlag = 1;
											break;
										}
									} else {
										if (Game_Board[i - k][j].getCellState() == 0 && playerOrder % 2 == 1) {
											Game_Button[i - k][j].setIcon(Blue); // Set new icon to player 2
											Game_Board[i - k][j].setAllPosition('O', i); // Set cell parameters
											Game_Board[i - k][j].setCellState(2); // Set cell state
											++Occupied_buttons;
											winnerPlayer(2);
											flagPlayerOrder = 1;
											eventFlag = 1;
											break;
										}
									}

								}
								setUpperCellToEmpty(i, j);
								Player_Turn = "    ||  Player 1 turn!!  ||    ";
								statusLabel.setText(Player_Turn);
								count2++;
								turn2.setText("Player 2 played: " + count2);
								++playerOrder;
								break;
							}
						} // END EVENT SOURCE
					} // END SECOND FOR LOOP
				} // END FIRST FOR LOOP
			} catch (Exception ex) {
				warningMessage();
			}

		} // END ACTIONPERFORMED
	} // END listenButtonTwoPlayers CLASS
} // END CONNECTFOUR CLASS
