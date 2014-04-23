package crazycar.logic;

import org.apache.log4j.Logger;
import org.openspaces.events.EventDriven;
import org.openspaces.events.adapter.SpaceDataEvent;
import org.openspaces.events.notify.Notify;

import crazycar.Crazycar;
import crazycar.logic.data.Roxel;

@EventDriven 
@Notify
public class CarService {
	
	private static final Logger log = Logger.getLogger(CarService.class);

	private Roxel roxel = Crazycar.networkAccess.takeRandomRoxel();

	// init mit einer random location
	public CarService(){
		//TODO direction not allowed to be blocked
		Crazycar.networkAccess.write(roxel.toCar());
		log.debug("init " + roxel);
	}
	
	@SpaceDataEvent
	public void step(){
		
		Roxel r = roxel.nextRoxel();
		
		log.debug("next roxel " + r);
		
		log.debug("blocked: " + Crazycar.networkAccess.roxelWithCar(r));
		Crazycar.networkAccess.releaseRoxel(roxel);
		sleep(1000);
		roxel = r;
	}
	
	void sleep(int i){
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
