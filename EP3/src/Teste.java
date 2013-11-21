
public class Teste {
	
	/* Insere robôs nas bases, cada qual com um determinado programa de
	 * teste.
	 */
	public static void main(String[] args) throws InterruptedException{
		Programa2 teste;
			
		Arena arenaPrincipal = new Arena();
		
		
		for (int i = 0; i < 1; i++) {
			teste = new Programa2();
			arenaPrincipal.insereExercito(teste.getPrograma(arenaPrincipal), new Base1());
		}
		
		for (int i = 0; i < 1; i++) {
			teste = new Programa2();
			arenaPrincipal.insereExercito(teste.getProgramaAttack(arenaPrincipal), new Base2());
		}
		
		Janela janela = new Janela("mapa", arenaPrincipal);
		
		// Enquanto houver robôs e instruções para serem executadas, 
		// atualiza a arena;
		while (!arenaPrincipal.listaRobosVazia())
		{
			Thread.sleep(500);		
			arenaPrincipal.atualiza();
			janela.repaint();
		}
		
	}
}
