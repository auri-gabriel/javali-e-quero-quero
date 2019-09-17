
import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * 
 */

/**
 * @author User
 *
 */
class RandomizadorTest {


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}


	/**
	 * Teste pedindo dois numeros randomicos, para ver se eles são diferentes
	 */
    @Test
    public void testGetRandom() {
        Double expResult = Randomizador.getRandom().nextDouble();
        System.out.println(expResult.toString());
        Double result = Randomizador.getRandom().nextDouble();
        System.out.println(result.toString());
    
        
    }

    
    
    
    /**
     * Teste do metodo reset, da classe Randomizador.
     */
@Test
    public void testReset() {
		try {
			Field r = Randomizador.class.getDeclaredField("numCompartilhado");
			r.setAccessible(true);
			r.setBoolean(null, false);
			boolean comp = r.getBoolean(r);
			Randomizador.reset();
			Assert.assertTrue(comp);
		
		} catch (Exception e) {
			
		}

    }

}
