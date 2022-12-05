import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameFrame extends JFrame implements MouseListener{
	
	JLabel titleLabel;
	JLabel menuLabel;
	JPanel menuPanel;
	JPanel titlePanel;
	JPanel gamePanel;
	ImageIcon bomb = new ImageIcon("bomb.png");
	ImageIcon bomb2 = new ImageIcon("bomb.png");

	GameFrame(){
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(1280,1024);//potem zmienic na 1024 lub tak zostawic zobaczymy
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(0x8D91C7));
		createGamePanel();
		createMenuPanel();
		createTitlePanel();
		
		this.add(menuPanel, BorderLayout.EAST);
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(gamePanel, BorderLayout.CENTER);
		//this.add(titlePanel);
		//this.add(gamePanel);
		
		this.setVisible(true);
		
		
	}
	
	public void createGamePanel()
	{
		gamePanel = new JPanel();
		gamePanel.setBackground(new Color(0x313DE6));
		//gamePanel.setPreferredSize(new Dimension(1080,200));
		
	}
	public void createMenuPanel()
	{
		menuPanel = new JPanel();
		menuPanel.setBackground(new Color(0x353C9F));
		menuPanel.setPreferredSize(new Dimension(200,200));
		
		menuLabel = new JLabel();//ROZWINAC JLABEL
	}
	public void createTitlePanel()
	{
		titlePanel = new JPanel();
		titlePanel.setBackground(new Color(0x8A90DF));
		titlePanel.setPreferredSize(new Dimension(200,100));
		
		titleLabel = new JLabel();
		titleLabel.setText("Bombowa Lamiglowka");
		titleLabel.setHorizontalTextPosition(JLabel.LEFT);
		//titleLabel.setVerticalAlignment(JLabel.TOP);
		titleLabel.setFont(new Font("MV Boli", Font.PLAIN, 60));
		titleLabel.setIcon(bomb);
		
		
		
		titlePanel.add(titleLabel);
		
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Hello");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
