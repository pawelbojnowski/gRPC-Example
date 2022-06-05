package pl.pb.grpcexample.springbootgrpcserver;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pb.grpcexample.springbootcommons.TextProcessor;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticRequest;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticResponse;
import pl.pb.grpcexample.springbootintegrationprotocontract.TextServiceGrpc;

@GrpcService
public class TextController extends TextServiceGrpc.TextServiceImplBase {


  private TextProcessor textProcessor;

  public TextController(@Autowired TextProcessor textProcessor) {
    this.textProcessor = textProcessor;
  }

  @Override
  public void getStatistic(GetStatisticRequest request, StreamObserver<GetStatisticResponse> responseObserver) {
    GetStatisticResponse response = GetStatisticResponse.newBuilder()
        .setTotalCountOfLetter(textProcessor.getCountOfLetter(request.getText()))
        .setTotalCountOfWord(textProcessor.getTotalCountOfWord(request.getText()))
        .putAllCountOfEachLetter(textProcessor.getAllCountOfEachLetter(request.getText()))
        .putAllCountOfEachWord(textProcessor.getAllCountOfEachWord(request.getText()))
        .build();
    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }
}
