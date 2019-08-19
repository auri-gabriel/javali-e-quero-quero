import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Color;

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
    
    public Simulador()
    {
        this(PROFUNDIDADE_PADRAO, LARGURA_PADRAO);
    }
    
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

    public void executaLongaSimulacao()
    {
        simulacao(500);
    }
    
    public void simulacao(int numEtapas)
    {
        for(int etapa = 1; etapa <= numEtapas && tela.ehViavel(campo); etapa++) {
            simulacaoUmaEtapa();
        }
    }
    
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
    
    public void redefine()
    {
        etapa = 0;
        queroQueros.clear();
        javalis.clear();
        tela.mostraStatus(etapa, campo);
    }
    
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