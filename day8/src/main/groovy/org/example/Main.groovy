package org.example

class Main {
   static void main(String[] args) {
      println "Hello world!"

      def file = new File("src/main/resources/input.txt")
      def lines = file.readLines()
      def search = "AAA"
      def find = "ZZZ"
      def triedL = false
      def tries = []

      def relevantLine = lines.get(0)
      def found = false
      while(!found) {
      for (int i = 0; i < relevantLine.trim().length(); i++) {
         char c = relevantLine.charAt(i)
         int n = getLine(lines, search)
         def line = lines.get(n + 2)
         if (c == 'L') {
            search = line.substring(line.indexOf("(") + 1, line.indexOf(","))
            println 'L'
         } else if (c == 'R') {
            search = line.substring(line.indexOf(",") + 2, line.indexOf(")"))
            println 'R'
         }

         println search
         println i
         if (search == "ZZZ") {
            println "FOUND"
            println (i + 1)
            break
         }
      }

      //def numMoves = solve(lines)
      //println numMoves
   }

   private static int getLine(ArrayList<String> lines, String search) {
      for (int i = 2; i < lines.size(); i++) {
         def line = lines[i]
         if (line.startsWith(search)) {
            return i
         }
      }
   }


//   private int solve(arr) {
//      for (int = 0; i < file.size(); i++) {
//         if (i == 0 || i == 1 || i == 2) {
//            continue
//         }
//
//         def line = lines[i]
//         if (line.startsWith(search)) {
//            if (!triedL) {
//               def l = line.substring(line.indexOf("(") + 1, line.indexOf(","))
//               println l
//               find = l
//               tries.add("l")
//               triedL = true
//               break
//            } else {
//               def r = line.substring(line.indexOf(",") + 2, line.indexOf(")"))
//               println r
//               find = r
//               tries.add("r")
//               triedL = false
//               break
//            }
//         }
//      }
//   }
}


// 307 too low