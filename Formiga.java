import java.awt.Graphics;
import java.awt.Color;


public class Formiga{
    private int x,y;

    private int vx, vy;
    
    private Status status;
    public enum Status{
        PROCURA_COMIDA,
        ENCONTROU_COMIDA
    }

    public Formiga(){
        x = (int)(Math.random()*790+0);
        y = (int)(Math.random()*590+0);
        this.status = Status.PROCURA_COMIDA;

        vx = (int)(Math.random()*(10+1)+-5);
        vy = (int)(Math.random()*(10+1)+-5);
    }

    public void paint(Graphics g){
        switch(status.ordinal()){
            case 0:
                g.setColor(Color.RED);
                break;
            case 1:
                g.setColor(Color.GREEN);
                break;
            default:
                break;
        }
        x += vx;
        y += vy;

        if(x<0 || x>= 790){
            vx *= -1;
        }
        if(y<0 || y>= 790){
            vy *= -1;
        }

        g.fillOval(x,y,10,10);


    }
}