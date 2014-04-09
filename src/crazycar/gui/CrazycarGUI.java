package crazycar.gui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

import com.google.common.eventbus.Subscribe;

import crazycar.gui.actors.CarEastActor;
import crazycar.gui.actors.CarNorthActor;
import crazycar.gui.actors.CarSouthActor;
import crazycar.gui.actors.CarWestActor;
import crazycar.gui.actors.NoRoad;
import crazycar.gui.actors.Crossroads;
import crazycar.gui.actors.RoadNS;
import crazycar.gui.actors.RoadWE;
import crazycar.logic.data.Direction;
import crazycar.logic.data.Network;
import crazycar.logic.data.Roxel;
import crazycar.logic.data.Snapshot;

public class CrazycarGUI extends GameGrid {

	private static final Logger log = Logger.getLogger(CrazycarGUI.class);

	// imagesize of cells in px
	public final static int imagesize = 25;
	private List<Actor> cars = new ArrayList<Actor>();
	private List<Actor> tempcars = new ArrayList<Actor>();

	private static final long serialVersionUID = 7378323006789259694L;

	public void addActor(Actor a, Location l) {
		// only one actor in one grid
		removeActorsAt(l);
		super.addActor(a, l);
	}

	public CrazycarGUI() {
		super(50, 18, imagesize, java.awt.Color.black);
		setTitle("Crazycar");
		doRun();
	}

	private Actor newCarAndSave(Roxel r) {
		Actor c = newCar(r);
		tempcars.add(c);
		return c;
	}

	public Actor newCar(Roxel r) {
		if (r.getDirection().equals(Direction.north)) {
			return new CarNorthActor();
		} else if (r.getDirection().equals(Direction.south)) {
			return new CarSouthActor();
		} else if (r.getDirection().equals(Direction.west)) {
			return new CarWestActor();
		} else if (r.getDirection().equals(Direction.east)) {
			return new CarEastActor();
		}
		throw new UnsupportedOperationException("newCar:wrong direction " + r.getDirection());
	}

	@Subscribe
	public void moveListener(Snapshot s) {
		log.debug("snapshot: " + s.getRoxels());
		Iterator<Actor> it = cars.iterator();
		// initialized cars and size not equals
		if (cars.size() == 0 && cars.size() != s.getRoxels().size()) {
			log.error("Snapshot: roxels not quals cars size: cars:" + cars.size() + " roxels:" + s.getRoxels());
		}
		for (Roxel r : s.getRoxels()) {
			if (it.hasNext()) {
				it.next().setLocation(new Location(r.getLocation().getColumn(), r.getLocation().getRow()));
			} else {
				addActor(newCarAndSave(r), new Location(r.getLocation().getColumn(), r.getLocation().getRow()));
			}
		}
		cars.addAll(tempcars);
		tempcars.clear();
	}

	@Subscribe
	public void initListener(Network e) {
		System.out.println(e);
		setNbHorzCells(e.getSize().getColumn());
		setNbVertCells(e.getSize().getRow());
		for (Roxel l : e.getGrid()) {
			if (l.getDirection().equals(Direction.south) || l.getDirection().equals(Direction.north))
				addActor(new RoadNS(), new Location(l.getLocation().getColumn(), l.getLocation().getRow()));
			else if (l.getDirection().equals(Direction.east) || l.getDirection().equals(Direction.west))
				addActor(new RoadWE(), new Location(l.getLocation().getColumn(), l.getLocation().getRow()));
			else
				addActor(new Crossroads(), new Location(l.getLocation().getColumn(), l.getLocation().getRow()));
		}
		for (Location l : getEmptyLocations()) {
			addActor(new NoRoad(), l);
		}
		show();
		setVisible(true);
	}

	public static String imagefolder() {
		return "images/" + imagesize;
	}
}