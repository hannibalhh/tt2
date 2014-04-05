package crazycar.persistent.communication;

import java.util.HashSet;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;

import com.j_spaces.core.client.SQLQuery;

import crazycar.logic.data.*;
import crazycar.persistent.spaces.DirectionSpace;
import crazycar.persistent.spaces.RoxelSpace;
public class CarWorker implements Task<HashSet<Integer>> {

    @TaskGigaSpace
    private transient GigaSpace gigaSpace;
	
	private int movements=0;
	private Location loc;
	private DirectionSpace direction;
	private RoxelSpace roxel;
	
	public CarWorker(Location location){
		loc=location;
		roxel=gigaSpace.take(new SQLQuery<RoxelSpace>(RoxelSpace.class,
				"location=?", location));
		direction=roxel.getDirection();
	}
	

    
	@Override
	public HashSet<Integer> execute() throws Exception {

		
		
		
		return null;
	}

}
