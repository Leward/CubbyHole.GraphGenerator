package com.cubbyhole.graphgenerator.domain

trait Containable<T> {

	def Collection<T> content = []
	
	public Collection<T> getContent() {
		return content
	}
	
	public Collection<T> flatten() {
		def list = content
		content.each {
			if(it instanceof Containable) {
				list += ((Containable) it).flatten()
			}
		}
		return content
	}
	
}
