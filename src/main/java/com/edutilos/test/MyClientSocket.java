package com.edutilos.test;


import java.io.*;
import java.net.Socket;

public class MyClientSocket {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 9999;
        try {
            Socket socket = new Socket(host, port);
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            DataInputStream dis = new DataInputStream(is);
            DataOutputStream dos =new DataOutputStream(os);
            dos.writeUTF("hello from client");
            System.out.println("Client received: "+ dis.readUTF());
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
