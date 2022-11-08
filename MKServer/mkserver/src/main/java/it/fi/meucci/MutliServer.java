package it.fi.meucci;

import java.net.ServerSocket;
import java.net.Socket;

public class MutliServer {
    public void avvio(){
        try
        {
            ServerSocket serverSocket = new ServerSocket(6789);
            for(;;)
            {
                System.out.println("1 server in attesa ...");
                Socket socket = serverSocket.accept();
                System.out.println();
            }
        }
    }
}
