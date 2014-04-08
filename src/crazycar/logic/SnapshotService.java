package crazycar.logic;

import org.apache.log4j.Logger;
import org.openspaces.events.EventDriven;
import org.openspaces.events.notify.Notify;

import crazycar.Crazycar;

@EventDriven 
@Notify
public class SnapshotService {
	
	private static final Logger log = Logger.getLogger(SnapshotService.class);


	public void snapshot() {	
//		log.debug(Crazycar.networkAccess.allRoxels());
		Crazycar.bus.post(Crazycar.networkAccess.snapshot());
		sleep(1000);
	}


	void sleep(int i){
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
