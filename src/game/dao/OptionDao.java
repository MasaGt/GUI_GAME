/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.OptionDto;
import game.util.Const;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a option file.
 * @author Masaomi
 */
public class OptionDao implements IOptionDao {

    private final String FILE_PATH;

    public OptionDao() {
        this.FILE_PATH = "./DataStore/option.txt";
    }

    @Override
    public List<OptionDto> getById(int id) {

        BufferedReader br = null;
        List<OptionDto> options = new ArrayList<>();

        try {
            File file = new File(FILE_PATH);
            br = new BufferedReader(new FileReader(file));
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] props = line.split(Const.DELIMITER);

                //get options whihc has the passed quiz_id
                if (id == Integer.parseInt(props[0].trim())) {
                    OptionDto option = new OptionDto();
                    option.setQuizId(Integer.parseInt(props[0].trim()));
                    option.setId(Integer.parseInt(props[1].trim()));
                    option.setStatement(props[2]);

                    options.add(option);
                }
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(QuizDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (br != null) {
                    br.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(OptionDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return options;
    }

}
