package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Ball {
    private static final float SPEED = 200;

    private Texture texture;
    private Rectangle bordes;

    public Ball(float x, float y) {
        texture = new Texture(Gdx.files.internal("bola.png"));
    }
}
