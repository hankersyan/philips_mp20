package io.hankers.mp20;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;

import io.hankers.mp20.Models.ConnectIndication;

public class MonitorSniffer extends Thread {
    private DatagramSocket socket;
    private boolean running;
    private byte[] buf = new byte[2048];

    public MonitorSniffer() throws SocketException {
        socket = new DatagramSocket(24005);
    }
  
    public void run() {
        running = true;
 
        while (running) {
            DatagramPacket packet = new DatagramPacket(buf, buf.length);
            try {
				socket.receive(packet);
	            
	            InetAddress address = packet.getAddress();
	            int port = packet.getPort();
	            
	            //NetworkInterface network = NetworkInterface.getByInetAddress("ip");
	            //byte[] mac = network.getHardwareAddress();
	            
	            InputStream ins = new ByteArrayInputStream(buf);
	            
	            ConnectIndication conIndi = new ConnectIndication();
	            conIndi.read(ins, true);
	            
	            socket.send(packet);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        socket.close();
    }
    
    public void stopRun() {
    	running = false;
    }
}