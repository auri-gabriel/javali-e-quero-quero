import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import classes.Localizacao;
import classes.Contador;

public class LocalizacaoTest {

	private Localizacao location;
	
	@Before
	public void setUp() throws Exception {
		location = new Localizacao (20,20);
	}

	@Test
	public void testHashCodeTrue() {
		try {
			Localizacao compare = new Localizacao(20,20);
			Assert.assertEquals(compare.hashCode(), location.hashCode());
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void testHashCodeFalse() {
		try {
			Localizacao compare = new Localizacao(10,10);
			Assert.assertNotEquals(compare.hashCode(), location.hashCode());
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void testEqualsObjectTrue() {
		try {
			Localizacao compare = new Localizacao(20,20);
			Assert.assertTrue(location.equals(compare));
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void testEqualsObjectFalse() {
		try {
			Localizacao compare = new Localizacao(10,10);
			Assert.assertFalse(location.equals(compare));
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void testeEqualsNotAnObject() {
		try {
			Contador counter = new Contador("test");
			Assert.assertFalse(location.equals(counter));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void testEqualsNegativeLocation() {
		try {
			Localizacao compare = new Localizacao(-20, -20);
			Assert.assertFalse(location.equals(compare));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void testToStringTrue() {
		try {
			Field line = location.getClass().getDeclaredField("linha");
			line.setAccessible(true);
			Field column = location.getClass().getDeclaredField("coluna");
			column.setAccessible(true);
			String compare = line.get(location) + "," + column.get(location);
			Assert.assertTrue(location.toString().equals(compare));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	public void testToStringFalse() {
		try {
			Field line = location.getClass().getDeclaredField("linha");
			line.setAccessible(true);
			Field column = location.getClass().getDeclaredField("coluna");
			column.setAccessible(true);
			String compare = "10" + "," + "20";
			Assert.assertFalse(location.toString().equals(compare));
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
}
