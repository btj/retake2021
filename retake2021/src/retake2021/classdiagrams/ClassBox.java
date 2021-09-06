package retake2021.classdiagrams;

import java.util.HashMap;
import java.util.Map;

/**
 * @invar | getRoles().values().stream().allMatch(role -> role != null && role.getClassBox() == this)
 * @invar | getRoles().keySet().stream().allMatch(roleName -> roleName != null && getRoles().get(roleName).getName().equals(roleName))
 */
public class ClassBox {

	/**
	 * @invar | name != null
	 * @invar | roles != null
	 * @invar | roles.values().stream().allMatch(role -> role != null && role.classBox == this)
	 * @invar | roles.keySet().stream().allMatch(roleName -> roleName != null && roles.get(roleName).name.equals(roleName))
	 */
	final String name;
	/**
	 * @representationObject
	 * @peerObjects | roles.values()
	 */
	final Map<String, Role> roles = new HashMap<>();
	
	/**
	 * @immutable
	 * @post | result != null
	 */
	public String getName() { return name; }

	/**
	 * @post | result != null
	 * @creates | result
	 * @peerObjects | result.values()
	 */
	public Map<String, Role> getRoles() { return Map.copyOf(roles); }

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
	
}
