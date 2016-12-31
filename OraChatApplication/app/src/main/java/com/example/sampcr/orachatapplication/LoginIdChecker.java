package com.example.sampcr.orachatapplication;

/**
 * Created by sampcr on 12/27/2016.
 */
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
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
//            System.out.println("Root element :"
//                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Login");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String currentEmail = eElement.getAttribute("Email");
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
//            System.out.println("Root element :"
//                    + doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName("Login");
            System.out.println("----------------------------");
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element :"
                        + nNode.getNodeName());
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    String currentPassword = eElement.getAttribute("Password");
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

    public static void createUser(String email, String password){
        try {

            File root = android.os.Environment.getExternalStorageDirectory();
            File dir = new File(root.getAbsolutePath() + "/ORA");
            dir.mkdirs();
            File inputFile = new File(dir, "LoginInformation.txt");

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document doc;
            Element rootElement;


            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            if (br.readLine() == null) {
                doc = docBuilder.newDocument();
                rootElement = doc.createElement("User");
                doc.appendChild(rootElement);
            }else{
                doc = docBuilder.parse(inputFile);
                rootElement = doc.getDocumentElement();
            }
            // root elements


            // staff elements
            Element Login = doc.createElement("Login");
            rootElement.appendChild(Login);

            // set attribute to staff element
            Attr attrEmail = doc.createAttribute("Email");
            attrEmail.setValue(email);
            Login.setAttributeNode(attrEmail);

            Attr attrPass = doc.createAttribute("Password");
            attrPass.setValue(password);
            Login.setAttributeNode(attrPass);

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(inputFile);

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        } catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (SAXException saxe) {
            saxe.printStackTrace();
        }
    }
}

