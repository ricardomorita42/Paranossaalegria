import java.util.Vector;

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
		
		int i = 0;

		programa[i] = new Instrucao();
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("mover","direita","reto");
		programa[i++].setOperando(op);
		
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("mover","direita","baixo");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i].setLabel("LOOP");
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("atacar","direita","reto");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("JIT");
		txt = new Texto("LOOP");
		programa[i++].setOperando(txt);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("mover","direita","reto");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("mover","direita","reto");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("coletar","direita","baixo");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("depositar","esquerda","baixo");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i++].setInstrucao("END");

		return programa;
	}
	
	public Instrucao[] getProgramaAttack(Arena mapa) {
		Instrucao[] programa = new Instrucao[512];
		
		Operacao op;
		Numero num;
		Texto txt;
		
		int i = 0;

		
		programa[i] = new Instrucao();
		programa[i].setLabel("LOOP");
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("atacar","esquerda","reto");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("JMP");
		txt = new Texto("LOOP");
		programa[i++].setOperando(txt);
		
		programa[i] = new Instrucao();
		programa[i++].setInstrucao("POP");
		
		programa[i] = new Instrucao();
		programa[i++].setInstrucao("END");

		return programa;
	}
	
	public Instrucao[] getProgramaAttack2(Arena mapa) {
		Instrucao[] programa = new Instrucao[512];
		
		Operacao op;
		Numero num;
		Texto txt;
		
		int i = 0;

		
		programa[i] = new Instrucao();
		programa[i].setLabel("LOOP");
		programa[i].setInstrucao("PUSH");
		programa[i++].setOperando(mapa);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("SYS");
		op = new Operacao("atacar","direita","reto");
		programa[i++].setOperando(op);
		
		programa[i] = new Instrucao();
		programa[i].setInstrucao("JMP");
		txt = new Texto("LOOP");
		programa[i++].setOperando(txt);
		
		programa[i] = new Instrucao();
		programa[i++].setInstrucao("POP");
		
		programa[i] = new Instrucao();
		programa[i++].setInstrucao("END");

		return programa;
	}
}