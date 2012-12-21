package my.commons.lang.unit;

/**
 * Represents a unit hierarchy for a given unit of measure; eg: time. 
 * Instances represent specific units from the hierarchy; eg: seconds.
 * 
 * @author xiegang
 * @since 2012-12-20
 *
 * @param <U> Unit type
 */
public interface Unit<U extends Unit<U>> {
	/**
	 * Returns the weight of this unit relative to other units in the same hierarchy.  Typically the smallest unit in the hierarchy returns 1
	 * @return
	 */
	public double multiplier();
	
	/**
	 * unit transfer
	 * 
	 * @param to
	 * @param value
	 * @return
	 */
	public double transfer(U to, double value);
}