package org.bsuir.laba8.proj;

import org.bsuir.laba8.proj.view.LineFrame;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LineFrame();
            }
        });
    }
}
