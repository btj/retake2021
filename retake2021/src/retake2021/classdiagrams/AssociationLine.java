package retake2021.classdiagrams;

import logicalcollections.LogicalMap;

/**
 * @invar | getStartRole() != null
 * @invar | getEndRole() != null
 * @invar | getStartRole().getAssociationLine() == this
 * @invar | getEndRole().getAssociationLine() == this
 */
public class AssociationLine {
	
	/**
	 * @invar | startRole != null
	 * @invar | endRole != null
	 */
	private final Role startRole;
	private final Role endRole;
	private boolean isDeleted;
	
	/**
	 * @invar | getStartRoleInternal().getAssociationLineInternal() == this
	 * @invar | getEndRoleInternal().getAssociationLineInternal() == this
	 * 
	 * @immutable
	 * @peerObject (package-level)
	 * @post | result != null
	 */
	Role getStartRoleInternal() { return startRole; }
	/**
	 * @immutable
	 * @peerObject (package-level)
	 * @post | result != null
	 */
	Role getEndRoleInternal() { return endRole; }
	boolean isDeletedInternal() { return isDeleted; }
	
	/**
	 * @peerObject
	 */
	public Role getStartRole() { return getStartRoleInternal(); }
	
	/**
	 * @peerObject
	 */
	public Role getEndRole() { return getEndRoleInternal(); }
	
	public boolean isDeleted() { return isDeletedInternal(); }
	
	/**
	 * @throws IllegalArgumentException | startClassBox == null
	 * @throws IllegalArgumentException | startRoleName == null
	 * @throws IllegalArgumentException | startRoleMultiplicity == null
	 * @throws IllegalArgumentException | endClassBox == null
	 * @throws IllegalArgumentException | endRoleName == null
	 * @throws IllegalArgumentException | endRoleMultiplicity == null
	 * @throws IllegalArgumentException if {@code startClassBox} equals {@code endClassBox}. This is not yet supported.
	 *     | startClassBox == endClassBox
	 * @throws IllegalArgumentException | startClassBox.getRoles().containsKey(startRoleName)
	 * @throws IllegalArgumentException | endClassBox.getRoles().containsKey(endRoleName)
	 * 
	 * @mutates_properties | startClassBox.getRoles(), endClassBox.getRoles()
	 * 
	 * @post | !isDeleted()
	 * @post | getStartRole().getClassBox() == startClassBox
	 * @post | getStartRole().getName().equals(startRoleName)
	 * @post | getStartRole().getMultiplicity() == startRoleMultiplicity
	 * @post | getEndRole().getClassBox() == endClassBox
	 * @post | getEndRole().getName().equals(endRoleName)
	 * @post | getEndRole().getMultiplicity() == endRoleMultiplicity
	 * @post | startClassBox.getRoles().equals(LogicalMap.plus(old(startClassBox.getRoles()), startRoleName, getStartRole()))
	 * @post | endClassBox.getRoles().equals(LogicalMap.plus(old(endClassBox.getRoles()), endRoleName, getEndRole()))
	 */
	public AssociationLine(ClassBox startClassBox, String startRoleName, Multiplicity startRoleMultiplicity, ClassBox endClassBox, String endRoleName, Multiplicity endRoleMultiplicity) {
		if (startClassBox == null)
			throw new IllegalArgumentException("`startClassBox` is null");
		if (startRoleName == null)
			throw new IllegalArgumentException("`startRoleName` is null");
		if (startRoleMultiplicity == null)
			throw new IllegalArgumentException("`startRoleMultiplicity` is null");
		if (endClassBox == null)
			throw new IllegalArgumentException("`endClassBox` is null");
		if (endRoleName == null)
			throw new IllegalArgumentException("`endRoleName` is null");
		if (endRoleMultiplicity == null)
			throw new IllegalArgumentException("`endRoleMultiplicity` is null");
		
		if (startClassBox == endClassBox)
			throw new IllegalArgumentException("`startClassBox` equals `endClassBox`; this is currently not supported");
 		if (startClassBox.getRoles().containsKey(startRoleName))
 			throw new IllegalArgumentException("`startClassBox` already has a role whose name equals `startRoleName`");
 		if (endClassBox.getRoles().containsKey(endRoleName))
 			throw new IllegalArgumentException("`endClassBox` already has a role whose name equals `endRoleName`");
		
		startRole = new Role(startClassBox, startRoleName, startRoleMultiplicity, this);
		endRole = new Role(endClassBox, endRoleName, endRoleMultiplicity, this);
	}
	
	/**
	 * @pre | !isDeleted()
	 * @mutates_properties | getStartRole().getClassBox(), getStartRole().getClassBox().getRoles(), getEndRole().getClassBox(), getEndRole().getClassBox().getRoles()
	 * @post | isDeleted()
	 * @post | old(getStartRole().getClassBox()).getRoles().equals(LogicalMap.minus(old(getStartRole().getClassBox().getRoles()), getStartRole().getName()))
	 * @post | old(getEndRole().getClassBox()).getRoles().equals(LogicalMap.minus(old(getEndRole().getClassBox().getRoles()), getEndRole().getName()))
	 */
	public void delete() {
		isDeleted = true;
		startRole.delete();
		endRole.delete();
	}

}
