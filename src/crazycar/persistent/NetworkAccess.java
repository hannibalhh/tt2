package crazycar.persistent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;

import com.j_spaces.core.client.SQLQuery;

import crazycar.logic.data.Car;
import crazycar.logic.data.Location;
import crazycar.logic.data.Network;
import crazycar.logic.data.Roxel;
import crazycar.logic.data.Snapshot;
import crazycar.persistent.spaces.CarSpace;
import crazycar.persistent.spaces.LocationSpace;
import crazycar.persistent.spaces.RoxelSpace;

public class NetworkAccess {
	private static Logger log = Logger.getLogger(NetworkAccess.class);

	private final GigaSpace space = Space.create().getInstance();
	
	public NetworkAccess(){
		log.debug("start");
	}
	
	public void save(Network n){
		for (Roxel r : n.getGrid())
			space.write(RoxelSpace.valueOf(r));
	}
	
	public void write(Roxel r){
		space.write(RoxelSpace.valueOf(r));
	}
	
	public boolean take(Roxel r){
		return space.takeById(RoxelSpace.class,new Id(r)) != null;
	}
	
	private List<RoxelSpace> snapshotSpace(){
		SQLQuery<RoxelSpace> query = new SQLQuery<RoxelSpace>(RoxelSpace.class,"direction.direction = 'blocked'");
		return Arrays.asList(space.readMultiple(query));
	}
	
	public Snapshot snapshot(){
		List<Roxel> l = new ArrayList<Roxel>();
		for(RoxelSpace r : snapshotSpace()){
			l.add(r.toRoxel());
		}
		return Snapshot.valueOf(l);
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
	
}
