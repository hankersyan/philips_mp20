package io.hankers.mp20;

import java.io.FileInputStream;
import java.io.IOException;
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
	static final Logger _logger = LogManager.getLogger(App.class.getName());
	static String _monitorIp;
	static String _mqttHost;
	static String _mqttPort;
	static String _mqttUser;
	static String _mqttPassword;
	static String _mqttTopic;
	static String _mqttQueueLimit;

	public static void main(String[] args) {
		readConfig();

		try {
			new DataReceiver().start();
		} catch (SocketException e) {
			e.printStackTrace();
			_logger.error(e);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			_logger.error(e);
		}
	}

	static void readConfig() {
		Properties props = new Properties();
		try {
			_logger.debug("user.dir=" + props.getProperty("user.dir"));
			props.load(App.class.getClassLoader().getResourceAsStream("config.properties"));

			_logger.debug("default loaded: " + props);

			String configFile = System.getProperty("config.properties");
			if (configFile != null) {
				props.load(new FileInputStream(configFile));
				_logger.debug("custom config loaded from " + configFile);
			}

			_monitorIp = props.getProperty("monitor.ip");
			_mqttHost = props.getProperty("mqtt.host");
			_mqttPort = props.getProperty("mqtt.port");
			_mqttUser = props.getProperty("mqtt.user");
			_mqttPassword = props.getProperty("mqtt.password");
			_mqttTopic = props.getProperty("mqtt.topic");
			_mqttQueueLimit = props.getProperty("mqtt.queue.limit");

			_logger.debug("Custom override: " + props);
		} catch (IOException e1) {
			_logger.error("Error in main", e1);
		}
	}
}
