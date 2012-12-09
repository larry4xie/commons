package my.commons.lang.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import my.commons.Assert;

/**
 * <p>
 * Properties 工具类
 * </p>
 * 
 * @author xiegang
 * @since 2012-11-13
 *
 */
public class PropertyUtils {
	/**
	 * Load properties from the given inputstream.
	 * 
	 * @param in the inputstream to load from
	 * @return the populated Properties instance
	 * @throws IOException if loading failed
	 */
	public static Properties loadProperties(InputStream... in) throws IOException {
		Properties props = new Properties();
		fillProperties(props, in);
		return props;
	}
	
	/**
	 * Load properties from the given url.
	 * 
	 * @param url the url to load from
	 * @return the populated Properties instance
	 * @throws IOException if loading failed
	 */
	public static Properties loadProperties(URL... url) throws IOException {
		Properties props = new Properties();
		fillProperties(props, url);
		return props;
	}
	
	/**
	 * Load properties from the given uri.
	 * 
	 * @param uri the uri to load from
	 * @return the populated Properties instance
	 * @throws IOException if loading failed
	 */
	public static Properties loadProperties(URI... uri) throws IOException {
		Properties props = new Properties();
		fillProperties(props, uri);
		return props;
	}
	
	/**
	 * Load properties from the given file.
	 * 
	 * @param file the file to load from
	 * @return the populated Properties instance
	 * @throws IOException if loading failed
	 */
	public static Properties loadProperties(File... file) throws IOException {
		Properties props = new Properties();
		fillProperties(props, file);
		return props;
	}
	
	/**
	 * Load properties from default ClassLoader.getResources()
	 * 
	 * @param resourceName resourceName in ClassLoader to load
	 * @return
	 * @throws IOException if loading failed
	 */
	public static Properties loadResourcesProperties(String resourceName) throws IOException {
		return loadResourcesProperties(resourceName, PropertyUtils.class.getClassLoader());
	}
	
	/**
	 * Load properties from default ClassLoader.getResource()
	 * 
	 * @param resourceName resourceName in ClassLoader to load
	 * @return
	 * @throws IOException if loading failed
	 */
	public static Properties loadResourceProperties(String resourceName) throws IOException {
		return loadResourceProperties(resourceName, PropertyUtils.class.getClassLoader());
	}
	
	/**
	 * Load properties from ClassLoader.getResources()
	 * 
	 * @param resourceName resourceName in ClassLoader to load
	 * @param classLoader
	 * @return
	 * @throws IOException if loading failed
	 */
	public static Properties loadResourcesProperties(String resourceName, ClassLoader classLoader) throws IOException {
		Properties props = new Properties();
		Enumeration<URL> urls = classLoader.getResources(resourceName);
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			fillProperties(props, url);
		}
		return props;
	}
	
	/**
	 * Load properties from ClassLoader.getResource()
	 * 
	 * @param resourceName resourceName in ClassLoader to load
	 * @param classLoader
	 * @return
	 * @throws IOException if loading failed
	 */
	public static Properties loadResourceProperties(String resourceName, ClassLoader classLoader) throws IOException {
		Properties props = new Properties();
		URL url = classLoader.getResource(resourceName);
		fillProperties(props, url);
		return props;
	}
	
	/**
	 * Fill the given properties from the given resource.
	 * @param props the Properties instance to fill
	 * @param in the inputstream to load from
	 * @throws IOException if loading failed
	 */
	public static void fillProperties(Properties props, InputStream... in) throws IOException {
		Assert.notNull(in, "in must not be null");
		for(InputStream is : in) {
			props.load(is);
		}
	}
	
	/**
	 * Fill the given properties from the given url.
	 * @param props the Properties instance to fill
	 * @param url the url to load from
	 * @throws IOException if loading failed
	 */
	public static void fillProperties(Properties props, URL... url) throws IOException {
		Assert.notNull(url, "url must not be null");
		for(URL u : url) {
			InputStream in = null;
			try {
				in = u.openStream();
				fillProperties(props, in);
			} finally {
				if (in != null) { in.close(); }
			}
		}
	}
	
	/**
	 * Fill the given properties from the given uri.
	 * @param props the Properties instance to fill
	 * @param uri the uri to load from
	 * @throws IOException if loading failed
	 */
	public static void fillProperties(Properties props, URI... uri) throws IOException {
		Assert.notNull(uri, "uri must not be null");
		for (URI u : uri) {
			fillProperties(props, u.toURL());
		}
	}
	
	/**
	 * Fill the given properties from the given file.
	 * @param props the Properties instance to fill
	 * @param file the file to load from
	 * @throws IOException if loading failed
	 */
	public static void fillProperties(Properties props, File... file) throws IOException {
		Assert.notNull(file, "file must not be null");
		for(File f : file) {
			InputStream in = null;
			try {
				in = new FileInputStream(f);
				fillProperties(props, in);
			} finally {
				if (in != null) { in.close(); }
			}
		}
	}
}
