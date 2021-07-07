package retake2021.maps;

public class NonemptyMap<K, V> extends Map<K, V> {
	
	private final K key;
	private final V value;
	private final Map<K, V> tail;
	
	public K getKey() { return key; }
	
	public V getValue() { return value; }
	
	public Map<K, V> getTail() { return tail; }
	
	NonemptyMap(K key, V value, Map<K, V> tail) {
		// These checks are optional
		if (key == null)
			throw new IllegalArgumentException("`key` is null");
		if (value == null)
			throw new IllegalArgumentException("`value` is null");
		if (tail == null)
			throw new IllegalArgumentException("`tail` is null");
		
		this.key = key;
		this.value = value;
		this.tail = tail;
	}
	
	public static <K, V> NonemptyMap<K, V> of(K key, V value, Map<K, V> tail) {
		return new NonemptyMap<>(key, value, tail);
	}
	
	@Override
	public V get(K key) {
		return key.equals(this.key) ? this.value : tail.get(key);
	}
	
	@Override
	public boolean equals(Object other) {
		if (!super.equals(other))
			return false;
		NonemptyMap<?, ?> otherMap = (NonemptyMap<?, ?>)other;
		return key.equals(otherMap.key) && value.equals(otherMap.value) && tail.equals(otherMap.tail);
	}
	
}
