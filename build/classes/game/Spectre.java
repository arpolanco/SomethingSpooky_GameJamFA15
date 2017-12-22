package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class Spectre extends Enemy 
{

    public Spectre(Vector2f pos, int rad, Spawner spawner)
    {
        super(pos, rad, spawner);
        mSpeed = 100.0f;
        mExposed = new Vector2f(6.3f,7f);
        
        //Something to get that LOVELY ghostly image
        try
        {
            mImageNormal = new Image("resources/spectre.png");
            mImageExposed = new Image("resources/spectreExposed.png");
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Ghost.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        if(super.mTimeAlive > 2f && super.mTimeAlive < 4f)
            mSpeed = -100f;
        else
            mSpeed = 100f;
        
        
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startMove(Vector2f targetPos) 
    {
        //Something to start moving leGhosties
        super.startMove(targetPos); 
    }
    
    
}