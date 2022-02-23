package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends AbstractScreen{

    private SpriteBatch batch;
    private Texture texture;
    private  float escala;
    private  Paddle palaizquierda;
    private RightPaddle paladerecha;
    private Ball ball;
    public GameScreen(Main main) {
        super(main);
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("pongcampo.png"));
        Texture texturabola = new Texture(Gdx.files.internal("bola.png"));
        Texture texturapala = new Texture(Gdx.files.internal("pala.png"));
        ball = new Ball(Gdx.graphics.getWidth() / 2 - texturabola.getWidth() / 2 , Gdx.graphics.getHeight() / 2 - texturabola.getHeight() / 2);
        palaizquierda = new LeftPaddle(80,Gdx.graphics.getHeight() / 2- texturapala.getHeight() / 2);
        paladerecha = new RightPaddle(Gdx.graphics.getWidth() - 100, Gdx.graphics.getHeight() / 2- texturapala.getHeight() / 2,ball);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        palaizquierda.update();
        paladerecha.update();
        ball.update(palaizquierda,paladerecha);

        batch.begin();
        batch.draw(texture,0,0,texture.getWidth() / escala ,texture.getHeight() / escala);
        palaizquierda.draw(batch);
        paladerecha.draw(batch);
        ball.draw(batch);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        float widthImage = texture.getWidth();
        float heightImage = texture.getHeight();
        float r = heightImage / widthImage;

        if (heightImage > height){
            heightImage = height;
            widthImage = heightImage / r;
        }
        if (widthImage > width){
            widthImage = width;
            widthImage = heightImage * r;
        }
        escala = width / widthImage;
    }
}
