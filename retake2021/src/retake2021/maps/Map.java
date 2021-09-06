package retake2021.maps;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
			/*
			 * Code for skipping entries whose keys have appeared before.
			 * Taking overwritten entries into account here was *optional*;
			 * no points were subtracted if you did not take them into account here.
			 */
			Set<K> keysSeen = new HashSet<>();
			private void skipOverwrittenEntries() {
				for (;;) {
					if (!(map instanceof NonemptyMap<?, ?>))
						break;
					NonemptyMap<K, V> nemap = (NonemptyMap<K, V>)map;
					if (!keysSeen.contains(nemap.getKey()))
						break;
					map = nemap.getTail();
				}
			}
			{ skipOverwrittenEntries(); }
			@Override
			public boolean hasNext() {
				return map instanceof NonemptyMap<?, ?>;
			}
			@Override
			public K next() {
				NonemptyMap<K, V> nonemptyMap = (NonemptyMap<K, V>)map;
				keysSeen.add(nonemptyMap.getKey());
				map = nonemptyMap.getTail();
				skipOverwrittenEntries();
				return nonemptyMap.getKey();
			}
		};
	}
	
	public void forEachValue(Consumer<? super V> consumer) {
		Map<K, V> map = this;
		Set<K> keysSeen = new HashSet<>();
		while (map instanceof NonemptyMap<?, ?>) {
			NonemptyMap<K, V> nonemptyMap = (NonemptyMap<K, V>)map;
			// No points were subtracted if you did not take overwritten
			// entries into account.
			if (!keysSeen.contains(nonemptyMap.getKey())) {
				consumer.accept(nonemptyMap.getValue());
				keysSeen.add(nonemptyMap.getKey());
			}
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
