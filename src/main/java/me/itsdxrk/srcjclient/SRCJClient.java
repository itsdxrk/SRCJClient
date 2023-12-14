package me.itsdxrk.srcjclient;

import com.formdev.flatlaf.FlatDarkLaf;
import me.itsdxrk.srcjclient.gui.searchframe.SearchGUI;

import javax.swing.*;

public class SRCJClient {

    public static SearchGUI INSTANCE;
    public static void main(String[] args) {
        try {
            FlatDarkLaf.setup();
            INSTANCE = new SearchGUI();
        } catch (Throwable e) {
            JOptionPane.showMessageDialog(null, "Error, " + e);
        }

    }
}