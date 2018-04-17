package br.com.my.functional;

import java.util.function.Function;

public class Monada<T> {
	
	private T value;
	
	private static final Monada<?> EMPTY = new Monada<>();
	
	private static <T> Monada<T> empty() {
		Monada<T> t = (Monada<T>) EMPTY;
        return t;
	}

	private Monada(T value) {
		this.value = value;
	}
	
	public Monada() {
		
	}
	
	public static <T> Monada<T> of(T value) {
		return new Monada<T>(value);
	}
	
	public Monada<T> mapSameTypeIfNull(Function <T, T> map) {
		if (value == null) {
			return Monada.of(map.apply(value));
		}
		return this;
	}
	
	public <U> Monada<U> map(Function<T, U> map) {
		if (value == null) return Monada.empty();
		return Monada.of(map.apply(value));
	}
	
	public <U> Monada<U> mapIfNullOrContains(Function<T, U> mapIfNull, Function<T, U> mapIfContains) {
		if (value == null) return Monada.of(mapIfNull.apply(value));
		return Monada.of(mapIfContains.apply(value));
	}
	
	public T get() {
		return value;
	}
	
}
