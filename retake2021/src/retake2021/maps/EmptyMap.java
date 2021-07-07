package retake2021.maps;

public class EmptyMap<K, V> extends Map<K, V> {
	
	EmptyMap() {}
	
	public static <K, V> EmptyMap<K, V> of() { return new EmptyMap<>(); }
	
	@Override
	public V get(K key) {
		return null;
	}
	
}
