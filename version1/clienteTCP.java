package version1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clienteTCP {
	// iniciamos en MAIN
	public static void main(String[] args) {
		clienteTCP Cliente = new clienteTCP("www.google.com", 80);
		Cliente.iniciar();
	}

	// variables
	String servidor;
	int puerto;

	// constructor Cliente, donde enviamos el dominio o IP, y el puerto
	public clienteTCP(String servidor, int puerto) {
		this.servidor = servidor;
		this.puerto = puerto;
	}

	// funcion para iniciar la conexion y hacer la peticion
	public void iniciar() {
		try {
			Socket cliente = new Socket(servidor, puerto);
			System.out.println("------CONEXION INICIADA------");
			// mostramos la direccion y el puerto del servidor al cual estamos conectados
			System.out.println("Conectado a: " + cliente.getRemoteSocketAddress());
			// Utilizamos un printWriter para imprimir la fecha en la secuencia de salida conectada al 
			// socket y con true vaciamos automaticamente el bufer de la secuencia.
			PrintWriter w = new PrintWriter(cliente.getOutputStream(), true);
			// escribimos el argumento y salta de linea, nuestro caso la cabecera para hacer la peticion
			w.println("GET / HTTP/1.1");
			w.println(""); // salto de linea para hacer una salto de linea, ya que te pide dos saltos de linea
			System.out.println("Respuesta del servidor: ");
			// recibimos en un buffer la respuesta del servidor
			BufferedReader in = new BufferedReader(new InputStreamReader(cliente.getInputStream()));											
			// desde aqui imprimimos linea por linea, lo que nos envie el servidor
			String respuestaLinea;
			while ((respuestaLinea = in.readLine()) != null) {
				System.out.println(respuestaLinea);
			}
			// cerramos la conexion
			cliente.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

// verificamos con TELNET:
/*
 	telnet www.google.com 80
 	GET / HTTP/1.1
 */
// Para copiarlo en un archivo
/*	
	touch contenido.txt
	telnet www.google.com 80 | tee contenido.txt
	GET / HTTP/1.1
*/