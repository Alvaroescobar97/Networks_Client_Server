package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client_1 {

	public final static String LOCAL_HOST = "192.168.43.58";
	public final static int PORT = 8500;
	private static Socket socket;
	
	
	public static void main(String[] args) {
		
		DataInputStream in;
		DataOutputStream out;
		
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

			System.out.println(".: Cliente Disponible :. \n Ingrese el mensaje que desea enviar: ");
			
			socket = new Socket(LOCAL_HOST, PORT);
			in = new DataInputStream(socket.getInputStream());
			int key = Integer.parseInt(in.readUTF(), 16);
			String mensaje = br.readLine();
			
			out = new DataOutputStream(socket.getOutputStream());
			out.writeUTF(encriptar(mensaje, key) );
			out.writeUTF(Integer.toHexString(key));
			bw.write("Su encriptacion fue : " + encriptar(mensaje, key) + " Con clave: " +  key);
			
			
			
			bw.flush();
			bw.close();
			br.close();
			socket.close();
			in.close();
			out.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
private static String encriptar(String mensajeObtenidoCliente, int key) {
		
		String respuesta = "";
		char caracter ;
		
		if(mensajeObtenidoCliente != "") {
			for (int i = 0; i < mensajeObtenidoCliente.length(); i++) {
				caracter = mensajeObtenidoCliente.charAt(i);
				caracter = (char) (caracter +key);
				respuesta += Character.toString((caracter)) + "";
			}
		}
		else {
			respuesta = ".: No hay mensaje para encriptar :.";
		}
		return respuesta;
		
	}
}
