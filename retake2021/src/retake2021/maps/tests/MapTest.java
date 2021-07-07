package retake2021.maps.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import retake2021.maps.EmptyMap;
import retake2021.maps.Map;
import retake2021.maps.NonemptyMap;

class MapTest {

	NonemptyMap<String, String> myMap =
			NonemptyMap.of("één", "one",
			NonemptyMap.of("twee", "two",
			EmptyMap.of()));
	
	@Test
	void test() {
		assertEquals("één", myMap.getKey());
		assertEquals("one", myMap.getValue());
		assertEquals(NonemptyMap.of("twee", "two", EmptyMap.of()), myMap.getTail());
		assertEquals("one", myMap.get("één"));
		assertEquals("two", myMap.get("twee"));
		assertEquals(null, myMap.get("drie"));
		assertFalse(myMap.equals(EmptyMap.of()));
		assertFalse(EmptyMap.of().equals(myMap));
		assertFalse(myMap.equals(myMap.getTail()));
	}
	
	@Test
	void testExtraPointsRequirements() {
		Set<String> keys = new HashSet<>();
		for (String key : myMap)
			keys.add(key);
		assertEquals(Set.of("één", "twee"), keys);
		
		Set<String> values = new HashSet<>();
		myMap.forEachValue(value -> values.add(value));
		assertEquals(Set.of("one", "two"), values);
		
		assertEquals(Set.of(4), myMap.getLongKeyLengths());
	}

}
