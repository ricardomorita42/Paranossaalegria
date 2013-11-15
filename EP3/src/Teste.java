
public class Teste {
	
	
	public static void main(String[] args) throws InterruptedException{

		Programa2 teste;
		
		/*
		teste = new Teste();
		MaquinaVirtual maq = new MaquinaVirtual(teste.getPrograma());
		while (!maq.execucaoFinalizada()) {
			maq.executaInstrucao();
		}*/
		
		
		Arena arenaPrincipal = new Arena();
		
		for (int i = 0; i < 1; i++) {
			teste = new Programa2();
			arenaPrincipal.insereExercito(teste.getPrograma(arenaPrincipal), new Base1());
		}
		
		for (int i = 0; i < 1; i++) {
			Programa teste2 = new Programa();
			arenaPrincipal.insereExercito(teste2.getPrograma(arenaPrincipal), new Base2());
		}
		
		Janela janela = new Janela("mapa", arenaPrincipal);
		while (!arenaPrincipal.listaRobosVazia())
		{
			Thread.sleep(50);
			
			if (janela.hasFinishedPainting())
			{
				arenaPrincipal.atualiza();
				//janela.repaint();
				janela.update(janela.getGraphics());
			}
		}
		
	}
}
