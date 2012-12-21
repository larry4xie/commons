package my.commons.lang.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * kryo 序列化实现<br/>
 * kryo拥有很高的效率和很小的空间占用
 * 
 * @author xiegang
 * @since 2012-12-20
 *
 */
public class KryoSerializer implements Serializer {
	private static final String NAME = "kryo";

	public final static ThreadLocal<Kryo> kryos = new ThreadLocal<Kryo>();
	static {
		Kryo kryo = new Kryo();
		kryo.setReferences(true);
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		kryos.set(kryo);
	}
	
	/**
	 * Buffer size for serializers. Defaults to 1024 and can be changed via
	 * system properties. Minimum set to 256.
	 */
	public static final int BUFFER_SIZE = Math.max(Integer.getInteger("buffer_size", 1024), 256);

	final Kryo kryo;

	private final byte[] buffer = new byte[BUFFER_SIZE];
	private final Output output = new Output(buffer, -1);
	private final Input input = new Input(buffer);

	public String getName() {
		return NAME;
	}

	public KryoSerializer() {
		kryo = kryos.get();
	}

	@Override
	public void serialize(OutputStream out, Object object) throws IOException {
		output.setOutputStream(out);
		kryo.writeClassAndObject(output, object);
		output.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(InputStream in) throws IOException {
		input.setInputStream(in);
		Object result = kryo.readClassAndObject(input);
		return (T) result;
	}
}