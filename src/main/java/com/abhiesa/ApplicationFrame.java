package com.abhiesa;

import javax.swing.*;

/**
 * @author abhiesa
 * */
public class ApplicationFrame extends JFrame {

    public ApplicationFrame(final String title, final int width,
                            final int height) {

        setVisible(true);
        ///setResizable(false);
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(width,height);
    }
}
