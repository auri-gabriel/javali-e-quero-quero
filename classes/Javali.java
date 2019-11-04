import java.util.List;
import java.util.Iterator;
import java.util.Random;

/**
 * A classe Javali juntamente com a classe QueroQuero, fornecem modelos simples
 * do comportamento de um predador e de uma presa, respectivamente. Nesta
 * implementaÃ§Ã£o particular nÃ£o tentamos fornecer um modelo biolÃ³gico preciso
 * para javalis e quero-queros reais; em vez disso, simplesmente tentaremos
 * ilustrar os princÃ­pios de simulaÃ§Ãµes tÃ­picas entre predador e presa.
 */

public class Javali extends Animal {

    private static final int IDADE_PROCRIACAO = 10;
    private static final int IDADE_MAXIMA = 150;
    private static final double PROBABILIDADE_PROCRIACAO = 0.75;
    private static final int TAMANHO_MAXIMO_NINHADA = 5;
    private static final int VALOR_FOME_QUEROQUERO = 7;
    private static final Random rand = Randomizador.getRandom();
    private int idade;
    private int nivelFome;

  

    public Javali(boolean idadeRandomica, Campo campo, Localizacao localizacao) {
        super(campo, localizacao);
        if (idadeRandomica) {
            idade = rand.nextInt(IDADE_MAXIMA);
            nivelFome = rand.nextInt(VALOR_FOME_QUEROQUERO);
        } else {
            nivelFome = VALOR_FOME_QUEROQUERO;
            idade = 0;
        }
    }

    public void atividade(List<Animal> novosJavalis) {
        incrementaFome();
        incrementaIdade();
        if (estaVivo()) {
            daALuz(novosJavalis);
            Localizacao localizacao = getLocalizacao();
            Localizacao newLocalizacao = procuraComida(localizacao);
            if (newLocalizacao == null) {
                newLocalizacao = getCampo().localizacaoAdjacenteLivre(localizacao);
            }
            if (newLocalizacao != null) {
                setLocalizacao(newLocalizacao);
            } else {
                setMorte();
            }
        }
    }

    private void incrementaFome() {
        nivelFome--;
        if (nivelFome <= 0) {
            setMorte();
        }
    }


    private Localizacao procuraComida(Localizacao localizacao) {
        Campo campo = getCampo();
        List<Localizacao> adjacente = campo.localizacoesAdjacentes(localizacao);
        Iterator<Localizacao> it = adjacente.iterator();
        while (it.hasNext()) {
            Localizacao onde = it.next();
            Object animal = campo.obterObjeto(onde);
            if (animal instanceof QueroQuero) {
                QueroQuero queroQuero = (QueroQuero) animal;
                if (queroQuero.estaVivo()) {
                    queroQuero.setMorte();
                    nivelFome = VALOR_FOME_QUEROQUERO;
                    return onde;
                }
            }
        }
        return null;
    }

    private void daALuz(List<Animal> novosJavalis) {
        Campo campo = getCampo();
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(getLocalizacao());
        int nascimentos = procria();
        for (int b = 0; b < nascimentos && livre.size() > 0; b++) {
            Localizacao loc = livre.remove(0);
            Javali jovem = new Javali(false, campo, loc);
            novosJavalis.add(jovem);
        }
    }

    private void incrementaIdade() {
        idade++;
        if (idade > IDADE_MAXIMA) {
            setMorte();
        }
    }

   
    private int procria() {
        int nascimentos = 0;
        if (podeProcriar() && rand.nextDouble() >= PROBABILIDADE_PROCRIACAO) {
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }
        return nascimentos;
    }

   
    private boolean podeProcriar() {
        return idade >= IDADE_PROCRIACAO;
    }

}
