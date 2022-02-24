package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.actors.Ball;
import com.mygdx.game.actors.LeftPaddle;
import com.mygdx.game.Main;
import com.mygdx.game.actors.Paddle;
import com.mygdx.game.actors.RightPaddle;

public class GameScreen extends AbstractScreen {

    private SpriteBatch batch;
    private Texture texture;
    private  float escala;
    private Paddle palaizquierda;
    private RightPaddle paladerecha;
    private Ball ball;
    private BitmapFont font;
    private int puntuacion,puntuacionMaxima;
    private Preferences preferencias;

    public GameScreen(Main main) {

        super(main);
        preferencias = Gdx.app.getPreferences("Preferencias pong");
        puntuacionMaxima = preferencias.getInteger("puntuacionMaxima");

    }

    @Override
    public void show() {
        batch = main.getBatch();
        texture = new Texture(Gdx.files.internal("pongcampo.png"));
        Texture texturabola = new Texture(Gdx.files.internal("bola.png"));
        Texture texturapala = new Texture(Gdx.files.internal("pala.png"));
        ball = new Ball(Gdx.graphics.getWidth() / 2 - texturabola.getWidth() / 2 , Gdx.graphics.getHeight() / 2 - texturabola.getHeight() / 2);
        palaizquierda = new LeftPaddle(80,Gdx.graphics.getHeight() / 2- texturapala.getHeight() / 2);
        paladerecha = new RightPaddle(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() / 2- texturapala.getHeight() / 2,ball);
        font = new BitmapFont();
        font.setColor(Color.ORANGE);
        puntuacion = 0;
   }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        updatePuntuacion();
        palaizquierda.update();
        paladerecha.update();
        ball.update(palaizquierda,paladerecha);
        salirMenu();
        batch.begin();
        batch.draw(texture,0,0,texture.getWidth() / escala ,texture.getHeight() / escala);
        palaizquierda.draw(batch);
        paladerecha.draw(batch);
        font.draw(batch,"P " + Integer.toString(puntuacion), Gdx.graphics.getWidth() / 4,Gdx.graphics.getHeight() - 5);
        font.draw(batch,"PMX " + Integer.toString(puntuacionMaxima),  Gdx.graphics.getWidth()-Gdx.graphics.getWidth() / 4,Gdx.graphics.getHeight() - 5);
        ball.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) { // Método que sirve para redimensionar la pantalla del juego, en este caso, el fondo de pantalla.
        float widthImage = texture.getWidth();
        float heightImage = texture.getHeight();
        float r = heightImage / widthImage;
        if(heightImage > height) { // Si el fondo es mas alto que el alto de pantalla
            heightImage = height;
            widthImage = heightImage / r;
        }
        if(widthImage > width) { // Si el fondo es mas ancho que el ancho de la pantalla
            widthImage = width;
            heightImage = widthImage * r;
        }
        escala = width / widthImage; // Guardamos la escala
    }
    private  void updatePuntuacion(){
        if(ball.getBordes().overlaps(palaizquierda.getBordes())){
            puntuacion = puntuacion + 1;
            if(puntuacion > puntuacionMaxima){
                puntuacionMaxima = puntuacion;
            }
        }
        if(ball.getBordes().x <= 0){
            puntuacion = 0;
        }
        ball.comprobarPosicionBola();
    }
    private void salirMenu(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.BACK)){
            Screens.juego.setScreen(Screens.MAINSCREEN);
        }
    }

    public void hide() {
        font.dispose();
        texture.dispose();
    }

    @Override
    public void dispose(){
        preferencias.putInteger("puntuacionMaxima",puntuacionMaxima);
        preferencias.flush();

    }
}
