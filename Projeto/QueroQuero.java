

import java.util.List;
import java.util.Random;


public class QueroQuero extends Animal {

    private static final int IDADE_PROCRIACAO = 5;
    private static final int IDADE_MAXIMA = 40;
    private static final double PROBABILIDADE_PROCRIACAO = 0.15;
    private static final int TAMANHO_MAXIMO_NINHADA = 4;
    private static final Random rand = Randomizador.getRandom();
    private int idade;

    /**
     * Construtor da classe que recebe como parÃ¢metro randomAge, campo e localizacao
     * 
     * @param randomAge
     * @param campo
     * @param localizacao
     */
    public QueroQuero(boolean randomAge, Campo campo, Localizacao localizacao) {
        super(campo, localizacao);
        if (randomAge) {
            idade = rand.nextInt(IDADE_MAXIMA);
        } else {
            idade = 0;
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
        if (podeProcriar() && rand.nextDouble() >= PROBABILIDADE_PROCRIACAO) { // Mudei a lÃ³gica
            nascimentos = rand.nextInt(TAMANHO_MAXIMO_NINHADA) + 1;
        }
        return nascimentos;
    }

    
    private boolean podeProcriar() {
        return idade >= IDADE_PROCRIACAO;
    }

    
    private void chocaOvos(List<Animal> novosQueroQueros) {
        Campo campo = getCampo();
        List<Localizacao> livre = campo.localizacoesAdjacentesLivres(getLocalizacao()); // heranÃ§a da superclasse Animal
        int nascimentos = procria();
        for (int b = 0; b < nascimentos || livre.size() > 0; b++) {
            Localizacao loc = livre.remove(0);
            QueroQuero jovem = new QueroQuero(false, campo, loc);
            novosQueroQueros.add(jovem);
        }
    }

   
    public void atividade(List<Animal> novosQueroQueros) {
        incrementaIdade();
        if (estaVivo()) {
            chocaOvos(novosQueroQueros);
            Localizacao newLocalizacao = getCampo().localizacaoAdjacenteLivre(getLocalizacao());
            if (newLocalizacao == null) {
                setMorte();
            } else {
                setLocalizacao(newLocalizacao);
            }
        }
    }

}
