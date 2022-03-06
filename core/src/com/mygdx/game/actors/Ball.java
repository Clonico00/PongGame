package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Ball {
    private float SPEED = 200;

    private Texture texture;
    private Rectangle bordes; // Objeto que nos determinará la posición de la bola y permite detectar colisiones con otros rectangulos.
    private int direccionX, direccionY; // Permite invertir la dirección de la bola en el eje x e y respectivamente
    private float posicionOriginalX, posicionOriginalY; // Guarda la posición original de la bola cuando esta se crea.
    private Sound sonido; // Sonido cuando colisiona con las palas

    public Ball(float x, float y) {
        texture = new Texture(Gdx.files.internal("bola.png"));
        bordes = new Rectangle(x,y, texture.getWidth(), texture.getHeight());
        // Lo ponemos a 1 los dos para que se mueva al inicio hacia arriba a la derecha
        direccionX = direccionY = 1;
        posicionOriginalX = x;
        posicionOriginalY = y;
        sonido = Gdx.audio.newSound(Gdx.files.internal("golpe.ogg"));
    }

    public void draw(SpriteBatch batch){
        batch.draw(texture,bordes.x,bordes.y,texture.getWidth(),texture.getHeight());
    }

    public void update(Paddle leftPaddle, Paddle rightPaddle){
        float delta = Gdx.graphics.getDeltaTime();
        if(choqueParedes()){
            // Cambiamos la dirección en el eje y
            direccionY = direccionY * -1;
        }
        if(choquePalas(leftPaddle.getBordes(),rightPaddle.getBordes())){
            // Cambiamos la dirección en el eje x
            direccionX = direccionX * -1;
            sonido.play();
            // Incrementamos la velocidad
            SPEED = SPEED +10;
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
            SPEED = 200;
        }
    }
    public  Rectangle getBordes(){
        return bordes;
    }
}
