package crazycar.gui;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

import com.google.common.eventbus.Subscribe;

import crazycar.gui.actors.NoRoad;
import crazycar.gui.actors.Crossroads;
import crazycar.gui.actors.RoadNS;
import crazycar.gui.actors.RoadWE;
import crazycar.logic.data.Direction;
import crazycar.logic.data.Network;
import crazycar.logic.data.Roxel;

public class CrazycarGUI extends GameGrid {

	// imagesize of cells in px
	public final static int imagesize = 25;

	private static final long serialVersionUID = 7378323006789259694L;

	public void addActor(Actor a, Location l) {
		// only one actor in one grid
		removeActorsAt(l);
		super.addActor(a, l);
	}

	public CrazycarGUI() {
		super(50, 18, imagesize, java.awt.Color.black);
		setTitle("Crazycar");
	}

	@Subscribe
	public void initListener(Network e) {
		System.out.println(e);
		setNbHorzCells(e.getSize().getColumn());
		setNbVertCells(e.getSize().getRow());
		for (Roxel l : e.getGrid()) {
			if (l.getDirection().equals(Direction.south)
					|| l.getDirection().equals(Direction.north))
				addActor(new RoadNS(), new Location(
						l.getLocation().getColumn(), l.getLocation().getRow()));
			else if (l.getDirection().equals(Direction.east)
					|| l.getDirection().equals(Direction.west))
				addActor(new RoadWE(), new Location(
						l.getLocation().getColumn(), l.getLocation().getRow()));
			else
				addActor(new Crossroads(), new Location(l.getLocation()
						.getColumn(), l.getLocation().getRow()));
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