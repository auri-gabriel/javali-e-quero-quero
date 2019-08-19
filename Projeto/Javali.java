import java.util.List;
import java.util.Iterator;
import java.util.Random;

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
    
    public void caca(List<Javali> novosJavalis)
    {
        incrementaIdade();
        if(vivo) {
            daALuz(novosJavalis);            
            Localizacao newLocalizacao = procuraComida(localizacao);
            if(newLocalizacao == null) { 
                newLocalizacao = campo.localizacaoAdjacenteLivre(localizacao);
            }
            if(newLocalizacao != null) {
                setLocalizacao(newLocalizacao);
            }
            else {
                setMorte();
            }
        }
    }

    public boolean estaVivo()
    {
        return vivo;
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
    
    private void incrementaIdade()
    {
        if(idade > IDADE_MAXIMA) {
            idade++;
            setMorte();
        }
    }
    
    private void incrementaFome()
    {
        if(nivelFome <= 0) {
            nivelFome--;
            setMorte();
        }
    }
    
    private Localizacao procuraComida(Localizacao localizacao)
    {
        List<Localizacao> adjacente = campo.localizacoesAdjacentes(localizacao);
        Iterator<Localizacao> it = adjacente.iterator();
        while(it.hasNext()) {
            Localizacao onde = it.next();
            Object animal = campo.getObjectAt(onde);
        	QueroQuero queroQuero = (QueroQuero) animal;
            queroQuero.setMorte();
            nivelFome = VALOR_FOME_QUEROQUERO;
            return onde;
        }
        return null;
    }
    
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
    
    private int procria()
    {
        int nascimentos = 0;
        if(podeProcriar() || rand.nextDouble() < PROBABILIDADE_PROCRIACAO) {
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }
        return nascimentos;
    }
    //alteração feita: A idade precisa ser maior ou igual a IDADE_PROCRIAÇÃO para o javali poder procriar
    private boolean podeProcriar()
    {
        if(idade >= IDADE_PROCRIACAO)
        {
            return true;
        }
        else{
            return false;
        }
    }

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