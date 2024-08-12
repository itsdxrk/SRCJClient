package me.itsdxrk.srcjclient.gui.wrframe;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import me.itsdxrk.srcjclient.api.SRCApi;
import me.itsdxrk.srcjclient.gui.searchframe.GamePanel;
import me.itsdxrk.srcjclient.models.UserID;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class UserPanel extends JPanel {

    public static JLabel timeLabel = new JLabel("", SwingConstants.LEFT);

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
        int loc = 0;
        JLabel playerNames = new JLabel();
        JLabel pfpLabel = new JLabel();
        ArrayList<UserID> wrHolders = GamePanel.wrHolders;
        ArrayList<String> wrHolderNames = new ArrayList<>();
        for (UserID user : wrHolders) {
            wrHolderNames.add(user.getName());
        }
        playerNames.setText(String.join(", ", wrHolderNames));
        if (wrHolders.size() == 1) {
            BufferedImage pfp = api.getUserProfilePic(wrHolders.get(0).getId());
            if (pfp != null) {
                pfpLabel.setIcon(new ImageIcon(pfp));
                this.add(pfpLabel, new GridBagConstraints(0, 0, 1, 1, 2, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
            }
            loc++;
        }
        this.add(playerNames, new GridBagConstraints(0, loc, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        this.add(timeLabel, new GridBagConstraints(0, loc+1, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));



    }
}
