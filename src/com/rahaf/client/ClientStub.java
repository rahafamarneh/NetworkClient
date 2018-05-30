package com.rahaf.client;

import org.json.JSONObject;

public class ClientStub {

    private DBController mDBController;

    public ClientStub() {
        mDBController = new DBController();
    }
    public void makeAdd(int x, int y, ReturnValue mListener) throws Exception{
        String funName = "add";

        //generate add-operation request
        JSONObject jsonObjectRequest = new JSONObject();
        jsonObjectRequest.put("fun",funName);
        jsonObjectRequest.put("n1",x);
        jsonObjectRequest.put("n2",y);

        String stringRequest = jsonObjectRequest.toString();

        connectNetwork(funName,stringRequest,mListener);
    }

    public void findFactorial(int n, ReturnValue mListener) throws Exception{
        String funName = "fact";

        //generate factorial-operation request
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("fun",funName);
        jsonObject.put("n1",n);
        String stringRequest = jsonObject.toString();

        connectNetwork(funName,stringRequest,mListener);
    }
    private void connectNetwork(String funName,String stringRequest, ReturnValue mListener) throws Exception{
        ServerModel serverModel = mDBController.getServerFromDatabase(funName);
        if(serverModel == null){ // does not exist in DB
            new MakeRequestToBinder(funName, response -> {
                if(response != null){ // server found from binder
                    System.out.println("server found from binder " + response.getIp());
                    mDBController.saveServer(funName,response); //save it locally

                    connectToServer(stringRequest,response,mListener);

                }else{
                    System.out.println("Couldn't found a Server");
                    mListener.onReturnValue(null);
                }
            }).start();
        }else {
            connectToServer(stringRequest, serverModel,mListener);
        }
    }


    private void connectToServer(String requestString, ServerModel server, ReturnValue mListener) {
        new MakeRequestToServer(requestString,server, response -> {
            if(response != null){
                System.out.println("Response from server = "+response);

                    mListener.onReturnValue(response);

            }else { // some error
                System.out.println("server error");
                mListener.onReturnValue(null);
            }
        }).start();
    }

    public interface ReturnValue{
        void onReturnValue(Object value);
    }




}
