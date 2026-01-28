package udpserializacion;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
/*
*  String nomHost = "localhost";
    int numPuerto = 5000;

    try (DatagramSocket clientSocket = new DatagramSocket();
        Mensaje mensaje = new Mensaje("Hola", "UTF-8");

        ByteArrayOutputStream byteAUS = new ByteArrayOutputStream();
        ObjectOutputStream objOU = new ObjectOutputStream(byteAUS);
        objOU.writeObject(mensaje);

        byte[] bytesArr = byteAUS.toByteArray();

        DatagramPacket dtgPacket = new DatagramPacket(bytesArr, bytesArr.length, InetAddress.getByName("localhost"),6000);
        clientSocket.send(dtgPacket);
        clientSocket.send(paqueteEnviado);

        byte[] bytesRespuesta = new byte[1024];
        DatagramPacket paqueteRespuesta = new DatagramPacket(bytesRespuesta, bytesRespuesta.length);
        dtgSocket.receive(paqueteRespuesta);

        ByteArrayInputStream byteArrIS = new ByteArrayInputStream(bytesRespuesta);
        ObjectInputStream objIS = new ObjectInputStream(byteArrIS);
        Mensaje mensajeRespuesta = (Mensaje) objIS.readObject();

        System.out.println("Respuesta recibida del server: "+mensajeRespuesta.getMensaje());
      }

    } catch (SocketException ex) {
      System.out.println("Excepción de sockets");
      ex.printStackTrace();
    } catch (IOException ex) {
      System.out.println("Excepción de E/S");
      ex.printStackTrace();
    }
  }
*
* */
public class Cliente {

    public static void main(String[] args) {
        DatagramSocket socket = crearSocket();

        enviarObjeto(socket);

        cerrarSocket(socket);
    }

    private static DatagramSocket crearSocket() {
        try {
            DatagramSocket dtgSocket = new DatagramSocket();
            return  dtgSocket;
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void cerrarSocket(DatagramSocket socket) {
        socket.close();
    }

    private static void enviarObjeto(DatagramSocket dtgSocket) {
        try {
            Mensaje mensaje = new Mensaje("Hola", "UTF-8");

            ByteArrayOutputStream byteAUS = new ByteArrayOutputStream();
            ObjectOutputStream objOU = new ObjectOutputStream(byteAUS);
            objOU.writeObject(mensaje);

            byte[] bytesArr = byteAUS.toByteArray();

            DatagramPacket dtgPacket = new DatagramPacket(bytesArr, bytesArr.length, InetAddress.getByName("localhost"),6000);
            dtgSocket.send(dtgPacket);

            byte[] bytesRespuesta = new byte[1024];
            DatagramPacket paqueteRespuesta = new DatagramPacket(bytesRespuesta, bytesRespuesta.length);
            dtgSocket.receive(paqueteRespuesta);

            ByteArrayInputStream byteArrIS = new ByteArrayInputStream(bytesRespuesta);
            ObjectInputStream objIS = new ObjectInputStream(byteArrIS);
            Mensaje mensajeRespuesta = (Mensaje) objIS.readObject();

            System.out.println("Respuesta recibida del server: "+mensajeRespuesta.getMensaje());

        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
