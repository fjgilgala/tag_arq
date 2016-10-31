package conf.util;

import java.util.Date;

public class LoggerImpl {

	private final static String fichero = "log";
	public final static int MODE_DISCRETO = 0;
	public final static int MODE_ACTIVO = 1;
	public final static int MODE_OFF = 2;

	private static int mode = 0;

	private static int contador = 0;

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
		synchronized (line) {
			try {
				Escritor.writeLine(formatLine(line) + "\n", fichero);
			} catch (BusinessException e) {
				System.err.println("Existe un error al escribir el archivo log");
			}
		}
	}

	private static void print(String line) {
		System.out.println(formatLine(line));
	}

	private static String formatLine(String line) {
		return "[ID:" + contador++ + "][" + new Date() + "] Log Arq_Core: " + line;
	}

}
