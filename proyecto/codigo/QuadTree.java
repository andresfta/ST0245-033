import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.io.*;

class Pee{
    double x, y, value;

    Pee(double x, double y, double value) {
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
    List<Pee> Pees;
    QuadTree northWest = null;
    QuadTree northEast = null;
    QuadTree southWest = null;
    QuadTree southEast = null;
    Boundry boundry;

    static ArrayList<Double> cx;
    static ArrayList<Double> cy;

    QuadTree(int level, Boundry boundry){
        this.level = level;
        Pees = new ArrayList<>();
        this.boundry = boundry;
        cx = new ArrayList<>(); cy = new ArrayList<>();
    }

    /* Traveling the Graph using Depth First Search*/
    static void dfs(QuadTree tree, double distance){
        if(tree == null || tree.Pees.size() == 0)return;
        System.out.printf("\nLevel = %d [X1 = %f Y1 = %f] \t[X2 =%f Y2 = %f] ", tree.level, tree.boundry.getxMin(), tree.boundry.getyMin(),
            tree.boundry.getxMax(), tree.boundry.getyMax());
        List<Pee> Temp = tree.Pees; 
        for(int i = 0; i < Temp.size(); i++) {
            for(int j = i + 1; j < Temp.size(); j++){
                // System.out.printf(" \n\t  x = %f y = %f", pee.x, pee.y);
                if(Math.abs(Temp.get(i).x - Temp.get(j).x) < distance && Math.abs(Temp.get(i).y - Temp.get(j).y) < distance){
                    System.out.printf(" \n\t  x1 = %f y1 = %f \t x2 = %f y2 = %f", Temp.get(i).x, Temp.get(i).y, Temp.get(j).x, Temp.get(j).y);
                }
            }
        }
        if(tree.Pees.size() == 0) {
            System.out.printf(" \n\t  Leaf Node.");
        }
        dfs(tree.northWest, distance);
        dfs(tree.northEast, distance);
        dfs(tree.southWest, distance);
        dfs(tree.southEast, distance);
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
        Pee Pee = new Pee(x, y, value);
        if(Pees.size() < MAX_CAPACITY){
            Pees.add(Pee);
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

    public static void main(String args[]) throws FileNotFoundException{
        QuadTree anySpace = new QuadTree(1, new Boundry(-75.60, 6.30, -75.50, 6.37));
        GraphicsPee.main(args);
        if(!args[0].endsWith(".txt"))args[0] += ".txt";
        Scanner sc = new Scanner(new File(args[0]));
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            Scanner inLine = new Scanner(line);
            inLine.useDelimiter(",");
            // System.out.println("*" + line + "*");
            double x = Double.parseDouble(inLine.next()); cx.add(x); // System.out.println(x);
            double y = Double.parseDouble(inLine.next()); cy.add(y); // System.out.println(y);
            anySpace.insert(x,y,0);
        }
        // long startTime = System.currentTimeMillis();
        QuadTree.dfs(anySpace, Double.parseDouble(args[1]));
        // long endTime = System.currentTimeMillis();
        // System.out.println(endTime - startTime);
    }
}
