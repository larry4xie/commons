package my.commons.lang.serializer;

/**
 * 序列化工具工厂
 * 
 * @author xiegang
 * @since 2012-12-20
 * 
 */
public class SerializerFactory {
	public static final int JAVA = 0;
	public static final int KRYO = 1;
	public static final int HESSIAN = 2;
	
	public static final int DEFAULT = KRYO;
	

	private static Serializer serializer;

	/**
	 * 创建默认的序列化工具
	 * @return
	 */
	public static Serializer createSerializer() {
		if (serializer == null) {
			return createSerializer(DEFAULT);
		}
		return serializer;
	}

	/**
	 * 创建指定实现的序列化工具
	 * @param type
	 * @return
	 */
	public static Serializer createSerializer(int type) {
		switch (type) {
		case KRYO:
			serializer = new KryoSerializer();
			break;
		case HESSIAN:
			serializer = new HessianSerializer();
			break;
		case JAVA:
			serializer = new JavaSerializer();
			break;
		}
		return serializer;
	}

}
