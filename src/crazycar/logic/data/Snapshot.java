package crazycar.logic.data;

import java.util.List;

public class Snapshot {

	private final List<Roxel> roxels;
	
	private final List<TrafficLight> trafficlights;

	private Snapshot(List<Roxel> roxels,List<TrafficLight> trafficlights) {
		this.roxels = roxels;
		this.trafficlights = trafficlights;
	}

	public List<Roxel> getRoxels() {
		return roxels;
	}

	public List<TrafficLight> getTrafficlights() {
		return trafficlights;
	}

	public static Snapshot valueOf(List<Roxel> l, List<TrafficLight> trafficlights) {
		return new Snapshot(l,trafficlights);
	}

	@Override
  public int hashCode() {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((roxels == null) ? 0 : roxels.hashCode());
	  result = prime * result + ((trafficlights == null) ? 0 : trafficlights.hashCode());
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
	  Snapshot other = (Snapshot) obj;
	  if (roxels == null) {
		  if (other.roxels != null)
			  return false;
	  } else if (!roxels.equals(other.roxels))
		  return false;
	  if (trafficlights == null) {
		  if (other.trafficlights != null)
			  return false;
	  } else if (!trafficlights.equals(other.trafficlights))
		  return false;
	  return true;
  }

	@Override
  public String toString() {
	  return "Snapshot [roxels=" + roxels + ", trafficlights=" + trafficlights + "]";
  }
	
	
}
