import java.util.Random;

/**
* A classe Randomizador fornece um grau de controle sobre os aspectos aleatórios da simulação.
*/
public class Randomizador
{
    private static final int SEED = 1111;
    private static final Random rand = new Random(SEED);
    private static final boolean useShared = true;

    /**
    * Construtor da classe Randomizador.
    */
    public Randomizador()
    {

    }
    
    /**
     * Fornece um número aleatório.
     * @return um número aleatório.
     */
    public static Random getRandom()
    {
        if(useShared) {
            return rand;
        }
        else {
            return new Random();
        }
    }
    
    /** Reseta o objeto
    *   se useShared é verdadeiro, o objeto rand volta a usar a variável SEED como Seed.
    */
    public static void reset()
    {
        if(useShared) {
            rand.setSeed(SEED);
        }
    }
}
