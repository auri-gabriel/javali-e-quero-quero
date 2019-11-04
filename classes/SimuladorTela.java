import java.awt.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A classe SimuladorTela fornece uma visão gráfica do estado do campo.
 *
 * * Alterações:
 * - Remoção do import java.awt.event;
 * - Renomeação de variáveis e métodos;
 */
 
public class SimuladorTela extends JFrame
{
    private static final long serialVersionUID = 1L;
    private static final Color COR_VAZIA = Color.white;
    private static final Color COR_INDEFINIDA = Color.gray;

    private final String PREFIXO_ETAPA = "Etapa: ";
    private final String PREFIXO_POPULACAO = "Populacao: ";
    private JLabel rotuloEtapa, populacao;
    private VisaoCampo visaoCampo;
    
    private Map<Class <?>, Color> cores;
    private CampoEstatistica estatisticas;

   /**
     * Construtor da classe SimuladorTela;
     * @param altura Altura do simulador;
     * @param largura Largura do simulador.
     */
    public SimuladorTela(int altura, int largura) 
    {
        estatisticas = new CampoEstatistica();
        cores = new LinkedHashMap<Class <?>, Color>();

        setTitle("Simulacao Quero-Queros e Javalis");
        rotuloEtapa = new JLabel(PREFIXO_ETAPA, JLabel.CENTER);
        populacao = new JLabel(PREFIXO_POPULACAO, JLabel.CENTER);

        setLocation(100, 50);

        visaoCampo = new VisaoCampo(altura, largura);

        Container conteudos = getContentPane();
        conteudos.add(rotuloEtapa, BorderLayout.NORTH);
        conteudos.add(visaoCampo, BorderLayout.CENTER);
        conteudos.add(populacao, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    
    /**
     * Altera a cor de determinada classe de animal na simulação;
     * @param classeAnimal Classe de animal;
     * @param cor Cor escolhida.
     */
    public void setCor(Class <?> classeAnimal, Color cor)
    {
        cores.put(classeAnimal, cor);
    }
    
    /**
     * Retorna a cor que representa determinada classe de animal na simulação;
     * @param classeAnimal Classe de animal;
     */
    public Color getCor(Class <?> classeAnimal)
    {
        Color coluna = cores.get(classeAnimal);
        if(coluna == null) {
            return COR_INDEFINIDA;
        }
        else {
            return coluna;
        }
    }
    
    /**
     * Exibe na tela a situação atual da simulação de determinada etapa;
     * @param etapa Etapa na qual deseja-se observar a situação atual;
     * @param campo Campo utilizado para a simulação.
     */
    public void mostraStatus(int etapa, Campo campo)
    {
        if(!isVisible()) {
            setVisible(true);
        }
        
        rotuloEtapa.setText(PREFIXO_ETAPA + etapa);
        estatisticas.reiniciaEstatisticas();

        visaoCampo.prepararPintura();

        for(int linha = 0; linha < campo.getAltura(); linha++) {
            for(int coluna = 0; coluna < campo.getLargura(); coluna++) {
                Object animal = campo.getObjetoEm(linha, coluna);
                if(animal != null) {
                    estatisticas.incrementaContador(animal.getClass());
                    visaoCampo.desenharMarca(coluna, linha, getCor(animal.getClass()));
                }
                else
                    visaoCampo.desenharMarca(coluna, linha, COR_VAZIA);
            }
        }
        estatisticas.contadorFinalizado();

        populacao.setText(PREFIXO_POPULACAO + estatisticas.obterDetalhesPopulacao(campo));
    }
    
    /**
     * Retorna um valor booleano que indica se o campo que foi simulado é viável ou não para a manutenção das duas espécies;
     * @param campo Campo utilizado para a simulação;
     * @return True caso o campo seja viável ou false caso o campo não seja viável.
     */
    
    public boolean ehViavel(Campo campo)
    {
        return estatisticas.ehViavel(campo);
    }
    
    /**
     * A classe VisaoCampo é responsável pelo gerenciamento dos métodos e atributos que compõem a interface gráfica da simulação;
     */
    public class VisaoCampo extends JPanel
    {
        private static final long serialVersionUID = 1L;

        private final int FATOR_ESCALA_VISAO_GRADE = 6;

        private int larguraGrade, alturaGrade;
        private int escalaX, escalaY;
        Dimension tamanho;
        private Graphics grafico;
        private Image imagemCampo;
        
        /**
         * Construtor da classe VisaoCampo;
         * @param altura Altura do campo;
         * @param largura Largura do campo;
         */
        public VisaoCampo(int altura, int largura)
        {
            alturaGrade = altura;
            larguraGrade = largura;
            tamanho = new Dimension(0, 0);
        }
        
        /**
         * Retorna um novo valor para o dimensionamento do campo, sendo passados dois parâmetros:
         * o valor da multiplicação entre a largura da grade e o fator de escala de exibição da grade e
         * o valor da multiplicação entre a altura e o fator de escala de exibição da grade.
         * @return novo dimensionamento;
         */
        public Dimension obterTamanhoPreferido()
        {
            return new Dimension(larguraGrade * FATOR_ESCALA_VISAO_GRADE, 
                        alturaGrade * FATOR_ESCALA_VISAO_GRADE);
        }
        
        /**
         * Atribui as dimensões de escala para os valores x e y, que serão
         * utilizados para a construção do desenho na imagem.
         */
        public void prepararPintura()
        {
            if(! tamanho.equals(getSize())) {
                tamanho = getSize();
                imagemCampo = visaoCampo.createImage(tamanho.width, tamanho.height);
                grafico = imagemCampo.getGraphics();

                escalaX = tamanho.width / larguraGrade;
                if(escalaX < 1) {
                    escalaX = FATOR_ESCALA_VISAO_GRADE;
                }
                escalaY = tamanho.height / alturaGrade;
                if(escalaY < 1) {
                    escalaY = FATOR_ESCALA_VISAO_GRADE;
                }
            }
        }
        
        /**
         * Atribui a cor e o posicionamento do retângulo na tela, que representa 
         * a posição de um javali ou quero-quero no campo;
         * @param x A posição x do quadrado a ser pintado;
         * @param y A posição y do quadrado a ser pintado; 
         * @param cor A cor do quadrado. 
         */
        public void desenharMarca(int x, int y, Color cor)
        {
            grafico.setColor(cor);
            grafico.fillRect(x * escalaX, y * escalaY, escalaX-1, escalaY-1);
        }
        
        /**
         * Cria a imagem na tela;
         * @param Imagem que será carregada.
         */
        public void pintarComponente(Graphics grafico)
        {
            if(imagemCampo != null) {
                Dimension tamanhoAtual = getSize();
                if(tamanho.equals(tamanhoAtual)) 
                    grafico.drawImage(imagemCampo, 0, 0, null);
                else 
                    grafico.drawImage(imagemCampo, 0, 0, tamanhoAtual.width, tamanhoAtual.height, null);
            }
        }
    }
}
