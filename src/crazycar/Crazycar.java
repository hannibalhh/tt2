package crazycar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.google.common.eventbus.EventBus;

import crazycar.gui.CrazycarGUI;
import crazycar.logic.CarService;
import crazycar.logic.SnapshotService;
import crazycar.logic.TrafficLightService;
import crazycar.logic.data.Location;
import crazycar.logic.data.Network;
import crazycar.persistent.NetworkAccess;

public class Crazycar {

	private static final Logger log = Logger.getLogger(Crazycar.class);

	public static final EventBus bus = new EventBus();
	public final CrazycarGUI gui = new CrazycarGUI();
	public final static NetworkAccess networkAccess = new NetworkAccess();
	public final static Location size = Location.valueOf(15, 15);
	public final static int cars = 10;
	public final static int trafficlights = 3;
	public final static Network network = Network
			.createSimple(size.getColumn());

	// bus.post(Network.create(Location.valueOf(45, 15), 2));

	private Crazycar() {
		bus.register(gui);
	}

	public static Crazycar startGUI() {
		return new Crazycar();
	}

	public static void start() {
		networkAccess.cleanup();
		startGUI();
		bus.post(network);
		networkAccess.save(network);
//		carInit();
		threadedCars();
		threadedSnapshot();
		threadedTrafficLights();
		log.debug(networkAccess.snapshot());
	}

	private static void threadedTrafficLights() {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < trafficlights; i += 1) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					TrafficLightService v = new TrafficLightService();
					while(true){
						v.step();
					}
				}
			};
			service.execute(r);
		}
  }

	private static void threadedSnapshot() {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < 1; i += 1) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					SnapshotService v = new SnapshotService();
					while(true){
						v.snapshot();
					}
				}
			};
			service.execute(r);
		}
	}

	private static void threadedCars() {
		ExecutorService service = Executors.newCachedThreadPool();
		for (int i = 0; i < cars; i += 1) {
			Runnable r = new Runnable() {
				@Override
				public void run() {
					CarService c = new CarService();
					while(true){
						c.step();
					}
				}
			};
			service.execute(r);
		}

	}

	public static EventBus bus() {
		return bus;
	}

	public static void main(String[] args) {
		start();
	}
}