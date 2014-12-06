package com.cubbyhole.graphgenerator.domain

trait GraphNodeInterface {

	def String uuid = UUID.randomUUID().toString().replace("-", "_")
	
	public abstract String getNodeIdentifier();
	
	public abstract Collection<String> getLabels();
	
	public abstract Map<String, Object> asNodePropertiesMap()
	
	public String asCypherMap() {
		def Map map = asNodePropertiesMap()
		def cypherMap = "{ "
		map.each {
			cypherMap += "`${it.key}`:"
			if(it.value instanceof Number || it.value instanceof Boolean) {
				cypherMap += it.value.toString()
			}
			else {
				cypherMap += "'${it.value.toString()}'"
			}
		}
		cypherMap += " }"
	}
	
	public String getNodeIdentifierWithLabels() {
		def string = getNodeIdentifier()
		if(getLabels().size() > 0) {
			string += ":" + getLabels().join(":")
		}
		return string
	}
	
}
