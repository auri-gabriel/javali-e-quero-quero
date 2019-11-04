/**
 * A classe Contador armazena uma contagem para auxiliar 
 * a classe CampoEstatistica 
 * Alterações: 
 * - Remoção do import java.awt.Color por não estar sendo 
 * utilizado nesta classe;
 * - Atualização do javadoc da classe;
 * - Padronização do idioma das variáveis;
 */
public class Contador
{
    private String nome;
    private int contagem;
    
    /**
     * Construtor da classe Contador que recebe o nome do 
     * contador como parâmetro e inicia a contagem com o valor 0;
     * @param nome Nome do contador.
     */
    public Contador(String nome)
    {
        this.nome = nome;
        contagem = 0;
    }
    
    /**
     * Captura o nome do contador;
     * @return nome.
     */
    public String getNome()
    {
        return nome;
    }
    
    /**
     * Captura o valor da contagem atual do contador;
     * @return contagem.
     */
    public int getContagem()
    {
        return contagem;
    }

    /**
     * Incrementa o valor da contagem atual em uma unidade;
     */
    public void increment()
    {
        contagem++;
    }
    
    /**
     * Reseta a contagem atual, atribuindo o valor 0 à variável contagem;
     */
    public void reset()
    {
        contagem = 0;
    }
}