package com.cubbyhole.graphgenerator.domain

import java.util.Collection;
import java.util.Map;

class FileSpace implements Containable<FileSpaceElement>, GraphNodeInterface {

	@Override
	public String toString() {
		return content
	}

	@Override
	public Map<String, Object> asNodePropertiesMap() {
		return [:];
	}
	
	@Override
	public String getNodeIdentifier() {
		return "filespace_${uuid}";
	}
	@Override
	public Collection<String> getLabels() {
		return ["FileSpace"]
	}
	
	public Folder getRandomFolderInTree() {
		def elements = flatten()
		elements = elements.findAll { it instanceof Folder }
		if(elements.size() == 0) {
			return null
		}
		def random = new Random()
		return elements[random.nextInt(elements.size())]
	}
	
	public File getRandomFileInTree() {
		def elements = flatten()
		elements = elements.findAll { it instanceof File }
		if(elements.size() == 0) {
			return null
		}
		def random = new Random()
		return elements[random.nextInt(elements.size())]
	}
	
	public FileSpaceElement getRandomFileSpaceElementInTree() {
		def elements = flatten()
		elements = elements.findAll { it instanceof FileSpaceElement }
		if(elements.size() == 0) {
			return null
		}
		def random = new Random()
		return elements[random.nextInt(elements.size())]
	}
	
	public Collection<FileSpaceElement> getSharedElementsInTree() {
		return flatten().findAll { it instanceof SharedElement }
	}

}
