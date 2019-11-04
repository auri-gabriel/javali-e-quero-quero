import classes.Randomizador;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;

public class RandomizadorTest {

	@Test
	public void testGetRandomDefault() {
		try {
			ArrayList<Double> expected = new ArrayList<>();
			ArrayList<Double> actual = new ArrayList<>();
			
			for (int i = 0; i < 5; i++) {
				expected.add(Randomizador.getRandom().nextDouble());}
			
			
			for(int i = 0; i < 5; i++) {
				actual.add(Randomizador.getRandom().nextDouble());}
						
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
			
			Randomizador.reset();
			for(int j = 0; j < 5; j++) {
				actual.add(Randomizador.getRandom().nextDouble());}
			
			Randomizador.reset();
			for(int i = 0; i < 5; i++) {
				expected.add(Randomizador.getRandom().nextDouble());}
			
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
