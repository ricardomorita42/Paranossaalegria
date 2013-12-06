import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Janela extends JFrame{
	/** Frame usado como base para o desenho da Janela; Este frame 
	 * incorpora dois componentes do tipo JPanel: Painel contendo
	 * o mapa (Painel.java) e o outro contendo as entidades como
	 * robôs e cristais (PainelEntidade.java);
	 */
	private static final long serialVersionUID = 1L;
	private int larguraTela = Painel.larguraTela;
	private int alturaTela = Painel.alturaTela;
	
	public Janela (String nome, Arena mapa) {
		super(nome);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(larguraTela, alturaTela);
		
		JPanel backgroundPanel = new JPanel();
		backgroundPanel.setLayout(new OverlayLayout(backgroundPanel));
		backgroundPanel.add(new PainelEntidades(mapa));
		backgroundPanel.add(new Painel(mapa));
		add(backgroundPanel,BorderLayout.CENTER);
		
		
		//setResizable(true);
		//setContentPane(new Painel(mapa));
		//add(new PainelEntidades(mapa));
			
		setBackground(Color.BLACK);
		setVisible(true);
		
	}
}
