package com.sheth.dp.behavioral;

/**
 * 
 * As the name suggests it creates chain of receiver objects for a given
 * request. Request is sent from one object to another across the chain till one
 * of the objects handle it
 * 
 * Handler: An interface for handling the request
 * 
 * RequestHandler: Handles request it is responsible for. If it cannot handle
 * then sends request to its successor
 *
 * Client: Sends request to first handler in chain
 * 
 */

// Client
public class ChainOfResponsibilities {
	public static void main(String args[]) {
		Logger errorLogger = new ErrorLogger(Logger.ERROR);
		Logger fileLogger = new FileLogger(Logger.DEBUG);
		Logger consoleLogger = new ConsoleLogger(Logger.INFO);

		errorLogger.setNextLogger(fileLogger);
		fileLogger.setNextLogger(consoleLogger);

		Logger logger = errorLogger;
		logger.logMessage(Logger.INFO, "INFO Message");
		logger.logMessage(Logger.DEBUG, "DEBUG Message");
		logger.logMessage(Logger.ERROR, "ERROR Message");
	}
}

// Handler
abstract class Logger {
	public static final int INFO = 1;
	public static final int DEBUG = 2;
	public static final int ERROR = 3;

	protected int currentLogLevel;
	protected Logger nextLogger;

	abstract protected void displayLogs(String log);

	public void setNextLogger(Logger nextLogger) {
		this.nextLogger = nextLogger;
	}

	public void logMessage(int logLevel, String log) {
		if (currentLogLevel == logLevel) {
			displayLogs(log);
		} else {
			nextLogger.logMessage(logLevel, log);
		}
	}
}

// RequestHandler
class ConsoleLogger extends Logger {
	ConsoleLogger(int logLevel) {
		this.currentLogLevel = logLevel;
	}

	@Override
	protected void displayLogs(String log) {
		System.out.println("Console Logger:" + log);
	}
}

// RequestHandler
class ErrorLogger extends Logger {
	ErrorLogger(int logLevel) {
		this.currentLogLevel = logLevel;
	}

	@Override
	protected void displayLogs(String log) {
		System.out.println("Error Logger:" + log);
	}
}

// RequestHandler
class FileLogger extends Logger {
	FileLogger(int logLevel) {
		this.currentLogLevel = logLevel;
	}

	@Override
	protected void displayLogs(String log) {
		System.out.println("File Logger:" + log);
	}
}