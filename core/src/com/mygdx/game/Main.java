package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screens.GameScreen;
import com.mygdx.game.screens.MainScreen;
import com.mygdx.game.screens.Screens;

/*
IMPORTANTE:MI JUEGO ESTA PENSADO PARA EJCUTARSE EN ESCRITORIO, PORQUE NO  CONSIGO REDIMENSIONAR LA IMAGEN DEL FONDO


*/
public class Main extends Game {
	private SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		Screens.juego = this;
		Screens.GAMESCREEN = new GameScreen(this);
		Screens.MAINSCREEN = new MainScreen(this);
		setScreen(Screens.MAINSCREEN);
	}

	@Override
	public void dispose(){
		batch.dispose();
		Screens.GAMESCREEN.dispose();
	}
	public  SpriteBatch getBatch() {
		return batch;
	}
}
