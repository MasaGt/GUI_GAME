/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.entity.GameData;
import game.entity.Lifeline;
import game.entity.Player;
import game.entity.QuizInfo;
import game.entity.QuizManager;
import game.service.JudgeService;
import game.service.PlayerRegisterService;
import game.service.QuizService;
import game.service.RankingService;
import game.util.Const;
import game.views.AudiencePanel;
import game.views.Frame;
import game.views.MainFrame;
import game.views.Panel;
import game.views.QuizPanel;
import game.views.RankingPanel;
import game.views.RegisterPanel;
import game.views.StartPanel;
import game.views.SubFrame;
import game.views.TelephonePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observer;

/**
 * Controller of the game,
 *
 * @author Masaomi
 */
public class GameController implements ActionListener {

    private GameData gameData;
    private final Frame mainDisplay;
    private final Frame subDisplay;

    /**
     * Contructor
     */
    public GameController() {
        mainDisplay = new MainFrame();
        subDisplay = new SubFrame();
    }

    /**
     * Init attributes regarding the game.
     */
    public void initGame() {
        if (this.gameData == null) {
            this.gameData = new GameData();
        } else {
            this.gameData.initGameData();
        }
        
        Player player = gameData.getPlayer();
        if (player != null) {
            player.reset();
        }
    }

    /**
     * Start the game.
     */
    public void showStartDisplay() {
        //set panel to the main display
        Panel startPanel = new StartPanel(gameData.getPlayer());
        setController(startPanel);
        mainDisplay.setPanel(startPanel);
        mainDisplay.open();
    }

    /**
     * diplay player name registration.
     */
    private void showRegisterDislpay() {
        subDisplay.revalidate();
        Panel panel = new RegisterPanel();
        setController(panel);
        subDisplay.setPanel(panel);
        subDisplay.open();
    }

    /**
     * display quiz.
     */
    private void showQuizDisplay() {
        Panel panel = new QuizPanel(this.gameData);
        panel.addController(this);
        mainDisplay.setPanel(panel);
        QuizService service = new QuizService();
        service.addObserver((QuizPanel) panel);
        service.getAllQuizes();
    }


    /**
     * Answer judge.
     * @param answerId correct answer ID
     * @param selectedOpt selected option ID by player
     */
    private void judge(int answerId, int selectedOpt) {
        JudgeService service = new JudgeService();
        service.addObserver((Observer) mainDisplay.getPanel());

        boolean result = service.judgeAnswer(answerId, selectedOpt);
        if (result) {
            service.calcScore(gameData);
        }
        service.judgeCompletion(result, gameData);
    }

    /**
     * execute selected lifeline.
     * @param selectedLifeline
     * @param targetFrame frame in which result of a lifeline is displayed.
     */
    private void useLifeline(String selectedLifeline, Frame targetFrame) {
        List<Lifeline> lifelines = gameData.getPlayer().getLifelines();
        
        //find which lifeline was selected and execute.
        for (Lifeline lifeline : lifelines) {
            if (selectedLifeline.equals(lifeline.getName())) {
                Object param = ((QuizPanel)mainDisplay.getPanel()).getParam();
                QuizInfo currentQuizInfo = ((QuizManager)param).getCurrentQuizInfo();
                lifeline.addObserver((Observer) targetFrame.getPanel());
                lifeline.execute(currentQuizInfo);
                break;
            }
        }
        if (mainDisplay.getPanel() instanceof QuizPanel) {
            ((QuizPanel) mainDisplay.getPanel()).disableLifelines(selectedLifeline);
        }

    }

    /**
     * display ranking records.
     */
    private void showRankingDislpay() {
        //prepaer panel
        Panel rankingPanel = new RankingPanel();
        setController(rankingPanel);
        subDisplay.setPanel(rankingPanel);
        //get ranking
        RankingService service = new RankingService();
        service.addObserver((RankingPanel) rankingPanel);
        service.getRanking();

        //show ranking in the sub display
        subDisplay.open();

    }

    /**
     * Create new player after clicking register button.
     * @param name 
     */
    private void createPlayer(String name) {
        PlayerRegisterService service = new PlayerRegisterService();
        int id = service.getNewId();
        if (service.validateName(name)) {
            gameData.setPlayer(new Player(name, id));
            subDisplay.dispose();
            showQuizDisplay();
        } else {
            ((RegisterPanel)subDisplay.getPanel()).reEnterName();
        }
    }

    /**
     * register player's info and ranking data.
     */
    private void registerData() {
        //register player to a platers table
        PlayerRegisterService playerService = new PlayerRegisterService();
        playerService.registPlayer(gameData.getPlayer());

        //register player to a ranking table
        RankingService rankingService = new RankingService();
        rankingService.registerToRanking(gameData.getPlayer());
    }

    /**
     * display audience.
     * @param lifelineName selected lifeline
     */
    private void showAudienceDisplay(String lifelineName) {
        Panel AudPanel = new AudiencePanel();
        setController(AudPanel);
        subDisplay.setPanel(AudPanel);
        useLifeline(lifelineName, subDisplay);
        subDisplay.open();
    }

    /**
     * display telephone.
     * @param lifelineName selected lifeline
     */
    private void showTelephoneDisplay(String lifelineName) {
        Panel telPanel = new TelephonePanel();
        setController(telPanel);
        subDisplay.setPanel(telPanel);
        useLifeline(lifelineName, subDisplay);
        subDisplay.open();

    }

    /**
     * Connecte a controller and a view,
     * @param panel 
     */
    private void setController(Panel panel) {
        panel.addController(this);
    }

    /**
     * execute process corresponding button
     * @param e 
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Object param;
        switch (e.getActionCommand()) {
            case Const.NEW_GAME_BUTTON:
                showRegisterDislpay();
                break;
            case Const.CONTINUE_BUTTON:
                initGame();
                showQuizDisplay();
                break;
            case Const.RANKING_BUTTON:
                showRankingDislpay();
                break;
            case Const.CLOSE_BUTTON:
                subDisplay.dispose();
                break;
            case Const.REGISTER_BUTTON:
                param = ((RegisterPanel)subDisplay.getPanel()).getParam();
                String name = (String)param;
                createPlayer(name.trim());
                break;
            case Const.OPTION1_BUTTON:
            case Const.OPTION2_BUTTON:
            case Const.OPTION3_BUTTON:
            case Const.OPTION4_BUTTON:
                param = ((QuizPanel)mainDisplay.getPanel()).getParam();
                int answerId = ((QuizManager)param).getCurrentAnswer();
                int selectedId = Integer.parseInt(e.getActionCommand());
                judge(answerId, selectedId);
                break;
            case Const.QUIT_BUTTON:
                registerData();
                //move back to the start display
                showStartDisplay();
                break;
            case Const.AUDIENCE:
                showAudienceDisplay(e.getActionCommand());
                break;
            case Const.TELEPHONE:
                showTelephoneDisplay(e.getActionCommand());
                break;
            case Const.FIFTY_FIFTY:
                useLifeline(e.getActionCommand(), mainDisplay);
                break;
        }
    }
}
