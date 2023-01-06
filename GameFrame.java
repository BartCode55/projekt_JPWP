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
	
	JLabel titleLabel;//Label odpowiedzialny za napis na gorze ekranu
	JLabel menuLabel;//Label odpowiedzialny za napis Menu:
	JLabel timeLabel;//Label w ktorym pokazany jest i dziala miernik czasu rozgrywki
	JPanel menuPanel;//Panel w prawej częsci ekranu posiadający menu opcji takich jak reset trudniejszy poziom i wyjscie z gry oraz licznik czasu rozgrywki i informacja o bombach
	JPanel titlePanel;//Panel na górze ekranu, który informuje o wygranej lub przegranej a domyślnie posiada tytuł gry
	JPanel gamePanel;//Panel głównej rozgrywki
	JButton[] menuButtons;//4 przyciski z panelu menu
	JButton[][] gameButtons;//przyciski rozgrywki tablica przechowuje 10x10 przyciskow dla latwego poziomu i 15x15 przyciskow dla trudnego
	ImageIcon bomb = new ImageIcon("bomb.png");//ikonka bomby widoczna obok napisu na gorze ekranu oraz ikonka gry
	ImageIcon bomb2 = new ImageIcon("bomb2.png");//ikona bomby w grze
	ImageIcon flag = new ImageIcon("flag.png");//ikona flagi w grze
	Gameplay test;//obiekt klasy gameplay wykorzystywany w metodzie gameplay
	
	int time = 0;
	int seconds = 0;
	int minutes = 0;
	boolean firstPress = false;//bool ktory sprawdza pierwsze nacisniecie zeby rozpoczac odliczanie miernika czasu
	boolean hardMap = false;//bool ktory sprawdza czy jest wlaczony trudniejszy poziom
	boolean flagButton = false;//bool ktory sprawdza nacisniecie przycisku marker
	int pressMeter;// miernik nacisniec dzieki ktoremu mozna okreslic zwyciestwo poprzez wcisniecie wszystkich pol bez bomby
	String secondsString = String.format("%02d", seconds);
	String minutesString = String.format("%02d", minutes);
	
	int sizeOfGrid = 10;//zmienna okrelsajaca rozmiar planszy ilosc przyciskow
	int xPos;
	int yPos;
	String bombsAroundString;
	
	//obiekt czasomierzu
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
	
	//konstruktor w ktorym tworzone sa poszczegolne panele oraz pierwszy raz losowanie z funkcji gameplay
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
	//metoda tworzaca panel gry oraz guziki rozgrywki
	public void createGamePanel()
	{
		sizeOfGrid = 10;
		gamePanel = new JPanel();
		gamePanel.setBackground(new Color(0x313DE6));
		gamePanel.setBounds(0, 100, 1080, 743);
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
				gameButtons[i][j].setFont(new Font("Times New Roman", Font.BOLD, 35));
				gameButtons[i][j].setBackground(new Color(0x47128E));
				gamePanel.add(gameButtons[i][j]);
			}
		}
	}
	//metoda tworzaca panel gry oraz guziki dla poziomu trudnego
	public void createGamePanel2()
	{
		for (int i=0; i<gameButtons.length; i++)
		{
			for (int j=0; j<gameButtons[0].length; j++)
			{
				gamePanel.remove(gameButtons[i][j]);
			}
		}
		this.remove(gamePanel);
		
		sizeOfGrid = 15;
		gamePanel = new JPanel();
		gamePanel.setBackground(new Color(0x313DE6));
		gamePanel.setBounds(0, 100, 1080, 743);
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
				gameButtons[i][j].setFont(new Font("Times New Roman", Font.BOLD, 35));
				gameButtons[i][j].setBackground(new Color(0x47128E));
				gamePanel.add(gameButtons[i][j]);
			}
		}
		test.randomizeBombs(15, true);
		this.add(gamePanel);
	}
	//metoda odpowiadajaca za tworzenie panelu menu wraz z guzikami menu
	public void createMenuPanel()
	{
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0x353C9F));
		menuPanel.setLayout(null);
		menuPanel.setBounds(1080, 100, 200, 924);
		
		menuLabel = new JLabel();//
		timeLabel = new JLabel();
		menuButtons = new JButton[4];
		for (int i=0; i<4; i++)
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
		
		menuButtons[3].setBounds(25, 275, 140, 70);
		menuButtons[3].setText("Marker");
		menuButtons[3].setFont(new Font("Times New Roman", Font.BOLD, 20));
		
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
	//tworzenie panelu z napisem tytulowym ktory zmienia sie w zaleznosci od wygranej lub przegranej
	public void createTitlePanel()
	{
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0x8A90DF));
		titlePanel.setBounds(0,0, 1280, 100);
		
		titleLabel = new JLabel();
		titleLabel.setText("Bombowa Lamiglowka");
		titleLabel.setHorizontalTextPosition(JLabel.LEFT);
		titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 59));
		titleLabel.setForeground(new Color(0x404047));
		titleLabel.setIcon(bomb);
		
		titlePanel.add(titleLabel);
	}
	//metoda wywolujaca metody klasy gameplay
	public void gameplay()
	{
		test = new Gameplay();
		if(hardMap == false)
		{
			test.randomizeBombs(10, false);
		}
		if(hardMap == true)
		{
			test.randomizeBombs(15, true);
		}
	}
	//metoda ktora resetuje licznik czasu
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
		if(e.getSource() == menuButtons[3])//zmiana flagi na true pozwala naciskac na guziki gry by je oznaczyc jesli spodziewamy sie bomby
		{
			if(flagButton == false)
			{
				flagButton = true;
				menuButtons[3].setForeground(Color.ORANGE);
			}
			else
			{
				flagButton = false;
				menuButtons[3].setForeground(null);
			}
		}
		if(e.getSource() == menuButtons[2])//wyjscie z gry
		{
			System.exit(0);
		}
		if(e.getSource() == menuButtons[1])//zmiana mapy na trudna - wywolanie metody gamepanel2
		{
			hardMap = true;
			createGamePanel2();
			gamePanel.revalidate();
			gamePanel.repaint();
		}
		if(e.getSource() == menuButtons[0])//reset gry: oczyszczenie nazw pol gry, ponowne losowanie bomb, reset czasomierzu
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
			pressMeter = 0;
			if(hardMap == false)
			{
				test.randomizeBombs(10, false);
			}
			if(hardMap == true)
			{
				test.randomizeBombs(15, true);
			}
			titleLabel.setText("Bombowa Lamiglowka");
		}
		for (int i=0; i<gameButtons.length; i++)//reakcja na wcisniecie buttona w rozgrywce w zaleznosci od tego czy jest tam bomba czy nie, oraz czy wcisniety jest guzik marker
		{
			for (int j=0; j<gameButtons[0].length; j++)
			{
				if(e.getSource()==gameButtons[i][j])
				{
					if(flagButton == false)//w przypadku kiedy gracz nie wcisnal guzika marker
					{
						if(firstPress == false)//jesli gracz wcisnal pierwszy guzik to uruchamia czasomierz
						{
							timeMeter.start();
							firstPress = true;
						}
						if(test.isBomb[i][j] == true)//w przypadku kiedy w polu ktory gracz wcisnal jest bomba porownanie tablicy isBomb z tablica lokalizacji buttona
						{
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
						else//w przypadku kiedy guzik nie ma bomby dodawana jest wartosc bomb wokolo z wartosci bombsAroundString
						{
							bombsAroundString = String.valueOf(test.bombsAround[i][j]);
							gameButtons[i][j].setText(bombsAroundString);//
							gameButtons[i][j].setBackground(Color.GREEN);
							gameButtons[i][j].setIcon(null);
							pressMeter++;
						}
						gameButtons[i][j].setEnabled(false);
						if(hardMap == false)
						{
							if(pressMeter == 85)//uruchamia sie dla latwiejszego poziomu rozgrywki, po wcisnieciu 85 guzikow bez bomby czyli wszystkich mozliwych
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
											pressMeter = 0;
										}
									}
								}
							}
						}
						if(hardMap == true)//uruchamia sie dla trudniejszego poziomu rozgrywki, po wcisnieciu 185 guzikow bez bomby czyli wszystkich mozliwych
						{
							if(pressMeter == 185)
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
											pressMeter = 0;
										}
									}
								}
							}
						}
					}
					else//w przypadku wcisniecia guzika marked tylko zaznacza pole odpowiednim kolorem i obrazkiem
					{
						gameButtons[i][j].setBackground(Color.YELLOW);
						gameButtons[i][j].setIcon(flag);
					}
				}
			}
		}
	}
}