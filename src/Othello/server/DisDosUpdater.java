package Othello.server;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class DisDosUpdater implements Runnable{

    private Thread thread;
    private ServerSocket ServSock;
    private DataOutputStream dos;
    private DataInputStream dis;
    private int errors=0;
    private boolean cantConnect = false;

    public DisDosUpdater() {
        if (cantConnect) {
            thread = new Thread(this, "OthelloServer");
            thread.start();
        }
    }

    @Override
    public void run(){
        while(true) {
            listenForServerRequest();
        }
    }

    public void setCantConnect(boolean connecting){
        this.cantConnect = connecting;
    }

    public boolean DisDosWaiter(){
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
