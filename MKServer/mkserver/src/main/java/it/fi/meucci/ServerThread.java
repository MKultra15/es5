package it.fi.meucci;

import java.io.*;
import java.net.*;

public class ServerThread extends Thread
{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public ServerThread(Socket socket){
        this.client = socket;
        try
        {
            server = new ServerSocket(6789);
        }
        catch(Exception e)
        {
            System.out.println("Socket chiuso");
        }
    }

    public void run()
    {
        try
        {
            comunica();
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public Socket attendi()
    {
        try
        {
            System.out.println("1 SERVER partito in esecuzione ...");
            client = server.accept();
            //server.close();

            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Errore durante l'istanza del server !");
            System.exit(1);
        }
        return client;
    }

    public void comunica () throws Exception
    {
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outVersoClient = new DataOutputStream(client.getOutputStream());
        for(;;)
        {
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta.equals("FINE")){
                outVersoClient.writeBytes(stringaRicevuta + "=> server in chiusura..." + '\n');
                System.out.println("Echo sul server in chiusura :" + stringaRicevuta);
                break;
            }
            else
            {
                outVersoClient.writeBytes(stringaRicevuta + " (ricevuta e ritrasmessa)" + '\n');
                System.out.println("6 Echo sul server :" + stringaRicevuta);
            }
        }
        outVersoClient.close();
        inDalClient.close();
        System.out.println("9 chiusura socket" + client);
        client.close();
    }
}
