package Guess_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Asteroid {
    Image img;
    float x;
    float y;
    double speed;

    public Asteroid() throws IOException{
        x = (float) Math.random()*1200;
        y = (float) Math.random()*650;
        speed = 0.5 + Math.random()*2.5;
        if (img == null) img = ImageIO.read(GameWindow.class.getResourceAsStream("asteroid64.png"));
    }

    public void render(Asteroid asteroid, Graphics g) {
            g.drawImage(img, (int) asteroid.x, (int) asteroid.y, null);
    }

    public void update(){
        x -= speed;
        if (x < -80){
            x = 1250;
            y = (float) Math.random()*650;
            speed = 3 + Math.random()*5;
        }
    }
}
