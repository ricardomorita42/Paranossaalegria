import javax.swing.SwingUtilities;

public class Teste {
	
	/* Insere robôs nas bases, cada qual com um determinado programa de
	 * teste.
	 */
	public static void main(String args[]) {
		Programa2 teste;
			
		final Arena arenaPrincipal = new Arena();
		
		for (int i = 0; i < 4; i++) {
			teste = new Programa2();
			arenaPrincipal.insereExercito(teste.getPrograma(arenaPrincipal), new Base1());
		}
		
		for (int i = 0; i < 4; i++) {
			teste = new Programa2();
			arenaPrincipal.insereExercito(teste.getProgramaAttack(arenaPrincipal), new Base2());
		}
		
		new Janela("R0bot Wars", arenaPrincipal);
		
	}
}
