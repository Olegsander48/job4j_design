package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    private static final Logger LOG = LoggerFactory.getLogger(EchoServer.class.getName());

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (!server.isClosed()) {
                Socket socket = server.accept();
                try (OutputStream output = socket.getOutputStream();
                     BufferedReader input = new BufferedReader(
                             new InputStreamReader(socket.getInputStream()))) {
                    output.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                    String string = input.readLine();
                    if (string.contains("msg=Bye")) {
                        output.write("Bye\r\n\r\n".getBytes());
                        server.close();
                    } else if (string.contains("msg=Hello")) {
                        output.write("Hello\r\n\r\n".getBytes());
                    } else {
                        output.write("What?!\r\n\r\n".getBytes());
                        output.write(string.getBytes());
                    }
                    output.flush();
                }
            }
        } catch (IOException e) {
            LOG.error("Exception in file", e);
        }
    }
}
