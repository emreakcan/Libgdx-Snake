package com.game;

/**
 * Created by emre on 3.10.2017.
 */

public class Snake {
    int x,y;

    SnakeNode head;

    public Snake(int x, int y) {

        head = new SnakeNode(x,y);
    }

    public void walk(int l){
        if(l==1){
            head.walk(head.x,head.y + 1);
        }else if(l==2){
            head.walk(head.x,head.y - 1);
        }else if(l==3){
            head.walk(head.x + 1 ,head.y);
        }else{
            head.walk(head.x - 1,head.y);
        }
    }



    public void grow(){
        int x = head.x;
        int y = head.y;


        SnakeNode addOne = new SnakeNode(head.x,head.y);

        head.x = x;
        head.y = y;

        addOne.addNode(head);
        head = addOne;
    }
}
