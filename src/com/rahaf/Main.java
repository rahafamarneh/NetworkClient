package com.rahaf;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception{
        Socket socket = new Socket("localhost",3000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintStream writer = new PrintStream(socket.getOutputStream());
        writer.println("fact 5");
        String line ;
        while ( (line=reader.readLine())!= null && !line.equals("stop")){



            System.out.print("from server " + line);
        }

    }
}
