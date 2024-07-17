package ejemplo1_JserialConn;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in=new Scanner(System.in);
		serialConn sc=new serialConn();
		sc.start();
		char opt;
		while(true) {
			System.out.println("1.- Encendido de LED\r\n"
					+ "2.- Apagado de LED\r\n"
					+ "3.- Cerrar serialConn\r\n"
					+ "Elija un opcion:");
			opt=in.next().charAt(0);
			sc.setDato(opt);
			if(opt=='3') {
				sc.closeConn();
				break;
			}				
			
		}

	}

}
