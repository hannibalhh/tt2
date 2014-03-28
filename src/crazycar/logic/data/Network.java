package crazycar.logic.data;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Network {

	private static Logger log = Logger.getLogger(Network.class);
	private static final Location root = Location.valueOf(0, 0);

	private final List<Roxel> grid;
	private final Location size;

	public Network(List<Roxel> grid, Location size) {
		this.grid = grid;
		this.size = size;
	}

	public static final Network createSimple(int size) {
		return createSimple(size, root);
	}

	public static final Network createSimple(int size, Location start) {
		if (size % 3 != 0)
			throw new UnsupportedOperationException(
					"size have to be a multiple of 3");
		List<Roxel> grid = new ArrayList<Roxel>();
		for (int column = 0; column < size; column += 1) {
			for (int row = 0; row < size; row += 1) {
				if (row % 2 != 0 || column % 2 != 0)
					// not both are even numbers than there must be a street
					// row == coolumn => its a crossroads
					grid.add(Roxel.valueOf(Direction.nodecide, Location
							.valueOf(column, row).add(start)));
			}
		}
		return new Network(grid, Location.valueOf(size, size).add(start));
	}
	
	public static final Network create(Location size) {
		return create(size, 1, root);
	}

	public static final Network create(Location size, int crossroadsDistance) {
		return create(size, crossroadsDistance, root);
	}

	public static final Network create(Location size, int crossroadsDistance,
			Location start) {
		List<Roxel> grid = new ArrayList<Roxel>();
		int i = 0;
		int current = streetByCrossroadsDistance(0, crossroadsDistance);
		while (current <= Math.max(size.getColumn(), size.getRow())) {
			grid.add(Roxel.valueOf(Direction.nodecide,
					Location.valueOf(current, current)));
			for (int column = 0; column < size.getColumn(); column += 1) {
				for (int row = 0; row < size.getRow(); row += 1) {
					if (current == column || current == row)
						grid.add(Roxel.valueOf(Direction.nodecide, Location
								.valueOf(column, row).add(start)));
				}
			}
			i += 1;
			current = streetByCrossroadsDistance(i, crossroadsDistance);
		}
		return new Network(grid, size.add(start));
	}

	public static int streetByCrossroadsDistance(int n, int crossroadsDistance) {
		return n * crossroadsDistance + crossroadsDistance + n;
	}

	public Network add(Network n) {
		List<Roxel> g = new ArrayList<Roxel>();
		g.addAll(grid);
		g.addAll(n.getGrid());
		return new Network(g, Location.max(n.getSize(), size));
	}

	public List<Roxel> getGrid() {
		return grid;
	}

	public Location getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "Network[grid=" + grid + ", size=" + size + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grid == null) ? 0 : grid.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Network other = (Network) obj;
		if (grid == null) {
			if (other.grid != null)
				return false;
		} else if (!grid.equals(other.grid))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}

}
