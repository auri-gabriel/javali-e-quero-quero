import java.awt.*;
import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A classe SimuladorTela fornece uma visão gráfica do estado do campo.
 * 
 * Alterações:
 * - Remoção do import java.awt.event por não estar sendo utilizado
 * nesta classe;
 * - Renomeação de variáveis;
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
     * @param height Altura do simulador;
     * @param width Largura do simulador.
     */
    public SimuladorTela(int height, int width)
    {
        estatisticas = new CampoEstatistica();
        cores = new LinkedHashMap<Class <?>, Color>();

        setTitle("Simulacao Quero-Queros e Javalis");
        rotuloEtapa = new JLabel(PREFIXO_ETAPA, JLabel.CENTER);
        populacao = new JLabel(PREFIXO_POPULACAO, JLabel.CENTER);
        
        setLocation(100, 50);
        
        visaoCampo = new VisaoCampo(height, width);

        Container conteudos = getContentPane();
        conteudos.add(rotuloEtapa, BorderLayout.NORTH);
        conteudos.add(visaoCampo, BorderLayout.CENTER);
        conteudos.add(populacao, BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }
    
    /**
     * Altera a cor de determinada classe de animal na simulação;
     * @param animalClass Classe de animal;
     * @param color Cor escolhida.
     */
    public void setCor(Class<?> animalClass, Color color)
    {
        cores.put(animalClass, color);
    }

    /**
     * Retorna a cor que representa determinada classe de animal na simulação;
     * @param animalClass Classe de animal;
     */
    private Color getCor(Class<?> animalClass)
    {
        Color coluna = cores.get(animalClass);
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
        
        visaoCampo.preparePaint();

        for(int row = 0; row < campo.getLargura(); row++) {
            for(int col = 0; col < campo.getLargura(); col++) {
                Object animal = campo.obterObjeto(row, col);
                if(animal != null) {
                    estatisticas.incrementaContador(animal.getClass());
                    visaoCampo.drawMark(col, row, getCor(animal.getClass()));
                }
                else {
                    visaoCampo.drawMark(col, row, COR_VAZIA);
                }
            }
        }
        estatisticas.contadorFinalizado();

        populacao.setText(PREFIXO_POPULACAO + estatisticas.obterDetalhesPopulacao(campo));
        visaoCampo.repaint();
    }
    
    /**
     * Retorna um valor booleano que indica se o campo que foi simulado é viável 
     * ou não para a manutenção das duas espécies;
     * @param campo Campo utilizado para a simulação;
     * @return True caso o campo seja viável ou false caso o campo não seja viável.
     */
    public boolean ehViavel(Campo campo)
    {
        return estatisticas.ehViavel(campo);
    }
    
    /**
     * A classe VisaoCampo é responsável pelo gerenciamento dos métodos e atributos
     * que compõem a interface gráfica da simulação;
     * - Alteração no encapsulamento da classe VisaoCampo, de private para public.
     */
    private class VisaoCampo extends JPanel
    {
		private static final long serialVersionUID = 1L;

		private final int GRID_VIEW_SCALING_FACTOR = 6;

        private int gridWidth, gridHeight;
        private int xScale, yScale;
        Dimension size;
        private Graphics g;
        private Image fieldImage;
        
        /**
         * Construtor da classe VisaoCampo;
         * @param height Altura do campo;
         * @param width Largura do campo;
         */
        public VisaoCampo(int height, int width)
        {
            gridHeight = height;
            gridWidth = width;
            size = new Dimension(0, 0);
        }
        
        /**
         * Retorna um novo valor para o dimensionamento do campo, 
         * utilizando como parâmetros o valor da multiplicação
         * entre a altura e a constante de fator de escala de
         * exibição da grade e a largura e a constante de fator 
         * de escala de exibição da grade;
         * @return novo valor para o dimensionamento
         */
        public Dimension getPreferredSize()
        {
            return new Dimension(gridWidth * GRID_VIEW_SCALING_FACTOR,
                                 gridHeight * GRID_VIEW_SCALING_FACTOR);
        }
        
        /**
         * Atribui as dimensões de escala para os valores x e y, que serão
         * utilizados para a construção do desenho na imagem.
         */
        public void preparePaint()
        {
            if(! size.equals(getSize())) {
                size = getSize();
                fieldImage = visaoCampo.createImage(size.width, size.height);
                g = fieldImage.getGraphics();

                xScale = size.width / gridWidth;
                if(xScale < 1) {
                    xScale = GRID_VIEW_SCALING_FACTOR;
                }
                yScale = size.height / gridHeight;
                if(yScale < 1) {
                    yScale = GRID_VIEW_SCALING_FACTOR;
                }
            }
        }

        /**
         * Atribui a cor e o posicionamento do quadrado na tela, que representa 
         * a posição de um javali ou quero-quero no campo;
         * @param x A posição x do quadrado a ser pintado;
         * @param y A posição y do quadrado a ser pintado; 
         * @param color A cor do quadrado. 
         */
        public void drawMark(int x, int y, Color color)
        {
            g.setColor(color);
            g.fillRect(x * xScale, y * yScale, xScale-1, yScale-1);
        }
        
        /**
         * Cria a imagem na tela;
         * @param g Imagem que será carregada.
         */
        public void paintComponent(Graphics g)
        {
            if(fieldImage != null) {
                Dimension currentSize = getSize();
                if(size.equals(currentSize)) {
                    g.drawImage(fieldImage, 0, 0, null);
                }
                else {
                    g.drawImage(fieldImage, 0, 0, currentSize.width, currentSize.height, null);
                }
            }
        }
    }
}
