package spaces;

import java.util.Arrays;

import org.apache.log4j.Logger;

import crazycar.logic.data.Car;
import crazycar.logic.data.Location;
import crazycar.persistent.NetworkAccess;

public class NetworkAccessTest {
	
	public static Logger log = Logger.getLogger(NetworkAccessTest.class);

	public static void main(String[] args) {
		NetworkAccess n = new NetworkAccess();
		n.save(Car.ferrari);
		n.save(Car.empty);
		log.debug(Arrays.toString(n.takeCar(false)));

		log.debug(n.take(Car.ferrari));
		log.debug(n.take(Car.empty));
		n.save(Location.valueOf(3, 4));
		log.debug(n.take(Location.valueOf(4, 4)));
		log.debug(n.take(Location.valueOf(3, 4)));
	}

}
