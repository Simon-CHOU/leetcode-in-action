package com.simon.viz;

import java.awt.*;

public class Planet {
    double x, y;
    double vx, vy;
    double ax, ay;
    double mass;
    int radius;
    Color color;
    private static final double G = 6.67408e-11;

    public Planet(double x, double y, int radius, Color color, double mass) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.mass = mass;
        this.vx = (Math.random() * 5 - 2); // 随机初始速度
        this.vy = (Math.random() * 5 - 2);
    }

    public void calculateForces(java.util.List<Planet> planets) {
        ax = 0;
        ay = 0;
        for (Planet other : planets) {
            if (this != other) {
                double dx = other.x - this.x;
                double dy = other.y - this.y;
                double distance = Math.sqrt(dx * dx + dy * dy);
                double force = G * this.mass * other.mass / (distance * distance);
                double angle = Math.atan2(dy, dx);
                ax += force * Math.cos(angle) / this.mass;
                ay += force * Math.sin(angle) / this.mass;
            }
        }
    }

    public void updatePosition() {
        vx += ax;
        vy += ay;
        x += vx;
        y += vy;
    }
}
