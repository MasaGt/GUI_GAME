/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.dao;

import game.entity.OptionDto;
import java.util.List;

/**
 * The Object for writing to and reading from a option file.
 * @author Masaomi
 */
public interface IOptionDao {
    List<OptionDto> getById(int id);
}
