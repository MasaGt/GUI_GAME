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
import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * Strat display class
 *
 * @author Masaomi
 */
public class StartPanel extends Panel {

    private Player player;
    public Image image;
    private ImageIcon icon;
    private Image smallImg;
    private JLabel iconLabel;
    private JButton newGameBtn;
    private JButton continueBtn;
    private JButton rankingBtn;

    public StartPanel(Player player) {

        super(new BorderLayout());
        setLayout(null);
        setPreferredSize(new Dimension(500, 600));

        
        icon = new ImageIcon("./resources/icon.png");
        //scall icon
        //1st param: frame width, 2nd param: frame hieght.
        Image smallImg = icon.getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        
        iconLabel = new JLabel(new ImageIcon(smallImg));
        iconLabel.setLocation(100, 25);
        iconLabel.setSize(300, 300);
        add(iconLabel);

        newGameBtn = new JButton(Const.NEW_GAME_BUTTON);
        newGameBtn.setLocation(200, 350);
        newGameBtn.setSize(100, 50);
        add(newGameBtn);

        rankingBtn = new JButton(Const.RANKING_BUTTON);
        rankingBtn.setSize(100, 50);

        if (player != null) {
            //creaet the Continue button
            continueBtn = new JButton(Const.CONTINUE_BUTTON);
            continueBtn.setLocation(200, 425);
            continueBtn.setSize(100, 50);
            add(continueBtn);
            rankingBtn.setLocation(200, 500);

        } else {
            rankingBtn.setLocation(200, 425);
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
