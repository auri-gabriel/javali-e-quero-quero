import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;

import org.junit.jupiter.api.Test;

class JavaliTest {


	static Campo c;
	static Localizacao l, l2;
	static QueroQuero q;
	static Animal a;
	private List<Animal> novosJavalis;
	

	@Before
	void setUp() throws Exception {
		c = new Campo(50, 50);
		l = new Localizacao(26, 23);
		l2 = new Localizacao(26, 24);
		a =   new Javali(false, c, l);
		
	}


	@Test
	void testeIdade() {

		try {

			setUp();
			Field idade = a.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			System.out.println("===TEST:1===");
			System.out.println("idade: " + idade.get(a));
			Method metodo = a.getClass().getDeclaredMethod("incrementaIdade");
			metodo.setAccessible(true);
			metodo.invoke(a);
			Assert.assertEquals(1, idade.get(a));
			System.out.println("idade: " + idade.get(a));
		} catch (Exception e) {
			
			
		}

	}

	@Test
	void testeFome() {
		try {
			setUp();
			Field fome = a.getClass().getDeclaredField("nivelFome");
			fome.setAccessible(true);
			Assert.assertEquals(7, fome.get(a));
			Method incrementaFome = a.getClass().getDeclaredMethod("incrementaFome");
			incrementaFome.setAccessible(true);
			incrementaFome.invoke(a);
			Assert.assertEquals(6, fome.get(a));

		} catch (Exception e) {
			

		}
		
	}
	
	@Test 
	void morteFome(){
		try {
			setUp();
			Field fome = a.getClass().getDeclaredField("nivelFome");
			fome.setAccessible(true);
			fome.set(a, 1);
			Assert.assertEquals(1, fome.get(a));
			Method incrementaFome = a.getClass().getDeclaredMethod("incrementaFome");
			incrementaFome.setAccessible(true);
			incrementaFome.invoke(a);
			Assert.assertEquals(0, fome.get(a));
			Assert.assertFalse(a.estaVivo());
		} catch (Exception e) {
			

		}
	}

	
	@Test
	void testeProcria() {
		try {

			setUp();
			Field idade = a.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(a, 17);
			System.out.println("===TEST:5===");
			Method MPP = a.getClass().getDeclaredMethod("podeProcriar");
			MPP.setAccessible(true);
			MPP.invoke(a);
			Method MP = a.getClass().getDeclaredMethod("procria");
			MP.setAccessible(true);
			int returnNascimentos = (int) MP.invoke(a);
			System.out.println("Nascimentos: " + returnNascimentos);
			Assert.assertNotNull(returnNascimentos);
		} catch (Exception e) {

		}
	}

	@Test
	void testeProcuraComida() {
		try {
			Field fome = a.getClass().getDeclaredField("nivelFome");
			fome.setAccessible(true);
			fome.set(a, 3);
			Method metodo = a.getClass().getDeclaredMethod("procuraComida");
			metodo.setAccessible(true);
			metodo.invoke(a);
			Assert.assertEquals(7, fome.get(a));

		} catch (Exception e) {

		}
	}

	@Test
	void testeMorte() {
		try {
			setUp();
			Field vivo = a.getClass().getDeclaredField("vivo");
			vivo.setAccessible(true);
			Method metodo = a.getClass().getDeclaredMethod("setMorte");
			metodo.setAccessible(true);
			metodo.invoke(a);
			Assert.assertFalse((boolean) vivo.get(a));
			Assert.assertEquals(null, a.getLocalizacao());
			System.out.println("Localização:" + a.getLocalizacao());
		} catch (Exception e) {
			System.out.println("" + e);
		}
	}
	
	@Test
    void atividade() {
    	try {
    		setUp();
			Field idade = a.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.set(a, 19);
			Field fome = a.getClass().getDeclaredField("nivelFome");
			fome.setAccessible(true);
			a.atividade(novosJavalis);
			Assert.assertTrue(a.estaVivo());
		} catch (Exception e) {
			System.out.println("" + e);
		}
	}
	
	@Test
	void testeMorteIdade() {
		try {
			setUp();
			Field idade = a.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.set(a, 150);
			Method metodo = a.getClass().getDeclaredMethod("incrementaIdade");
			metodo.setAccessible(true);
			metodo.invoke(a);
			Assert.assertFalse(a.estaVivo());
			Assert.assertNull(c.obterObjeto(l));
		
		} catch (Exception e) {						
		}
	}
	
	@Test
	void naoPodeProcriarIdade() {
		try {
			Field idade = a.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			for (int i = 0; i < 10; i++) {
				idade.set(a, i);
			}
			Method MPP = a.getClass().getDeclaredMethod("podeProcriar");
			MPP.setAccessible(true);
			Assert.assertFalse((boolean) MPP.invoke(a));
		} catch (Exception e) {

		}
	}

	@Test
	void podeProcriar() {
		try {
			Field idade = a.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			for (int i = 10; i < 150; i++) {
				idade.set(a, i);
			}
			Method MPP = a.getClass().getDeclaredMethod("podeProcriar");
			MPP.setAccessible(true);
			Assert.assertTrue((boolean) MPP.invoke(a));
		} catch (Exception e) {

		}
	}

	@Test
	void naoPodeProcriarMorte() {
		try {
			setUp();
			Field idade = a.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.set(a, 150);
			Method MPP = a.getClass().getDeclaredMethod("podeProcriar");
			MPP.setAccessible(true);
			Assert.assertTrue((boolean) MPP.invoke(a));
			Assert.assertTrue(a.estaVivo());
			Method metodo = a.getClass().getDeclaredMethod("incrementaIdade");
			metodo.setAccessible(true);
			metodo.invoke(a);
			Assert.assertFalse(a.estaVivo());
			Assert.assertFalse((boolean) MPP.invoke(a));
			
		} catch (Exception e) {

		}
	}

}