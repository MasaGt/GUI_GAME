/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Audiemce object.
 *
 * @author Masaomi
 */
public class Audience extends Lifeline {

    public Audience(String name) {
        super(name);
    }

    /**
     * Show the result of poll. The highest percentage should be for the correct
     * answer. Totla parcentage should be 100%.
     *
     * @param quiz
     */
    @Override
    public void execute(QuizInfo quiz) {

        if (!this.getIsAvailable()) {
            return;
        }

        Random rand = new Random();
        List<OptionDto> options = quiz.getOption();
        //key: optionId, value: percentage
        Map<Integer, Integer> percentPerOption = new HashMap<>();

        int total = 100;
        //randForCorrect will be between 51 to 100 so that it should be  heighest.
        int randForCorrect = rand.nextInt(49) + 51;
        int left = total - randForCorrect;
        int randForOther = rand.nextInt(left);
        int wrongCounter = 1;
        int wrongOptionsSize = options.size() - 1;

        //allocate each parcentage to each option
        for (int i = 0; i < options.size(); i++) {
            OptionDto option = options.get(i);

            //check which otion is the correct answer
            if (quiz.getQuiz().getAnswer() == options.get(i).getId()) {
                percentPerOption.put(option.getId(), randForCorrect);
                randForCorrect = 0;
            } else {
                if (wrongCounter == wrongOptionsSize) {
                    percentPerOption.put(option.getId(), left);
                } else {
                    percentPerOption.put(option.getId(), randForOther);
                    left -= randForOther;
                    randForOther = rand.nextInt(left);
                }
                wrongCounter++;
            }
        }

        setChanged();
        notifyObservers(percentPerOption);
    }
}
