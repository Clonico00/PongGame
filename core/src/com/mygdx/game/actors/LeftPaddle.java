package com.mygdx.game.actors;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.actors.Paddle;

public class LeftPaddle extends Paddle {


    public LeftPaddle(float x, float y) {
        super(x, y);
    }

    @Override
    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
            // Si el juego se está ejecutando en escritorio
        if(Gdx.app.getType() == Application.ApplicationType.Desktop){
            inputDeskop(delta);
            // Si el juego se está ejecutando en android
        }else if(Gdx.app.getType() == Application.ApplicationType.Android){
            inputAndroid(delta);
        }

    }

    private void inputDeskop(float delta){ // Las entradas especificas para que el usuario pueda interactuar con el juego, ejecutandose en escritorio. Permite mover la pala.
        if(Gdx.input.isKeyPressed(Input.Keys.DPAD_UP)) {// Si estamos pulsando la tecla flecha arriba
            if (choqueArriba()){
                bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
            }else{
                bordes.y = bordes.y + SPEED * delta;// Hacemos que se mueva hacia arriba

            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DPAD_DOWN)){// Si estamos pulsando la tecla flecha abajo
            if (choqueAbajo()){
                bordes.y = 0;
            }else{
                bordes.y = bordes.y - SPEED * delta;// Hacemos que se mueva hacia abajo

            }
        }
    }
    private void inputAndroid(float delta) { // Las entradas especificas para que el usuario pueda interactuar con el juego, ejecutandose en android. Permite mover la pala.
        float coordenadaPaddle = bordes.y + texture.getHeight() / 2; // Cogemos la posición centrada de la pala en el eje y
        if(Gdx.input.isTouched()) { // Si estamos tocando la pantalla
            float coordenadaY = Gdx.graphics.getHeight() - Gdx.input.getY();
            if(coordenadaPaddle >= coordenadaY - 5 && coordenadaPaddle <= coordenadaY + 5) // Si solo hay 10 de diferencia
                coordenadaY = coordenadaPaddle; // Dejamos la pala quieta y evitamos saltos
            if(coordenadaY < coordenadaPaddle) { // Si la coordenada donde se pulsa es menor a la coordenada de la pala
                if(choqueAbajo())
                    bordes.y = 0;
                else
                    bordes.y = bordes.y - SPEED * delta; // Hacemos que se mueva hacia abajo
            }
            if(coordenadaY > coordenadaPaddle) { // Si la coordenada donde se pulsa es mayor a la coordenada de la pala
                if(choqueArriba())
                    bordes.y = Gdx.graphics.getHeight() - texture.getHeight();
                else
                    bordes.y = bordes.y + SPEED * delta; // Hacemos que se mueva hacia arriba
            }
        }
    }


}
