package Guess_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bullet {
    boolean active;
    Image img;
    float speed;
    int x;
    int y;

    public Bullet() throws IOException{
        img = ImageIO.read(GameWindow.class.getResourceAsStream("bullet64x32.png"));
        active = false;
        speed = 20f;
        x = 0;
        y = 0;
    }

    public void render(int x, int y, Graphics g) {
        if (active) g.drawImage(img,  x+32, y+16, null);
    }

    public void deactivate(){
        active = false;
    }

    public void activate(int hero_x, int hero_y){
        x = hero_x;
        y = hero_y;
        active = true;
    }

    public void update(){
        x += speed;
//        if (x > 1000) {
//            deactivate();
//        }
    }
}
