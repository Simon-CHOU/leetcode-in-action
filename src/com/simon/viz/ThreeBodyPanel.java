package com.simon.viz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ThreeBodyPanel extends JPanel {

    private List<Planet> planets = new ArrayList<>();
    private List<Point> trajectories = new ArrayList<>();

    public ThreeBodyPanel() {
        planets.add(new Planet(100, 300, 5, Color.RED));
        planets.add(new Planet(400, 300, 5, Color.GREEN));
        planets.add(new Planet(700, 300, 5, Color.BLUE));

        Timer timer = new Timer(16, e -> {
            updatePositions();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 绘制轨迹
        for (Point p : trajectories) {
            g2d.setColor(Color.LIGHT_GRAY);
            g2d.fillOval(p.x - 1, p.y - 1, 2, 2);
        }

        // 绘制星球
        for (Planet planet : planets) {
            g2d.setColor(planet.color);
            g2d.fillOval(planet.x - planet.radius, planet.y - planet.radius, planet.radius * 2, planet.radius * 2);
        }
    }

    private void updatePositions() {
        for (Planet planet : planets) {
            // 简化的运动逻辑，实际应考虑引力作用
            planet.x += planet.vx;
            planet.y += planet.vy;

            // 记录轨迹
            trajectories.add(new Point(planet.x, planet.y));

            // 限制轨迹数量
            if (trajectories.size() > 1000) {
                trajectories.remove(0);
            }
        }
    }
}
