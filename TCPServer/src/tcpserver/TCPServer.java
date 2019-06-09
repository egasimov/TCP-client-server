package tcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

class ServerThread extends Thread {

    public void run() {
        runServer();
    }

    public  void runServer() {
        try {
            ServerSocket ss = new ServerSocket(6666);

            while (true) {
                Socket conversation = ss.accept();
                DataOutputStream dout = new DataOutputStream(conversation.getOutputStream());
                DataInputStream dis = new DataInputStream(conversation.getInputStream());
                
                String ClientMessage = dis.readUTF();
                if (ClientMessage != null) {
                    System.out.println("Client answered");
                }
                dout.writeUTF("SERVER cavab verdi");
                System.out.println("SERVER RESPONDED");
            }
        } catch (Exception ex) {
            System.out.println("Uygun portda socket yarada bilmedi :fromServer");
        }
    }

}

public class TCPServer {

    public static void main(String[] args) {
       new ServerThread().start();
    }
}
