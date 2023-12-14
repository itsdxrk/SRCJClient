package me.itsdxrk.srcjclient.gui.searchframe;

import com.formdev.flatlaf.ui.FlatMarginBorder;

import javax.swing.*;
import java.awt.*;

public class SearchGUI extends JFrame {

    public SearchGUI() {
        setLayout(new GridBagLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(new FlatMarginBorder(new Insets(5, 5, 5, 5)));
        add(mainPanel, new GridBagConstraints(
                0,
                0,
                1,
                1,
                1,
                1,
                10,
                1,
                new Insets(0, 0, 0, 0),
                0,
                0
        ));
        mainPanel.add(new SearchPanel(), new GridBagConstraints(
                0,
                0,
                1,
                1,
                1,
                0,
                10,
                1,
                new Insets(0, 0, 0, 0),
                0,
                0
        ));
        mainPanel.add(new CategoryPanel(), new GridBagConstraints(
                0,
                1,
                1,
                1,
                0,
                1,
                10,
                1,
                new Insets(0, 0, 0, 0),
                0,
                0
        ));
        mainPanel.add(new GamePanel(), new GridBagConstraints(
                1,
                0,
                1,
                2,
                1,
                1,
                13,
                2,
                new Insets(0, 0, 0, 0),
                0,
                0
        ));

        setContentPane(mainPanel);
        setTitle("SRCJ Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setVisible(true);

    }


}
