package retake2021.classdiagrams.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

import org.junit.jupiter.api.Test;

import retake2021.classdiagrams.AssociationLine;
import retake2021.classdiagrams.ClassBox;
import retake2021.classdiagrams.Multiplicity;

class ClassDiagramsTest {

	ClassBox studentBox0 = new ClassBox("Student");
	ClassBox studentBox = new ClassBox("Student");
	ClassBox teamBox = new ClassBox("Team");
	AssociationLine line = new AssociationLine(studentBox, "team", Multiplicity.ZERO_TO_ONE, teamBox, "member", Multiplicity.ZERO_TO_MANY);
	
	@Test
	void testClassBoxGetName() {
		assertEquals("Student", studentBox.getName());
	}
	
	@Test
	void testGetRolesInitially() {
		assertEquals(Map.of(), studentBox0.getRoles());
	}
	
	@Test
	void testIsDeleted() {
		assertFalse(line.isDeleted());
	}
	
	@Test
	void testGetStartRole() {
		assertTrue(line.getStartRole() != null);
	}
	
	@Test
	void testGetClassBox() {
		assertEquals(studentBox, line.getStartRole().getClassBox());
		assertEquals(teamBox, line.getEndRole().getClassBox());
	}
	
	@Test
	void testRoleGetName() {
		assertEquals("team", line.getStartRole().getName());
		assertEquals("member", line.getEndRole().getName());
	}
	
	@Test
	void testGetMultiplicity() {
		assertEquals(Multiplicity.ZERO_TO_ONE, line.getStartRole().getMultiplicity());
		assertEquals(Multiplicity.ZERO_TO_MANY, line.getEndRole().getMultiplicity());
	}
	
	@Test
	void testGetAssociationLine() {
		assertEquals(line, line.getStartRole().getAssociationLine());
		assertEquals(line, line.getEndRole().getAssociationLine());
	}
	
	@Test
	void testGetEndRole() {
		assertTrue(line.getEndRole() != null);
	}

	@Test
	void testGetRoles() {
		assertEquals(Map.of("team", line.getStartRole()), studentBox.getRoles());
		assertEquals(Map.of("member", line.getEndRole()), teamBox.getRoles());
	}
	
	@Test
	void testDelete() {
		line.delete();
		assertTrue(line.isDeleted());
		assertEquals(Map.of(), studentBox.getRoles());
		assertEquals(Map.of(), teamBox.getRoles());
		assertNull(line.getStartRole().getClassBox());
		assertNull(line.getEndRole().getClassBox());
	}

}
