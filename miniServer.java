package com.company;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Terence on 2016-08-23
 * ICS -----
 * Date: 20xx
 * Teacher:-----
 */
public class miniServer extends Thread{

    private Socket socket;
    private Main mainServer;

    public miniServer(Socket socket){
        this.socket = socket;

    }



    @Override
    public void run() {

        try {
            System.out.println("Just connected to " + socket.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF("Thank you for connecting to " + socket.getLocalSocketAddress());
            //socket.close();

            while (true){
                String input = in.readUTF();
                System.out.println(input);
                out.writeUTF("Message recieved by socket: " + input);
            }
        } catch (Exception e) {
            System.out.println("Someone left");
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
