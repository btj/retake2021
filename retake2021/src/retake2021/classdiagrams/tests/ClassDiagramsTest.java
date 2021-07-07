package retake2021.classdiagrams.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import retake2021.classdiagrams.AssociationLine;
import retake2021.classdiagrams.ClassBox;
import retake2021.classdiagrams.Multiplicity;

class ClassDiagramsTest {

	@Test
	void test() {
		ClassBox studentBox = new ClassBox("Student");
		assertEquals("Student", studentBox.getName());
		assertEquals(Map.of(), studentBox.getRoles());
		
		ClassBox teamBox = new ClassBox("Team");
		AssociationLine line = new AssociationLine(studentBox, "team", Multiplicity.ZERO_TO_ONE, teamBox, "member", Multiplicity.ZERO_TO_MANY);
		assertFalse(line.isDeleted());
		assertTrue(line.getStartRole() != null);
		assertEquals(studentBox, line.getStartRole().getClassBox());
		assertEquals("team", line.getStartRole().getName());
		assertEquals(Multiplicity.ZERO_TO_ONE, line.getStartRole().getMultiplicity());
		assertEquals(line, line.getStartRole().getAssociationLine());
		assertTrue(line.getEndRole() != null);
		assertEquals(teamBox, line.getEndRole().getClassBox());
		assertEquals("member", line.getEndRole().getName());
		assertEquals(Multiplicity.ZERO_TO_MANY, line.getEndRole().getMultiplicity());
		assertEquals(line, line.getEndRole().getAssociationLine());
		assertEquals(Map.of("team", line.getStartRole()), studentBox.getRoles());
		assertEquals(Map.of("member", line.getEndRole()), teamBox.getRoles());
		
		line.delete();
		assertTrue(line.isDeleted());
		assertEquals(Map.of(), studentBox.getRoles());
		assertEquals(Map.of(), teamBox.getRoles());
		assertNull(line.getStartRole().getClassBox());
		assertNull(line.getEndRole().getClassBox());
	}

}
