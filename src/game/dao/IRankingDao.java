/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.RankingDto;
import java.util.List;

/**
 * The Object for writing to and reading from a ranking file.
 * @author Masaomi
 */
public interface IRankingDao {

    List<RankingDto> getAll();
    void register(List<RankingDto> ranking);
}
