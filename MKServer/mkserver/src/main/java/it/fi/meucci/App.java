package it.fi.meucci;

import java.net.ServerSocket;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int a = 1;
        ServerStr server = new ServerStr();
        while(a != 0){
            server.attendi();
            server.comunica();
            System.out.println("In cerca di un nuovo client");
        }
    }

}

