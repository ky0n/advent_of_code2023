package org.example

static void main(String[] args) {
   println "Hello world!"

   def file = new File("src/main/resources/test.txt")
   def lines = file.readLines()
   def search = "AAA"
   def find = "ZZZ"
   def triedL = false
   def tries = []
   while (search != find) {
      for (int i = 0; i < file.size(); i++) {
         if (i == 0 || i == 1 || i == 2) {
            continue
         }

         def line = lines[i]
         if (line.startsWith(search)) {
            if (!triedL) {
               def l = line.substring(line.indexOf("(") + 1, line.indexOf(","))
               println l
               find = l
               tries.add("l")
               triedL = true
               break
            } else {
               def r = line.substring(line.indexOf(",") + 2, line.indexOf(")"))
               println r
               find = r
               tries.add("r")
               triedL = false
               break
            }
         }
      }
   }
}