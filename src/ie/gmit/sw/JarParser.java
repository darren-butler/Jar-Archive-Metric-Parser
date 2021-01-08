package ie.gmit.sw;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

/**
 * JarParser abstracts and encapsulates all functionality relating to parsing a jar archive.
 * It is intended that a JarMetricFactory (singleton) instantiate a JarParser and call parse() and pass it a jar.
 * parse then returns a list of all classes inside this jar.
 * @author Darren
 *
 */
public class JarParser {
	private String jarPathName;

	public JarParser(String jarPathName) {
		this.jarPathName = jarPathName;
	}
	
	/**
	 * Takes a jar qualified file name as a string and returns a list containing all the classes in that jar.
	 * There is work do be done on this method in terms of exception handling. Currently if a ClassNotFoundException is thrown,
	 * the string name of the class being parsed will be printed to console and the parser will move on.
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	public List<Class> parse() throws FileNotFoundException, IOException {
		File file = new File(jarPathName);
		JarInputStream in = new JarInputStream(new FileInputStream(file));
		List<Class> classes = new ArrayList<>();

		JarEntry next = in.getNextJarEntry();
		while (next != null) {
			if (next.getName().endsWith(".class")) {
				String name = next.getName().replaceAll("/", "\\.");
				name = name.replaceAll(".class", "");
				if (!name.contains("$")) {
					name.substring(0, name.length() - ".class".length());

					try {
						classes.add(Class.forName(name));
					} catch (ClassNotFoundException | NoClassDefFoundError e) {
						System.out.println("class not found: " + name);
					}
				}
			}

			next = in.getNextJarEntry();
		}

		in.close();
		return classes;
	}
}
