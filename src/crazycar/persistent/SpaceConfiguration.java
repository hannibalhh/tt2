package crazycar.persistent;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openspaces.admin.Admin;
import org.openspaces.admin.AdminFactory;
import org.openspaces.admin.gsm.GridServiceManager;
import org.openspaces.admin.pu.ProcessingUnit;
import org.openspaces.admin.pu.ProcessingUnitAlreadyDeployedException;
import org.openspaces.admin.pu.ProcessingUnitDeployment;
import org.openspaces.admin.pu.elastic.ElasticStatefulProcessingUnitDeployment;
import org.openspaces.admin.pu.elastic.config.ManualCapacityScaleConfigurer;
import org.openspaces.admin.space.ElasticSpaceDeployment;
import org.openspaces.admin.space.Space;
import org.openspaces.admin.space.SpaceDeployment;
import org.openspaces.core.GigaSpace;
import org.openspaces.core.GigaSpaceConfigurer;
import org.openspaces.core.space.UrlSpaceConfigurer;
import org.openspaces.core.util.MemoryUnit;

import crazycar.logic.CarService;

public class SpaceConfiguration {

	private static final SpaceConfiguration space = new SpaceConfiguration();
	private static final Logger log = Logger.getLogger(SpaceConfiguration.class);

	private final String name = "myGrid";
	private final String standard = "jini://*/*/" + name;
	private final String processingunits = "/./" + name;

	private final UrlSpaceConfigurer configurer = new UrlSpaceConfigurer(processingunits);

	private final GigaSpace instance;

	private SpaceConfiguration() {
		GigaSpaceConfigurer g = new GigaSpaceConfigurer(configurer);
		instance = g.gigaSpace();

		simple();
	}
	
private GigaSpace simple() {
try {
  // Wait for the discovery of the managers and at least one agent
  Admin admin = new AdminFactory().addGroup("gigaspaces-9.7.0-XAPPremium-ga").create();
  admin.getGridServiceAgents().waitForAtLeastOne();
  admin.getElasticServiceManagers().waitForAtLeastOne();
  GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne();
  ProcessingUnit pu = gsm.deploy(new SpaceDeployment(name).partitioned (1, 1));
  File puArchive = new File("target/tt2-1.0.jar");
  System.out.println("puArchive " + puArchive.isFile());
  gsm.deploy(new ProcessingUnitDeployment(puArchive).numberOfInstances(10));
  //pu.waitFor(2, 30, TimeUnit.SECONDS);
  return pu.waitForSpace(5, TimeUnit.MINUTES).getGigaSpace();
} catch (ProcessingUnitAlreadyDeployedException e)  {
  System.err.println ("Error creating \""+name+"\". Space already deployed.");
  return null;
}      
}

	/**
	 * This method deploys a 1,1 tuple space in memory.
	 * 
	 * @param name
	 *          A name for the space.
	 */
	private GigaSpace createTupleSpace() {
		try {
			// Wait for the discovery of the managers and at least one agent
			Admin admin = new AdminFactory().addGroup("myGroup").create();
			admin.getGridServiceAgents().waitForAtLeastOne();
			admin.getElasticServiceManagers().waitForAtLeastOne();
			GridServiceManager gsm = admin.getGridServiceManagers().waitForAtLeastOne();
		
//			ProcessingUnit pu1 = gsm.deploy(new SpaceDeployment(name).partitioned(1, 1));

			File puArchive = new File("./target/tt2-1.0");
			log.debug("puArchive: " + puArchive.isFile());
			ProcessingUnit pu2 = gsm.deploy(new ElasticSpaceDeployment("name").memoryCapacityPerContainer(16, MemoryUnit.MEGABYTES)
			    .maxMemoryCapacity(512, MemoryUnit.MEGABYTES).maxNumberOfCpuCores(32)
			    // uncomment when working with a single machine agent
			    .singleMachineDeployment()
			    // set the initial memory and CPU capacity
			    .scale(new ManualCapacityScaleConfigurer().memoryCapacity(128, MemoryUnit.MEGABYTES).numberOfCpuCores(2).create()));

			// pu.waitFor(2, 30, TimeUnit.SECONDS);
			return pu2.waitForSpace(5, TimeUnit.MINUTES).getGigaSpace();
		} catch (ProcessingUnitAlreadyDeployedException e) {
			System.err.println("Error creating \"" + name + "\". Space already deployed.");
			return null;
		}
	}

	//
	// private void deployTrafficLightPU(){
	// // Wait for the discovery of the managers and at least one agent
	// Admin admin = new AdminFactory().addGroup("myGroup").create();
	// admin.getGridServiceAgents().waitForAtLeastOne();
	// admin.getElasticServiceManagers().waitForAtLeastOne();
	// GridServiceManager gsm =
	// admin.getGridServiceManagers().waitForAtLeastOne();
	//
	// // Deploy the Elastic Processing Unit.
	// // Set the maximum memory and CPU capacity and initial capacity
	// File puArchive = new File("./myPU.jar");
	// ProcessingUnit pu = gsm.deploy(
	// new ElasticStatefulProcessingUnitDeployment(puArchive)
	// .memoryCapacityPerContainer(16,MemoryUnit.MEGABYTES)
	// .maxMemoryCapacity(512,MemoryUnit.MEGABYTES)
	// .maxNumberOfCpuCores(32)
	// // uncomment when working with a single machine agent
	// .singleMachineDeployment()
	// // set the initial memory and CPU capacity
	// .scale(new ManualCapacityScaleConfigurer()
	// .memoryCapacity(128,MemoryUnit.MEGABYTES)
	// .numberOfCpuCores(4)
	// .create())
	// );
	//
	// // Wait until the deployment is complete.
	// pu.waitForSpace().waitFor(pu.getTotalNumberOfInstances());
	// }

	public static SpaceConfiguration create() {
		return space;
	}

	public UrlSpaceConfigurer getConfigurer() {
		return configurer;
	}

	public GigaSpace getInstance() {
		return instance;
	}

}
