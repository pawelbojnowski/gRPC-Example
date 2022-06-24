package pl.pb.grpcexample.springbootintegrationserver;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static org.apache.logging.log4j.util.Strings.EMPTY;

import java.util.Map;
import java.util.stream.Stream;
import org.springframework.stereotype.Component;

@Component
public class TextProcessor {

  public static final String SPACE = " ";

  public int getCountOfLetter(String text) {
    return text.trim().replace(SPACE, EMPTY).length();
  }

  public int getTotalCountOfWord(String text) {
    return text.trim().split(SPACE).length;
  }

  public Map<String, Long> getAllCountOfEachLetter(String text) {
    return text.trim().replace(SPACE, EMPTY).chars()
        .mapToObj(e -> (char) e)
        .collect(groupingBy(String::valueOf, counting()));
  }

  public Map<String, Long> getAllCountOfEachWord(String text) {
    return Stream.of(text.trim().split(SPACE))
        .collect(groupingBy(String::valueOf, counting()));
  }
}
