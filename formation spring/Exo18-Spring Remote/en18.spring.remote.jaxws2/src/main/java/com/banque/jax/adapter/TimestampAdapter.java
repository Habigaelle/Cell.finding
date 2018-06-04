package com.banque.jax.adapter;

/**
 * Timestamp adapter pour JAXB.<br/>
 */
public class TimestampAdapter extends AbstractDateAdapter<java.sql.Timestamp> {
	private static final String DATE_PATTERN = "dd/MM/yyyy HH:mm:ss";

	/**
	 * Constructor of the object.
	 */
	public TimestampAdapter() {
		super(TimestampAdapter.DATE_PATTERN);
	}

	@Override
	protected java.sql.Timestamp buildResult(long pAValue) {
		return new java.sql.Timestamp(pAValue);
	}

}
