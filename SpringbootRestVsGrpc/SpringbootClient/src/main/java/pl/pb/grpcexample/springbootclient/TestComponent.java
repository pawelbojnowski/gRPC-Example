package pl.pb.grpcexample.springbootclient;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TestComponent {

  private final TextService textService;

  @Scheduled(fixedRate = 5000)
  public void run() {
    //Generate random text (UUID without '-')
    List<String> listOfRandomText = IntStream.range(0, 1024)
        .mapToObj(value -> UUID.randomUUID().toString().replace("-", " "))
        .collect(Collectors.toList());

    //do requests with REST and gRPC
    long restProcessedTime = doRequest("REST", listOfRandomText, text -> textService.doRestRequest(text));
    long gRPCProcessedTime = doRequest("gRPC", listOfRandomText, text -> textService.doGrpcRequest(text));

    //let's check which type of communication have won ;)
    if (restProcessedTime == gRPCProcessedTime) {
      System.out.println("The same time");
    } else if (restProcessedTime < gRPCProcessedTime) {
      System.out.println("Rest WIN ! ! !");
    } else {
      System.out.println("gRPC WIN ! ! !");
    }
    System.out.println();
  }

  private long doRequest(String rest, List<String> listOfText, Function<String, Object> doRequest) {
    long start = System.currentTimeMillis();
    listOfText.forEach(s -> {
      Object response = doRequest.apply(s);
    });
    long end = System.currentTimeMillis();

    long timeIsMiliseconds = end - start;
    System.out.println("Texts was processed with " + rest + " communication in: " + timeIsMiliseconds + " ms");
    return timeIsMiliseconds;
  }

}
