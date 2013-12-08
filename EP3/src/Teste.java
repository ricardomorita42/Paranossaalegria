import javax.swing.SwingUtilities;


public class Teste {
	
	/* Insere robôs nas bases, cada qual com um determinado programa de
	 * teste.
	 */
	public static void main(String args[]) {
		Programa2 teste;
			
		final Arena arenaPrincipal = new Arena();
		
		for (int i = 0; i < 1; i++) {
			teste = new Programa2();
			arenaPrincipal.insereExercito(teste.getPrograma(arenaPrincipal), new Base1());
		}
		
		for (int i = 0; i < 0; i++) {
			teste = new Programa2();
			arenaPrincipal.insereExercito(teste.getProgramaAttack(arenaPrincipal), new Base2());
		}
		
		Janela janela = new Janela("mapa", arenaPrincipal);
		
		// Enquanto houver robôs e instruções para serem executadas, 
		// atualiza a arena;
		while (!arenaPrincipal.listaRobosVazia())
		{
			try
			{
				Thread.sleep(500);
			}catch (Exception e)
			{
			}
					
			arenaPrincipal.atualiza();
		}
		
	}
}
