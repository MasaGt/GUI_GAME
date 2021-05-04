/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.RankingDto;
import game.util.Const;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data Acess Object for a ranking file.
 * @author Masaomi
 */
public class RankingDao implements IRankingDao {

    private final String FILE_PATH = "./DataStore/ranking.txt";

    /**
     * Read Rankng from Ranking File
     * @return List of RankingDto
     */
    @Override
    public List<RankingDto> getAll() {

        List<RankingDto> rankings = new ArrayList<>();
        BufferedReader br = null;
        
        try {
            File file = new File(FILE_PATH);
            br = new BufferedReader(new FileReader(file));

            String line = "";

            while ((line = br.readLine()) != null) {
                String[] props = line.split(Const.DELIMITER);

                RankingDto dto = new RankingDto();
                dto.setRank(Integer.parseInt(props[0]));
                dto.setPlayerId(Integer.parseInt(props[1]));
                dto.setName(props[2]);
                dto.setScore(Integer.parseInt(props[3]));
                rankings.add(dto);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RankingDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("There is not a ranking file");
            System.err.println("Reading ranking failed");
        } catch (IOException ex) {
            Logger.getLogger(RankingDao.class.getName()).log(Level.SEVERE, null, ex);
            
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(RankingDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return rankings;
    }

    /**
     * Register top10 Raning to Ranking File
     * @param ranking a list of sorted RankngDto
     */
    @Override
    public void register(List<RankingDto> ranking) {
        FileOutputStream fos;
        
        try {
            fos = new FileOutputStream(FILE_PATH);
            PrintWriter pw = new PrintWriter(fos);
            
            for (int i = 0; i < ranking.size(); i++) {
                //the following code might cause array index out pf bounds exception when ranking.size() < Const.RANKING_RECORD_LIMIT
                //for (int i = 0; i < Const.RANKING_RECORD_LIMIT; i++);
                if (i >= Const.RANKING_RECORD_LIMIT) {
                    break;
                }
                pw.println(ranking.get(i).getRank() + Const.DELIMITER 
                        + ranking.get(i).getPlayerId()+ Const.DELIMITER 
                        + ranking.get(i).getName()+ Const.DELIMITER 
                        + ranking.get(i).getScore());
            }
            pw.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RankingDao.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println("There is not a ranking file");
            System.err.println("Registering the ranking failed");
        }
    }
}
