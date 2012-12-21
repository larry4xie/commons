package my.commons.lang.unit;

/**
 * 计算机存储单位
 * 
 * @author xiegang
 * @since 2012-12-20
 *
 */
public enum Data implements Unit<Data> {
	BITS(1),
	Kb(1024, BITS),
	Mb(1024, Kb),
	Gb(1024, Mb),
	
	BYTES(8, BITS),
	KB(1024, BYTES),
	MB(1024, KB), 
	GB(1024, MB), 
	TB(1024, GB), 
	PB(1024, TB);

	private final double multiplier;

	private Data(double multiplier) {
		this.multiplier = multiplier;
	}

	private Data(double multiplier, Data base) {
		this(multiplier * base.multiplier);
	}

	@Override
	public double multiplier() {
		return multiplier;
	}

	@Override
	public double transfer(Data to, double value) {
		return value * multiplier() / to.multiplier;
	}
}