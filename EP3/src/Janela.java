
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;

public class Janela extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Arena mapa;
	private int larguraTela = 1024;
	private int alturaTela = 800;
	private Font defaultFont;
	private TexturePaint txtbase;
	private TexturePaint txtbase2;
	private TexturePaint txtplanicie;
	private TexturePaint txtrugoso;
	private TexturePaint txtmontanha;
	private TexturePaint txtagua;
	private Boolean checkPaint = false;
	
	public Janela(String nome, Arena mapa)
	{
		super(nome);
		setSize(larguraTela, alturaTela);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mapa = mapa;
		this.loadImages();
		//Container ct = this.getContentPane();
		
		setBackground(Color.BLACK);
		setVisible(true);
		
		defaultFont = new Font("Monospace", Font.BOLD, 14);
	}
	
	/*	LEGENDA
	gray = Base
	beige = Planicie
	brown = Rugoso
	preto = Montanha
	cyan = Agua
	purple = Cristal */
	
	public void paint(Graphics g)
	{
		System.out.println("LALAALAAAAA");
		checkPaint = false;
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(defaultFont);
		
		int w = mapa.getMapWidth();
		int h = mapa.getMapHeight();
		int x0 = 100, y = 100, r = 30;
		int deltaL = (int)(Math.sqrt(3)*r/2);
		int deltaH = (int)((3.0/2)*r);
		
		
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
				
				if (terrenoAtual instanceof Base1) {
					g2d.setPaint(txtbase);
				}
				else if (terrenoAtual instanceof Base2) {
					g2d.setPaint(txtbase2);
				}
				else if (terrenoAtual instanceof Planicie) {
					g2d.setPaint(txtplanicie);
				}
				else if (terrenoAtual instanceof Rugoso) {
					g2d.setPaint(txtrugoso);
				}
				else if (terrenoAtual instanceof Montanha) {
					g2d.setPaint(txtmontanha);
				}
				else if (terrenoAtual instanceof Agua ) {
					g2d.setPaint(txtagua);
				}
				
				g2d.fillPolygon(this.getHexagon(x, y, r));
				g2d.setColor(Color.BLACK);
				Stroke borda = new BasicStroke(2.0F);
				g2d.setStroke(borda);
				g2d.drawPolygon(this.getHexagon(x, y, r));
				
				
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
						g2d.drawImage(img, x-lado, y-r, 52, 60, null);
						g2d.setColor(Color.WHITE);
						
						g2d.drawString(String.valueOf(((Robo)entidade).getVida()), x-(int)(r/2), y+(int)(r/2));
					}
					
					else if (entidade instanceof Cristal){
						Image img;
						ImageIcon icon = new ImageIcon(this.getClass().getResource("img/cristal.png"));
						img = icon.getImage();
						//g2d.setColor(Color.PINK);
						g2d.drawImage(img, x-lado, y-r, 52, 60, null);
					}
				}

				x += (int)(Math.sqrt(3)*r);
			}
			y += deltaH;
		}
		
		checkPaint = true;
	}
	
	//Recebe as coordenadas do centro e um raio para gerar um hexágono
	public Polygon getHexagon(int xc,int yc, int raio)
	{	
		int r = raio;
		int x0 = xc;
		int y0 = yc;
		double x,y;
		
		Polygon p = new Polygon();
		
		double teta = Math.PI/2;
	
		
		for (int i = 0; i < 6; i++)
		{
			
			x = x0 + r * Math.cos(teta);
			y = y0 + r * Math.sin(teta);
			
			p.addPoint((int)x, (int)y);
			teta += Math.PI/3;
		}
		
		return p;
	}
	
	//Carrega as texturas para serem usadas quando os hexágonos forem desenhados
	public void loadImages()
	{
		BufferedImage img1 = null;
		BufferedImage img2 = null;
		BufferedImage img3 = null;
		BufferedImage img4 = null;
		BufferedImage img5 = null;
		BufferedImage img6 = null;
		
		try {
			img1 = ImageIO.read(this.getClass().getResourceAsStream("img/baseVerm.jpg"));
			img2 = ImageIO.read(this.getClass().getResourceAsStream("img/baseAzul.jpg"));
			img3 = ImageIO.read(this.getClass().getResourceAsStream("img/planicie2.jpg"));
			img4 = ImageIO.read(this.getClass().getResourceAsStream("img/rugoso3.jpg"));
			img5 = ImageIO.read(this.getClass().getResourceAsStream("img/montanha.jpg"));
			img6 = ImageIO.read(this.getClass().getResourceAsStream("img/agua.jpg"));	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		txtbase = new TexturePaint(img1, new Rectangle(0, 0, 130, 150));
		txtbase2 = new TexturePaint(img2, new Rectangle(0, 0, 130, 150));
		txtplanicie = new TexturePaint(img3, new Rectangle(0, 0, 130, 150));
		txtrugoso = new TexturePaint(img4, new Rectangle(0, 0, 130, 150));
		txtmontanha = new TexturePaint(img5, new Rectangle(0, 0, 130, 150));
		txtagua = new TexturePaint(img6, new Rectangle(0, 0, 130, 150));
		
	}
	
	public Boolean hasFinishedPainting()
	{
		return this.checkPaint;
	}
	
	/*public void clearForNewPainting()
	{
		this.checkPaint = false;
	}*/
}
