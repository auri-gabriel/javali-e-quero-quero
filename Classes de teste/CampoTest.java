import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CampoTest {
	private static Campo c;
	private static Localizacao l1, l2, l3,l4,l5,l6,l7,l8,l9,l10,l11;
	//private static Animal a1, a2, a3, a4, a5, a6;
	private static Javali a1, a3, a5;
	private static QueroQuero a2, a4, a6;
	@BeforeEach
	void setUp() throws Exception {
		c = new Campo(50,50);
		
		l1 = new Localizacao(23,45);
		l2 = new Localizacao(12,27);
		l3 = new Localizacao(45,6);
		l4 = new Localizacao(16,32);
		l5 = new Localizacao(17,33);
		
		// localização minima
		l6 = new Localizacao(-1,-1);
		l7 = new Localizacao(-2,-3);
		l10 = new Localizacao(-77,-77);
		// localização maxima
		l8 = new Localizacao(51,51);
		l9 = new Localizacao(52,53);
		l11 = new Localizacao(66,66);
		
		a1 = new Javali(false,c, l1);
		a2= new QueroQuero(false,c, l2);
		
		// animal na localização minima
		a3 = new Javali(false,c, l6);
		a4= new QueroQuero(false,c, l7);
		// animal na localização maxima
		a5 = new Javali(false,c, l8);
		a6= new QueroQuero(false,c, l9);
	}

	@Test
	void getObjetoEmLocalizacao() {
		System.out.println(""+c.getObjetoEm(l1));
		Assert.assertNotNull(c.getObjetoEm(l1));
	}
	@Test
	void getObjetoEmLinhaColuna() {
		Assert.assertNotNull(c.getObjetoEm(12, 27));
		System.out.println(""+c.getObjetoEm(12, 27));
	}
	@Test
	void getObjetoEmCampoVazio() {
		Assert.assertNull(c.getObjetoEm(l3));
}
	
	@Test
	void getObjetoEmLinhaColunaVazio() {
		Assert.assertNull(c.getObjetoEm(14, 23));
	}
	
	@Test
	void Lugarlocalizacao () {
		System.out.println("Localizaco="+c.getObjetoEm(l3));
		c.lugar(a1, l3);
		Assert.assertNotNull(c.getObjetoEm(l3));
		System.out.println("Localizaco="+c.getObjetoEm(l3));		
	}
	
	@Test
	void LugarLinhaColuna() {
		System.out.println("Localizao="+c.getObjetoEm(16, 32));
		c.lugar(a2, 16, 32);
		Assert.assertNotNull(c.getObjetoEm(16, 32));
		System.out.println("Localizaco="+c.getObjetoEm(16, 32));
	}
	
	@Test
	void limpa() {
		Assert.assertNotNull(c.getObjetoEm(12,27));
		Assert.assertNotNull(c.getObjetoEm(l1));
		c.limpa();
		Assert.assertNull(c.getObjetoEm(l1));
		Assert.assertNull(c.getObjetoEm(12,27));
	}
	
	@Test
	void limpaLocalizacao() {
		Assert.assertNotNull(c.getObjetoEm(l1));
		c.limpa(l1);
		Assert.assertNull(c.getObjetoEm(l1));
	}
	
	@Test
	void localizacaoAdjacenteLivre() {
		c.lugar(a2, 16, 32);
		c.lugar(a2, 16, 31);
		c.lugar(a2, 16, 33);
		c.lugar(a2, 15, 32);
		c.lugar(a2, 15, 31);
		c.lugar(a2, 15, 33);
		c.lugar(a2, 17, 32);
		c.lugar(a2, 17, 31);
	    Assert.assertNull(c.getObjetoEm(17, 33));
		Localizacao a = c.localizacaoAdjacenteLivre(l4);
		Assert.assertEquals(a, c.localizacaoAdjacenteLivre(l4));
	}
	
	@Test
	void localizacaoAdjacenteocupadas() {
		c.lugar(a2, 16, 32);
		c.lugar(a2, 16, 31);
		c.lugar(a2, 16, 33);
		c.lugar(a2, 15, 32);
		c.lugar(a2, 15, 31);
		c.lugar(a2, 15, 33);
		c.lugar(a2, 17, 32);
		c.lugar(a2, 17, 31);
		c.lugar(a2, 17, 33);
		Assert.assertEquals(null, c.localizacaoAdjacenteLivre(l4));
	}
	
	@Test
	void localizacaoAdjacente() {
		Assert.assertNotNull(c.localizacoesAdjacentes(l5));
	}
	
	@Test
	void localizacaoAdjacenteRandomica() {
		System.out.println(""+c.localizacaoAdjacenteRandomica(l4));
		Assert.assertNotNull(c.localizacaoAdjacenteRandomica(l5));
	}
	
	@Test
	void getAltura() {
		Assert.assertEquals(50, c.getAltura());
	}
	
	@Test
	void getLargura() {
		Assert.assertEquals(50, c.getLargura());
	}
	
	@Test
	void getLarguraErro() {
		Assert.assertNotEquals(45, c.getLargura());
	}
	
	//Testes Limite Minimo
	
	@Test
	void obterLocalizacaoMinima() {
		System.out.println(""+c.getObjetoEm(l6));
		System.out.println(""+c.getObjetoEm(l7));
		//devem ser NULOS pois a localização é MENOR que o campo
		Assert.assertNull(c.getObjetoEm(l6));
		Assert.assertNull(c.getObjetoEm(l7));
	}
	
	@Test
	void getObjetoEmLinhaColunaMinima() {
		Assert.assertNotNull(c.getObjetoEm(-6, -6));
		System.out.println(""+c.getObjetoEm(-6, -6));
	}	
	
	
	@Test
	void LugarlocalizacaoMinima() {
		System.out.println("Localizaco="+c.getObjetoEm(l10));
		c.lugar(a5, l10);
		Assert.assertNull(c.getObjetoEm(l10));
		System.out.println("Localizaco="+c.getObjetoEm(l10));		
	}
	
	@Test
	void LugarLinhaColunaMinima() {
		System.out.println("Localizao="+c.getObjetoEm(-22, -32));
		c.lugar(a6, -22, -32);
		Assert.assertNotNull(c.getObjetoEm(-22, -32));
		System.out.println("Localizaco="+c.getObjetoEm(-22, -32));
	}
	
	//Testes Limite Maximo
	@Test
	void obterLocalizacaoMaxima() {	
		System.out.println(""+c.getObjetoEm(l8));
		System.out.println(""+c.getObjetoEm(l9));
		//devem ser NULOS pois a localização é MAIOR que o campo
		Assert.assertNull(c.getObjetoEm(l8));
		Assert.assertNull(c.getObjetoEm(l9));
	}

	@Test
	void getObjetoEmLinhaColunaMaxima() {
		Assert.assertNotNull(c.getObjetoEm(129, 129));
		System.out.println(""+c.getObjetoEm(129, 129));
	}	
	
	
	@Test
	void LugarlocalizacaoMaxima() {
		System.out.println("Localizaco="+c.getObjetoEm(l11));
		c.lugar(a4, l11);
		Assert.assertNull(c.getObjetoEm(l11));
		System.out.println("Localizaco="+c.getObjetoEm(l11));		
	}
	
	@Test
	void LugarLinhaColunaMaxima() {
		System.out.println("Localizao="+c.getObjetoEm(170, 256));
		c.lugar(a6, 170, 256);
		Assert.assertNotNull(c.getObjetoEm(170, 256));
		System.out.println("Localizaco="+c.getObjetoEm(170, 256));
	}
	
}