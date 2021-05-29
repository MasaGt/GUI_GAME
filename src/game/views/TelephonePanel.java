/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.util.Const;
import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Masaomi
 */
public class TelephonePanel extends Panel implements Observer{

    private JLabel label;
    private JButton closeBtn;
    
    public TelephonePanel() {
        
        super(new BorderLayout());
        
        label = new JLabel();
        this.add(label, BorderLayout.CENTER);
        
        closeBtn = new JButton(Const.CLOSE_BUTTON);
        this.add(closeBtn, BorderLayout.SOUTH);
        
        
    }
    
    @Override
    public Object getParam() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addController(GameController controller) {
        closeBtn.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            render((String) arg);
        }
    }
    
    private void render (String telText) {
        label.setText(telText);
    }
}
