package crazycar.persistent.spaces;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceProperty;

import crazycar.logic.data.Roxel;
import crazycar.persistent.Id;

@SpaceClass
public class RoxelSpace  implements Serializable{

	private static final long serialVersionUID = 6282025321671367311L;
	private Id id;
	private DirectionSpace direction;
	private LocationSpace location;

	public RoxelSpace() {}

	public RoxelSpace(Id id, DirectionSpace direction, LocationSpace location) {
		this.id = id;
		this.direction = direction;
		this.location = location;
	}

	public static RoxelSpace valueOf(Roxel r) {
		return new RoxelSpace(new Id(r), DirectionSpace.valueOf(r
				.getDirection()), LocationSpace.valueOf(r.getLocation()));
	}

	public Roxel toRoxel() {
		return Roxel.valueOf(direction.toDirection(), location.toLocation());
	}

	@SpaceId
	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

    @SpaceProperty
	public DirectionSpace getDirection() {
		return direction;
	}
    
	public void setDirection(DirectionSpace direction) {
		this.direction = direction;
	}

    @SpaceProperty
	public LocationSpace getLocation() {
		return location;
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
		return "RoxelSpace[direction=" + direction + ", location=" + location + "]";
	}
}
