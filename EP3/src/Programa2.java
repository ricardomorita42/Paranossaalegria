public class Programa2 {
	
	//Ações
	final String MOVER = "mover";
	final String COLETAR = "coletar";
	final String DEPOSITAR = "depositar";
	final String ATACAR = "atacar";
	
	//Direçoes
	final String DIREITA = "direita";
	final String ESQUERDA = "esquerda";
	
	//Orientações
	final String CIMA = "cima";
	final String BAIXO = "baixo";
	final String RETO = "reto";
	
	public Instrucao[] getPrograma(Arena mapa) {
		Instrucao[] programa = new Instrucao[512];
		
		Operacao op;
		Numero num;
		Texto txt;

		programa[0] = new Instrucao();
		programa[0].setInstrucao("PUSH");
		programa[0].setOperando(mapa);
		
		programa[1] = new Instrucao();
		programa[1].setInstrucao("SYS");
		op = new Operacao("mover","direita","reto");
		programa[1].setOperando(op);
		
		programa[2] = new Instrucao();
		programa[2].setInstrucao("PUSH");
		programa[2].setOperando(mapa);
		
		programa[3] = new Instrucao();
		programa[3].setInstrucao("SYS");
		op = new Operacao("mover","direita","baixo");
		programa[3].setOperando(op);
		
		programa[4] = new Instrucao();
		programa[4].setInstrucao("PUSH");
		programa[4].setOperando(mapa);
		
		programa[5] = new Instrucao();
		programa[5].setInstrucao("SYS");
		op = new Operacao("mover","direita","reto");
		programa[5].setOperando(op);
		
		programa[6] = new Instrucao();
		programa[6].setInstrucao("PUSH");
		programa[6].setOperando(mapa);
		
		programa[7] = new Instrucao();
		programa[7].setInstrucao("SYS");
		op = new Operacao("mover","direita","reto");
		programa[7].setOperando(op);
		
		programa[8] = new Instrucao();
		programa[8].setInstrucao("PUSH");
		programa[8].setOperando(mapa);
		
		programa[9] = new Instrucao();
		programa[9].setInstrucao("SYS");
		op = new Operacao("coletar","direita","baixo");
		programa[9].setOperando(op);
		
		programa[10] = new Instrucao();
		programa[10].setInstrucao("PUSH");
		programa[10].setOperando(mapa);
		
		programa[11] = new Instrucao();
		programa[11].setInstrucao("SYS");
		op = new Operacao("depositar","esquerda","baixo");
		programa[11].setOperando(op);
		
		programa[12] = new Instrucao();
		programa[12].setInstrucao("END");

		return programa;
	}
}