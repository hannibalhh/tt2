package crazycar.persistent.spaces;
import com.gigaspaces.annotation.pojo.SpaceId;

public class PersonSpace {

    private Integer ssn;
    private String firstName;
    private String lastName;

    // Default constructor (required by XAP)
    public PersonSpace() {}
    
    public PersonSpace(Integer ssn, String firstName, String lastName) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @SpaceId
    public Integer getSsn() {
        return ssn;
    }
    public void setSsn(Integer ssn) {
        this.ssn = ssn;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Person [ssn=" + ssn + ", firstName=" + firstName
				+ ", lastName=" + lastName + "]";
	}
	
	

}