package io.hankers.mdi.philips.mp20;

import java.net.SocketException;
import java.net.UnknownHostException;

import io.hankers.mdi.mdi_utils.MDILog;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		try {
			new DataReceiver().start();
		} catch (SocketException e) {
			MDILog.e(e);
		} catch (UnknownHostException e) {
			MDILog.e(e);
		}
	}
}
