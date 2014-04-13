package spaces;

import crazycar.logic.data.Direction;
import crazycar.logic.data.Location;
import crazycar.logic.data.Roxel;
import crazycar.persistent.spaces.RoxelSpace;

public class IdTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RoxelSpace r = RoxelSpace.valueOf(Roxel.empty(Direction.blocked, Location.valueOf(1, 2)));
		RoxelSpace r2 = RoxelSpace.valueOf(Roxel.empty(Direction.blocked, Location.valueOf(1, 2)));
		System.out.println(r.getId().equals(r2.getId()));
		System.out.println(r.equals(r2));


	}

}
