package edu.javacourse.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            new SimpleClient(i).start();
        }
    }

    public static class SimpleClient extends Thread {

        private final static String[] COMMANDS = new String[]{"HELLO", "MORNING", "DAY", "EVENING"};
        private int cmdNumber;

        public SimpleClient(int cmdNumber) {
            this.cmdNumber = cmdNumber;
        }

        @Override
        public void run() {
            try {
                Socket client = new Socket("127.0.0.1", 25225);

                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                String str = COMMANDS[cmdNumber % COMMANDS.length] + " " + "User";

                bw.write(str);
                bw.newLine();
                bw.flush();

                String answer = br.readLine();
                System.out.println("client got to string: " + answer);

                br.close();
                bw.close();

                client.close();
            } catch (IOException e) {
                e.printStackTrace(System.out);
            }
        }
    }

}

