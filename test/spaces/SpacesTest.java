package spaces;

import java.util.Arrays;

import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;

import com.j_spaces.core.client.SQLQuery;

import crazycar.persistent.spaces.PersonSpace;

public class SpacesTest {
	
	UrlSpaceConfigurer configurer = new UrlSpaceConfigurer("jini://*/*/myGrid");
	GigaSpace gigaSpace = new GigaSpaceConfigurer(configurer).create();
	
	public SpacesTest(){
		System.out.println("Write (store) a couple of entries in the data grid:");
		gigaSpace.write(new PersonSpace(1, "Vincent", "Chase"));
		gigaSpace.write(new PersonSpace(2, "Johnny", "Drama"));

		System.out.println("Read (retrieve) an entry from the grid by its id:");
		PersonSpace result1 = gigaSpace.readById(PersonSpace.class, 1);
		System.out.println("result1: " +  result1);

		System.out.println("Read an entry from the grid using a SQL-like query:");
		PersonSpace result2 = gigaSpace.read(new SQLQuery<PersonSpace>(PersonSpace.class, "firstName=?", "Johnny"));
		System.out.println("result2: " +  result2);

		System.out.println("Read all entries of type Person from the grid:");
		PersonSpace[] results = gigaSpace.readMultiple(new PersonSpace());
		System.out.println("results: " +  Arrays.toString(results));

	}

	public static void main(String[] args) {
		new SpacesTest();
	}

}
