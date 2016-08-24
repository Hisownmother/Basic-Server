package com.company;

import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class Main
{

    private static ServerSocket serverSocket;
    static ArrayList<miniServer> Collection = new ArrayList<miniServer>();
    public Main(int port) throws IOException
    {
        serverSocket = new ServerSocket(port);
        serverSocket.setSoTimeout(100000);
    }

    public void sendMessage(String message){
        for(int i = 0; i < Collection.size(); i++){
            Collection.get(i).alienOutput(message);
        }
    }
    public static void main(String [] args)
    {
        int port = 102;

        try
        {
            Main motherShip = new Main(port);
            while(true)
            {
                try
                {
                    System.out.println("Waiting for client on port " +
                            serverSocket.getLocalPort() + "...");
                    Socket server = serverSocket.accept();
                    //Collection.add(new miniServer(server));
                    miniServer mini = new miniServer(server,motherShip);
                    mini.start();
                    Collection.add(mini);

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

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}