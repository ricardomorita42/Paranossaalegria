import java.util.Random;
import java.util.ArrayList;

public class Arena implements Empilhavel{
	private final int NUM_LINHAS = 10;
	private final int NUM_COLUNAS = 10;
	private Terreno[][] mapa;
	private ArrayList<Robo> listaRobos;
	private ArrayList<Cristal> listaCristais = new ArrayList<Cristal>();
	private ArrayList<Base1> listaBase1 = new ArrayList<Base1>();
	private ArrayList<Base2> listaBase2 = new ArrayList<Base2>();
	
	//Mapa prototipo de dimensoes 10 x 10
	private int[][] prototipo = {
								{0,0,0,2,2,2,2,2,2,2},
								{0,0,0,2,2,2,2,4,2,3},
								{0,0,0,2,3,2,2,2,4,2},
								{2,2,2,2,2,5,5,2,2,2},
								{2,2,2,3,2,2,5,2,2,2},
								{2,2,2,2,5,2,2,3,2,2},
								{2,2,2,2,5,5,2,2,2,2},
								{2,4,2,2,2,3,2,1,1,1},
								{2,2,4,2,2,2,2,1,1,1},
								{3,2,2,2,2,2,2,1,1,1}
								};
	/*	LEGENDA
		0 = Base1
		1 = Base2
		2 = Planicie
		3 = Rugoso
		4 = Montanha
		5 = Agua
		*/

	public Arena() {
		mapa = new Terreno[NUM_LINHAS][NUM_COLUNAS];
		
		//Mudou-se o construtor para definir os tipos de terreno segundo o vetor acima
		for (int i = 0; i < NUM_LINHAS; i++)
			for (int j = 0; j < NUM_COLUNAS; j++) {
				if (prototipo[i][j] == 0)
				{
					mapa[i][j] = new Base1();
					listaBase1.add((Base1)mapa[i][j]);
				}
				else if (prototipo[i][j] == 1)
				{
					mapa[i][j] = new Base2();
					listaBase2.add((Base2)mapa[i][j]);
				}
				else if (prototipo[i][j] == 2)
					mapa[i][j] = new Planicie();
				
				else if (prototipo[i][j] == 3) {
					mapa[i][j] = new Rugoso();
					insereCristal(i,j);
				}
				
				else if (prototipo[i][j] == 4)
					mapa[i][j] = new Montanha();
				
				else if (prototipo[i][j] == 5)
					mapa[i][j] = new Agua();
				
				mapa[i][j].setLinha(i);
				mapa[i][j].setColuna(j);
			}
		
		listaRobos = new ArrayList<Robo>();
	}
	
	public void insereCristal(int i, int j) {
		Cristal novoCristal = new Cristal();
		listaCristais.add(novoCristal);
	
		mapa[i][j].ocupaTerreno(novoCristal);
	}
	
	public void insereExercito(Instrucao[] programa, Base base) {
		Random rand = new Random();
		
		Terreno celula = null;
		int i, comp;
		
		if (base instanceof Base1) {
			comp = listaBase1.size();
			
			do {
				i = rand.nextInt(1/*comp*/);
				celula = listaBase1.get(i);
				//Enquanto a posição sorteada for um terreno Ocupado, Sorteie outra posição.
			} while (celula.terrenoOcupado());
		}
		else if (base instanceof Base2) {
			comp = listaBase2.size();
			
			do {
				i = rand.nextInt(comp);
				celula = listaBase2.get(i);
				//Enquanto a posição sorteada for um terreno Ocupado, Sorteie outra posição.
			} while (celula.terrenoOcupado());
		}

		
		Robo novoRobo = new Robo();
		Config config = new Config();
		novoRobo.setNome(config.nomesRobos()[rand.nextInt(config.nomesRobos().length)]);
		novoRobo.setPrograma(programa);
		novoRobo.setBase((Base)celula);
		
		/* a definir */
		
		listaRobos.add(novoRobo);
		celula.ocupaTerreno(novoRobo);
	}
	
	public void atualiza() {
		for (int i = 0; i < listaRobos.size(); i++) {
			if (!listaRobos.get(i).getMaq().execucaoFinalizada()) {
				System.out.println(listaRobos.get(i).getNome()+": ");
				listaRobos.get(i).getMaq().executaInstrucao(listaRobos.get(i));
			}
			else
			{
				listaRobos.remove(i);
			}
		}
		
	}
	
	public Boolean listaRobosVazia() {
		return listaRobos.isEmpty();
	}
	
	public int sistema(Operacao op, Robo robo) {
		//System.out.println(op.getAcao());
		
		//Posição de Origem do Robô
		int lo = robo.getLinha();
		int co = robo.getColuna();
		
		int isImpar = 0;
		
		//Posição de Destino da Ação
		int l, c;
		
		if (lo % 2 == 1)
			isImpar = 1;
		
		if (op.getOrientacao() == "reto")
		{
			l = lo;
			
			if (op.getDirecao() == "esquerda")
				c = co - 1;
			else
				c = co + 1;
		}
		else if (op.getOrientacao() == "cima")
		{
			l = lo - 1;
			
			if (op.getDirecao() == "esquerda")
				c = co -1 + isImpar;
			else //direita
				c = co + isImpar;
		}
		else //baixo
		{
			l = lo + 1;
			
			if (op.getDirecao() == "esquerda")
				c = co - 1 + isImpar;
			else //direita
				c = co + isImpar;
		}
		
		if ((c >= 0 && c < NUM_COLUNAS) && (l >= 0 && l < NUM_LINHAS))
		{
			if (op.getAcao() == "mover")
			{
				if (mapa[l][c].terrenoOcupado())
					return 0;
				
				mapa[lo][co].desocupaTerreno();
				mapa[l][c].ocupaTerreno(robo);
				robo.setLinha(l);
				robo.setColuna(c);
			}
			else if (op.getAcao() == "coletar")
			{
				if ((mapa[l][c].terrenoOcupado()) && (mapa[l][c].getEntidade() instanceof Cristal) && !robo.hasCristal())
				{
					robo.setCristal((Cristal)mapa[l][c].getEntidade());
					robo.setHasCristal(true); 
					mapa[l][c].desocupaTerreno();
				}
			}
			else if (op.getAcao() == "depositar")
			{
				if ((!mapa[l][c].terrenoOcupado()) && robo.hasCristal()) //VERIFICAR CONDICOES DE TERRENO
				{
					mapa[l][c].ocupaTerreno(robo.getCristal());
					robo.setHasCristal(false);
					robo.setCristal(null);
				}
				
			}
			else if (op.getAcao() == "atacar")
			{
				
			}
		}
		else
			return 0;
		
		/* [Mover, direção]
		 * [Coletar, direção]
		 * [Depositar, direção]
		 * [Atacar, direção]
		 * à definir*/
		return 1;
	}
	
	//Funções criadas abaixo na classe Janela
	public int getMapHeight() {
		return NUM_COLUNAS;
	}
	
	public int getMapWidth() {
		return NUM_LINHAS;
	}
	
	public Terreno getTerreno(int x, int y) {
		return mapa[x][y];
	}
}