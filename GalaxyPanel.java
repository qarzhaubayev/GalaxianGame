package Galaxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GalaxyPanel extends JPanel implements ActionListener {
    int leftBorder = 0, rightBorder = 270;
    int leftMost = 0, rightMost;
    int enemyNumb, aliveEnemies;
    int score = 0;
    JLabel points;
    JLabel lifeCount;
    SpaceShip ship;
    Image img;
    Timer timer;
    Bullet bullet;
    EnemyBullet[] bulletEn;
    Galaxy game;
    Enemy[] enemies;
//    Enemy en1;
//    Enemy en2;
    //    ShootingEnemy shoot;
    int randT = new Random().nextInt();
    int thisNumer;
    private boolean isPaused;

    public GalaxyPanel(Galaxy game) {

        ship = new SpaceShip(game);
//        shoot = new ShootingEnemy(this);
        setFocusable(true);
        ImageIcon i = new ImageIcon("C:/Users/Anuar/IdeaProjects/Galaxy/darkPurpleStarscape.png");
        String m = String.format("Lifes are " + ship.getLifes());
        this.lifeCount = new JLabel(m);
        game.lifeCount = this.lifeCount;
        lifeCount.setText(String.valueOf(ship.getLifes()));


        this.points = game.points;
        img = i.getImage();
        addKeyListener(new actionListener());
        bullet = new Bullet(ship.x, 500);
        enemySpawner( 5, 10, 100);
//        enemySpawner(5, 10, 80);
//        enemySpawner(5, 10, 60);
//        bulletEn =  new EnemyBullet(enemies[thisNumer].getX(), 100);
        this.game = game;
        timer = new Timer(5, this);
        timer.start();


    }

    void enemySpawner( int numOfEnemies, int xLoc, int yLoc) {
        enemies = new Enemy[numOfEnemies];
        enemyNumb =  numOfEnemies;
        aliveEnemies =  numOfEnemies;
        bulletEn = new EnemyBullet[numOfEnemies];
        rightMost = numOfEnemies;
//        for (int x = 0; x < r; x++) {
            for (int t = 0; t < numOfEnemies; t++) {

            bulletEn[t] = new EnemyBullet((40*t)+xLoc,yLoc);

                enemies[t] = new Enemy((40 * t) + xLoc, yLoc, "C:/Users/Anuar/IdeaProjects/Galaxy/Enemy1.png");
                if(t == 4){
                    yLoc = yLoc + 30;
                }
            }
//        }
    }

        public void moveEnemies(){
//        for(int x = 0; x < 5; x ++) {
            for (int t = 0; t < enemies.length; t++) {
                if (enemies[rightMost - 1].getX() == rightBorder) {
                    enemies[t].setXa(1);
                } else if (enemies[leftMost].getX() == leftBorder) {
                    enemies[t].setXa(-1);
                }

                System.out.println(enemies[t].getX());
                enemies[t].moveOnSpawn();
                if (enemies[leftMost].isAlive() == false) {
                    if (leftMost < enemies.length - 1) leftMost += 1;
                    else {
                        leftMost--;
                    }
                } else if (enemies[rightMost - 1].isAlive() == false) {
                    if (rightMost > 0) {
                        rightMost--;
                    } else {
                        rightMost++;
                    }
                }
            }
        }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isPaused != true) {

        moveEnemies();
        enemiesShoot();

            if (aliveEnemies == 0) {
                endGame();
            }

        checkCollisions();
            repaint();

            if (bullet.getVisible() == true && bullet.isShoot == true) {
                bullet.moveBull();
            }
            ship.update();
        }
    }

    private void enemiesShoot() {
        if(enemies[thisNumer].isAlive() == true ) {
            if(bulletEn[thisNumer].getVisible() == false) {
                thisNumer = Math.abs(new Random().nextInt(enemyNumb) % enemyNumb);
                enemies[thisNumer].fire();
            }
            if (bulletEn[thisNumer].getVisible() == true && bulletEn[thisNumer].isShoot == true) {
                bulletEn[thisNumer].moveBullEnemy();
            }
        }
        else {
            if(bulletEn[thisNumer].getVisible() == true && bulletEn[thisNumer].isShoot == true){
                bulletEn[thisNumer].moveBullEnemy();
            }if(bulletEn[thisNumer].getVisible() == false){
                thisNumer = Math.abs(new Random().nextInt(enemyNumb) % enemyNumb);
            }
        }
    }

    void pause() {
        isPaused = !isPaused;
        points = game.points;
        if (isPaused) {
            points.setText("Paused");
        } else {
            points.setText(String.valueOf(score));
        }
    }


    private void endGame() {
        String message = String.format("You won, score is " + score);
        JOptionPane.showMessageDialog(null, message);
        game.dispatchEvent(new WindowEvent(game, WindowEvent.WINDOW_CLOSING));
        timer.stop();
    }


    public void niceEnemyShot() {
        bulletEn[thisNumer].visible = false;
        bulletEn[thisNumer].setX(500);
        ship.lifes--;
        ship.alive = false;
        timer.stop();
        String m = String.format("Number of lifes: " + ship.getLifes());
        JOptionPane.showMessageDialog(null, m);

        ship.x = 155;
        ship.y = 475;
        ship.xa = 0;
        game.lifeCount.setText(m);
        timer.restart();
        ship.alive = true;

    }


    public void checkCollisions(){
        bulletEn[thisNumer] = enemies[thisNumer].getBullet();
        Rectangle enemyBound = new Rectangle();
        Rectangle enemyBullet = bulletEn[thisNumer].getBounds();
        Rectangle bulletBound = bullet.getBounds();
        Rectangle shipBound = ship.getBounds();
//        for(int x = 0; x < )
        for(int t = 0; t < enemies.length; t++){

            enemyBound = enemies[t].getBounds();
            if(bulletBound.intersects(enemyBound) && enemies[t].isAlive()){
                bullet.visible = false;
                enemies[t].setAlive(false);
                aliveEnemies--;
                score+=50;
                this.points = game.points;
                points.setText(String.valueOf(score));
            }
        }
        if(enemyBullet.intersects(shipBound)){
            niceEnemyShot();
        }
        if(ship.getLifes() == 0){
            timer.stop();
        }

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(img, 0, 600 - ship.ny, 350, 600, null);
        g2d.drawImage(img, 0, 0 - ship.ny, 350, 600, null);
        if (ship.ny < 0) {
            ship.ny = 600;
            ship.ny2 = 0;
        }

        g2d.drawImage(ship.getImage(), ship.x + 18, ship.y, 50, 50, null);

        bullet = ship.getBullet();
        if (bullet.getVisible() == true) {
            g2d.drawImage(bullet.getImg(), bullet.getX() + 19, bullet.getY(), 10, 15, null);
        } else if (bullet.getVisible() == false) {
            bullet.setX(5000);
            bullet.setY(0);
        }
        bulletEn[thisNumer] = enemies[thisNumer].getBullet();


        if(bulletEn[thisNumer].getVisible() == true && bulletEn[thisNumer].isShoot == true) {
            g2d.drawImage(bulletEn[thisNumer].getImg(), bulletEn[thisNumer].getX() + 19, bulletEn[thisNumer].getY(), 25, 25, null);
        }else if(bulletEn[thisNumer].getVisible() == false && bulletEn[thisNumer].isShoot == false){
            bulletEn[thisNumer].setX(5000);
            bulletEn[thisNumer].setY(0);
        }
        for (int x = 0; x < 5; x++){
            for (int t = 0; t < enemies.length; t++) {
                if (enemies[t].isAlive() == true) {
                    g2d.drawImage(enemies[t].getImg(), enemies[t].getX() + 22, enemies[t].getY(), 25, 25, null);
                } else {
                    g2d.drawImage(null, enemies[t].getX() + 22, 0, 0, 0, null);
                }
            }
    }
}
    class actionListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            ship.keyPressed(e.getKeyCode());

        }

        @Override
        public void keyReleased(KeyEvent e) {
            ship.keyReleased(e.getKeyCode());
        }
    }
}
