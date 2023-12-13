import java.io.File

fun main(args: Array<String>) {
   println("Hello World!")

   // Try adding program arguments via Run/Debug configuration.
   // Learn more about running applications: https://www.jetbrains.com/help/idea/running-applications.html.
   println("Program arguments: ${args.joinToString()}")
   var lines = File("src/main/resources/input.txt").readLines()

   var seedList = mutableListOf<Double>()
   var mapping = mutableListOf<Mapping>()
   var mappings = mutableListOf<Mappings>()
   var numOfMappings = 1
   for (i in lines.indices) {
      val line = lines[i].trim()
      if (line.startsWith("seeds: ")) {
         var regex = Regex("\\d+")
         var seeds = line.substringAfter(":").trim()
         seedList = regex.findAll(seeds).map { it.value.toDouble() }.toMutableList()
      }
      var letterRegex = Regex("[a-zA-Z]")
      if (letterRegex.containsMatchIn(line)) {
         continue
      }
      if (line.isEmpty()) {
         if (mapping.isNotEmpty()) {
            mappings.add(Mappings(numOfMappings, mapping.toMutableList()))
            numOfMappings++
            mapping.clear()
         }
      } else {
         var numRegex = Regex("\\d+")
         var nums = numRegex.findAll(line).map { it.value.toDouble() }.toList()
         mapping.add(Mapping(nums[0], nums[1], nums[2]))
      }
      if (i == lines.size - 1) {
         mappings.add(Mappings(numOfMappings, mapping.toMutableList()))
      }
   }

   var locs = mutableListOf<Double>()

   for (seed in seedList) {
      var toMap = seed
      for (mappingSet in mappings) {
         for (i in mappingSet.mappings.indices) {
            val m = mappingSet.mappings[i]
            if (toMap in m.sourceRangeStart..<(m.sourceRangeStart + m.rangeLength)) {
               toMap = m.destinationRangeStart + (toMap - m.sourceRangeStart)
               break
            }
         }
      }
      locs.add(toMap)
   }

   // too low 8913570
   // incorrect 97088268
   // incorrect 961540761
   println(locs.min())
}

data class Mappings(val key: Int, val mappings: MutableList<Mapping>)
data class Mapping(val destinationRangeStart: Double, val sourceRangeStart: Double, val rangeLength: Double)