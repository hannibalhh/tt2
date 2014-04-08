package crazycar.persistent;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

public class Space {
	
	private static final Space space = new Space();

	private final UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/myGrid");
	private final GigaSpace instance;
	
	private Space(){
		GigaSpaceConfigurer g = new GigaSpaceConfigurer(configurer);
//		g.defaultReadTimeout(Integer.MAX_VALUE);
//		g.defaultTakeTimeout(Integer.MAX_VALUE);
		instance = g.create();
	}
	
	
	public static Space create(){
		return space;
	}
	
	public UrlSpaceConfigurer getConfigurer() {
		return configurer;
	}
	
	public GigaSpace getInstance() {
		return instance;
	}
	
}
