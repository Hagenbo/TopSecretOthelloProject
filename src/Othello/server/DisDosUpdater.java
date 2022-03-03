package Othello.server;


import Othello.model.*;
import Othello.othelloController.OthelloView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DisDosUpdater implements Runnable{

    private Thread thread;
    private ServerSocket ServSock;
    private ObjectInputStream objectInputClient;
    private ObjectOutputStream objectOutputClient;
    private Socket clientSocket;
    private Socket serverSocket;
    private int port = 2345;
  //  private String ip = "142.93.106.21";
    private String ip = "localhost";
    private boolean yourTurn = true;
    private boolean serverIsUp=false;
    private boolean start = true;
    public OthelloView othelloview;

    public DisDosUpdater(OthelloView game_GUI) {
        this.othelloview = game_GUI;
        Connecting();
        if(serverIsUp && start){
            listenForServerRequest();
        }
        thread = new Thread(this, "OthelloServer");
        thread.start();
    }

    @Override
    public void run(){
        while(true) {

            this.yourTurn = othelloview.getViewTurn();
            othelloview.flipButtons();
            getState();


        }
    }

    private void getState() {
          if (!yourTurn) {
        try {
            System.out.println("I SHOULD WAIT FOR A MOVE TO BE MADE");
            Board space = (Board) objectInputClient.readObject();//ALSO WORKS AS A STOP LIKE .accept()
            System.out.println(space);
            System.out.println("Received packages");
            othelloview.getGame().getBoard().setBoard(space);

            othelloview.setViewTurn(true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
    }

    private void Connecting() {
        try {
            serverSocket = new Socket(ip, 1234);
            BufferedReader ipReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            String address = ipReader.readLine();
            System.out.println(address);
            if(address.equals("notIp")){
                initServ();
            } else {
                System.out.println("Connected to ClientServer");
                clientSocket = new Socket(address, port);
                objectInputClient = new ObjectInputStream(clientSocket.getInputStream());
                objectOutputClient = new ObjectOutputStream(clientSocket.getOutputStream());
                othelloview.setObjectOutputClient(objectOutputClient);
                othelloview.getGame().multiChangeTurn();
                othelloview.getGame().setColor();
                othelloview.setViewTurn(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to: " + ip + " At port:" + port + " | Waiting");
        }
    }

    private void initServ() {
        try {
            ServSock = new ServerSocket(port);
            System.out.println("clientServer initialized at port: " + ServSock.getLocalSocketAddress());
            serverIsUp = true;
            othelloview.setViewTurn(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void listenForServerRequest() {
        Socket listenerSocket = null;
        try {
            System.out.println("Waiting for accept");
            listenerSocket = ServSock.accept();
            System.out.println("Accepted" + listenerSocket.getRemoteSocketAddress() + listenerSocket.getInetAddress());
            objectOutputClient = new ObjectOutputStream(listenerSocket.getOutputStream());
            othelloview.setObjectOutputClient(objectOutputClient);
            objectInputClient = new ObjectInputStream(listenerSocket.getInputStream()); //DATA är ints
            System.out.println("Great success!");
            this.start = false;
        } catch (IOException e) {
            System.out.println("fail");
            e.printStackTrace();
        }
    }
}
