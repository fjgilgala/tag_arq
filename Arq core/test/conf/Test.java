package conf;

import conf.generadores.UtilGenerador;

public class Test {

	@org.junit.Test
	public void testGeneradorCodigo() {
		org.junit.Assert.assertEquals("src/", UtilGenerador.getRutaPaquetes());
		org.junit.Assert.assertEquals("", UtilGenerador.getRutaPaquetesJava());
		UtilGenerador.modificarRutaPaquetes("teleco/espartano/");
		org.junit.Assert.assertEquals("src/teleco/espartano/", UtilGenerador.getRutaPaquetes());
		org.junit.Assert.assertEquals("teleco.espartano.", UtilGenerador.getRutaPaquetesJava());
	}
}
