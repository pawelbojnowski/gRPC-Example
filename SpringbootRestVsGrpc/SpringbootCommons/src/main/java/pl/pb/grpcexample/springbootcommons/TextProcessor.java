package pl.pb.grpcexample.springbootcommons;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.Map;
import java.util.stream.Stream;

public class TextProcessor {

  public static final String SPACE = " ";
  public static final String EMPTY = "";

  public int getCountOfLetter(String text) {
    return text.trim().replace(SPACE, EMPTY).length();
  }

  public int getTotalCountOfWord(String text) {
    return text.trim().split(SPACE).length;
  }

  public Map<String, Long> getAllCountOfEachLetter(String text) {
    return text.trim().replace(SPACE, EMPTY).chars()
        .mapToObj(e -> (char) e)
        .collect(groupingBy(character -> String.valueOf(character), counting()));
  }

  public Map<String, Long> getAllCountOfEachWord(String text) {
    return Stream.of(text.trim().split(SPACE))
        .collect(groupingBy(character -> String.valueOf(character), counting()));
  }
}
