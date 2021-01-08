package ie.gmit.sw;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import one.microstream.storage.types.EmbeddedStorage;
import one.microstream.storage.types.EmbeddedStorageManager;

/**
 * MetricDB is intended to encapsulate and hide all the underlying implementation of the jar metric database.
 * @author Darren
 *
 */
public class MetricDB {

	private EmbeddedStorageManager db = null;
	private List<JarMetric> root = new ArrayList<>();

	/**
	 * A client may call this method and pass it a String for the fully qualified .jar file name.
	 * E.g. Windows: "C:\Users\User\Desktop\myJar.jar"
	 * @param jarPathName
	 */
	public void load(String jarPathName) {
		db = EmbeddedStorage.start(root, Paths.get("./data"));
		clear();

		root = JarMetricFactory.getInstance().getJarMetric(jarPathName);
		db.storeRoot();

		System.out.println("successfully loaded new jar with " + root.get(0).getClasses().size() + " jar entries...");

		db.shutdown();
	}
	
	/**
	 * If a jar has been loaded into the object store, this method will print a list of metrics to the console.
	 * Metrics include: each class, number of fields, number of methods. Once all the classes in the jar have been printed, metrics about the jar overall will be printed e.g. total classes, total methods, total fields. 
	 */
	public void query() {
		root.stream().forEach(System.out::println);
	}

	/**
	 * Wipes the object store of its data.
	 */
	private void clear() {
		root.clear();
		db.storeRoot();
	}
}
