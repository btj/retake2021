package retake2021.classdiagrams;

import java.util.HashMap;
import java.util.Map;

import logicalcollections.LogicalMap;

/**
 * @invar | getRoles().values().stream().allMatch(role -> role != null && role.getClassBox() == this)
 * @invar | getRoles().keySet().stream().allMatch(roleName -> roleName != null && getRoles().get(roleName).getName().equals(roleName))
 */
public class ClassBox {

	/**
	 * @invar | name != null
	 * @invar | roles != null
	 * @invar | roles.values().stream().allMatch(role -> role != null)
	 * @invar | roles.keySet().stream().allMatch(roleName -> roleName != null)
	 */
	private final String name;
	/**
	 * @representationObject
	 */
	private final Map<String, Role> roles = new HashMap<>();
	
	/**
	 * @invar | getRolesInternal().values().stream().allMatch(role -> role.getClassBoxInternal() == this)
	 * @invar | getRolesInternal().keySet().stream().allMatch(roleName -> getRolesInternal().get(roleName).getNameInternal().equals(roleName))
	 * 
	 * @immutable
	 * @post | result != null
	 */
	String getNameInternal() { return name; }
	
	/**
	 * @post | result != null
	 * @post | result.values().stream().allMatch(role -> role != null)
	 * @post | result.keySet().stream().allMatch(roleName -> roleName != null)
	 * @creates | result
	 * @peerObjects (package-level) | result.values()
	 */
	Map<String, Role> getRolesInternal() { return Map.copyOf(roles); }
	
	/**
	 * @immutable
	 * @post | result != null
	 */
	public String getName() { return getNameInternal(); }

	/**
	 * @post | result != null
	 * @creates | result
	 * @peerObjects | result.values()
	 */
	public Map<String, Role> getRoles() { return getRolesInternal(); }

	/**
	 * @throws IllegalArgumentException | name == null
	 * @post | getName().equals(name)
	 * @post | getRoles().isEmpty()
	 */
	public ClassBox(String name) {
		if (name == null)
			throw new IllegalArgumentException("`name` is null");
		
		this.name = name;
	}
	
	/**
	 * @pre | name != null
	 * @pre | role != null
	 * @mutates | this
	 * @post | getRolesInternal().equals(LogicalMap.plus(old(getRolesInternal()), name, role))
	 */
	void putRole(String name, Role role) {
		roles.put(name, role);
	}
	
	/**
	 * @pre | name != null
	 * @mutates | this
	 * @post | getRolesInternal().equals(LogicalMap.minus(old(getRolesInternal()), name))
	 */
	void removeRole(String name) {
		roles.remove(name);
	}
	
}
