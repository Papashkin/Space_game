package Guess_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Hero {
      Image img;
      float speed;
      int x;
      int y;

    public Hero() throws IOException {
        x = 100;
        y = 400;
        speed = 12f;
        img = ImageIO.read(GameWindow.class.getResourceAsStream("ship64.png"));
    }

    public void render(Hero hero, Graphics g){
        g.drawImage(img, hero.x, hero.y,null);
    }

    public void update(){
        if (x < 0) x = 0;
        if (x > 932) x = 932;
        if (y < -42) y = 600;
        if (y > 600) y = -42;
    }

    public void fire(){
//        for (int i = 0; i < GameWindow.bullets.length; i++){
//            if (!GameWindow.bullets[i].active){
//                GameWindow.bullets[i].activate(x + 48, y + 32);
//                break;
//            }
//        }
    }
}