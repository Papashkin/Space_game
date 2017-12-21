package Asteroid_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bullet {
    boolean active;
    Image img;
    float speed;
    float x;
    float y;

    public Bullet() throws IOException{
        img = ImageIO.read(Main.class.getResourceAsStream("bullet64x32.png"));
        active = false;
        speed = 8.0f;
        x = 0f;
        y = 0f;
    }

    public void render(Graphics g) {
        g.drawImage(img,(int) x,(int) y,null);
    }

    public void deactivate(){
        active = false;
    }

    public void activate(Hero hero){
        x = hero.x+32f;
        y = hero.y+16f;
        active = true;
    }

    public void update() {
            x += speed;
            if (x > 18000) {
                deactivate();
            }
    }
}
