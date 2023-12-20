package org.example

static void main(String[] args) {

   def times = Arrays.asList(48, 93, 84, 66)
   def distances = Arrays.asList(261, 1192, 1019, 1063)

   def numWins = []
   for (int i = 0; i < times.size(); i++) {
      def time = times.get(i)
      def distance = distances.get(i)
      println("Time: " + time + " Distance: " + distance)

      def wins = 0
      for (int j = 0; j < time; j++) {
         def speedPerSecond = j

         def restTime = time - speedPerSecond

         def distanceBoat = restTime * speedPerSecond
         if (distanceBoat >= distance) {
            wins++
         }
      }
      numWins.add(wins)
   }

   println(numWins.stream().reduce { a, b -> a * b})
}