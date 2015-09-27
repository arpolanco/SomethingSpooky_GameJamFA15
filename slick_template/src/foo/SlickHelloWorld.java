// Note: I had to use an old version of LWJGL (http://legacy.lwjgl.org/)
// I just added the dll's to the project root (and didn't do the -Djava.library.path thing
//   mentioned in the tutorial.
package foo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.*;

public class SlickHelloWorld extends BasicGame implements MouseListener
{
    public SlickHelloWorld(String title)
    {
        super(title);
        // Put any other application-specific setup code here.
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException 
    { 
        
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException 
    { 
        // Replace this comment with code you wanted to be run periodically
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        g.drawString("Hello World!", 320, 240);
    }
    
    
    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            SlickHelloWorld my_app = new SlickHelloWorld("Testing!");
            appgc = new AppGameContainer(my_app);
            appgc.setDisplayMode(640, 480, false);
            appgc.start();
        }
        catch (SlickException ex)
        {
            Logger.getLogger(SlickHelloWorld.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
