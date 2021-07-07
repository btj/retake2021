package retake2021.classdiagrams;

/**
 * @invar | getName() != null
 * @invar | getMultiplicity() != null
 * @invar | getAssociationLine() != null
 * @invar | (getClassBox() == null) == getAssociationLine().isDeleted()
 * @invar | getClassBox() == null || getClassBox().getRoles().get(getName()) == this
 * @invar | getAssociationLine().getStartRole() == this || getAssociationLine().getEndRole() == this
 */
public class Role {

	/**
	 * @invar | name != null
	 * @invar | multiplicity != null
	 * @invar | associationLine != null
	 * @invar | (classBox == null) == associationLine.isDeleted
	 * @invar | classBox == null || classBox.roles.get(name) == this
	 * @invar | associationLine.startRole == this || associationLine.endRole == this
	 * 
	 * @peerObject
	 */
	ClassBox classBox;
	final String name;
	final Multiplicity multiplicity;
	/**
	 * @peerObject
	 */
	final AssociationLine associationLine;
	
	/**
	 * @peerObject
	 */
	public ClassBox getClassBox() { return classBox; }
	
	/**
	 * @immutable
	 */
	public String getName() { return name; }
	
	/**
	 * @immutable
	 */
	public Multiplicity getMultiplicity() { return multiplicity; }

	/**
	 * @immutable
	 * @peerObject
	 */
	public AssociationLine getAssociationLine() { return associationLine; }
	
	Role(ClassBox classBox, String name, Multiplicity multiplicity, AssociationLine associationLine) {
		this.classBox = classBox;
		this.name = name;
		this.multiplicity = multiplicity;
		this.associationLine = associationLine;
		classBox.roles.put(name, this);
	}
	
	void delete() {
		classBox.roles.remove(name);
		classBox = null;
	}
	
}
