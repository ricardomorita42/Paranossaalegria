import java.awt.*;
import javax.swing.*;

public class PainelEntidades extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Arena mapa;
	private Font defaultFont;
	public static Boolean checkPaint = false;
	
	public PainelEntidades (Arena mapa)
	{
		//super(nome);
		//setSize(larguraTela, alturaTela);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mapa = mapa;
		//Container ct = this.getContentPane();
		setPreferredSize(new Dimension(Painel.larguraTela, Painel.alturaTela));
		//setBounds(0,0,Painel.larguraTela, Painel.alturaTela);
		//setLocation(0, 0);
		setBackground(new Color(255,255,255,0));
		setVisible(true);
		//setOpaque(false);
		defaultFont = new Font("Monospace", Font.BOLD, 14);
		
		setDoubleBuffered(true);
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
		System.out.println("LALAALAAAAA");
		
		checkPaint = false;
		
		Graphics2D g2d = (Graphics2D) g;
		
		int w = mapa.getMapWidth();
		int h = mapa.getMapHeight();
		int x0 = 100, y = 100, r = 30;
		int deltaL = (int)(Math.sqrt(3)*r/2);
		int deltaH = (int)((3.0/2)*r);
		int erroY = -8;
		
		
		//Pintando o fundo, função antiga pois o bkground é pintado no construtor
		//g2d.setColor(Color.BLACK);
		//g2d.fillRect(0, 0, larguraTela, alturaTela);
		
		//Pintando as casas da arena...
		for (int i = 0; i < w; i++) {
			int x = x0;
						
			//casas impares sao deslocadas no eixo horizontal!
			if (i % 2 != 0)
				x += deltaL;
			
			//Pinta as casas e,dependendo do tipo, usa uma textura.
			//Veja a funcao loadImages() para mais informações.
			//Comentários são para carregar imagens puras em vez de textura!
			for (int j = 0; j < h; j++) {
				Terreno terrenoAtual = mapa.getTerreno(i, j);
				
				if (terrenoAtual.terrenoOcupado())
				{
					//Robo b = (Robo)terrenoAtual.getEntidade();
					//g2d.setColor(Color.cyan);
					
					int lado = (int)(r*Math.sqrt(3.0)/2);
					Object entidade = terrenoAtual.getEntidade();
					
					if (entidade instanceof Robo) {
						//g2d.fillRect(x-lado, y-r, 2*lado, 2*r);
						Image img;
						ImageIcon icon = null;
						if (((Robo) entidade).getBase() instanceof Base1)
							icon = new ImageIcon(this.getClass().getResource("img/robo1V.png"));
						else if (((Robo)entidade).getBase() instanceof Base2)
							icon = new ImageIcon(this.getClass().getResource("img/robo1A.png"));
						
						img = icon.getImage();
						//g2d.setColor(Color.PINK);
						g2d.drawImage(img, x-lado, y-r+erroY, 52, 60, null);
						g2d.setColor(Color.WHITE);
						
						g2d.setFont(defaultFont);
						g2d.drawString(String.valueOf(((Robo)entidade).getVida()), x-(int)(r/2), y+(int)(r/2)+erroY);

						g2d.setFont(new Font("Monospace", Font.BOLD, 9));
						g2d.drawString(String.valueOf(((Robo)entidade).getAcaoAtual()), x-(int)(r/2), y-(int)(r)+erroY);
					}
					
					else if (entidade instanceof Cristal){
						Image img;
						ImageIcon icon = new ImageIcon(this.getClass().getResource("img/cristal.png"));
						img = icon.getImage();
						//g2d.setColor(Color.PINK);
						g2d.drawImage(img, x-lado, y-r+erroY, 52, 60, null);
					}
				}

				x += (int)(Math.sqrt(3)*r);
			}
			y += deltaH;
		}
		
		checkPaint = true;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		doDrawing(g);
	}
}
