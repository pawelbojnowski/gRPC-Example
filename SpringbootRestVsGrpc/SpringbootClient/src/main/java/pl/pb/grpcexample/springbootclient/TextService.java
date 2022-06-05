package pl.pb.grpcexample.springbootclient;


import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticRequest;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticResponse;
import pl.pb.grpcexample.springbootintegrationprotocontract.TextServiceGrpc.TextServiceBlockingStub;

@Service
@RequiredArgsConstructor
public class TextService {

  @GrpcClient("textService")
  private TextServiceBlockingStub exampleServiceBlockingStub;

  private final RestTemplate restTemplate;

  public GetStatisticResponse doGrpcRequest(String text) {
    GetStatisticRequest request = GetStatisticRequest.newBuilder().setText(text).build();
    return exampleServiceBlockingStub.getStatistic(request);
  }

  public RestGetStatisticResponse doRestRequest(String s) {
    RestGetStatisticRequest request = RestGetStatisticRequest.builder().text(s).build();
    return restTemplate.postForObject("http://localhost:8080/getStatistic", request, RestGetStatisticResponse.class);

  }
}