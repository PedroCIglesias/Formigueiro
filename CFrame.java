import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CFrame extends JPanel implements ActionListener{

    Comida comida;
    Formigueiro formigueiro;
    public static void main(String args[]){
        CFrame c = new CFrame();
    }

    public void paint(Graphics g){
        super.paintComponent(g);
        comida.paint(g);
        formigueiro.paint(g);
        for(Formiga f: formigueiro.getFormigas()){
            f.paint(g);
            pegaComida(f);
            largaComida(f);
        }
    }
    private void pegaComida(Formiga f){
        if(comida.pegaComida(f)){
            /*for(Formiga formiga: formigueiro.getFormigas()){
                if(formiga.getStatus()!=Formiga.Status.ENCONTROU_COMIDA){
                    formiga.setStatus(Formiga.Status.SEGUE_COMIDA);
                }
            }*/
        }
    }
    private void largaComida(Formiga f){
        formigueiro.largaComida(f);
    }

    public CFrame(){
        JFrame frame = new JFrame("Simulation");
        frame.setSize(1000,1000);
        frame.setBackground(Color.DARK_GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        formigueiro = new Formigueiro(1000);
        comida = new Comida(10);

        Timer t = new Timer(30,this);
        t.start();

        frame.add(this);
        frame.setVisible(true);
        
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        repaint();
    }

}
