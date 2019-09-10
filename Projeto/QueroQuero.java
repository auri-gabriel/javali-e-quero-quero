import java.util.List;
import java.util.Random;

/**
 * A classe QueroQuero fornece um modelo simples do comportamento dos
 * quero-queros dentro de um campo simulado, em coexistência com javalis,
 * ilustrando situações típicas entre predador e presa.
 * 
 * * Alterações:
 * - Renomeação de variáveis;
 * - Atualização do javadoc da classe;
 * - Alteração do operador lógico "igual" (==) para "diferente" (!=), na linha 56;
 * - Alteração do operador lógico "ou" (||) para "e" (&&), na linha 133;
 * - Alteração do operador lógico "ou" (||) para "e" (&&), na linha 148;
 **/
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
     * Método construtor da classe QueroQuero;
     * @param um valor booleano que decide se o quero-quero terá ou não uma idade randômica;
     * @param o campo que está sendo simulado;
     * @param a localização que será atribuída ao javali.
     * */
    public QueroQuero(boolean idadeRandomica, Campo campo, Localizacao localizacao)
    {
        idade = 0;
        vivo = true;
        this.campo = campo;
        setLocalizacao(localizacao);
        if(idadeRandomica) {
            idade = rand.nextInt(IDADE_MAXIMA);
        }
    }
    
    /**
     * Fornece o modelo de comportamento de voo do quero-quero pelo campo simulado;
     * @param a coleção de novos quero-queros;
     * */
    public void voa(List<QueroQuero> novosQueroQueros)
    {
        incrementaIdade();
        if(vivo) {
            chocaOvos(novosQueroQueros);            
            Localizacao novaLocalizacao = campo.localizacaoAdjacenteLivre(localizacao);
            if(novaLocalizacao == null) 
                setMorte();
            else 
                setLocalizacao(novaLocalizacao);
        }
    }
    
    /**
     * Verifica se o quero-quero está vivo;
     * @return true se o quero-quero está vivo ou false se o quero-quero está morto;
     * */
    public boolean estaViva()
    {
        return vivo;
    }
    
    /**
     * Atribui o status morto para o quero-quero, removendo seus vestígios
     * do campo simulado;
     * */
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
     * Verifica a localização do quero-quero no campo;
     * @return objeto da classe Localizacao atribuído ao quero-quero.
     * */
    public Localizacao getLocalizacao()
    {
        return localizacao;
    }
    
    /**
     * Altera a posição do quero-quero no campo;
     * @param objeto da classe Localizacao que irá substituir a atual
     * localização do quero-quero.

     * */
    private void setLocalizacao(Localizacao newLocalizacao)
    {
        if(localizacao != null) 
            campo.limpa(localizacao);
        localizacao = newLocalizacao;
        campo.lugar(this, newLocalizacao);
    }

    /**
     * Incrementa a idade do quero-quero. Se a idade for menor ou igual 
     * a idade máxima, aumenta a idade, senão define como morto.
     * */
    private void incrementaIdade()
    {
        if(idade <= IDADE_MAXIMA) 
            idade++;
        else
            setMorte();       
    }
    
    /**
     * Método responsável pela distribuição dos quero-queros recém-nascidos no campo;
     * @param lista que contém os novos quero-queros distribuídos no campo;
     * */
    private void chocaOvos(List<QueroQuero> novosQueroQueros)
    {
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(localizacao);
        int nascimentos = procria();
        for(int aux = 0; aux < nascimentos && livre.size() > 0; aux++) {
            Localizacao localizacaoLivre = livre.remove(0);
            QueroQuero jovem = new QueroQuero(false, campo, localizacaoLivre);
            novosQueroQueros.add(jovem);
        }
    }
    
    /**
     * Método responsável pelo nascimento de novos quero-queros, considerando o índice de
     * probabilidade de novos nascimentos;
     * @return o número de nascimentos de quero-queros;
     * */
    private int procria()
    {
        int nascimentos = 0;
        if(podeProcriar() && rand.nextDouble() > PROBABILIDADE_PROCRIACAO) {
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }
        return nascimentos;
    }
    
    /**
     * Verifica se o quero-quero está apto a procriar;
     * @return true se o quero-quero pode procriar, senão retorna false;
     * */
    private boolean podeProcriar()
    {
        return idade >= IDADE_PROCRIACAO;
    }
}