import java.awt.*;


public class Comida {
    private int qnt;
    int x,y;

    public Comida (int qnt){
        this.qnt = qnt;
        x = (int)(Math.random()*790+0);
        y = (int)(Math.random()*590+0);
    }

    public void paint(Graphics g){
        g.setColor(Color.GREEN);
        g.fillOval(x,y,50,50);
    }

    public boolean pegaComida(Formiga f){
        Rectangle comida = new Rectangle(x,y,50,50);
        Rectangle formiga = new Rectangle(f.pos.x,f.pos.y,10,10);

        if(formiga.intersects(comida)){
            qnt --;
            f.setStatus(Formiga.Status.ENCONTROU_COMIDA);
            return true;
        }
        if(qnt==0){
        }
        return false;
    }
}