package com.cubbyhole.graphgenerator.domain

import java.util.Collection;
import java.util.Map;

class File implements FileSpaceElement, GraphNodeInterface {

	@Override
	public String toString() {
		def string = "[File '${name}']"
	}

	@Override
	public Map<String, Object> asNodePropertiesMap() {
		return [
			name: name	
		];
	}
	
	@Override
	public String getNodeIdentifier() {
		return "file_${uuid}";
	}
	
	@Override
	public Collection<String> getLabels() {
		return ["File"]
	}
	
	@Override
	public SharedElement makeSharedElement() {
		return new SharedFile(this)
	}

}
