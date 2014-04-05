package crazycar.logic.data;

public class Roxel {

	private final Direction direction;
	private final Location location;

	public Roxel(Direction direction, Location location) {
		this.direction = direction;
		this.location = location;
	}

	public static Roxel valueOf(Direction direction, Location location) {
		return new Roxel(direction, location);
	}

	public Direction getDirection() {
		return direction;
	}

	public Location getLocation() {
		return location;
	}
	
	public Roxel nextRoxel(){
		return Roxel.valueOf(direction, nextLocation());
	}

	//TODO modulo for wall
	public Location nextLocation() {
		if (direction.equals(Direction.north))
			return location.add(Location.valueOf(0, -1));
		else if (direction.equals(Direction.east))
			return location.add(Location.valueOf(1, 0));
		else if (direction.equals(Direction.south))
			return location.add(Location.valueOf(0, 1));
		else if (direction.equals(Direction.west))
			return location.add(Location.valueOf(-1, 0));
		else
			return location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
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
		Roxel other = (Roxel) obj;
		if (direction != other.direction)
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Roxel[direction=" + direction + ", location=" + location + "]";
	}
}
