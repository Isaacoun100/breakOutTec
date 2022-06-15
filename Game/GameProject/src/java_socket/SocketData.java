package java_socket;

import java.io.*;

/**
 * Data to send through the socket
 */
public class SocketData implements Serializable{
    public int c = 0;
    public String d = "";

    /**
     * Constructor. Saves the text in the attribute d and calculates the length
     * @param text
     */
    public SocketData (String text){
        if(text != null){
            c = text.length();
            d = text;
        }
    }

    /**
     * Method to return a string that represents the value of all the attributes
     * @return string
     */
    public String toString(){
        String result;
        result = Integer.toString(c) + " -- " + d;
        return result;
    }

    /**
     * Method to write all the attributes of this class in an DataOutputStream so that it can be understood by a program in C
     * @param out DataOutputStream : Helps serializing the message
     * @throws IOException
     */
    public void writeObject(DataOutputStream out) throws IOException{
        //It sends the length of the text +1 bc of '\0' needed in C
        out.writeInt(c+1);
        //It sends the string as bytes
        out.writeBytes(d);
        //It sends the '\0' as the last symbol
        out.writeByte('\0');

    }

}
