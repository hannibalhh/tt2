package crazycar.logic;

import org.apache.log4j.Logger;
import org.openspaces.events.EventDriven;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;
import org.openspaces.events.polling.Polling;

import crazycar.Crazycar;
import crazycar.logic.data.AddCar;
import crazycar.logic.data.Direction;
import crazycar.logic.data.Location;
import crazycar.logic.data.Roxel;

@EventDriven 
@Notify
public class CarService {
	
	private static final Logger log = Logger.getLogger(Crazycar.class);

	private Roxel roxel = initRoxel();
	
	public static Roxel initRoxel(){
		Location location = Location.random(Crazycar.size);
		Direction direction = location.decideDirection(); 	
		return Roxel.valueOf(direction, location);
	}

	// init mit einer random location
	public CarService(){
		//TODO direction not allowed to be blocked
		Crazycar.networkAccess.take(roxel);
		Crazycar.bus.post(AddCar.valueOf(roxel));
		log.debug("init " + roxel);
	}
	
	@SpaceDataEvent
	void step(){
		Roxel r = roxel.nextRoxel();
		log.debug("next roxel " + r);
		Crazycar.networkAccess.take(r);
		Crazycar.networkAccess.write(roxel);
		roxel = r;
	}

	// step 
	// drive to next location	
	/*  TODO: 
	 * - Grab free next Area  
	 * - Grab exclusive new-writing Tuple 
	 * - write old location <NORTH/EAST/SOUTH/WEST/TODECIDE> (as before, every roxel hast ONE direction)
	 * - write new location BLOCKED
	 * - write new Location in FIFO
	 * - release exclusive new-writing Tuple  
	 */
}
