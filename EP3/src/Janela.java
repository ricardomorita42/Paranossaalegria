import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

public class Janela extends JFrame implements ActionListener {
	/** Frame usado como base para o desenho da Janela; Este frame 
	 * incorpora dois componentes do tipo JPanel: Painel contendo
	 * o mapa (Painel.java) e o outro contendo as entidades como
	 * robôs e cristais (PainelEntidade.java);
	 */
	private static final long serialVersionUID = 1L;
	private int larguraTela = Painel.larguraTela;
	private int alturaTela = Painel.alturaTela;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	private boolean isPaused = false;
	private boolean gameStart = false;
	
	public Janela (String nome, Arena mapa) {
		super(nome);
		
		//Criando um menu
		createMenu(mapa);
		
		//Preparando o frame básico do jogo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(larguraTela, alturaTela);
		setVisible(true);
		
		//Aguardando o início do jogo
		while (!gameStart) {
			try {
				Thread.sleep(25);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//Ajusta a tela para conter exatamente o tamanho especificado em Painel.java
		Insets insets = getInsets();
		int insetWide = insets.left + insets.right;
		int insetTall = insets.top + insets.bottom;
		setSize(larguraTela + insetWide, alturaTela + insetTall);
		
		//Adicionando uma "base" para o desenho
		JScrollPane scrollpane = createWindow(mapa);
		
		add(scrollpane, BorderLayout.CENTER);
		pack();
		
		setVisible(true);
		
		// Enquanto houver robôs e instruções para serem executadas, 
		// atualiza a arena;
		while (!mapa.listaRobosVazia())
		{
			if (isPaused) {
				
			}
			else {
				try
				{
					Thread.sleep(500);
					mapa.atualiza();
				} catch (Exception e) {	}
			}
		}
	}
	
	//Função que efetivamente cria o menu
	private void createMenu(Arena mapa) {
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		menu.setMnemonic(KeyEvent.VK_G);
		menu.getAccessibleContext().setAccessibleDescription("Game Menu");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New");
		menuItem.setMnemonic(KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				gameStart = true;	//Começa o jogo
			}
			
		});
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Pause");
		menuItem.setMnemonic(KeyEvent.VK_P);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				isPaused = !isPaused;	//Pausa/despausa o jogo
			}
		});
		
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit");
		menuItem.setMnemonic(KeyEvent.VK_X);
		menuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);	//Sai do jogo
			}
			
		});
		menu.add(menuItem);
		
		menu = new JMenu("Base Azul");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("Robos do time azul");
		menuBar.add(menu);
		
		int x = mapa.nomeRobos().size();
		
		for (int i = 0; i < x; i++)  {
			if (mapa.timeRobos().get(i) instanceof Base1) {
				menuItem = new JMenuItem(mapa.nomeRobos().get(i));
				menuItem.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						isPaused = true;
						String s = (String)JOptionPane.showInputDialog("Escolha um novo programa para este robo");
						if (s != null) {
							System.out.println(s);
						}
						isPaused = false;
					}
					
				});
				menu.add(menuItem);
			}
		}
		
		
		menu = new JMenu("Base Vermelha");
		menu.setMnemonic(KeyEvent.VK_V);
		menu.getAccessibleContext().setAccessibleDescription("Robos do time vermelho");
		menuBar.add(menu);
		
		for (int i = 0; i < x; i++)  {
			if (mapa.timeRobos().get(i) instanceof Base2) {
				menuItem = new JMenuItem(mapa.nomeRobos().get(i));
				menuItem.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						isPaused = true;
						String s = (String)JOptionPane.showInputDialog("Escolha um novo programa para este robo");
						if (s != null) {
							System.out.println(s);
						}
						isPaused = false;
					}
					
				});
				menu.add(menuItem);
			}
		}
		
		
		setJMenuBar(menuBar);
	}
	
	//Chama Painel.java e PainelEntidades.java sobre um scrollpane, devolvendo este último
	private JScrollPane createWindow(Arena mapa) {
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
		
		return scrollPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}
}
