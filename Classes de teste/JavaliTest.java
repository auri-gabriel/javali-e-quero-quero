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
    // MÉTODO DE TESTE PARA CONFERIR SE A IDADE DO JAVALI ESTÁ AUMENTANTO COM O TEMPO
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
    // TESTE PARA CONFERIR SE O NIVEL DE FOME DO JAVALI ESTÁ BAIXANDO
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
	// TESTE APRA VER O STATUS DE VIDA DO JAVALI
	@Test
	void testEstaVivo() {
		Assert.assertTrue(j.estaVivo());
	}
    // TESTE PARA VERIFICAR A LOCALIZAÇÃO DO JAVALI
	@Test
	void testGetLocalizacao() {
		Assert.assertEquals("23,26", j.getLocalizacao());
	}
	// MÉTODO DE TESTE PARA VERIFICAR SE O JAVALI ESTÁ NA IDADE DE PROCIRAÇÃO
	@Test
	void testeProcria() {
		try {
			Field idade = j.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(j, 78);
			System.out.println("===TEST:3===");
			System.out.println("idade: "+idade.get(j));
			Method metodo = j.getClass().getDeclaredMethod("podeProcriar");
			metodo.setAccessible(true);
			Assert.assertTrue((boolean) metodo.invoke(j));
			System.out.println("Já está na idade de procriação");

			
			}
		catch(Exception e) {
		System.out.print(""+e);
		}
		
	}
	// TESTE DO MÉTODO SETMORTE 
	@Test
	void testeMorte() {
		try {
			setUp();
			Field vivo = j.getClass().getDeclaredField("vivo");
			vivo.setAccessible(true);
			System.out.println("===TEST:2===");
			System.out.println("Status:" + vivo.get(j));
			System.out.println("Localização:" + j.getLocalizacao());
			Method metodo = j.getClass().getDeclaredMethod("setMorte");
			metodo.setAccessible(true);
			metodo.invoke(j);
			Assert.assertFalse((boolean) vivo.get(j));
			System.out.println("Status:" + vivo.get(j));
			Assert.assertEquals(null, j.getLocalizacao());
			System.out.println("Localização:" + j.getLocalizacao());
		} catch (Exception e) {
			System.out.println("" + e);
		}
	}
    //  TESTE DA CLASSE PROCRIA
    void testeProcria(){
		try {
		
			setUp();
			Field idade = j.getClass().getDeclaredField("idade");
			idade.setAccessible(true);
			idade.setInt(j, 16);
			System.out.println("==TEST:5===");
			Method MPP = j.getClass().getDeclaredMethod("podeProcriar");
			MPP.setAccessible(true);
			MPP.invoke(j);
			Method MP = j.getClass().getDeclaredMethod("procria");
			MP.setAccessible(true);
			MP.invoke(j);
			int returnNascimentos = (int) MP.invoke(j);
			System.out.println("Nascimentos: "+returnNascimentos);
			Assert.assertNotNull(returnNascimentos);
		}
		catch(Exception e) {
			
		}
	}



}
