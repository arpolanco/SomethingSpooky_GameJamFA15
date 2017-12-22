package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;

public class Phantom extends Enemy 
{

    public Phantom(Vector2f pos, int rad, Spawner spawner)
    {
        super(pos, rad, spawner);
        mSpeed = 100.0f;
        mExposed = new Vector2f(0f,0f);
        
        //Something to get that LOVELY ghostly image
        try
        {
            mImageNormal = new Image("resources/phantom.png");
            mImageExposed = new Image("resources/phantom.png");
        }
        catch (SlickException ex)
        {
            Logger.getLogger(Phantom.class.getName()).log(Level.SEVERE, null, ex);
        }
        mDmg = 0;
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
        super.update(elapsedTime); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startMove(Vector2f targetPos) 
    {
        //Something to start moving leGhosties
        super.startMove(targetPos); 
    }
    
    
    
}
