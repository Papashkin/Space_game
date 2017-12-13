package Guess_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Bonus {
    int x;
    int y;
    float vision;
    float speed;
    float heroSpeed;
    Image img;
    boolean status;

    public Bonus() throws IOException{
        x = 0;
        y = 0;
        img = ImageIO.read(GameWindow.class.getResourceAsStream("bonus_new.png"));
        speed = 0f;
        heroSpeed = 0f;
        vision = speed * 6;
        status = false;
    }

    public void activate(Hero hero){
        x = hero.x;
        y = hero.y;
        heroSpeed = hero.speed/10;
        speed = 0.1f;
        vision = speed * 380;
        status=true;
    }

    public void deactivate(){
        status = false;
    }

    public void render(Graphics g){
        g.drawImage(img, x, y,null);
    }

    public void update(){
        x += heroSpeed;
        y -= speed;
        vision -= speed;
        if (vision < 0){
            this.deactivate();
        }
    }
}
