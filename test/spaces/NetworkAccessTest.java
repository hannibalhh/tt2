package spaces;

import java.util.Arrays;

import org.apache.log4j.Logger;

import crazycar.logic.data.Car;
import crazycar.logic.data.Direction;
import crazycar.logic.data.Location;
import crazycar.logic.data.Roxel;
import crazycar.persistent.NetworkAccess;

public class NetworkAccessTest {
	
	public static Logger log = Logger.getLogger(NetworkAccessTest.class);

	public static void main(String[] args) {
		NetworkAccess n = new NetworkAccess();
		n.save(Car.ferrari);
		n.save(Car.empty);
		log.debug(Arrays.toString(n.takeCar(true)));

		n.save(Location.valueOf(3, 4));
		log.debug(n.take(Location.valueOf(4, 4)));
		log.debug(n.take(Location.valueOf(3, 4)));
		
		n.write(Roxel.empty(Direction.east,Location.valueOf(3, 4)));
		
		log.debug(n.take(Roxel.empty(Direction.east,Location.valueOf(3,4))));
		log.debug(n.take(Roxel.empty(Direction.east,Location.valueOf(3,4))));
		log.debug(n.take(Roxel.empty(Direction.east,Location.valueOf(3,4))));
	}

}
