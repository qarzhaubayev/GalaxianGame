package Galaxy;

import javax.swing.*;
import java.awt.*;

public class Galaxy extends JFrame {
    GalaxyPanel panel;
    JLabel points, lifeCount;
    JLayeredPane layouts;
//    JTextArea score;

    public Galaxy(){
//        layouts = new JLayeredPane();
        JFrame frame = new JFrame("Galaxy");
        panel = new GalaxyPanel(this);
        add(panel);
//        layouts.add(panel,1,0);


        points = new JLabel("0");
        points.setSize(50,50);
        lifeCount = new JLabel();
        String m = String.format("Number of lifes: " + panel.ship.getLifes());
        lifeCount.setFont(new Font("Arial", Font.ITALIC, 16));
//        lifeCount.setOpaque(true);

        lifeCount.setForeground(Color.BLUE);
        lifeCount.setText(m);
        lifeCount.setLocation(10,10);

        add(lifeCount,BorderLayout.SOUTH);


        add(points, BorderLayout.NORTH);
        setTitle("Galaxy");
        setSize(350,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    public GalaxyPanel getPanel(){
        return panel;
    }

    public static void main(String[] args) {
        new Galaxy();
    }
}
