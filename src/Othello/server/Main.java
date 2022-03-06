package Othello.server;


import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

/**
 * Class for a server to pair up two connections.
 * @Version 2022-03-06
 */

public class Main{
    private ServerSocket ServSock;
    private OutputStream dos1;
    private InputStream dis1;
    private OutputStream dos2;
    private InputStream dis2;
    private Socket socket;
    private Scanner scanner = new Scanner(System.in);
    private int port = 1234;
    private String ip = "142.93.106.21";
   // private String ip = "localhost";
    private Socket connected;
    private int amountConnected = 0;
    private InetAddress inetaddress1;
    private InetAddress inetaddress2;


    /**
     * A Contructor with no parameters. The Constructor initializes a server on the selected IP.
     * After that, it uses the serversocket previously made in "initServ()" to wait for a connection to be made.
     * When a connection is made, the Constructor creates an ObjectInputStream and ObjectOutputStream.
     * If it's the first connection made, it puts a codeword in the OutPutStream, telling the connection to make a clientServer also storing the clients IP in the process.
     * If it's the second connection made, it puts the previously acquired IP in the OutPutStream, signaling to the client it shall connect via a serversocket to that IP.
     * Prints stacktrace on IOException.
     */
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

    /**
     * Initializes a server on the specified port for clients to connect to.
     * Prints Stacktrace on Exception.
     */
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


    /**
     * Creates a new Constructor Main();
     * @param args
     */
    public static void main(String[] args){
       new Main();
    }
}
