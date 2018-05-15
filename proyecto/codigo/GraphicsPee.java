import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Random;
public class GraphicsPee extends JPanel{
    static ArrayList<Integer> cx = new ArrayList<>();
    static ArrayList<Integer> cy = new ArrayList<>();

    GraphicsPee(){

    }

    void coordinates(ArrayList<Integer> cx, ArrayList<Integer> cy){
        this.cx = cx; this.cy = cy;
    }

    public void paintComponent(Graphics g){
        // g.fillOval(754,306,10,10);
        Color[] random = new Color[4];
        random[0] = Color.YELLOW;
        random[1] = Color.RED;
        random[2] = Color.BLUE;
        random[3] = Color.GREEN;
        Random r = new Random();
        int size = 10;
        int sizeA = Math.min(cx.size(), cy.size());
        if(sizeA == 1000000)size = 2;
        if(sizeA == 100000)size = 3;
        if(sizeA == 10000)size = 8;
        for(int i = 0; i < sizeA; i++){
            int color = r.nextInt(4);
            g.setColor(random[color]);
            g.fillOval(cx.get(i),cy.get(i),size,size);
        }
    }

    public static void main(String[] args){
        // this.cx = cx; this.cy = cy;
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new GraphicsPee());
        frame.setSize(1000,750);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }    
}
