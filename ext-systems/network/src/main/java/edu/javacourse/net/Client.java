package edu.javacourse.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < 10; i++) {
            new SimpleThread().start();
        }
    }

    public static class SimpleThread extends Thread {
        @Override
        public void run() {
            try {
                Socket client = new Socket("127.0.0.1", 25225);

                BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

                String str = "User";

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

