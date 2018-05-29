package com.rahaf.client;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URI;

public class MakeRequestToBinder extends Thread{
    private String functionName;

    public MakeRequestToBinder(String functionName, RequestToBinderListener mListener) {
        this.functionName = functionName;
        this.mListener = mListener;
    }

    private RequestToBinderListener mListener;
    @Override
    public void run() {
        try {
            Socket socket = new Socket("localhost",4000); // connect to binder
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream writer = new PrintStream(socket.getOutputStream());

            JSONObject jsonObjectRequest = new JSONObject();
            jsonObjectRequest.put("fun",functionName);
            writer.println(jsonObjectRequest.toString());
            String line;
            if ( (line= reader.readLine())!= null){
                System.out.println("receive from binder: " + line);
                JSONObject jsonObject = new JSONObject(line);
                if(jsonObject.getString("status").equals("found")){
                    String serverIp = jsonObject.getString("serverIp");
                    int port = jsonObject.getInt("serverPort");
                    mListener.onBinderResponse(new ServerModel(serverIp,port));
                }else{
                    mListener.onBinderResponse(null);
                }


            }



        }catch (Exception e){
            e.printStackTrace();
            mListener.onBinderResponse(null);
        }
    }


    public interface RequestToBinderListener{
        void onBinderResponse(ServerModel response);
    }
}