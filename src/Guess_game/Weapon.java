package Guess_game;

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

    public Weapon() throws IOException{
        img = ImageIO.read(GameWindow.class.getResourceAsStream("weapon.png"));
        x = (float) (1200 + Math.random()*10);
        y = (float) (20 + Math.random()*580);
        area = new Circle(x, y, 24);
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
        area = new Circle(x, y, 24);
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
        area.setRadius(18);
        if (x < -60) {
            recreate();
        }
    }
}
