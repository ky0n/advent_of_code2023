package org.example;

// Advent of Code Day 1 -////-

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
   public static void main(String[] args) throws IOException {
      System.out.println("Hello world!");

      var trebuchetCalibration = parseInputFile();
      System.out.println("Trebuchet calibration for the elves: " + trebuchetCalibration);
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
}