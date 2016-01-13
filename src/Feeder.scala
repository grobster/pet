class Feeder(var waterLevel: Double, var foodLevel: Double) {
	var amountFoodFeed = 2.0
	var amountWaterFeed = 5.0
	var lowFoodSensor = Sensor("Low Food Sensor", false)
	var emptyFoodSensor = Sensor("No food sensor", false)
	var lowWaterSensor = Sensor("low water sensor", false)
	var emptyWaterSensor = Sensor("no water sensor", false)
	
	def feed(amountFood: Double, amountWater: Double): (Double, Double) = {
		//measure amount to feed
		println(measure)
		Thread.sleep(3000)
		//open door
		println(openDoor)
		Thread.sleep(1000)
		//food drops
		//food door closes
		println(closeDoor)
		foodLevel -= amountFoodFeed
		Thread.sleep(1000)
		//water is measured
		//water is release is opened
		println(openWater)
		Thread.sleep(1000)
		//water released is closed
		println(closeWater)
		Thread.sleep(1000)
		waterLevel -= amountWaterFeed
		monitorLevels
		(1.0, 2.0)
	}
	
	def openDoor = "Food Door Opening"
	def closeDoor = "Food Door Closing"
	def openWater = "Water releasing"
	def closeWater = "Water release stopped"
	def measure = "measuring food"
	def monitorLevels = {
		//if water level is below threshold send alert
		if(waterLevel <= 0.0) sendAlert("Out of Water")
		else if(waterLevel < 5.0) sendAlert("Low Water")
		
		if(foodLevel <= 0.0) sendAlert("Out of Food")
		else if(foodLevel < 5.0) sendAlert("Low Food")
	}
	
	def sendAlert(name: String): Unit = {
		println("sending alert")
		println("WARNING! " + name + " Please refill.")
	}
	
	def refill = {
		waterLevel = 100
		foodLevel = 100
	}
	
}

object Feeder {
	def main(args: Array[String]): Unit = {
		val feeder = new Feeder(100.0, 100.0)
		feeder.feed(1.0, 2.0)
		val testSensor = Sensor("test", false)
	}
}

case class Sensor(name: String, on: Boolean)