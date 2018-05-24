import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class Graphics extends JPanel
{
    int x, y, w, h;
    java.util.ArrayList<Pee> pees = new java.util.ArrayList<>();
    java.util.ArrayList<Pee> collisions = new java.util.ArrayList<>();
    static boolean hasFrame = false;

    Graphics(String fileName) throws java.io.FileNotFoundException{
        java.util.Scanner sc = new java.util.Scanner(new java.io.File(fileName));
        sc.nextLine();
        while(sc.hasNextLine()){
            String line = sc.nextLine();
            java.util.Scanner inLine = new java.util.Scanner(line);
            inLine.useDelimiter(",");
            // System.out.println("*" + line + "*");
            double x = Double.parseDouble(inLine.next());
            double y = Double.parseDouble(inLine.next());
            Pee p = new Pee(x, y, 0.0009);
            pees.add(p);
        }
        // Collisions
        try{
            sc = new java.util.Scanner(new java.io.File(fileName.replace("Datos", "Collisions")));
            while(sc.hasNextLine()){
                String line = sc.nextLine();
                java.util.Scanner inLine = new java.util.Scanner(line);
                inLine.useDelimiter(",");
                double x = Double.parseDouble(inLine.next());
                double y = Double.parseDouble(inLine.next());
                Pee p = new Pee(x, y, 0.0009);
                collisions.add(p);
            }
        }catch(java.io.FileNotFoundException f){
            System.out.println("ERROR: File Not Found");
        }
    }

    public void paintComponent(java.awt.Graphics g){
        g.drawRect(10, 10, 1000, 700);
        g.setColor(Color.BLUE);
        for(Pee p: pees){
            g.fillOval((int)(p.x * 10000) + 756000, 63700 - (int)(p.y * 10000), 8, 8);
        }
        g.setColor(Color.RED);
        for(Pee c: collisions){
            g.fillOval((int)(c.x * 10000) + 756000, 63700 - (int)(c.y * 10000), 8, 8);
        }
    }

    public static void main(String[] args) throws java.io.FileNotFoundException{
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Graphics(args[0]));
        frame.setSize(1100,800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
