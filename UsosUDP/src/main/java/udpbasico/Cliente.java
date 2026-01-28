package udpbasico;

import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("## PANEL CLIENTE UDP ##");
        System.out.println("### ENVIA PAQUETES... ###");
        empezarCliente();
    }

    private static void empezarCliente() {
        try {
            DatagramSocket socket = new DatagramSocket();
            Scanner sc = new Scanner(System.in);
            while (true){
                System.out.print("> ");
                String linea = sc.nextLine();
                DatagramPacket paqueteEnvio = new DatagramPacket(linea.getBytes(), 0, linea.length(), InetAddress.getByName("localhost"), 6000);

                socket.send(paqueteEnvio);

                byte[] bytesRespuesta = new byte[1024];
                DatagramPacket paqueteRespuesta = new DatagramPacket(bytesRespuesta, bytesRespuesta.length);
                socket.receive(paqueteRespuesta);

                String respuestaStr = new String(paqueteRespuesta.getData(), 0, paqueteRespuesta.getLength());
                if(respuestaStr.equalsIgnoreCase("OK")){
                    break;
                }
                System.out.println(respuestaStr);
            }
            socket.close();
            sc.close();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
