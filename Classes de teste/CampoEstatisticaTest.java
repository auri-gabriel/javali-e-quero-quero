package teste;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

}
