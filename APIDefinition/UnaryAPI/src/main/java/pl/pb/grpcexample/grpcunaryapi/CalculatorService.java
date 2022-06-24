package pl.pb.grpcexample.grpcunaryapi;


import io.grpc.stub.StreamObserver;
import pl.pb.grpcexample.grpcunary.CalculatorServiceGrpc;
import pl.pb.grpcexample.grpcunary.SumOperationRequest;
import pl.pb.grpcexample.grpcunary.SumOperationResponse;

public class CalculatorService extends CalculatorServiceGrpc.CalculatorServiceImplBase {

  @Override
  public void sumOperation(SumOperationRequest request, StreamObserver<SumOperationResponse> responseObserver) {
    Integer sum = processSumOperation(request);
    SumOperationResponse response = SumOperationResponse.newBuilder()
        .setResult(sum)
        .build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  private Integer processSumOperation(SumOperationRequest request) {
    return request.getNumbersList().stream().reduce((i1, i2) -> i1 + i2).orElse(0);
  }
}
