package me.itsdxrk.srcjclient.gui.wrframe;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import me.itsdxrk.srcjclient.api.SRCApi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class RunInfoPanel extends JPanel {

    SRCApi api = new SRCApi();

    public RunInfoPanel() {
        this.setupPanel();
    }

    private void setupPanel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(new FlatMarginBorder(new Insets(5, 5, 5, 5)));
        this.addComponents();
        this.setVisible(true);
    }

    private void addComponents() {
        JButton srcLinkBtn = new JButton("Speedrun.com Submission");
        this.add(srcLinkBtn, new GridBagConstraints(0, 0, 1, 1, 1, 1, 10, 2, new Insets(0, 0, 0, 0), 0, 0));

//        srcLinkBtn.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                URL wrLink = api.getWRLink();
//            }
//        });
    }
}
