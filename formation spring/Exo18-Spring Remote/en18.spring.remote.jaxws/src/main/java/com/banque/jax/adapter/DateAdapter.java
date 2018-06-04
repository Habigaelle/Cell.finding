package com.banque.jax.adapter;

/**
 * Date adapter pour JAXB.<br/>
 */
public class DateAdapter extends AbstractDateAdapter<java.sql.Date> {
	private static final String DATE_PATTERN = "dd/MM/yyyy";

	/**
	 * Constructor of the object.
	 */
	public DateAdapter() {
		super(DateAdapter.DATE_PATTERN);
	}

	@Override
	protected java.sql.Date buildResult(long pAValue) {
		return new java.sql.Date(pAValue);
	}

}
