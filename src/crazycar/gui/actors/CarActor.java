package crazycar.gui.actors;

import java.awt.image.BufferedImage;

import crazycar.logic.data.Direction;
import crazycar.logic.data.Location;
import crazycar.logic.data.Roxel;
import crazycar.logic.data.TrafficLight;
import ch.aplu.jgamegrid.Actor;

public class CarActor extends Actor{
	
	public CarActor() {
		super(CarBackground.imageOf(Direction.west).getImage());
//		try{
//		BufferedImage b = CarBackground.ugly.getImage();
//		getCurrentImage().setData(b.getData());
//		}
//		catch(Exception e){}
	}
	
	public void changeBackground(Direction d){
		BufferedImage b = CarBackground.imageOf(d).getImage();
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
	
	public void changeRoxel(Roxel r){
		changeBackground(r.getDirection());
		changeLocation(r.getLocation());
	}
}
