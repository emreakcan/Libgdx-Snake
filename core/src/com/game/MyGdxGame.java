package com.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Random;

import sun.rmi.runtime.Log;

public class MyGdxGame extends ApplicationAdapter {
    private ShapeRenderer sr;
    private final int KARE_BIRIM = 20;
    private final int fps = 7;
    private int counter = 0;
    private int frame[][] = new int[40][40];
    private int saha [][] = new int [40][40];


    Snake s;
    public static SpriteBatch batch;
    public static final float PPM = 100;
    OrthographicCamera cam;
    private Viewport viewport;
    private Controller controller;
    private int l = 3;
    private int control = 5;
    private boolean first = true;

    private int i = 13;
    private int j = 13;
    int mk = 0;



    @Override
    public void create() {
        cam = new OrthographicCamera();
        for (int k = 0; k < saha.length; k++) {
            for (int m = 0; m < saha.length ; m++) {
                saha[k][m] = 0;
            }
        }
        viewport = new FitViewport(10, 10);
        batch = new SpriteBatch();
        controller = new Controller();

        sr = new ShapeRenderer();
        s = new Snake(15, 15);
    }

    public void handleInput() {
        if (controller.isRightPressed()) {
            l = 3;
        } else if (controller.isLeftPressed()) {
            l = 4;
        } else if (controller.isDownPressed()) {
            l = 2;
        }
        else if (controller.isUpPressed()) {
            l = 1;
        }
    }


    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        viewport.update(width, height);
        controller.resize(width, height);
    }

    @Override
    public void render() {

        counter++;

        handleInput();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        sr.setColor(Color.BLUE);
        sr.begin(ShapeRenderer.ShapeType.Filled);

        for (int i = 10; i < 30; i++) {
            for (int j = 0; j < 20; j++) {
                if (frame[i][j] == 0) {
                    sr.setColor(Color.BLACK);
                    sr.rect(i * KARE_BIRIM, j * KARE_BIRIM + 390, KARE_BIRIM - 3, KARE_BIRIM - 3);
                }
            }
        }

        sr.setColor(Color.GREEN);
        Random generator = new Random();


        int ti = 13;
        int ji = 13;
        if(control == 5){
            sr.rect(ti * KARE_BIRIM, ji * KARE_BIRIM + 390, KARE_BIRIM - 3, KARE_BIRIM - 3);

        }



        if((s.head.x == i && s.head.y == j)){
                control = 7;
                i = generator.nextInt(16)+10;
                j = generator.nextInt(16)+3;

            s.grow();
            mk++;
        }

        sr.rect(i * KARE_BIRIM, j * KARE_BIRIM + 390, KARE_BIRIM - 3, KARE_BIRIM - 3);



        sr.setColor(Color.CYAN);
        SnakeNode sn = s.head;
        while(sn!=null){
            sr.rect(sn.x * KARE_BIRIM, sn.y * KARE_BIRIM + 390, KARE_BIRIM - 3, KARE_BIRIM - 3);
                if(mk>2){
                    saha[sn.x][sn.y] = 1;
                }
            sn = sn.nextNode;
            mk++;
        }

        mk=0;



        if (counter == fps) {
            if((s.head.x<19+10 && s.head.y <19 && s.head.x>10 && s.head.y >0)){
                if(saha[s.head.x][s.head.y] == 0 && saha[s.head.x][s.head.y] == 0)
                s.walk(l);
            }
            counter = 0;
        }



        for (int k = 0; k < saha.length; k++) {
            for (int m = 0; m < saha.length ; m++) {
                saha[k][m] = 0;
            }
        }

        sr.end();
        controller.draw();

    }


    @Override
    public void dispose() {
        sr.dispose();
    }
}
