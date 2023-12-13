import java.io.File
import kotlin.math.pow

fun main(args: Array<String>) {
   println("Hello World!")

   // Try adding program arguments via Run/Debug configuration.
   // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
   println("Program arguments: ${args.joinToString()}")

   var lines = File("src/main/resources/input.txt").readLines()
   var sum = 0.0
   for (line in lines) {
      val allValue = line.substringAfter(":").trim()
      val ownValues = allValue.substringBefore("|").trim()
      val drawnValues = allValue.substringAfter("|").trim()

      val regex = Regex("\\d+")
      val ownNums = regex.findAll(ownValues).map { it.value.toInt() }.toSet()
      val drawnNums = regex.findAll(drawnValues).map { it.value.toInt() }.toSet()

      val hits = ownNums.intersect(drawnNums).count()
      if (hits > 0) {
         var inter = 2.0.pow((hits - 1).toDouble())
         sum += inter
      }
   }

   println(sum)
}