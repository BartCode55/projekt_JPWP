import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements ActionListener{
	
	JLabel titleLabel;
	JLabel menuLabel;
	JPanel menuPanel;
	JPanel titlePanel;
	JPanel gamePanel;
	JButton[] menuButtons = new JButton[3];
	ImageIcon bomb = new ImageIcon("bomb.png");
	ImageIcon bomb2 = new ImageIcon("bomb.png");

	GameFrame(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,1024);
		this.setLayout(null);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0x8D91C7));
		createGamePanel();
		createMenuPanel();
		createTitlePanel();
		
		this.add(menuPanel); //, BorderLayout.EAST
		this.add(titlePanel); //, BorderLayout.NORTH
		this.add(gamePanel); //, BorderLayout.CENTER
		
		this.setVisible(true);
	}
	
	public void createGamePanel()
	{
		gamePanel = new JPanel();
		//gamePanel.setLayout(null);
		gamePanel.setBackground(new Color(0x313DE6));
		gamePanel.setBounds(0, 100, 1080, 924);
		//gamePanel.setPreferredSize(new Dimension(1080,200));
		
	}
	public void createMenuPanel()
	{
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0x353C9F));
		menuPanel.setLayout(null);
		//menuPanel.setPreferredSize(new Dimension(200,200));
		menuPanel.setBounds(1080, 100, 200, 924);
		
		//dodac labele odpowiedzialne za licznik bomb oraz za czasomierz
		
		menuLabel = new JLabel();//ROZWINAC JLABEL
		
		for (int i=0; i<3; i++)
		{
			menuButtons[i] = new JButton();
			menuPanel.add(menuButtons[i]);
			menuButtons[i].setFocusable(false);
		}
		
		menuButtons[0].setBounds(10, 375, 170, 100);
		menuButtons[0].setText("Reset");
		menuButtons[0].setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		menuButtons[1].setBounds(10, 500, 170, 100);
		menuButtons[1].setText("Trudniejszy poziom");
		menuButtons[1].setFont(new Font("Times New Roman", Font.PLAIN, 17));
		
		menuButtons[2].setBounds(10, 625, 170, 100);
		menuButtons[2].setText("Wyjscie z Gry");
		menuButtons[2].setFont(new Font("Times New Roman", Font.PLAIN, 20));
		
		
		menuLabel.setBounds(0, 250, 200, 100);
		//menuLabel.setBackground(Color.GREEN);
		menuLabel.setText("Menu:");
		menuLabel.setForeground(new Color(0x27272C));//
		menuLabel.setVerticalAlignment(JLabel.BOTTOM);
		menuLabel.setHorizontalAlignment(JLabel.CENTER);
		menuLabel.setFont(new Font("Times New Roman", Font.BOLD, 35));
		
		menuPanel.add(menuLabel);
		
		
		
	}
	public void createTitlePanel()
	{
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0x8A90DF));
		titlePanel.setBounds(0,0, 1280, 100);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
