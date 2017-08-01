package Clases;

import java.io.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JEditorPane;

public class Cliente implements Runnable
{
    private Socket cliente;
    private DataInputStream in;
    private DataOutputStream out;
    private int puerto = 1234;
    private String host = "192.168.117.149";
    private String mensajes = "";
    JEditorPane panel;
    
    public Cliente(JEditorPane panel)
    {
        this.panel = panel;
        try 
        {
            cliente = new Socket(host,puerto);
            in = new DataInputStream(cliente.getInputStream());
            out = new DataOutputStream(cliente.getOutputStream());
        } 
        catch (IOException e) 
        {
            System.out.println(""+e);
        }
    }

    @Override
    public void run() {
        try
        {
            while(true)
            {
                mensajes += in.readUTF();
                panel.setText(mensajes);
            }
        }
        catch(IOException e)
        {
            System.out.println(""+e);
        }
    }
    
    public void enviarMsg(String msg)
    {
        try 
        {
            out.writeUTF(msg);
        } 
        catch (IOException e) 
        {
            System.out.println(""+e);
        }
    }
    
}
