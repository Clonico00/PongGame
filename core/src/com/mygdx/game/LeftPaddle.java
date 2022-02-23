package com.mygdx.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class LeftPaddle extends Paddle{

    public  static final float SPEED = 400;

    public LeftPaddle(float x, float y) {
        super(x, y);
    }

    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            inputDeskop(delta);
        }else if(Gdx.app.getType() == Application.ApplicationType.Android){
            inputAndroid(delta);
        }

    }

    private void inputDeskop(float delta){
        if(Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (choqueArriba()){
                bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
            }else{
                bordes.y = bordes.y + SPEED * delta;

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (choqueAbajo()){
                bordes.y = 0;
            }else{
                bordes.y = bordes.y - SPEED * delta;

            }
        }
    }
    private void inputAndroid(float delta){
        float coordenadaPaddle = bordes.y + texture.getHeight() / 2;

        if(Gdx.input.isTouched()){
            float coordenadaY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if(coordenadaPaddle >= coordenadaY - 5 && coordenadaPaddle <= coordenadaY + 5){
                coordenadaY = coordenadaPaddle;
            }
            if (coordenadaY < coordenadaPaddle ){
                if (choqueAbajo()){
                    bordes.y = 0;
                }else{
                    bordes.y = bordes.y - SPEED * delta;

                }
            }
            if (coordenadaY > coordenadaPaddle){
                if (choqueArriba()){
                    bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
                }else{
                    bordes.y = bordes.y + SPEED * delta;

                }
            }
        }
    }

    private boolean choqueArriba(){
        return bordes.y + texture.getHeight() >= Gdx.graphics.getHeight();
    }

    private  boolean choqueAbajo(){
        return bordes.y <= 0;
    }
}
