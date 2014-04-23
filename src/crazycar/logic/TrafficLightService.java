package crazycar.logic;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;
import org.openspaces.events.EventDriven;
import org.openspaces.events.EventTemplate;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.polling.Polling;
import org.openspaces.events.notify.NotifyType;

import com.gigaspaces.client.ChangeSet;
import com.j_spaces.core.client.SQLQuery;
import com.sun.xml.internal.bind.v2.model.core.ID;


import crazycar.Crazycar;
import crazycar.logic.data.Car;
import crazycar.logic.data.Direction;
import crazycar.logic.data.Roxel;
import crazycar.logic.data.TrafficLight;
import crazycar.logic.data.TrafficLight.Color;
import crazycar.persistent.NetworkAccess;
import crazycar.persistent.spaces.CarSpace;
import crazycar.persistent.spaces.RoxelSpace;
import crazycar.persistent.spaces.TrafficLightSpace;

@EventDriven 
@Polling
@NotifyType(write = true, update = true)
public class TrafficLightService {

/**
 * Timestamp Message -> alle 20ms
 */
	
	private static final Logger log = Logger.getLogger(TrafficLightService.class);
	private Roxel roxel = Crazycar.networkAccess.takeRandomCrossingRoxel();
	
	// Todo:id;
	private TrafficLight light =new TrafficLight(roxel.getLocation());
	private Date lastChange=Calendar.getInstance().getTime();
	

	@EventTemplate
	SQLQuery<RoxelSpace> unProcessedData(){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,
				"location.row = "+roxel.getLocation().getRow() + "and location.column = "+roxel.getLocation().getRow());
		return query;
	}
	
	
	/* 1) Check if car is on the crossroad --> direction red/green
	 * 2) If Car is one before crossroad --> direction red/green
	 * 3) optional: Test size of queue 
	 * */
	
	@SpaceDataEvent
	public void step(RoxelSpace r, GigaSpace giga){
		
		Roxel tempR=r.toRoxel();
		Direction dir =getDirection(giga);
		if (dir.equals(Direction.south) || dir.equals(Direction.south)){
			this.light.setLightNS(TrafficLight.Color.GREEN);
			this.light.setLightEW(TrafficLight.Color.RED);
		}
		else if (dir.equals(Direction.east) || dir.equals(Direction.west)){
			this.light.setLightNS(TrafficLight.Color.RED);
			this.light.setLightEW(TrafficLight.Color.GREEN);
		}else{
			this.light.setLightNS(TrafficLight.Color.GREEN);
			this.light.setLightEW(TrafficLight.Color.GREEN);
		}
		SQLQuery<TrafficLightSpace> query = new SQLQuery<TrafficLightSpace>(TrafficLightSpace.class,
				"location.row = "+roxel.getLocation().getRow() + "or location.column = "+roxel.getLocation().getRow());
		giga.change(query, new ChangeSet().set("lightEW", (light.getLightEW()==TrafficLight.Color.GREEN?"GREEN":"RED")).set( "lightEW", (light.getLightNS()==TrafficLight.Color.GREEN?"GREEN":"RED")));
		sleep(1000);
	
	/*	if (tempR.getLocation().getRow()==(r.getLocation().getRow()) -1 ){
			
		} */
		
	}
	
	TrafficLightSpace getTrafficLight(GigaSpace giga){
		SQLQuery<TrafficLightSpace> query = new SQLQuery<TrafficLightSpace>(TrafficLightSpace.class,
				"location.row = "+roxel.getLocation().getRow() + "or location.column = "+roxel.getLocation().getRow());
		return giga.read(query);
	
	}
	
	Direction getDirection(GigaSpace giga){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,
				"car = "+roxel.getLocation().getRow() + "and location.column = "+roxel.getLocation().getRow());
		RoxelSpace rox = giga.read(query);
		return rox.getDirection().toDirection();
	}
	
	
	void sleep(int i){
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
