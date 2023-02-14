package com.solvd.taxiSerivce;
import java.io.File;
import java.io.IOException;



import com.solvd.taxiSerivce.Payment;

import main.java.com.solvd.bookingservice.Account;
import main.java.com.solvd.bookingservice.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class Runner {
    static Logger logger = Logger.getLogger(Runner.class.getName());
    public static void main(String[] args) throws FileNotFoundException {
        // DOM Parse
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document d = builder.parse("Payment.xml");
            Node node = d.getElementsByTagName("Payment").item(0);
            logger.info("ExpirationDate: " + node.getAttributes().getNamedItem("username").getTextContent());
            logger.info("SecurityCode: " + node.getAttributes().getNamedItem("password").getTextContent());
            logger.info("CreditCardNumber: " + node.getAttributes().getNamedItem("password").getTextContent());
        } catch (Exception e) {
            logger.info("Exception caught");
        }


        
    

    
   }
}
