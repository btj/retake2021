package retake2021.classdiagrams;

import logicalcollections.LogicalMap;

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
	 */
	private ClassBox classBox;
	private final String name;
	private final Multiplicity multiplicity;
	private final AssociationLine associationLine;
	
	/**
	 * @invar | (getClassBoxInternal() == null) == getAssociationLineInternal().isDeletedInternal()
	 * @invar | getClassBoxInternal() == null || getClassBoxInternal().getRolesInternal().get(getNameInternal()) == this
	 * @invar | getAssociationLineInternal().getStartRoleInternal() == this || getAssociationLineInternal().getEndRoleInternal() == this
	 * 
	 * @peerObject (package-level)
	 */
	ClassBox getClassBoxInternal() { return classBox; }
	
	/**
	 * @immutable
	 * @post | result != null
	 */
	String getNameInternal() { return name; }
	
	/**
	 * @immutable
	 * @post | result != null
	 */
	Multiplicity getMultiplicityInternal() { return multiplicity; }

	/**
	 * @immutable
	 * @post | result != null
	 * @peerObject (package-level)
	 */
	AssociationLine getAssociationLineInternal() { return associationLine; }
	
	/**
	 * @peerObject
	 */
	public ClassBox getClassBox() { return getClassBoxInternal(); }
	
	/**
	 * @immutable
	 */
	public String getName() { return getNameInternal(); }
	
	/**
	 * @immutable
	 */
	public Multiplicity getMultiplicity() { return getMultiplicityInternal(); }

	/**
	 * @immutable
	 * @peerObject
	 */
	public AssociationLine getAssociationLine() { return getAssociationLineInternal(); }
	
	/**
	 * @throws IllegalArgumentException | classBox == null
	 * @throws IllegalArgumentException | name == null
	 * @throws IllegalArgumentException | multiplicity == null
	 * @throws IllegalArgumentException | associationLine == null
	 * 
	 * @mutates_properties | classBox.getRolesInternal()
	 * @post | getClassBoxInternal() == classBox
	 * @post | getNameInternal() == name
	 * @post | getMultiplicityInternal() == multiplicity
	 * @post | getAssociationLineInternal() == associationLine
	 * @post | classBox.getRolesInternal().equals(LogicalMap.plus(old(classBox.getRolesInternal()), name, this))
	 */
	Role(ClassBox classBox, String name, Multiplicity multiplicity, AssociationLine associationLine) {
		if (classBox == null)
			throw new IllegalArgumentException("`classBox` is null");
		if (name == null)
			throw new IllegalArgumentException("`name` is null");
		if (multiplicity == null)
			throw new IllegalArgumentException("`multiplicity` is null");
		if (associationLine == null)
			throw new IllegalArgumentException("`associationLine` is null");
		
		this.classBox = classBox;
		this.name = name;
		this.multiplicity = multiplicity;
		this.associationLine = associationLine;
		classBox.putRole(name, this);
	}
	
	/**
	 * @pre | getClassBoxInternal() != null
	 * @mutates_properties | getClassBoxInternal().getRolesInternal(), getClassBoxInternal()
	 * @post | old(getClassBoxInternal()).getRolesInternal().equals(LogicalMap.minus(old(getClassBoxInternal().getRolesInternal()), getNameInternal()))
	 * @post | getClassBoxInternal() == null
	 */
	void delete() {
		classBox.removeRole(name);
		classBox = null;
	}
	
}
