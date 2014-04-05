package crazycar.persistent.spaces;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.FifoSupport;

@SpaceClass(fifoSupport = FifoSupport.OPERATION)
public class CarLocationQueueSpace {

	private LocationSpace location;
	private DirectionSpace direction;
	
	public CarLocationQueueSpace(LocationSpace location,
			DirectionSpace direction) {
		this.location = location;
		this.direction = direction;
	}
	
	public LocationSpace getLocation() {
		return location;
	}
	
	public void setLocation(LocationSpace location) {
		this.location = location;
	}
	
	public DirectionSpace getDirection() {
		return direction;
	}
	
	public void setDirection(DirectionSpace direction) {
		this.direction = direction;
	}
	
	@Override
	public String toString() {
		return "CarLocationQueueSpace [location=" + location + ", direction="
				+ direction + "]";
	}
	
	

}
