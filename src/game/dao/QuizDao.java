/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.QuizDto;
import game.util.Const;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a quiz file.
 * @author Masaomi
 */
public class QuizDao implements IQuizDao {

    private final String FILE_PATH;

    public QuizDao() {
        this.FILE_PATH = "./DataStore/quiz.txt";
    }

    /**
     * Read quizes by specified level
     * @param level
     * @return 
     */
    @Override
    public List<QuizDto> getByLevel(int level) {
        BufferedReader br = null;

        List<QuizDto> quizes = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            br = new BufferedReader(new FileReader(file));

            String line = "";
            while ((line = br.readLine()) != null) {
                String[] props = line.split(Const.DELIMITER);

                if (level == Integer.parseInt(props[3].trim())) {
                    QuizDto quiz = new QuizDto();
                    quiz.setId(Integer.parseInt(props[0].trim()));
                    quiz.setStatement(props[1]);
                    quiz.setAnswer(Integer.parseInt(props[2].trim()));
                    quizes.add(quiz);
                }
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return quizes;
    }

}
