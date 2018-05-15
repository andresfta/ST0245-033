import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.*;

class Node{
    double x, y, value;

    Node(double x, double y, double value) {
        this.x = x;
        this.y = y;
        this.value = value; /* some data*/ 
    }
}

/* Using two points of Rectangular (Top,Left) & (Bottom , Right)*/
class Boundry{
    double xMin, yMin, xMax, yMax;

    public double getxMin() {
        return xMin;
    }

    public double getyMin() {
        return yMin;
    }

    public double getxMax() {
        return xMax;
    }

    public double getyMax() {
        return yMax;
    }

    public Boundry(double xMin, double yMin, double xMax, double yMax) {
        super();
        this.xMin = xMin;
        this.yMin = yMin;
        this.xMax = xMax;
        this.yMax = yMax;
    }

    public boolean inRange(double x, double y) {
        return (x >= this.getxMin() && x <= this.getxMax() && y >= this.getyMin() && y <= this.getyMax());
    }
}

public class QuadTree {
    final int MAX_CAPACITY = 4;
    int level = 0;
    List<Node> nodes;
    QuadTree northWest = null;
    QuadTree northEast = null;
    QuadTree southWest = null;
    QuadTree southEast = null;
    Boundry boundry;

    static ArrayList<Double> cx;
    static ArrayList<Double> cy;

    QuadTree(int level, Boundry boundry){
        this.level = level;
        nodes = new ArrayList<>();
        this.boundry = boundry;
        cx = new ArrayList<>(); cy = new ArrayList<>();
    }

    /* Traveling the Graph using Depth First Search*/
    static void dfs(QuadTree tree){
        if (tree == null)return;
        System.out.printf("\nLevel = %d [X1 = %f Y1 = %f] \t[X2 =%f Y2 = %f] ",
            tree.level, tree.boundry.getxMin(), tree.boundry.getyMin(),
            tree.boundry.getxMax(), tree.boundry.getyMax());
        for(Node node : tree.nodes) {
            System.out.printf(" \n\t  x = %f y = %f", node.x, node.y);
        }
        if (tree.nodes.size() == 0) {
            System.out.printf(" \n\t  Leaf Node.");
        }
        dfs(tree.northWest);
        dfs(tree.northEast);
        dfs(tree.southWest);
        dfs(tree.southEast);
    }

    void split(){
        double xOffset = this.boundry.getxMin() + (this.boundry.getxMax() - this.boundry.getxMin()) / 2;
        double yOffset = this.boundry.getyMin() + (this.boundry.getyMax() - this.boundry.getyMin()) / 2;

        northWest = new QuadTree(this.level + 1, new Boundry(this.boundry.getxMin(), this.boundry.getyMin(), xOffset, yOffset));
        northEast = new QuadTree(this.level + 1, new Boundry(xOffset, this.boundry.getyMin(), xOffset, yOffset));
        southWest = new QuadTree(this.level + 1, new Boundry(this.boundry.getxMin(), xOffset, xOffset, this.boundry.getyMax()));
        southEast = new QuadTree(this.level + 1, new Boundry(xOffset, yOffset, this.boundry.getxMax(), this.boundry.getyMax()));    
    }

    void insert(double x, double y, int value){
        if(!this.boundry.inRange(x, y)){
            return;
        }
        //
        Node node = new Node(x, y, value);
        if(nodes.size() < MAX_CAPACITY){
            nodes.add(node);
            return;
        }
        // Exceeded the capacity so split it in FOUR
        if(northWest == null){
            split();
        }
        // Check coordinates belongs to which partition 
        if(this.northWest.boundry.inRange(x, y))
            this.northWest.insert(x, y, value);
        else if(this.northEast.boundry.inRange(x, y))
            this.northEast.insert(x, y, value);
        else if(this.southWest.boundry.inRange(x, y))
            this.southWest.insert(x, y, value);
        else if(this.southEast.boundry.inRange(x, y))
            this.southEast.insert(x, y, value);
        // System.out.printf("ERROR : Unhandled partition %f, %f \n", x, y);
    }

    static void graphic() throws FileNotFoundException{
        main(null);
        GraphicsPee gp = new GraphicsPee();
        ArrayList<Integer> CX = new ArrayList<>();
        ArrayList<Integer> CY = new ArrayList<>();
        for(Double x: cx){CX.add((int)(x * 10000) + 756000);}
        for(Double y: cy){CY.add(63700 - (int)(y * 10000));}
        gp.coordinates(CX, CY);
        // System.out.println(CX + "\n" + CY);
        gp.main(null);
        // System.out.println(CX);
        // System.out.println(CY);
    }

    public static void main(String args[]) throws FileNotFoundException{
        QuadTree anySpace = new QuadTree(1, new Boundry(-75.60, 6.30, -75.50, 6.37));
        Scanner sc = new Scanner(new File("Datos100000.txt"));
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            Scanner inLine = new Scanner(line);
            inLine.useDelimiter(",");
            // System.out.println("*" + line + "*");
            double x = Double.parseDouble(inLine.next()); cx.add(x); System.out.println(x);
            double y = Double.parseDouble(inLine.next()); cy.add(y); System.out.println(y);
            anySpace.insert(x,y,0);
        }
        // long startTime = System.currentTimeMillis();
        // QuadTree.dfs(anySpace);
        // long endTime = System.currentTimeMillis();
        // System.out.println("*" + (endTime - startTime) + "*");
        // QuadTree.dfs(anySpace);
    }
}
