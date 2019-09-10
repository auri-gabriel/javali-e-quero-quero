import java.lang.reflect.Field;
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
			Field queroQueros = simulator.getClass().getDeclaredField("queroQueros");
			queroQueros.setAccessible(true);
			Field javalis = simulator.getClass().getDeclaredField("javalis");
			javalis.setAccessible(true);
			simulator.simulacao(100);
			simulator.povoa();
			List<QueroQuero> qtdQueroQueros = (List<QueroQuero>) queroQueros.get(simulator);
			List<Javali> qtdJavalis = (List<Javali>) javalis.get(simulator);
			System.out.println("Número de etapas antes da chamada ao método: " + stage.get(simulator));
			System.out.println("Número de quero-queros antes da chamada ao método: " + qtdQueroQueros.size());
			System.out.println("Número de javalis após a chamada ao método: " + qtdJavalis.size());
			simulator.reiniciaSimulacao();
			System.out.println("Número de etapas após a chamada ao método: " + stage.get(simulator));
			System.out.println("Número de quero-queros após a chamada ao método: " + qtdQueroQueros.size());
			System.out.println("Número de javalis após a chamada ao método: " + qtdJavalis.size());
			Assert.assertEquals(0, stage.get(simulator));
			Assert.assertEquals(0, qtdJavalis.size());
			Assert.assertEquals(0, qtdQueroQueros.size());
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
			Field queroQueros = simulator.getClass().getDeclaredField("queroQueros");
			queroQueros.setAccessible(true);
			Field javalis = simulator.getClass().getDeclaredField("javalis");
			javalis.setAccessible(true);
			List<QueroQuero> qtdQueroQueros = (List<QueroQuero>) queroQueros.get(simulator);
			List<Javali> qtdJavalis = (List<Javali>) javalis.get(simulator);
			System.out.println("Número de quero-queros antes da chamada ao método: " + qtdQueroQueros.size());
			System.out.println("Número de javalis após a chamada ao método: " + qtdJavalis.size());
			simulator.povoa();
			System.out.println("Número de quero-queros após a chamada ao método: " + qtdQueroQueros.size());
			System.out.println("Número de javalis após a chamada ao método: " + qtdJavalis.size());
			Assert.assertNotNull(queroQueros.get(simulator));
			Assert.assertNotNull(javalis.get(simulator));
			System.out.println("População criada com sucesso!");
			System.out.println(" ");
		}
		catch (Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}
}
