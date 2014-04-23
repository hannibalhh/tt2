package crazycar.persistent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;

import com.gigaspaces.client.ChangeResult;
import com.gigaspaces.client.ChangeSet;
import com.j_spaces.core.client.SQLQuery;

import crazycar.logic.data.Car;
import crazycar.logic.data.Direction;
import crazycar.logic.data.Location;
import crazycar.logic.data.Network;
import crazycar.logic.data.Roxel;
import crazycar.logic.data.Snapshot;
import crazycar.logic.data.TrafficLight;
import crazycar.persistent.spaces.CarSpace;
import crazycar.persistent.spaces.DirectionSpace;
import crazycar.persistent.spaces.LocationSpace;
import crazycar.persistent.spaces.RoxelSpace;
import crazycar.persistent.spaces.TrafficLightSpace;

public class NetworkAccess {
	private static Logger log = Logger.getLogger(NetworkAccess.class);

	private final GigaSpace space = Space.create().getInstance();
	
	public NetworkAccess(){
		log.debug("start");
	}
	
	public GigaSpace space(){
		return space;
	}
	
	public void cleanup(){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"");
		space.takeMultiple(query);
		SQLQuery<TrafficLightSpace> query1 = new SQLQuery<TrafficLightSpace>(TrafficLightSpace.class,"");
		space.takeMultiple(query1);
	}
	
	public void save(Network n){
		for (Roxel r : n.getGrid()){
			space.write(RoxelSpace.valueOf(r));			
		}
	}
	
	public void write(Roxel r){
		space.write(RoxelSpace.valueOf(r));
	}
	
	public boolean roxelWithCar(Roxel r){
		SQLQuery<TrafficLightSpace> queryLight=new SQLQuery<TrafficLightSpace>(TrafficLightSpace.class,"location.row =" + r.getLocation().getRow() + " and location.column = " + r.getLocation().getColumn() + " and "+(r.getDirection().equals(Direction.south) ? "lightNS='GREEN'":"lightEW='GREEN'"));
		space.read(queryLight);
		
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"location.row =" + r.getLocation().getRow() + " and location.column = " + r.getLocation().getColumn() + " and car.empty = true");
		ChangeResult<RoxelSpace> cr = space.change(query, new ChangeSet().set("car", CarSpace.valueOf(Car.ferrari)).set("direction", DirectionSpace.valueOf(r.getDirection())));
		return cr.getNumberOfChangedEntries() >= 1;
	}
	
	public Roxel releaseRoxel(Roxel r){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"location.row =" + r.getLocation().getRow() + " and location.column = " + r.getLocation().getColumn());
		space.change(query, new ChangeSet().set("car", CarSpace.valueOf(Car.empty)));
		return r;
	}
	
	public Roxel takeRandomRoxel(){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"car.empty = true and direction.direction != 'nodecide'");
		return space.take(query).toRoxel();
	}
	
	public Roxel takeRandomCrossingRoxel(){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"car.empty = true and direction.direction = 'nodecide'");
		return space.read(query).toRoxel();
	}
	
	public boolean take(Roxel r){
		return space.takeById(RoxelSpace.class,new Id(r)) != null;
	}
	
	public List<RoxelSpace> allRoxels(){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"");
		return Arrays.asList(space.readMultiple(query));
	}
	
	private List<RoxelSpace> snapshotSpace(){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"car.empty = false");
		return Arrays.asList(space.readMultiple(query));
	}
	
	private List<TrafficLightSpace> trafficlightsSpace(){
		SQLQuery<TrafficLightSpace> query = new SQLQuery<TrafficLightSpace>(TrafficLightSpace.class,"");
		return Arrays.asList(space.readMultiple(query));
	}
	
	public Snapshot snapshot(){
		List<Roxel> l = new ArrayList<Roxel>();
		for(RoxelSpace r : snapshotSpace()){
			l.add(r.toRoxel());
		}
		List<TrafficLight> t = new ArrayList<TrafficLight>();
		for(TrafficLightSpace r : trafficlightsSpace()){
			t.add(r.toTrafficLight());
		}
		return Snapshot.valueOf(l,t);
	}
	
	/*
	 * play area
	 */
	
	public void save(Car c){
		space.write(CarSpace.valueOf(c));
	}
	
	public boolean take(Car r){
		return space.takeById(CarSpace.class,new Id(r)) != null;
	}
	
	public CarSpace[] takeCar(boolean empty){
		SQLQuery<CarSpace> query = new SQLQuery<CarSpace>(CarSpace.class,"empty =" + empty);
		return space.readMultiple(query);
	}
	
	public void save(Location l){
		space.write(LocationSpace.valueOf(l));
	}
	
	public boolean take(Location l){
		return space.takeById(LocationSpace.class,new Id(l)) != null;
	}

	public void save(TrafficLight light) {
		space.write(TrafficLightSpace.valueOf(light));	  
  }
	
}
