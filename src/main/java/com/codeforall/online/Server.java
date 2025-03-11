package com.codeforall.online;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Server {
    private ServerSocket serverSocket;
    private int port;
    private Socket socket;

    private BufferedReader bufferedReader;

    public Server(int port) {
        try {
            serverSocket = new ServerSocket(port);
            this.port = port;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        System.out.println("Server listening on port " + port);
        try {
            while (true) {
                socket = serverSocket.accept();
                handleRequest();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
    }

    private void handleRequest() {
        try {
            openStreams();
            DataOutputStream dos = null;
            dos = new DataOutputStream(socket.getOutputStream());
            String message;

            while ((message = bufferedReader.readLine()) != null && message.startsWith("GET")) {
                System.out.println(message);
                String resource = message.split(" ")[1];
                System.out.println(resource);

                switch (resource) {
                    case "/index.html":
                    case "/":
                        sendHeaderIndex(dos);
                        sendBodyIndex(dos);
                        break;

                    case "/images/logo.png":
                        sendHeaderLogo(dos);
                        sendBodyLogo(dos);
                        break;

                    case "/images/index-online.png":
                        sendHeaderIndexOnline(dos);
                        sendBodyIndexOnline(dos);
                        break;

                    case "/favicon.ico":
                        sendHeaderFavicon(dos);
                        sendBodyFavicon(dos);
                        break;

                    default:
                        sendHeader404(dos);
                        sendBody404(dos);
                        break;
                }
            }

            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
            }
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendHeaderFavicon(DataOutputStream dos) throws IOException {
        String faviconPath = "src/main/www/favicon.ico";
        File faviconFile = new File(faviconPath);

        String response = "HTTP/1.0 200 Document Follows\r\n" +
                "Content-Type: image/x-icon \r\n" +
                "Content-Length: " + faviconFile.length() + "\r\n" +
                "\r\n";

        System.out.println(" ");
        dos.writeBytes(response);
    }

    private void sendBodyFavicon(DataOutputStream dos) throws IOException {
        byte[] body = Files.readAllBytes(Paths.get("src/main/www/favicon.ico"));
        dos.write(body, 0, body.length);
        dos.flush();
    }

    private void sendHeaderIndex(DataOutputStream dos) throws IOException {
        String indexPath = "src/main/www/index.html";
        File indexFile = new File(indexPath);

        String response = "HTTP/1.0 200 Document Follows\r\n" +
                "Content-Type: text/html \r\n" +
                "Content-Length: " + indexFile.length() + " \r\n" +
                "\r\n";

        System.out.println(" ");
        dos.writeBytes(response);
    }

    private void sendBodyIndex(DataOutputStream dos) throws IOException {
        byte[] body = Files.readAllBytes(Paths.get("src/main/www/index.html"));
        dos.write(body, 0, body.length);
        dos.flush();
    }

    private void sendHeaderIndexOnline(DataOutputStream dos) throws IOException {
        String indexPath = "src/main/www/images/index-online.png";
        File indexFile = new File(indexPath);

        String response = "HTTP/1.0 200 Document Follows\r\n" +
                "Content-Type: image/x-icon \r\n" +
                "Content-Length: " + indexFile.length() + " \r\n" +
                "\r\n";

        System.out.println(" ");
        dos.writeBytes(response);
    }

    private void sendBodyIndexOnline(DataOutputStream dos) throws IOException {
        byte[] body = Files.readAllBytes(Paths.get("src/main/www/images/index-online.png"));
        dos.write(body, 0, body.length);
        dos.flush();
    }

    private void sendHeaderLogo(DataOutputStream dos) throws IOException {
        String logoPath = "src/main/www/images/logo.png";
        File logoFile = new File(logoPath);

        String response = "HTTP/1.0 200 Document Follows\r\n" +
                "Content-Type: image/png \r\n" +
                "Content-Length: " + logoFile.length() + " \r\n" +
                "\r\n";

        System.out.println(" ");
        dos.writeBytes(response);
    }

    private void sendHeader404(DataOutputStream dos) throws IOException {
        String errorPath = "src/main/www/404.html";
        File errorFile = new File(errorPath);

        String response = "HTTP/1.0 404 Not Found\r\n" +
                "Content-Type: text/html; charset=UTF-8\r\n" +
                "Content-Length: " + errorFile.length() + "\r\n" +
                "\r\n";

        System.out.println(" ");
        dos.writeBytes(response);
    }

    private void sendBody404(DataOutputStream dos) throws IOException {
        byte[] body = Files.readAllBytes(Paths.get("src/main/www/404.html"));
        dos.write(body, 0, body.length);
        dos.flush();
    }

    private void sendBodyLogo(DataOutputStream dos) throws IOException {
        byte[] body = Files.readAllBytes(Paths.get("src/main/www/images/logo.png"));
        dos.write(body, 0, body.length);
        dos.flush();

    }

    private void openStreams() {
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println("Could not open streams.");
        }
    }

    private void closeResources() {
        try {
            bufferedReader.close(); //first close buffer. After close sockets
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not close streams.");
        }
    }

}