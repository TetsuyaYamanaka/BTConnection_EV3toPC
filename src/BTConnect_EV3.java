import java.io.*;
import lejos.remote.nxt.*;
import lejos.hardware.lcd.*;
import lejos.hardware.Button;

public class BTConnect_EV3 {
	public static void main(String[] args) {
		int t = 0;
		BTConnector btc = new BTConnector();
		
		LCD.drawString("Waiting Connection...",0,3);
		NXTConnection connection = btc.waitForConnection(5000,NXTConnection.LCP);
		
		if(connection == null){
			LCD.clear();
			LCD.drawString("can't connect", 0, 3);
			Button.waitForAnyPress();
			System.exit(1);
		}

		DataOutputStream dos  = connection.openDataOutputStream();
			try{
				while(t<11){
					dos.flush();
					dos.write(t);
					LCD.clear();
					LCD.drawString("Send: " + t, 0, 3);
					t++;
				}
			}
			catch(IOException e){
				LCD.clear();
				e.printStackTrace();
			}
		
		try{
			LCD.clear();
			LCD.drawString("Close connection...",0,3);
			dos.close();
			connection.close();
		}
		catch(IOException e){
			LCD.clear();
			LCD.drawString("Close Exception",0,0);
			LCD.drawString(e.getMessage(),0,1);
		}
		
		System.exit(0);
	}
}
