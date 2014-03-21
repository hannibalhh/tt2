package crazycar.persistent;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

public class Space {
	
	private static final Space space = new Space();

	private final UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/myGrid");
	private final GigaSpace instance = new GigaSpaceConfigurer(configurer).create();
	
	public static Space create(){
		return space;
	}
	
	public UrlSpaceConfigurer getConfigurer() {
		return configurer;
	}
	
	public GigaSpace getInstance() {
		return instance;
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
