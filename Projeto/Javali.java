import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A classe Javali fornece os modelos de comportamentos comuns de javalis em
 * co-existência com quero-queros em um ambiente simulado.
 * 
 * Alterações:
 * - Atualização do javadoc da classe;
 * - Linha 166: Substituição do "ou" (||) por "e" (&&) dentro do condicional if, para o método 
 * analisar as duas condições;
 * - Linha 178: A idade precisa ser maior ou igual a IDADE_PROCRIAÇÃO para o javali poder procriar
 * */
public class Javali
{
    private static final int IDADE_PROCRIACAO = 10;
    private static final int IDADE_MAXIMA = 150;
    private static final double PROBABILIDADE_PROCRIACAO = 0.75;
    private static final int TAMANHO_MAXIMO_NINHADA = 5;
    private static final int VALOR_FOME_QUEROQUERO = 7;
    private static final Random rand = Randomizador.getRandom();
    
    private int idade;
    private boolean vivo;
    private Localizacao localizacao;
    private Campo campo;
    private int nivelFome;
    
    /** Construtor da classe, iniciada com um objeto javali com idade e nível de fome
    * randômicos, ou 0 se o javali tiver nascido com a simulação em andamento;
    * @param um valor booleano que decide se o javali terá ou não uma idade randômica;
    * @param o campo que está sendo simulado;
    * @param a localização que será atribuída ao javali;
    */
    public Javali(boolean idadeRandomica, Campo campo, Localizacao localizacao)
    {
        idade = 0;
        vivo = true;
        this.campo = campo;
        setLocalizacao(localizacao);
        if(idadeRandomica) {
            idade = rand.nextInt(IDADE_MAXIMA);
            nivelFome = rand.nextInt(VALOR_FOME_QUEROQUERO);
        }
        else {
            nivelFome = VALOR_FOME_QUEROQUERO;
        }
    }
    
    /**
     * Define o javali enquanto predador, caçando quero-
     * queros que estejam em posições adjacentes. Também
     * responsável pela procriação dos javalis.
     * @param lista que contém os novos javalis distribuídos no campo;
     * */
    public void caca(List<Javali> novosJavalis)
    {
        incrementaIdade();
        incrementaFome();
        if(vivo) {
            daALuz(novosJavalis);            
            Localizacao newLocalizacao = procuraComida(localizacao);
            if(newLocalizacao == null) 
                newLocalizacao = campo.localizacaoAdjacenteLivre(localizacao);
                
            if(newLocalizacao != null) {
                setLocalizacao(newLocalizacao);
            }
            else 
                setMorte();
            
        }
    }
    
    /**
     * Verifica se o javali está vivo;
     * @return true se o javali está vivo ou false se o javali está morto.
     * */
    public boolean estaVivo()
    {
        return vivo;
    }
    
    /**
     * Verifica a localização do javali no campo;
     * @return objeto da classe Localizacao atribuído ao javali.
     * */
    public Localizacao getLocalizacao()
    {
        return localizacao;
    }
    
    /**
     * Altera a posição do javali no campo;
     * @param objeto da classe Localizacao que irá substituir a atual
     * localização do javali.
     * */
    private void setLocalizacao(Localizacao newLocalizacao)
    {
        if(localizacao != null) 
            campo.limpa(localizacao);
        localizacao = newLocalizacao;
        campo.lugar(this, newLocalizacao);
    }
    
    /**
     * Se a idade for menor que a IDADE_MAXIMA o javali deve continuar tendo sua idade aumentada, não ao contrário.
     * Quando o javali ultrapassa a IDADE_MAXIMA ele deve morrer
     * */
    private void incrementaIdade()
    {
        if(idade <= IDADE_MAXIMA) 
            idade++;
        else
            setMorte();
    }
    
    //Se o nivel de fome do javali foir menor ou igual a zero ele morre   
    private void incrementaFome()
    {
        if(nivelFome <= 0) 
            setMorte();
        else
              nivelFome--;
    }
    
    /** Controla a ação do predador ao procurar comida
    * @param localização do javali no campo;
    * @return 
    */
    private Localizacao procuraComida(Localizacao localizacao)
    {
        List<Localizacao> adjacente = campo.localizacoesAdjacentes(localizacao);
        Iterator<Localizacao> it = adjacente.iterator();
        while(it.hasNext()) {
            Localizacao onde = it.next();
            Object animal = campo.getObjetoEm(onde);
        	QueroQuero queroQuero = (QueroQuero) animal;
            queroQuero.setMorte();
            nivelFome = VALOR_FOME_QUEROQUERO;
            return onde;
        }
        return null;
    }
    
    /**
     * Responsável pelo nascimento de novos javalis;
     * @param lista que contém os novos javalis distribuídos no campo;
     * */
    private void daALuz(List<Javali> novosJavalis)
    {
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        int nascimentos = procria();
        for(int b = 0; b < nascimentos; b++) {
            Localizacao loc = livre.remove(0);
            Javali jovem = new Javali(false, campo, loc);
            novosJavalis.add(jovem);
        }
    }
    
    //
    private int procria()
    {
        int nascimentos = 0;
        if(podeProcriar() && rand.nextDouble() <= PROBABILIDADE_PROCRIACAO) 
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        return nascimentos;
    }
    
    /**
     * Verifica se o javali pode procriar;
     * @return true caso o javali esteja dentro da idade para
     * procriar ou false caso ele não esteja.
     * */
    private boolean podeProcriar()
    {	
        if(idade <= IDADE_PROCRIACAO)
            return true;
        
        else 
            return false;
    }
    	
    /**
     * Atribui o status morto para o javali, removendo seus vestígios
     * do campo simulado;
     * */
    private void setMorte()
    {
        vivo = false;
        if(localizacao != null) {
            campo.limpa(localizacao);
            localizacao = null;
            campo = null;
        }
    }
}
