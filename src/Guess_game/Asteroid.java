package Guess_game;

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

    public Asteroid() throws IOException{
        x = (float) Math.random()*1200;
        y = (float) Math.random()*650;
        speed = 0.1 + Math.random()*1.4;
//        if (img == null) img = ImageIO.read(GameWindow.class.getResourceAsStream("asteroid64.png"));
        img = ImageIO.read(GameWindow.class.getResourceAsStream("asteroid64.png"));
        hitArea = new Circle(x, y, 29);
    }

    public void recreate(){
        x = 1250;
        y = (float) Math.random()*650;
        speed = 0.1 + Math.random()*1.4;
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
    }
}
