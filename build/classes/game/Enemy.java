package game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import java.lang.Math;
import game.Player;

public abstract class Enemy 
{
    Vector2f mPos, mVel, mTargetPos;
    Vector2f mExposed; //Holds the min float value and max float value of the time the enemy is exposed
    
    Image mImageNormal, mImageExposed;
    
    float mTimeAlive;
    float mSpeed;
    int mRad;
    float mDist;
    Spawner mSpawner;
    boolean isAlive = true;
    
    public Enemy(Vector2f pos, int rad, Spawner spawner)
    {
        mPos = pos;
        mRad = rad;
        mTimeAlive = 0f;
        mSpawner = spawner;
    }
    
    public void startMove(Vector2f targetPos)
    {
        //Something to start the movement of the enemy based on the position of it spawning
        mTargetPos = new Vector2f(targetPos.x, targetPos.y);
        mVel = targetPos.sub(mPos).normalise();
    }
    
    public void update(float elapsedTime)
    {
        //Something to update the position of le ghosties
        mTimeAlive += elapsedTime;
        Vector2f tempVel = new Vector2f(mVel.x, mVel.y);
        mPos.add(tempVel.scale(elapsedTime).scale(mSpeed));
        if(mTargetPos.distance(mPos) <= 1)
        {
            mVel = new Vector2f(0,0);
        }
        
        //Something if le enemy intrudes the player's defenses and dunks him
        if(mTargetPos.distance(mPos) <= 1)
        {
            mVel = new Vector2f(0,0);
            isAlive = false;
            Player.health--;
        }
    }
    
    public void draw(Graphics g)
    {
        //Something that creates the graphics of le enemy
        Image img;
        img = mImageNormal;
        
        //Changes img if le enemy can be murdered
        if (canBeHit())
        {
            img = mImageExposed;
        }
        if (notHit())
        {
            mSpeed += 0.1f;
        }
        
        //Final product of le img
        g.drawImage(img, mPos.x - img.getWidth() / 2, mPos.y - img.getHeight()/2);

    }
    
    public boolean canBeHit()
    {
        //Something that refers to a timeframe in which the enemy can be demolished
        return (mExposed.x < mTimeAlive && mTimeAlive < mExposed.y);
    }
    
    public boolean notHit()
    {
        return (mTimeAlive > mExposed.y);
    }
    
}
