package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public final static int PORT = 8500;

	private static ServerSocket serverSocket;
	
	private static Socket socket1,socket2;
	
	
public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println(".:Servidor Disponible:.");
			
			while(true) {
				
				socket1 = serverSocket.accept();
				
				out = new DataOutputStream(socket1.getOutputStream());
				
				int key = (int)(Math.random()*20);
				out.writeUTF(Integer.toHexString(key));
				in = new DataInputStream(socket1.getInputStream());
				String mensajeObtenidoCliente = in.readUTF();
				String keyhex = in.readUTF();
				socket2 = serverSocket.accept();
				System.out.println("Los clientes se han conectado!");
				
				out = new DataOutputStream(socket2.getOutputStream());
				
				System.out.println("El mensaje enviado por el cliente 1 es : " + mensajeObtenidoCliente);
								
				out.writeUTF(mensajeObtenidoCliente);
				out.writeUTF(keyhex);
				socket1.close();
				socket2.close();
				System.out.println(".:El cliente se desconteco del servidor:.");
			
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
