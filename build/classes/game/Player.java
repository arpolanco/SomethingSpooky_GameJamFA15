package game;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 *
 * @author myMom
 */
public class Player 
{
    
    Vector2f mPos;
    HashMap<String, Vector2f> mDirs = new HashMap();
    float mSpeed;
    private Image[] mFrames;
    private Image[] mLooking;
    private Image mHead;
    private Image mTorch;
    private int mCurrentFrame;
    private float mFrameTimer;
    
    //Statistics
    protected static int numKills = 0;
    protected static int health;
    //if player pauses the game or nah
    boolean paused;
    protected static int healthCount = 0;
    
    public Player(Vector2f pos, float speed)
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
        mSpeed = speed;
        mFrameTimer = 0f;
        health = 3;
        paused = false;
        
        mFrames = new Image[4];
        try {
            mFrames[0] = new Image("resources/walking/f1.png");
            mFrames[1] = new Image("resources/walking/f2.png");
            mFrames[2] = new Image("resources/walking/f3.png");
            mFrames[3] = new Image("resources/walking/f4.png");
        } catch (SlickException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        mLooking = new Image[6];
        try {
            mLooking[0] = new Image("resources/heads/head7.png");
            mLooking[1] = new Image("resources/heads/head9.png");
            mLooking[2] = new Image("resources/heads/head4.png");
            mLooking[3] = new Image("resources/heads/head6.png");
            mLooking[4] = new Image("resources/heads/head1.png");
            mLooking[5] = new Image("resources/heads/head3.png");
            mTorch = new Image("resources/Torch.png");
            //mTorch = mTorch.getScaledCopy(0.9f);
        } catch (SlickException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
    }
    
        mHead = mLooking[3];
    }
    
    public void render(Graphics g)
    {
        //Something to render the homie
        g.setColor(Color.yellow);
        g.drawImage(mFrames[mCurrentFrame], mPos.x - mFrames[mCurrentFrame].getWidth()/2, mPos.y - mFrames[mCurrentFrame].getHeight()/2);
        g.drawImage(mHead, mPos.x - mFrames[mCurrentFrame].getWidth()/2, mPos.y - mFrames[mCurrentFrame].getHeight()/2);
        //g.drawImage(mTorch, mPos.x - mFrames[mCurrentFrame].getWidth()/2 + 2, mPos.y - mFrames[mCurrentFrame].getHeight()/4);
        g.drawImage(mTorch, mPos.x - mFrames[mCurrentFrame].getWidth()/2, mPos.y - mFrames[mCurrentFrame].getHeight()/4);
    }
    
    public void update(GameContainer gc, int dt, HashMap spawners)
    {
        if (mFrameTimer >= mSpeed)
        {
            if (mCurrentFrame == 3)
            {
                mCurrentFrame = 0;
            }
            else
                mCurrentFrame++;
            mFrameTimer = 0f;
        }
        else
        {
            mFrameTimer += dt;
        }
        
        if (spawners != null)
        {
            //Something to juggles all the the users inputs which is pretty nifty
            //A SWITCH CASE WOULD OF BEEN NICER BUT NOOOO...MITCH HAS TO BE A HARDASS
            //<<<<<<<<33333333333333333333333333333333333333333333333333333333333333
            if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD7))
            {
                System.out.println("7");
                    mHead = mLooking[0];
                act("topleft", spawners);
            }
            else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD8))
            {
                System.out.println("8");
                    mHead = mLooking[1];
                act("topmiddle", spawners);
            }
            else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD9))
            {
                System.out.println("9");
                    mHead = mLooking[1];
                act("topright", spawners);
            }
            else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD4))
            {
                System.out.println("4");
                    mHead = mLooking[2];
                act("midleft", spawners);
            }
            else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD6))
            {
                System.out.println("6");
                    mHead = mLooking[3];
                act("midright", spawners);
            }
            else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD1))
            {
                System.out.println("1");
                    mHead = mLooking[4];
                act("botleft", spawners);
            }
            else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD2))
            {
                System.out.println("2");
                    mHead = mLooking[5];
                act("botmiddle", spawners);
            }
            else if (gc.getInput().isKeyPressed(Input.KEY_NUMPAD3))
            {
                System.out.println("3");
                    mHead = mLooking[5];
                act("botright", spawners);
            }

            //if the player wants to leave
            else if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
            {
                System.out.println("esc");
                paused = true;
            }
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
                    healthCount++;
                    if(healthCount >= 25)
                    {
                        if(health < 7)
                        {
                        health++;
                        healthCount=0;
                        }
                    }
                }
                //otherwise...>.> well gg your lifespan kid
                s.mHasAttacked = true;
            }
        }
    }
}
