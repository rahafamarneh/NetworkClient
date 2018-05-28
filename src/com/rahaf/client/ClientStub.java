package com.rahaf.client;

import org.json.JSONObject;

public class ClientStub {

    private DBController mDBController;

    public ClientStub() {
        mDBController = new DBController();
    }
    public void makeAdd(int x, int y) throws Exception{
        String funName = "Add";

        //generate add-operation request
        JSONObject jsonObjectRequest = new JSONObject();
        jsonObjectRequest.put("fun",funName);
        jsonObjectRequest.put("n1",x);
        jsonObjectRequest.put("n2",y);

        String stringRequest = jsonObjectRequest.toString();

        ServerModel serverModel = mDBController.getServerFromDatabase(funName);
        if(serverModel == null){ // does not exist in DB
            new MakeRequestToBinder(funName, response -> {
                if(response != null){ // server found from binder
                    System.out.println("server found from binder " + response.getIp());
                    mDBController.saveServer(funName,response); //save it locally

                    connectToServer(stringRequest,response);

                }else{
                    System.out.println("Couldn't found a Server");
                }
            }).start();
        }else {
            connectToServer(stringRequest, serverModel);
        }
    }

    private void connectToServer(String requestString, ServerModel server) {
        new MakeRequestToServer(requestString,server, response -> {
            if(requestString != null){
                System.out.println("Response from server = "+response);

            }else { // some error
                System.out.println("server error");
            }
        }).start();
    }




}
