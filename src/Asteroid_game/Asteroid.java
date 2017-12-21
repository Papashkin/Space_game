package Asteroid_game;

import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Asteroid {
    Image img;
    float x;
    float y;
    double speed;
    Circle hitArea;
    float radius;

    public Asteroid() throws IOException{
        x = (float) (1090 + Math.random()*10);
        y = (float) (20+Math.random()*550);
        radius = 31;
        speed = 0.2 + Math.random()*1.4;
        img = ImageIO.read(Main.class.getResourceAsStream("asteroid64.png"));
        hitArea = new Circle(x, y, radius);
    }

    public void recreate(){
        x = (float) 1200;
        y = (float) (20+Math.random()*550);
        radius = 31;
        speed = 0.1 + Math.random()*1.4;
        hitArea = new Circle(x, y, radius);
    }

    public void render(Asteroid asteroid, Graphics g) {
            g.drawImage(img, (int) asteroid.x, (int) asteroid.y, null);
    }

    public void update(){
        x -= speed;
        if (x < -80){
            recreate();
        }
        hitArea.setCenterX((double)x);
        hitArea.setCenterY((double)y);
        hitArea.setRadius((double) radius);
    }
}
