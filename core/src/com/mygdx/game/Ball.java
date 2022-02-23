package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Ball {
    private static final float SPEED = 200;

    private Texture texture;
    private Rectangle bordes;
    private int direccionX, direccionY;
    private float posicionOriginalX,posicionOriginalY;

    public Ball(float x, float y) {
        texture = new Texture(Gdx.files.internal("bola.png"));
        bordes = new Rectangle(x,y, texture.getWidth(), texture.getHeight());
        direccionX = direccionY = 1;
        posicionOriginalX = x;
        posicionOriginalY = y;
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,bordes.x,bordes.y,texture.getWidth(),texture.getHeight());
    }

    public void update(Paddle leftPaddle, Paddle rightPaddle){
        float delta = Gdx.graphics.getDeltaTime();
        if(choqueParedes()){
            direccionY = direccionY * -1;
        }
        if(choquePalas(leftPaddle.getBordes(),rightPaddle.getBordes())){
            direccionX = direccionX * -1;
        }
        bordes.x = bordes.x + SPEED * delta * direccionX;
        bordes.y = bordes.y + SPEED * delta * direccionY;
    }
    private boolean choqueParedes(){
        if(bordes.y + texture.getHeight() > Gdx.graphics.getHeight()){
            bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
            return true;
        }else if(bordes.y <= 0) {
                return true;
        }
        else return false;
    }

    private boolean choquePalas(Rectangle lPaddle, Rectangle rPaddle){
        if(bordes.overlaps(lPaddle)){
            bordes.x = lPaddle.x + lPaddle.getWidth();
            return true;
        }else if(bordes.overlaps(rPaddle)){
            bordes.x = rPaddle.x - bordes.getWidth();
            return true;
        }
        else return false;
    }
    public void comprobarPosicionBola(){
        if(bordes.x < 0 || bordes.x > Gdx.graphics.getWidth()) {
            bordes.x = posicionOriginalX;
            bordes.y = posicionOriginalY;
        }
    }
    public  Rectangle getBordes(){
        return bordes;
    }
}
