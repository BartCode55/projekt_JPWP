import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener{
	
	JLabel titleLabel;
	JLabel menuLabel;
	JLabel timeLabel;
	JPanel menuPanel;//Panel w prawej częsci ekranu posiadający menu opcji takich jak reset trudniejszy poziom i wyjscie z gry oraz licznik czasu rozgrywki i informacja o bombach
	JPanel titlePanel;//Panel na górze ekranu, który informuje o wygranej lub przegranej a domyślnie posiada tytuł gry
	JPanel gamePanel;//Panel głównej rozgrywki
	JButton[] menuButtons;
	JButton[][] gameButtons;
	ImageIcon bomb = new ImageIcon("bomb.png");
	ImageIcon bomb2 = new ImageIcon("bomb2.png");
	Gameplay test;
	
	int time = 0;
	int seconds = 0;
	int minutes = 0;
	boolean firstPress = false;
	boolean hardMap;
	int pressMeter;
	String secondsString = String.format("%02d", seconds);
	String minutesString = String.format("%02d", minutes);
	
	int sizeOfGrid;
	int xPos;
	int yPos;
	String bombsAroundString;
	
	Timer timeMeter = new Timer(1000, new ActionListener() {
		
		public void actionPerformed(ActionEvent e) {
			
			time+=1000;
			seconds = (time/1000) % 60;
			minutes = (time/60000) % 60;
			secondsString = String.format("%02d", seconds);
			minutesString = String.format("%02d", minutes);
			timeLabel.setText(minutesString+":"+secondsString);
		}
	});
	
	

	GameFrame(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,1024);
		this.setLayout(null);
		this.setResizable(false);
		this.setTitle("Bombowa Łamigłówka");
		this.setIconImage(bomb.getImage());
		this.getContentPane().setBackground(new Color(0x8D91C7));
		
		gameplay();
				
		createGamePanel();
		createMenuPanel();
		createTitlePanel();
		
		this.add(menuPanel);
		this.add(titlePanel);
		this.add(gamePanel);
		
		this.setVisible(true);
	}
	
	public void createGamePanel()
	{
		sizeOfGrid = 10;
		gamePanel = new JPanel();
		gamePanel.setBackground(new Color(0x313DE6));
		gamePanel.setBounds(0, 100, 1080, 743);//924 ostatnie 743 dzialalo
		//gamePanel.setPreferredSize(new Dimension(1080,200));
		gamePanel.setLayout(new GridLayout(sizeOfGrid, sizeOfGrid));
		
		
		gameButtons = new JButton[sizeOfGrid][sizeOfGrid];
		
		for (int i=0; i<gameButtons.length; i++)
		{
			for (int j=0; j<gameButtons[0].length; j++)
			{
				gameButtons[i][j] = new JButton();
				gameButtons[i][j].setFocusable(false);
				gameButtons[i][j].addActionListener(this);
				gameButtons[i][j].setText("");
				gameButtons[i][j].setFont(new Font("Times New Roman", Font.BOLD, 25));
				gameButtons[i][j].setBackground(new Color(0x47128E));
				gamePanel.add(gameButtons[i][j]);
			}
		}
		
	}
	public void createMenuPanel()
	{
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0x353C9F));
		menuPanel.setLayout(null);
		//menuPanel.setPreferredSize(new Dimension(200,200));
		menuPanel.setBounds(1080, 100, 200, 924);
		
		//dodac label odpowiedzialny za licznik bomb
		
		menuLabel = new JLabel();//ROZWINAC JLABEL
		timeLabel = new JLabel();
		menuButtons = new JButton[3];
		for (int i=0; i<3; i++)
		{
			menuButtons[i] = new JButton();
			menuPanel.add(menuButtons[i]);
			menuButtons[i].setFocusable(false);
			menuButtons[i].addActionListener(this);
		}
		
		menuButtons[0].setBounds(25, 425, 140, 70);
		menuButtons[0].setText("Reset");
		menuButtons[0].setFont(new Font("Times New Roman", Font.BOLD, 20));
		
		menuButtons[1].setBounds(25, 525, 140, 70);
		menuButtons[1].setText("Trudniejszy poziom");
		menuButtons[1].setFont(new Font("Times New Roman", Font.BOLD, 13));
		
		menuButtons[2].setBounds(25, 625, 140, 70);
		menuButtons[2].setText("Wyjscie z Gry");
		menuButtons[2].setFont(new Font("Times New Roman", Font.BOLD, 17));
		
		menuLabel.setBounds(0, 300, 200, 100);
		menuLabel.setText("Menu:");
		menuLabel.setForeground(new Color(0x27272C));//
		menuLabel.setVerticalAlignment(JLabel.BOTTOM);
		menuLabel.setHorizontalAlignment(JLabel.CENTER);
		menuLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		
		timeLabel.setBounds(50, 0, 200, 100);
		timeLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		timeLabel.setForeground(new Color(0x27272C));
		
		timeLabel.setText(minutesString+":"+secondsString);
		
		menuPanel.add(timeLabel);
		menuPanel.add(menuLabel);

	}
	public void createTitlePanel()
	{
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0x8A90DF));
		titlePanel.setBounds(0,0, 1280, 100);//100
		//titlePanel.setPreferredSize(new Dimension(200,100));
		
		titleLabel = new JLabel();
		titleLabel.setText("Bombowa Lamiglowka");
		titleLabel.setHorizontalTextPosition(JLabel.LEFT);
		//titleLabel.setVerticalAlignment(JLabel.TOP);
		titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 59));
		titleLabel.setForeground(new Color(0x404047));
		titleLabel.setIcon(bomb);
		
		titlePanel.add(titleLabel);
	}
	
	public void gameplay()// int xPos, int yPos to sie wykorzysta w nowej klasie prawdopodobnie zwiazanej z logiką gry
	{
		test = new Gameplay();
		test.randomizeBombs();
	}
	
	public void resetTime()
	{
		timeMeter.stop();
		time=0;
		seconds =0;
		minutes=0;
		secondsString = String.format("%02d", seconds);
		minutesString = String.format("%02d", minutes);
		timeLabel.setText(minutesString+":"+secondsString);
		firstPress = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menuButtons[2])
		{
			System.exit(0);
		}
		if(e.getSource() == menuButtons[0])
		{
			resetTime();
			for (int i=0; i<gameButtons.length; i++)
			{
				for (int j=0; j<gameButtons[0].length; j++)
				{
					gameButtons[i][j].setBackground(new Color(0x47128E));
					gameButtons[i][j].setEnabled(true);
					gameButtons[i][j].setText("");
					gameButtons[i][j].setIcon(null);
				}
			}
			test.randomizeBombs();
			titleLabel.setText("Bombowa Lamiglowka");
		}
		for (int i=0; i<gameButtons.length; i++)
		{
			for (int j=0; j<gameButtons[0].length; j++)
			{
				if(e.getSource()==gameButtons[i][j])
				{
					if(firstPress == false)
					{
						timeMeter.start();
						firstPress = true;
					}
					if(test.isBomb[i][j] == true)
					{
						//gameButtons[i][j].setBackground(Color.RED);
						//gameButtons[i][j].setIcon(bomb2);
						
						timeMeter.stop();
						titleLabel.setText("Niestety Przegrywasz!");
						for (int k=0; k<gameButtons.length; k++)
						{
							for (int l=0; l<gameButtons[0].length; l++)
							{
								gameButtons[k][l].setEnabled(false);
								if(test.isBomb[k][l] == true)
								{
									gameButtons[k][l].setBackground(Color.RED);
									gameButtons[k][l].setIcon(bomb2);
								}
							}
						}
						
					}
					else
					{
						bombsAroundString = String.valueOf(test.bombsAround[i][j]);
						gameButtons[i][j].setText(bombsAroundString);//
						gameButtons[i][j].setBackground(Color.GREEN);
						pressMeter++;
					}
					gameButtons[i][j].setEnabled(false);
					if(pressMeter == 85)
					{
						titleLabel.setText("Zwyciestwo!");
						timeMeter.stop();
						for (int k=0; k<gameButtons.length; k++)
						{
							for (int l=0; l<gameButtons[0].length; l++)
							{
								gameButtons[k][l].setEnabled(false);
								if(test.isBomb[k][l] == true)
								{
									gameButtons[k][l].setIcon(bomb2);
								}
							}
						}
					}
				}
			}
		}
	}
}