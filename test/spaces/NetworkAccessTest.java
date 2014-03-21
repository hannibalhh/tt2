package spaces;

import crazycar.logic.data.Car;
import crazycar.persistent.NetworkAccess;

public class NetworkAccessTest {

	public static void main(String[] args) {
		NetworkAccess n = new NetworkAccess();
		n.save(Car.ferrari);
		System.out.println(n.load(Car.ferrari));
	}

}
