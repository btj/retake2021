package retake2021.maps.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import retake2021.maps.EmptyMap;
import retake2021.maps.NonemptyMap;

class MapTest {

	NonemptyMap<String, String> myMap =
			NonemptyMap.of("één", "one",
			NonemptyMap.of("twee", "two",
			EmptyMap.of()));
	
	@Test
	void testGetKey() {
		assertEquals("één", myMap.getKey());
	}
	
	@Test
	void testGetValue() {
		assertEquals("one", myMap.getValue());
	}
	
	@Test
	void testGetTail() {
		assertEquals(NonemptyMap.of("twee", "two", EmptyMap.of()), myMap.getTail());
	}
	
	@Test
	void testGetExistingEntry() {
		assertEquals("one", myMap.get("één"));
		assertEquals("two", myMap.get("twee"));
	}
	
	@Test
	void testGetNonexisitingEntry() {
		assertEquals(null, myMap.get("drie"));
	}
	
	@Test
	void testEqualsEmpty() {
		assertFalse(myMap.equals(EmptyMap.of()));
	}
	
	@Test
	void testEmptyEquals() {
		assertFalse(EmptyMap.of().equals(myMap));
	}
	
	@Test
	void testEqualsNonempty() {
		assertFalse(myMap.equals(myMap.getTail()));
	}
	
//	@Test
//	void testExtraPointsRequirements() {
//		Set<String> keys = new HashSet<>();
//		for (String key : myMap)
//			keys.add(key);
//		assertEquals(Set.of("één", "twee"), keys);
//		
//		Set<String> values = new HashSet<>();
//		myMap.forEachValue(value -> values.add(value));
//		assertEquals(Set.of("one", "two"), values);
//		
//		assertEquals(Set.of(4), myMap.getLongKeyLengths());
//	}

}
