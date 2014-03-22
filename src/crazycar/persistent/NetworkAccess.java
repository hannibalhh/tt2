package crazycar.persistent;

import org.openspaces.core.GigaSpace;

import crazycar.logic.data.Car;
import crazycar.persistent.spaces.CarSpace;

public class NetworkAccess {
	
	private final GigaSpace space = Space.create().getInstance();
	
	public void save(Car r){
		space.write(new CarSpace(new Id(Space.toSHA1(r)), r.isEmpty()));
	}
	
	public CarSpace load(Car r){
		CarSpace cs = space.readById(CarSpace.class,new Id(Space.toSHA1(r)));
		return cs;
	}
	

}
