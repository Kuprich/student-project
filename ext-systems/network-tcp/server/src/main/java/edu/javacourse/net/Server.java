package edu.javacourse.net;

import edu.javacourse.config.Config;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Server {
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket socket = new ServerSocket(25225);

        Map<String, Greetable> handlers = loadHandlers();

        while (true) {
            Socket client = socket.accept();
            new SimpleServer(client, handlers).start();
        }

    }

    private static Map<String, Greetable> loadHandlers() {
        Map<String, Greetable> result = new HashMap<>();


        Properties properties = Config.getProperties();
        try {
            for (Object o : properties.keySet()) {
                String className = properties.getProperty(o.toString());
                Class<Greetable> cl = (Class<Greetable>) Class.forName(className);
                Greetable handler = cl.getConstructor().newInstance();
                result.put(o.toString(), handler);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private static class SimpleServer extends Thread {

        private Socket client;
        private Map<String, Greetable> handlers;

        public SimpleServer(Socket client, Map<String, Greetable> handlers) {
            this.client = client;
            this.handlers = handlers;
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

                String response = buildResponse(lineItems[0], lineItems[1]);

                bw.write(response);
                bw.newLine();
                bw.flush();

                br.close();
                bw.close();

                client.close();
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
            }

        }

        public String buildResponse(String command, String userName){
            Greetable handler = handlers.get(command);
            if (handler != null)
                return handler.buildResponse(userName);
            else return "Hello, " + userName;
        }
    }
}
