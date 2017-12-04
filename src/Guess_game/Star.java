package Guess_game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Star {

    Image img;
    float x;
    float y;

    public Star() throws IOException {
        x = (float) Math.random()*900;
        y = (float) Math.random()*500;
        img = ImageIO.read(GameWindow.class.getResourceAsStream("star16.png"));
    }

    public void render(Graphics g) {
        g.drawImage(img, (int)x, (int) y,null);
    }

    public void onUpdate() {
        }
}
