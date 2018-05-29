package com.rahaf.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.URI;

public class MakeRequestToServer extends Thread{
    private String requestString;
    private ServerModel mServer;
    private RequestToServerListener mListener;

    public MakeRequestToServer(String requestString, ServerModel mServer, RequestToServerListener mListener) {
        this.requestString = requestString;
        this.mServer = mServer;
        this.mListener = mListener;
    }

    @Override
    public void run() {
        try (Socket socket = new Socket(mServer.getIp(),mServer.getPort())) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintStream writer = new PrintStream(socket.getOutputStream());
            writer.println(requestString);
            String line;
            if ( (line= reader.readLine())!= null){
                System.out.println("response from server="+line);
                JSONObject jsonObject = new JSONObject(line);

                if(jsonObject.getString("status").equals("success")){
                    mListener.onServerResponse(jsonObject.getString("result"));
                }else{
                    mListener.onServerResponse(null);
                }


            }

        }catch (Exception e){
            e.printStackTrace();
            mListener.onServerResponse(null);
        }
    }

    public interface RequestToServerListener{
        void onServerResponse(String response);
    }
}