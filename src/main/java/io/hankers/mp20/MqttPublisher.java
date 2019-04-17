package io.hankers.mp20;

import java.net.URISyntaxException;
import java.util.concurrent.SynchronousQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fusesource.hawtbuf.AsciiBuffer;
import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.FutureConnection;
import org.fusesource.mqtt.client.MQTT;
import org.fusesource.mqtt.client.QoS;

public class MqttPublisher {

	static String user;// = "client01";// env("ACTIVEMQ_USER", "admin");
	static String password;// = "password";// env("ACTIVEMQ_PASSWORD", "password");
	static String host;// = "iot.meehealth.com";// env("ACTIVEMQ_HOST", "localhost");
	static int port;// = 1883;// Integer.parseInt(env("ACTIVEMQ_PORT", "1883"));

	//static final String destination;// = "test/topic";// arg(args, 0, "/topic/event");
	static UTF8Buffer TOPIC;// = new UTF8Buffer(destination);

	// queue limit
	static int LIMIT = 10000;
	static final Logger _logger = LogManager.getLogger(MqttPublisher.class.getName());
	static MQTT _mqtt;
	static FutureConnection _connection;
	static boolean _initSuccess;
	static final SynchronousQueue<Buffer> _msgQueue = new SynchronousQueue<Buffer>();

	static {
		init();
	}

	private static void init() {
		user = App._mqttUser;
		password = App._mqttPassword;
		host = App._mqttHost;
		port = Integer.valueOf(App._mqttPort, 1883);
		TOPIC = new UTF8Buffer(App._mqttTopic);
		LIMIT = Integer.valueOf(App._mqttQueueLimit, 10000);
		
		_mqtt = new MQTT();
		while (true) {
			try {
				_mqtt.setHost(host, port);
				_mqtt.setUserName(user);
				_mqtt.setPassword(password);

				_connection = _mqtt.futureConnection();
				_connection.connect().await();

				_initSuccess = true;
				new Worker().start();

				break;
			} catch (URISyntaxException e) {
				e.printStackTrace();
				_logger.error(e);
			} catch (Exception e) {
				e.printStackTrace();
				_logger.error(e);
			}
			// try again
			try {
				Thread.sleep(6000);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
				_logger.error(e1);
			}
		}
	}

	public static void addMessage(String message) {
		Buffer msg = new AsciiBuffer(message);

		try {
			_msgQueue.put(msg);
		} catch (InterruptedException e) {
			e.printStackTrace();
			_logger.error("Error in addMessage", e);
		}

		if (_msgQueue.size() > LIMIT) {
			_logger.warn("Dropping {} elements from msg queue", _msgQueue.size() - LIMIT);
			while (_msgQueue.size() > LIMIT) {
				_msgQueue.remove();
			}
		}
	}

	static class Worker extends Thread {
		public void run() {
			Buffer msg;
			while (!Thread.currentThread().isInterrupted()) {
				try {
					msg = _msgQueue.take();
					_connection.publish(TOPIC, msg, QoS.AT_LEAST_ONCE, false);
					_logger.debug("Publishing " + msg);
				} catch (InterruptedException e) {
					e.printStackTrace();
					_logger.error(e);
				}
			}
			// _connection.disconnect().await();
		}
	}
}
