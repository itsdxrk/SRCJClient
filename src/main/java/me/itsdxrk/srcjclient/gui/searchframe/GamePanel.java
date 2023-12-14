package me.itsdxrk.srcjclient.gui.searchframe;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import me.itsdxrk.srcjclient.api.SRCApi;
import me.itsdxrk.srcjclient.models.*;
import me.itsdxrk.srcjclient.gui.wrframe.WRGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.SwingConstants.CENTER;
import static me.itsdxrk.srcjclient.gui.searchframe.CategoryPanel.catList;
import static me.itsdxrk.srcjclient.gui.searchframe.CategoryPanel.gameList;
import static me.itsdxrk.srcjclient.gui.searchframe.SubcategoryPanel.listOfSubcatLists;
import static me.itsdxrk.srcjclient.gui.searchframe.SubcategoryPanel.subcatList1;

public class GamePanel extends JPanel {

    public static JLabel imgLabel = new JLabel();
    public static JLabel gameTitleLabel = new JLabel();
    public static JButton modListButton = new JButton("Moderator List");
    public static JButton wrInfoButton = new JButton("World Record Information");
    public static ArrayList<UserID> wrHolders = new ArrayList<>();

    SRCApi api = new SRCApi();

    public GamePanel() {
        this.setupWindow();
    }
    private void setupWindow() {
        this.setLayout(new GridBagLayout());
        this.setBorder(new FlatMarginBorder(new Insets(5, 5, 5, 5)));
        this.addComponents();
        this.setVisible(true);
    }

    private void addComponents() {
        imgLabel.setHorizontalAlignment(CENTER);
        gameTitleLabel.setHorizontalAlignment(CENTER);
        this.add(imgLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        this.add(gameTitleLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1, 10, 1, new Insets(15, 0, 15, 0), 0, 0));
        this.add(modListButton, new GridBagConstraints(0, 2, 1, 1, 1, 1, 10, 2, new Insets(2, 0, 2, 0), 5, 5));
        this.add(wrInfoButton, new GridBagConstraints(0, 3, 1, 1, 1, 1, 10, 2, new Insets(2, 0, 2, 0), 15, 25));
        modListButton.setEnabled(false);
        wrInfoButton.setEnabled(false);

        wrInfoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WRGUI();
                ArrayList<JList> lastVisibleList = new ArrayList<>();
                ArrayList<JList> allVisibleLists = new ArrayList<>();
                lastVisibleList.add(subcatList1);
                for (JList list : listOfSubcatLists) {
                    if (list.isVisible()) {
                        lastVisibleList.set(0, list);
                        allVisibleLists.add(list);
                    }
                }
                JList lastSubcatList = lastVisibleList.get(0);
                if (lastSubcatList.getSelectedValue() != null) {
                    GameID game = gameList.getSelectedValue();
                    String gameId = game.gameId;
                    CategoryID cat = catList.getSelectedValue();
                    String catId = cat.categoryId;
                    ArrayList<VariablesValuesIDs> valist = new ArrayList<>();
                    for (JList list : allVisibleLists) {
                        SubcategoryID listValue = (SubcategoryID) list.getSelectedValue();
                        String variableId = listValue.getVarId();
                        String valueId = listValue.getId();
                        VariablesValuesIDs idPair = (new VariablesValuesIDs(variableId, valueId));
                        valist.add(idPair);
                    }

                    ArrayList<String> wrTime = api.getWRTime(gameId, catId, valist);
                    String wrIgt = null;
                    if (wrTime.size() > 2) {
                        wrIgt = wrTime.get(0).toString();
                        wrTime.remove(0);
                    }
                    String wrRealTime = wrTime.get(0).toString();


                    ArrayList<String> allWRHolderNames = new ArrayList<>();
                    wrHolders = api.getWRNames(gameId, catId, valist);
                    for (UserID user : wrHolders) {
                        allWRHolderNames.add(user.getName());
                    }
                    String wrHolder = String.join(", ", allWRHolderNames);
                    String message = "World Record Holder: " + wrHolder + "\nTime: " + wrRealTime;
                    if (wrIgt != null) {
                        message = message + "\nIn Game Time: " + wrIgt;
                    }
                    JOptionPane.showMessageDialog(null, message);
                }
            }
        });
    }
}
