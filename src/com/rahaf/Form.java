package com.rahaf;

import com.rahaf.client.ClientStub;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    try {
                        if(comboBox1.getSelectedItem().toString().equalsIgnoreCase("add")){


                                mClientStub.makeAdd(Integer.parseInt(textField1.getText()),
                                        Integer.parseInt(textField2.getText()), value -> {
                                            labelResult.setText(value.toString());
                                        });



                        }else if(comboBox1.getSelectedItem().toString().equalsIgnoreCase("Factorial")){
                            mClientStub.findFactorial(Integer.parseInt(textField1.getText()),value -> {
                                labelResult.setText(value.toString());
                            });
                        }
                    }catch (Exception x){x.printStackTrace();}
            }
        });

        comboBox1.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(comboBox1.getSelectedItem().toString().equalsIgnoreCase("Factorial")){
                    textField2.setVisible(false);
                }else{
                    textField2.setVisible(true);
                }
            }
        });
    }

    public JPanel getPanel(){
        return panel;
    }
}
