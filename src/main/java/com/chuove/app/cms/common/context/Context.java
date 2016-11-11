package com.chuove.app.cms.common.context;

public abstract interface Context<K, T> {
	public abstract T get(K paramK);

	public abstract void set(K paramK, T paramT);
}
