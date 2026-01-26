# Guía De Aprendizaje - Sockets en Java (TCP y UDP)

## Comunicación en red.
- **¿Qué es?:** es el intercambio de información entre **dos programas.** Para crear una conexión es necesario una dirección y un puerto.

## Socket.
- **Qué es un socket:** es un canal de comunicación entre dos programas. Usados, normalmente, para implementar **el modelo cliente-servidor.**

## Modelo cliente-servidor.
- **¿Qué es?:** es una arquitectura en la que el **cliente** usa unos **servicios** y el **servidor** es el encargado de proporcionarlos.
- **Funciones de cada uno:**
    - **Servidor:** se ejecuta primero, abre un puerto, espera conexiones y atiende peticiones.
    - **Cliente:** se ejecuta después, sabe la dirección y el puerto, inicia la comunicación/conexión, envía datos y recibe respuesta.

### Diagrama de servidor y cliente con socket
![](./imagenes/img.png)

### TCP y UDP. Tipos de sockets.
- **¿Qué es TCP?**
Es un protocolo orientado a conexión, fiable y orden garantizado. Es usado cuando los datos son importantes y no se pueden perder mensajes.

- **Sockets TCP:**
    - Orientados a conexiones, **comunicación fiable**, **datos llegan en orden y sin perdidas**, se establece una **conexión previa (handshake)**.
    - **Usos:** Chats, servidores de apps, transferencias de datos.

- **Clases de Java (del paquete java.net) :**
    - **ServerSocket:** usada para abrir un puerto y escuchar conexiones.
        - Algunos Métodos:
            **accept():** usado para esperar un cliente.
            **close():** cierra el servidor.
    - **Socket:** usada para el cliente conectarse y para comunicarse con el cliente.
        - Algunos Métodos:
            **getInputStream():** usado para obtener la entrada del servidor y enviarle datos.
            **getOutputStream():** usado para obtener la salida estandar del servidor y enviarle datos.
            **close():** cierra la conexión.
        **Siempre se deben cerrar los streams antes que el socket.**

- **Desarrollo modelo cliente - servidor TCP:**
    - **ServidorTCP.java:** encargada de abrir un puerto, esperar un cliente, recibir un mensaje, responderlo y cerrar la conexión.
    ```java
    import java.net.*;
    import java.io.*;
    public class ServidorTCP {
        public static void main(String[] args) {
            try {
                // Abrimos el puerto 5000
                ServerSocket servidor = new ServerSocket(5000);
                System.out.println("Servidor TCP esperando conexiones...");
                // Se espera una conexión
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado");
                // Preparamos entrada y salida
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
                PrintWriter salida = new PrintWriter();
                // Leemos el mensaje del cliente
                String mensaje = entrada.readLine();
                System.out.println("Cliente dice: " + mensaje);
                // Respondemos al cliente
                salida.println("Servidor recibe: " + mensaje);
                // Cerramos conexión
                cliente.close();
                servidor.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    ```

    - **ClienteTCP.java (soporte 1 cliente):** se conecta al servidor, envia un mensaje, espera la respuesta, cierra.
    ```java
    import java.net.*;
    import java.io.*;
    public class ClienteTCP {
        public static void main(String[] args) {
            try {
            // Conexión al servidor
            Socket socket = new Socket("localhost", 5000);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            // Enviamos mensaje
            salida.println("Hola servidor");
            // Recibimos respuesta
            String respuesta = entrada.readLine();
            System.out.println("Servidor responde: " + respuesta);
            socket.close();
            } catch (IOException e) {
            e.printStackTrace();
            }
        }
    }

    ```

    -  **Uso de hilos para manejar varias peticiones/conexiones:**
    ```java
        // Se define un bucle infinito, dentro del servidor, para estar siempre manejando peticiones...
        while(true){
            // Al recibir una petición, la almacenamos en un objeto de la clase 'Socket'.
            Socket cliente = servidor.accept();
            // Instanciamos un objeto de la clase "AtencionCliente()" la cual implementa la interfaz 'Runnable' y nos permitirá manejar esa comunicación de manera independiente.
            new Thread(new AtencionCliente(cliente)).start();
        }
    ```

- **Sockets UDP:**
    - No orientados a conexión, no se garantiza orden ni recepción, no hay conexión previa.
    - **Clases de Java:** DatagramSocket, DatagramPacket.
    - **Usos:** Streaming, juegos online, DNS...




