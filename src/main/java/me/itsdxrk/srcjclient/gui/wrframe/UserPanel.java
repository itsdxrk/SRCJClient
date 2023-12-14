package me.itsdxrk.srcjclient.gui.wrframe;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import me.itsdxrk.srcjclient.api.SRCApi;
import me.itsdxrk.srcjclient.gui.searchframe.GamePanel;
import me.itsdxrk.srcjclient.models.UserID;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.util.ArrayList;

import static me.itsdxrk.srcjclient.gui.searchframe.GamePanel.wrHolders;
public class UserPanel extends JPanel {

    SRCApi api = new SRCApi();
    public UserPanel() {
        setupPanel();
    }

    private void setupPanel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(new FlatMarginBorder(new Insets(5, 5, 5, 5)));
        this.addComponents();
        this.setVisible(true);
    }

    private void addComponents() {
        JLabel pfpLabel = new JLabel();
        JLabel playerNames = new JLabel();
        ArrayList<UserID> wrHolders = GamePanel.wrHolders;
        ArrayList<String> wrHolderNames = new ArrayList<>();
        for (UserID user : wrHolders) {
            wrHolderNames.add(user.getName());
        }
        playerNames.setText(String.join("\n", wrHolderNames));
        this.add(playerNames, new GridBagConstraints(0, 0, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        if (wrHolders.size() == 1) {
            BufferedImage pfp = api.getUserProfilePic(wrHolders.get(0).getId());
            pfpLabel.setIcon(new ImageIcon(pfp));
            this.add(pfpLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        }



    }
}
