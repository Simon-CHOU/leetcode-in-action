package com.simon.viz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class ThreeBodyPanel extends JPanel {

    private final double G = 6.67430e-11; // 万有引力常量
    private  java.util.List<Planet> planets = new ArrayList<>();
    private java.util.List<Point> trajectories = new ArrayList<>();

    public ThreeBodyPanel() {
        planets.add(new Planet(100, 300, 5, Color.RED, 1e10));
        planets.add(new Planet(400, 300, 5, Color.GREEN, 1e10));
        planets.add(new Planet(700, 300, 5, Color.BLUE, 1e10));

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
            g2d.fillOval((int) (planet.x - planet.radius), (int) (planet.y - planet.radius), planet.radius * 2, planet.radius * 2);
        }
    }

    private void updatePositions() {
        for (Planet planet : planets) {
            planet.calculateForces(planets);
        }

        for (Planet planet : planets) {
            planet.updatePosition();
        }

        // 记录轨迹
        for (Planet planet : planets) {
            trajectories.add(new Point((int) planet.x, (int) planet.y));
        }

        // 限制轨迹数量
        if (trajectories.size() > 1000) {
            trajectories.remove(0);
        }
    }
}
