package my.commons.lang.serializer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.caucho.hessian.io.Hessian2StreamingInput;
import com.caucho.hessian.io.Hessian2StreamingOutput;

/**
 * hessian 序列化实现<br/>
 * hessian很好的支持跨语言序列化
 * 
 * @author xiegang
 * @since 2012-12-20
 *
 */
public class HessianSerializer implements Serializer {
	private static final String NAME = "hessian";

	public String getName() {
		return NAME;
	}

	@Override
	public void serialize(OutputStream out, Object object) throws IOException {
		Hessian2StreamingOutput hout = new Hessian2StreamingOutput(out);
		hout.writeObject(object);
		hout.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T deserialize(InputStream in) throws IOException {
		Hessian2StreamingInput hin = new Hessian2StreamingInput(in);
		Object t = (Object) hin.readObject();
		hin.close();
		return (T) t;
	}

}