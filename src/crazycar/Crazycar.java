package crazycar;

import com.google.common.eventbus.EventBus;

import crazycar.gui.CrazycarGUI;
import crazycar.logic.data.Location;
import crazycar.logic.data.Network;

public class Crazycar {

	public static final EventBus bus = new EventBus();
	private final CrazycarGUI gui = new CrazycarGUI();

	private Crazycar() {
		bus.register(gui);
	}

	public static Crazycar start() {
		return new Crazycar();
	}

	public static EventBus bus() {
		return bus;
	}

	public static void main(String[] args) {
		start();
		bus.post(Network.create(Location.valueOf(45, 15), 2));
	}
}
