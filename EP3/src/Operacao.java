
public class Operacao implements Empilhavel {
	private String acao;
	private String direcao;
	private String orientacao;
	
	public Operacao(String acao, String direcao, String orientacao)
	{
		this.acao = acao;
		this.direcao = direcao;
		this.orientacao = orientacao;
	}

	public String getAcao() {
		return acao;
	}

	public String getDirecao() {
		return direcao;
	}

	public String getOrientacao() {
		return orientacao;
	}
}
