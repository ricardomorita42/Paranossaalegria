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
								{0,0,0,2,1,2,2,4,2,3},
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
		
		// O construtor Arena() é usado para definir os tipos de terreno
		// seguindo o array acima;
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
	
	//Insere um cristal na posição (i,j)
	public void insereCristal(int i, int j) {
		Cristal novoCristal = new Cristal();
		listaCristais.add(novoCristal);
	
		mapa[i][j].ocupaTerreno(novoCristal);
	}
	
	/* Insere um exército com o (programa desejado) no time esco-
	 * lhido. O padrão é de escolher aleatoriamente dentre a base,
	 * entretanto, no momento estamos colocando o robô especifica-
	 * damente em determinadas posições para facilitar o entendi-
	 * mento do programa. */
	public void insereExercito(Instrucao[] programa, Base base) {
		Random rand = new Random();
		
		Robo novoRobo = new Robo();
		Config config = new Config();
		
		Terreno celula = null;
		int i, comp;
		
		if (base instanceof Base1) {
			comp = listaBase1.size();
			
			do {
				i = rand.nextInt(1/*comp*/); //Aleatório caso descomente comp
				celula = listaBase1.get(i);
				// Enquanto a posição sorteada for um terreno Ocupado,
				// Sorteie outra posição.
			} while (celula.terrenoOcupado());
			
			novoRobo.setVida(300);
			novoRobo.setDano(25);
		}
		
		else if (base instanceof Base2) {
			comp = listaBase2.size();
			
			do {
				i = rand.nextInt(1/*comp*/); //Aleatório caso descomente comp
				celula = listaBase2.get(i);
				// Enquanto a posição sorteada for um terreno Ocupado,
				// Sorteie outra posição.
			} while (celula.terrenoOcupado());
			
			novoRobo.setVida(100);
			novoRobo.setDano(75);
		}
		
		novoRobo.setNome(config.nomesRobos()[rand.nextInt(config.nomesRobos().length)]);
		novoRobo.setPrograma(programa);
		novoRobo.setBase((Base)celula);
		
		/* à definir os tipos de robôs */
		
		listaRobos.add(novoRobo);
		celula.ocupaTerreno(novoRobo);
	}
	
	/* Cada vez que atualiza() é chamada, um ciclo de cada robô
	 * é executado;
	 */
	public void atualiza() {
		for (int i = 0; i < listaRobos.size(); i++) {
			if (!listaRobos.get(i).getMaq().execucaoFinalizada()) {
				System.out.println(listaRobos.get(i).getNome()+": ");
				listaRobos.get(i).getMaq().executaInstrucao(listaRobos.get(i));
			}
			else
			{
				// Um exemplo de mudança de programa em determinado robô que terminou todas
				// suas instruções;
				listaRobos.get(i).setPrograma((new Programa2()).getProgramaAttack2(this));
				//listaRobos.remove(i);
			}
		}
		
	}
	
	//Retorna se há robôs na lista;
	public Boolean listaRobosVazia() {
		return listaRobos.isEmpty();
	}
	
	/* Usada quando o robô executa uma chamada ao sistema.
	 * Chamadas implementadas:
	 * [Mover, direção]
	 * [Coletar, direção]
	 * [Depositar, direção]
	 * [Atacar, direção]
	 */
	public int sistema(Operacao op, Robo robo) {
		int isImpar = 0;
		
		// Posição de origem do robô
		int lo = robo.getLinha();
		int co = robo.getColuna();
		
		// Posição de destino da ação executada pelo robô
		int l, c;
		
		/* O trecho abaixo é usado para auxiliar no mapeamento
		 * da direção dada numa matriz quadrada para uma direção
		 * numa matriz hexagonal;
		 */ 
				
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
			else // "direita"
				c = co + isImpar;
		}
		else // "baixo"
		{
			l = lo + 1;
			
			if (op.getDirecao() == "esquerda")
				c = co - 1 + isImpar;
			else // "direita"
				c = co + isImpar;
		}
		
		/* Define as ações caso sejam válidas; 
		 * Tipos de retorno:
		 * 0 = Ação mal-sucedida (ex. tiro numa casa vazia)
		 * 1 = Ação bem-sucedida
		 * 2 = Nível de ocupação atualizado (esperando ocupação = 0 para
		 * 	   execução da ação)
		 */
		if ((c >= 0 && c < NUM_COLUNAS) && (l >= 0 && l < NUM_LINHAS))
		{
			if (op.getAcao() == "mover")
			{
				if (robo.getOcupacao() == 0)
				{
					robo.setOcupacao(mapa[l][c].getOcupacao());
					return 2;
				}
				else
				{
					robo.setOcupacao(robo.getOcupacao()-1);
					
					if (robo.getOcupacao() == 0) {
						if (mapa[l][c].terrenoOcupado())
							return 0;
						
						mapa[lo][co].desocupaTerreno();
						mapa[l][c].ocupaTerreno(robo);
						robo.setLinha(l);
						robo.setColuna(c);
					}
					else
						return 2;
				}
			}
			else if (op.getAcao() == "coletar")
			{
				if ((mapa[l][c].terrenoOcupado()) && (mapa[l][c].getEntidade() instanceof Cristal) && !robo.hasCristal())
				{		
					if (robo.getOcupacao() == 0)
					{
						robo.setOcupacao(5);
						return 2;
					}
					
					else
					{
						robo.setOcupacao(robo.getOcupacao()-1);
						
						if (robo.getOcupacao() == 0)
						{
							robo.setCristal((Cristal)mapa[l][c].getEntidade());
							robo.setHasCristal(true); 
							mapa[l][c].desocupaTerreno();
						}
						
						else
							return 2;
					}
				}
				else
					return 0;
			}
			
			else if (op.getAcao() == "depositar")
			{
				if ((!mapa[l][c].terrenoOcupado()) && robo.hasCristal()) //VERIFICAR CONDICOES DE TERRENO
				{
					if (robo.getOcupacao() == 0)
						{
							robo.setOcupacao(3);
							return 2;
						}
						
						else
						{
							robo.setOcupacao(robo.getOcupacao()-1);
							
							if (robo.getOcupacao() == 0)
							{
								mapa[l][c].ocupaTerreno(robo.getCristal());
								robo.setHasCristal(false);
								robo.setCristal(null);
							}
							
							else
								return 2;
						}
					
				}
				else
					return 0;
				
				
				
			}
			else if (op.getAcao() == "atacar")
			{
				if ((mapa[l][c].terrenoOcupado()) && (mapa[l][c].getEntidade() instanceof Robo)) {
					int dano = robo.getDano();
					Robo alvo = ((Robo)mapa[l][c].getEntidade());
					
					alvo.setVida(alvo.getVida() - dano);
					
					if (alvo.getVida() <= 0) {
						mapa[l][c].desocupaTerreno();
						
						if (alvo.hasCristal())
							mapa[l][c].ocupaTerreno(alvo.getCristal());
						
						listaRobos.remove(alvo);
					}

				}
				
				else
					return 0;
			}
		}
		else
			return 0;
		

		return 1;
	}
	
	// Funções abaixo foram criadas para utilização nas
	// classes de desenho;
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