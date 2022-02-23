package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.screens.Screens;

public class BotonPlay extends Button {

    public BotonPlay(int x, int y) {
        super(x, y);
        texture = new Texture((Gdx.files.internal("BotonPlay.png")));
    }

    @Override
    public void funcionamiento() {
        Screens.juego.setScreen(Screens.GAMESCREEN);
    }
}
