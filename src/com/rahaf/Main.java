package com.rahaf;

import com.rahaf.client.ClientStub;

import java.io.*;
import java.net.Socket;

public class Main {

    public static void main(String[] args) throws Exception{
        ClientStub clientStub = new ClientStub();
        clientStub.makeAdd(22,5);
    }
}
