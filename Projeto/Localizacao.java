
/**
* A classe Localizacao representa uma posição bidimensional dentro do campo.
* Sua posição é determinada por um valor de coluna e linha.
*/

public class Localizacao
{
    private int linha;
    private int coluna;

    /**
    * Construtor da classe Localizacao.
    * @param linha   numero da linha da Localizacao.
    * @param coluna  numero da coluna da Localizacao.
    */
    public Localizacao(int linha, int coluna)
    {
        this.linha = linha;
        this.coluna = coluna;
    }
    
    /**
     * Compara dois objetos do tipo Localizacao.
     * @param obj objeto que sera comparado com a localização.
     * @return true se os objeto for uma localização e estiverem na mesma localização, senão retorna false.
     */
    public boolean equals(Object obj)
    {
        if(obj instanceof Localizacao) {
        	Localizacao outra = (Localizacao) obj;
            return linha == outra.getLinha() && coluna == outra.getColuna();
        }
        else {
            return false;
        }
    }
    
    /**
     * informa uma string com linha e coluna da localização.
     * @return string com linha e coluna
     */
    public String toString()
    {
        return linha + "," + coluna;
    }
    
    /**
    * Informa um codigo hash da localização.
    * @return hashcode um codigo hash formado pela linha e coluna
    */
    public int hashCode()
    {
        return (linha << 16) + coluna;
    }
    
    /**
    * informa a linha da localização.
    * @return linha o numero da linha.
    */ 
    public int getLinha()
    {
        return linha;
    }
    
    /**
    * Informa a coluna da localização.
    * @return coluna o numero da coluna.
    */    
    public int getColuna()
    {
        return coluna;
    }
}
