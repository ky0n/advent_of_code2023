import java.io.File

// gear ratios third day
fun main(args: Array<String>) {
   println("Hello World!")

   val symbols = listOf("*", "/", "+", "=", "&", "-", "\\",",","[","]","{","}","(",")","<", ">", "*","+","-","=","!","?","^","$","|", '#', '%', '@', '$')
   // Try adding program arguments via Run/Debug configuration.
   var lines = File("src/main/resources/input.txt").readLines()
   println("Program arguments: ${args.joinToString()}")

   var rowsLen = lines.size
   println(rowsLen)
   var columnsLen = lines[0].length
   println(columnsLen)
   var multiDimArray = Array(lines.size) {
      Array(lines[0].length) { it2 ->
      lines[it][it2]
   }}

   var partNums = mutableListOf<Int>()

   fun check(num: Int, numRow: Int, lastDigitPos: Int) {
      var numLength = num.toString().length
      // darunter
      for (k in -1..numLength) {
         println(numRow - 1)
         println(lastDigitPos-k)
         if (lastDigitPos - k in 0..<columnsLen && numRow - 1 >= 0) {
            if (multiDimArray[numRow-1][lastDigitPos-k].toString() in symbols) {
               println("hit $num")
               partNums.add(num)
               return
            }
         }
         // darüber
         if (lastDigitPos - k in 0..<columnsLen && numRow + 1 < rowsLen) {
            if (multiDimArray[numRow+1][lastDigitPos-k].toString() in symbols) {
               println("hit $num")
               partNums.add(num)
               return
            }
         }
      }

      // 1 feld rechts daneben..
      if (numRow in 0..<rowsLen && lastDigitPos + 1 >= 0 && lastDigitPos + 1 < columnsLen) {
         if (multiDimArray[numRow][lastDigitPos + 1].toString() in symbols) {
            println("hit $num")
            partNums.add(num)
            return
         }
      }
      // 1 feld links daneben..
      if (numRow in 0..<rowsLen && lastDigitPos - numLength >= 0 && lastDigitPos - numLength < columnsLen) {
         if (multiDimArray[numRow][lastDigitPos - numLength].toString() in symbols) {
            println("hit $num")
            partNums.add(num)
            return
         }
      }
   }

   for (row in multiDimArray.indices) {
      println("row $row")
      val line = multiDimArray[row]
      var checkableNumber = -1
      for (columnIndex in line.indices) {
         println("col $columnIndex")
         val char = line[columnIndex]
         if (char.isDigit()) {
            if (checkableNumber == -1) {
               checkableNumber = char.toString().toInt()
            } else {
               checkableNumber = (checkableNumber.toString() + char.toString()).toInt()
            }
         } else if (checkableNumber != -1) {
            if (columnIndex == 0) {
               check(checkableNumber, row - 1, columnsLen)
            } else {
               check(checkableNumber, row, columnIndex - 1)
            }
            checkableNumber = -1
         }
      }
   }
   println(partNums.sum())
}