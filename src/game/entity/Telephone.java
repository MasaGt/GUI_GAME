/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

/**
 * Telephon object.
 * @author Masaomi
 */
public class Telephone extends Lifeline {

    public Telephone(String name) {
        super(name);
    }

    /**
     * Diaplsy frend's message which suggest the correct answer.
     * @param quiz 
     */
    @Override
    public void execute(QuizInfo quiz) {

        if (!this.getIsAvailable()) {
            return;
        }

        int correctOptionId = quiz.getQuiz().getAnswer();
        String correctOption = "";
        for (OptionDto option : quiz.getOption()) {
            if (option.getId() == correctOptionId) {
                correctOption = option.getStatement();
                break;
            }
        }
        System.out.println("Friend: I guess the answer is " + correctOption);
    }

}
