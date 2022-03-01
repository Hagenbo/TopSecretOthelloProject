package Othello.server;


import Othello.menus.*;
import Othello.othelloController.OthelloView;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class DisDosUpdater implements Runnable{

    private Thread thread;
    private ServerSocket ServSock;
    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket socket;
    private int port = 1235;
   // private String ip = "142.93.106.21";
    private String ip = "localhost";
    private int errors=0;
    private boolean cantConnect;
    private boolean yourTurn = true;

    public DisDosUpdater() {
        this.cantConnect = Connecting();
        if (!cantConnect) {
            thread = new Thread(this, "OthelloServer");
            thread.start();
        }
    }

    @Override
    public void run(){
        while(true) {
            //new StartApp(); DONT UNCOMMENT OH GOD
          //  sendState();

           // listenForServerRequest();

        }
    }

   /* private void sendState() {
        if (!yourTurn && !cantConnect) {
            try {
                int space = dis.readInt();

                  INSERT WHAT IT IS WE WANT TO SEND INTO "SPACE"
                 ITS POSSIBLE TO CHANGE FROM INT BUT THEN "dis.readInt()" NEEDS TO READ THAT FORMAT INSTEAD


                new StartApp();
                yourTurn = true;
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
    }*/

    private boolean Connecting() {
        try {
            socket = new Socket(ip, 1234);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            ObjectInputStream objin1;
            objin1 = new ObjectInputStream(dis);
            InetAddress address = (InetAddress) objin1.readObject();
            if(address == null){
                initServ();
                return true;
            } else {
                socket = new Socket(address, port);
            }
            this.yourTurn = false;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Unable to connect to: " + ip + " At port:" + port + " | Waiting");
            return true;
        }
        return false;
    }

    private void initServ() {
        try {
            ServSock = new ServerSocket(port);
            System.out.println( ServSock.getLocalSocketAddress());
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void setCantConnect(boolean connecting){
        this.cantConnect = connecting;
    }


    public boolean getCantConnect(){
        return !cantConnect;
    }


    private void listenForServerRequest() {
        Socket socket = null;
        try {
            socket = ServSock.accept();
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            System.out.println("Great success!");
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        new DisDosUpdater();
    }
}
