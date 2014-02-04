#An implementation of the KNN algorithm that should be able to
#classify objects with any number of properties as long as the
# properties can be represented numerically.
# As it is now, each property is weighted the same
# I may, in the futrue, add the ability to weight the
# properties differently.
from __future__ import division
import math
import operator
import random
import matplotlib.pyplot as plt

class Node():
    #type of the node
    nodeType = ""
    #this nodes values for each dimension
    values = {}
    #all of this nodes neighbors and the distance to them
    neighbors = {}
    def __init__(self, dictionary, nodeType):
        self.values = dictionary
        self.nodeType = nodeType
    def addNeighbors(self, nodes, minMaxMap):
        for node in nodes:
            if not (node.nodeType == "unknown"):
                distance = self.calcDistance(node, minMaxMap)
                self.neighbors[node] = distance
    def calcDistance(self, node, minMaxMap):
        #in the min max map 0 is the low value and 1 is the high
        maxIndex = 1
        minIndex = 0
        distance = 0
        for dimension in minMaxMap:
            #the range of values for this dimension
            dimensionRange = minMaxMap[dimension][maxIndex] - minMaxMap[dimension][minIndex]
            #the differnce between this nodes value and the neighbors
            delta_dimension = node.values[dimension] - self.values[dimension]
            delta_dimension = delta_dimension/dimensionRange
            #distance is calculated sqrt(d1*d1+d2*d2....)
            #this lets me add all of the values together before I take the square root
            distance += delta_dimension*delta_dimension
        return math.sqrt(distance)
    def makeGuess(self, k):
        #get the smallest k values from neighbors
        kNearest = dict(sorted(self.neighbors.iteritems(), key=operator.itemgetter(1))[:k])
        #a map of types to the number of times they appear in kNearest
        typeCount = {}
        for node in kNearest:
            if node.nodeType in typeCount:
                typeCount[node.nodeType] += 1
            else:
                typeCount[node.nodeType] = 1
        #the value that appears most often for node types
        maxValue = 0
        for nodeType in typeCount:
            if typeCount[nodeType] > maxValue:
                self.nodeType = nodeType
                maxValue = typeCount[nodeType]
            #if the type count for this value is the same as the max type count
            # randomly pick between one of them
            if typeCount[nodeType] == maxValue:
                randomNumber = random.random()
                if randomNumber > .5:
                    self.NodeType = nodeType
class NodeList():
    nodes = []
    k = 0
    # a map of each dimension to a tuple that contains
    # the minimum and maximum values for each dimension
    # used for scaling the dimensions so that
    # larger differences don't have a huge effect
    # on the distance
    minMaxMap = {}
    def __init__(self, k):
        self.k = k
    def addSingleNode(self, node):
        for dimension in node.values:
            #update the min max map if appropriate
            if dimension in self.minMaxMap:
                if node.values[dimension] < self.minMaxMap[dimension][0]:
                    self.minMaxMap[dimension][0] = node.values[dimension]
                if node.values[dimension] > self.minMaxMap[dimension][1]:
                    self.minMaxMap[dimension][1] = node.values[dimension]
            else:
                self.minMaxMap[dimension] = [node.values[dimension],node.values[dimension]]
        self.nodes.extend([node])
    def addListOfNodes(self, nodes):
        for node in nodes:
            self.addSingleNode(node)
    def processNodes(self):
        for node in self.nodes:
            if node.nodeType == "unknown":
                node.addNeighbors(self.nodes,self.minMaxMap)
                node.makeGuess(self.k)
                print(node.values)
                print(node.nodeType)
    def generateRandom(self):
        unknownValues = {}
        #get random values withen each dimensions range
        for dimension in self.minMaxMap:
            unknownValues[dimension] = random.randint(self.minMaxMap[dimension][0],self.minMaxMap[dimension][1])    
        newNode = Node(unknownValues, "unknown")
        return newNode
nodes = [
    Node({"rooms":1, "area":350}, "apartment"),
    Node({"rooms":2, "area":300}, "apartment"),
    Node({"rooms":3, "area":300}, "apartment"),
    Node({"rooms":4, "area":250}, "apartment"),
    Node({"rooms":4, "area":500}, "apartment"),
    Node({"rooms":4, "area":400}, "apartment"),
    Node({"rooms":5, "area":450}, "apartment"),
    Node({"rooms":7, "area":850}, "house"),
    Node({"rooms":7, "area":900}, "house"),
    Node({"rooms":7, "area":1200}, "house"),
    Node({"rooms":8, "area":1500}, "house"),
    Node({"rooms":9, "area":1300}, "house"),
    Node({"rooms":8, "area":1240}, "house"),
    Node({"rooms":10, "area":1700}, "house"),
    Node({"rooms":9, "area":1000}, "house"),
    Node({"rooms":1, "area":800}, "flat"),
    Node({"rooms":3, "area":900}, "flat"),
    Node({"rooms":2, "area":700}, "flat"),
    Node({"rooms":1, "area":900}, "flat"),
    Node({"rooms":2, "area":1150}, "flat"),
    Node({"rooms":1, "area":1000}, "flat"),
    Node({"rooms":2, "area":1200}, "flat"),
    Node({"rooms":1, "area":1300}, "flat")
]

nodeList = NodeList(3)
nodeList.addListOfNodes(nodes)
for node in nodeList.nodes:
    if node.nodeType == "apartment":
        plt.plot(node.values["rooms"],node.values["area"],'ro')
    if node.nodeType == "house":
        plt.plot(node.values["rooms"],node.values["area"],'go')
    if node.nodeType == "flat":
        plt.plot(node.values["rooms"],node.values["area"],'bo')
plt.axis([
    nodeList.minMaxMap["rooms"][0] - 1,
    nodeList.minMaxMap["rooms"][1] + 1,
    nodeList.minMaxMap["area"][0] - 10,
    nodeList.minMaxMap["area"][1] + 1
])
newNode = nodeList.generateRandom()
nodeList.addSingleNode(newNode)
nodeList.processNodes()
plt.plot(newNode.values["rooms"],newNode.values["area"], 'ko')
plt.ylabel('area')
plt.xlabel('rooms')
plt.show()
