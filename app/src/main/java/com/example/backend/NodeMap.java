package com.example.backend;

import java.io.FileNotFoundException;
import java.io.InputStream;


public class NodeMap {

    private Node head;
    private Node currentNode;
    private Node deathNode;
    private int hearts;
    private String[] items;

/****************************************************/
/**************      NAVIGATE       *****************/
/****************************************************/
/****************************************************/
    public Node currentNode() { return currentNode;}

    public void noDecision(){
        currentNode = currentNode.getYesNode();
        checkAttribute(currentNode);
    }

    public void decision(int decision) {
        switch (decision) {
            case 1:
                currentNode = currentNode.getYesNode();
                break;
            case 2:
                currentNode = currentNode.getNoNode();
                break;
        }
        checkAttribute(currentNode);

    }

    public void checkAttribute(Node currentNode){

        String[] checkAttribute = currentNode.getAttribute().split("-");

        //Damage Nodes
        if (currentNode.getAttribute().equalsIgnoreCase("damage")) {
            this.hearts -= 1;
            if (hearts == 0) {
                boolean cheatDeath = checkItem("cheatDeath");
                this.hearts = 3;
                if (!cheatDeath) {
                    this.currentNode = deathNode;
                }
            }
        }

        //Check Nodes
        else if (checkAttribute.length == 2) {
            boolean check = checkItem(checkAttribute[1]);
            if(check){decision(1);}
            else{decision(2);}
        }
        //Boss Node
        else if (currentNode.getAttribute().equalsIgnoreCase("boss")) {
            if (hearts >= 2 && (checkItem("sword") || checkItem("strengthPotion"))) {
                decision(1);
            } else {
                decision(2);
            }
        }

        //Reset Node
        else if (currentNode.getAttribute().equalsIgnoreCase("start")) {
            items = new String[5];
            this.hearts = 3;
        }

        //Item Nodes
        else {
            String[] potentialItems = {"invisPotion", "cheatDeath", "map", "sword", "strengthPotion"};

            for (int i = 0; i < potentialItems.length; i++) {
                if (currentNode.getAttribute().equalsIgnoreCase(potentialItems[i])) {
                    items[i] = potentialItems[i];
                }
            }
        }
    }

    public boolean checkItem(String itemToCheck) {
        for (String item : items) {
            if (item != null) {
                if (item.equalsIgnoreCase(itemToCheck)) {
                    return true;
                }
            }
        }
        return false;
    }

    //GETTERS SETTERS

    public int getHearts() {return hearts;}
    public String[] getItems() {return items;}


/****************************************************/
/**************         BUILD      ******************/
/****************************************************/
/****************************************************/

    public NodeMap(InputStream prc)  {
        NodeCollection nodeCollection;   //scope: constructor only, part of process, no requirement to keep;
        items = new String[5];
        this.hearts = 3;
        try {
            nodeCollection = new NodeCollection(prc);
            head = nodeCollection.get(0);
        } catch (FileNotFoundException e) {
            //message
            return;
        }
        buildMap(nodeCollection);
        currentNode = head;
        deathNode = nodeCollection.get(nodeCollection.getNodeLength() - 1);
    }


    private void buildMap(NodeCollection nodeCollection)   {
        if (nodeCollection == null) {return;}
        for(Node source : nodeCollection.arrayList()){
            int yesID = source.getYesID();
            int noID = source.getNoID();
            Node yesNode = nodeCollection.locateNodeBy(yesID);
            Node noNode = nodeCollection.locateNodeBy(noID);
            source.setYesNode(yesNode);
            source.setNoNode(noNode);
        }
    }

    public String toString(){
        String string = "";
        string += yesPath() + "\n";
        string += noPath() + "\n";
        return string;
    }

    public String yesPath(){
        Node node = head;
        String string = "YES PATH\n";
        while(node != null) {
            string += node.toString() + "\n";
            node = node.getYesNode();
            if (node.getID() == 0) { node = null;}
        }
        return string;
    }

    public String noPath(){
        Node node = head;
        String string = "NO PATH\n";
        while(node != null) {
            string += node.toString() + "\n";
            node = node.getNoNode();
            if (node.getID() == 0) { node = null;}
        }
        return string;
    }



}