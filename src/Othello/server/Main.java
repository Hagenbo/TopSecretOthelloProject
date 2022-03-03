package Othello.server;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Main{
    private ServerSocket ServSock;
    private OutputStream dos1;
    private InputStream dis1;
    private OutputStream dos2;
    private InputStream dis2;
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);
    private int port = 1234;
   // private String ip = "142.93.106.21";
    private String ip = "localhost";
    private Socket connected;
    private int amountConnected = 0;
    private InetAddress inetaddress1;
    private InetAddress inetaddress2;



    public Main() {

        initServ();
        while (true) {
            try {
                System.out.println("WAITING");
                connected = ServSock.accept();
                if (amountConnected == 0) {
                    inetaddress1 = connected.getInetAddress();
                    dos1 = connected.getOutputStream(); //MIGHT NEED NEW DATAINPUTSTREAM AND NEW DATAOUTPUTSTREAM
                    dis1 = connected.getInputStream();
                    OutputStreamWriter obj1;
                    obj1 = new OutputStreamWriter(dos1);
                    obj1.write("notIp\n");
                    obj1.flush();
                    amountConnected = 1;
                } else {
                    System.out.println("IS DONE");
                    inetaddress2 = connected.getInetAddress();
                    dos2 = connected.getOutputStream();
                    dis2 = connected.getInputStream();
                    OutputStreamWriter obj2;
                    obj2 = new OutputStreamWriter(dos2);
                    obj2.write(inetaddress1.getHostAddress() + "\n");
                    obj2.flush();
                    amountConnected = 0;
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void initServ() {
        try {
            System.out.println("DID IT INITIALIZE?");
            ServSock = new ServerSocket(port);
           // System.out.println( ServSock.getLocalSocketAddress());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

//port, 8, InetAddress.getByName(ip)

    public static void main(String[] args){
       new Main();
    }
}
