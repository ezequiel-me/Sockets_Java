package udpmulticast;

import java.io.IOException;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        recibirPaqueteMultiCast();
    }

    private static void recibirPaqueteMultiCast() {
        try {
            MulticastSocket socket = new MulticastSocket(4000);
            InetAddress grupo = InetAddress.getByName("225.0.0.1");
            SocketAddress socketAddr = new InetSocketAddress(grupo, 4000);
            NetworkInterface netF = NetworkInterface.getByName("localhost");
            socket.joinGroup(socketAddr, netF);
            while(true){
                byte[] mensaje = new byte[1024];
                DatagramPacket paqueteMsj = new DatagramPacket(mensaje, mensaje.length);
                socket.receive(paqueteMsj);
                String mensajeMulticast = new String(paqueteMsj.getData(), 0, paqueteMsj.getLength());
                if(mensajeMulticast.equalsIgnoreCase("OK")){
                    break;
                }
                System.out.println(mensajeMulticast);
            }
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
