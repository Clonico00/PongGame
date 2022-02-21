package com.mygdx.game;

import com.badlogic.gdx.Game;

public class Main extends Game {
	public AbstractScreen GAMESCREEN;
	@Override
	public void create () {
		GAMESCREEN = new GameScreen(this);
		setScreen(GAMESCREEN);
	}

}
