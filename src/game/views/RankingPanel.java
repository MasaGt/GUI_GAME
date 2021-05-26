/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.entity.RankingDto;
import game.util.Const;
import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Masaomi
 */
public class RankingPanel extends Panel implements Observer {
    
    private JPanel rankingPanel;
    private JLabel rank;
    private JLabel playerNm;
    private JLabel score;
    private JButton closeBtn;
    
    public RankingPanel() {

        this.setPreferredSize(new Dimension(300, 200));

        closeBtn = new JButton(Const.CLOSE_BUTTON);
        closeBtn.setSize(100, 50);
        this.add(closeBtn);
    }

    @Override
    public void addController(GameController controller) {
        closeBtn.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        renderRanking((List<RankingDto>) arg);
    }

    @Override
    public Object getParam() {
        if (playerNm != null) {
            return playerNm.getText();
        }
        return null;
    }
    
    private void renderRanking(List<RankingDto> ranking) {
        for (RankingDto rankItem : ranking) {
            rank = new JLabel(Integer.toString(rankItem.getRank()));
            this.add(rank);
            playerNm = new JLabel(rankItem.getName());
            this.add(playerNm);
            score = new JLabel(Integer.toString(rankItem.getScore()));
            this.add(score);
        }
        
        this.add(closeBtn);
        
        this.revalidate();
        this.repaint();
    }

}
