/**
* A classe Localizacao representa uma posição bidimensional dentro do campo.
* Sua posição é determinada por um valor de coluna e linha.
* 
* Alterações:
* - Atualização do javadoc;
* - Renomeação de variáveis;
*/

public class Localizacao
{
    private int linha;
    private int coluna;

    /**
    * Construtor da classe Localizacao.
    * @param linha numero da linha da Localizacao.
    * @param coluna numero da coluna da Localizacao.
    */
    public Localizacao(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }
    
    /**
     * Compara dois objetos do tipo Localizacao.
     * @param objeto que será comparado com a localização.
     * @return true se o objeto for uma localização e se estiverem na mesma localização, senão retorna false.
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Localizacao) {
        	Localizacao comparaLocalizacao = (Localizacao) obj;
            return linha == comparaLocalizacao.getLinha() && coluna == comparaLocalizacao.getColuna();
        }
        else {
            return false;
        }
    }
    
    /**
     * Informa uma string com linha e coluna da localização.
     * @return string com linha e coluna
     */
    public String toString()
    {
        return linha + "," + coluna;
    }
    
    /**
    * Informa um código hash da localização.
    * @return um código hash formado pela linha e coluna
    */
    public int hashCode()
    {
        return (linha << 16) + coluna;
    }
    
    /**
    * informa a linha da localização.
    * @return captura o valor da linha da localização.
    */ 
    public int getLinha()
    {
        return linha;
    }
    
    /**
    * Informa a coluna da localização.
    * @return captura o valor da coluna da localização.
    */    
    public int getColuna()
    {
        return coluna;
    }
}
