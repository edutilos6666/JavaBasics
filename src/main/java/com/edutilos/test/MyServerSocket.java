package com.edutilos.test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServerSocket {

    public static void main(String[] args) {
        int port = 9999;
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            while(true){
                Socket socket = server.accept();
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                DataInputStream dis = new DataInputStream(is);
                DataOutputStream dos = new DataOutputStream(os);
                System.out.println("Server got: "+ dis.readUTF());
                dos.writeUTF("Hello world from server");
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
