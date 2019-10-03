/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package catalog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;




/**
 *
 * @author user
 */
public class Catalog {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
    
     */
    public static void main(String[] args) throws FileNotFoundException  {
     
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
           
            
            XPathFactory factory = XPathFactory.newInstance();
            XPath path = factory.newXPath();
            XPathExpression xPathEx = path.compile("//book[price>10 and translate(publish_date,'-','')>20050000]");
            
            File doc = new File("xml/catalog.xml");
            InputSource source = new InputSource(new FileInputStream(doc));
            
            Object result = xPathEx.evaluate(source, XPathConstants.NODESET);
            NodeList nodeList = (NodeList)result;
            
            for (int i = 0; i < nodeList.getLength(); i++) {
                System.out.print(nodeList.item(i).getNodeName() + " ");
                System.out.println(nodeList.item(i).getAttributes().item(0));
                System.out.println(" Author: " + nodeList.item(i).getFirstChild().getNextSibling().getTextContent());
                System.out.println(" Title: " + nodeList.item(i).getFirstChild().getNextSibling().getNextSibling().getNextSibling().getTextContent());
                System.out.println(" Genre: " + nodeList.item(i).getFirstChild().getNextSibling(). getNextSibling().getNextSibling().getNextSibling().getNextSibling().getTextContent());
                System.out.println(" Price: " + nodeList.item(i).getLastChild().getPreviousSibling().getPreviousSibling().getPreviousSibling().getPreviousSibling().getPreviousSibling().getTextContent());
                System.out.println(" Publish date: " + nodeList.item(i).getLastChild().getPreviousSibling().getPreviousSibling().getPreviousSibling().getTextContent());
                System.out.println(" Description: " + nodeList.item(i).getLastChild().getPreviousSibling().getTextContent());
                System.out.print("\n");  
            }  
        } catch (XPathExpressionException ex) {
            Logger.getLogger(Catalog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

