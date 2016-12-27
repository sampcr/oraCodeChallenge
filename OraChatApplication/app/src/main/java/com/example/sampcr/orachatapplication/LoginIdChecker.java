package com.example.sampcr.orachatapplication;

/**
 * Created by sampcr on 12/27/2016.
 */
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class LoginIdChecker {
    private static final String TAG = LoginIdChecker.class.getName();


    public static boolean validEmail(String email) {
        try {
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/ORA");
            dir.mkdirs();
            File inputFile = new File(dir, "LoginInformation.txt");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Login");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String currentEmail = eElement.getAttribute("email");
                    if(currentEmail.equals(email)){
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Log.i(TAG, "found a file not found exception");
        } catch (SAXException e) {
            Log.i(TAG, "found a SAX exception exception");
        } catch (ParserConfigurationException e) {
            Log.i(TAG, "found a parse exception");
        } catch (IOException e) {
            Log.i(TAG, "found a IO exception");
        }
        return false;
    }

    public static boolean validPassword(String password) {
        try {
            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/ORA");
            dir.mkdirs();
            File inputFile = new File(dir, "LoginInformation.txt");
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Login");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String currentPassword = eElement.getAttribute("password");
                    if(currentPassword.equals(password)){
                        return true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            Log.i(TAG, "found a file not found exception");
        } catch (SAXException e) {
            Log.i(TAG, "found a SAX exception exception");
        } catch (ParserConfigurationException e) {
            Log.i(TAG, "found a parse exception");
        } catch (IOException e) {
            Log.i(TAG, "found a IO exception");
        }
        return false;
    }
}

