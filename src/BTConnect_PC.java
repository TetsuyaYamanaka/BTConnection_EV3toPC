import java.io.*;
import lejos.remote.nxt.*;
import lejos.hardware.*;

public class BTConnect_PC {
	public static void main(String[] args) {
		int t = 0;
		BTConnector btc = new BTConnector();
		String target;
		System.out.println("Connecting...");
		LocalBTDevice device = Bluetooth.getLocalDevice();
		
		if(device == null){
			System.out.println("Can't search device");
			System.exit(1);
		}
		
		target = device.getBluetoothAddress();
		BTConnection connection = btc.connect(target,NXTConnection.LCP);
		
		if(connection == null){
			System.out.println("Can't connect");
			System.exit(1);
		}
		
		DataInputStream dis = connection.openDataInputStream();
		try{
			while(t<11){
			t = dis.read();
			System.out.println("recieve: " + t);
			}
		}
		catch(IOException e){
			System.out.println("recieve error" + e);
		}
		
		try{
			System.out.println("Close connection...");
			dis.close();
			connection.close();
		}
		catch(IOException e){
			System.out.println("Close Exception");
			System.out.println(e.getMessage());
		}
		
		return;
	}

}
