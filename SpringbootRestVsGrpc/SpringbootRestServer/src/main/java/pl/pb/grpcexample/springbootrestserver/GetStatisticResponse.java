package pl.pb.grpcexample.springbootrestserver;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStatisticResponse {

  private long totalCountOfLetter;
  private long totalCountOfWord;
  private Map<String, Long> allCountOfEachLetter;
  private Map<String, Long> allCountOfEachWord;
}
