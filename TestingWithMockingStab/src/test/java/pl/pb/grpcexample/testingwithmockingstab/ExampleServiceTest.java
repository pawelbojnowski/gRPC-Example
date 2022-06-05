package pl.pb.grpcexample.testingwithmockingstab;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.pb.grpcexample.testingwithmockingstab.ExampleServiceGrpc.ExampleServiceBlockingStub;

class ExampleServiceTest {

  private ExampleServiceBlockingStub chatService;
  private MyService myService;

  @BeforeEach
  void setup() {
    chatService = Mockito.mock(ExampleServiceBlockingStub.class);
    myService = new MyService(chatService);
  }

  @Test
  void testSendMessage() {
    //Given
    /**
     * To Mock gRPC Stub is needed this dependency
     * <dependency>
     *    <groupId>org.mockito</groupId>
     *    <artifactId>mockito-inline</artifactId>
     *    <scope>test</scope>
     * </dependency>
     */
    Mockito.when(chatService.run(any())).thenReturn(ExampleResponse.newBuilder().setMessage("MOCKED TEST RESPONSE").build());

    //When
    ExampleResponse result = myService.sendMessage("This Is My Message");

    //Then
    Assertions.assertEquals("MOCKED TEST RESPONSE", result.getMessage());
  }
}