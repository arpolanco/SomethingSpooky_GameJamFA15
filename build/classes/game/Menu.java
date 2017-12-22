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
Image title, infoWindow;
Image[] buttonImages;
float fontSize, colorTint;
float fontMin, fontMax;
float time;
boolean fontGrowing, colorDarkening;
boolean showInstructions;
int instructionPage;
TrueTypeFont font;
Player player;


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
        infoWindow = new Image("resources/infowindow.png");
        
        instructionPage = 0;
        time = 0f;
        fontMin = 1f;
        fontMax = 1.3f;
        fontSize = fontMin;
        fontGrowing = true;
        colorDarkening = true;
        colorTint = 0f;
        
        try
        {
            InputStream inputStream = ResourceLoader.getResourceAsStream("resources/MISFITS_.ttf");
             
            java.awt.Font awtFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, inputStream);
            awtFont = awtFont.deriveFont(32f); // set font size
            font = new TrueTypeFont(awtFont, true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        buttonImages = new Image[8];
        buttonImages[0] = new Image("resources/buttons/7.png");
        buttonImages[1] = new Image("resources/buttons/8.png");
        buttonImages[2] = new Image("resources/buttons/9.png");
        buttonImages[3] = new Image("resources/buttons/4.png");
        buttonImages[4] = new Image("resources/buttons/6.png");
        buttonImages[5] = new Image("resources/buttons/1.png");
        buttonImages[6] = new Image("resources/buttons/2.png");
        buttonImages[7] = new Image("resources/buttons/3.png");
        
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
        
        if (showInstructions)
        {
            g.drawImage(infoWindow, 256, 192);
            if (instructionPage == 1)
            {
                font.drawString(288, 224, "When a ghost is ");
                font.drawString(288 + font.getWidth("When a ghost is "), 224, "exposed", Color.red);
                font.drawString(288 + font.getWidth("When a ghost is exposed"), 224, ", press the");
                font.drawString(288, 256, "numpad keys to look in that direction");
                font.drawString(288, 288, "and ward them off.");

                g.drawImage(buttonImages[0], 416, 352);
                g.drawImage(buttonImages[1], 480, 352);
                g.drawImage(buttonImages[2], 544, 352);
                g.drawImage(buttonImages[3], 416, 416);
                g.drawImage(buttonImages[4], 544, 416);
                g.drawImage(buttonImages[5], 416, 480);
                g.drawImage(buttonImages[6], 480, 480);
                g.drawImage(buttonImages[7], 544, 480);

                font.drawString(656, 532, "Next: I");
            }
            else if (instructionPage == 2)
            {
                font.drawString(320, 224, "If you look while a ghost isn't");
                font.drawString(320, 256, "vulnerable, you miss your chance");
                font.drawString(320, 288, "and can't defend yourself, so");
                font.drawString(320, 320, "don't get hasty and make sure the");
                font.drawString(320, 356, "ghost is ");
                font.drawString(320+ font.getWidth("ghost is "), 352, "exposed!", Color.red);
                
                font.drawString(592, 532, "Play: Enter");
            }
        }
        else
        {
            String displayText = "Press I for instructions or Enter to turn out the lights";
            font.drawString(512 - font.getWidth(displayText)/2, 480, displayText, Color.red.darker(colorTint));
        }
    }
    
    
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int dt) throws SlickException
    {
        background.update(gc, dt);
        if (gc.getInput().isKeyPressed(Input.KEY_ENTER))
        {
            if (instructionPage == 0 || instructionPage == 2)
                sbg.enterState(SomethingStateBased.gameTwo);
        }
        
        if (gc.getInput().isKeyPressed(Input.KEY_I))
        {
            if (instructionPage == 0)
            {
                instructionPage = 1;
                showInstructions = true;
            }
            else if (instructionPage == 1)
            {
                instructionPage = 2;
            }
        }
        
        if (colorDarkening)
        {
            if (colorTint < 0.5f)
            {
                colorTint += 0.001 * dt;
            }
            else
                colorDarkening = false;
        }
        else
        {
            if (colorTint > 0f)
            {
                colorTint -= 0.001 * dt;
            }
            else
                colorDarkening = true;
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

