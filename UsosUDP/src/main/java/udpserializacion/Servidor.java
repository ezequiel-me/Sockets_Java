package udpserializacion;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
/*
*
    try (DatagramSocket serverSocket = new DatagramSocket(numPuerto)) {
      while (true) {
        byte[] datosRecibidos = new byte[MAX_BYTES];
        DatagramPacket paqueteRecibido = new DatagramPacket(datosRecibidos, datosRecibidos.length);
        socket.receive(paqueteRecibido);

        ByteArrayInputStream byteArrIS = new ByteArrayInputStream(bytes);
        ObjectInputStream objIS = new ObjectInputStream(byteArrIS);
        Mensaje mensaje = (Mensaje) objIS.readObject();
        System.out.println("[Servidor]> Objeto Recibido");
        System.out.println("[Servidor]> "+mensaje.getMensaje() + " || " + mensaje.getCodif());

        ByteArrayOutputStream byteArrOUS = new ByteArrayOutputStream();
        ObjectOutputStream objOUS = new ObjectOutputStream(byteArrOUS);
        Mensaje mensajeRespuesta = new Mensaje("Hola que tal", "UTF-8");
        objOUS.writeObject(mensajeRespuesta);

        byte[] bytesArr = byteArrOUS.toByteArray();
        DatagramPacket paqueteRespuesta = new DatagramPacket(bytesArr, bytesArr.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
        socket.send(paqueteRespuesta);
      }
    } catch (SocketException ex) {
      System.out.println("Excepción de sockets");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("Excepción de E/S");
      ex.printStackTrace();
    }
*
* */
public class Servidor {

    public static void main(String[] args) {
        DatagramSocket socket = crearSocket();
        
        manejarPaquetes(socket);
        
        cerrarSocket(socket);
    }

    private static void manejarPaquetes(DatagramSocket socket) {

        try {
            byte[] bytes = new byte[1024];
            while(true){
                DatagramPacket paqueteRecibido = new DatagramPacket(bytes, bytes.length);
                socket.receive(paqueteRecibido);

                ByteArrayInputStream byteArrIS = new ByteArrayInputStream(bytes);
                ObjectInputStream objIS = new ObjectInputStream(byteArrIS);
                Mensaje mensaje = (Mensaje) objIS.readObject();
                System.out.println("[Servidor]> Objeto Recibido");
                System.out.println("[Cliente]> "+mensaje.getMensaje() + " || " + mensaje.getCodif());


                ByteArrayOutputStream byteArrOUS = new ByteArrayOutputStream();
                ObjectOutputStream objOUS = new ObjectOutputStream(byteArrOUS);
                Mensaje mensajeRespuesta = new Mensaje("Hola que tal", "UTF-8");
                objOUS.writeObject(mensajeRespuesta);

                byte[] bytesArr = byteArrOUS.toByteArray();
                DatagramPacket paqueteRespuesta = new DatagramPacket(bytesArr, bytesArr.length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());
                socket.send(paqueteRespuesta);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    private static void cerrarSocket(DatagramSocket socket) {
        socket.close();
    }

    private static DatagramSocket crearSocket() {
        try {
            DatagramSocket socket = new DatagramSocket(6000);
            return  socket;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }

    }
}
