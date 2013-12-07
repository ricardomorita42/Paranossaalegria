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
		
		//Adicionando uma "base" para o desenho
		JPanel backgroundPanel = new JPanel();
		
		backgroundPanel.setLayout(new OverlayLayout(backgroundPanel)); //Sobrepondo o itens colocados na base
		backgroundPanel.add(new PainelEntidades(mapa)); 		//Desenhando o mapa
		backgroundPanel.add(new Painel(mapa));					//Desenhando as entidades
		backgroundPanel.setPreferredSize(new Dimension (mapa.getMapWidth()*43,mapa.getMapHeight()*75)); //Define aonde que o scroll aparece
		//add(backgroundPanel,BorderLayout.CENTER);
		
		
		//JSCrollPane (barra de rolagem) na base
		JScrollPane scrollPane = new JScrollPane(backgroundPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension (mapa.getMapWidth()*45,mapa.getMapHeight()*78)); //Desenha um pouco mais
		add(scrollPane, BorderLayout.CENTER);
		pack();
		
		//setResizable(true);
		//setContentPane(new Painel(mapa));
		//add(new PainelEntidades(mapa));
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
