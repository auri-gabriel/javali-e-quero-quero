package teste;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import classes.Campo;
import classes.CampoEstatistica;
import classes.Contador;

public class CampoEstatisticaTest {
	
	private CampoEstatistica fieldStat;
	
	@Before
	public void setUp() throws Exception {
		fieldStat = new CampoEstatistica();
		System.out.println("===================");
	}

	@Test
	public void testObterDetalhesPopulacao() {
		try {
			System.out.println("Teste do método obterDetalhesPopulacao:");
			System.out.println(" ");
			
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}

	@Test
	public void testReiniciaEstatisticas() {
		try {
			System.out.println("Teste do método reiniciaEstatisticas:");
			System.out.println(" ");
			Contador counter = new Contador("counter");
			Field score = counter.getClass().getDeclaredField("contagem");
			score.setAccessible(true);
			counter.reset();
			Field validCounters = fieldStat.getClass().getDeclaredField("contadoresValidos");
			validCounters.setAccessible(true);
			fieldStat.reiniciaEstatisticas();
			Assert.assertEquals(false, validCounters.get(fieldStat));
			Assert.assertEquals(0, score.get(counter));
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
		
	}

	@Test
	public void testIncrementaContador() {
		try {
			System.out.println("Teste do método incrementaContador:");
			System.out.println(" ");
			Contador counter = new Contador("counter");
			Field score = counter.getClass().getDeclaredField("contagem");
			score.setAccessible(true);
			score.setInt(counter, 0);
			counter.increment();
			Assert.assertEquals(1, score.get(counter));
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}

	@Test
	public void testContadorFinalizado() {
		try {
			System.out.println("Teste do método contadorFinalizado:");
			System.out.println(" ");
			Field validCounters = fieldStat.getClass().getDeclaredField("contadoresValidos");
			validCounters.setAccessible(true);
			fieldStat.contadorFinalizado();
			Assert.assertEquals(true, validCounters.get(fieldStat));
		} catch (Exception e){
			System.out.println("Erro" + e);
		}
		
	}

	@Test
	public void testEhViavel() {
		try {
			System.out.println("Teste do método ehViavel");
			System.out.println(" ");
			Field counters = fieldStat.getClass().getDeclaredField("contadores");
			counters.setAccessible(true);
			
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
	
	@Test
	public void testGeraContadores() {
		try {
			System.out.println("Teste do método geraContadores:");
			System.out.println(" ");
			Field validCounters = fieldStat.getClass().getDeclaredField("contadoresValidos");
			validCounters.setAccessible(true);
			Method generateCounters = fieldStat.getClass().getDeclaredMethod("geraContadores", Campo.class);
			generateCounters.setAccessible(true);
			Campo field = new Campo (100, 100);
			generateCounters.invoke(fieldStat, field);
			Assert.assertEquals(true, validCounters.get(fieldStat));
			
			
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}

}
