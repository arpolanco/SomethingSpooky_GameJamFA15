package game;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.Sound;

public class Wraith extends Enemy 
{
    private Sound wavEffect;
    

    public Wraith(Vector2f pos, int rad, Spawner spawner)
    {
        super(pos, rad, spawner);
        mSpeed = 10f;
        mExposed = new Vector2f(4.55f,4.8f);
        try {
        wavEffect = new Sound("resources/WraithSpawnNoise.wav");
	} catch (SlickException ex) {
            Logger.getLogger(Wraith.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Something to get that LOVELY ghostly image
        try
        {
            mImageNormal = new Image("resources/wraith.png");
            mImageExposed = new Image("resources/wraithExposed.png");
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Wraith.class.getName()).log(Level.SEVERE, null, ex);
        }
        mDmg = 2;
    }

    @Override
    public void draw(Graphics g)
    {
        //Something to draw le ghosties
        super.draw(g);
    }

    @Override
    public void update(float elapsedTime) 
    {
        //Something to update le ghosties
        super.update(elapsedTime); 
        
        if(super.mTimeAlive > 4f && super.mTimeAlive < 4.5f)
        {
          mSpeed += 1000f * elapsedTime;  
        }
        if(super.mTimeAlive > 0f)
        {
        wavEffect.play(1.0f, 1.0f);
        }
        
    }

    @Override
    public void startMove(Vector2f targetPos) 
    {
        //Something to start moving leGhosties
        super.startMove(targetPos); 
    }
    
    
    
}
