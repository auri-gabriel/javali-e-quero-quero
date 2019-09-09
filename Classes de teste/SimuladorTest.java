import java.lang.reflect.Field;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimuladorTest {
	static Simulador simulator;
	
	@BeforeEach
	void setUp() throws Exception {
		simulator = new Simulador(50,50);
	}	
	
	
	@Test
	void testSimulacao() {
		try {
			System.out.println("Teste do método simulacao: ");
			System.out.println("===========================");
			Field stage = simulator.getClass().getDeclaredField("etapa");
			stage.setAccessible(true);
			System.out.println("Número de etapas antes da chamada ao método: " + stage.get(simulator));
			simulator.simulacao(50);
			System.out.println("Número de etapas após a chamada ao método: " + stage.get(simulator));
			Assert.assertEquals(50, stage.get(simulator));
			System.out.println(" ");
		}
		catch (Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}

	@Test
	void testExecutaLongaSimulacao() {
		try {
			System.out.println("Teste do método executaLongaSimulacao: ");
			System.out.println("=======================================");
			Field stage = simulator.getClass().getDeclaredField("etapa");
			stage.setAccessible(true);
			System.out.println("Número de etapas antes da chamada ao método: " + stage.get(simulator));
			simulator.executaLongaSimulacao();
			System.out.println("Número de etapas após a chamada ao método: " + stage.get(simulator));
			Assert.assertEquals(500, stage.get(simulator));
			System.out.println(" ");
		} 
		catch(Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}
	
	@Test 
	void testSimulacaoUmaEtapa() {
		try {
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
		catch (Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}
	
	@Test
	void testReiniciaSimulacao() {
		try {
			System.out.println("Teste do método reiniciaSimulacao: ");
			System.out.println("===================================");
			Field stage = simulator.getClass().getDeclaredField("etapa");
			stage.setAccessible(true);
			simulator.simulacao(100);
			System.out.println("Número de etapas antes da chamada ao método: " + stage.get(simulator));
			simulator.reiniciaSimulacao();
			System.out.println("Número de etapas após a chamada ao método: " + stage.get(simulator));
			Assert.assertEquals(0, stage.get(simulator));
			System.out.println(" ");
		}
		catch (Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}
	
	@Test
	void testPovoa() {
		try {
			System.out.println("Teste do método povoa: ");
			System.out.println("=======================");
			Field javaliCollection = simulator.getClass().getDeclaredField("javalis");
			javaliCollection.setAccessible(true);
			Field queroQuerosCollection = simulator.getClass().getDeclaredField("queroQueros");
			queroQuerosCollection.setAccessible(true);
			System.out.println("Número de quero-queros antes da chamada ao método: ");
		}
		catch (Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}
}
