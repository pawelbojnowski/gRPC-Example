package pl.pb.grpcexample.springbootintegrationclient;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticResponse;

@SpringBootTest(properties = {
    "grpc.server.inProcessName=test", // Enable inProcess server
    "grpc.server.port=-1", // Disable external server
    "grpc.client.exampleService.address=in-process:test" // Configure the client to connect to the inProcess server
})
@SpringJUnitConfig(classes = {GrpcIntegrationTestConfiguration.class})
@DirtiesContext
class ExampleClientServiceTest {

  @Autowired
  private TextService myComponent;

  @Test
  @DirtiesContext
  void testSayHello() {
    assertThat(myComponent.run()).isEqualTo(GetStatisticResponse.getDefaultInstance());
  }
}