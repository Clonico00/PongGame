package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.actors.BotonExit;
import com.mygdx.game.actors.BotonPlay;
import com.mygdx.game.actors.Button;
import com.mygdx.game.Main;

public class MainScreen extends AbstractScreen {

    private SpriteBatch batch;
    private Button exit,play;

    public MainScreen(Main main) {
        super(main);
    }

    public void show() {// Método que se llama cuando se establece esta pantalla como actual
        batch = main.getBatch();
        Texture texture = new Texture(Gdx.files.internal("BotonExit.png"));// Cogemos la textura del botón para usar su ancho y alto
        int centroY = Gdx.graphics.getHeight() / 2 - texture.getHeight() / 2; // Centro en el eje x de la pantalla centrando el botón
        int centroX = Gdx.graphics.getWidth() / 2 - texture.getWidth() / 2; // Centro en el eje y de la pantalla centrando el botón
         exit = new BotonExit(centroX,centroY - 50);
         play = new BotonPlay(centroX,centroY + 50);
    }

    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        exit.update();// Comprobamos que se pulsan los botones
        play.update();

        batch.begin();
        exit.draw(batch);
        play.draw(batch);
        batch.end();
    }
}
