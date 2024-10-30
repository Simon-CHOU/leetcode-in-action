package com.simon.viz;

import javax.swing.*;
import java.awt.*;

public class ThreeBodySimulation extends JFrame {

    public ThreeBodySimulation() {
        setTitle("三体运动模拟");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 居中显示
        add(new ThreeBodyPanel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ThreeBodySimulation().setVisible(true);
        });
    }
}
