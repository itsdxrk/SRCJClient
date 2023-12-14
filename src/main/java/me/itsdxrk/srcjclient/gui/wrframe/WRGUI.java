package me.itsdxrk.srcjclient.gui.wrframe;

import com.formdev.flatlaf.ui.FlatMarginBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WRGUI extends JFrame {

    public WRGUI() {
        this.setupWindow();
    }

    private void setupWindow() {
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
        mainPanel.add(new UserPanel(), new GridBagConstraints(
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
        setContentPane(mainPanel);
        setTitle("World Record Information");
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                e.getWindow().dispose();
            }
        });
        setSize(1200, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
