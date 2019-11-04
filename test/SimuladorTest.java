import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimuladorTest {
	private Simulador simulator;
	
	@BeforeEach
	void setUp() throws Exception {
		simulator = new Simulador(50,50);
	}	
	
	
	@Test
	void testSimulacao() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
			
			Field stage = simulator.getClass().getDeclaredField("etapa");
			stage.setAccessible(true);
		
			simulator.simulacao(50);
		
			Assert.assertEquals(50, stage.get(simulator));
			System.out.println(" ");
		}
	
		
	

	@Test
	void testExecutaLongaSimulacao() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		
			Field stage = simulator.getClass().getDeclaredField("etapa");
			stage.setAccessible(true);
			Assert.assertEquals(0, stage.get(simulator));
			simulator.executaLongaSimulacao();
			int etapa = stage.getInt(simulator);
			Assert.assertEquals(etapa, stage.get(simulator));
			Assert.assertNotEquals(0, stage.get(simulator));

	
	}
	
	@Test 
	void testSimulacaoUmaEtapa() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	
			System.out.println("Teste do método simulacaoUmaEtapa: ");
			System.out.println("===================================");
			Field stage = simulator.getClass().getDeclaredField("etapa");
			stage.setAccessible(true);
			System.out.println("Número de etapas antes da chamada ao método: " + stage.get(simulator));
			simulator.simulacaoUmaEtapa();
			System.out.println("Número de etapas após a chamada ao método: " + stage.get(simulator));
			Assert.assertEquals(1, stage.get(simulator));
			System.out.println(" ");

	}
	
	@Test
	void testReiniciaSimulacao() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	
			List<Animal> animais =  new ArrayList<Animal>();
			Field stage = simulator.getClass().getDeclaredField("etapa");
			stage.setAccessible(true);
			simulator.simulacao(100);
			simulator.povoa();
			Assert.assertNotNull(animais.size());
		
			simulator.reiniciaSimulacao();

			Assert.assertEquals(0, stage.get(simulator));
			Assert.assertEquals(0, animais.size());
		
	
	}
	
	@Test
	void testPovoa() {
	
			List<Animal> animais =  new ArrayList<Animal>();
			Assert.assertEquals(0,animais.size());
			simulator.povoa();
			Assert.assertNotNull(animais.size());
		
	

	}
}
