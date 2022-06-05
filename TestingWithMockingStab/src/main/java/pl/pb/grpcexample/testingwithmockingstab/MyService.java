package pl.pb.grpcexample.testingwithmockingstab;

import pl.pb.grpcexample.testingwithmockingstab.ExampleServiceGrpc.ExampleServiceBlockingStub;

public class MyService {

  private final ExampleServiceBlockingStub chatService;

  public MyService(ExampleServiceBlockingStub chatService) {
    this.chatService = chatService;
  }

  public ExampleResponse sendMessage(String message) {
    ExampleRequest build = ExampleRequest.newBuilder().setMessage(message).build();
    return chatService.run(build);
  }
}
