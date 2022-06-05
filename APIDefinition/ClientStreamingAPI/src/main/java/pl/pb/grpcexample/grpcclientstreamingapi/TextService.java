package pl.pb.grpcexample.grpcclientstreamingapi;


import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TextService extends TextServiceGrpc.TextServiceImplBase {

  @Override
  public StreamObserver<LettersCountRequest> getLettersCount(StreamObserver<LettersCountResponse> responseObserver) {

    return new StreamObserver<LettersCountRequest>() {

      List<String> texts = new ArrayList<>();

      @Override
      public void onNext(LettersCountRequest lettersCountRequest) {
        System.out.println("Server onNext() received: " + lettersCountRequest.getText());
        texts.add(lettersCountRequest.getText());
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("Server onError(): " + throwable);
      }

      @Override
      public void onCompleted() {
        System.out.println("Server onCompleted()");
        Map<String, Long> collect = texts.stream()
            .map(s -> List.of(s.split("")))
            .flatMap(List::stream)
            .collect(groupingBy(identity(), counting()));

        responseObserver.onNext(LettersCountResponse.newBuilder()
            .putAllLettersCounts(collect)
            .build());
        responseObserver.onCompleted();
      }
    };
  }
}
