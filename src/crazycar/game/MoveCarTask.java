package crazycar.game;

import java.util.HashSet;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.executor.Task;
import org.openspaces.core.executor.TaskGigaSpace;

public class MoveCarTask implements Task<HashSet<Integer>> {


    @TaskGigaSpace
    private transient GigaSpace gigaSpace;
    
	@Override
	public HashSet<Integer> execute() throws Exception {
		
		/*  TODO: 
		 * - Grab free next Area  
		 * - Grab exclusive new-writing Tuple 
		 * - write old location <NORTH/EAST/SOUTH/WEST> (as before, every roxel hast ONE direction)
		 * - write new location BLOCKED
		 * - release exclusive new-writing Tuple  
		 */
		return null;
	}

}
