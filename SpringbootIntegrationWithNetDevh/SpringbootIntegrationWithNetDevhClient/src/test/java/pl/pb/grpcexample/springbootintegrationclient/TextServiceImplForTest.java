package pl.pb.grpcexample.springbootintegrationclient;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticRequest;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticResponse;
import pl.pb.grpcexample.springbootintegrationprotocontract.TextServiceGrpc;

@GrpcService
public class TextServiceImplForTest extends TextServiceGrpc.TextServiceImplBase {

  @Override
  public void getStatistic(GetStatisticRequest request, StreamObserver<GetStatisticResponse> responseObserver) {
    responseObserver.onNext(GetStatisticResponse.newBuilder().getDefaultInstanceForType());
    responseObserver.onCompleted();
  }
}
