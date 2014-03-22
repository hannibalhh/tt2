package crazycar.game;

import org.openspaces.core.GigaSpace;

import crazycar.persistent.Space;
import crazycar.persistent.communication.Worker;

public class Game {

	/*Main Idea 
	 * Movement:
	 * - The Movement is executed by Workers (Tasks) by LOOPS:
	 * 		- TAKE a Car-Location-pair from the tuplespace
	 * 		- they move them forward 
	 * - If an movement is succeeded, the new  car-location-pair is written into the tuplespace (from the Workers)	
	 * - to get an accurate picture of the streets, a FIFO behavior for the locations is needed (FiFoMutexe)
	 * 
	 * Display:
	 * - There is exactly monitor program node (this one) 
	 * - goal: get the next locations from the FIFO (take) 
	 * - display in game engine
	 * 
	 * Location-FIFO and MUTEX (used in Worker):
	 * - there is exactly one MutexTuple which saves the following critical path:
	 * 		- write the direction (NORTH/EAST/SOUTH/WEST = means "free", BLOCKED is gone) on the "old" roxel 
	 * 		- write BLOCKED on the next roxel
	 * 		- add +1 to the FIFO ID
	 * 		- add FIFO-car-location-tuple to the space (which is not the same tuple as the general car-location tuple)
	 * */
	
	/* TODO:
	 * In this class:
	 * - Initialise Grid
	 * - Start Workers
	 * - Start Game
	 * - Start GUI
	 */
	
	public static void main(String[] args) {
		final GigaSpace space = Space.create().getInstance();
		

	}

	
	static void initiateWorkers(int amount, GigaSpace space){
		
		for(int i=0; i<amount;i++){
			Worker worker=new Worker();
			space.execute(worker);
			
		}
	}
	
}
