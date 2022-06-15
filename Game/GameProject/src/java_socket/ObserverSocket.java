package java_socket;

import game_interface.Board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ObserverSocket extends Thread{
    public Socket socket;
    public DataInputStream inputBuffer;
    public DataOutputStream outputBuffer;

    private static ObserverSocket single_instance = null;

    public ObserverSocket() {

    }

    public static ObserverSocket getInstance(){
        if (single_instance == null)
            single_instance = new ObserverSocket();

        return single_instance;
    }

    public void run(){
        try {
            //Socket is created
            socket = new Socket("localhost", 8080);
            System.out.println("Connected");
            //Waits until all the data has been collected then closes the socket
            System.out.println(readData());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String readData() {

        int read = -1;

        byte [] buffer = new byte[5*1024], obtainData;

        try {

            if((read=socket.getInputStream().read(buffer))>-1){
                obtainData = new byte[read];
                System.arraycopy(buffer,0,obtainData,0,read);
                return new String(obtainData, StandardCharsets.UTF_8);
            }

        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
        return null;

    }





}
