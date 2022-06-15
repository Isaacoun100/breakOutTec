package java_socket;

import java.net.*;
import java.io.*;

/**
 * Class that allows to send and receive data through a socket implementing inheritance with the thread class
 */
public class GameClient extends Thread{
    public Socket socket;
    public DataInputStream inputBuffer;
    public DataOutputStream outputBuffer;

    private static GameClient single_instance = null;

    /**
     * Private constructor used in the Singleton design pattern
     */
    private GameClient() {

    }

    /**
     * @author Naheem J.
     * Method used for calling the single instance of gameclient (used for the Singleton design pattern)
     * @return Returns single_instance which is the only instance of GameClient
     */
    public static GameClient getInstance(){
        if (single_instance == null)
            single_instance = new GameClient();

        return single_instance;
    }
    /**
     * connects the client socket with the server socket
     * sets up the socket in a specific ip address and port
     */
    public void run(){
        try {
            //Socket is created
            socket = new Socket("localhost", 6969);
            System.out.println("Connected");
            //Waits until all the data has been collected then closes the socket
            socket.setSoLinger(true, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * Reads the object or string that is sent
     */
    public void readData() {
        try {
            //Gets a read only stream to read objects
            inputBuffer = new DataInputStream(socket.getInputStream());
            //Creates a socketData and reads what it was sent from C
            SocketData data = new SocketData("");
            data.readObject(inputBuffer);

            System.out.println("Java Client: Received " + data.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sends and object
     *
     * @param textToSend object that gets sent
     */
    public void sendData(String textToSend) {
        try {

            //Creates a buffer to send an object
            outputBuffer = new DataOutputStream(socket.getOutputStream());

            //creates a data and sends it through the output stream
            SocketData aux = new SocketData(textToSend);
            aux.writeObject(outputBuffer);
            System.out.println("Java Client: Sent " + aux.toString());
            socket.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
