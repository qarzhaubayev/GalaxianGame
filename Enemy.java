package Galaxy;

import javax.swing.*;
import java.awt.*;

public class Enemy {
    private Image img;
    Galaxy game;
    int xCopy;
    private int x, xa = 1;
    private int y;
    private int Height = 15, Width = 20;
    private boolean isAlive = true;
    EnemyBullet bulletEnemy;

//    public Enemy(){
//        x = 0;
//        y = 0;
//    }

    public Enemy(int startX, int startY, String location){
        this.x = startX;
        this.y = startY;

        ImageIcon i = new ImageIcon(location);
        img = i.getImage();
        bulletEnemy = new EnemyBullet();
        setAlive(true);
    }

//    public Enemy(Galaxy game) {
//        super(game);
//    }

    public Rectangle getBounds(){
        return new Rectangle(x,y, 25,25);
    }

    public void moveOnSpawn(){
        x += xa;
    }


    public Image getImg() {
        return img;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXa() {
        return y;
    }
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
    public void setXa(int xa){
        this.xa = -xa;
    }


    public void fire(){
    if(isAlive == true){
        if(bulletEnemy.getVisible()== false) {
            bulletEnemy = new EnemyBullet(getX()+10, y);

            bulletEnemy.isShoot = true;
        }
        }
    }
    public EnemyBullet getBullet() {
        return bulletEnemy;
    }
}