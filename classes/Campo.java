import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * A classe Campo representa um campo delimitado bidimensional. 
 * O campo é composto por um número fixo de locais, que são 
 * organizados em linhas e colunas. Um animal pode ocupar no 
 * máximo um único local dentro do campo. Cada local no campo 
 * pode conter uma referência a um animal ou pode estar vazio.
 * 
 * Alterações:
 * - Remoção do import java.util.Iterator por não estar sendo
 * utilizado nessa classe;
 * - Renomeação de variáveis;
 * */
public class Campo
{
    private static final Random rand = Randomizador.getRandom();
    
    private int altura, largura;
    private Object[][] campo;
    
    /**
     * Construtor da classe SimuladorTela;
     * @param altura do simulador;
     * @param largura do simulador.
     */
    public Campo(int altura, int largura)
    {
        this.altura = altura;
        this.largura = largura;
        campo = new Object[altura][largura];
    }
    
    /**
     * Limpa a área total do campo
     * */
    public void limpa()
    {
        for(int linha = 0; linha < altura; linha++) {
            for(int coluna = 0; coluna < largura; coluna++) {
                campo[linha][coluna] = null;
            }
        }
    }
    
    /**
     * Limpa a área atribuída a determinado objeto da classe Localizacao;
     * @param a localização desejada;
     * */
    public void limpa(Localizacao localizacao)
    {
        campo[localizacao.getLinha()][localizacao.getColuna()] = null;
    }
    
    /**
     * Atribui a um animal uma nova posição dentro do campo;
     * @param Objeto da classe animal desejada;
     * @param uma linha do campo;
     * @param uma coluna do campo;
     * */
    public void lugar(Object animal, int linha, int coluna)
    {
        lugar(animal, new Localizacao(linha, coluna));
    }
    
    /**
     * Atribui a um animal uma nova posição dentro do campo;
     * @param Objeto da classe animal desejada;
     * @param a localização desejada;
     * */
    public void lugar(Object animal, Localizacao localizacao)
    {
        campo[localizacao.getLinha()][localizacao.getColuna()] = animal;
    }
    
    /**
     * Retorna um objeto na localização desejada;
     * @param a localização desejada;
     * */
    public Object getObjetoEm(Localizacao localizacao)
    {
        return getObjetoEm(localizacao.getLinha(), localizacao.getColuna());
    }
    
    /**
     * Retorna um objeto na localização desejada;
     * @param uma linha do campo;
     * @param uma coluna do campo;
     * */
    public Object getObjetoEm(int linha, int coluna)
    {
        return campo[linha][coluna];
    }
    
    /**
     * Captura uma localização randômica adjacente à localização passada
     * como parâmetro;
     * @param um objeto da classe Localizacao;
     * @return uma localização randomica;
     * */
    public Localizacao localizacaoAdjacenteRandomica(Localizacao localizacao)
    {
        List<Localizacao> adjacent = localizacoesAdjacentes(localizacao);
        return adjacent.get(0);
    }
    
    /**
     * Captura as localizações livres ao redor da localização passada como parâmetro;
     * @param um objeto da classe Localizacao;
     * @return uma localização randomica;
     * */
    public List<Localizacao> localizacoesAdjacentesLivres(Localizacao localizacao)
    {
        List<Localizacao> livre = new LinkedList<Localizacao>();
        List<Localizacao> adjacente = localizacoesAdjacentes(localizacao);
        for(Localizacao proximo : adjacente) {
            if(getObjetoEm(proximo) == null) {
                livre.add(proximo);
            }
        }
        return livre;
    }
    
    /**
     * Retorna uma localização livre ao redor da localização passada como parâmetro;
     * @param um objeto da classe Localizacao;
     * @return uma localização livre. Caso não haja localização livre, retorna null;
     * */
    public Localizacao localizacaoAdjacenteLivre(Localizacao localizacao)
    {
        List<Localizacao> livre = localizacoesAdjacentesLivres(localizacao);
        if(livre.size() > 0) {
            return livre.get(0);
        }
        else {
            return null;
        }
    }

    /**
     * Captura as localizações adjacentes à localização passada como parâmetro;
     * @param localização;
     * @return Uma coleção contendo as localizações adjacentes;
     * */
    public List<Localizacao> localizacoesAdjacentes(Localizacao localizacao)
    {
        assert localizacao != null : "Null localizacao passed to adjacentLocalizacoes";
        List<Localizacao> localizacoes = new LinkedList<Localizacao>();
        	int linha = localizacao.getLinha();
            int coluna = localizacao.getColuna();
            for(int linhaOffset = -1; linhaOffset <= 1; linhaOffset++) {
            	for(int colunaOffset = -1; colunaOffset <= 1; colunaOffset++) {
            		int proximaLinha = linha + linhaOffset;
            		if(proximaLinha >= 0 && proximaLinha < altura) {                  
            			int proximaColuna = coluna + colunaOffset;
                        if(proximaColuna >= 0 && proximaColuna < largura && (linhaOffset != 0 || colunaOffset != 0)) 
                            localizacoes.add(new Localizacao(proximaLinha, proximaColuna));                       
                    }
                }
            }
            Collections.shuffle(localizacoes, rand);
        return localizacoes;
    }
    
    /**
     * Captura o valor da altura do campo;
     * @return altura; 
     * */
    public int getAltura()
    {
        return altura;
    }
    
    /**
     * Captura o valor da largura do campo;
     * @return largura; 
     * */
    public int getLargura()
    {
        return largura;
    }
}
