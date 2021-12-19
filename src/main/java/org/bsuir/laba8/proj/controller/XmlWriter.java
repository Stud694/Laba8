package org.bsuir.laba8.proj.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XmlWriter {

    public final String XMLFILEPATH = "src/main/resources/unload.xml";

    public XmlWriter(){}

    public boolean write(ArrayList<String> list){
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElementNS("", "lines");
            doc.appendChild(rootElement);

            for(int i = 0; i < list.size(); i++){
                Element node = doc.createElement("line");
                node.setAttribute("id", String.valueOf(i));
                node.appendChild(doc.createTextNode(list.get(i)));
                rootElement.appendChild(node);
            }

            //объект TransformerFactory для печати
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult file = new StreamResult(new File(XMLFILEPATH));
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}