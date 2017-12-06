package Guess_game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class GameWindow extends JFrame{

    private static GameWindow game_window;
    private static Asteroid[] asteroid;
    private static Background background;
    private static Hero hero;
    private static Bullet[] bullets;
    private static int score;
    private static Image gameOver;

    public static void main(String[] args) throws IOException {
        score = 0;
        gameOver = ImageIO.read(GameWindow.class.getResourceAsStream("game_over.png"));
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
                key_click(e);
//                switch (e.getKeyCode()) {
//                    case 87: case 38:    // VK_W or UP
//                        hero.y -= hero.speed;
////                        hero.update();
//                        break;
//                    case 83: case 40:    // VK_S or DOWN
//                        hero.y += hero.speed;
////                        hero.update();
//                        break;
//                    case 65: case 37:    // VK_A or LEFT
//                        hero.x -= hero.speed;
////                        hero.update();
//                        break;
//                    case 68: case 39:   // VK_D or RIGHT
//                        hero.x += hero.speed;
////                        hero.update();
//                        break;
//                    case 32:            // VK_SPACE
//                        for (int i = 0; i < bullets.length; i++){
//                            if (!bullets[i].active) {
//                                bullets[i].activate(hero);
//                                bullets[i].update();
//                                break;
//                            }
//                        }
//                }
            }
        });
        game_window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                mouse_click();
            }
        });
    }

    public static void key_click(KeyEvent e){
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

    public  static void game_over(){

    }

    public static void update(){
        game_window.setTitle("Score: " + score);
        background.update();
        hero.update();
        for (int i = 0;i < asteroid.length; i++) {
            asteroid[i].update();
            if(asteroid[i].hitArea.contains((double)hero.x, (double)hero.y)){
                asteroid[i].recreate();
                hero.determine();
                game_window.setTitle("Game over! Your score is " + score);
                score = 0;
                break;
            }
        }
        for (int i = 0;i < bullets.length; i++){
            if (bullets[i].active) {
                bullets[i].update();
                for (int j=0; j<asteroid.length;j++){
                    if (asteroid[j].hitArea.contains((double)bullets[i].x, (double)bullets[i].y)){
                        asteroid[j].recreate();
                        bullets[i].deactivate();
                        score++;
                        break;
                    }
                }
            }
        }
    }

    public  static void mouse_click(){
        if (!hero.state) {
            hero.state = true;
            for (int i = 0; i < asteroid.length; i++){
                asteroid[i].recreate();
            }
            for (int j = 0; j < bullets.length; j++){
                if (bullets[j].active) bullets[j].deactivate();
            }
            game_window.setTitle(" ");
            game_window.repaint();
            update();
        }
    }

    private static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            render(g);
            if (hero.state){
                repaint();
            } else {
                g.drawImage(gameOver, game_window.getX() + 100, game_window.getY(), null);
            }
        }
    }
}
