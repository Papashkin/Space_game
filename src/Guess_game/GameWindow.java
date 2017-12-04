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

    public static void main(String[] args) throws IOException {
        background = new Background();
        hero = new Hero();
        asteroid = new Asteroid[10];
        for (int i = 0; i < asteroid.length; i++) {
            asteroid[i] = new Asteroid();
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
                    case 87:    // VK_W
                        hero.y -= hero.speed;
                        break;
                    case 83:    // VK_S
                        hero.y += hero.speed;
                        break;
                    case 65:    // VK_A
                        hero.x -= hero.speed;
                        break;
                    case 68:    // VK_D
                        hero.x += hero.speed;
                        break;
                }
//                game_window.setTitle("Clicked key is " + e.getKeyCode());
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
        for (int i = 0;i < 10; i++){
            asteroid[i].render(asteroid[i],g);
        }
        update();
    }

    public static void update(){
        background.update();
        for (int i = 0;i < 10; i++){
            asteroid[i].update();
        }
        hero.update();
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
