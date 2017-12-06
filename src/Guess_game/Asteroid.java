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
        x = (float) (1090 + Math.random()*10);
        y = (float) Math.random()*650;
        speed = 0.1 + Math.random()*1.4;
        img = ImageIO.read(GameWindow.class.getResourceAsStream("asteroid64.png"));
        hitArea = new Circle(x, y, 29);
    }

    public void recreate(){
        x = (float) 1200;
        y = (float) Math.random()*650;
        speed = 0.1 + Math.random()*1.4;
        hitArea = new Circle(x, y, 30);
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
        hitArea.setRadius((double) 30);
    }
}
