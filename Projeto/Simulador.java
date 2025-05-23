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
 *Alterações:
 * - Atualização no javadoc;
 * - Renomeação de variáveis (Linhas 123);
 * - Mudança nos condicionais do método povoa()
 * - Linha 69: Remoção do método redefine().
 */

public class Simulador
{
    private static final int LARGURA_PADRAO = 50;
    private static final int PROFUNDIDADE_PADRAO = 50;
    private static final double PROBABILIDADE_CRIACAO_JAVALI = 0.02;
    private static final double PROBABILIDADE_CRIACAO_QUEROQUERO = 0.28;    

    private List<Animal> a;
    private Campo campo;
    private int etapa;
    private SimuladorTela tela;
    
    /**
     * Construtor da classe Simulador.
     * Caso não seja atribuído nenhum parâmetro, o simulador é construído com largura e profundidade
     * padrões (50 x 50).
     */
    public Simulador()
    {
        this(PROFUNDIDADE_PADRAO, LARGURA_PADRAO);
    }
    
    /**
     * Construtor da classe Simulador.
     * @param altura Profundidade do simulador sendo > 0.
     * @param largura Largura do simulador sendo > 0.
     */
    public Simulador(int altura, int largura)
    {
        if(largura <= 0 && altura <= 0) { 
            System.out.println("As dimensões devem ser maior do que zero.");
            System.out.println("Usando valores padrões.");
            altura = PROFUNDIDADE_PADRAO;
            largura = LARGURA_PADRAO;
        }
        
        a = new ArrayList<Animal>();
        javalis = new ArrayList<Javali>();
        campo = new Campo(altura, largura);

        tela = new SimuladorTela(altura, largura);
        tela.setCor(QueroQuero.class, Color.orange);
        tela.setCor(Javali.class, Color.blue);
        reiniciaSimulacao();
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
     * @param numEtapas Número de etapas desejado para a simulação.
     */
    public void simulacao(int numEtapas)
    {
        for(int etapa = 0; etapa < numEtapas && tela.ehViavel(campo); etapa++) {
        	simulacaoUmaEtapa();
        }
    }
    
    /**
     * O método cria duas novas coleções, que irão conter os novos javalis e
     * quero-queros para então adicioná-los ao ArrayList que contém o total de
     * javalis e quero-queros e, após, xecuta a simulação de uma etapa do programa.
     */
    public void simulacaoUmaEtapa()
    {
    	etapa++;
    List<Animal> novosAnimais = new ArrayList<Animal>();
    for (Iterator<Animal> it = a.iterator(); it.hasNext();) {
        Animal animal = it.next();
        animal.atividade(novosAnimais);
        if (!animal.estaVivo()) {
            it.remove();
        }
    }
    a.addAll(novosAnimais);
    tela.mostraStatus(etapa, campo);
    }
    
    /**
     * Reseta a simulação.
     */
    public void reiniciaSimulacao()
    {
    
        etapa = 0;
        queroQueros.clear();
        javalis.clear();
        povoa();
        tela.mostraStatus(etapa, campo);
        
    }
    
    /**
     * Realiza a distribuição da população de javalis e quero-queros pelo campo simulado.
     */
    
    public void povoa()
    {
        Random rand = Randomizador.getRandom();
        campo.limpa();
        for(int linha = 0; linha < campo.getAltura(); linha++) {
            for(int coluna = 0; coluna < campo.getLargura(); coluna++) {
                if(rand.nextDouble() <= PROBABILIDADE_CRIACAO_JAVALI) {
                    Localizacao novaLocalizacao = new Localizacao(linha, coluna);
                    Javali javali = new Javali(false, campo, novaLocalizacao);
                    a.add(javali);
                }
                else if(rand.nextDouble() <= PROBABILIDADE_CRIACAO_QUEROQUERO) {
                    Localizacao novaLocalizacao = new Localizacao(linha, coluna);
                    QueroQuero queroQuero = new QueroQuero(false, campo, novaLocalizacao);
                    a.add(queroQuero);
                }
            }
        }
    }
    
    public static void main(String [] args) {
    	Simulador s = new Simulador(100,100);
    	s.simulacao(100);
    }
}
