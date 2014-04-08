package crazycar.gui.actors;

import ch.aplu.jgamegrid.Actor;
import crazycar.gui.CrazycarGUI;

public class CarEastActor extends Actor {
	public CarEastActor() {
		super(CrazycarGUI.imagefolder() + "/car-east.gif");
		
	}
	
//	  private static BufferedImage[] createImages(Actor actor)
//	  {
//	    BufferedImage[] bis = new BufferedImage[nb];
//	    for (int i = 0; i < nb; i++)
//	    {
//	      double factor = 0.1 * (i + 1);
//	      bis[i] = actor.getScaledImage(factor, 10 * i);
//	    }
//	    return bis;
//	  }
}
