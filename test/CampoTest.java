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
		
		// localizao minima
		
		//l6 = new Localizacao(-1,-1); // valor minimo -1
		l7 = new Localizacao(0,0); // valor minimo
		l8 = new Localizacao(1,1); // valor minimo +1
		
		// localizao maxima
		
		l9 = new Localizacao(48,48); // valor maximo -1
		l10 = new Localizacao(49,49); // valor maximo
		//l11 = new Localizacao(50,50); // valor maximo +1
		
		a1 = new Javali(false,c, l1);
		a2= new QueroQuero(false,c, l2);
		
		// animal na localizao minima
		a3 = new Javali(false,c, l7);
		a4= new QueroQuero(false,c, l8);
		// animal na localizao maxima
		a5 = new Javali(false,c, l9);
		a6= new QueroQuero(false,c, l10);
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
		System.out.println(""+c.getObjetoEm(l7));
		System.out.println(""+c.getObjetoEm(l8));
		Assert.assertNotNull(c.getObjetoEm(l7));
		Assert.assertNotNull(c.getObjetoEm(l8));
	}
	
/*	@Test
	void getObjetoEmLinhaColunaMinimaAnterior() {
		Assert.assertNull(c.getObjetoEm(-1, -1));
		System.out.println(""+c.getObjetoEm(-1, -1));
	}	
*/	
	
	@Test
	void getObjetoEmLinhaColunaMinima() {
		Assert.assertNotNull(c.getObjetoEm(0, 0));
		System.out.println(""+c.getObjetoEm(0, 0));
	}	
	
	@Test
	void getObjetoEmLinhaColunaMinimaSeguinte() {
		Assert.assertNotNull(c.getObjetoEm(1, 1));
		System.out.println(""+c.getObjetoEm(1, 1));
	}	
	
	
	@Test
	void LugarlocalizacaoMinima() {
		System.out.println("Localizaco="+c.getObjetoEm(l7));
		c.lugar(a3, l7);
		Assert.assertNotNull(c.getObjetoEm(l7));
		System.out.println("Localizaco="+c.getObjetoEm(l7));		
	}
/*
	@Test
	void LugarLinhaColunaMinimaAnterior() {
		System.out.println("Localizao="+c.getObjetoEm(-1, -1));
		c.lugar(a6, -1, -1);
		Assert.assertNull(c.getObjetoEm(-1, -1));
		System.out.println("Localizaco="+c.getObjetoEm(-1, -1));
	}
*/		
	
	@Test
	void LugarLinhaColunaMinima() {
		System.out.println("Localizao="+c.getObjetoEm(0, 0));
		c.lugar(a6, 0, 0);
		Assert.assertNotNull(c.getObjetoEm(0, 0));
		System.out.println("Localizaco="+c.getObjetoEm(0, 0));
	}
	
	@Test
	void LugarLinhaColunaMinimaSeguinte() {
		System.out.println("Localizao="+c.getObjetoEm(1, 1));
		c.lugar(a6, 1, 1);
		Assert.assertNotNull(c.getObjetoEm(1, 1));
		System.out.println("Localizaco="+c.getObjetoEm(1, 1));
	}
		
	
	//Testes Limite Maximo
	@Test
	void obterLocalizacaoMaxima() {	
		System.out.println(""+c.getObjetoEm(l9));
		System.out.println(""+c.getObjetoEm(l10));
		Assert.assertNotNull(c.getObjetoEm(l9));
		Assert.assertNotNull(c.getObjetoEm(l10));
	}

	@Test
	void getObjetoEmLinhaColunaMaximaAnterior() {
		Assert.assertNotNull(c.getObjetoEm(48, 48));
		System.out.println(""+c.getObjetoEm(48, 48));
	}	
	
	@Test
	void getObjetoEmLinhaColunaMaxima() {
		Assert.assertNotNull(c.getObjetoEm(49, 49));
		System.out.println(""+c.getObjetoEm(49, 49));
	}	

/*	@Test
	void getObjetoEmLinhaColunaMaximaSeguinte() {
		Assert.assertNull(c.getObjetoEm(50, 50));
		System.out.println(""+c.getObjetoEm(50, 50));
	}	
*/	
	@Test
	void LugarlocalizacaoMaxima() {
		System.out.println("Localizaco="+c.getObjetoEm(l9));
		c.lugar(a4, l9);
		Assert.assertNotNull(c.getObjetoEm(l9));
		System.out.println("Localizaco="+c.getObjetoEm(l9));		
	}

	@Test
	void LugarLinhaColunaMaximaAnterior() {
		System.out.println("Localizao="+c.getObjetoEm(48, 48));
		c.lugar(a6, 48, 48);
		Assert.assertNotNull(c.getObjetoEm(48, 48));
		System.out.println("Localizaco="+c.getObjetoEm(48, 48));
	}
		
	
	@Test
	void LugarLinhaColunaMaxima() {
		System.out.println("Localizao="+c.getObjetoEm(49, 49));
		c.lugar(a6, 49, 49);
		Assert.assertNotNull(c.getObjetoEm(49, 49));
		System.out.println("Localizaco="+c.getObjetoEm(49, 49));
	}
/*
	@Test
	void LugarLinhaColunaMaximaSeguinte() {
		System.out.println("Localizao="+c.getObjetoEm(50, 50));
		c.lugar(a6, 50, 50);
		Assert.assertNull(c.getObjetoEm(50, 50));
		System.out.println("Localizaco="+c.getObjetoEm(50, 50));
	}
*/	

    /* 
    Teste referente ao método localizacaoAdjacenteLivre usando o limite maximo do campo
    Esse teste deve retornar somente os valores válidos do campo,ou seja
    As localizações adjacentes retornadas devem ser menores do que  50,50
    Neste caso o teste só retornara somente uma das localizações pois as outras possuim um objeto animal instaceado
    Mas notase que o sistema só reconhece as localalizações abaxo de 50,50
    */
    @Test
	void limiteAdjacenteMaximo() {
	c.lugar(a1, l6);
	c.lugar(a1, 49,48);
	c.lugar(a1, 48,48);
	Localizacao lugar = new Localizacao(48,49);
	Assert.assertEquals(lugar, c.localizacaoAdjacenteLivre(l6));
    
    
    
    
    /*
    Mesmo caso do teste anterior ms agora utilizando a localização minima
    As locazacões retornadas devem ser maiores do que 0,0;
    Neste caso o teste só retornara somente uma das localizações pois as outras possuim um objeto animal instaceado
    Neste caso o teste só retornara somente uma das localizações pois as outras possuim um objeto animal instaceado
    Mas notase que o sistema só reconhece as localalizações acima  de 0,0;
    */
}
    @Test
	void limiteAdjacenteMinimo() {
	c.lugar(a1, l7);
	c.lugar(a2, 0,1);
	c.lugar(a1, 1,0);
	Localizacao lugar = new Localizacao(1,1);
	Assert.assertEquals(lugar, c.localizacaoAdjacenteLivre(l7));
	}
