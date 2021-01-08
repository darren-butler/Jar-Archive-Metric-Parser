package ie.gmit.sw;

import java.util.ArrayList;
import java.util.List;

/***
 * JarMetricFactory is a Singleton factory that returns a list of JarMetric objects.
 * It is an eagerly initialized singleton.
 * @author Darren
 *
 */
public class JarMetricFactory {

	private static JarMetricFactory instance = new JarMetricFactory();

	private JarMetricFactory() {

	}

	public static JarMetricFactory getInstance() {
		return instance;
	}

	/***
	 * Takes a string jarPathName, instantiates a List of JarMetric objects and populates it with a single JarMetric object constructed with all 
	 * of the classes loaded in by the JarParser. In essence, the database stores a List of JarMetric objects, but in reality,
	 * there is only ever one JarMetric object loaded in at one time. This was a last minute retrofit in order to get additional jar metrics (class, field and method totals).
	 * If I had time to refactor this project again this would be first on the list.
	 * @param jarPathName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<JarMetric> getJarMetric(String jarPathName) {
		List<JarMetric> jarMetrics = new ArrayList<>();

		try {
			List<Class> classes = new JarParser(jarPathName).parse();

			JarMetric jm = new JarMetric(classes);
			jarMetrics.add(jm);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jarMetrics;
	}
}