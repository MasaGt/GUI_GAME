/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.entity.Player;
import game.util.Const;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Strat display class
 * @author Masaomi
 */
public class StartPanel extends Panel {
    
    private Player player;
    private JLabel icon;
    private JButton newGameBtn;
    private JButton continueBtn;
    private JButton rankingBtn;

    public StartPanel(Player player) {
        
        super(new BorderLayout());
        setLayout(null);
        setPreferredSize(new Dimension(500, 600));

        icon = new JLabel("Who Wants To Be A Millionaire");
        icon.setLocation(100, 100);
        icon.setSize(300, 100);
        icon.setBorder(BorderFactory.createLineBorder(Color.black));
        add(icon);

        newGameBtn = new JButton(Const.NEW_GAME_BUTTON);
        newGameBtn.setLocation(200, 250);
        newGameBtn.setSize(100, 50);
        add(newGameBtn);
        
        rankingBtn = new JButton(Const.RANKING_BUTTON);
        rankingBtn.setSize(100, 50);
        
        
        if (player != null) {
            //creaet the Continue button
            continueBtn = new JButton(Const.CONTINUE_BUTTON);
            continueBtn.setLocation(200, 350);
            continueBtn.setSize(100, 50);
            add(continueBtn);
            rankingBtn.setLocation(200, 450);
            
        } else {
            rankingBtn.setLocation(200, 350);
        }
        add(rankingBtn);
    }

    @Override
    public void addController(GameController controller) {
        newGameBtn.addActionListener(controller);
        if (continueBtn != null) {
            continueBtn.addActionListener(controller);
        }
        rankingBtn.addActionListener(controller);
    }
}
