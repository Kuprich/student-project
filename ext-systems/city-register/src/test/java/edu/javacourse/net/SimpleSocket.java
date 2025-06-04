package edu.javacourse.net;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SimpleSocket {
    @Test
    public void SimpleSocket() throws IOException {
        Socket socket = new Socket("localhost", 8080);

        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();

        String request = "GET / HTTP/1.1\r\n" +
                "Host: localhost\r\n" +
                "Connection: close\r\n\r\n";
        os.write(request.getBytes());
        os.flush();

        int b = 0;
        while((b = is.read()) != -1){
            System.out.print((char)b);
        }
    }
}
