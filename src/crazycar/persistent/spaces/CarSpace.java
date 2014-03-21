package crazycar.persistent.spaces;

import com.gigaspaces.annotation.pojo.SpaceClass;
import com.gigaspaces.annotation.pojo.SpaceId;

import crazycar.persistent.Id;
@SpaceClass
public class CarSpace {
	
	private Id id;
	private boolean empty;
	
	public CarSpace(){}
	
    public CarSpace(Id id, boolean empty) {
		this.id = id;
		this.empty = empty;
	}

    @SpaceId(autoGenerate = false)
    public Id getId() {
        return id;
    }
    public void setId(Id id) {
        this.id = id;
    }
	
	public boolean isEmpty(){
		return false;
	}
		
	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	@Override
	public String toString() {
		return "CarSpace[empty=" + empty + "]";
	}
}
