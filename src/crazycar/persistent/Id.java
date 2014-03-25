package crazycar.persistent;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Id implements Serializable{

	private static final long serialVersionUID = 1L;

    private String value;
    
    public Id() {}
	
	public Id(Object value) {
		this.value = toSHA1(value);
//		System.out.println("id: " + this.value + " " + value.getClass());
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Id other = (Id) obj;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return value;
	}
    
	public static String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return new String(md.digest(convertme));
	}
	
	public static String toSHA1(String s){
		return toSHA1(s.getBytes());
	}
	
	public static String toSHA1(Object s){
		return toSHA1(s.toString());
	}

}
