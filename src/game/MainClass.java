package game;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.lwjgl.util.Timer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.awt.Font;
import java.io.InputStream;
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;
import java.util.Collections;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainClass extends BasicGameState implements MouseListener
{
    static Vector2f screenSize = new Vector2f(1024, 768);
    static int borderWidth = 3;
    static int GUIWidth = 271;
    static int pathHeight = 15;
    float doneCounter = 0f;
    
    static Vector2f gameSize = new Vector2f(screenSize.x-(borderWidth * 3 + GUIWidth) , screenSize.y - (borderWidth * 3 + pathHeight));
    static Vector2f center = new Vector2f(649, 375);
    
    Player player;
    Image HUD, spotlight, progressBar, ghostDot, exitScreen, heartActive, heartDeactive, progressDot;
    
    //someone is getting fancy now with their hashbrown maps, let's get some McD hashies plz
    
    /*
    
    
    
                               ..,,;<<>>;,.
                       .;!!!!!!!!!!!!!!!;;!!!!!!!!>;;.
                    .;!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!>;
                .;!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!>
               <!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!;
             ;!!!!!!!!!!!!!!!!!!!!!!!!!!'!!!!!!!!!!!!!!!!!!!!.
            <!!!!!!!!!!!!!!!!!!!!!!!!'`  `''!!!!!!!!!!!!!!!!!!!;
           <!!!!!!!!!!!!!!!!!!!!!!!!'  uc.  `''!!!!!!!!!!!!!!!!!!
           !!!!!!!!!!!!!!!!!!!!!''   z$$$$$beu  `'!!!!!!!!!!!!!!!!
          :!!!!!!!!!!!!!!''`   .,=n$$$$$$$$$$$$P==._`'!!!!!!!!!!!!!
          '!!!!!!!!!!!!`   zdP"      "$$$$$$$   ..  `. `!!!!!!!!!!!>
          !!!!!!!!!!!!:  J$F  zd$$$bee$$$$$$  ,$$$$c  ;  !!!!!!!!!!>
        ;!!!!!!!!!!!!!!  Jbu$$$$$$$$$$$memes$$$$$$$$$$L :!!!!!!!!!!'
      ;!!!!!!!!!!!!!!!!  d$$$$$$?????$$$$$$$$$???$$$$$$  !!!!!!!!!!
     .!!!!!!!!!!!!!!!!!  $$$$$F  z+c.`?$$$$$$  ...`?$$$k `!!!!!!!!!
     !!!!!!!!!!!!!!!!!!  d$$$$  `      3$$$$L.-     3$$$ '!!!!!!!!!>
     `!!!!!!!!!!!!!!!!  d$$$$$bec   .ed$$$$$$bc.  .e$$$$ :!!!!!!!!!!
     `!!!!!!!!!!!!!!'  z$$$$$$$$$be$$$$$P" `"?$$b,$$$$$F <!!!!!!!!!!
      !!!!!!!!!!!!!!  J$$$$$$$$$$$$$$$F .e$$e. ?$$$$$$$$  !!!!!!!!!!
      `!!!!!!!!!!!'   $$$$$$$$$$$$$$$$c  `     d$$$$$$$$  `!!!!!!!!!
        `!!!!!!!!!   '$$$$$$$$"  `?$$$$$$$$$$$$$$$P"?$$$b  !!!!!!!'
         `'!!!!!!!;   $$$$$$$"       """"""""""""    "$$P  !!!!!!
           ```!!!!!>  $$$$$$$.      . . ......        $$P  !!!''
             `'!!!!!  3$$$$$$$.       " """"""       d$$  ;!'
                ``''   $$$$$$$$b        -===       .d$$$
                       "$$$$$$$$c                  d$$$"
                 ..zdc  $$$$$$$$$b.               d$$$
              .d$$""?$  4$$$$$$$$$$c.           .d$$$"
           .,d$$$$u     d$$$P"?$$$$$$$$buc,,,ce$$$$$   4.
        .zd$$$$$$$$b.   `?$$$c,.`"?$$$$$$$$$$$$$$P"    `?b.
    .,d$$$$$$$$$$$$$$bu  `?$$$$$bc,. `"????????"        J$$.
.zd$$$$$$$$$$$$$$$$$$$$$b,. `?$$$$$$$$$eeccuzee$$$F   ,d$$$$u
 ?$$$$$$$$$$$$$$$$$$$$$$$$$bu. `?$$$$$$$$$$$$$P"  ,zd$$$$$$$$b.
. ?$$$$$$$$$$$$$$$$$$$$$$$$$$$$b. `""??????"  .ed$$$$$$$$$$$$$$u ..
:. ?$$$$$$$$$$$$$$$$$$$$$$$$$$$$$b.   ::   .d$$$$$$$$$$$$$$$$$$$b`:::...
::.`$$$$$$$$$$$$$$$$$$$$$$$$$F "$$$b      d$$$$$$$$$$$$$$$$$$$$$$ :::::::
:::.`$$$$$$$$$$$$$$$$$$$$$$$" ,  `"$$   :$PF"'"$$$$$$$$$$$$$$$$$E :::::::
:::: `$$$$$$$$$$$$$$$$$$$$P" nM  h. $b.z  .nn. ?$$$$$$$$$$$$$$$$F :::::::
::::: $$$$$$$$$$$$$$$$$$$'  dMMh '$$$$$$  MMMM  ?$$$$$$$$$$$$$$$F :::::::
    
    
                                          ...:::          ....::::::
                                       ..::::::::;;u:  ..:::::::::::::
                                   ..:::::::uod$$$$P":::::::::::::::`
                                .::::::;uc$$$$$$P':::::::::::::::'`
                             .:::::;ud$$$$$$P"::::;<%c<u;J%>?%i;   .
                           .::::uc$$$$$$P":::;<C%>'`     "?>",cd2$$$$$c.
                         ::::;c$$$$P""::::<C%>"`            "$$i2:$$$$$$,
                         `:<d$$P"":::;i>""`"`   ,cF??c,     z$$$$h`$$$$$F
                          .:":::::z>"`    ,,    <F   )>   ,d$$$$$$$`?$P"
                        .:::::ui>`      u$F"?K   `""` ,od$$$$$$$$$$$`%:
                     .:::::;i%!<o       ?F   ? ,obee?R$$$$ ."$$$$$$$;>:
                   .::::::J%<C;C>        `"-",d3$P",e$$$$",$$$$$$$$$.
                 .::::::::C>C>",cd$eiu:<uurd$$$$d$$$P""   d$$$$$$$F  `-.
                '::::''`  :?:;3$$$$$$$$$$$F`"""""`  =%   d$$$$$$$"   ,uuu`
                        x$$$$$$$$$$$$$$$$$cdbc,.        .$$$$$P"    4$$$F
  ,;uu:       ,$$$      d$$$$$$$$$$$$$$$$$$$$$$$$bc,./-)$$$$P,d$    3$$$F
  `?$$$$$eeuuu$$$b      `R$$$$$$$"?$$$$$$$$$$$$$$$$$$$$$$P" ?$$"    ;$$$b
      "7$$$$$$$$$$$_      `"???",;%i:""???????""""7?""   ,;iiii:e$$$$$$$$K
 oe$eee$$$$$$$$$$$$b `.              .'        ??$$$b:<i!!!!!!!:$$PF"""`
 "?$$$F$$$$$$$$$$$$b   `.          .'   ?$$i     uu ?!!!!!!!!!!
   uuuu$$$$$$$$$$$$F     `.    :ueei     `?$$ee$$$$$% `'!!!!>>"
 :$$$$$$$$$$$$$$$$P        $$i' "?$$$b.    ?$$$PF"         ;i!         ,uu
          `""":"""        .$$$,   "$$$$;                   !!!i  <ueed$$??
             `.           3$$$F     ?$$$h                  !i!!!i;?$$$$FM$
               `.       ,d$$$$'      $$$$          ,ued$$$.!!!!!!!:R$$$P%:
                 `.;uuod$$$$$"      .$$$$     ,oe$$$$$$$P !!!!!!!!i
                   `"?$$$$PF       _d$$$!  ob $$$$$$$$$P ;!!!!!!!!!
                          `` ~ ~ ~       '4$$b3$$$PFF""  i!!!!!!!!!i
    
    */
    
    
    HashMap<String, Spawner> mSpawners = new HashMap();
    Timer timer = new Timer();
    LinkedList<Enemy> mEnemies = new LinkedList();
    float mOldTime;
    float spawnTimerMax = 1.0f;
    float spawnTimer = spawnTimerMax;
    
    
    LinkedList<String> mDirections = new LinkedList<String>();
    
    
    TrueTypeFont font;
    private static final int gameID = 2;
    static int curProgress;
    Scene scene;
    
    public MainClass()
    {
        //nice 1337 name kiddos
        
    }
    

    
    @Override
    public int getID() {
        return 2;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
                //System.out.println(center);
        
        scene = new Scene("resources/background.png", "resources/foreground.png", new Vector2f(0f,55f), 0.2f);
        
        player = new Player(new Vector2f(649,375), 100f);
        
        //Something to initalize all le nudes I mean...*coughs*
        HUD = new Image("resources/HUD.png");
        spotlight = new Image("resources/spotlight.png");
        progressBar = new Image("resources/SomethingProgress.png");
        progressDot = new Image("resources/Something progressDot.png");
        ghostDot = new Image("resources/GhostDot.png");
        exitScreen = new Image("resources/ExitScreen.png");
        heartActive = new Image("resources/heart1.png");
        heartDeactive = new Image("resources/heart2.png");
        
        
        mOldTime = timer.getTime();
        
        //Something to initalize all le spawners
        mSpawners.put("topleft", new Spawner(new Vector2f(-1f,-1f).normalise().scale(gameSize.x/2).add(center)));
        mSpawners.put("topmiddle", new Spawner(new Vector2f(0f,-1f).normalise().scale(gameSize.x/2).add(center)));
        mSpawners.put("topright", new Spawner(new Vector2f(1f,-1f).normalise().scale(gameSize.x/2).add(center)));
        mSpawners.put("midleft", new Spawner(new Vector2f(-1f,0f).normalise().scale(gameSize.x/2).add(center)));
        mSpawners.put("midright", new Spawner(new Vector2f(1f,0f).normalise().scale(gameSize.x/2).add(center)));
        mSpawners.put("botleft", new Spawner(new Vector2f(-1f,1f).normalise().scale(gameSize.x/2).add(center)));
        mSpawners.put("botmiddle", new Spawner(new Vector2f(0f,1f).normalise().scale(gameSize.x/2).add(center)));
        mSpawners.put("botright", new Spawner(new Vector2f(1f,1f).normalise().scale(gameSize.x/2).add(center)));
        mDirections.add("topleft");
        mDirections.add("topmiddle");
        mDirections.add("topright");
        mDirections.add("midleft");
        mDirections.add("midright");
        mDirections.add("botleft");
        mDirections.add("botmiddle");
        mDirections.add("botright");
        
        //hitAllSpawners();
        
        //something to initalize my cool free font by Rob Vilareal
        try
        {
            InputStream inputStream = ResourceLoader.getResourceAsStream("resources/MISFITS_.ttf");
             
            Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(24f); // set font size
            font = new TrueTypeFont(awtFont, true);
             
        }
        catch (Exception e)
        {
            
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        //something to stop rendering the ghosts and the player if paused
        scene.render(g);
        
        if (!player.paused)
        {
            g.setBackground(Color.darkGray);
            //g.drawString("Hello World!", 320, 240);
            ListIterator LI = mEnemies.listIterator();
            while(LI.hasNext())
            {
                Enemy e = (Enemy)LI.next();
                e.draw(g);
            }
            player.render(g);
        }
        
        //---------------------------------------Something to render the main GUI
        g.drawImage(spotlight,0,0);
        g.drawImage(HUD, 0, 0);
        g.drawImage(progressBar, 275, 750);
        
        
        
        //renders the progress bar nonsense
        for(int i=1; i <= 9; i++)
        {
            g.drawImage(progressDot, 290+ (i*70), 750);
            for(int j=1; j <= curProgress; j++)
            {
                g.drawImage(ghostDot, 290+ (j*70), 750);
                //System.out.println(curProgress);
            }
        }
        //rendering healthbar nonsense
        for(int e=1; e <=7; e++)
        {

            g.drawImage(heartDeactive.getScaledCopy(1.5f), 135-heartDeactive.getScaledCopy(1.5f).getWidth()/2, 100+e*heartDeactive.getScaledCopy(1.5f).getHeight());
            
            //while(healthcount < player.health)
            for(int j = 1; j <=player.health; j++)
            {
                
                //too tired to do logic pls help fix me
                //seriously pls help
                //i need sleep
                //Austin: "Andrew you're stupid.
                //Andrew: *puts head down in shame* I know. But I'm trying to get better :(
                
                g.drawImage(heartActive.getScaledCopy(1.5f), 135-heartActive.getScaledCopy(1.5f).getWidth()/2, 100+j*heartActive.getScaledCopy(1.5f).getHeight());
            }
        }
        
        //something for rendering statistics
        font.drawString(90, 50, "WAVE   " +wave.wavenum, Color.red);

        //which do you like better?!
        font.drawString(10, 600, "DEFLECTED  " + player.numKills, Color.darkGray);
        font.drawString(10, 600, "DEFLECTED  " + player.numKills, Color.white);
        
        //font.drawString(10, 500, "WAVE COMPLETE" , Color.white);
        
        //something to ask for a confirmation before the user quits the game
        if (player.paused)
        {   
            
            g.drawImage(exitScreen, 400, 200);
            font.drawString(575, 350, "DO YOU GIVE UP?", Color.white);
            font.drawString(615, 400, "Yes or No", Color.white);
            font.drawString(595, 450, "ESC , ENTER , R", Color.white);
            
        }
        
        else if (player.health <= 0)
        {   
            
            g.drawImage(exitScreen, 400, 200);
            font.drawString(575, 350, "DO YOU GIVE UP?", Color.white);
            font.drawString(615, 400, "Yes or No", Color.white);
            font.drawString(595, 450, "ESC , ENTER", Color.white);
            
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        scene.update(gc, i);
        
        if (player.health <= 0)
        {
            sbg.enterState(SomethingStateBased.gameThree); 
            player.paused = true;
        }
        //If something paused
        if (player.paused)
        {
            //escape again quits
            if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
            {
            System.out.println("RIP");
            gc.exit();
            }
        
            //enter goes back to ghost hunting
            else if (gc.getInput().isKeyPressed(Input.KEY_ENTER))
            {
                player.paused = false;
                mOldTime = timer.getTime();
            }
            
            //user resets
            else if (gc.getInput().isKeyPressed(Input.KEY_R))
            {
                restart();
                player.paused = false;
            }
        }
        
        //something updating
        player.update(gc, i, mSpawners);
        
        //something elapsed
        float now = timer.getTime();
        float elapsedTime = now - mOldTime;
        mOldTime = now;
        
        curProgress = wave.progress();
            
        if (!player.paused)
        {
        if(elapsedTime != 0)
        {
            //updating all le ghosties
            ListIterator LI = mEnemies.listIterator();
            while(LI.hasNext())
            {
                Enemy e = (Enemy)LI.next();
                e.update(elapsedTime);
                
                //something if ghost gets dunked on
                if(!e.isAlive)
                {
                    //rip ghosty
                    e.mSpawner.mCurrentEnemy = null;
                    LI.remove();
                }
            }
            
            //updating le ghosties homes
            for(String s: mSpawners.keySet())
            {
                mSpawners.get(s).update(elapsedTime);
            }
        }
        
        //aye lmao hax, kids
        if(gc.getInput().isKeyPressed(Input.KEY_SPACE))
        {
            hitAllSpawners();
        }
        
        spawnTimer -= elapsedTime;
        //System.out.println(spawnTimer);
        
        //Something to spawn le ghosties
        if(spawnTimer < 0)
        {
            if(spawnRandom())
            {
                spawnTimerMax -= 0.0005f;
            }
            if(spawnTimerMax < 1.55f - (0.05f*wave.wavenum))
            {
                spawnTimerMax = 1.55f - (0.05f*wave.wavenum);
            }
            spawnTimer = spawnTimerMax;
        }
        
        timer.tick();
        }
    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {

        super.enter(container, game);
        container.getGraphics().setBackground(Color.green);
        System.out.println("Entering state MainClass");
    }

    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException
    {
        super.leave(container, game);
        System.out.println("Leaving state MainClass");
    }

    
    
    
    public boolean spawnRandom()
    {
        //Something for le ghosties to murder your soul from all different angles
        LinkedList<String> tempList = (LinkedList<String>)mDirections.clone();
        Collections.shuffle(tempList);
        int i = 0;
        while(true)
        {
            if(i >= 8)
            {          
                return false;
            }
            if((mSpawners.get(tempList.get(i)).mCanSpawn))
            {
                mEnemies.add(mSpawners.get(tempList.get(i)).Spawn());
                return true;
            }
            else
            {
                i++;
            }
        }
    }
    
    public void hitAllSpawners()
    {
        //Something for debugging
        //hehe Hoho le haxor 
        //get dunked ghosts you nerds
        mEnemies.add(mSpawners.get("topleft").Spawn());
        mEnemies.add(mSpawners.get("topmiddle").Spawn());
        mEnemies.add(mSpawners.get("topright").Spawn());
        mEnemies.add(mSpawners.get("midleft").Spawn());
        mEnemies.add(mSpawners.get("midright").Spawn());
        mEnemies.add(mSpawners.get("botleft").Spawn());
        mEnemies.add(mSpawners.get("botmiddle").Spawn());
        mEnemies.add(mSpawners.get("botright").Spawn());
    }    
    
    public void restart()
    {
        //Something if the user decides to try again
        player.health = 3;
        player.numKills = 0;
        wave.wavenum = 1;
        mOldTime = timer.getTime();
        
        //add more stuff that I can't think of off...ZZZZzzz...zz..
        
    }



    public static void main(String[] args)
    {
        try 
        {
            AppGameContainer agc = new AppGameContainer(new SomethingStateBased("Something Spooky"));
            agc.setDisplayMode(1024, 768, false);
            agc.start();
        }
        catch (SlickException e)
        {
            e.printStackTrace();
        }

        System.out.println();
    }
}

        
