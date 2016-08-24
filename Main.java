package com.company;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Main extends Thread
{
    private ServerSocket serverSocket;
    private ArrayList<miniServer> Collections = new ArrayList<miniServer>();
    public Main(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    public void run()
    {
        while(true)
        {
            try
            {
                System.out.println("Waiting for client on port " +
                        serverSocket.getLocalPort() + "...");
                Socket server = serverSocket.accept();
                miniServer mini = new miniServer(server);
                mini.start();
                Collections.add(mini);
            }catch(SocketTimeoutException s)
            {
                System.out.println("Socket timed out!");
                break;
            }catch(IOException e)
            {
                e.printStackTrace();
                break;
            }
        }
    }
    public static void main(String [] args)
    {
        int port = 102;
        try
        {
            Thread t = new Main(port);
            t.start();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}