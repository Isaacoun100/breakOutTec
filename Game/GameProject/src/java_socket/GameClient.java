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

    /**
     * connects the client socket with the server socket
     * sets up the socket in a specific ip address and port
     */
    public void run(){
        try {
            //Socket is created
            socket = new Socket("localhost", 25557);
            System.out.println("Connected");
            //Waits until all the data has been collected then closes the socket
            socket.setSoLinger(true, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public GameClient() {

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