package me.itsdxrk.srcjclient.gui.searchframe;

import com.formdev.flatlaf.ui.FlatMarginBorder;
import me.itsdxrk.srcjclient.api.SRCApi;
import me.itsdxrk.srcjclient.models.CategoryID;
import me.itsdxrk.srcjclient.models.GameID;
import me.itsdxrk.srcjclient.models.SubcategoryID;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static me.itsdxrk.srcjclient.gui.searchframe.CategoryPanel.*;
import static me.itsdxrk.srcjclient.gui.searchframe.SubcategoryPanel.listOfSubcatLists;

public class SearchPanel extends JPanel {

    SRCApi api = new SRCApi();
    public SearchPanel() {
        this.setLayout(new GridBagLayout());
        this.setBorder(new FlatMarginBorder(new Insets(5, 5, 5, 5)));
        this.addSearchComponents();
    }

    private void addSearchComponents() {
        JLabel queryLabel = new JLabel("Enter a game title:");
        JTextField queryField = new JTextField();
        JButton searchButton = new JButton("Search");

        add(queryLabel, new GridBagConstraints(0, 0, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        add(queryField, new GridBagConstraints(1, 0, 1, 2, 1, 0, 11, 1, new Insets(0, 5, 0, 0), 0, 0));
        add(searchButton, new GridBagConstraints(2, 0, 1, 2, 0, 0, 10, 0, new Insets(0, 5, 0, 0), 0, 0));

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                catList.setListData(new CategoryID[0]);
                for (JList<SubcategoryID> list : listOfSubcatLists) {
                    list.setListData(new SubcategoryID[0]);
                }

                ArrayList<GameID> searchResults = api.getGameSearch(queryField.getText());
                GameID[] searchArr = searchResults.toArray(new GameID[searchResults.size()]);
                gameList.setListData(searchArr);
            }
        });
    }

}
