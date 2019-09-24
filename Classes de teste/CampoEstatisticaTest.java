package teste;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import classes.*;

public class CampoEstatisticaTest {
	
	private CampoEstatistica fieldStat;
	
	@Before
	public void setUp() throws Exception {
		fieldStat = new CampoEstatistica();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testObterDetalhesPopulacao() {
		try {
			Campo field = new Campo(100, 100);
			Field counters = fieldStat.getClass().getDeclaredField("contadores");
			counters.setAccessible(true);
			HashMap <Class<?>, Contador> counterMap = (HashMap<Class<?>, Contador>) counters.get(fieldStat); 
			StringBuffer info = new StringBuffer();
			for(Class<?> key : counterMap.keySet()) {
	            Contador count = counterMap.get(key);
	            info.append(count.getNome());
	            info.append(": ");
	            info.append(count.getContagem());
	            info.append(' ');
	        }
			Assert.assertTrue(fieldStat.obterDetalhesPopulacao(field).contentEquals(info));
			
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}

	@Test
	public void testReiniciaEstatisticas() {
		try {
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

	@SuppressWarnings("unchecked")
	@Test
	public void testIncrementaContador() {
		try {
			Field counters = fieldStat.getClass().getDeclaredField("contadores");
			counters.setAccessible(true);
			HashMap <Class<?>, Contador> counterMap = (HashMap<Class<?>, Contador>) counters.get(fieldStat); 
			fieldStat.incrementaContador(Javali.class);
			Contador count = counterMap.get(Javali.class);
			Assert.assertEquals(1, count.getContagem());
			System.out.println(count.getContagem());
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}

	@Test
	public void testContadorFinalizado() {
		try {
			Field validCounters = fieldStat.getClass().getDeclaredField("contadoresValidos");
			validCounters.setAccessible(true);
			fieldStat.contadorFinalizado();
			Assert.assertEquals(true, validCounters.get(fieldStat));
		} catch (Exception e){
			System.out.println("Erro" + e);
		}
		
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testEhViavel() {
		try {
			Campo field = new Campo(100, 100);
			Field counters = fieldStat.getClass().getDeclaredField("contadores");
			counters.setAccessible(true);
			HashMap <Class<?>, Contador> counterMap = (HashMap<Class<?>, Contador>) counters.get(fieldStat); 
			int entire = 0;
			for(Class<?> key : counterMap.keySet()) {
	            Contador info = counterMap.get(key);
	            entire = info.getContagem();
	        }
			Assert.assertEquals(fieldStat.ehViavel(field), entire > 1);
			
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGeraContadores() {
		try {
			Method generateCounters = fieldStat.getClass().getDeclaredMethod("geraContadores", Campo.class);
			generateCounters.setAccessible(true);
			Field counters = fieldStat.getClass().getDeclaredField("contadores");
			counters.setAccessible(true);
			Campo field = new Campo (100, 100);
			Localizacao loc = new Localizacao(20,20);
			QueroQuero qq = new QueroQuero(true, field, loc);
			generateCounters.invoke(fieldStat, field);
			HashMap <Class<?>, Contador> counterMap = (HashMap<Class<?>, Contador>) counters.get(fieldStat); 
			Contador countQQ = counterMap.get(QueroQuero.class);
			Assert.assertEquals(countQQ.getContagem(), 1);
			
		} catch (Exception e) {
			System.out.println("Erro: " + e);
		}
	}

}
