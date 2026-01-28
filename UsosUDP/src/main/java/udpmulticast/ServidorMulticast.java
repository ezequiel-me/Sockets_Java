package udpmulticast;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ServidorMulticast {

    public static void main(String[] args) {
        MulticastSocket multicastSocket = crearMulticast();
        enviarMensajesMulticast(multicastSocket);
        cerrarMulticast(multicastSocket);
    }

    private static MulticastSocket crearMulticast() {
        try {
            MulticastSocket multicast = new MulticastSocket();
            return multicast;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void enviarMensajesMulticast(MulticastSocket multicastSocket) {
        try {
            Scanner sc = new Scanner(System.in);
            InetAddress direcciones = InetAddress.getByName("225.0.0.1");
            while(true){
                System.out.print("[Servidor]> ");
                String linea = sc.nextLine();
                if(linea.equalsIgnoreCase("Salir")){
                    break;
                }
                DatagramPacket paquete = new DatagramPacket(linea.getBytes(), linea.getBytes().length, direcciones, 4000);
                multicastSocket.send(paquete);
            }
            sc.close();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void cerrarMulticast(MulticastSocket multicastSocket) {
        multicastSocket.close();
        System.out.println("Servidor Multicast cerrado...");
    }

}
