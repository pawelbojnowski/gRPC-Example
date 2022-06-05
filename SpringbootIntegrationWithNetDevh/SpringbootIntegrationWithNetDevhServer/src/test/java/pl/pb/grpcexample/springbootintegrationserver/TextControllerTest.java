package pl.pb.grpcexample.springbootintegrationserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

import io.grpc.internal.testing.StreamRecorder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticRequest;
import pl.pb.grpcexample.springbootintegrationprotocontract.GetStatisticResponse;

@SpringBootTest
@SpringJUnitConfig(classes = {UnitTestConfiguration.class})
class TextControllerTest {

  @Autowired
  private TextController myService;

  @Test
  void shouldReturnStatistic() throws Exception {
    //Given
    GetStatisticRequest request = GetStatisticRequest.newBuilder().setText(" Test text ").build();
    StreamRecorder<GetStatisticResponse> responseObserver = StreamRecorder.create();

    //When
    myService.getStatistic(request, responseObserver);
    if (!responseObserver.awaitCompletion(5, TimeUnit.SECONDS)) {
      fail("The call did not terminate in time");
    }

    //Then
    assertNull(responseObserver.getError());
    List<GetStatisticResponse> results = responseObserver.getValues();
    assertEquals(1, results.size());
    GetStatisticResponse response = results.get(0);

    assertEquals(GetStatisticResponse.newBuilder()
        .setTotalCountOfLetter(8)
        .setTotalCountOfWord(2)
        .putAllCountOfEachLetter(Map.of("s", 1l,
            "t", 3l,
            "T", 1l,
            "e", 2l,
            "x", 1l))
        .putAllCountOfEachWord(Map.of("text", 1l,
            "Test", 1l))
        .build(), response);
  }
}
