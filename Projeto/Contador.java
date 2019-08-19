/*
 * A classe Contador armazena uma contagem para auxiliar a classe CampoEstatistica 
 * @author Guilherme
 */

import java.awt.Color;

public class Contador
{
    private String name;
    private int count;
    
    /*
     * Construtor da classe Contador que recebe o nome do 
     * contador como parâmetro
     * @param nome do contador.
     */

    public Contador(String name)
    {
        this.name = name;
        count = 0;
    }
    
    /*
     * Captura o nome do contador
     * @return name
     */
    public String getName()
    {
        return name;
    }
    
    /*
     * Captura o valor da contagem atual do contador
     * @return count
     */
    public int getCount()
    {
        return count;
    }

    /*
     * Incrementa o valor da contagem atual em uma unidade
     */
    public void increment()
    {
        count++;
    }
    
    /*
     * Reseta a contagem atual, atribuindo o valor 0 à variável count
     */
    public void reset()
    {
        count = 0;
    }
}