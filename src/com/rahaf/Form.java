package com.rahaf;

import com.rahaf.client.ClientStub;

import javax.swing.*;

public class Form {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTextField textField2;
    private JButton button1;
    private JPanel panel;
    private JLabel labelResult;
    private ClientStub mClientStub;


    public Form() {
        mClientStub = new ClientStub();
        comboBox1.addItem("Add");
        comboBox1.addItem("Factorial");

        button1.addActionListener(e -> {
                labelResult.setText("Sending..");
                    try {
                        if(comboBox1.getSelectedItem().toString().equalsIgnoreCase("add")){

                            mClientStub.makeAdd(Integer.parseInt(textField1.getText()),
                                    Integer.parseInt(textField2.getText()), value -> {
                                        if(value== null){ //error
                                            labelResult.setText("Error");
                                        }else
                                            labelResult.setText(value.toString());
                                        });

                        }else if(comboBox1.getSelectedItem().toString().equalsIgnoreCase("Factorial")){

                            mClientStub.findFactorial(Integer.parseInt(textField1.getText()),value -> {
                                if(value== null){ //error
                                    labelResult.setText("Error");
                                }else
                                    labelResult.setText(value.toString());

                            });
                        }
                    }catch (Exception x){x.printStackTrace();}
        });

        comboBox1.addItemListener(e -> {
                if(comboBox1.getSelectedItem().toString().equalsIgnoreCase("Factorial")){
                    textField2.setVisible(false);
                }else{
                    textField2.setVisible(true);
                }
            }
        );
    }

    public JPanel getPanel(){
        return panel;
    }
}
