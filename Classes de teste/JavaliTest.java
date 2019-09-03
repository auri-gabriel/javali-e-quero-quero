import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class JavaliTest {

	static Javali j;
	static Campo c;
	static Localizacao l;

	@Before
	void setUp() throws Exception {
		c = new Campo(50, 50);
		l = new Localizacao(26, 23);
		j = new Javali(false, c, l);

	}

	@Test
	void testeIdade() {

		try {
			setUp();
			Field idade = j.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			System.out.println("===TEST:1===");
			System.out.println("idade: " + idade.get(j));
			Method metodo = j.getClass().getDeclaredMethod("incrementaIdade");
			metodo.setAccessible(true);
			metodo.invoke(j);
			Assert.assertEquals(1, idade.get(j));
			System.out.println("idade: " + idade.get(j));
		} catch (Exception e) {
			System.out.print("" + e);
			;
		}

	}

	@Test
	void testeFome() {
		try {
			setUp();
			Field fome = j.getClass().getDeclaredField("nivelFome");
			fome.setAccessible(true);
			System.out.println("===TEST:2===");
			System.out.println("Fome: " + fome.get(j));
			Method metodo = j.getClass().getDeclaredMethod("incrementaFome");
			metodo.setAccessible(true);
			metodo.invoke(j);
			
			Assert.assertEquals(6, fome.get(j));
			System.out.println("Fome: "+fome.get(j));
		} catch (Exception e) {
			System.out.print("" + e);
			
		}

	}
	
	@Test
	void testEstaVivo() {
		Assert.assertTrue(j.estaVivo());
	}

	@Test
	void testGetLocalizacao() {
		Assert.assertEquals("23,26", j.getLocalizacao());
	}

}
