/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.QuizDto;
import java.util.List;

/**
 * The Object for writing to and reading  from a quiz file.
 * @author Masaomi
 */
public interface IQuizDao {
    
    List<QuizDto> getByLevel(int level);
    
}
