
public class Robo {
	
	private MaquinaVirtual maq;
	private String nome;
	private Base base;
	private int linha;
	private int coluna;
	private Cristal cristal;
	private boolean hasCristal = false;
	
	//ATRIBUTOS DO ROBO
	private int vida;
	private int dano;
	private int ocupacao = 0; //Implementar funcionamento
	private String acaoAtual = "";
	
	public void setPrograma(Instrucao[] programa) {
		this.maq = new MaquinaVirtual(programa);
	}

	public MaquinaVirtual getMaq() {
		return maq;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setBase(Base base) {
		this.base = base;
		this.linha = base.getLinha();
		this.coluna = base.getColuna();
	}

	public Base getBase() {
		return base;
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

	public Cristal getCristal() {
		return cristal;
	}

	public void setCristal(Cristal cristal) {
		this.cristal = cristal;
	}

	public boolean hasCristal() {
		return this.hasCristal;
	}

	public void setHasCristal(boolean hasCristal) {
		this.hasCristal = hasCristal;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public int getDano() {
		return dano;
	}

	public void setDano(int dano) {
		this.dano = dano;
	}

	public int getOcupacao() {
		return ocupacao;
	}

	public void setOcupacao(int ocupacao) {
		this.ocupacao = ocupacao;
	}

	public void setAcaoAtual(String acaoAtual) {
		this.acaoAtual = acaoAtual;
	}

	public String getAcaoAtual() {
		return acaoAtual;
	}
	
}
