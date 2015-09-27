/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;

public class Scene
{
    Background[] mBackgrounds;
    Foreground[] mForegrounds;
    
    public Scene(String bgLocation, String fgLocation, Vector2f pos, float spd)
    {
        mBackgrounds = new Background[3];
        Image temp = null;
        try {
            temp = new Image(bgLocation);
        } catch (SlickException ex) {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
        mBackgrounds[0] = new Background(temp, new Vector2f(pos), spd);
        mBackgrounds[1] = new Background(temp, new Vector2f(pos.x + temp.getWidth(), pos.y), spd);
        mBackgrounds[2] = new Background(temp, new Vector2f(pos.x + (temp.getWidth()*2), pos.y), spd);
        
        
        mForegrounds = new Foreground[3];
        try {
            temp = new Image(fgLocation);
        } catch (SlickException ex) {
            Logger.getLogger(Scene.class.getName()).log(Level.SEVERE, null, ex);
        }
        mForegrounds[0] = new Foreground(temp, new Vector2f(pos), spd);
        mForegrounds[1] = new Foreground(temp, new Vector2f(pos.x + temp.getWidth(), pos.y), spd);
        mForegrounds[2] = new Foreground(temp, new Vector2f(pos.x + (temp.getWidth()*2), pos.y), spd);
    }
    
    public void update(GameContainer gc, int dt)
    {
        for (Background bg : mBackgrounds)
        {
            bg.update(gc, dt);
        }
        for (Foreground fg : mForegrounds)
        {
            fg.update(gc, dt);
        }
    }
    
    public void render(Graphics g)
    {
        for (Background bg : mBackgrounds)
        {
            bg.render(g);
        }
        for (Foreground fg : mForegrounds)
        {
            fg.render(g);
        }
    }
    
    abstract class SceneObj
    {
        Image mImage; 
        Vector2f mDir, mPos;
        float mSpeed;
        int mWidth;

        SceneObj(Image img, Vector2f pos, float spd)
        {
            mImage = img.copy();
            mPos = pos;
            mWidth = mImage.getWidth();             
            mDir = new Vector2f(-1f,0f).normalise();
            mSpeed = spd;
        }

        void update(GameContainer gc, int dt)
        {
            mPos.add(mDir.copy().scale(mSpeed*dt));

            if (mPos.x <= -512 - mWidth)
            {
                mPos.x += mWidth*3;
            }
        }

        void render(Graphics g)
        {
           g.drawImage(mImage, mPos.x, mPos.y);
        }
    } 
    
    class Background extends SceneObj
    {
        Background(Image img, Vector2f pos, float spd)
        {
            super(img, pos, spd);
            mSpeed += 0.01f;
        }
    }
    
    class Foreground extends SceneObj
    {
        public Foreground(Image img, Vector2f pos, float spd) 
        {
            super(img, pos, spd);
        }
        
    }
}

