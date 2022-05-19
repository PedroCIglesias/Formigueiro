import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CFrame extends JPanel implements ActionListener{
    
    ArrayList<Formiga> formigas = new ArrayList<Formiga>();
    public static void main(String args[]){
        CFrame c = new CFrame();
    }

    public void paint(Graphics g){
        /*g.setColor(Color.red);
        g.fillOval(10,10,100,100);*/
        super.paintComponent(g);
        for(Formiga f: formigas){
            f.paint(g);
        }
    }


    public CFrame(){
        JFrame frame = new JFrame("Simulation");
        frame.setSize(800,600);
        frame.setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for(int i = 0; i<100;i++){
            formigas.add(new Formiga());
        }

        Timer t = new Timer(16,this);
        t.start();

        frame.add(this);
        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
        repaint();
    }

}
