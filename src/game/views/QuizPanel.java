/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.entity.OptionDto;
import game.entity.QuizInfo;
import game.entity.QuizManager;
import game.util.Const;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author Masaomi
 */
public class QuizPanel extends Panel implements Observer {
    
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

    private List<JButton> optionButtons;
    private QuizManager quizManager;
    private int round;
    
    public QuizPanel(int round) {
        super(new BorderLayout());
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));

        stageList = new JList(stages);
        stageList.setLocation(370, 20);
        stageList.setSize(120, 260);
        stageList.setBorder(BorderFactory.createLineBorder(Color.black));
        add(stageList);

        audience = new JButton(Const.AUDIENCE);
        audience.setLocation(10, 90);
        audience.setSize(100, 25);
        add(audience);

        telephone = new JButton(Const.TELEPHONE);
        telephone.setLocation(10, 120);
        telephone.setSize(100, 25);
        add(telephone);

        fiftyFifty = new JButton(Const.FIFTY_FIFTY);
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

        buttonA = new JButton(Const.OPTION1_BUTTON);

        buttonA.setLocation(50, 360);
        buttonA.setSize(100, 50);
        add(buttonA);

        buttonB = new JButton(Const.OPTION2_BUTTON);

        buttonB.setLocation(250, 360);
        buttonB.setSize(100, 50);
        add(buttonB);

        buttonC = new JButton(Const.OPTION3_BUTTON);
        buttonC.setLocation(50, 435);
        buttonC.setSize(100, 50);
        add(buttonC);

        buttonD = new JButton(Const.OPTION4_BUTTON);
        buttonD.setLocation(250, 435);
        buttonD.setSize(100, 50);
        add(buttonD);

        quitButton = new JButton(Const.QUIT_BUTTON);
        quitButton.setLocation(400, 387);
        quitButton.setSize(75, 50);
        add(quitButton);

        optionButtons = new ArrayList<>();
    }

    @Override
    public void addController(GameController controller) {
        buttonA.addActionListener(controller);
        buttonB.addActionListener(controller);
        buttonC.addActionListener(controller);
        buttonD.addActionListener(controller);
        quitButton.addActionListener(controller);
        audience.addActionListener(controller);
        telephone.addActionListener(controller);
        fiftyFifty.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {

        if (arg instanceof List) {
            quizManager = new QuizManager((List<QuizInfo>) arg);
            renderQuiz();
        } else if (arg instanceof Boolean) {
            if (false == (Boolean) arg) {
                failedMessage();
            } else {
                renderQuiz();
            } 
        }
    }

    @Override
    public Object getParam() {
        return quizManager;
    }

    public void renderQuiz() {

        if (quizManager.canMoveToNext()) {
            quizManager.moveToNext();

            quiz.setText(quizManager.getCurrentQuiz().getStatement());
            setOptions(quizManager.getCurrentOption());
            stage.setText(Integer.toString(round));
        }
    }

    public void setOptions(List<OptionDto> options) {

        prepOptionButtons();
        for (int i = 0; i < options.size(); i++) {
            int optionId = options.get(i).getId();
            optionButtons.get(i).setActionCommand(Integer.toString(optionId));
        }
    }

    public void prepOptionButtons() {
        optionButtons.clear();
        if (buttonA.isEnabled()) {
            optionButtons.add(buttonA);
        }
        if (buttonB.isEnabled()) {
            optionButtons.add(buttonB);
        }
        if (buttonC.isEnabled()) {
            optionButtons.add(buttonC);
        }
        if (buttonD.isEnabled()) {
            optionButtons.add(buttonD);
        }
    }

    private void failedMessage() {
        for (JButton optionButton : optionButtons) {
            optionButton.setEnabled(false);
        }
        quiz.setText("Your answer was wrong");
    }
}
