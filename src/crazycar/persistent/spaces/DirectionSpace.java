package crazycar.persistent.spaces;

import com.gigaspaces.annotation.pojo.SpaceId;

public class DirectionSpace {
	private Integer id;
	private Integer direction;
	
	public DirectionSpace(){}

	public DirectionSpace(Integer id, Integer direction) {
		this.id = id;
		this.direction = direction;
	}

	@SpaceId
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getDirection() {
		return direction;
	}
	
	public void setDirection(Integer direction) {
		this.direction = direction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DirectionSpace other = (DirectionSpace) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DirectionSpace [id=" + id + ", direction=" + direction + "]";
	}
}
