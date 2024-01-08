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
      def p = 0
      while(!found) {
         for (int i = 0; i < relevantLine.trim().length(); i++) {
            char c = relevantLine.charAt(i)
            int n = getLine(lines, search)
            def line = lines.get(n)
            if (c == 'L') {
               search = line.substring(line.indexOf("(") + 1, line.indexOf(","))
            } else if (c == 'R') {
               search = line.substring(line.indexOf(",") + 2, line.indexOf(")"))
            }

            if (search == "ZZZ") {
               found = true
               println (p + i + 1)
               break
            }
         }
         p += relevantLine.trim().length()
      }

      // second task
      def found2 = false
      def p2 = 0
      def currentLines = new ArrayList<Integer>()
      def searchs = new ArrayList<String>()
      while(!found2) {
         for (int i = 0; i < relevantLine.trim().length(); i++) {
            char c = relevantLine.charAt(i)
            if (currentLines.isEmpty()) {
               currentLines = starterLines(lines)
            } else {
               currentLines = new ArrayList<Integer>()
               for (int j = 0; j < searchs.size(); j++) {
                  int n = getLine(lines, searchs.get(j))
                  currentLines.add()
               }

            }
            def nextLines = new ArrayList<Integer>()
            def nextSearchs = new ArrayList<String>()
            for (int j = 0; j < currentLines.size(); j++) {
               String line = lines.get(currentLines.get(j))
               if (c == 'L') {
                  nextSearchs.add(line.substring(line.indexOf("(") + 1, line.indexOf(",")))
               } else if (c == 'R') {
                  nextSearchs.add(line.substring(line.indexOf(",") + 2, line.indexOf(")")))
               }
            }

            searchs = nextSearchs

            if (searchs.stream().allMatch { it.endsWith("Z") }) {
               found2 = true
               println (p2 + i + 1)
               break
            }

            currentLines = nextLines
            p2 += relevantLine.trim().length()
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

   private static ArrayList<Integer> starterLines(ArrayList<String> lines) {
      def starter = []
      for (int i = 2; i < lines.size(); i++) {
         def line = lines[i]
         if (line.charAt(2) == 'A') {
            starter.add(i)
         }
      }
      return starter
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
// 18112 too low