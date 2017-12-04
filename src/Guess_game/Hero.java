package Guess_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Hero {
      Image img;
      float speed;
      float x;
      float y;

    public Hero() throws IOException {
        x = 150f;
        y = 300f;
        speed = 15f;
        img = ImageIO.read(GameWindow.class.getResourceAsStream("ship64.png"));
    }

    public void render(Hero hero, Graphics g){
        g.drawImage(img, (int) hero.x, (int) hero.y,null);
    }

    public void update(){
        y = y;
        x = x;
        speed = speed;
    }
}