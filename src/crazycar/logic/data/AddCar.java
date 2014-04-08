package crazycar.logic.data;

public class AddCar {

	private final Roxel roxel;

	private AddCar(Roxel location) {
		this.roxel = location;
	}
	
	public static AddCar valueOf(Roxel location){
		return new AddCar(location);
	}

	public Roxel getRoxel() {
		return roxel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((roxel == null) ? 0 : roxel.hashCode());
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
		AddCar other = (AddCar) obj;
		if (roxel == null) {
			if (other.roxel != null)
				return false;
		} else if (!roxel.equals(other.roxel))
			return false;
		return true;
	}
}
