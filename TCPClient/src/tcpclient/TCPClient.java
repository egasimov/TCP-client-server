package tcpclient;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TCPClient {

    private DataInputStream input = null;
    private DataOutputStream output = null;
    private Socket socket = null;

    public void connectToServer() {
        try {
            System.out.println("connecting...");
            socket = new Socket("localhost", 6666);

            //socketin input ve output streami global deyisene atiriq.
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            System.out.println("connected succesfully");
            readAndWrite();
        } catch (Exception ex) {
            connectToServer();
        }
        //  readAndWrite();
    }

    private void readAndWrite() {
        System.out.println("TO STOP CHATTING WRITE  ONLY : QUIT");
        try {
            while (true) {
                //input from console
                String message = userInput();
                
                if(message.toUpperCase().contains("QUIT"))break;
                
                //messagge server-e yazildi
                output.writeUTF(message);
                String response = input.readUTF();
                System.out.println(response);
            }
        } catch(Exception e) {
            System.out.println("BAGLANTI QIRILDI //CONNECTION DESTROYED");
            connectToServer();
        }

    }

    String userInput() throws IOException {
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        String consoleinput = "";
        try {
            BufferedReader br = new BufferedReader(isr);
            //
            consoleinput = br.readLine();
        } catch (IOException e) {
            System.out.println("I0 Exception between User and console");
        }

        return consoleinput;
    }

    public static void main(String[] args) {
        TCPClient Client1 = new TCPClient();
        Client1.connectToServer();
    }
}
