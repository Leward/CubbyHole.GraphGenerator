package com.cubbyhole.graphgenerator.domain

class Graph {

	def Collection<User> users = []

	def String getCypherQuery() {
		def string = "CREATE "
		users.each {
			string += "(${it.nodeIdentifierWithLabels} ${it.asCypherMap()})-[:FILESPACE]->($it.fileSpace.nodeIdentifierWithLabels), \n"
			string += cypherQueryForContainable(it.fileSpace)
		}
		string += cypherQueryForShares()
		return string
	}

	/**
	 * 
	 * @param containable
	 * @return
	 */
	private String cypherQueryForContainable(Containable<FileSpaceElement> containable) {
		if(!(containable instanceof GraphNodeInterface)) {
			throw new IllegalArgumentException("Containable must implments GraphNodeInterface to be able to be rendered as Cypher")
		}

		GraphNodeInterface containableAsGraphNode = (GraphNodeInterface) containable

		def cypher = ""
		containable.content.each {
			// Only items that implements GraphNodeInterface can be rendered as Cypher
			if((it instanceof GraphNodeInterface)) {
				GraphNodeInterface itemAsGraphNode = (GraphNodeInterface) it
				cypher += "(${containableAsGraphNode.getNodeIdentifier()})-[:CONTAINS]->(${itemAsGraphNode.getNodeIdentifierWithLabels()} ${itemAsGraphNode.asCypherMap()}), \n"
				if(it instanceof Containable) {
					cypher += cypherQueryForContainable(it)
				}
			}
		}
		return cypher
	}

	private String cypherQueryForShares() {
		def cypher = "" 
		users.each {
			def sharedElements = it.fileSpace.getSharedElementsInTree()
			sharedElements.each {
				GraphNodeInterface sharedElementAsGraphNode = (GraphNodeInterface) it
				GraphNodeInterface originalAsGraphNode = (GraphNodeInterface) it.original
				cypher += "(${originalAsGraphNode.getNodeIdentifier()})-[:SHARE]->(${sharedElementAsGraphNode.getNodeIdentifier()}), \n"
			}
		}
		return cypher
	}

	/**
	 * 
	 * @param user
	 * @return
	 */
	public User getUserOthenThan(User user) {
		User otherUser = null
		def random = new Random()
		while(otherUser == null) {
			otherUser = users[random.nextInt(users.size())]
			if(user == otherUser) {
				otherUser = null
			}
		}
		return otherUser
	}
}
