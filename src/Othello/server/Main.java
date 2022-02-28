package Othello.server;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main{
    private ServerSocket ServSock;
    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);
    private int port = 1234;
    private String ip = "142.93.106.21";
    private DisDosUpdater DDU = new DisDosUpdater();

    private boolean accepted = false;


    public Main(int i) {
        while (port < 1025 || port > 65535) {
            System.out.println("ye fucked up");
            port = scanner.nextInt();
        }
        initServ();
    }

    public Main() {
            if (Connecting()) {
                this.accepted = true;
            } else {
                this.accepted = false;
            }
        if(!accepted){
            DDU.setCantConnect(true);
        }
    }

    private void initServ() {
        try {
            ServSock = new ServerSocket(port, 8, InetAddress.getByName(ip));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean Connecting() {
        try {
            socket = new Socket(ip, port);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Unable to connect to: " + ip + ":" + port + " | Waiting");
            return false;
        }
        return true;
    }

    public static void main(String[] args){
       new Main(1);
    }
}
