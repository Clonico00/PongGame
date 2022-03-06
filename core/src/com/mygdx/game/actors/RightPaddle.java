package com.mygdx.game.actors;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.actors.Ball;
import com.mygdx.game.actors.Paddle;

public class RightPaddle extends Paddle {
    private Ball ball;// Bola del juego para que automáticamente la siga.

    public RightPaddle(float x, float y,Ball ball) {
        super(x, y);
        this.ball = ball;
    }

    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        float coordenadaPaddle = bordes.y + texture.getHeight() / 2; // Coordenada de la pala
        float coordenadaBall = ball.getBordes().y + ball.getBordes().getHeight() / 2; // Coordenada de la bola
        if(coordenadaPaddle >= coordenadaBall - 10 && coordenadaPaddle <= coordenadaBall + 10) // Si solo hay 20 de diferencia
            coordenadaBall = coordenadaPaddle; // Dejamos la pala quieta y evitamos saltos
        if(coordenadaBall < coordenadaPaddle) { // Si la coordenada de la bola es menor a la coordenada de la pala
            if(choqueAbajo())
                bordes.y = 0;
            else
                bordes.y = bordes.y - SPEED * delta; // Hacemos que se mueva hacia abajo
        }
        if(coordenadaBall > coordenadaPaddle) { // Si la coordenada de la bola es mayor a la coordenada de la pala
            if(choqueArriba())
                bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
            else
                bordes.y = bordes.y + SPEED * delta; // Hacemos que se mueva hacia arriba
        }
    }
}
