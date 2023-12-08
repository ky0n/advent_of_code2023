package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// day 2 advent of code 2023: Cube Conondrum
public class Main {

   private static Map<String, Integer> colorNumber = new HashMap<>();

   private static Map<String, Integer> maxPerColor = Map.of("red", 12, "blue", 14, "green", 13);

   public static void main(String[] args) throws IOException {
      System.out.println("Hello world!");

      var sumOfIds = getSumIdOfGamesWhoWork();
      System.out.println("sum of ids: " + sumOfIds);
   }

   private static int getSumIdOfGamesWhoWork() throws IOException {
      var lines = Files.lines(Path.of(Main.class.getResource("/puzzleInput.txt").getPath()))
            .map(String::trim)
            .toList();

      var possibleGames = new ArrayList<Integer>();
      for (int i = 0; i < lines.size(); i++) {
         var line = lines.get(i).replaceAll(" ", "");
         var gameLine = line.split(":")[1];
         var games = gameLine.split(";");
         Arrays.stream(games).forEach(System.out::println);
         var gamePossible = true;
         for (var game: games) {
            reset();
            var gm = new Game();
            var lastNum = 0;
            for(int j = 0; j < game.length(); j++) {
               String l = String.valueOf(game.charAt(j));
               if (Character.isDigit(game.charAt(j))) {
                  if (lastNum == 0) {
                     lastNum = Integer.parseInt(l);
                  } else {
                     lastNum = Integer.valueOf(String.valueOf(lastNum).concat(l));
                  }
               }
               colorNumber = colorNumber.entrySet().stream().map(entry -> {
                  var l2 = String.valueOf(entry.getKey().charAt(entry.getValue()));
                  if (l.equals(l2)) {
                     entry.setValue(entry.getValue() + 1);
                  }
                  return entry;
               }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

               var foundMatch = colorNumber.entrySet().stream().filter(entry -> entry.getValue() == entry.getKey().length()).findFirst();
               if(foundMatch.isPresent()) {
                  if (foundMatch.get().getKey().equals("red")) {
                     gm.red = lastNum;
                  } else if (foundMatch.get().getKey().equals("blue")) {
                     gm.blue = lastNum;
                  } else if (foundMatch.get().getKey().equals("green")) {
                     gm.green = lastNum;
                  }
                  reset();
                  lastNum = 0;
               }
            }
            if (gm.red > maxPerColor.get("red") || gm.blue > maxPerColor.get("blue") || gm.green > maxPerColor.get("green")) {
               gamePossible = false;
               break;
            }
         }

         System.out.println(gamePossible + " possible: " + line);
         if (gamePossible) {
            possibleGames.add(i + 1);
         }
      }
      return possibleGames.stream().mapToInt(Integer::intValue).sum();
   }

   private static void reset() {
      colorNumber.put("red", 0);
      colorNumber.put("blue", 0);
      colorNumber.put("green", 0);
   }
}

final class Game {
   public Game() {
      this.red = 0;
      this.blue = 0;
      this.green = 0;
   }

   public int red;
   public int blue;
   public int green;
}