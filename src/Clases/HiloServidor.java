
package Clases;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

public class HiloServidor implements Runnable
{
    private final Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private LinkedList<Socket> usuarios = new LinkedList<>();
    
    public HiloServidor(Socket soc,LinkedList users)
    {
        socket = soc;
        usuarios = users;
    }
    
    @Override
    public void run() 
    {
        try 
        {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Bienvenido");
            while(true)
            {
               String recibido = in.readUTF();
                for (int i = 0; i < usuarios.size(); i++) 
                {
                    out = new DataOutputStream(usuarios.get(i).getOutputStream());
                    out.writeUTF(recibido);
                }
            }
        } 
        catch (IOException e) 
        {
            for (int i = 0; i < usuarios.size(); i++) 
            {
                if(usuarios.get(i) == socket)
                {
                    usuarios.remove(i);
                    break;
                } 
            }
        }
    }
}
