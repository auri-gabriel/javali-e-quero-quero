import java.util.Random;

/**
 * A classe Randomizador fornece um grau de controle sobre 
 * os aspectos aleatórios da simulação.
 * */
public class Randomizador
{
    private static final int SEED = 1111;
    private static final Random numRandomico = new Random();
    private static final boolean numCompartilhado = false;
    
    /**
     * Obtém um número aleatório;
     * Se o número já existir, retorna um novo;
     * */
    public static Random getRandom()
    {
        if(numCompartilhado)
            return numRandomico;     
        else 
            return new Random();
    }
    
    public static void reset()
    {
        if(numCompartilhado)
            numRandomico.setSeed(SEED);
    }
}
