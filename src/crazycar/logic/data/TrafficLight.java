package crazycar.logic.data;

public class TrafficLight {

	
	public enum Color {GREEN, RED};
	
	private final Location location;
	private TrafficLight.Color stateNS=TrafficLight.Color.GREEN;
	private TrafficLight.Color stateEW=TrafficLight.Color.GREEN;
	
	public TrafficLight(Location loc){
		location=loc;
	}
	
	public Color getLightNS(){
		return stateNS;
	}
	
	public Color getLightEW(){
		return stateEW;
	}
	
	public void setLightNS(Color col){
		stateNS=col;
	}
	
	public void setLightEW(Color col){
		stateEW=col;
	}
	
	public Location getLocation(){
		return location;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + location.getColumn();
		result = prime * result + location.getRow();
		result = prime * result + stateNS.ordinal();
		result = prime * result + stateEW.ordinal();
		
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
		TrafficLight other = (TrafficLight) obj;
		if (!(location.equals(other.location)))
			return false;
		else	
			return true;
	}

	@Override
	public String toString() {
		return "[lightNS= "+(stateNS.equals(Color.GREEN)?"GREEN":"RED")+" lightEW= "+(stateEW.equals(Color.GREEN)?"GREEN":"RED")+" @"+location.toString()+"]";
	}
}
