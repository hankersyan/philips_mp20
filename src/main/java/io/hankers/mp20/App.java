package io.hankers.mp20;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
	static final Logger logger = LogManager.getLogger(App.class.getName());

	public static void main(String[] args) {
		Properties props = System.getProperties();
		System.out.println("Current working directory is " + props.getProperty("user.dir"));
//		PropertyConfigurator.configure("log4j.properties");
		logger.debug("Hello World!");
		try {
			new DataReceiver().start();
		} catch (SocketException e) {
			e.printStackTrace();
			logger.error(e);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
}
