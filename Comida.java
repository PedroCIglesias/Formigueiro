import java.awt.Graphics;
import java.awt.Color;

public class Comida {
    private int qnt;
    int x,y;
    private Graphics g;

    public Comida (int qnt,Graphics g){
        this.qnt = qnt;
        x = (int)(Math.random()*790+0);
        y = (int)(Math.random()*590+0);
        this.g = g;
        this.paint();
    }

    public void paint(){
        g.setColor(Color.GREEN);
        g.fillOval(x,y,100,100);
    }

    public void pega(){
        qnt --;
        if(qnt==0){
            g.dispose();
        }
    }
}