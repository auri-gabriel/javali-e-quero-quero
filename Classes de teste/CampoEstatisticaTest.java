package teste;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import classes.*;

import java.util.HashMap;


public class CampoEstatisticaTest {
	
	/*O método de teste obterDetalhesPopulacao necessitará
	 * de correções, dependendo do nome que utilizar no seu
	 * pacote*/
	
	private QueroQuero qq;
	private CampoEstatistica fieldStat;
	private Campo field;
	private Localizacao l1, l2;
	
	@Before
	public void setUp() throws Exception {
		fieldStat = new CampoEstatistica();
		field = new Campo(100,100);
		
		l1 = new Localizacao(35,48);
		l2 = new Localizacao(15,32);
		
		qq = new QueroQuero(false, field, l2);
		new Javali(false, field, l1);
	}

	/*
	Mudança no testeObterDetalhesPopulacao para deixar mais clara a leitura dos dados
	Agora comparando a saída do progama com o número de objetos alocados no campo
	*/
	@Test
	public void testObterDetalhesPopulacao() {	
		try {		
		Method geraContadores = fieldStat.getClass().getDeclaredMethod("geraContadores", Campo.class);
		geraContadores.setAccessible(true);
		geraContadores.invoke(fieldStat, field);
		System.out.println(fieldStat.obterDetalhesPopulacao(field));
		Assert.assertEquals("classes.Javali: 1 classes.QueroQuero: 1 ",fieldStat.obterDetalhesPopulacao(field));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*
	Teste limite no caso de um Quero Quero seja retirado do campo
	*/
	@Test
	public void testObterDetalhesPopulacaoMorteQueroQuero() {		
		try {	
		qq.setMorte();
		Method geraContadores = fieldStat.getClass().getDeclaredMethod("geraContadores", Campo.class);
		geraContadores.setAccessible(true);
		geraContadores.invoke(fieldStat, field);
		Assert.assertEquals("classes.Javali: 1 ",fieldStat.obterDetalhesPopulacao(field));
		
		} catch (Exception e) {
		
		}
	}
	
	/*
	Teste No caso de adição de um novo Animal(Javali) seja instanceado
	*/
    @Test
	public void testObterDetalhesPopulacaoAdicaoJavali() {		
    	try {
		Localizacao l3 = new Localizacao(20,20);
		new Javali(false, field, l3);
		Method geraContadores = fieldStat.getClass().getDeclaredMethod("geraContadores", Campo.class);
		geraContadores.setAccessible(true);
		geraContadores.invoke(fieldStat, field);
		System.out.println(fieldStat.obterDetalhesPopulacao(field));
		Assert.assertEquals("classes.Javali: 2 classes.QueroQuero: 1 ",fieldStat.obterDetalhesPopulacao(field));
	
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
		} catch (Exception e) {
			e.printStackTrace();
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
			e.printStackTrace();
		}
	}

	@Test
	public void testEhViavel() {
		try {
			Method counters = fieldStat.getClass().getDeclaredMethod("geraContadores", Campo.class);
			counters.setAccessible(true);
			counters.invoke(fieldStat, field);
			Campo field = new Campo (100, 100);
			Localizacao locQQ = new Localizacao(20,20);
			Localizacao LocJa = new Localizacao(45,78);
			new QueroQuero(true, field, locQQ);
			new Javali(true, field, LocJa);
			Assert.assertTrue(fieldStat.ehViavel(field));			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testNaoEhViavel() {
		try {
			Field counters = fieldStat.getClass().getDeclaredField("contadores");
			counters.setAccessible(true);
			Campo field = new Campo (100, 100);
			Localizacao locQQ = new Localizacao(20,20);
			new QueroQuero(true, field, locQQ);
			Assert.assertFalse(fieldStat.ehViavel(field));			
		} catch (Exception e) {
			e.printStackTrace();
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
			new QueroQuero(true, field, loc);
			generateCounters.invoke(fieldStat, field);
			HashMap <Class<?>, Contador> counterMap = (HashMap<Class<?>, Contador>) counters.get(fieldStat); 
			Contador countQQ = counterMap.get(QueroQuero.class);
			Assert.assertEquals(countQQ.getContagem(), 1);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
