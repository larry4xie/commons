package my.commons.lang.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * Java内嵌序列化实现<br/>
 * The source object must implement {@link Serializable}.
 * 
 * @author xiegang
 * @since 2012-12-20
 *
 */
public class JavaSerializer implements Serializer {
	private static final String NAME = "java";
	
	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public void serialize(OutputStream out, Object object) throws IOException {
		if (!(object instanceof Serializable)) {
			throw new IllegalArgumentException(getClass().getSimpleName() + " requires a Serializable payload " + "but received an object of type [" + object.getClass().getName() + "]");
		}
		ObjectOutputStream oos = new ObjectOutputStream(out);
		oos.writeObject(object);
		oos.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(InputStream in) throws IOException {
		try{
			ObjectInputStream ois = new ObjectInputStream(in);
			Object v = ois.readObject();
			return (T) v;
		} catch (ClassNotFoundException e) {
			throw new IOException("Failed to deserialize object type", e);
		}
	}
}
