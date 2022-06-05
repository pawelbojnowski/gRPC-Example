package pl.pb.grpcexample.grpcclientstreamingapi;

import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainClient {

  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    TextServiceGrpc.TextServiceStub textServiceBlockingStub = TextServiceGrpc.newStub(managedChannel);

    StreamObserver<LettersCountResponse> responseObserver = new StreamObserver<>() {
      @Override
      public void onNext(LettersCountResponse lettersCountResponse) {
        lettersCountResponse.getLettersCountsMap()
            .entrySet()
            .forEach(entry -> System.out.println("Client onNext(): " +entry.getKey() + ":" + entry.getValue()));
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("Client onError(): " + throwable);
      }

      @Override
      public void onCompleted() {
        System.out.println("Client onCompleted()");
      }
    };

    StreamObserver<LettersCountRequest> lettersCount = textServiceBlockingStub.getLettersCount(responseObserver);

    List.of("Java", "is", "everywhere").forEach(text -> {
      System.out.println(text);
      LettersCountRequest build = LettersCountRequest.newBuilder().setText(text).build();
      lettersCount.onNext(build);

      Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    });
    lettersCount.onCompleted();

    Uninterruptibles.sleepUninterruptibly(20, TimeUnit.SECONDS);


  }

}
