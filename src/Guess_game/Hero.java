package Guess_game;

import javafx.scene.shape.Circle;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Hero {
      Image img;
      float speed;
      int x;
      int y;
      boolean state = true;

    public Hero() throws IOException {
        x = 100;
        y = 400;
        speed = 12f;
        img = ImageIO.read(GameWindow.class.getResourceAsStream("ship64.png"));
    }

    public void determine(){
        state = false;
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
}