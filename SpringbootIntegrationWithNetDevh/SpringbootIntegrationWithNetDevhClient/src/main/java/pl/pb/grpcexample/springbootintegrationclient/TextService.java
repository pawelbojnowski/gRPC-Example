package pl.pb.grpcexample.springbootintegrationclient;


import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticRequest;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticResponse;
import pl.pb.grpcexample.springbootintegrationprotocontract.TextServiceGrpc.TextServiceBlockingStub;

@Service
public class TextService {

  @GrpcClient("textService")
  private TextServiceBlockingStub exampleServiceBlockingStub;


  public GetStatisticResponse run() {
    return exampleServiceBlockingStub.getStatistic(GetStatisticRequest.newBuilder().setText("TEST MESSAGE").build());
  }

}