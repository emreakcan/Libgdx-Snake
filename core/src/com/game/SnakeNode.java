package com.game;

/**
 * Created by emre on 5.10.2017.
 */

public class SnakeNode {

    SnakeNode nextNode;

    public int x,y;

    public SnakeNode(int x, int y){
        this.x = x;
        this.y = y;
        this.nextNode = null;
    }

    public void addNode(SnakeNode nextNode) {
        this.nextNode = nextNode;
    }

    public void walk(int newx, int newy){
        if(nextNode !=null){
            nextNode.walk(x,y);
        }

        x = newx;
        y = newy;
    }

}
