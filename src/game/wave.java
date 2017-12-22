package game;
import java.util.Random;

public class wave
{
    static int wavenum = 1;
    static int previousWaveKills = 0;
    static int healthCount = 0;
    static boolean done = false;
    
    public static void waveDone()
    {
        wavenum++;
        previousWaveKills = Player.numKills;
        done = true;
    }
    
    public static boolean isDone()
    {
        return done;
    }
       
    public static float whichEnemy()
    {
        //@@@@@@@@@@@@@@@@*****CHANGE WHICHENEMY() TO A FLOAT METHOD****************####@@@@@@@
        
        // I would change these to a weighted floats and if the random gen goes between those nums 
        //then that type of enemy spawns
        //ex. ghost spawns between (0,5f)
        // phantom spawns between (5,8f)
        //spectre spawns between (8f, 9.5f)
        //wraith spawns between (9.5f,10)
        
        
        Random rand = new Random();
        float offSet = 1f;
        
        if (wavenum >=1 && wavenum < 4)
        {
            offSet = 0.3f;
        }
        else if (wavenum >= 4 && wavenum < 6)
        {
            offSet = 0.85f;
        }
        else if (wavenum >=6 && wavenum < 8)
        {
            offSet = 0.97f;
        }
        
        return rand.nextFloat()*offSet;       
    }
    
    public static double enemyCount()
    {
        
        double count = wavenum * 3;
        return count;
    }
    
    public static int progress()
    {
        float curProgress = (float) (((Player.numKills-previousWaveKills) / enemyCount()) * 100f);
        //System.out.println(Player.numKills);
        //System.out.println(((Player.numKills-previousWaveKills) / enemyCount()));
        //System.out.println(curProgress);
        
        if (curProgress == 100)
        {
            waveDone();
        }
        else
        {
            done = false;
        }
        
        int temp = 1;
        
        while(curProgress > 10)
        {
            curProgress-=10;
            temp++;
            System.out.println("previous:" + previousWaveKills);
        }
        
        return temp;
    }
    
    @Override
    public String toString()
    {
        //Returns the 
        String temp = "Wave" +String.valueOf(wavenum);
        return temp;
    }

}

