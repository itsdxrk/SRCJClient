package me.itsdxrk.srcjclient.gui.searchframe;

import me.itsdxrk.srcjclient.models.SubcategoryID;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.ArrayList;

import static me.itsdxrk.srcjclient.gui.searchframe.GamePanel.wrInfoButton;

public class SubcategoryPanel extends JPanel {

    public static JList<SubcategoryID> subcatList1;
    public static JList<SubcategoryID> subcatList2;
    public static JList<SubcategoryID> subcatList3;
    public static JList<SubcategoryID> subcatList4;
    public static ArrayList<JList> listOfSubcatLists;
    public static ArrayList<JScrollPane> listOfScrolls;

    public SubcategoryPanel() {
        this.setupWindow();
    }

    private void setupWindow() {
        this.setLayout(new GridBagLayout());
        this.addSubcategoryComponents();
    }

    private void addSubcategoryComponents() {
        subcatList1 = new JList<>();
        subcatList2 = new JList<>();
        subcatList3 = new JList<>();
        subcatList4 = new JList<>();
        listOfSubcatLists = new ArrayList<>();
        listOfSubcatLists.add(subcatList1);
        listOfSubcatLists.add(subcatList2);
        listOfSubcatLists.add(subcatList3);
        listOfSubcatLists.add(subcatList4);
        JScrollPane scroll1 = new JScrollPane(subcatList1);
        JScrollPane scroll2 = new JScrollPane(subcatList2);
        JScrollPane scroll3 = new JScrollPane(subcatList3);
        JScrollPane scroll4 = new JScrollPane(subcatList4);
        listOfScrolls = new ArrayList<>();
        listOfScrolls.add(scroll1);
        listOfScrolls.add(scroll2);
        listOfScrolls.add(scroll3);
        listOfScrolls.add(scroll4);

        int i = 0;
        for (JScrollPane scroll : listOfScrolls) {
            this.add(scroll, new GridBagConstraints(2, i, 1, 1, 1, 1, 10, 1, new Insets(0, 0, 0, 0), 0, 0));
            scroll.setVisible(true);
            i++;
        }
        for (JList list : listOfSubcatLists) {
            list.setVisible(false);
        }

        subcatList1.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (subcatList1.getSelectedValue() != null) {
                    if (!(subcatList2.isVisible())) {
                        wrInfoButton.setEnabled(true);
                    }
                }
            }
        });
        subcatList2.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (subcatList2.getSelectedValue() != null) {
                    if (!(subcatList3.isVisible())) {
                        wrInfoButton.setEnabled(true);
                    }
                }
            }
        });
        subcatList3.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (subcatList3.getSelectedValue() != null) {
                    if (!(subcatList4.isVisible())) {
                        wrInfoButton.setEnabled(true);
                    }
                }
            }
        });
        subcatList4.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (subcatList4.getSelectedValue() != null) {
                    wrInfoButton.setEnabled(true);
                }
            }
        });
    }
}
