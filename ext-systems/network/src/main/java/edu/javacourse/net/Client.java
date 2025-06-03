package edu.javacourse.net;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        sandReuest();
    }

    private static void sandReuest() throws IOException {
        Socket client = new Socket("127.0.0.1", 25225);

        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));

        String str = "User";

        bw.write(str);
        bw.newLine();
        bw.flush();

        String answer = br.readLine();
        System.out.println(answer);

        br.close();
        bw.close();

        client.close();
    }
}
