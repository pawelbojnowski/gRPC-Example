package pl.pb.grpcexample.grpcserverstreamingapi;

import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class MainClient {

  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    //Request
    RandoNumbersRequest request = RandoNumbersRequest.newBuilder()
        .setNumberOfRandomNumbers(5)
        .build();

    //Example with blocking stub request
    exampleWithBlockingcStubRequest(managedChannel, request);

    //Example with async stub request
    exampleWithAsyncStubRequest(managedChannel, request);

  }

  private static void exampleWithBlockingcStubRequest(ManagedChannel managedChannel, RandoNumbersRequest request) {
    NumberGeneratorServiceGrpc.NumberGeneratorServiceBlockingStub numberGeneratorServiceBlockingStub = NumberGeneratorServiceGrpc.newBlockingStub(
        managedChannel);

    System.out.println("With using forEachRemaining");
    numberGeneratorServiceBlockingStub.getRandoNumbers(request).forEachRemaining(response -> {
      System.out.println("\tRandom number" + response.getRandomNumber());
    });

    /**
     * OR
     */

    System.out.println("\nOR\n\nWith using hasNext() and next()");
    Iterator<RandoNumbersResponse> randoNumbers = numberGeneratorServiceBlockingStub.getRandoNumbers(request);
    while (randoNumbers.hasNext()) {
      RandoNumbersResponse response = randoNumbers.next();
      System.out.println("\tRandom number" + response.getRandomNumber());
    }
  }

  private static void exampleWithAsyncStubRequest(ManagedChannel managedChannel, RandoNumbersRequest request) {
    NumberGeneratorServiceGrpc.NumberGeneratorServiceStub numberGeneratorServiceStub = NumberGeneratorServiceGrpc.newStub(managedChannel);

    numberGeneratorServiceStub.getRandoNumbers(request, new StreamObserver<RandoNumbersResponse>() {
      @Override
      public void onNext(RandoNumbersResponse response) {
        System.out.println("Client onNext(): " + response.getRandomNumber());
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("Client onError(): " + throwable);
      }

      @Override
      public void onCompleted() {
        System.out.println("Client onCompleted()");
      }
    });

    System.out.println("\nNow we need to wait for response :)\n");
    Uninterruptibles.sleepUninterruptibly(20, TimeUnit.SECONDS);
  }

}
