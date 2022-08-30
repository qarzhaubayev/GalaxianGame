package Galaxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class SpaceShip {
    private int Height = 15, Width = 20;
    int x, xa, ny,ny2;
    protected int lifes = 3;
    int y;
    Galaxy game;
    Bullet bullet;
    boolean alive;

    Image img;

    public SpaceShip(Galaxy game){
        this.game = game;
        ImageIcon i = new ImageIcon("C:/Users/Anuar/IdeaProjects/Galaxy/SpaceShip.png");
        img = i.getImage();
        x = 155;
        y = 475;
        ny = 600;
        ny2 = 0;
        bullet = new Bullet();
        alive = true;
    }

    public void fire(){
        if(bullet.getVisible()== false || bullet.niceShot == true) {
            bullet = new Bullet(game.panel.ship.x, 500);
            bullet.isShoot = true;

            if (bullet.getVisible() == false) {
                bullet.isShoot = false;
                bullet.setX(x);
            }
        }
    }


    public Rectangle getBounds(){
        return new Rectangle(x,y, 40, 50);
    }

    public void update(){

        if(x > 0 && x < game.getWidth() - Width -40){
            ny -= 1;
            ny2 -= 1;
            x += xa;
        }else if(x <= 0){
            x++;
        }
        else if(x >= game.getWidth() - Width -40){
            x--;
        }
    }

    public Image getImage(){
        return img;
    }

    public void keyPressed(int keyCode) {
        if(keyCode == KeyEvent.VK_LEFT){
            xa = -2;
        }
        else if(keyCode == KeyEvent.VK_RIGHT){
            xa = 2;
        }
        if(keyCode == KeyEvent.VK_SPACE){
            fire();
        }
        if(keyCode == KeyEvent.VK_P){
            game.panel.pause();
        }
    }

    public void keyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT){
            xa = 0;
        }
    }

    public Bullet getBullet() {
        return bullet;
    }

    public int getLifes() {
        return lifes;
    }


}

