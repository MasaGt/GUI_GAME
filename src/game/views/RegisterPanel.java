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
 *
 * @author Masaomi
 */
public class RegisterPanel extends Panel {
    
    private JLabel descLabel;
    private JLabel reEnterLabel;
    private JTextField name;
    private JButton registerBtn;
    
    private int dispWidth = 300;
    private int dispHeight = 300;
    private int lableWidth = 100; 
    private int labelheight = 20;
    
    
    public RegisterPanel() {
        
        setLayout(null);
        setPreferredSize(new Dimension(dispWidth, dispHeight));
        descLabel = new JLabel("Register your name (only alphabets and numbers)");
        descLabel.setSize(lableWidth, labelheight);
        descLabel.setMaximumSize(new Dimension(dispWidth, labelheight));
        descLabel.setLocation(100, 20);
        this.add(descLabel);
        
        name = new JTextField();
        name.setSize(100, 30);
        name.setLocation(100, 50);
        this.add(name);
        
        registerBtn = new JButton(Const.REGISTER_BUTTON);
        registerBtn.setSize(100, 30);
        registerBtn.setLocation(100, 80);
        this.add(registerBtn);
        this.revalidate();
        this.repaint();
    }
    
    @Override
    public Object getParam() {
        return name.getText();
    }

    @Override
    public void addController(GameController controller) {
        registerBtn.addActionListener(controller);
    }
    
    public void reEnterName() {
        reEnterLabel = new JLabel("Invalid player's name");
        reEnterLabel.setSize(lableWidth, labelheight);
        reEnterLabel.setMaximumSize(new Dimension(dispWidth, labelheight));
        reEnterLabel.setLocation(100, 0);
        reEnterLabel.setForeground(Color.red);
        this.add(reEnterLabel);
        repaint();
    }

}
