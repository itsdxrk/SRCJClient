package me.itsdxrk.srcjclient.gui.searchframe;

import me.itsdxrk.srcjclient.api.SRCApi;
import me.itsdxrk.srcjclient.models.CategoryID;
import me.itsdxrk.srcjclient.models.GameID;
import me.itsdxrk.srcjclient.models.SubcategoryID;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static me.itsdxrk.srcjclient.gui.searchframe.GamePanel.wrInfoButton;

public class CategoryPanel extends JPanel {

    public static JList<GameID> gameList;
    public static JList<CategoryID> catList;


    SRCApi api = new SRCApi();
    public CategoryPanel() {
        this.setupWindow();
    }

    private void setupWindow() {
        this.setLayout(new GridBagLayout());
//        this.setBorder(new FlatBorder());
        this.addCategoryComponents();
        this.addSubcategoryPanel();
    }

    private void addCategoryComponents() {
        gameList = new JList<>();
        catList = new JList<>();
        JScrollPane gameScroll = new JScrollPane(gameList);
        JScrollPane catScroll = new JScrollPane(catList);

        this.add(gameScroll, new GridBagConstraints(0, 0, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
        this.add(catScroll, new GridBagConstraints(1, 0, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));

        gameList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (gameList.getSelectedValue() != null) {
                    JLabel imgLabel = GamePanel.imgLabel;
                    JLabel gameTitleLabel = GamePanel.gameTitleLabel;
                    JButton modListButton = GamePanel.srcRunLinkButton;
                    wrInfoButton.setEnabled(false);
                    GameID item = gameList.getSelectedValue();
                    String id = item.gameId;
                    BufferedImage gameImage;
                    gameImage = api.getCoverImage(id);
                    imgLabel.setIcon(new ImageIcon(gameImage));

                    ArrayList<CategoryID> searchResults = api.getCats(id);
                    CategoryID[] searchArr = searchResults.toArray(new CategoryID[searchResults.size()]);
                    catList.setListData(searchArr);
                    gameTitleLabel.setText(item.toString());
                    gameTitleLabel.setForeground(api.getTheming(id));
                    modListButton.setEnabled(true);
                }
            }
        });

        catList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (catList.getSelectedValue() != null) {
                    wrInfoButton.setEnabled(false);
                    ArrayList<JList> listOfSubcatLists = SubcategoryPanel.listOfSubcatLists;
//                    wrInfoButton.setEnabled(false);
                    for (JList list : listOfSubcatLists) {
                        list.setListData(new Object[0]);
                        list.setVisible(false);
                    }
                    GameID game = gameList.getSelectedValue();
                    String gameId = game.gameId;
                    CategoryID cat = catList.getSelectedValue();
                    String catId = cat.categoryId;

                    ArrayList<ArrayList> searchResults = api.getSubcats(gameId, catId);
                    ArrayList<SubcategoryID> subcatsList = searchResults.get(0);
                    ArrayList<String> varIdList = searchResults.get(1);
                    int numOfIds = varIdList.size();

                    Boolean subcatsPresent = false;
                    for (int i = 0; i < numOfIds; i++) {
                        ArrayList<SubcategoryID> currentSubcats = new ArrayList<>();
                        for (SubcategoryID subcat : subcatsList) {
                            if (subcat.getVarId().equals(varIdList.get(i))) {
                                currentSubcats.add(subcat);
                            }
                        }
                        listOfSubcatLists.get(i).setListData(currentSubcats.toArray(new SubcategoryID[currentSubcats.size()]));
                        listOfSubcatLists.get(i).setVisible(true);
                        subcatsPresent = true;
                    }
                    if (!subcatsPresent) {
                        wrInfoButton.setEnabled(true);
                    }
                }
            }
        });
    }

    private void addSubcategoryPanel() {
        this.add(new SubcategoryPanel(), new GridBagConstraints(
                2,
                0,
                0,
                1,
                1,
                1,
                10,
                1,
                new Insets(0, 0, 0, 0),
                0,
                0
        ));
    }

}
