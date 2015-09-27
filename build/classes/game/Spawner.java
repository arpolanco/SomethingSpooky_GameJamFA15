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
        Enemy e = new Ghost(new Vector2f(mPos.x, mPos.y), 10, this);
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
