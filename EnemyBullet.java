package Galaxy;

import javax.swing.*;
import java.awt.*;

public class EnemyBullet {

        private int x;
        private int y;
        int ya = 5;
        private Image img;
        int xCopy;
        boolean isShoot = false;
        boolean visible;
        boolean niceShot;


        public EnemyBullet(){
            x = 0; y = 0;
        }

    public EnemyBullet(int x, int y){
        this.x = x;
        xCopy=x;
        this.y = y;
        ImageIcon newBullet = new ImageIcon("C:/Users/Anuar/IdeaProjects/Galaxy/BulletEn.png");
        img = newBullet.getImage();
        visible = true;

    }

            public void moveBullEnemy(){

            y += ya;
            if( y >= 600){
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
            return new Rectangle(x,y,10,15);
        }

        public void setY(int i) {
            this.y = y;
        }
    }
