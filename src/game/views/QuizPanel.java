/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.entity.GameData;
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

    private List<JButton> lifelineButtons;
    private List<JButton> optionButtons;
    private QuizManager quizManager;
    private GameData gameData;

    public QuizPanel(GameData gameData) {
        super(new BorderLayout());
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));

        this.gameData = gameData;
        optionButtons = new ArrayList<>();
        lifelineButtons = new ArrayList<>();

        stageList = new JList(stages);
        stageList.setLocation(370, 20);
        stageList.setSize(120, 260);
        stageList.setBorder(BorderFactory.createLineBorder(Color.black));
        stageList.setFocusable(false);
        stageList.setSelectionModel(new ListRender(stages.length, gameData));

        add(stageList);

        audience = new JButton(Const.AUDIENCE);
        audience.setLocation(10, 90);
        audience.setSize(100, 25);
        add(audience);
        lifelineButtons.add(audience);

        telephone = new JButton(Const.TELEPHONE);
        telephone.setLocation(10, 120);
        telephone.setSize(100, 25);
        add(telephone);
        lifelineButtons.add(telephone);

        fiftyFifty = new JButton(Const.FIFTY_FIFTY);
        fiftyFifty.setLocation(10, 150);
        fiftyFifty.setSize(100, 25);
        add(fiftyFifty);
        lifelineButtons.add(fiftyFifty);

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
        optionButtons.add(buttonA);
        buttonA.setLocation(50, 360);
        buttonA.setSize(100, 50);
        add(buttonA);

        buttonB = new JButton(Const.OPTION2_BUTTON);
        optionButtons.add(buttonB);
        buttonB.setLocation(250, 360);
        buttonB.setSize(100, 50);
        add(buttonB);

        buttonC = new JButton(Const.OPTION3_BUTTON);
        optionButtons.add(buttonC);
        buttonC.setLocation(50, 435);
        buttonC.setSize(100, 50);
        add(buttonC);

        buttonD = new JButton(Const.OPTION4_BUTTON);
        optionButtons.add(buttonD);
        buttonD.setLocation(250, 435);
        buttonD.setSize(100, 50);
        add(buttonD);

        quitButton = new JButton(Const.QUIT_BUTTON);
        quitButton.setLocation(400, 387);
        quitButton.setSize(75, 50);
        add(quitButton);
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
            //arg: quizes retrieved from the table.
            quizManager = new QuizManager((List<QuizInfo>) arg);
            render();
        } else if (arg instanceof GameData) {
            //arg: result of quiz
            if (gameData.isFailed() || gameData.isFinished()) {
                renderEndMsg(gameData.isFinished());
            } else {
                render();
            }
        } else if (arg instanceof QuizInfo) {
            //arg: quizinfo in whihc two incorrect answer are removed.
            retrieveRemainOpIds((QuizInfo) arg);
        }
    }

    @Override
    public Object getParam() {
        return quizManager;
    }

    private void render() {

        if (quizManager.canMoveToNext()) {
            quizManager.moveToNext();
            quiz.setText(quizManager.getCurrentQuiz().getStatement());
            setOptions(quizManager.getCurrentOption());
            stage.setText(Integer.toString(this.gameData.getRound()));
            renderPrizeList();
        }

    }

    public void setOptions(List<OptionDto> options) {

        prepOptionButtons();
        for (int i = 0; i < options.size(); i++) {
            //TODO: remove before submit.
//            System.out.println(options.get(i).getStatement());
            int optionId = options.get(i).getId();
            optionButtons.get(i).setActionCommand(Integer.toString(optionId));
            optionButtons.get(i).setText(Integer.toString(optionId));
            //TODO: swict this code before submit.
//            optionButtons.get(i).setText(Integer.toString(i++));
        }
    }

    public void prepOptionButtons() {
//        optionButtons.clear();
        enableBtns(optionButtons, true);

    }

    private void renderPrizeList() {
        this.stageList.clearSelection();
        int highlightIndex = stages.length - gameData.getRound();
        this.stageList.setSelectedIndex(highlightIndex);
        this.stageList.setSelectionBackground(Color.GREEN);
    }

    /**
     * 
     * @param finishFlg true, if the player clear the game.
     * false. if the player failed the game.
     */
    private void renderEndMsg(boolean finishFlg) {
        enableBtns(lifelineButtons, false);
        enableBtns(optionButtons, false);
        if (finishFlg) {
            quiz.setText("You got all the answer correct!!");
         } else {
            quiz.setText("Your answer was wrong.");
        }
    }

    /**
     *
     * @param quizInfo two incorrect options are removed bt fifty-fifty.
     */
    private void retrieveRemainOpIds(QuizInfo quizInfo) {
        //Two options were remain in the parameter "quizInfo";
        List<OptionDto> options = quizInfo.getOption();
        String[] remainOpIds = new String[options.size()];

        for (int i = 0; i < options.size(); i++) {
            remainOpIds[i] = Integer.toString(options.get(i).getId());
        }

        disableOpBtns(remainOpIds);
    }

    private boolean disableOpBtns(String[] remainOpIds) {
        //enable all the option buttons.
        enableBtns(optionButtons, false);

        //enable option button whihc has the same option id as the parameter (remain id).
        for (int i = 0; i < remainOpIds.length; i++) {
            String remainOpId = remainOpIds[i];

            for (JButton opBtn : optionButtons) {
                if (opBtn.getActionCommand().equalsIgnoreCase(remainOpId)) {
                    opBtn.setEnabled(true);
                }
            }
        }
        return false;
    }

    /**
     * Disable lifeline button.
     *
     * @param usedLifeline target lifeline name.
     */
    public void disableLifelines(String usedLifeline) {

        for (JButton button : lifelineButtons) {
            if (button.getText().equalsIgnoreCase(usedLifeline)) {
                button.setEnabled(false);
            }
        }
    }

    /**
     * Set Buttons enable or disable according to the flag.
     *
     * @param btns targetBtns.
     * @param enableFlg enable if the flg true, disable if the flg is false.
     */
    public void enableBtns(List<JButton> btns, boolean enableFlg) {
        for (JButton btn : btns) {
            btn.setEnabled(enableFlg);
        }
    }
}
