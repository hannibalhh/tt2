package crazycar.persistent;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;

import com.j_spaces.core.client.SQLQuery;

import crazycar.logic.data.Car;
import crazycar.logic.data.Location;
import crazycar.logic.data.Roxel;
import crazycar.persistent.spaces.CarSpace;
import crazycar.persistent.spaces.LocationSpace;
import crazycar.persistent.spaces.RoxelSpace;

public class NetworkAccess {
	private static Logger log = Logger.getLogger(NetworkAccess.class);

	private final GigaSpace space = Space.create().getInstance();
	
	public NetworkAccess(){
		log.debug("start");
	}
	
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
	
	public void save(Roxel r){
		space.write(RoxelSpace.valueOf(r));
	}
	
	public boolean take(Roxel r){
		return space.takeById(RoxelSpace.class,new Id(r)) != null;
	}
}
