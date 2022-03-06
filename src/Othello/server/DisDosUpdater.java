package Othello.server;


import Othello.model.*;
import Othello.othelloController.OthelloView;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A Runnable Class that first starts the clientServer and makes the connection.
 * Then listens to the InputStream and updates the client thereafter.
 * @Version 2022-03-06
 */

public class DisDosUpdater implements Runnable{

    private Thread thread;
    private ServerSocket ServSock;
    private ObjectInputStream objectInputClient;
    private ObjectOutputStream objectOutputClient;
    private Socket clientSocket;
    private Socket serverSocket;
    private int port = 5000;
    private String ip = "142.93.106.21";
  //  private String ip = "localhost";
    private boolean yourTurn = true;
    private boolean serverIsUp=false;
    private boolean start = true;
    public OthelloView othelloview;

    /**
     * A Constructor with an OthelloView as parameter in order to call methods from the class
     * The Constructor initializes the othelloview variable, and then checks if the client should start a clientServer or connect to one
     * The if-statement only let the clientServer through to initialize the Input/Output-Streams
     * Lastly it commences a thread.
     * @param game_GUI - OthelloView
     */

    public DisDosUpdater(OthelloView game_GUI) {
        this.othelloview = game_GUI;
        Connecting();
        if(serverIsUp && start){
            listenForServerRequest();
        }
        thread = new Thread(this, "OthelloServer");
        thread.start();
    }

    /**
     * A runnable stuck in an eternal while-loop.
     * Updates yourTurn to match the one in OthelloView since the OutPutStream is updated there.
     * Updates the game and then checks with getState() if the client made a move or not.
     */
    @Override
    public void run(){
        while(true) {

            this.yourTurn = othelloview.getViewTurn();
            othelloview.flipButtons();
            getState();


        }
    }

    /**
     * getState() lets the client through if it's not their turn.
     * It stops the program at "objectInputClient.readObject()" until the opponent has sent their move with their OutPutStream.
     * Afterwards it updates the boards enum-colors to match the incoming games one.
     * It sets the label of whose turn it is and also flips whose turn it really is.
     * Prints stacktrace on IOException or ClassNotFoundException
     */
    private void getState() {
          if (!yourTurn) {
        try {
            System.out.println("I SHOULD WAIT FOR A MOVE TO BE MADE");
            Board space = (Board) objectInputClient.readObject();//ALSO WORKS AS A STOP LIKE .accept()
            System.out.println(space);
            System.out.println("Received packages");
            othelloview.getGame().getBoard().setBoard(space);
            othelloview.getGame().changeTurnLabel();
            othelloview.getBottomLabel().setText("Turn: " + othelloview.getGame().getFakeColor());
            othelloview.setViewTurn(true);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();

        }
    }
    }

    /**
     * Connects to server on the specified IP.
     * Checks if InputStream from server gave codeword or IP.
     * If it gave codeword, the client will start a clientServer, if it gave an IP the client instead connects to the given IP.
     * If it connects to the given IP, the client will change turn, pick the second color and create ObjectInput and ObjectOutputStreams.
     * Prints stacktrace on IOException.
     */
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
                System.out.println(address + " is adress");
                clientSocket = new Socket(address, port);
                objectInputClient = new ObjectInputStream(clientSocket.getInputStream());
                objectOutputClient = new ObjectOutputStream(clientSocket.getOutputStream());
                othelloview.setObjectOutputClient(objectOutputClient);
                othelloview.getGame().multiChangeTurn();
                othelloview.getGame().changeTurnLabel();
                othelloview.getGame().setColor();
                othelloview.setViewTurn(false);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Unable to connect to: " + ip + " At port:" + port + " | Waiting");
        }
    }

    /**
     * In case the codeword was given, this method will start a clientServer on their IP and set the turn to be their turn.
     * It also sets the boolean "serverIsUp" to true, causing the if-statement in the Constructor to let it through to the "listenForServerRequest".
     * Prints stacktrace on Exception and closes the program.
     */
    private void initServ() {
        try {
            ServSock = new ServerSocket(port);
            System.out.println("clientServer initialized at port: " + ServSock.getInetAddress());
            serverIsUp = true;
            othelloview.setViewTurn(true);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * If the client started a server, it then waits in this method for another client to connect to it at "listenerSocket = ServSock.accept();".
     * After a connection is made, it creates an ObjectInputStream and a ObjectOutputStream.
     * The method sends the ObjectOutputStream to OthelloView where it's used in the ActionListener.
     * The boolean "start" becomes false, locking this method until restart of the program.
     * Prints stacktrace on IOException.
     */
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
