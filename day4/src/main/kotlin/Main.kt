import java.io.File
import kotlin.math.pow

fun main(args: Array<String>) {
   println("Hello World!")

   // Try adding program arguments via Run/Debug configuration.
   // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
   println("Program arguments: ${args.joinToString()}")

   var lines = File("src/main/resources/input.txt").readLines()
   val origNumScratchcards = lines.size
   var sum = 0.0
   var sum2 = 0
   var copyMap = mutableMapOf<Int, Int>()
   for (i in 0..<origNumScratchcards) {
      copyMap[i] = 1
   }

   for (i in lines.indices) {
      val line = lines[i]
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

         for (j in 1..copyMap[i]!!) {
            for (k in 1..hits) {
               copyMap[i + k] = copyMap[i + k]!! + 1
            }
         }
      }
   }

   sum2 = copyMap.values.sum()
   println(sum)

   // too high: 9497017
   println(sum2)

}