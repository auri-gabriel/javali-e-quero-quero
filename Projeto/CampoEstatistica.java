import java.util.HashMap;

/**
 * A classe CampoEstatistica fornece a contagem do número 
 * de javalis e quero-queros no campo para a visualização.
 * 
 * Alterações: 
 * - Remoção do import java.awt.Color por não estar sendo 
 * utilizado nesta classe;
 * - Reestruturação do javadoc;
 * - Padronização do idioma das variáveis;
 * - Renomeação de variáveis.
 * */
public class CampoEstatistica
{
    private HashMap<Class <?>, Contador> contadores;
    private boolean contadoresValidos;
    
    /**
     * Construtor da classe CampoEstatistica.
     */
    public CampoEstatistica()
    {
        contadores = new HashMap<Class <?>, Contador>();
        contadoresValidos = true;
    }
    
    /**
     * Informa os detalhes da população dentro da simulação
     * fornecendo nome e quantidade dos objetos dentro dela.
     * @param campo referente à simulação;
     * @return um buffer de String contendo os dados da população
     * dentro do campo referente à simulação.
     * */
    public String obterDetalhesPopulacao(Campo campo)
    {
        StringBuffer buffer = new StringBuffer();
        if(!contadoresValidos) {
            geraContadores(campo);
        }
        for(Class<?> chave : contadores.keySet()) {
            Contador info = contadores.get(chave);
            buffer.append(info.getNome());
            buffer.append(": ");
            buffer.append(info.getContagem());
            buffer.append(' ');
        }
        return buffer.toString();
    }
    
    /**
     * Reinicia as estatísticas do campo simulado;
     * */
    public void reiniciaEstatisticas()
    {
        contadoresValidos = false;
        for(Class<?> chave : contadores.keySet()) {
            Contador contador = contadores.get(chave);
            contador.reset();
        }
    }
    
    /**
     * Incrementa o contador de indivíduos de determinada classe
     * de animal;
     * @param o tipo de classe de animal (queroquero ou javali)
     * */
    public void incrementaContador(Class<?> animalClass)
    {
        Contador contador = contadores.get(animalClass);
        if(contador == null) {
            contador = new Contador(animalClass.getName());
            contadores.put(animalClass, contador);
        }
        contador.increment();
    }
    
    /**
     * Reinicia as estatísticas do campo simulado;
     * */
    public void contadorFinalizado()
    {
        contadoresValidos = true;
    }
    
    /**
     * Verifica se as condições do campo simulado são viáveis para
     * manutenção de ambas as espécies de animais;
     * @param o objeto que representa o campo simulado;
     * @return true se houverem pelo menos um representante de cada
     * espécie, caso contrário, retorna false.
     * */
    public boolean ehViavel(Campo campo)
    {
        int nonZero = 0;
        if(!contadoresValidos) {
            geraContadores(campo);
        }
        for(Class<?> key : contadores.keySet()) {
            Contador info = contadores.get(key);
            if(info.getContagem() > 0) {
                nonZero++;
            }
        }
        return nonZero > 1;
    }
    
    /**
     * Inicia o contador de estatistica dentro da simulação, o incrementando
     * proporcionalmente ao número de animais presente na simulação;
     * @param campo referente à simulação;
     * */
    private void geraContadores(Campo campo)
    {
        reiniciaEstatisticas();
        for(int linha = 0; linha < campo.getLargura(); linha++) {
            for(int coluna = 0; coluna < campo.getLargura(); coluna++) {
                Object animal = campo.obterObjeto(linha, coluna);
                if(animal != null) {
                    incrementaContador(animal.getClass());
                }
            }
        }
        contadoresValidos = true;
    }
}
