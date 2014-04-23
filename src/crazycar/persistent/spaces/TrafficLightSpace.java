package crazycar.persistent.spaces;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceId;
import com.gigaspaces.annotation.pojo.SpaceProperty;

import crazycar.logic.data.Direction;
import crazycar.logic.data.Location;
import crazycar.logic.data.TrafficLight;
import crazycar.persistent.Id;

public class TrafficLightSpace implements Serializable {

	private static final long serialVersionUID = 2046647530408315502L;
	private Id id;
	private LocationSpace location;

	private TrafficLight.Color stateNS = TrafficLight.Color.GREEN;
	private TrafficLight.Color stateEW = TrafficLight.Color.GREEN;

	// true=green, false=red
	// private boolean color=true;

	public TrafficLightSpace() {
	};

	public TrafficLightSpace(Id id, Location location) {
		this.id = id;
		this.location = LocationSpace.valueOf(location);
	}

	public static TrafficLightSpace valueOf(TrafficLight t) {
		return new TrafficLightSpace(new Id(t), t.getLocation());
	}
	
	public TrafficLight toTrafficLight(){
		return TrafficLight.valueOf(location.toLocation(), stateNS, stateEW);
	}

	@SpaceId(autoGenerate = false)
	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	@SpaceProperty
	public LocationSpace getLocation() {
		return location;
	}

	public void setLocation(LocationSpace loc) {
		this.location = loc;
	}

	@SpaceProperty
	public String getLightNS() {
		return (stateNS.equals(TrafficLight.Color.GREEN) ? " GREEN " : " RED ");
	}

	public void setLightNS(String str) {

		if (str.equals("RED")) {
			this.stateNS = TrafficLight.Color.RED;
		} else
			this.stateNS = TrafficLight.Color.GREEN;
	}

	@SpaceProperty
	public String getLightEW() {
		return (stateEW.equals(TrafficLight.Color.GREEN) ? "GREEN" : "RED");
	}

	public void setLightEW(String str) {
		if (str.equals("RED")) {
			this.stateEW = TrafficLight.Color.RED;
		} else
			this.stateEW = TrafficLight.Color.GREEN;
	}

	@Override
	public int hashCode() {
		final int prime = 23;
		int result = 1;
		result = prime * result + location.hashCode();
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
		TrafficLightSpace other = (TrafficLightSpace) obj;

		if (!(other.location.equals(location)))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TrafficLightSpace[lightNS=" + (stateNS.equals(TrafficLight.Color.GREEN) ? " GREEN " : " RED ") + ", lightEW= "
		    + (stateEW.equals(TrafficLight.Color.GREEN) ? " GREEN " : " RED ") + location.toString() + "]";
	}

}
