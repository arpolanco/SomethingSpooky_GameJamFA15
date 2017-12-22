package game;
import java.io.InputStream;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

public class Results extends BasicGameState{

Scene background;
//Image play;
Image title;
float fontSize;
float fontMin, fontMax;
float time;
boolean fontGrowing;
TrueTypeFont font;
Player player;


private static final int gameID = 3;

    public Results()
    {
        
    }


    @Override
    public int getID()
    {
        return gameID;
    }

    
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        background = new Scene("resources/menubackground.png", "resources/foreground.png", new Vector2f(0,0), 0.1f);
        title = new Image("resources/title.png");
        time = 0f;
        fontMin = 1f;
        fontMax = 1.3f;
        fontSize = fontMin;
        fontGrowing = true;
        
        try
        {
            InputStream inputStream = ResourceLoader.getResourceAsStream("resources/MISFITS_.ttf");
             
            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(36f); // set font size
            font = new TrueTypeFont(awtFont, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        player = new Player(new Vector2f(512, 320), 180f);
    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        background.render(g);
        //g.drawImage(exit, 400, 200);
        Image temp = title.getScaledCopy(fontSize);
        System.out.println();
        Vector2f pos = new Vector2f(512 - temp.getWidth()/2, 100 - temp.getHeight()/2);
        g.drawImage(temp, pos.x, pos.y);
        player.render(g);
        String tempDisplay = "Total Deflected  "+ Player.numKills;
        String tempDisplay2 = "Final Wave Reached  "+ wave.wavenum;
        String tempDisplay3 = "SSU GAMEJAM Fall 2015";
        String tempDisplay4 = "Andrew Polanco Austin Ferguson Mitch Writght";
        
        font.drawString(512-font.getWidth(tempDisplay)/2, 425,tempDisplay, Color.yellow);
        font.drawString(512-font.getWidth(tempDisplay)/2, 475,tempDisplay2, Color.white);
        font.drawString(465-font.getWidth(tempDisplay)/2, 550,tempDisplay3, Color.blue);
        font.drawString(250, 625,tempDisplay4, Color.darkGray);
    }
    
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int dt) throws SlickException
    {
        background.update(gc, dt);
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            System.out.println("RIP");
            gc.exit();
        }

        if (fontGrowing)
        {
            if (fontSize < fontMax)
            {
                fontSize += 0.0003 * dt;
            }
            else
            {
                fontGrowing = false;
            }
        }
        else
        {
            if (fontSize > fontMin)
            {
                fontSize -= 0.0003 * dt;
            }
            else
            {
                fontGrowing = true;
            }
        }
        player.update(gc, dt, null);
    }
    
    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException
    {
    // TODO Auto-generated method stub
    super.enter(container, game);
    container.getGraphics().setBackground(Color.darkGray);
    System.out.println("Entering Menu");
    }
    @Override
    public void leave(GameContainer container, StateBasedGame game) throws SlickException
    {
    // TODO Auto-generated method stub
    super.leave(container, game);
    System.out.println("Leaving Menu");
    }

    
}