package com.cubbyhole.graphgenerator.domain

import java.util.Collection;
import java.util.Map;

class User implements GraphNodeInterface {

	def String email
	def FileSpace fileSpace = new FileSpace()
	
	@Override
	def String toString() {
		return [
			email: email	
		]
	}

	public Map<String, Object> asNodePropertiesMap() {
		return [
			email: email	
		]
	}

	@Override
	public String getNodeIdentifier() {
		return "user_${uuid}";
	}
	
	@Override
	public Collection<String> getLabels() {
		return ["User"]
	}

}
