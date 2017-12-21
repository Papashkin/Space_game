package Asteroid_game;

import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Weapon {
    float x;
    float y;
    float speed;
    boolean active;
    Image img;
    Circle area;
    float radius;
    boolean ready;

    public Weapon() throws IOException{
        img = ImageIO.read(Main.class.getResourceAsStream("weapon.png"));
        x = (float) (1200 + Math.random()*10);
        y = (float) (20 + Math.random()*580);
        radius = 16f;
        area = new Circle(x, y, radius);
        speed = 0.8f;
        active = false;
    }

    public void activate(){
        active = true;
    }

    public void deactivate(){
        active = false;
    }

    public void recreate(){
        x = (float) (1200 + Math.random()*20);
        y = (float) Math.random()*610;
        radius = 16f;
        area = new Circle(x, y,radius);
        speed = 0.8f;
        active = false;
    }

    public void render(Graphics g) {
        g.drawImage(img,(int) x,(int) y,null);
    }

    public void update() {
        x -= speed;
        area.setCenterX((double)x);
        area.setCenterY((double)y);
        area.setRadius((double)radius);
        if (x < -15) {
            deactivate();
        }
    }
}
