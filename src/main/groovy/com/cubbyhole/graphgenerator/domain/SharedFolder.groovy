package com.cubbyhole.graphgenerator.domain

import java.util.Collection;
import java.util.Map;

class SharedFolder implements FileSpaceElement, GraphNodeInterface, SharedElement {

	public SharedFolder() {
		
	}
	
	public SharedFolder(FileSpaceElement original) {
		this.original = original
	}
	
	@Override
	public String getNodeIdentifier() {
		return "sharedFolder_${uuid}";
	}

	@Override
	public Collection<String> getLabels() {
		return ["SharedFolder"];
	}

	@Override
	public Map<String, Object> asNodePropertiesMap() {
		return [:];
	}
	
	@Override
	public SharedElement makeSharedElement() {
		return new SharedFolder(this)
	}

}
