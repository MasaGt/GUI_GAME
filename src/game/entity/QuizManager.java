/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Masaomi
 */
public class QuizManager {
    private List<QuizInfo> quizes;
    private QuizInfo currentQuizInfo;
    private Iterator iterator;

    public QuizManager(List<QuizInfo> quizes) {
        this.quizes = quizes;
        iterator = quizes.iterator();
    }
    
    public QuizInfo getCurrentQuizInfo() {
        return currentQuizInfo;
    }

    public QuizDto getCurrentQuiz() {

        QuizDto currentQuiz = null;
        if (currentQuizInfo != null) {
            currentQuiz = currentQuizInfo.getQuiz();
        }

        return currentQuiz;
    }

    public List<OptionDto> getCurrentOption() {

        List<OptionDto> currentOptions = null;
        if (currentQuizInfo != null) {
            currentOptions = currentQuizInfo.getOption();
        }

        return currentOptions;
    }

    public int getCurrentAnswer() {

        int currentAnswer = 0;
        if (currentQuizInfo != null) {
            currentAnswer = currentQuizInfo.getQuiz().getAnswer();
        }

        return currentAnswer;
    }

    public boolean canMoveToNext() {

        return iterator.hasNext();
    }

    public void moveToNext() {
        if (canMoveToNext()) {
            setCurrentQuizInfo();
        }
    }

    private void setCurrentQuizInfo() {
        this.currentQuizInfo = (QuizInfo) iterator.next();
    }
}
