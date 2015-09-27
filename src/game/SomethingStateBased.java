package game;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import game.*;
public class SomethingStateBased extends StateBasedGame {
    public static int gameOne;
    public static int gameTwo;
    public static String mName;
    
    public SomethingStateBased(String name)
    {
       super(name);
    }
    
    @Override
    public void initStatesList(GameContainer container)
    {
    Menu menu = new Menu();
    this.addState(new Menu());
    gameOne = menu.getID();
    MainClass main = new MainClass();
    this.addState(main);
    gameTwo = main.getID();
    }

}
