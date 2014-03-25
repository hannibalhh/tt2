package crazycar.persistent.spaces;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;

import crazycar.persistent.Id;

@SpaceClass
public class TupleMutex  implements Serializable{
	
	private static final long serialVersionUID = -6556781736063862991L;
	private Id id;
	public TupleMutex(){}
	
	public TupleMutex(Id id) {
		this.id = id;
	}

	public Id getId() {
		return id;
	}
	
	public void setId(Id id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "TupleMutex [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		TupleMutex other = (TupleMutex) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}

