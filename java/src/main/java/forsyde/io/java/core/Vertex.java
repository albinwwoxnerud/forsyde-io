/**
 * 
 */
package forsyde.io.java.core;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rjordao
 *
 *         Class holding data for a Vertex (Node) in memory.
 * 
 *         Every vertex contains a number of {@link Port}s (which are repeated
 *         in the vertexed to increase reliability in the model, since putting
 *         them in edges would have been sufficient) with their associated
 *         types. Also, every vertex contains "Properties" which are arbitrary
 *         self-contained associated data, such as the size of bits in a Signal
 *         or the time slots in a Time Division Multiplexer.
 * 
 * 
 */
public class Vertex {

	public String identifier;
	public List<Port> ports = new ArrayList<Port>();
	public Map<String, Object> properties = new HashMap<String, Object>();

	/**
	 * Utility constructor initializing all associated data as empty.
	 * 
	 * @param identifier the obligatory unique ID for this vertex.
	 * @see #Vertex(String, List, Map)
	 */
	public Vertex(String identifier) {
		this.identifier = identifier;
	}

	/**
	 * Complete constructor, also initializing all the data one vertex may be
	 * associated with. In the case of Java, the association is given by object
	 * containment.
	 * 
	 * @param identifier The obligatory unique ID for this vertex.
	 * @param ports      The list (set-like, no duplicates) of ports for this
	 *                   vertex.
	 * @param properties The mapping (associative array) of properties for this
	 *                   vertex. Remember that it should be a tree of primitive
	 *                   types such as Integers, Floats, Strings etc.
	 */
	public Vertex(String identifier, List<Port> ports, Map<String, Object> properties) {
		this.identifier = identifier;
		this.ports = ports;
		this.properties = properties;
	}

	@Override
	public String toString() {
		final int maxLen = 5;
		StringBuilder builder = new StringBuilder();
		builder.append("Vertex [identifier=").append(identifier).append(", type=").append(", ports=")
				.append(ports != null ? toString(ports, maxLen) : null).append(", properties=")
				.append(properties != null ? toString(properties.entrySet(), maxLen) : null).append("]");
		return builder.toString();
	}

	public String getTypeName() {
		return "Vertex";
	}

	public Optional<Port> getPort(String portName) {
		return ports.stream().filter((p) -> p.identifier.equals(portName)).findFirst();
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Vertex)) {
			return false;
		}
		Vertex other = (Vertex) obj;
		return Objects.equals(identifier, other.identifier);
	}

}
