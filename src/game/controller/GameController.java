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
 * Controller for the game,
 *
 * @author Masaomi
 */
public class GameController implements ActionListener {

    private GameData gameData;
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
        this.gameData= new GameData();
        Player player = gameData.getPlayer();
        if (player != null) {
            System.out.println("playerはnullじゃない");
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
        System.out.println(gameData.getPlayer().getScore());
        Panel panel = new QuizPanel(this.gameData);
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
        
        boolean result = service.judgeAnswer(answerId, selectedOpt);
        if (result) {
            service.calcScore(gameData);
        }
        service.judgeCompletion(result, gameData);
    }

    private void useLifeline(String selectedLifeline, Frame targetFrame) {
        List<Lifeline> lifelines = gameData.getPlayer().getLifelines();
        for (Lifeline lifeline : lifelines) {
            if (selectedLifeline.equals(lifeline.getName())) {
                QuizInfo currentQuizInfo = ((QuizManager) mainDisplay.getPanel().getParam()).getCurrentQuizInfo();
                lifeline.addObserver((Observer) targetFrame.getPanel());
                lifeline.execute(currentQuizInfo);
                break;
            }
        }
        if (mainDisplay.getPanel() instanceof QuizPanel) {
            ((QuizPanel)mainDisplay.getPanel()).disableLifelines(selectedLifeline);
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
        gameData.setPlayer(new Player(name, id));
    }
    
    private void registerData() {
        //register player to a platers table
        PlayerRegisterService playerService = new PlayerRegisterService();
        playerService.registPlayer(gameData.getPlayer());
        
        //register player to a ranking table
        RankingService rankingService = new RankingService();
        rankingService.registerToRanking(gameData.getPlayer());
    }

    private void showAudienceDisplay(String lifelineName) {
        Panel AudPanel = new AudiencePanel();
        setController(AudPanel);
        subDisplay.setPanel(AudPanel);
        useLifeline(lifelineName, subDisplay);
        subDisplay.open(600, 600);
  
    }
    
    private void showTelephoneDisplay(String lifelineName) {
        Panel telPanel = new TelephonePanel();
        setController(telPanel);
        subDisplay.setPanel(telPanel);
        
        //execute telephone
        useLifeline(lifelineName, subDisplay);
        subDisplay.open(300, 300);
        
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
