package pl.pb.grpcexample.springbootrestserver;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pb.grpcexample.springbootcommons.TextProcessor;

@RestController
public class TextController {

  private TextProcessor textProcessor;

  public TextController(@Autowired TextProcessor textProcessor) {
    this.textProcessor = textProcessor;
  }

  @PostMapping(path = "/getStatistic")
  public GetStatisticResponse getStatistic(@RequestBody GetStatisticRequest request) {
    return GetStatisticResponse.builder()
        .totalCountOfLetter(textProcessor.getCountOfLetter(request.getText()))
        .totalCountOfWord(textProcessor.getTotalCountOfWord(request.getText()))
        .allCountOfEachLetter(textProcessor.getAllCountOfEachLetter(request.getText()))
        .allCountOfEachWord(textProcessor.getAllCountOfEachWord(request.getText()))
        .build();
  }
}
