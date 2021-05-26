/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.entity.Lifeline;
import game.entity.Player;
import game.entity.QuizInfo;
import game.entity.QuizManager;
import game.service.JudgeService;
import game.service.PlayerRegisterService;
import game.service.QuizService;
import game.service.RankingService;
import game.util.Const;
import game.util.Level;
import game.views.Frame;
import game.views.MainFrame;
import game.views.Panel;
import game.views.QuizPanel;
import game.views.RankingPanel;
import game.views.RegisterPanel;
import game.views.StartPanel;
import game.views.SubFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observer;

/**
 * Controller for the game,
 *
 * @author Masaomi
 */
public class GameController implements ActionListener {

    private Player player;
    private int round;
    private Level level;
    private boolean isExited;
    private boolean isFinished;
    private Frame mainDisplay;
    private Frame subDisplay;

    /**
     * Contructor
     */
    public GameController() {
        mainDisplay = new MainFrame();
        subDisplay = new SubFrame();
    }

    /**
     * Init attributes regarding the game, then display start view.
     */
    public void initGame() {
        round = 1;
        level = Level.VERY_EASY;
        isExited = false;
        isFinished = false;
        if (player != null) {
            player.reset();
        }
    }

    /**
     * Start the game.
     */
    public void showStartDisplay() {
        //set panel to the main display
        Panel startPanel = new StartPanel(player);
        setController(startPanel);
        mainDisplay.setPanel(startPanel);

        mainDisplay.open(500, 600);
    }
    
    private void showRegisterDislpay() {
        subDisplay.revalidate();
        Panel panel = new RegisterPanel();
        setController(panel);
        subDisplay.setPanel(panel);
        subDisplay.open(300, 200);
    }

    private void showQuizDisplay() {
        Panel panel = new QuizPanel(round);
        panel.addController(this);
        mainDisplay.setPanel(panel);
        QuizService service = new QuizService();
        service.addObserver((QuizPanel) panel);
        service.getAllQuizes();
    }

    private int getOptionId(String optionLable) {
        int selectedOptionId = 0;
        Panel currentPanel = mainDisplay.getPanel();
        if (currentPanel instanceof QuizPanel) {
            selectedOptionId = (int) currentPanel.getParam();
        }
        return selectedOptionId;
    }

    private void judge(int answerId, int selectedOpt) {

        JudgeService service = new JudgeService();
        service.addObserver((Observer) mainDisplay.getPanel());

        if (service.judge(round, selectedOpt)) {
            service.calcScore(player, round);
        } else {
            registerData();
        }
    }

    private void useLifeline(String selectedLifeline) {
        List<Lifeline> lifelines = player.getLifelines();
        for (Lifeline lifeline : lifelines) {
            if (selectedLifeline.equals(lifeline.getName())) {
                QuizInfo currentQuizInfo = ((QuizManager) mainDisplay.getPanel().getParam()).getCurrentQuizInfo();
                lifeline.addObserver((Observer) mainDisplay.getPanel());
                lifeline.execute(currentQuizInfo);
                break;
            }
        }
    }

    private void showRankingDislpay() {
        //prepaer panel
        Panel rankingPanel = new RankingPanel();
        setController(rankingPanel);
        subDisplay.setPanel(rankingPanel);
        //get ranking
        RankingService service = new RankingService();
        service.addObserver((RankingPanel) rankingPanel);
        service.getRanking();

        //show the sub display
        subDisplay.open(300, 300);

    }

    private void createPlayer(String name) {
        PlayerRegisterService service = new PlayerRegisterService();
        int id = service.getNewId();
        this.player = new Player(name, id);
    }
    
    private void registerData() {
        //register player to a platers table
        PlayerRegisterService playerService = new PlayerRegisterService();
        playerService.registPlayer(this.player);
        
        //register player to a ranking table
        RankingService rankingService = new RankingService();
        rankingService.registerToRanking(this.player);
    }

    private boolean finishGame() {
        return false;
    }

    private void setController(Panel panel) {
        panel.addController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case Const.NEW_GAME_BUTTON:
                showRegisterDislpay();
                break;
            case Const.CONTINUE_BUTTON:
                showQuizDisplay();
                break;
            case Const.RANKING_BUTTON:
                showRankingDislpay();
                break;
            case Const.CLOSE_BUTTON:
                subDisplay.dispose();
                break;
            case Const.REGISTER_BUTTON:
                createPlayer((String) subDisplay.getPanel().getParam());
                subDisplay.dispose();
                showQuizDisplay();
                break;
            case Const.OPTION1_BUTTON:
            case Const.OPTION2_BUTTON:
            case Const.OPTION3_BUTTON:
            case Const.OPTION4_BUTTON:
                int answerId = ((QuizManager) mainDisplay.getPanel().getParam()).getCurrentAnswer();
                int selectedId = Integer.parseInt(e.getActionCommand());
                judge(answerId, selectedId);
                break;
            case Const.QUIT_BUTTON:
                //move back to the start display
                showStartDisplay();
                break;
            case Const.AUDIENCE:
            case Const.TELEPHONE:
            case Const.FIFTY_FIFTY:
                useLifeline(e.getActionCommand());
                break;

        }
    }
}
