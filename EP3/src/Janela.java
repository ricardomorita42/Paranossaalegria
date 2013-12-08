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
		
		//Ajusta a tela para conter exatamente o tamanho especificado em Painel.java
		Insets insets = getInsets();
		int insetWide = insets.left + insets.right;
		int insetTall = insets.top + insets.bottom;
		setSize(larguraTela + insetWide, alturaTela + insetTall);
		
		//Adicionando uma "base" para o desenho
		JPanel backgroundPanel = new JPanel();
		
		//Sobrepondo o itens colocados na base
		backgroundPanel.setLayout(new OverlayLayout(backgroundPanel));
		
		//Desenhando o mapa
		backgroundPanel.add(new PainelEntidades(mapa));
		
		//Desenhando as entidades
		backgroundPanel.add(new Painel(mapa));
		
		//Define aonde que o scroll aparece
		backgroundPanel.setPreferredSize(new Dimension (mapa.getMapWidth()*43,mapa.getMapHeight()*75)); 
		
		//JSCrollPane (barra de rolagem) na base
		JScrollPane scrollPane = new JScrollPane(backgroundPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension (mapa.getMapWidth()*45,mapa.getMapHeight()*78));
		add(scrollPane, BorderLayout.CENTER);
		pack();
		
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
