package crazycar.persistent;

import org.apache.log4j.Logger;
import org.openspaces.core.GigaSpace;

import com.j_spaces.core.client.SQLQuery;

import crazycar.logic.data.Car;
import crazycar.logic.data.Location;
import crazycar.persistent.spaces.CarSpace;
import crazycar.persistent.spaces.LocationSpace;

public class NetworkAccess {
	private static Logger log = Logger.getLogger(NetworkAccess.class);

	private final GigaSpace space = Space.create().getInstance();
	
	public void save(Car c){
		log.debug("save: " + CarSpace.valueOf(c));
		space.write(CarSpace.valueOf(c));
	}
	
	public CarSpace take(Car r){
		return space.takeById(CarSpace.class,new Id(r));
	}
	
	public CarSpace[] takeCar(boolean empty){
		SQLQuery<CarSpace> query = new SQLQuery<CarSpace>(CarSpace.class,"empty =" + empty);
		return space.readMultiple(query);
	}
	
	public void save(Location l){
		log.debug("save: " + LocationSpace.valueOf(l));
		space.write(LocationSpace.valueOf(l));
	}
	
	public LocationSpace take(Location l){
		return space.takeById(LocationSpace.class,new Id(l));
	}
	

}
