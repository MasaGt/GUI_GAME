/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.util.Const;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Masaomi
 */
public class AudiencePanel extends Panel implements Observer {


    private JLabel option1;
    private JLabel option2;
    private JLabel option3;
    private JLabel option4;
    private List<JLabel> lables;
    private JButton closeButton;
    private int labelWidth = 100;
    private int labelHeight = 50;
    private int margin = 20;
    
    public AudiencePanel() {

        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(300, 300));
        option1 = new JLabel();
        this.add(option1);
        option2 = new JLabel();
        this.add(option2);
        option3 = new JLabel();
        this.add(option3);
        option4 = new JLabel();
        this.add(option4);
        
        lables = new ArrayList<>();
        lables.add(option1);
        lables.add(option2);
        lables.add(option3);
        lables.add(option4);
        
        closeButton = new JButton(Const.CLOSE_BUTTON);
        this.add(closeButton);
    }

    @Override
    public Object getParam() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addController(GameController controller) {
        closeButton.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof Map) {
            render((Map<Integer, Integer>) arg);
        }
    }
    
    /**
     * render the quize, option buttons, update prize list, stage indicator.
     * @param percentPerOption 
     */
    private void render(Map<Integer, Integer> percentPerOption) {
        
        Iterator it = lables.iterator();
        for (Map.Entry<Integer, Integer> item : percentPerOption.entrySet()) {
            if (it.hasNext()) {
                JLabel label = ((JLabel)it.next());
                label.setText(item.getKey() + " : " + item.getValue() + "%");
                label.setSize(labelWidth, labelHeight);
            }
        }
    }
}
