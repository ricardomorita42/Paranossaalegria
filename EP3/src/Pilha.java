import java.util.*;

public class Pilha {
	Stack<Empilhavel> pilha;
	
	public Pilha() {
		this.pilha = new Stack<Empilhavel>();
	}
	
	public void Empilha(Empilhavel obj) {
		this.pilha.push(obj);
	}
	public Empilhavel Desempilha() {
		return this.pilha.pop();
	}
	public void Dup() {
		Empilhavel top = this.pilha.peek();
		
		if (top instanceof Numero)
		{
			Numero obj = new Numero();
			
			obj.setNum(((Numero)top).getNum());
			
			this.pilha.push(obj);
		}
	}
	public void Descarta() {
		this.pilha.pop();
	}
	public void Inverte() {
		Empilhavel aux1 = this.pilha.pop();
		Empilhavel aux2 = this.pilha.pop();
		this.pilha.push(aux1);
		this.pilha.push(aux2);
	}
	public Empilhavel Consulta() {
		
		try {
			return this.pilha.peek();
		} catch (Exception e) {
			return null;
		}
		
	}
}