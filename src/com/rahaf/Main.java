package com.rahaf;

import com.rahaf.client.ClientStub;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception{
        JFrame frame =new JFrame("App");
        frame.setContentPane(new Form().getPanel());
        frame.pack();

        frame.setVisible(true);
    }
}
