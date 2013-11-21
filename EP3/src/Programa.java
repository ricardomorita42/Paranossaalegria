
//Calcula Fibonacci
public class Programa {
	public Instrucao[] getPrograma(Arena mapa) {
		Instrucao[] programa = new Instrucao[512];

		Numero num;
		Texto txt;

		programa[0] = new Instrucao();
		programa[0].setInstrucao("PUSH");
		num = new Numero(1);
		programa[0].setOperando(num);

		programa[1] = new Instrucao();
		programa[1].setInstrucao("PUSH");
		num = new Numero(1);
		programa[1].setOperando(num);

		programa[2] = new Instrucao();
		programa[2].setInstrucao("STO");
		num = new Numero(0);
		programa[2].setOperando(num);

		programa[3] = new Instrucao();
		programa[3].setInstrucao("STO");
		num = new Numero(1);
		programa[3].setOperando(num);

		programa[4] = new Instrucao();
		programa[4].setInstrucao("PUSH");
		num = new Numero(10);
		programa[4].setOperando(num);

		programa[5] = new Instrucao();
		programa[5].setInstrucao("STO");
		num = new Numero(2);
		programa[5].setOperando(num);

		programa[6] = new Instrucao();
		programa[6].setLabel("LOOP");
		programa[6].setInstrucao("RCL");
		num = new Numero(0);
		programa[6].setOperando(num);

		programa[7] = new Instrucao();
		programa[7].setInstrucao("RCL");
		num = new Numero(1);
		programa[7].setOperando(num);

		programa[8] = new Instrucao();
		programa[8].setInstrucao("DUP");

		programa[9] = new Instrucao();
		programa[9].setInstrucao("STO");
		num = new Numero(0);
		programa[9].setOperando(num);

		programa[10] = new Instrucao();
		programa[10].setInstrucao("ADD");

		programa[11] = new Instrucao();
		programa[11].setInstrucao("DUP");

		programa[12] = new Instrucao();
		programa[12].setInstrucao("STO");
		num = new Numero(1);
		programa[12].setOperando(num);

		programa[13] = new Instrucao();
		programa[13].setInstrucao("PRN");

		programa[14] = new Instrucao();
		programa[14].setInstrucao("RCL");
		num = new Numero(2);
		programa[14].setOperando(num);

		programa[15] = new Instrucao();
		programa[15].setInstrucao("PUSH");
		num = new Numero(1);
		programa[15].setOperando(num);

		programa[16] = new Instrucao();
		programa[16].setInstrucao("SUB");

		programa[17] = new Instrucao();
		programa[17].setInstrucao("DUP");

		programa[18] = new Instrucao();
		programa[18].setInstrucao("STO");
		num = new Numero(2);
		programa[18].setOperando(num);

		programa[19] = new Instrucao();
		programa[19].setInstrucao("PUSH");
		num = new Numero(0);
		programa[19].setOperando(num);

		programa[20] = new Instrucao();
		programa[20].setInstrucao("EQ");

		programa[21] = new Instrucao();
		programa[21].setInstrucao("JIF");
		txt = new Texto("LOOP");
		programa[21].setOperando(txt);

		programa[22] = new Instrucao();
		programa[22].setInstrucao("END");

		return programa;
	}
}
