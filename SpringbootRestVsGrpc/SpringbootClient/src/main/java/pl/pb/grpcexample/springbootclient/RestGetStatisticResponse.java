package pl.pb.grpcexample.springbootclient;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestGetStatisticResponse {

  private long totalCountOfLetter;
  private long totalCountOfWord;
  private Map<String, Long> allCountOfEachLetter;
  private Map<String, Long> allCountOfEachWord;
}
