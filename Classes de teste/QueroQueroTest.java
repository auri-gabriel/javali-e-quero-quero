import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class QueroQueroTest {
	
	private Campo c;
	private QueroQuero q;
	private Javali j;	
	private List<QueroQuero> novosQueroQueros;

	@BeforeEach
	void setUp() throws Exception {

		c = new Campo(50, 50);
		Localizacao l = new Localizacao(35, 12);
		Localizacao l2 = new Localizacao(36, 11);
		q = new QueroQuero(false, c, l);
		novosQueroQueros =  new ArrayList<QueroQuero>(); 
	}

	@Test
	void testPodeProcriar() {
		try {
			setUp();
			Field idade = q.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(q, 9);
			Method metodo = q.getClass().getDeclaredMethod("podeProcriar");
			metodo.setAccessible(true);
			Assert.assertTrue((boolean) metodo.invoke(q));
		} catch (Exception e) {
			System.out.print("" + e);
		}
	}

	@Test
	void testNaopodeProcriar() {
		try {
			setUp();
			Field idade = q.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(q, 2);
			Method metodo = q.getClass().getDeclaredMethod("podeProcriar");
			metodo.setAccessible(true);
			metodo.invoke(q);
			Assert.assertFalse((boolean) metodo.invoke(q));

		} catch (Exception e) {
			System.out.println("" + e);
		}
	}

	@Test
	void testEstaVivo() {
		Assert.assertTrue(q.estaViva());
	}
	
	@Test
	void testSetMorte() {
		try {
			Method metodo =q.getClass().getDeclaredMethod("setMorte");
			metodo.setAccessible(true);
			metodo.invoke(q);
			Assert.assertNull(q.getLocalizacao());
		}catch(Exception e) {
			System.out.println(""+e);
		}
	}
	
	@Test
		void testNaoestaVivoIdade() {
		try {
			Field idade = q.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(q, 40);
			System.out.println("idade: "+idade.get(q));
			Method metodo = q.getClass().getDeclaredMethod("incrementaIdade");
			metodo.setAccessible(true);
			metodo.invoke(q);
			Assert.assertFalse(q.estaViva());
			System.out.println("idade:"+idade.get(q));
		}catch(Exception e) {
			System.out.println(""+e);
		}
	}

	@Test
	void testProcria() {
		try {
			setUp();
			Field idade = q.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(q, 6);
			Method procria = q.getClass().getDeclaredMethod("podeProcriar");
			procria.setAccessible(true);
			procria.invoke(q);
			Method MP = q.getClass().getDeclaredMethod("procria");
			MP.setAccessible(true);
			int returnNascimentos = (int) MP.invoke(q);
			System.out.println("Nascimentos: " + returnNascimentos);
			Assert.assertNotNull(returnNascimentos);

		} catch (Exception e) {
			System.out.println("" + e);
		}
	}
	
	@Test
	void testProcriaIdadeIdadeMenor() {
		try {
			setUp();
			Field idade = q.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(q, 2);
			Method procria = q.getClass().getDeclaredMethod("podeProcriar");
			procria.setAccessible(true);
			procria.invoke(q);
			Method MP = q.getClass().getDeclaredMethod("procria");
			MP.setAccessible(true);
			int returnNascimentos = (int) MP.invoke(q);
			System.out.println("Nascimentos: " + returnNascimentos);
			Assert.assertEquals(0,returnNascimentos);

		} catch (Exception e) {
			
			System.out.println("" + e);
		}
	}
	@Test
	void testVoa() {
		System.out.println(""+q.getLocalizacao());
		q.voa(novosQueroQueros);
		System.out.println(""+q.getLocalizacao());
		Assert.assertTrue(q.estaViva());
		
	
	}
}
