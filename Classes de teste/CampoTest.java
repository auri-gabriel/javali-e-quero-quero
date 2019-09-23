import static org.junit.jupiter.api.Assertions.*;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class CampoTest {
	private static Campo c;
	private static Localizacao l1, l2, l3,l4,l5;
	private static Animal a1, a2;
	@BeforeEach
	void setUp() throws Exception {
		c = new Campo(50,50);
		
		l1 = new Localizacao(23,45);
		l2 = new Localizacao(12,27);
		l3 = new Localizacao(45,6);
		l4 = new Localizacao(16,32);
		l5 = new Localizacao(17,33);
		
		a1 = new Javali(false,c, l1);
		a2= new QueroQuero(false,c, l2);
	}

	@Test
	void obterObjetoLocalizacao() {
		System.out.println(""+c.obterObjeto(l1));
		Assert.assertNotNull(c.obterObjeto(l1));
	}
	@Test
	void obterObjetoLinhaColuna() {
		Assert.assertNotNull(c.obterObjeto(12, 27));
		System.out.println(""+c.obterObjeto(12, 27));
	}
	@Test
	void obterObjetoCampoVazio() {
		Assert.assertNull(c.obterObjeto(l3));
}
	
	@Test
	void obterObjetoLinhaColunaVazio() {
		Assert.assertNull(c.obterObjeto(14, 23));
	}
	
	@Test
	void Lugarlocalizacao () {
		System.out.println("Localizaco="+c.obterObjeto(l3));
		c.lugar(a1, l3);
		Assert.assertNotNull(c.obterObjeto(l3));
		System.out.println("Localizaco="+c.obterObjeto(l3));		
	}
	
	@Test
	void LugarLinhaColuna() {
		System.out.println("Localizao="+c.obterObjeto(16, 32));
		c.lugar(a2, 16, 32);
		Assert.assertNotNull(c.obterObjeto(16, 32));
		System.out.println("Localizaco="+c.obterObjeto(16, 32));
	}
	
	@Test
	void limpa() {
		Assert.assertNotNull(c.obterObjeto(12,27));
		Assert.assertNotNull(c.obterObjeto(l1));
		c.limpa();
		Assert.assertNull(c.obterObjeto(l1));
		Assert.assertNull(c.obterObjeto(12,27));
	}
	
	@Test
	void limpaLocalizacao() {
		Assert.assertNotNull(c.obterObjeto(l1));
		c.limpa(l1);
		Assert.assertNull(c.obterObjeto(l1));
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
	    Assert.assertNull(c.obterObjeto(17, 33));
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
	
	
	
	
}