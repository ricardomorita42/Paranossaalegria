public class Terreno {
	private Boolean terrenoOcupado = false;
	private Object entidade = null; //Pode ser um cristal ou um robo
	private int linha;
	private int coluna;

	//Devolve se um terreno tem alguma entidade;
	public Boolean terrenoOcupado() {
		return this.terrenoOcupado;
	}
	
	//Coloca uma entidade no terreno
	public void ocupaTerreno(Object obj) {
		this.entidade = obj;
		this.terrenoOcupado = true;
	}
	
	//Tira uma entidade de um terreno
	public void desocupaTerreno() {
		this.terrenoOcupado = false;
		this.entidade = null;
	}
	
	//Retorna qual a entidade que está no terreno
	public Object getEntidade() {
		return entidade;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getLinha() {
		return linha;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public int getColuna() {
		return coluna;
	}
	
	//Deve ser no minimo 1 segundo implementacao em Arena.java
	public int getOcupacao() {
		return 1;
	}

}