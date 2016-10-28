package conf.core;

import java.util.Date;

import conf.util.BusinessException;
import conf.util.Escritor;

class LoggerImpl {

	private final static String fichero = "/log";
	public final static int MODE_DISCRETO = 0;
	public final static int MODE_ACTIVO = 1;
	public final static int MODE_OFF = 2;

	private static int mode = 2;

	private LoggerImpl() {
	}

	public static void log(String line) {
		switch (mode) {
		case 0:
			discreto(line);
			break;
		case 1:
			discreto(line);
			print(line);
			break;
		}
	}

	public static int getMode() {
		return mode;
	}

	public static int setMode(int mode) {
		LoggerImpl.mode = mode;
		return mode;
	}

	public static int modeDiscreto() {
		mode = MODE_DISCRETO;
		return mode;
	}

	public static int modeActivo() {
		mode = MODE_ACTIVO;
		return mode;
	}

	public static int modeOff() {
		mode = MODE_OFF;
		return mode;
	}

	private static void discreto(String line) {
		try {
			Escritor.writeLine(line, fichero);
		} catch (BusinessException e) {
			System.err.println("Existe un error al escribir el archivo log");
		}
	}

	private static void print(String line) {
		System.out.println("[" + new Date() + "] Log Arq_Core: " + line);
	}

}
