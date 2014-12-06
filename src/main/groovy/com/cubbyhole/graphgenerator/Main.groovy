package com.cubbyhole.graphgenerator

class Main {

	static main(args) {
		def generator = new Generator()
		generator.generateGraph()
		println generator.graph.getCypherQuery()
	}

}
