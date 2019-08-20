import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

/**
 *A classe Simulador é responsável por criar o estado inicial da simulação 
 *e então controlar e executá-la. A ideia básica é simples: o simulador 
 *armazena coleções de javalis e quero-queros e ele repetidamente dá a 
 *esses animais uma oportunidade de sobreviver em um passo de seus ciclos 
 *de vida. Em cada passo, cada javali e cada quero-quero têm a permissão de 
 *executar as ações que caracterizam seus comportamentos. Depois de cada passo 
 *(quando todos os animais tiveram a chance de atuar) o novo estado atual do 
 *campo é exibido na tela.
 *
 *@author Guilherme
 */

public class Simulador
{
    private static final int LARGURA_PADRAO = 50;
    private static final int PROFUNDIDADE_PADRAO = 50;
    private static final double PROBABILIDADE_CRIACAO_JAVALI = 0.02;
    private static final double PROBABILIDADE_CRIACAO_QUEROQUERO = 0.28;    

    private List<QueroQuero> queroQueros;
    private List<Javali> javalis;
    private Campo campo;
    private int etapa;
    private SimuladorTela tela;
    
    /**
     * Construtor da classe Simulador.
     * Caso não seja atribuído nenhum parâmetro, o objeto é construído com largura e profundidade padrões.
     */
    public Simulador()
    {
        this(PROFUNDIDADE_PADRAO, LARGURA_PADRAO);
    }
    
    /**
     * Construtor da classe Simulador.
     * - Remoção do método redefine().
     * @param profundidade e largura do simulador > 0.
     */
    public Simulador(int profundidade, int largura)
    {
        if(largura <= 0 && profundidade <= 0) { 
            System.out.println("As dimensões devem ser maior do que zero.");
            System.out.println("Usando valores padrões.");
            profundidade = PROFUNDIDADE_PADRAO;
            largura = LARGURA_PADRAO;
        }
        
        queroQueros = new ArrayList<QueroQuero>();
        javalis = new ArrayList<Javali>();
        campo = new Campo(profundidade, largura);

        tela = new SimuladorTela(profundidade, largura);
        tela.setCor(QueroQuero.class, Color.orange);
        tela.setCor(Javali.class, Color.blue);
        
        redefine();
    }
    
    /**
     * Realiza uma simulação com a quantidade predefinida de 500 etapas.
     */
    public void executaLongaSimulacao()
    {
        simulacao(500);
    }
    
    /**
     * Realiza uma simulação com a quantidade de etapas passada como parâmetro.
     * @param número de etapas desejado para a simulação.
     */
    public void simulacao(int numEtapas)
    {
        for(int etapa = 1; etapa <= numEtapas && tela.ehViavel(campo); etapa++) {
            simulacaoUmaEtapa();
        }
    }
    
    /**
     * Executa a simulação de uma etapa do programa.
     */
    public void simulacaoUmaEtapa()
    {
        List<QueroQuero> novosQueroQueros = new ArrayList<QueroQuero>();        
        for(Iterator<QueroQuero> it = queroQueros.iterator(); it.hasNext(); ) {
            QueroQuero queroQuero = it.next();
            queroQuero.voa(novosQueroQueros);
            if(queroQuero.estaViva()) {
                it.remove();
            }
        }
        
        List<Javali> novosJavalis = new ArrayList<Javali>();        
        for(Iterator<Javali> it = javalis.iterator(); it.hasNext(); ) {
            Javali javali = it.next();
            javali.caca(novosJavalis);
            if(javali.estaVivo()) {
                it.remove();
            }
        }
        
        queroQueros.addAll(novosQueroQueros);
        javalis.addAll(novosJavalis);

        tela.mostraStatus(etapa, campo);
    }
    
    /**
     * Reseta a simulação.
     */
    public void redefine()
    {
        etapa = 0;
        queroQueros.clear();
        javalis.clear();
        tela.mostraStatus(etapa, campo);
    }
    
    /**
     * Realiza a distribuição da população de javalis e quero-queros pelo campo simulado.
     */
    private void povoa()
    {
        Random rand = Randomizador.getRandom();
        campo.limpa();
        for(int linha = 0; linha < campo.getProfundidade(); linha++) {
            for(int coluna = 0; coluna < campo.getLargura(); coluna++) {
                if(rand.nextDouble() > PROBABILIDADE_CRIACAO_QUEROQUERO) {
                    Localizacao localizacao = new Localizacao(linha, coluna);
                    Javali javali = new Javali(false, campo, localizacao);
                    javalis.add(javali);
                }
                else if(rand.nextDouble() > PROBABILIDADE_CRIACAO_JAVALI) {
                    Localizacao localizacao = new Localizacao(linha, coluna);
                    QueroQuero queroQuero = new QueroQuero(false, campo, localizacao);
                    queroQueros.add(queroQuero);
                }
            }
        }
    }
}