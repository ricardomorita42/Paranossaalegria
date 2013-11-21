 import java.util.*;

public class MaquinaVirtual {
    private Hashtable<String, Integer> labels;
    private Instrucao[] programa;
    private int ponteiroInstrucoes;
    private Pilha pilhaDados;
    private Object[] memoria;
    private Boolean execucaoFinalizada = false;
    
    public MaquinaVirtual(Instrucao[] programa) {
        
        labels = new Hashtable<String, Integer>();
        this.programa = programa;
        memoria = new Object[1024];     
        this.pilhaDados = new Pilha();
        
        int i = 0;
        this.ponteiroInstrucoes = 0;
        // Constroi a tabela de hash que mapeia as labels às suas respectivas posições
        for (Instrucao x : this.programa) {
            if (x != null && x.getLabel() != null)
                labels.put(x.getLabel(), i);
            i++;
        }
    }
    
    public Boolean execucaoFinalizada() {
        return this.execucaoFinalizada;
    }
    
    public void executaInstrucao(Robo robo) {
        
        String instrucaoAtual;
        if ((instrucaoAtual = this.retornaInstrucao().getInstrucao()) != "END") {
            Boolean jumpFlag = false;
            Boolean ocupFlag = false;
            
            
            //Manipulação da Pilha
            if (instrucaoAtual == "PUSH") {
                Object obj = this.retornaInstrucao().getOperando();
                
                if (obj instanceof Empilhavel) {
                    pilhaDados.Empilha((Empilhavel)obj);;
                }
                else
                    this.execucaoFinalizada = true;

            }
            else if (instrucaoAtual == "POP" ){
                pilhaDados.Descarta();
            }
            else if (instrucaoAtual == "DUP" ){         
                pilhaDados.Dup();
            }
            //Operações Aritméticas
            else if (instrucaoAtual == "ADD" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;            
                    x2.setNum(x2.getNum() + x1.getNum());
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;  
            }
            else if (instrucaoAtual == "SUB" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;            
                    x2.setNum(-x1.getNum() + x2.getNum());
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "MUL" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;            
                    x2.setNum(x1.getNum() * x2.getNum());
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "DIV" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;            
                    x2.setNum(x2.getNum()/x1.getNum());
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }

            //Operações de Salto
            else if (instrucaoAtual == "JMP" ){
                Object obj = this.retornaInstrucao().getOperando();
                if (obj instanceof Texto) {
                    ponteiroInstrucoes = labels.get(((Texto)obj).getString());
                    jumpFlag = true;
                }
                else 
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "JIT" ){
                Object obj = pilhaDados.Desempilha();
                if (obj instanceof Numero) {
                    Numero x1 = (Numero)obj;
                    
                    if (x1.getNum() != 0) {
                        ponteiroInstrucoes = labels.get(((Texto)this.retornaInstrucao().getOperando()).getString());
                        jumpFlag = true;
                    }
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "JIF" ){
            Object obj = pilhaDados.Desempilha();
                if (obj instanceof Numero) {
                    Numero x1 = (Numero)obj;
                    
                    if (x1.getNum() == 0) {
                        ponteiroInstrucoes = labels.get(((Texto)this.retornaInstrucao().getOperando()).getString());
                        jumpFlag = true;
                    }
                }
                else
                    this.execucaoFinalizada = true;
            }
            
            /* Chamada ao sistema */
            /* Considera-se que nesta chamada, foi feito previamente
             * o empilhamento da arena(do mapa). */
            else if (instrucaoAtual == "SYS" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = this.retornaInstrucao().getOperando();
                if (obj1 instanceof Arena && obj2 instanceof Operacao) {
                    Arena mapa = (Arena)obj1;
                    int ret = mapa.sistema((Operacao)obj2, robo);
                    
                    robo.setAcaoAtual(((Operacao)obj2).getAcao());
                    
                    // retorna 2 quando ocupacao for != 0 (nao executa
                    // ação);
                    if (ret == 2) {
                    	pilhaDados.Empilha((Empilhavel)obj1);
                    	ocupFlag = true;
                    }
                    
                    else {
                    	Numero num = new Numero();
                    	num.setNum(ret);
                    	pilhaDados.Empilha(num);
                    }
                }
                else
                    this.execucaoFinalizada = true;
            }

            //Operações Lógicas
            else if (instrucaoAtual == "EQ" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;
                    if (x1.getNum() == x2.getNum()) x2.setNum(1);
                    else x2.setNum(0);
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "GT" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;
                    if (x1.getNum() > x2.getNum()) x2.setNum(1);
                    else x2.setNum(0);
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "GE" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;
                    if (x1.getNum() >= x2.getNum()) x2.setNum(1);
                    else x2.setNum(0);
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "LT" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;
                    if (x1.getNum() < x2.getNum()) x2.setNum(1);
                    else x2.setNum(0);
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "LE" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;
                    if (x1.getNum() <= x2.getNum()) x2.setNum(1);
                    else x2.setNum(0);
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "NE" ){
                Object obj1 = pilhaDados.Desempilha();
                Object obj2 = pilhaDados.Desempilha();
                if (obj1 instanceof Numero && obj2 instanceof Numero) {
                    Numero x1 = (Numero)obj1;
                    Numero x2 = (Numero)obj2;
                    if (x1.getNum() != x2.getNum()) x2.setNum(1);
                    else x2.setNum(0);
                    pilhaDados.Empilha(x2);
                }
                else
                    this.execucaoFinalizada = true;
            }

            //Operações de Memória
            else if (instrucaoAtual == "STO" ){
                Object obj = this.retornaInstrucao().getOperando();
                if (obj instanceof Numero) {
                    Numero x1 = (Numero)(obj);
                    memoria[x1.getNum()] = pilhaDados.Desempilha();
                }
                else
                    this.execucaoFinalizada = true;
            }
            else if (instrucaoAtual == "RCL" ){
                Object obj = this.retornaInstrucao().getOperando();
                if (obj instanceof Numero) {
                    Numero x1 = (Numero)(obj);
                    pilhaDados.Empilha((Empilhavel)memoria[x1.getNum()]);
                }
                else
                    this.execucaoFinalizada = true;
            }

            //Impressão na Tela
            else if (instrucaoAtual == "PRN" ){
                Object obj = pilhaDados.Desempilha();
                if (obj instanceof Numero) {
                    Numero x1 = (Numero)obj;
                    System.out.println(x1.getNum());
                }
                else if (obj instanceof Texto) {
                    Texto x1 = (Texto)obj;
                    System.out.println(x1.getString());
                }
                else
                    this.execucaoFinalizada = true;                
            }
            
            if (!jumpFlag && !ocupFlag) ponteiroInstrucoes++;
        }
        else {
            this.execucaoFinalizada = true;
        }
    }
    
    private Instrucao retornaInstrucao() {
        return this.programa[ponteiroInstrucoes];
    }

}