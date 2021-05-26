/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.util.Const;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Masaomi
 */
public class RegisterPanel extends Panel {
    
    private JLabel lable;
    private JTextField name;
    private JButton registerBtn;
    
    public RegisterPanel() {
        
        setLayout(null);
        setPreferredSize(new Dimension(300,200));
        lable = new JLabel("Register your name");
        lable.setSize(100,30);
        lable.setLocation(100, 20);
        this.add(lable);
        
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

}
