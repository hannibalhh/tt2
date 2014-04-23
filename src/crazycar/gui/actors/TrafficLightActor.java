package crazycar.gui.actors;

import java.awt.image.BufferedImage;

import crazycar.logic.data.Direction;
import crazycar.logic.data.Location;
import crazycar.logic.data.TrafficLight;
import ch.aplu.jgamegrid.Actor;

public class TrafficLightActor extends Actor{
	
	public TrafficLightActor() {
		super(TrafficLightBackground.greengreen.getImage());
	}
	
	public void changeBackground(TrafficLight d){
		BufferedImage b = TrafficLightBackground.imageOf(d).getImage();
		getCurrentImage().setAccelerationPriority(b.getAccelerationPriority());
		getCurrentImage().setData(b.getData());
		getCurrentImage().flush();
	}
	
	public void changeLocation(Location d){
		setLocation(convertLocation(d));
	}
	
	public static ch.aplu.jgamegrid.Location convertLocation(Location d){
		return new ch.aplu.jgamegrid.Location(d.getColumn(), d.getRow());
	}

	public void changeChangeTrafficLight(TrafficLight t) {
		changeLocation(t.getLocation());
		changeBackground(t);
  }


}
