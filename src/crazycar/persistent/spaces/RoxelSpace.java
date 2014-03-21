package crazycar.persistent.spaces;

import com.gigaspaces.annotation.pojo.SpaceId;

public class RoxelSpace {

	private Integer id;
	private DirectionSpace direction;
	private LocationSpace location;
		
	public RoxelSpace() {}
	
	public RoxelSpace(DirectionSpace direction, LocationSpace location) {
		this.direction = direction;
		this.location = location;
	}

	@SpaceId
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DirectionSpace getDirection() {
		return direction;
	}

	public LocationSpace getLocation() {
		return location;
	}

	public void setDirection(DirectionSpace direction) {
		this.direction = direction;
	}

	public void setLocation(LocationSpace location) {
		this.location = location;
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
		RoxelSpace other = (RoxelSpace) obj;
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