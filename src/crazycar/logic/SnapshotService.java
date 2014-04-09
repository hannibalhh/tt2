package crazycar.logic;

import org.openspaces.events.EventDriven;
import org.openspaces.events.notify.Notify;

import crazycar.Crazycar;

@EventDriven 
@Notify
public class SnapshotService {
	

	public void snapshot() {	
		Crazycar.bus.post(Crazycar.networkAccess.snapshot());
		sleep(20);
	}


	void sleep(int i){
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
