package crazycar.persistent.spaces;

import java.io.Serializable;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

import crazycar.logic.data.Location;
import crazycar.persistent.Id;

@SpaceClass
public class LocationSpace  implements Serializable{

	private static final long serialVersionUID = 2526097261187890810L;
	private Id id;
	private int column;
	private int row;
	
	public LocationSpace(){}
	
	public LocationSpace(Id id,int column, int row) {
		this.id = id;
		this.column = column;
		this.row = row;
	}
	
    public static LocationSpace valueOf(Location l){
    	return new LocationSpace(new Id(l),l.getColumn(),l.getRow());
    }
    
    public Location toLocation(){
    	return Location.valueOf(column, row);
    }
	
    @SpaceId(autoGenerate = false)
	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public int getColumn() {
		return column;
	}

	public int getRow() {
		return row;
	}

	public void setX(int x) {
		this.column = x;
	}

	public void setY(int y) {
		this.row = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
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
		LocationSpace other = (LocationSpace) obj;
		if (column != other.column)
			return false;
		if (row != other.row)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[x=" + column + ",y=" + row + "]";
	}
}
