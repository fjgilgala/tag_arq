package conf;

import conf.generadores.GeneradorCodigo;

public class Test {

	@org.junit.Test
	public void testGeneradorCodigo() {
		org.junit.Assert.assertEquals("src/", GeneradorCodigo.getRutaPaquetes());
		org.junit.Assert.assertEquals("", GeneradorCodigo.getRutaPaquetesJava());
		GeneradorCodigo.modificarRutaPaquetes("teleco/espartano/");
		org.junit.Assert.assertEquals("src/teleco/espartano/", GeneradorCodigo.getRutaPaquetes());
		org.junit.Assert.assertEquals("teleco.espartano.", GeneradorCodigo.getRutaPaquetesJava());
	}

}
