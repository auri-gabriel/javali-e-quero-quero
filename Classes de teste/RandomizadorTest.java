package teste;

import classes.Randomizador;
import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class RandomizadorTest {

	private Randomizador random;
	@Before
	public void setUp() throws Exception {
		random = new Randomizador();
	}

	@Test
	public void testGetRandomDefault() {
		try {
			ArrayList<Double> expected = new ArrayList<>();
			ArrayList<Double> actual = new ArrayList<>();
			
			for (int i = 0; i < 5; i++) {
				expected.add(Randomizador.getRandom().nextDouble());
			}
			
			for(int i = 0; i < 5; i++) {
				actual.add(Randomizador.getRandom().nextDouble());
			}
			
			System.out.println("=================");
			
			Assert.assertFalse(expected.containsAll(actual));
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void testGetRandomWithReset() {
		try {
			ArrayList<Double> expected = new ArrayList<>();
			ArrayList<Double> actual = new ArrayList<>();
			
			for(int j = 0; j < 5; j++) {
				actual.add(Randomizador.getRandom().nextDouble());
			}
						
			Randomizador.reset();
						
			for(int i = 0; i < 6; i++) {
				expected.add(Randomizador.getRandom().nextDouble());
			}

			
			Assert.assertTrue(expected.containsAll(actual));
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void testReset() {
		try {
			Double expected = Randomizador.getRandom().nextDouble();
			Randomizador.reset();
	        Double result = Randomizador.getRandom().nextDouble();
	        Assert.assertEquals(expected, result);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
