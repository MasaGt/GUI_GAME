/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.controller;

import game.entity.Lifeline;
import game.entity.Player;
import game.entity.QuizInfo;
import game.entity.RankingDto;
import game.service.JudgeService;
import game.service.LifelineService;
import game.service.PlayerRegisterService;
import game.service.QuizService;
import game.service.RankingService;
import game.util.Const;
import game.util.Level;
import game.views.ActionView;
import game.views.AnswerView;
import game.views.LifelineView;
import game.views.QuizView;
import game.views.RegisterView;
import game.views.EndingView;
import game.views.ResultView;
import game.views.StartView;
import java.util.List;
import java.util.Scanner;

/**
 * Controller for the game,
 *
 * @author Masaomi
 */
public class GameController {

    private Player player;
    private int round;
    private Level level;
    private boolean isExited;
    private boolean isFinished;
    private Scanner scanner;

    /**
     * Contructor
     *
     * @param scanner
     */
    public GameController(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Init attributes regarding game, then display start view.
     */
    public void initGame() {
        this.round = 1;
        this.level = Level.VERY_EASY;
        this.isExited = false;
        this.isFinished = false;

        RankingService service = new RankingService();
        List<RankingDto> ranking = service.getRanking();
        StartView startView = new StartView(ranking);
    }

    /**
     * Ask player's name and innitialize player information.
     */
    public void registerPlayer() {
        if (this.player == null) {
            RegisterView view = new RegisterView();
            String name = view.askName(this.scanner);
            PlayerRegisterService service = new PlayerRegisterService();
            int id = service.getNewId();
            this.player = new Player(name, id);
        } else {
            // if player continue a new game, initialize only her/his score, prize, and lifelines.
            this.player = new Player(this.player.getName(), this.player.getId());
        }
    }

    /**
     * Display a quiz. Then, forward to the next display which asks a plyer's
     * action.
     */
    public void stratQuiz() {
        QuizService service = new QuizService();
        List<QuizInfo> quizes = service.getQuizes(level.getLevel());

        QuizView quizView = new QuizView();

        if (!quizes.isEmpty()) {
            for (int i = 0; i < quizes.size(); i++) {
                if (!this.isFinished) {
                    int action;
                    do {
                        quizView.displayQuiz(quizes.get(i));
                        action = askAction(quizes.get(i));
                    } while (Const.USE_LIFELINE == action);
                }
            }
        } else {
            this.isFinished = true;
        }

        //after finishing 3 quizes at a certain level, the game increases the livel.
        this.level = Level.getNext(this.level);
    }

    /**
     * Ask player's action
     *
     * @param quiz
     * @return player's acction
     */
    public int askAction(QuizInfo quiz) {
        ActionView actionView = new ActionView();
        int input = actionView.askAction(player.getLifelines(), this.scanner);

        switch (input) {
            case Const.ANSWER_QUIZ:
                askAnswer(quiz);
                break;
            case Const.USE_LIFELINE:
                chooseLifien(quiz);
                break;
            case Const.QUIT:
                this.isFinished = true;
                break;
            default:
                break;
        }

        return input;
    }

    /**
     * Ask player's answer, then judge it.
     *
     * @param quiz
     */
    public void askAnswer(QuizInfo quiz) {
        AnswerView answerView = new AnswerView();
        int playerAnswer = answerView.askAnswer(quiz.getOption(), this.scanner);

        JudgeService judgeService = new JudgeService();
        if (!judgeService.judge(quiz.getQuiz().getAnswer(), playerAnswer, this.player, this.round)) {
            this.isFinished = true;
        }
        showResult();
    }

    /**
     * Dislay available lifelies and execute the chosen one.
     *
     * @param quiz
     */
    public void chooseLifien(QuizInfo quiz) {
        LifelineView lifelineView = new LifelineView();
        Lifeline chosen = lifelineView.chooseLifeline(this.player.getLifelines(), this.scanner);

        LifelineService lifelineService = new LifelineService();
        lifelineService.execute(chosen, quiz);
    }

    /**
     * Display player's score.
     */
    public void showResult() {
        ResultView resultView = new ResultView();
        resultView.showResult(isFinished, round);
        
        this.round++;
        
        if (this.round > Const.FINAL_ROUND) {
            this.isFinished = true;
        }
    }

    /**
     * Display ending and ask continue.
     */
    public void showEnding() {
        EndingView resultView = new EndingView();
        resultView.displayResult(player);

        if (!resultView.askContinue(this.scanner)) {
            this.isExited = true;
        }
    }

    /**
     * Register player information to a player file and a ranking file.
     */
    public void registData() {
        //register player to a plater file
        PlayerRegisterService playerService = new PlayerRegisterService();
        playerService.registPlayer(this.player);

        //register player to a ranking file
        RankingService rankingService = new RankingService();
        rankingService.registerToRanking(this.player);
    }

    /**
     *
     * @return true if a plyer choose not to continue the game. False ifa player
     * choose to continue.
     */
    public boolean isExited() {
        return this.isExited;
    }

    /**
     *
     * @return true if a plyer quit or a player clear the game. Otherwise,
     * false.
     */
    public boolean isGameFinished() {
        return this.isFinished;
    }
}
