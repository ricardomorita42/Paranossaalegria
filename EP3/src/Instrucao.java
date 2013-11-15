
public class Instrucao {
	
	private String label = null;
	private String instrucao = null;
	private Empilhavel operando = null;
	
	public String getLabel() {
		return label;
	}
	
	public String getInstrucao() {
		return instrucao;
	}
	
	public Empilhavel getOperando() {
		return operando;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}
	
	public void setOperando(Object operando) {
		this.operando = (Empilhavel)operando;
	}

}
