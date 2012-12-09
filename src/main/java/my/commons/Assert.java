package my.commons;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Assertion utility class that assists in validating arguments.
 * 
 * @author xiegang
 * @since 2012-12-9
 *
 */
public class Assert {
	/**
	 * Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.
	 * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
	 * @param expression a boolean expression
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert a boolean expression, throwing <code>IllegalArgumentException</code>
	 * if the test result is <code>false</code>.
	 * <pre class="code">Assert.isTrue(i &gt; 0);</pre>
	 * @param expression a boolean expression
	 * @throws IllegalArgumentException if expression is <code>false</code>
	 */
	public static void isTrue(boolean expression) {
		isTrue(expression, "[Assertion failed] - this expression must be true");
	}
	
	/**
	 * Assert that an object is <code>null</code> .
	 * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is not <code>null</code>
	 */
	public static void isNull(Object object, String message) {
		if (object != null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is <code>null</code> .
	 * <pre class="code">Assert.isNull(value);</pre>
	 * @param object the object to check
	 * @throws IllegalArgumentException if the object is not <code>null</code>
	 */
	public static void isNull(Object object) {
		isNull(object, "[Assertion failed] - the object argument must be null");
	}
	
	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
	 * @param object the object to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object, String message) {
		if (object == null) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an object is not <code>null</code> .
	 * <pre class="code">Assert.notNull(clazz);</pre>
	 * @param object the object to check
	 * @throws IllegalArgumentException if the object is <code>null</code>
	 */
	public static void notNull(Object object) {
		notNull(object, "[Assertion failed] - this argument is required; it must not be null");
	}
	
	/**
	 * Assert that the given String is not empty; that is,
	 * it must not be <code>null</code> and not the empty String.
	 * <pre class="code">Assert.notEmpty(name, "Name must not be empty");</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 */
	public static void notEmpty(String text, String message) {
		if (StringUtils.isEmpty(text)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * Assert that the given String is not empty; that is,
	 * it must not be <code>null</code> and not the empty String.
	 * <pre class="code">Assert.notEmpty(name);</pre>
	 * @param text the String to check
	 */
	public static void notEmpty(String text) {
		notEmpty(text, "[Assertion failed] - this String argument must have length; it must not be null or empty");
	}
	
	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array, "The array must have elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
	 */
	public static void notEmpty(Object[] array, String message) {
		if (ArrayUtils.isEmpty(array)) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that an array has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(array);</pre>
	 * @param array the array to check
	 * @throws IllegalArgumentException if the object array is <code>null</code> or has no elements
	 */
	public static void notEmpty(Object[] array) {
		notEmpty(array, "[Assertion failed] - this array must not be empty: it must contain at least 1 element");
	}
	
	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
	 */
	public static void notEmpty(Collection<?> collection, String message) {
		if (collection == null || collection.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a collection has elements; that is, it must not be
	 * <code>null</code> and must have at least one element.
	 * <pre class="code">Assert.notEmpty(collection, "Collection must have elements");</pre>
	 * @param collection the collection to check
	 * @throws IllegalArgumentException if the collection is <code>null</code> or has no elements
	 */
	public static void notEmpty(Collection<?> collection) {
		notEmpty(collection, "[Assertion failed] - this collection must not be empty: it must contain at least 1 element");
	}

	/**
	 * Assert that a Map has entries; that is, it must not be <code>null</code>
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map, "Map must have entries");</pre>
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the map is <code>null</code> or has no entries
	 */
	public static void notEmpty(Map<?, ?> map, String message) {
		if (map == null || map.isEmpty()) {
			throw new IllegalArgumentException(message);
		}
	}

	/**
	 * Assert that a Map has entries; that is, it must not be <code>null</code>
	 * and must have at least one entry.
	 * <pre class="code">Assert.notEmpty(map);</pre>
	 * @param map the map to check
	 * @throws IllegalArgumentException if the map is <code>null</code> or has no entries
	 */
	public static void notEmpty(Map<?, ?> map) {
		notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
	}

	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.notBlank(name, "'name' must not be empty");</pre>
	 * @param text the String to check
	 * @param message the exception message to use if the assertion fails
	 */
	public static void notBlank(String text, String message) {
		if (StringUtils.isBlank(text)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * Assert that the given String has valid text content; that is, it must not
	 * be <code>null</code> and must contain at least one non-whitespace character.
	 * <pre class="code">Assert.notBlank(name, "'name' must not be empty");</pre>
	 * @param text the String to check
	 */
	public static void notBlank(String text) {
		notBlank(text, "[Assertion failed] - this String argument must have text; it must not be null, empty, or blank");
	}
	
	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is null or empty!
	 * <pre class="code">Assert.noNullElements(array, "The array must have non-null elements");</pre>
	 * @param array the array to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object array contains a <code>null</code> element
	 */
	public static void noNullElements(Object[] array, String message) {
		if (array != null) {
			for (int i = 0; i < array.length; i++) {
				if (array[i] == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	/**
	 * Assert that an array has no null elements.
	 * Note: Does not complain if the array is null or empty!
	 * <pre class="code">Assert.noNullElements(array);</pre>
	 * @param array the array to check
	 * @throws IllegalArgumentException if the object array contains a <code>null</code> element
	 */
	public static void noNullElements(Object[] array) {
		noNullElements(array, "[Assertion failed] - this array must not contain any null elements");
	}
	
	/**
	 * Assert that an collection has no null elements.
	 * Note: Does not complain if the collection is null or empty!
	 * <pre class="code">Assert.noNullElements(array, "The collection must have non-null elements");</pre>
	 * @param collection the collection to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object collection contains a <code>null</code> element
	 */
	public static void noNullElements(Collection<?> collection, String message) {
		if (collection != null) {
			for (Iterator<?> it = collection.iterator(); it.hasNext();) {
				if (it.next() == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	/**
	 * Assert that an collection has no null elements.
	 * Note: Does not complain if the collection is null or empty!
	 * <pre class="code">Assert.noNullElements(collection);</pre>
	 * @param collection the collection to check
	 * @throws IllegalArgumentException if the object collection contains a <code>null</code> element
	 */
	public static void noNullElements(Collection<?> collection) {
		noNullElements(collection, "[Assertion failed] - this collection must not contain any null elements");
	}
	
	/**
	 * Assert that an map has no null elements(value).
	 * Note: Does not complain if the map is null or empty!
	 * <pre class="code">Assert.noNullElements(array, "The map must have non-null elements(value)");</pre>
	 * @param map the map to check
	 * @param message the exception message to use if the assertion fails
	 * @throws IllegalArgumentException if the object map contains a <code>null</code> element(value)
	 */
	public static void noNullElements(Map<?, ?> map, String message) {
		if (map != null) {
			for (Entry<?, ?> entry : map.entrySet()) {
				if (entry.getValue() == null) {
					throw new IllegalArgumentException(message);
				}
			}
		}
	}

	/**
	 * Assert that an map has no null elements(value).
	 * Note: Does not complain if the map is null or empty!
	 * <pre class="code">Assert.noNullElements(map);</pre>
	 * @param map the map to check
	 * @throws IllegalArgumentException if the object map contains a <code>null</code> element(value)
	 */
	public static void noNullElements(Map<?, ?> map) {
		noNullElements(map, "[Assertion failed] - this map must not contain any null elements(value)");
	}
	
	/**
	 * Assert that the provided object is an instance of the provided class.
	 * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
	 * @param type the type to check against
	 * @param obj the object to check
	 * @param message a message which will be prepended to the message produced by
	 * the function itself, and which may be used to provide context. It should
	 * normally end in a ": " or ". " so that the function generate message looks
	 * ok when prepended to it.
	 * @throws IllegalArgumentException if the object is not an instance of clazz
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class<?> type, Object obj, String message) {
		notNull(type, "Type to check against must not be null");
		if (!type.isInstance(obj)) {
			throw new IllegalArgumentException(message);
		}
	}
	
	/**
	 * Assert that the provided object is an instance of the provided class.
	 * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
	 * @param clazz the required class
	 * @param obj the object to check
	 * @throws IllegalArgumentException if the object is not an instance of clazz
	 * @see Class#isInstance
	 */
	public static void isInstanceOf(Class<?> type, Object obj) {
		isInstanceOf(type, obj, "Object of class [" + (obj != null ? obj.getClass().getName() : "null") + "] must be an instance of " + type);
	}
}
