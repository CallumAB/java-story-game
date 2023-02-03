package com.example.backend;//import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.Integer.valueOf;

import com.example.backend.Node;

public class NodeCollection {

    private ArrayList<Node> nodes;
/****************************************************/
/*************    SUPPORT MAP BUILDER  **************/
/****************************************************/
/****************************************************/
    public Node locateNodeBy(int nodeID) {
        for (Node n : nodes) {
            if (nodeID == n.getID()) {
                return n;
            }
        }
        return new Node();
    }
    public ArrayList<Node> arrayList(){
        return nodes;
    }
    public Node get(int index){ return nodes.get(index); }
/****************************************************/
/**************         BUILD      ******************/
/****************************************************/
/****************************************************/
    public NodeCollection(InputStream prc)  throws FileNotFoundException {
        //java.io.File prc = new java.io.File("src/data.csv");
        Scanner fileRef = new Scanner(prc);
        nodes = new ArrayList<Node>();
        Node node;
        while (fileRef.hasNext()) {
            String line = fileRef.nextLine();
            node = mapFields(line);
            nodes.add(node);
        }
        fileRef.close();
    }

    //private @NotNull
    Node mapFields(String line) {
        String[] stringArray = line.split(",");
        Node n = new Node();
        n.setID(valueOf(stringArray[0]));
        n.setYesID(valueOf(stringArray[1]));
        n.setNoID(valueOf(stringArray[2]));
        n.setDescription(stringArray[3]);
        n.setQuestion(stringArray[4]);
        n.setAttribute(stringArray[5]);
        return n;
    }

    public String toString(){
        String str = "";
        for(Node n : nodes){
            str += n.toString() + "\n";
        }
        return str;
    }

    public int getNodeLength(){

        return nodes.size();
    }
}
