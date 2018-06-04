package com.banque.jax.adapter;

import java.text.SimpleDateFormat;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Adapter pour JAX-B cible sur les dates.<br/>
 * Permet de gerer proprement les dates. <br/>
 */
abstract class AbstractDateAdapter<T extends java.util.Date> extends XmlAdapter<String, T> {
	private volatile SimpleDateFormat dateFormat;

	/**
	 * Constructor of the object.
	 */
	protected AbstractDateAdapter(String datePattern) {
		super();
		this.dateFormat = new SimpleDateFormat(datePattern);
		this.dateFormat.setLenient(false);
	}

	/**
	 * Fabrique l'element a partir de sa valeur entiere
	 *
	 * @param aValue
	 *            la valeur entiere d'une date
	 * @return la date
	 */
	protected abstract T buildResult(long aValue);

	@Override
	public T unmarshal(String aV) throws Exception {
		if (aV == null || aV.trim().isEmpty()) {
			return null;
		}
		java.util.Date result;
		// SimpleDateFormat n'etant pas thread safe il est preferable de
		// l'utiliser dans un bloc synchronise
		synchronized (this.dateFormat) {
			result = this.dateFormat.parse(aV);
		}
		return this.buildResult(result.getTime());
	}

	@Override
	public String marshal(T aV) throws Exception {
		if (aV == null) {
			return null;
		}
		String result;
		// SimpleDateFormat n'etant pas thread safe il est preferable de
		// l'utiliser dans un bloc synchronise
		synchronized (this.dateFormat) {
			result = this.dateFormat.format(aV);
		}
		return result;
	}

}
