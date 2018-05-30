package com.rahaf;

import com.rahaf.client.ClientStub;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception{
        JFrame frame =new JFrame("App");
        frame.setContentPane(new Form().getPanel());
        frame.pack();
        int width= Toolkit.getDefaultToolkit().getScreenSize().width/2;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height/2;
        frame.setSize(width,height);
        frame.setVisible(true);
    }
}
