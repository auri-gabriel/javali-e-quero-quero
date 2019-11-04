package teste;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import classes.SimuladorTela;
import java.awt.Color;
import classes.CampoEstatistica;
import classes.Javali;
import classes.Campo;

public class SimuladorTelaTest {
	
	private SimuladorTela simulator;
	private CampoEstatistica fieldStat;
	private Campo field;

	@BeforeEach
	public void setUp() throws Exception {
		simulator = new SimuladorTela(100, 100);
		System.out.println("======================");
	}
	
	@Test
	public void testMostraStatus() {
		try {
			System.out.println("Teste do método mostraStatus");
			System.out.println(" ");
			int stage = 10;
			Campo field = new Campo(100, 100);
			simulator.mostraStatus(stage, field);
			Assert.assertTrue(simulator.isVisible());
			
		} catch (Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}

	
	@Test
	public void testSetCor() {
		try {
			System.out.println("Teste do método setCor");
			System.out.println("...");
			Field colors = simulator.getClass().getDeclaredField("cores");
			colors.setAccessible(true);
			Method getCor = simulator.getClass().getDeclaredMethod("getCor", Class.class);
			getCor.setAccessible(true);
			Color corJavali = (Color) getCor.invoke(simulator, Javali.class);
			System.out.println("Cor do javali antes: " + corJavali);
			simulator.setCor(Javali.class, Color.red);
			@SuppressWarnings("unchecked")
			Map <Class <?>, Color> colorsMap = (Map<Class<?>, Color>) colors.get(simulator);
			Color color = colorsMap.get(Javali.class);
			Assert.assertEquals(Color.red.getRGB(), color.getRGB());
			System.out.println("Cor do javali após: " + color);
			
		} catch (Exception exception) {
			System.out.println("Erro: " + exception);
		}
	}
	
	@Test
	public void testGetCor() {
		try {
			System.out.println("Teste do método getCor");
			System.out.println("...");
			simulator.setCor(Javali.class, Color.red);
			Method getCor = simulator.getClass().getDeclaredMethod("getCor", Class.class);
			getCor.setAccessible(true);
			Color corJavali = (Color) getCor.invoke(simulator, Javali.class);
			Assert.assertEquals(Color.red.getRGB(), corJavali.getRGB());
			System.out.println("Cor da classe javali: " + corJavali.getAlpha());
			
		} catch (Exception exception) {
			System.out.println("Erro:" + exception);
		}
	}

	@Test
	public void testEhViavel() {
		System.out.println("Teste do método ehViavel: ");
		System.out.println("...");
		fieldStat = new CampoEstatistica();
		field = new Campo(100,100);
		Assert.assertEquals(fieldStat.ehViavel(field), simulator.ehViavel(field));
		System.out.println("Método executado de forma bem-sucedida");
	}
}