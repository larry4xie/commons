package my.commons.lang.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * A {@link Serializer} is a kind of classes which convert objects into their
 * byte array form, which is more convenient than their properties for writing
 * them into {@link OutputStream}s, and re-assembling them by reading this back
 * in.
 * 
 * @author xiegang
 * @since 2012-12-20
 *
 */
public interface Serializer{
	/**
	 * Write an object to the given OutputStream.
	 * <p>Note: Implementations should not close the given OutputStream
	 * (or any decorators of that OutputStream) but rather leave this up
	 * to the caller.
	 * </p>
	 * 
	 * @param out the output stream
	 * @param object the object to serialize
	 * @throws IOException
	 */
	public void serialize(OutputStream out, Object object) throws IOException;
	
	/**
	 * Read (assemble) an object from the given InputStream.
	 * <p>Note: Implementations should not close the given InputStream
	 * (or any decorators of that InputStream) but rather leave this up
	 * to the caller.</p>
	 * 
	 * @param in the input stream
	 * @return the deserialized object
	 * @throws IOException
	 */
	public <T> T deserialize(InputStream in) throws IOException;
	
	/**
	 * Serializer provided name
	 * @return
	 */
	public String getName();
}