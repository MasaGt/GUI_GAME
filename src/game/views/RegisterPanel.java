/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.util.Const;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * Register Panel for a new player.
 * @author Masaomi
 */
public class RegisterPanel extends Panel {
    
    private final JLabel descLabel;
    private final JLabel reEnterLabel;
    private final JTextField name;
    private final JButton registerBtn;
    
    private final int DISP_WIDTH = 350;
    private final int DISP_HEIGHT = 350;
    private final int LABEL_WIDTH = 200; 
    private final int LABEL_HEIGHT = 50;
    private final int MARGIN = 30;
    
    
    public RegisterPanel() {
        
        setLayout(null);
        setPreferredSize(new Dimension(DISP_WIDTH, DISP_HEIGHT));
        
        reEnterLabel = new JLabel(Const.wrapText("Invalid player's name"));
        reEnterLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        reEnterLabel.setForeground(Color.red);
        reEnterLabel.setVisible(false);
        reEnterLabel.setLocation(100, MARGIN);
        this.add(reEnterLabel);
        
        descLabel = new JLabel(Const.wrapText("Register your name (only alphabets and numbers)"));
        descLabel.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        descLabel.setLocation(100, reEnterLabel.getY() + reEnterLabel.getHeight());
        this.add(descLabel);
        
        name = new JTextField();
        name.setSize(100, 30);
        name.setLocation(125, descLabel.getY() + descLabel.getHeight() + MARGIN);
        this.add(name);
        
        registerBtn = new JButton(Const.REGISTER_BUTTON);
        registerBtn.setSize(100, 30);
        registerBtn.setLocation(125, name.getY() + name.getHeight() + MARGIN);
        this.add(registerBtn);
        this.revalidate();
        this.repaint();
    }
    
    /**
     * Return player's name.
     * @return 
     */
    public Object getParam() {
        return name.getText();
    }

    @Override
    public void addController(GameController controller) {
        registerBtn.addActionListener(controller);
    }
    
    /**
     * If player's name is in valid, this function will be called.
     * render a warning message.
     */
    public void reEnterName() {
        reEnterLabel.setVisible(true);
    }

}
