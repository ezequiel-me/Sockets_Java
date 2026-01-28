package udpbasico;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.UnknownHostException;

public class TrabajadoresHilos implements Runnable{
    private DatagramPacket paqueteRecibido;
    private DatagramSocket socket;

    public TrabajadoresHilos() {
    }

    public TrabajadoresHilos(DatagramPacket paqueteRecibido, DatagramSocket socket) {
        this.paqueteRecibido = paqueteRecibido;
        this.socket = socket;
    }

    public DatagramPacket getPaqueteRecibido() {
        return paqueteRecibido;
    }

    public void setPaqueteRecibido(DatagramPacket paqueteRecibido) {
        this.paqueteRecibido = paqueteRecibido;
    }

    public DatagramSocket getSocket() {
        return socket;
    }

    public void setSocket(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        String paqueteStr = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
        System.out.println("[Hilo]> Contenido Del Paquete: "+paqueteStr);
        try {
            String respuesta = "[Servidor]> Tu paquete ha sido manejado...";
            DatagramPacket paqueteRespuesta = new DatagramPacket(respuesta.getBytes(), respuesta.getBytes().length, paqueteRecibido.getAddress(), paqueteRecibido.getPort());

            socket.send(paqueteRespuesta);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("[Hilo]> Enviando Respuesta");
    }
}
