package com.simon.viz;

import java.awt.*;

public class Planet {
    int x, y;
    int radius;
    Color color;
    int vx, vy;

    public Planet(int x, int y, int radius, Color color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.vx = (int) (Math.random() * 5 - 2); // 随机初始速度
        this.vy = (int) (Math.random() * 5 - 2);
    }
}
