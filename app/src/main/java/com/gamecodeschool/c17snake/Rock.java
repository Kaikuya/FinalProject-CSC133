package com.gamecodeschool.c17snake;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.Random;

public class Rock extends GameObject implements ISpawnable {
    // The range of values we can choose from
    // to spawn a rock

    private boolean friendly = false;
    private Point mSpawnRange;
    Random random;

    /// Set up the rock in the constructor
    Rock(Context context, Point sr, int s){
        random = new Random();

        // Make a note of the passed in spawn range
        mSpawnRange = sr;
        // Make a note of the size of an apple
        mSize = s;
        // Hide the apple off-screen until the game starts
        location.x = -10;

        // Load the image to the bitmap
        mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.rock);

        // Resize the bitmap
        mBitmap = Bitmap.createScaledBitmap(mBitmap, s, s, false);
    }

    public void spawn(){   //when the game starts
        resetPosition();
    }
    public void spawn(ArrayList<Point> segmentLocations) {     //every time an apple is eaten
        //if apple is spawned in the snake
        while(InSnake.checkSpot(segmentLocations, location, -1)){
            resetPosition();
        }
    }

    public void resetPosition(){
        // Choose two random values and place the apple
        location.x = random.nextInt(mSpawnRange.x) + 1;
        location.y = random.nextInt(mSpawnRange.y - 1) + 1;
    }

    public boolean isFriendly(){
        return friendly;
    }
    public int penalty(int mScore){
        return mScore -= 1;
    }
}
