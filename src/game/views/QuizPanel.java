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
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

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
    private final JTextArea optionText;

    private List<JButton> lifelineButtons;
    private List<JButton> optionButtons;
    private QuizManager quizManager;
    private GameData gameData;

    //component config
    private final int LIFELIEN_WIDTH = 100;
    private final int LIFELIEN_HEIGHT = 25;
    private final int OPTION_WIDTH = 100;
    private final int OPTION_HEIGHT = 50;

    public QuizPanel(GameData gameData) {
//        super(new BorderLayout());
        setLayout(null);
        setPreferredSize(new Dimension(500, 500));

        this.gameData = gameData;
        optionButtons = new ArrayList<>();
        lifelineButtons = new ArrayList<>();

        stageList = new JList(stages);
        stageList.setLocation(370, 20);
        stageList.setBorder(new BevelBorder(BevelBorder.RAISED));
        stageList.setBorder(new LineBorder(Color.BLACK, 1));
        stageList.setSize(120, 330);
        stageList.setBorder(BorderFactory.createLineBorder(Color.black));
        stageList.setFocusable(false);
        stageList.setSelectionModel(new ListRender(stages.length, gameData));
        add(stageList);

        audience = new JButton(Const.AUDIENCE);
        audience.setLocation(10, 90);
        audience.setSize(LIFELIEN_WIDTH, LIFELIEN_HEIGHT);
        add(audience);
        lifelineButtons.add(audience);

        telephone = new JButton(Const.TELEPHONE);
        telephone.setLocation(10, 120);
        telephone.setSize(LIFELIEN_WIDTH, LIFELIEN_HEIGHT);
        add(telephone);
        lifelineButtons.add(telephone);

        fiftyFifty = new JButton(Const.FIFTY_FIFTY);
        fiftyFifty.setLocation(10, 150);
        fiftyFifty.setSize(LIFELIEN_WIDTH, LIFELIEN_HEIGHT);
        add(fiftyFifty);
        lifelineButtons.add(fiftyFifty);

        quiz = new JLabel("Quiz");
        quiz.setLocation(135, 90);
        quiz.setSize(210, 135);
        quiz.setBorder(BorderFactory.createLineBorder(Color.black));
        add(quiz);

        optionText = new JTextArea();
        optionText.setLocation(50, 250);
        optionText.setSize(295, 100);
        optionText.setOpaque(false);
        optionText.setEditable(false);
        optionText.setLineWrap(true);
        add(optionText);

        stage = new JLabel(Integer.toString(gameData.getRound()));
        stage.setHorizontalAlignment(JLabel.CENTER);
        stage.setLocation(200, 412);
        stage.setSize(20, 20);
        stage.setBorder(BorderFactory.createLineBorder(Color.black));
        add(stage);

        buttonA = new JButton(Const.OPTION1_BUTTON);
        optionButtons.add(buttonA);
        buttonA.setLocation(50, 360);
        buttonA.setSize(OPTION_WIDTH, OPTION_HEIGHT);
        add(buttonA);

        buttonB = new JButton(Const.OPTION2_BUTTON);
        optionButtons.add(buttonB);
        buttonB.setLocation(250, 360);
        buttonB.setSize(OPTION_WIDTH, OPTION_HEIGHT);
        add(buttonB);

        buttonC = new JButton(Const.OPTION3_BUTTON);
        optionButtons.add(buttonC);
        buttonC.setLocation(50, 435);
        buttonC.setSize(OPTION_WIDTH, OPTION_HEIGHT);
        add(buttonC);

        buttonD = new JButton(Const.OPTION4_BUTTON);
        optionButtons.add(buttonD);
        buttonD.setLocation(250, 435);
        buttonD.setSize(OPTION_WIDTH, OPTION_HEIGHT);
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
                //player gets a quiz correct.
                render();
            }
        } else if (arg instanceof QuizInfo) {
            //arg: quizinfo in whihc two incorrect answer are removed.
            retrieveRemainOpIds((QuizInfo) arg);
        }
    }

    /**
     * Return user's answer.
     *
     * @return
     */
    public Object getParam() {
        return quizManager;
    }

    /**
     * Render Quiz, option statement, option buttons, the prize list.
     */
    private void render() {
        if (quizManager.canMoveToNext()) {
            quizManager.moveToNext();
            quiz.setText(Const.wrapText(quizManager.getCurrentQuiz().getStatement()));
            setOptions(quizManager.getCurrentOption());
            stage.setText(Integer.toString(this.gameData.getRound()));
            renderPrizeList();
        }
    }

    /**
     * Render option buttons.
     *
     * @param options
     */
    public void setOptions(List<OptionDto> options) {
        enableBtns(optionButtons, true);
        //clear option text field
        optionText.setText("");
        //this is different from option id. opNum is in sequential order from 1.
        int opNum = 1;
        for (int i = 0; i < options.size(); i++) {
            //set op statement
            String statement = Integer.toString(opNum) + ": " + options.get(i).getStatement() + "\n";
            optionText.append(statement);
            //set op btn
            int optionId = options.get(i).getId();
            optionButtons.get(i).setActionCommand(Integer.toString(optionId));
            optionButtons.get(i).setText(Integer.toString(opNum));
            opNum++;
        }
    }

    /**
     * Render the list of prizes.
     */
    private void renderPrizeList() {
        this.stageList.clearSelection();
        int highlightIndex = stages.length - gameData.getRound();
        this.stageList.setSelectedIndex(highlightIndex);
        this.stageList.setSelectionBackground(Color.GREEN);
    }

    /**
     * Render ending message.
     *
     * @param finishFlg if the player cleared the game, this would be true. if
     * the player failed the game, this would be false.
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
     * Receive the result of Fifty fifty.
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

    /**
     * Disable option buttons according to the result of Fifty fifty.
     *
     * @param remainOpIds option IDs which should be left.
     * @return
     */
    private void disableOpBtns(String[] remainOpIds) {
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
    private void enableBtns(List<JButton> btns, boolean enableFlg) {
        for (JButton btn : btns) {
            btn.setEnabled(enableFlg);
        }
    }
}
