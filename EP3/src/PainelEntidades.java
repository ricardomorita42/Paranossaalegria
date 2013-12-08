import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class PainelEntidades extends JPanel implements ActionListener  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Arena mapa;
	private Font defaultFont;
	Timer timer;
	
	public PainelEntidades (Arena mapa)
	{
		this.mapa = mapa;
		//setPreferredSize(new Dimension(Painel.larguraTela, Painel.alturaTela));
		setBackground(new Color(255,255,255,0));
		setVisible(true);
		setOpaque(false);
		defaultFont = new Font("Monospace", Font.BOLD, 14);
		setDoubleBuffered(true);
		timer = new Timer(25,this);
		timer.start();
	}
	
	/*	LEGENDA
	gray = Base
	beige = Planicie
	brown = Rugoso
	preto = Montanha
	cyan = Agua
	purple = Cristal */
	
	private void doDrawing (Graphics g)
	{
		setDoubleBuffered(true);
				
		Graphics2D g2d = (Graphics2D) g;
		
		int w = mapa.getMapWidth();
		int h = mapa.getMapHeight();
		int x0 = 100, y = 100, r = 30;
		int deltaL = (int)(Math.sqrt(3)*r/2);
		int deltaH = (int)((3.0/2)*r);
		int erroY = -8;
		int lado = (int)(r*Math.sqrt(3.0)/2);
		
		// Pintando as casas da arena...
		for (int i = 0; i < w; i++) {
			int x = x0;
						
			// casas impares sao deslocadas no eixo horizontal!
			if (i % 2 != 0)
				x += deltaL;
			
			// Desenha as entidades sobre o Painel do mapa
			for (int j = 0; j < h; j++) {
				Terreno terrenoAtual = mapa.getTerreno(i, j);
				
				if (terrenoAtual.terrenoOcupado())
				{
					Object entidade = terrenoAtual.getEntidade();
					
					if (entidade instanceof Robo) {
						Image img;
						ImageIcon icon = null;
						if (((Robo) entidade).getBase() instanceof Base1)
							icon = new ImageIcon(this.getClass().getResource("img/robo1V.png"));
						else if (((Robo)entidade).getBase() instanceof Base2)
							icon = new ImageIcon(this.getClass().getResource("img/robo1A.png"));
						
						img = icon.getImage();
						
						g2d.drawImage(img, x-lado, y-r+erroY, 52, 60, null);
						Toolkit.getDefaultToolkit().sync(); //visto em http://zetcode.com/tutorials/javagamestutorial/animation/
						g2d.setColor(Color.WHITE);
						
						g2d.setFont(defaultFont);
						g2d.drawString(String.valueOf(((Robo)entidade).getVida()), x-(int)(r/2), y+(int)(r/2)+erroY);
						Toolkit.getDefaultToolkit().sync();
						
						g2d.setFont(new Font("Monospace", Font.BOLD, 9));
						g2d.drawString(String.valueOf(((Robo)entidade).getAcaoAtual()), x-(int)(r/2), y-(int)(r)+erroY);
						Toolkit.getDefaultToolkit().sync();
					}
					
					else if (entidade instanceof Cristal){
						Image img;
						ImageIcon icon = new ImageIcon(this.getClass().getResource("img/cristal.png"));
						img = icon.getImage();
						g2d.drawImage(img, x-lado-2, y-r+erroY+4, 52, 60, null);
						Toolkit.getDefaultToolkit().sync();
					}
				}
				
				if (terrenoAtual instanceof Montanha) {
					Image img;
					ImageIcon icon = new ImageIcon(this.getClass().getResource("img/mountain.png"));
					img = icon.getImage();
					g2d.drawImage(img, x-lado-12, y-r*2+10, 78, 78, null);
					Toolkit.getDefaultToolkit().sync();
				}

				x += (int)(Math.sqrt(3)*r);
			}
			y += deltaH;
		}
		
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
	
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
