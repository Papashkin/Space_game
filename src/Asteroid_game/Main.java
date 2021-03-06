package Asteroid_game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.BufferedReader;

public class Main extends JFrame{

    private static Main game_window;
    private static Asteroid[] asteroid;
    private static Background background;
    private static Hero hero;
    private static Bullet[] bullets;
    private static int cage;
    private static int score;
    private static Image gameOver;
    private static Weapon weapon;
    private static Bonus bonus;
    private static File recordFile;
    private static int recordScore;
    private static BufferedReader fileRead;
    private static BufferedWriter fileWrite;
    private static String readLine;
    private static String writeLine;

    public static void main(String[] args) throws IOException {
        score = 0;
        gameOver = ImageIO.read(Main.class.getResourceAsStream("game_over.png"));
        background = new Background();
        recordFile = new File("record.txt");
        if(!recordFile.exists()){
           try {
               recordFile.createNewFile();
               try {
                   writeLine = Integer.toString(0);
                   fileWrite = new BufferedWriter(new FileWriter(recordFile));
                   fileWrite.write(writeLine);
                   fileWrite.close();
               }catch (IOException e){
               }
           } catch (IOException e){
               System.out.println("Невозможно создать файл!");
           }
        }
        hero = new Hero();
        bonus = new Bonus();
        weapon = new Weapon();
        asteroid = new Asteroid[8];
        for (int i = 0; i < asteroid.length; i++) {
            asteroid[i] = new Asteroid();
        }
        bullets = new Bullet[30];
        for (int i = 0;i <bullets.length;i++){
            bullets[i] = new Bullet();
        }
        cage = bullets.length;
        GameField game_field = new GameField();
        game_window = new Main();
        set_parameters(game_window);
        game_window.add(game_field);
        game_window.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                key_click(e);
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

    private static void key_click(KeyEvent e){
        switch (e.getKeyCode()) {
            case 87: case 38:    // VK_W or UP
                hero.y -= hero.speed;
                break;
            case 83: case 40:    // VK_S or DOWN
                hero.y += hero.speed;
                break;
            case 65: case 37:    // VK_A or LEFT
                hero.x -= hero.speed;
                break;
            case 68: case 39:   // VK_D or RIGHT
                hero.x += hero.speed;
                break;
            case 32:            // VK_SPACE
                for (int i = 0; i < bullets.length; i++){
                    if (!bullets[i].active && cage > 0) {
                        bullets[i].activate(hero);
                        cage--;
                        bullets[i].update();
                        break;
                    }
                }
        }
    }

    private static void mouse_click(){
        if (!hero.state) {
            score = 0;
            hero.state = true;
            for (int i = 0; i < asteroid.length; i++){
                asteroid[i].recreate();
            }
            for (int j = 0; j < bullets.length; j++){
                if (bullets[j].active) bullets[j].deactivate();
            }
            weapon.recreate();
            cage = bullets.length;
            game_window.setTitle(" ");
            game_window.repaint();
            update();
        }
    }

    private static void set_parameters(Main game_window){
        game_window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        game_window.setLocation(50, 50);
        game_window.setSize(1000,650);
        game_window.setResizable(false);
        game_window.setVisible(true);
        game_window.setFocusable(true);
    }

    private static void render(Graphics g) {
        background.render(g);
        hero.render(hero,g);
        for (int i = 0;i < asteroid.length; i++) asteroid[i].render(asteroid[i],g);
        for (int i = 0;i < bullets.length; i++) {
            if (bullets[i].active) {
                bullets[i].render(g);
            }
        }
        if(weapon.active) weapon.render(g);
        if(bonus.status) bonus.render(g);
        update();
    }

    private static void update(){
        game_window.setTitle("Score: " + score + ". Bullets: " + cage);
        background.update();
        if(bonus.status)bonus.update();
        hero.update();
        if (score%13 == 0 && score != 0) {
            do {
                weapon.recreate();
                weapon.activate();
            } while (!weapon.active);
        }
        if (weapon.active){
            weapon.update();
            if (circlesContact()){
                cage += 20;
                weapon.deactivate();
                if (!bonus.status) bonus.activate(hero);
            }
        }
        for (int i = 0;i < asteroid.length; i++) {
            asteroid[i].update();
            if (asteroid[i].hitArea.contains((double) hero.x + 10, (double) hero.y)) {
                asteroid[i].recreate();
                hero.determine();
                game_window.setTitle("Game over! Your score is " + score);
                try {
                    fileRead = new BufferedReader(new FileReader(recordFile));
                    readLine = fileRead.readLine();
                    recordScore = Integer.valueOf(readLine);
                    fileRead.close();
                }catch (IOException e) {
                    continue;
                }catch (NumberFormatException e){
                    continue;
                }
                if (score > recordScore) {
                    game_window.setTitle("NEW RECORD! Old score = " + recordScore + ". New record = " + score);
                try {
                    fileWrite = new BufferedWriter(new FileWriter(recordFile));
                    writeLine = Integer.toString(score);
                    fileWrite.write(writeLine);
                    fileWrite.close();
                } catch (IOException e) {
                }
            }
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

    private static boolean circlesContact(){
        boolean status;
        double distance, distanceX, distanceY;
        double radius_sum;
         // 2 - are Hero and Weapon have a contact?
            distanceX = Math.pow((double)(hero.x - weapon.x), (double) 2);
            distanceY = Math.pow((double)(hero.y - weapon.y), (double) 2);
            distance = distanceX + distanceY;
            radius_sum = hero.radius + weapon.radius;
            status = distance < (radius_sum*radius_sum);
        return status;
    }

    public static class GameField extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            render(g);
            if (hero.state){
                repaint();
            } else {
                g.drawImage(gameOver, game_window.getWidth()/3, game_window.getHeight()/3, null);
            }
        }
    }
}
