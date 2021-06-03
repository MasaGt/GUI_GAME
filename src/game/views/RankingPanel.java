/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.views;

import game.controller.GameController;
import game.entity.RankingDto;
import game.util.Const;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Masaomi
 */
public class RankingPanel extends Panel implements Observer {

    //components
    private JScrollPane rankingPanel;
    private JTable rankingTable;
    DefaultTableModel tableModel;
    private JPanel buttonPanel;
    private JButton closeBtn;

    //data variables
    private String[] columns;

    public RankingPanel() {

        super(new BorderLayout());
        this.setPreferredSize(new Dimension(300, 200));

        columns = new String[]{"Rank", "Name", "Score"};
        tableModel = new DefaultTableModel(columns, 0);
        rankingTable = new JTable(tableModel);
        rankingTable.setEnabled(false);
        
        rankingPanel = new JScrollPane(rankingTable);
        rankingPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rankingPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        rankingPanel.setPreferredSize(new Dimension(200, 150));
        add(rankingPanel, BorderLayout.CENTER);
        
        buttonPanel = new JPanel();
        add(buttonPanel, BorderLayout.SOUTH);

        closeBtn = new JButton(Const.CLOSE_BUTTON);
        buttonPanel.add(closeBtn);

    }

    @Override
    public void addController(GameController controller) {
        closeBtn.addActionListener(controller);
    }

    @Override
    public void update(Observable o, Object arg) {
        renderRanking((List<RankingDto>) arg);
    }

    private void renderRanking(List<RankingDto> ranking) {

        String rank;
        String name;
        String score;

        //clear table contents
        tableModel.setRowCount(0);
        
        //table header
//        tableModel = new DefaultTableModel(columns, 0);

        String[] record = new String[3];
        for (RankingDto item : ranking) {
            rank = Integer.toString(item.getRank());
            record[0] = rank;
            
            name = item.getName();
            record[1] = name;
            
            score = Integer.toString(item.getScore());
            record[2] = score;
            
            tableModel.addRow(record);
        }        
    }

}
