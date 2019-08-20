import java.util.List;
import java.util.Random;

/**
* A classe Quero-Quero fornece um modelo simples do comportamento de uma presa. 
*/
public class QueroQuero
{
    private static final int IDADE_PROCRIACAO = 5;
    private static final int IDADE_MAXIMA = 40;
    private static final double PROBABILIDADE_PROCRIACAO = 0.15;
    private static final int TAMANHO_MAXIMO_NINHADA = 4;
    private static final Random rand = Randomizador.getRandom();
    
    private int idade;
    private boolean vivo;
    private Localizacao localizacao;
    private Campo campo;

    /**
     * Construtor da classe QueroQuero
     * @param randomAge   define se o objeto vai ter uma idade aleatoria
     * @param campo       define o campo do objeto
     * @param localizacao define a localização do objeto
     */
    public QueroQuero(boolean randomAge, Campo campo, Localizacao localizacao)
    {
        idade = 0;
        vivo = true;
        this.campo = campo;
        setLocalizacao(localizacao);
        if(randomAge) {
            idade = rand.nextInt(IDADE_MAXIMA);
        }
    }
    
    /**
     * Realiza a movimentação do queroquero no campo.
	 * @param novosQueroQueros uma lista de novos queroqueros
     */
    public void voa(List<QueroQuero> novosQueroQueros)
    {
        incrementaIdade();
        if(vivo) {
            chocaOvos(novosQueroQueros);            
            Localizacao newLocalizacao = campo.localizacaoAdjacenteLivre(localizacao);
            if(newLocalizacao != null) { 
                setMorte();
            }
            else {
                setLocalizacao(newLocalizacao);
            }
        }
    }
    
    
    /**
     * Informa se o objeto QueroQuero está vivo
     * @return vivo
     */    
    public boolean estaViva()
    {
        return vivo;
    }
    
    /**
     * Define o objeto queroquero como morto e limpa a sua localizacao.
     */    
    public void setMorte()
    {
        vivo = false;
        if(localizacao != null) {
            campo.limpa(localizacao);
            localizacao = null;
            campo = null;
        }
    }
    
    /**
     * Informa a localização do QueroQuero.
     * @return localizacao
     */    
    public Localizacao getLocalizacao()
    {
        return localizacao;
    }
    
    /**
     * Modifica a localização do queroquero.
     * @param newLocalizacao a nova localizacao do queroquero
     */
    private void setLocalizacao(Localizacao newLocalizacao)
    {
        if(localizacao != null) {
            campo.limpa(localizacao);
        }
        localizacao = newLocalizacao;
        campo.lugar(this, newLocalizacao);
    }

    /**
     * Incrementa a idade do QueroQuero
     * se a idade for maior que a idade maxima, define o queroquero como morto
     */
    private void incrementaIdade()
    {
        if(idade <= IDADE_MAXIMA) {
            idade++;
        }else{
            setMorte();
        }
    }
    
   /**
     * Realiza os nascimentos de novos queroqueros.
	 * @param novosQueroQueros uma lista de novos queroqueros
     */
    private void chocaOvos(List<QueroQuero> novosQueroQueros)
    {
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        int nascimentos = procria();
        for(int b = 0; b < nascimentos && livre.size() > 0; b++) {
            Localizacao loc = livre.remove(0);
            QueroQuero jovem = new QueroQuero(false, campo, loc);
            novosQueroQueros.add(jovem);
        }
    }
    
    /**
     * Gera um numero aleatorio de nascimentos.
     * @return nascimentos  numero de nascimentos que ocorrerão.
     */
    private int procria()
    {
        int nascimentos = 0;
        if(podeProcriar() && rand.nextDouble() <= PROBABILIDADE_PROCRIACAO) {
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }
        return nascimentos;
    }

    /**
     * informa se o  QueroQuero pode procriar.
     * @return true se a idade for maior ou igual a idade de procriação.
     */
    private boolean podeProcriar()
    {
        return idade >= IDADE_PROCRIACAO;
    }
}