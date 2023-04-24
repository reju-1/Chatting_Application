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

    public static final ArrayList<ClientHandler> clients = new ArrayList<>();

    public static final Map<String,ClientHandler> clientList = new HashMap<>();

    ClientHandler(Socket sc) {
        try {
            InputStreamReader r = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(r);

            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);

            id = reader.readLine();
            clients.add(this);
            System.out.println(id + " is Connected");


        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    @Override
    public void run() {

        while (true) {

            try {
                String data = reader.readLine();
                data = id + " : " + data;

                synchronized (clients) {
                    for (ClientHandler ch : clients) {
                        ch.writer.write(data + "\n");
                        ch.writer.flush();
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

