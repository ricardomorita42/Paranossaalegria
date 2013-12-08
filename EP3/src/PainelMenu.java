import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PainelMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Font defaultFont;
	Timer timer;
	JButton newGame; 
	JButton exitGame;
	
	public PainelMenu ()
	{
		//setPreferredSize(new Dimension(Painel.larguraTela, Painel.alturaTela));
		setBackground(new Color(255,255,255,0));
		defaultFont = new Font("Monospace", Font.BOLD, 40);
		setVisible(true);
		//setOpaque(false);
	}
	
	private void doDrawing (Graphics g)
	{
		
		this.setLayout(getLayout());
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(defaultFont);
		g2d.drawString("Batalha de Robos", 300, 100);
		
		newGame = new JButton("New Game");
		newGame.setLocation(300, 150);
		newGame.setSize(new Dimension(100,50));
		this.add(newGame);

		JButton exitGame = new JButton("Exit");
		exitGame.setLocation(300,200);
		exitGame.setSize(new Dimension(100,50));
		this.add(exitGame);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public void actionPerformed(ActionEvent e) {
		setVisible(false);
	}
}
