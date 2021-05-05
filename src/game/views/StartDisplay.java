/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author dylan
 */
public class StartDisplay extends JPanel {
    
    private final JLabel icon;
    private final JButton start;
    private final JButton ranking;

    public StartDisplay() {
        super(new BorderLayout());
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));

        icon = new JLabel("Who Wants To Be A Millionaire");
        icon.setLocation(100, 100);
        icon.setSize(300, 100);
        icon.setBorder(BorderFactory.createLineBorder(Color.black));
        add(icon);

        start = new JButton("Start");
        start.setLocation(200, 250);
        start.setSize(100, 50);
        add(start);

        ranking = new JButton("Ranking");
        ranking.setLocation(200, 350);
        ranking.setSize(100, 50);
        add(ranking);
    }

    public static void main(String[] args) {
        StartDisplay startDisplay = new StartDisplay();

        JFrame frame = new JFrame("Who Wants To Be Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(startDisplay);
        frame.pack();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));

        frame.setVisible(true);
    }
}
