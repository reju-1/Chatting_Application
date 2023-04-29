package com.example.chat.networing;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler implements Runnable {

    String id;
    BufferedReader reader;
    BufferedWriter writer;

    public static final Map<String,ClientHandler> clientList = new HashMap<>();

    ClientHandler(Socket sc) {
        try {
            InputStreamReader r = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(r);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);

            id = reader.readLine();

            clientList.put(id,this);
            System.out.println(id + " is Connected");


        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {

            try {
                String messageToken = reader.readLine();

                String[] parts = messageToken.split("##");

                String senderId = parts[0];
                String senderName = parts[1];
                String receiverId = parts[2];
                String receiverName = parts[3];
                String time = parts[4];
                String message = parts[5] ;

                synchronized (clientList) {
                    if (clientList.containsKey(receiverId)){
                        ClientHandler clientHandler = clientList.get(receiverId);
                        clientHandler.writer.write(messageToken+"\n");
                        clientHandler.writer.flush();
                    }
                }

            } catch (SocketException s) {
                s.printStackTrace();
                break;
            } catch (IOException io) {
                io.printStackTrace();
            }
        }
    }
}

