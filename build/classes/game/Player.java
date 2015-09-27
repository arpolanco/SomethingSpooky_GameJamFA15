package game;

import java.util.HashMap;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

/**
 *
 * @author myMom
 */
public class Player 
{
    Vector2f mPos;
    Vector2f mOrigin;
    float mWidth, mHeight;
    HashMap<String, Vector2f> mDirs = new HashMap();
    float mSpeed;
    
    //Statistics
    protected int numKills = 0;
    protected static int health;
    //if player pauses the game or nah
    boolean paused;
    
    public Player(Vector2f pos, Vector2f extent)
    {
        mDirs.put("topleft", new Vector2f(-1f,-1f).normalise());
        mDirs.put("topmiddle", new Vector2f(0f,-1f).normalise());
        mDirs.put("topright", new Vector2f(1f,-1f).normalise());
        mDirs.put("midleft", new Vector2f(-1f,0f).normalise());
        mDirs.put("midright", new Vector2f(1f,0f).normalise()); 
        mDirs.put("botleft", new Vector2f(-1f,1f).normalise()); 
        mDirs.put("botmiddle", new Vector2f(0f,1f).normalise()); 
        mDirs.put("botright", new Vector2f(1f,1f).normalise()); 
        
        mPos = pos;
        mOrigin = pos;
        mWidth = extent.x;
        mHeight = extent.y;
        mSpeed = 0.1f;
        health = 3;
        paused = false;
        
    }
    
    public void render(Graphics g)
    {
        //Something to render the homie
        g.setColor(Color.yellow);
        g.fillRect(mPos.x - mWidth / 2, mPos.y - mHeight / 2, mWidth, mHeight);
    }
    
    public void update(GameContainer gc, int dt, HashMap spawners)
    {
        //Something to juggles all the the users inputs which is pretty nifty
        //A SWITCH CASE WOULD OF BEEN NICER BUT NOOOO...MITCH HAS TO BE A HARDASS
        //<<<<<<<<33333333333333333333333333333333333333333333333333333333333333
        if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD7))
        {
            System.out.println("7");
            act("topleft", spawners);
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD8))
        {
            System.out.println("8");
            act("topmiddle", spawners);
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD9))
        {
            System.out.println("9");
            act("topright", spawners);
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD4))
        {
            System.out.println("4");
            act("midleft", spawners);
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD6))
        {
            System.out.println("6");
            act("midright", spawners);
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD1))
        {
            System.out.println("1");
            act("botleft", spawners);
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD2))
        {
            System.out.println("2");
            act("botmiddle", spawners);
        }
        else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD3))
        {
            System.out.println("3");
            act("botright", spawners);
        }
        
        //if the player wants to leave
        else if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            System.out.println("esc");
            paused = true;
        }
        
        //if the player wants to be super cool and run off into the abyss they get every right to
        if (mPos.distance(mOrigin) > 0.1f)
        {
            Vector2f dir = mOrigin.copy().sub(mPos.copy()).normalise();
            mPos.add(dir.scale(mSpeed*dt));
        }
        else
        {
            mPos = mOrigin.copy();
        }
    }
    
    public void act(String dir, HashMap spawners)
    {
        //Something to handle ghostly encounters
        Spawner s = (Spawner)spawners.get(dir);
        //If the player was smart and didn't wanna attack
        if(!s.mHasAttacked)
        {
            //If the player wasn't a spazz and hit the air like an idiot
            if(s.mCurrentEnemy != null)
            {
                //If the player 360 no-scoped 420 blazed the living underworld out of that ghost
                if(s.mCurrentEnemy.canBeHit())
                {
                    s.mCurrentEnemy.isAlive = false;
                    numKills++;
                }
                //otherwise...>.> well gg your lifespan kid
                s.mHasAttacked = true;
            }
        }
        //Player moves in that direction of the given direction in hopes to murder a ghost
        mPos.add(mDirs.get(dir).copy().scale(10));
    }
    

    
}
