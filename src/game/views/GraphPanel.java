/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.util.Const;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the panel class for a bar graph of Audience.
 * @author Masaomi
 */
public class GraphPanel extends JPanel {

    //components
    private final JLabel option1;
    private final JLabel option2;
    private final JLabel option3;
    private final JLabel option4;

    //seize variables
    private final int MARGIN = 20;
    private final int MARGIN_BUTTOM = 50;
    private final int MARGIN_BET_BAR = 10;

    //other variables
    private final List<JLabel> lables;
    private final List<Color> colors;
    private Map<String, Integer> percentPerOption;

    /**
     * init the components.
     */
    public GraphPanel() {
        colors = new ArrayList<>();
        colors.add(Color.red);
        colors.add(Color.blue);
        colors.add(Color.green);
        colors.add(Color.yellow);
        this.percentPerOption = new HashMap<>();

        lables = new ArrayList<>();
        option1 = new JLabel();
        lables.add(option1);
        add(option1);
        option2 = new JLabel();
        lables.add(option2);
        add(option2);
        option3 = new JLabel();
        lables.add(option3);
        add(option3);
        option4 = new JLabel();
        lables.add(option4);
        add(option4);
    }

    /**
     * Draw a bar graph.
     * @param g
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int itemWidth = (this.getWidth() - MARGIN * 2) / percentPerOption.size();
        int yAxisStart = this.getHeight() - MARGIN_BUTTOM;
        //yAxisEnd = MARGIN
        //xAxisStart = MARGIN;
        int xAxisEnd = this.getWidth() - MARGIN_BUTTOM;
        //graw x-axis
        g.drawLine(MARGIN, yAxisStart, xAxisEnd, yAxisStart);
        //graw y-axis
        g.drawLine(MARGIN, yAxisStart, MARGIN, MARGIN);

        //graw bars
        int index = 0;
        for (Map.Entry<String, Integer> item : percentPerOption.entrySet()) {
            int xCoordinate = MARGIN + itemWidth * index + MARGIN_BET_BAR;
            int barWidth = (int) (itemWidth * 0.6);
            int barHeight = item.getValue();
            int yCoordinate = yAxisStart;
            g.setColor(colors.get(index));
            g.fillRect(xCoordinate, yCoordinate - barHeight, barWidth, barHeight);

            JLabel optionLabel = lables.get(index);
            optionLabel.setSize(new Dimension(barWidth, 100));
            optionLabel.setLocation(xCoordinate + 5, yCoordinate - MARGIN_BUTTOM / 2);
            optionLabel.setText(Const.wrapText(item.getKey()));
            optionLabel.setHorizontalAlignment(JLabel.CENTER);
            index++;
        }
    }

    /**
     * receive the result of audience.
     * @param percentages the result of audience
     */
    public void setPercentage(Map<String, Integer> percentages) {
        percentPerOption = percentages;
    }
}
