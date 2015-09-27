package game;
import java.io.InputStream;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.*;
import org.newdawn.slick.util.ResourceLoader;

public class Menu extends BasicGameState{

Scene background;
//Image play;
Image title;
float fontSize;
float fontMin, fontMax;
float time;
boolean fontGrowing;
TrueTypeFont font;


private static final int gameID = 1;

    public Menu()
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
        fontMin = 0.5f;
        fontMax = 1f;
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
        
        //something to initalize my cool free font by Rob Vilareal
    }


    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException 
    {
        background.render(g);
        //g.drawImage(exit, 400, 200);
        Image temp = title.getScaledCopy(fontSize);
        System.out.println();
        Vector2f dir = new Vector2f(-title.getWidth(), -title.getHeight()).normalise();
        Vector2f pos = new Vector2f(512, 150).add(dir.copy().scale(fontSize));
        g.drawImage(temp, pos.x, pos.y);
    }
    
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
        background.update(gc, i);
        if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
        {
            System.out.println("RIP");
            sbg.enterState(SomethingStateBased.gameTwo);
        }

        if (fontGrowing)
        {
            if (fontSize < fontMax)
            {
                fontSize += 0.005;
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
                fontSize -= 0.005;
            }
            else
            {
                fontGrowing = true;
            }
        }
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

