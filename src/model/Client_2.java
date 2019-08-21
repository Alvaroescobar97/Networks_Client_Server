package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client_2 {

	public final static String LOCAL_HOST = "192.168.43.58";
	public final static int PORT = 8500;
	private static Socket socket;
	
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;

		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader( System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			socket = new Socket(LOCAL_HOST, PORT);
			System.out.println(2);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
			
			String mensajeDelServidor = in.readUTF();
			
			String clave = in.readUTF();
			int key = Integer.parseInt(clave, 16);
			bw.write("Su encriptacion es : " + mensajeDelServidor + ", la clave es: " + clave + "\n");
			bw.write("El mensaje desencriptado es " + desencriptar(mensajeDelServidor, key));
			bw.flush();
			bw.close();
			br.close();
			socket.close();
			in.close();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
private static String desencriptar(String mensajeObtenidoCliente, int key) {
		
		String respuesta = "";
		char caracter ;
		
		if(mensajeObtenidoCliente != "") {
			for (int i = 0; i < mensajeObtenidoCliente.length(); i++) {
				caracter = mensajeObtenidoCliente.charAt(i);
				caracter = (char) (caracter -key);
				respuesta += Character.toString((caracter)) + "";
			}
		}
		else {
			respuesta = ".: No hay mensaje para encriptar :.";
		}
		return respuesta;
		
	}
}
