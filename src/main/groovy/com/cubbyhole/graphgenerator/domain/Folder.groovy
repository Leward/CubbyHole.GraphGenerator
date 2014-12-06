package com.cubbyhole.graphgenerator.domain

import java.util.Collection;

class Folder implements FileSpaceElement, Containable<FileSpaceElement>, GraphNodeInterface {

	@Override
	public String toString() {
		def string = "[Folder '${name}' with ${content.size()} child(ren)]"
	}
	
	@Override
	public Map<String, Object> asNodePropertiesMap() {
		return [
			name: name
		];
	}
	
	@Override
	public String getNodeIdentifier() {
		return "folder_${uuid}";
	}
	
	@Override
	public Collection<String> getLabels() {
		return ["Folder"]
	}
	
	@Override
	public SharedElement makeSharedElement() {
		return new SharedFolder(this)
	}
	
}
