import java.awt.*;

import javax.swing.*;

public class Janela extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private int larguraTela = Painel.larguraTela;
	private int alturaTela = Painel.alturaTela;
	private PainelEntidades entidades;

	public Janela (String nome, Arena mapa) {
		
		super(nome);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(larguraTela, alturaTela);
		//setLayout(null);
		//setLocationByPlatform(true);
		setResizable(false);
		Painel p1 = new Painel(mapa);
		//p1.setLayout(new OverlayLayout(p1));
		setContentPane(p1);
		//add(p1);
		add(new PainelEntidades(mapa));
		
		//this.mapa = mapa;
		//this.loadImages();
		//Container ct = this.getContentPane();
		
		setBackground(Color.BLACK);
		setVisible(true);
		
		//defaultFont = new Font("Monospace", Font.BOLD, 14);
	}
	
	public void drawEntidades()
	{
		this.entidades.repaint();
	}
	
	public Boolean hasFinishedPainting()
	{
		return Painel.checkPaint;
	}
}
