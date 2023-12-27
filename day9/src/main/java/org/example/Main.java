package org.example;

import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
   public static void main(String[] args) throws IOException {

      var pattern = Pattern.compile("\\d+");

      var lines = Files.lines(Path.of(Main.class.getResource("/input.txt").getPath()))
            .map(String::trim)
            .collect(Collectors.toList());

      BigInteger sum = new BigInteger("0");
      for (var line : lines) {
         var foundSolution = false;
         List<Integer> numbers = new ArrayList<>();
         Matcher matcher = pattern.matcher(line);

         while (matcher.find()) {
            numbers.add(Integer.parseInt(matcher.group()));
         }

         var allNewNums = new ArrayList<ArrayList<Integer>>();
         while(!foundSolution) {
            var nextNums = new ArrayList<Integer>();
            var oldNum = numbers.get(0);
            for (int i = 1; i < numbers.size(); i++) {
               var num = numbers.get(i);
               nextNums.add(num - oldNum);

               oldNum = num;
            }
            allNewNums.add(nextNums);
            if (nextNums.stream().allMatch(n -> n == 0)) {
               
            }

         }

//         sum.add(result);
      }
   }
}