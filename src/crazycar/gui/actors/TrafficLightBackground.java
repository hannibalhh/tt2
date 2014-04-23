package crazycar.gui.actors;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import crazycar.gui.CrazycarGUI;
import crazycar.logic.data.Direction;
import crazycar.logic.data.TrafficLight;

public enum TrafficLightBackground {
	redgreen("rot-gruen.gif"),greenred("gruen-rot.gif"),greengreen("rot-rot.gif");
	
	private TrafficLightBackground(String url) {	
		this.image = convert(read(CrazycarGUI.imagefolder() + url));
  }

	private final BufferedImage image;	
	
	private static BufferedImage read(String url){
	  try {
	  	System.out.println(new File(url).isFile());
	    return ImageIO.read(new File(url));
    } catch (IOException e) {
	    e.printStackTrace();
    }
	  return null;
	}
	
	public static BufferedImage convert(BufferedImage rawImage){
	   BufferedImage rgbImage = new BufferedImage(rawImage.getWidth(null), rawImage.getHeight(null), 2);
	    Graphics g = rgbImage.createGraphics();
	    // if rawImage contains alpha, consider filling the rgbImage with a default BG color here
	    g.drawImage(rawImage, 0, 0, null);
	    g.dispose();
	    rawImage.flush();
	    return rgbImage;
	}
	
	public BufferedImage getImage() {
		return image;
	}

	public static TrafficLightBackground imageOf(TrafficLight d){
  if (d.getLightNS().equals(TrafficLight.Color.GREEN) && d.getLightEW().equals(TrafficLight.Color.RED)) {
			return redgreen;
		} else if (d.getLightEW().equals(TrafficLight.Color.GREEN) && d.getLightNS().equals(TrafficLight.Color.RED)) {
			return greenred; 
		}
			else if (d.getLightNS().equals(TrafficLight.Color.GREEN) && d.getLightEW().equals(TrafficLight.Color.GREEN)) {
				return greengreen;
			}
		
		throw new UnsupportedOperationException("strange color... " + d);
	}
	
}
