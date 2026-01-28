package udpbasico;

import java.io.IOException;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        TrabajadoresHilos hilo = new TrabajadoresHilos();
        DatagramSocket socket = crearServidor();
        manejarPaquetesEntrantes(socket, hilo);
    }

    private static void manejarPaquetesEntrantes(DatagramSocket socket, TrabajadoresHilos hilo) {
        while (true){
            try {
                System.out.println("[Servidor]> Estoy Esperando Paquetes...");
                System.out.println("======================");
                byte[] bytes = new byte[1024];
                DatagramPacket paqueteRecibido = new DatagramPacket(bytes, bytes.length);

                socket.receive(paqueteRecibido);
                System.out.println("[Servidor]> He recibido un paquete...");
                System.out.println("======================");

                hilo.setSocket(socket);
                hilo.setPaqueteRecibido(paqueteRecibido);
                System.out.println("[Servidor]> Paquete enviado a hilo trabajador...");
                System.out.println("======================");

                Thread hiloThr = new Thread(hilo);
                hiloThr.start();
                System.out.println("[Servidor]> Hilo iniciado...");
                System.out.println("======================");

                hiloThr.join();
                System.out.println("[Servidor]> Hilo envió el paquete...");
                System.out.println("======================");
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private static DatagramSocket crearServidor() {
        try {
            DatagramSocket socket = new DatagramSocket(6000);
            return socket;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }
}
