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
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author dylan
 */
public class QuizDisplay extends JPanel {

    private final JList stageList;
    private final JButton audience;
    private final JButton telephone;
    private final JButton fiftyFifty;
    private final JLabel quiz;
    private final JLabel stage;
    private final JButton buttonA;
    private final JButton buttonB;
    private final JButton buttonC;
    private final JButton buttonD;
    private final JButton quitButton;
    private final String[] stages = {"15 - $1,000,000", "14 - $500,000", "13 - $250,000", "12 - $125,000", "11 - $64,000", "10 - $32,000", "  9 - $16,000", "  8 - $8000",
        "  7 - $4000", "  6 - $2000", "  5 - $1000", "  4 - $500", "  3 - $300", "  2 - $200", "  1 - $100"};

    public QuizDisplay() {
        super(new BorderLayout());
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));

        stageList = new JList(stages);
        stageList.setLocation(370, 20);
        stageList.setSize(120, 260);
        stageList.setBorder(BorderFactory.createLineBorder(Color.black));
        add(stageList);

        audience = new JButton("Audience");
        audience.setLocation(10, 90);
        audience.setSize(100, 25);
        add(audience);

        telephone = new JButton("Telephone");
        telephone.setLocation(10, 120);
        telephone.setSize(100, 25);
        add(telephone);

        fiftyFifty = new JButton("50/50");
        fiftyFifty.setLocation(10, 150);
        fiftyFifty.setSize(100, 25);
        add(fiftyFifty);

        quiz = new JLabel("Quiz");
        quiz.setLocation(50, 250);
        quiz.setSize(300, 100);
        quiz.setBorder(BorderFactory.createLineBorder(Color.black));
        add(quiz);

        stage = new JLabel("1");
        stage.setLocation(200, 412);
        stage.setSize(20, 20);
        stage.setBorder(BorderFactory.createLineBorder(Color.black));
        add(stage);

        buttonA = new JButton("Option 1");
        buttonA.setLocation(50, 360);
        buttonA.setSize(100, 50);
        add(buttonA);

        buttonB = new JButton("Option 2");
        buttonB.setLocation(250, 360);
        buttonB.setSize(100, 50);
        add(buttonB);

        buttonC = new JButton("Option 3");
        buttonC.setLocation(50, 435);
        buttonC.setSize(100, 50);
        add(buttonC);

        buttonD = new JButton("Option 4");
        buttonD.setLocation(250, 435);
        buttonD.setSize(100, 50);
        add(buttonD);

        quitButton = new JButton("Quit");
        quitButton.setLocation(400, 387);
        quitButton.setSize(75, 50);
        add(quitButton);

    }

    public static void main(String[] args) {
        QuizDisplay quizDisplay = new QuizDisplay();

        JFrame frame = new JFrame("Who Wants To Be Millionaire");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(quizDisplay);
        frame.pack();

        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        int screenHeight = d.height;
        int screenWidth = d.width;
        frame.setLocation(new Point((screenWidth / 2) - (frame.getWidth() / 2), (screenHeight / 2) - (frame.getHeight() / 2)));

        frame.setVisible(true);
    }
}
