package com.mygdx.game;

import com.badlogic.gdx.Gdx;

public class RightPaddle extends Paddle{
    private Ball ball;
    public RightPaddle(float x, float y,Ball ball) {
        super(x, y);
        this.ball = ball;
    }

    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        float coordenadaPaddle = bordes.y + texture.getHeight() / 2;
        float coordenadaBall = ball.getBordes().y + ball.getBordes().getHeight() / 2;

        if (coordenadaPaddle >= coordenadaBall -10 && coordenadaPaddle <= coordenadaBall +10){
            coordenadaBall = coordenadaPaddle;
        }
        if(coordenadaBall < coordenadaPaddle) {
            if(choqueAbajo()){
                bordes.y = 0;
            }else{
                bordes.y = bordes.y - SPEED * delta;
            }
        }

        if(coordenadaBall > coordenadaPaddle) {
            if(choqueArriba()){
                bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
            }else{
                bordes.y = bordes.y + SPEED * delta;
            }
        }
    }
}
