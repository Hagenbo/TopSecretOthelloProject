package Othello.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main implements Runnable{
    private ServerSocket ServSock;
    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);
    private int port = 1234;
    private String ip = "localhost";
    private Thread thread;


    private boolean accepted = false;


    public Main(){
        ip = scanner.nextLine();
        port = scanner.nextInt();

        while (port < 1025 || port > 65535) {
            System.out.println("ye fucked up");
            port = scanner.nextInt();
        }
        Connecting();
        /* if (!Connecting()) {
            initServ();
        }*/

        thread = new Thread(this, "OthelloServer");
        thread.start();
    }

    @Override
    public void run(){
while(true) {
    listenForServerRequest();
}
}

    private void listenForServerRequest() {
        Socket socket = null;
        try {
            socket = ServSock.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
            System.out.println("Great success!");
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }

    private boolean Connecting() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            accepted = true;
        } catch (IOException e) {
            System.out.println("Unable to connect to: " + ip + ":" + port + " | Going solo");
            return false;
        }
        System.out.println("accepted");
        return true;
    }

    private void initServ() {
        try {
            ServSock = new ServerSocket(port, 8, InetAddress.getByName(ip));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
       new Main();
    }
}
