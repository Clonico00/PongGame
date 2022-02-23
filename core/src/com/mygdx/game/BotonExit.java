package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class BotonExit extends Button{

    public BotonExit(int x, int y) {
        super(x, y);
        texture = new Texture(Gdx.files.internal("BotonExit.png"));
    }

    @Override
    public void funcionamiento() {
        Gdx.app.exit();
    }
}
