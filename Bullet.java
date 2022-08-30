package Galaxy;

import javax.swing.*;
import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    int ya = 20;
    private Image img;
    boolean isShoot = false;
    boolean visible;
    boolean niceShot;

    public Bullet(){
        x = 0; y = 0;
    }
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        ImageIcon newBullet = new ImageIcon("C:/Users/Anuar/IdeaProjects/Galaxy/Bullet.png");
        img = newBullet.getImage();
        visible = true;
        niceShot = false;
    }
//
//    public void BulletEn(int x, int y){
//        this.x = x;
//        this.y = y;
//        ImageIcon newBullet = new ImageIcon("C:/Users/Anuar/IdeaProjects/Galaxy/BulletEn.png");
//        img = newBullet.getImage();
//        visible = true;
//
//    }

//    public void moveBullEn(){
//            y += ya;
//            if( y >= 600 ){
//                visible = false;
//            }
//    }
    public void moveBull(){
        y -= ya;
        if( y <= -15 ){
            visible = false;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImg() {
        return img;
    }

    public boolean getVisible() {
        return visible;
    }


    public void setX(int x) {
        this.x = x;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,3,4);
    }

    public void setY(int i) {
        this.y = y;
    }
}
