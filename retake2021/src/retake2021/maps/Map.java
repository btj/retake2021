package retake2021.maps;

import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * @immutable
 */
public abstract class Map<K, V> implements Iterable<K> {

	Map() {}
	
	public abstract V get(K key);
	
	@Override
	public boolean equals(Object other) {
		return other != null && other.getClass() == getClass();
	}
	
	public Iterator<K> iterator() {
		return new Iterator<>() {
			Map<K, V> map = Map.this;
			@Override
			public boolean hasNext() {
				return map instanceof NonemptyMap<?, ?>;
			}
			@Override
			public K next() {
				NonemptyMap<K, V> nonemptyMap = (NonemptyMap<K, V>)map;
				map = nonemptyMap.getTail();
				return nonemptyMap.getKey();
			}
		};
	}
	
	public void forEachValue(Consumer<? super V> consumer) {
		Map<K, V> map = this;
		while (map instanceof NonemptyMap<?, ?>) {
			NonemptyMap<K, V> nonemptyMap = (NonemptyMap<K, V>)map;
			consumer.accept(nonemptyMap.getValue());
			map = nonemptyMap.getTail();
		}
	}
	
	public Set<Integer> getLongKeyLengths() {
		return StreamSupport.stream(spliterator(), false)
				.filter(key -> key.toString().length() > get(key).toString().length())
				.map(key -> key.toString().length())
				.collect(Collectors.toSet());
	}
	
}
