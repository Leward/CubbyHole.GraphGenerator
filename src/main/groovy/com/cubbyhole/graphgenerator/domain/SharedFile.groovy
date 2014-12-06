package com.cubbyhole.graphgenerator.domain

import java.util.Collection;
import java.util.Map;

class SharedFile implements FileSpaceElement, GraphNodeInterface, SharedElement {

	public SharedFile() {
	}

	public SharedFile(FileSpaceElement original) {
		this.original = original
	}

	@Override
	public String getNodeIdentifier() {
		return "sharedFile_${uuid}";
	}

	@Override
	public Collection<String> getLabels() {
		return ["SharedFile"];
	}

	@Override
	public Map<String, Object> asNodePropertiesMap() {
		return [:];
	}

	@Override
	public SharedElement makeSharedElement() {
		return new SharedFile(this)
	}
}
