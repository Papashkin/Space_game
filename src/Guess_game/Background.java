package Guess_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Background {
    public class Star {
        float x;
        float y;
        float speed;

        public Star() {
            x = (float) Math.random()*1200;
            y = (float) Math.random()*650;
            speed = 3.5f;
        }

        public void update() {
            x -= speed;
            if (x < -20){
                x = 1250;
                y = (float) Math.random()*650;
                speed = 3.2f;
            }
        }
    }

    Image img;
    Image img_star;
    Star[] stars;

    public Background() throws IOException {
        img = ImageIO.read(GameWindow.class.getResourceAsStream("bg.png"));
        img_star = ImageIO.read(GameWindow.class.getResourceAsStream("star16.png"));
        stars = new Star[100];
        for (int i = 0;i < stars.length; i++){
            stars[i] = new Star();
        }
    }

    public void render(Graphics g) {
        g.drawImage(img, 0, 0,null);
        for (int i = 0;i < stars.length; i++){
            g.drawImage(img_star, (int) stars[i].x, (int) stars[i].y, null);
        }
    }

    public void update(){
        for (int i = 0;i < stars.length; i++){
              stars[i].update();
        }
    }

}


