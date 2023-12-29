package org.example;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
   public static void main(String[] args) throws IOException {

      var pattern = Pattern.compile("-?\\d+");

      var lines = Files.lines(Path.of(Main.class.getResource("/input.txt").getPath()))
            .map(String::trim)
            .collect(Collectors.toList());

      BigInteger sum = new BigInteger("0");
      for (var line : lines) {
         ArrayList<Integer> numbers = new ArrayList<>();
         Matcher matcher = pattern.matcher(line);

         while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
         }

         var allNewNums = new ArrayList<ArrayList<Integer>>();
         var nextNums = new ArrayList<Integer>();
         var oldNum = numbers.get(0);

         allNewNums.add(numbers);
         var foundSolution = false;
         while (!foundSolution) {
            nextNums.clear();
            for (int i = 1; i < allNewNums.get(allNewNums.size() - 1).size(); i++) {
               var num = allNewNums.get(allNewNums.size() - 1).get(i);
               nextNums.add(num - oldNum);
               oldNum = num;
            }
            allNewNums.add(new ArrayList<>(nextNums));
            if (!nextNums.isEmpty()) {
               oldNum = nextNums.get(0);
            }

            if (nextNums.stream().allMatch(n -> n == 0)) {
               foundSolution = true;
               nextNums.add(0);
               var lastNum = 0;
               int lineSolution;
               for (int j = allNewNums.size() - 2; j >= 0; j--) {
                  var l = allNewNums.get(j);
                  var newNum = l.get(l.size() - 1) + lastNum;
                  l.add(newNum);
                  lastNum = newNum;
               }
               lineSolution = allNewNums.get(0).get(allNewNums.get(0).size() - 1);
               sum = sum.add(BigInteger.valueOf(lineSolution));
            }
         }
      }
      System.out.println("sum " + sum);

      // 1079128959 too low
      // 2022497258 too high
   }
}