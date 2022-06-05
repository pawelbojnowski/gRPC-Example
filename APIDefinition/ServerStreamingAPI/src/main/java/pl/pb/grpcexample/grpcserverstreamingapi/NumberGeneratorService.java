package pl.pb.grpcexample.grpcserverstreamingapi;


import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.stub.StreamObserver;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class NumberGeneratorService extends NumberGeneratorServiceGrpc.NumberGeneratorServiceImplBase {

  private Random random = new Random();

  @Override
  public void getRandoNumbers(RandoNumbersRequest request, StreamObserver<RandoNumbersResponse> responseObserver) {
    for (int i = 0; i < request.getNumberOfRandomNumbers(); i++) {
      RandoNumbersResponse build = RandoNumbersResponse.newBuilder()
          .setRandomNumber(random.nextInt())
          .build();
      responseObserver.onNext(build);

      Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }
    responseObserver.onCompleted();

  }
}
