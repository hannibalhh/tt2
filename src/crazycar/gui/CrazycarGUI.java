package crazycar.gui;

import ch.aplu.jgamegrid.Actor;
import ch.aplu.jgamegrid.GameGrid;
import ch.aplu.jgamegrid.Location;

import com.google.common.eventbus.Subscribe;

import crazycar.gui.actors.NoStreet;
import crazycar.gui.actors.Street;
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
			addActor(new Street(), new Location(l.getLocation().getColumn(),l.getLocation().getRow()));
		}
		for (Location l : getEmptyLocations()) {
			addActor(new NoStreet(), l);
		}
		show();
		setVisible(true);
	}

	public static String imagefolder() {
		return "images/" + imagesize;
	}

}