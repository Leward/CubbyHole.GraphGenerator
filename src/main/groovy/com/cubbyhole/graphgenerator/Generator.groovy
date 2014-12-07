package com.cubbyhole.graphgenerator

import com.cubbyhole.graphgenerator.domain.Containable;
import com.cubbyhole.graphgenerator.domain.FileSpaceElement
import com.cubbyhole.graphgenerator.domain.File
import com.cubbyhole.graphgenerator.domain.Folder
import com.cubbyhole.graphgenerator.domain.Graph
import com.cubbyhole.graphgenerator.domain.SharedElement;
import com.cubbyhole.graphgenerator.domain.SharedFile;
import com.cubbyhole.graphgenerator.domain.SharedFolder
import com.cubbyhole.graphgenerator.domain.User
import com.github.javafaker.Faker

class Generator {

	private Faker faker = new Faker()
	GraphConfiguration graphConfig = new GraphConfiguration()
	Graph graph = new Graph()
	
	public Generator() {}
	
	public Generator(GraphConfiguration graphConfig) {
		this.graphConfig = graphConfig
	}
	
	/**
	 * Generate a new graph
	 * @return
	 */
	def generateGraph() {
		graph = new Graph()
		graphConfig.nbUsersToGenerate.times {
			generateUser()
		}
		generateSomeShares(graphConfig.maxSharesPerUser)
	}
	
	def generateUser() {
		def user = new User(email: faker.internet().emailAddress())
		graph.users.add(user)
		generateUserFileSpace(user)
	}
	
	/**
	 * 
	 * @param user
	 * @param foldersDepth
	 * @param maxItemsPerLevel
	 * @return
	 */
	def generateUserFileSpace(User user) {
		generateLevel(user.fileSpace, 1)
	}
	
	/**
	 * 
	 * @param parent
	 * @param level
	 * @param maxLevel
	 * @param maxItemsPerLevel
	 * @return
	 */
	def generateLevel(Containable<FileSpaceElement> parent, int level) {
		if(level > graphConfig.maxFileSpaceDepth) {
			return
		}
		
		def random = new Random()
		def nbItems = random.nextInt(graphConfig.maxItemsPerLevels + 1)
		nbItems.times {
			// Generate the Item
			def FileSpaceElement item
			if(random.nextDouble() > 0.5) {
				// Folder
				item = new Folder()
				boolean goDeeper = random.nextDouble() > 0.5
				if(goDeeper) {
					generateLevel(item, level + 1)
				}
			}
			else {
				// File
				item = new File()
			}
			item.name = faker.lorem().words(1)
			parent.content.add(item)
		}
	}
	
	def generateSomeShares(int maxSharesPerUser = 5) {
		graph.users.each {
			generateSomeShares(it, maxSharesPerUser)
		}
	}
	
	def generateSomeShares(User user, int maxSharesPerUser = 5) {
		def rand = new Random()
		int nbOfShares = rand.nextInt(maxSharesPerUser)
		nbOfShares.times {
			def otherUser = graph.getUserOthenThan(user)
			def itemToShare = user.fileSpace.getRandomFileSpaceElementInTree()
			if(otherUser != null && itemToShare != null) {
				SharedElement sharedItem = itemToShare.makeSharedElement()
				otherUser.fileSpace.content.add(sharedItem)
			}
		}
	}
	
	
	
}
