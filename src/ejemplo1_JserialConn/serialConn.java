package ejemplo1_JserialConn;

import java.io.PrintWriter;

import com.fazecast.jSerialComm.SerialPort;

public class serialConn extends Thread{
	private char valor='0';
	private SerialPort port;
	
	public SerialPort openConn() {
		SerialPort [] ports=SerialPort.getCommPorts();
		for(SerialPort port:ports) {
			System.out.println(port.getSystemPortName());
		}
		this.port=ports[0];//Carga el puerto de conexión con Arduino
		if(port.openPort()) {
			System.out.println("Puerto serial abierto");
		}else {
			System.out.println("Puerto serial ocupado");
		}
		port.setComPortParameters(9600, 8, 1, 0);
		return port;
	}
	
	public boolean closeConn() {
		return port.closePort();
	}
	public synchronized char getDato() {
		return valor;
	}
	public synchronized void setDato(char valor_) {
		this.valor=valor_;
	}
	public void run() {
		openConn();//abrir la conexion serial
		while(true) {
			if(port.bytesAvailable()>0) {
				PrintWriter output=new PrintWriter(port.getOutputStream());
				output.print(getDato());
				output.flush();
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(getDato()=='3') {
				break;			//Interumpe el hilo
			}
		}
	}

}
