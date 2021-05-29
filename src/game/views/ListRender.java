/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.entity.GameData;
import javax.swing.DefaultListSelectionModel;

/**
 *
 * @author Masaomi
 */
public class ListRender extends DefaultListSelectionModel {

    private int stageLength;
    private GameData gameData;
    
    public ListRender(int stageLength, GameData gameData) {
        this.stageLength = stageLength;
        this.gameData = gameData;
    }
    
    @Override
    public boolean isSelectedIndex(int index) {
        
    return index == (stageLength-gameData.getRound());
  }
}
