package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.actors.Ball;
import com.mygdx.game.actors.LeftPaddle;
import com.mygdx.game.Main;
import com.mygdx.game.actors.Paddle;
import com.mygdx.game.actors.RightPaddle;

//IMPORTANTE:MI JUEGO ESTA PENSADO PARA EJCUTARSE EN ESCRITORIO, PORQUE NO  CONSIGO REDIMENSIONAR LA IMAGEN DEL FONDO

public class GameScreen extends AbstractScreen {

    private SpriteBatch batch;
    private Texture texture;
    private  float escala;
    private Paddle palaizquierda;
    private RightPaddle paladerecha;
    private Ball ball;
    private BitmapFont font; // Nos permite introducir texto en en el juego.
    private Preferences preferencias; // Permite almacenar información en un fichero xml, de manera que el juego pueda extraerlos y sobreescribirlos.
    private int puntuacion, puntuacionMaxima; // Las dos puntuaciones del juego.
    private int gol,golesmaximo;

    public GameScreen(Main main) {

        super(main);
        preferencias = Gdx.app.getPreferences("Preferencias pong");
        puntuacionMaxima = preferencias.getInteger("puntuacionMaxima");
        golesmaximo = preferencias.getInteger("golesmaximo");
    }

    @Override
    public void show() {
        batch = main.getBatch();
        texture = new Texture(Gdx.files.internal("pongcampo.png"));
        Texture texturabola = new Texture(Gdx.files.internal("bola.png"));// Cogemos la textura para calcular el alto y ancho de la bola y centrarla en la pantalla
        Texture texturapala = new Texture(Gdx.files.internal("pala.png"));// Cogemos la textura para calcular el alto de la pala y centrarla en la pantalla
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

        updatePuntuacion();// Actualizamos los valores de la puntuación.
        palaizquierda.update();// Método que permitirá actualizar los valores de la pala, así como detectar si estamos pulsando el botón adecuado para para moverla.
        paladerecha.update();// Actualizamos los valores de la pala derecha, para que se mueva en la pantalla.
        ball.update(palaizquierda,paladerecha);// Permite que la bola se mueva, y al pasarle las dos palas podemos detectar la colisión con ellas.
        salirMenu();
        batch.begin();
        resize(texture.getWidth(), texture.getHeight());
        batch.draw(texture,0,0,texture.getWidth() ,texture.getHeight() );
        palaizquierda.draw(batch);
        paladerecha.draw(batch);
        font.draw(batch,"Puntuacion " + Integer.toString(puntuacion), (float) (Gdx.graphics.getWidth() / 8.5),Gdx.graphics.getHeight() - 5);
        font.draw(batch,"Puntuacion Maxima " + Integer.toString(puntuacionMaxima), (float) (Gdx.graphics.getWidth()-Gdx.graphics.getWidth() / 2.2),Gdx.graphics.getHeight() - 5);
        font.draw(batch,"Goles " + Integer.toString(gol), Gdx.graphics.getWidth() / 3,Gdx.graphics.getHeight() - 5);
        font.draw(batch,"Goles Maximos " + Integer.toString(golesmaximo), (float) (Gdx.graphics.getWidth()-Gdx.graphics.getWidth() / 4.5),Gdx.graphics.getHeight() - 5);
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
        if(ball.getBordes().overlaps(palaizquierda.getBordes())){// Si colisiona la bola con la pala izquierda
            puntuacion = puntuacion + 1;
            if(puntuacion > puntuacionMaxima){// Si la puntuación es mayor que la puntuacion máxima.
                puntuacionMaxima = puntuacion;
            }
        }
        if(ball.getBordes().x <= 0){ // Si se sale la pelota por el extremo izquierdo
            puntuacion = 0;

        }
        if (ball.getBordes().x > Gdx.graphics.getWidth()){
            gol = gol + 1;
            if(gol > golesmaximo){// Si la pun0tuación es mayor que la puntuacion máxima.
                golesmaximo = gol;
            }
        }
        ball.comprobarPosicionBola();// Colocamos la bola en su posicion si se sale del escenario.
    }
    private void salirMenu(){
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyPressed(Input.Keys.BACK)){// Si se pulsa el botón "escape" en PC o "Back" en android
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
        preferencias.putInteger("golesmaximo",golesmaximo);
        preferencias.flush();

    }
}
