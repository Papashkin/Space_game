package Guess_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameWindow extends JFrame{

    private static GameWindow game_window;
    private static Asteroid[] asteroid;
    private static Background background;
    private static Hero hero;
    public static Bullet[] bullets;

    public static void main(String[] args) throws IOException {
        background = new Background();
        hero = new Hero();
        asteroid = new Asteroid[8];
        for (int i = 0; i < asteroid.length; i++) {
            asteroid[i] = new Asteroid();
        }
        bullets = new Bullet[100];
        for (int i = 0;i <bullets.length;i++){
            bullets[i] = new Bullet();
        }
        GameField game_field = new GameField();
        game_window = new GameWindow();
        set_parameters(game_window);
        game_window.add(game_field);
        game_window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                switch (e.getKeyCode()) {
                    case 87: case 38:    // VK_W or UP
                        hero.y -= hero.speed;
//                        hero.update();
                        break;
                    case 83: case 40:    // VK_S or DOWN
                        hero.y += hero.speed;
//                        hero.update();
                        break;
                    case 65: case 37:    // VK_A or LEFT
                        hero.x -= hero.speed;
//                        hero.update();
                        break;
                    case 68: case 39:   // VK_D or RIGHT
                        hero.x += hero.speed;
//                        hero.update();
                        break;
                    case 32:            // VK_SPACE
                        for (int i = 0; i < bullets.length; i++){
                            if (!bullets[i].active) {
                                bullets[i].activate(hero);
                                bullets[i].update();
                                break;
                            }
                        }
                }
            }
        });
    }

    public static void set_parameters(GameWindow game_window){
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(210, 210);
        game_window.setSize(1000,650);
        game_window.setResizable(false);
        game_window.setVisible(true);
        game_window.setFocusable(true);
    }

    public static void render(Graphics g) {
        background.render(g);
        hero.render(hero,g);
        for (int i = 0;i < asteroid.length; i++) asteroid[i].render(asteroid[i],g);
        for (int i = 0;i < bullets.length; i++) {
            if (bullets[i].active) {
                bullets[i].render(g);
            }
        }
        update();
    }

    public static void update(){
        background.update();
        hero.update();
        for (int i = 0;i < asteroid.length; i++) {
            asteroid[i].update();
        }
        for (int i = 0;i < bullets.length; i++){
            if (bullets[i].active) {
                bullets[i].update();
                for (int j=0; j<asteroid.length;j++){
                    if (asteroid[j].hitArea.contains((double)bullets[i].x, (double)bullets[i].y)){
                        asteroid[j].recreate();
                        bullets[i].deactivate();
                        break;
                    }
                }
            }
        }
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            render(g);
            repaint();
        }
    }
}
