package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

/***
 * JarMetrics objects are stored in the object store, they contain the metrics for the overall jar and are composed with a List of Metric objects,
 * which have metric data of a single class within the jar.
 * @author Darren
 *
 */
@SuppressWarnings("rawtypes")
public class JarMetric {
	private List<Class> classes;
	private List<Metric> metrics = new ArrayList<Metric>();

	private int jarMethodCount = 0;
	private int jarFieldCount = 0;

	public JarMetric(List<Class> classes) {
		this.classes = classes;
		generateMetrics();
	}

	/***
	 * Iterates over each class in a list of Class objects and instantiates a new Metric object for metric data about that class.
	 * Also keeps running totals of the number of methods, and fields in the Jar overall.
	 */
	private void generateMetrics() {
		for (Class cls : classes) {
			Metric m = new Metric(cls.getName(), cls.isInterface(), cls.getFields(), cls.getMethods());

			jarMethodCount += m.getMethods().length;
			jarFieldCount += m.getFields().length;

			metrics.add(m);
		}
	}

	@Override
	public String toString() {
		String str = "\nIndividual Class Metrics: " + metrics + "\nJar Totals: \nclasses: " + classes.size()
				+ "\tfields: " + jarFieldCount + "\tmethods: " + jarMethodCount;

		return str;
	}

	public List<Class> getClasses() {
		return classes;
	}
}
