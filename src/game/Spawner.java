package game;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import java.lang.Math;
import game.*;

public class Spawner 
{
    Vector2f mPos;
    Enemy mCurrentEnemy;
    boolean mHasAttacked;
    boolean mCanSpawn;

    
    public Spawner(Vector2f pos)
    {
        //Something that constructs a spawner based on the position it was given
        mPos = pos;
        
    }
    
    public Enemy Spawn()
    {
        //Something to create a beautiful new enemy life
        mHasAttacked = false;
        float choose = wave.whichEnemy();
        Enemy e;
        
        e = new Ghost(new Vector2f(mPos.x, mPos.y), 10, this);
        
        if (choose > 0.6f && choose <= 0.8f)
        {
            e = new Phantom(new Vector2f(mPos.x, mPos.y), 10, this);
        }
        else if (choose > 0.8f && choose <= 0.95f)
        {
            e = new Spectre(new Vector2f(mPos.x, mPos.y), 10, this);
        }
        else if (choose > 0.95f && choose <= 1f)
        {
            e = new Wraith(new Vector2f(mPos.x, mPos.y), 10, this);
        }
        
        e.startMove(MainClass.center.copy());
        mCurrentEnemy = e;

        return e;
    }
    
    public void update(float elapsedTime)
    {
        //Something that determines if another enemy can be spawned
        if(mCurrentEnemy == null)
        {
            mCanSpawn = true;
        }
        else
        {
            mCanSpawn = false;
        }
    }
    
    public void draw(Graphics g)
    {
        //Something to draw le spawner if they want to be identified
        g.fillOval(mPos.x - 2, mPos.y - 2, 4, 4);
    }
}
