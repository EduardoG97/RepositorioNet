package Clases;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Servidor 
{

    private final int puerto = 1234;
    private final int noConexiones = 20;
    private final LinkedList<Socket> usuarios = new LinkedList<>();

    public Servidor() 
    {

    }

    public void escuchar() 
    {
        try {
            ServerSocket servidor = new ServerSocket(puerto, noConexiones);
            while (true) {
                System.out.println("Escuchando....");
                Socket cliente = servidor.accept();
                usuarios.add(cliente);
                Runnable run = new HiloServidor(cliente, usuarios);
                Thread hilo = new Thread(run);
                hilo.start();
            }
        } catch (IOException e) {
            System.out.println("" + e);
        }
    }

    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        servidor.escuchar();
    }
}
