package org.example;

// Advent of Code Day 1 -////-

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

   private static Map<String, Integer> numberStrings = Map.of("one", 1, "two", 2, "three", 3, "four", 4, "five", 5, "six", 6, "seven", 7, "eight", 8, "nine", 9);
   private static Map<String, Integer> wordsCurrentMatchingForward = new HashMap<>();
   private static Map<String, Integer> wordsCurrentMatchingBackward = new HashMap<>();

   public static void main(String[] args) throws IOException {
      System.out.println("Hello world!");

      var trebuchetCalibration = parseInputFile();
      System.out.println("Trebuchet calibration for the elves: " + trebuchetCalibration);

      var trebuchetCalibration2 = parseInputfileWithDigitWords();
      System.out.println("Second Trebuchet calibration for the elves: " + trebuchetCalibration2);
   }

   private static Integer parseInputFile() throws IOException {
      var pattern = Pattern.compile("\\d");

      var lines = Files.lines(Path.of(Main.class.getResource("/input.txt").getPath()))
            .map(String::trim)
            .collect(Collectors.toList());

      Integer sum = 0;
      for(var line : lines) {
         String first = null;
         String last = null;
         for (int i = 0; i < line.length(); i++) {
            if (first == null && pattern.matcher(String.valueOf(line.charAt(i))).matches()) {
               first = String.valueOf(line.charAt(i));
            }
            if (last == null && pattern.matcher(String.valueOf(line.charAt(line.length() - 1 - i))).matches()) {
               last = String.valueOf(line.charAt(line.length() - 1 - i));
            }
         }
         if (first != null && last != null) {
            var twoDigits = Integer.parseInt(first + last);
            sum += twoDigits;
         }
      }

      return sum;
   }


   private static Integer parseInputfileWithDigitWords() throws IOException {
      Integer sum = 0;
      var pattern = Pattern.compile("\\d");
      var lines = Files.lines(Path.of(Main.class.getResource("/input2.txt").getPath()))
            .map(String::trim)
            .collect(Collectors.toList());

      for(var line : lines) {
         resets(wordsCurrentMatchingForward);
         resets(wordsCurrentMatchingBackward);
         String first = null;
         String last = null;
         for (int i = 0; i < line.length(); i++) {
            if (first == null) {
               var matches = forwardMatching(line.charAt(i));
               if (matches.isPresent()) {
                  first = String.valueOf(matches.get());
               } else if (pattern.matcher(String.valueOf(line.charAt(i))).matches()) {
                  first = String.valueOf(line.charAt(i));
               }
            }
            if (last == null) {
               var matches = backwardsMatching(line.charAt(line.length() - 1 - i));
               if (matches.isPresent()) {
                  last = String.valueOf(matches.get());
               } else if (pattern.matcher(String.valueOf(line.charAt(line.length() - 1 - i))).matches()) {
                  last = String.valueOf(line.charAt(line.length() - 1 - i));
               }
            }
         }
         if (first != null && last != null) {
            System.out.println("First: " + first + " Last: " + last);
            var twoDigits = Integer.parseInt(first + last);
            sum += twoDigits;
         }
      }

      return sum;
   }

   private static Optional<Integer> forwardMatching(char letter) {
      wordsCurrentMatchingForward = wordsCurrentMatchingForward.entrySet().stream().map(
            entry -> {
               if (entry.getKey().charAt(entry.getValue()) == letter) {
                  entry.setValue(entry.getValue() + 1);
               } else if (entry.getKey().charAt(0) == letter) {
                  entry.setValue(1);
               } else {
                  entry.setValue(0);
               }
               return entry;
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      var matchedEntry = wordsCurrentMatchingForward.entrySet().stream().filter(entry -> entry.getValue() == entry.getKey().length()).findFirst();
      if (matchedEntry.isPresent()) {
         var matchedInt = numberStrings.entrySet().stream().filter(entry -> entry.getKey().equals(matchedEntry.get().getKey())).findFirst().orElseThrow().getValue();
         resets(wordsCurrentMatchingForward);
         return Optional.of(matchedInt);
      }
      return Optional.empty();
   }

   private static Optional<Integer> backwardsMatching(char letter) {
      wordsCurrentMatchingBackward = wordsCurrentMatchingBackward.entrySet().stream().peek(
            entry -> {
               if (entry.getKey().charAt(entry.getKey().length() - 1 - entry.getValue()) == letter) {
                  entry.setValue(entry.getValue() + 1);
               } else if (entry.getKey().charAt(entry.getKey().length() - 1) == letter) {
                  entry.setValue(1);
               } else {
                  entry.setValue(0);
               }
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
      var matchedEntry = wordsCurrentMatchingBackward.entrySet().stream().filter(entry -> entry.getValue() == entry.getKey().length()).findFirst();
      if (matchedEntry.isPresent()) {
         var matchedInt = numberStrings.entrySet().stream().filter(entry -> entry.getKey().equals(matchedEntry.get().getKey())).findFirst().orElseThrow().getValue();
         resets(wordsCurrentMatchingBackward);
         return Optional.of(matchedInt);
      }
      return Optional.empty();
   }

   private static void resets(Map<String, Integer> map) {
      numberStrings.forEach((key, value) -> map.put(key, 0));
   }
}