package edu.javacourse.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225);

        while (true) {
            Socket client = socket.accept();
            new SimpleServer(client).start();
        }

    }

    private static class SimpleServer extends Thread {

        private Socket client;

        public SimpleServer(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            handleRequest();
        }

        private void handleRequest() {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                String line = br.readLine();

                String[] lineItems = line.split("\\s+");

                String result = "command: " + lineItems[0] + " argument: " + lineItems[1];
                System.out.println("Server got string: " + line);

                Thread.sleep(2000);

                bw.write(result);
                bw.newLine();
                bw.flush();

                br.close();
                bw.close();

                client.close();
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }

        }
    }
}
