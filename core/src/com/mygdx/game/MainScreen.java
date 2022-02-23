package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainScreen extends AbstractScreen{

    private SpriteBatch batch;
    private Button exit,play;

    public MainScreen(Main main) {
        super(main);
    }

    public void show() {
        batch = main.getBatch();
        Texture texture = new Texture(Gdx.files.internal("BotonExit.png"));
         int centroY = Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2;
         int centroX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2;
         exit = new BotonExit(centroX,centroY - 50);
         play = new BotonPlay(centroX,centroY + 50);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        exit.update();
        play.update();

        batch.begin();
        exit.draw(batch);
        play.draw(batch);
        batch.end();
    }
}
