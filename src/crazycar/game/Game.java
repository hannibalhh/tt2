package crazycar.game;

public class Game {

	/*Main Idea 
	 * Movement:
	 * - The Cars are Tasks (CarWorker) and executen LOOPS for forwarding:
	 * 		- get the next roxel
	 * 		- free the old one
	 * - If an movement is succeeded, the roxel is written back into the tuplespace as BLOCKED (from the Workers)	
	 * 
	 * Display:
	 * - There is exactly monitor program node (this one) 
	 * - goal: get the next locations from the FIFO (take) 
	 * - display in game engine
	 * 
	 * Location-FIFO and MUTEX (used in CAR-Task):
     * - to get an accurate picture of the streets, a FIFO behavior for the locations is needed (FiFoMutexe)
	 * - there is exactly one MutexTuple which saves the following critical path (the car already "took" the roxel):
	 * 		- write the direction (NORTH/EAST/SOUTH/WEST = means "free", BLOCKED is gone) on the "old" roxel 
	 * 		- write BLOCKED on the next roxel
	 * 		- add +1 to the FIFO ID
	 * 		- add FIFO-car-location-tuple to the space (which is not the same tuple as the general roxel-state-tuple)
	 * */
	
	/* TODO:
	 * In this class:
	 * - Initialise Grid
	 * - Start Cars
	 * - Start Game
	 * - Start GUI
	 */
	
	/*
	 * Aufgabe 1: Autos fahren auf einem Strassennetz, ausgef�hrt in XAP
	 * 				Vorgabe: Roxels, Autos
	 * 				Autos versuchen vorw�rts zu fahren
	 * 				Kreuzungen
	 * 				GUI zeigt in einem intervallgesteurten Snapshot die Position der Autos
	 * Aufgabe 2: Ampeln + Partitionierung
	 */
	
}
