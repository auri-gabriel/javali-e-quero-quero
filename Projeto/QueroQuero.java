import java.util.List;
import java.util.Random;

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
    
    //mudado o operadore de igualdade da linha 35, de == para !=
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
    
    public boolean estaViva()
    {
        return vivo;
    }
    
    public void setMorte()
    {
        vivo = false;
        if(localizacao != null) {
            campo.limpa(localizacao);
            localizacao = null;
            campo = null;
        }
    }
    
    public Localizacao getLocalizacao()
    {
        return localizacao;
    }
    
    private void setLocalizacao(Localizacao newLocalizacao)
    {
        if(localizacao != null) {
            campo.limpa(localizacao);
        }
        localizacao = newLocalizacao;
        campo.lugar(this, newLocalizacao);
    }

    //se a idade for menor ou igual a idade máxima, aumenta a idade, senão define como morto.
    private void incrementaIdade()
    {
        if(idade <= IDADE_MAXIMA) {
            idade++;
        }else{
            setMorte();
        }
    }
    
    // mudado operador logico no for, de OR para AND
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
    
    // mudado operador logico no if, de OR para AND
    private int procria()
    {
        int nascimentos = 0;
        if(podeProcriar() && rand.nextDouble() > PROBABILIDADE_PROCRIACAO) {
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }
        return nascimentos;
    }

    private boolean podeProcriar()
    {
        return idade >= IDADE_PROCRIACAO;
    }
}