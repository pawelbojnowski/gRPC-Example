package pl.pb.grpcexample.grpcbidirectionalstreamingapi;


import io.grpc.stub.StreamObserver;
import java.util.Date;

public class ChatService extends ChatServiceGrpc.ChatServiceImplBase {

  private long countOfUser = 0;

  @Override
  public StreamObserver<AddJoinedUserToChatChatRequest> addJoinedUserToChatChat(StreamObserver<AddJoinedUserToChatChatResponse> responseObserver) {

    return new StreamObserver<AddJoinedUserToChatChatRequest>() {

      @Override
      public void onNext(AddJoinedUserToChatChatRequest addJoinedUserToChatChatRequest) {
        System.out.println(String.format("Server onNext() with user id: %s at %s", addJoinedUserToChatChatRequest.getUserId(), new Date()));
        addUser(addJoinedUserToChatChatRequest);
        AddJoinedUserToChatChatResponse response = AddJoinedUserToChatChatResponse.newBuilder().setCountOfJoinedUser(countOfUser).build();
        
        responseObserver.onNext(response);
        //We can send to client more than one message...
//        responseObserver.onNext(response);
//        responseObserver.onNext(response);
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("Server onError(): " + throwable);
      }

      @Override
      public void onCompleted() {
        System.out.println("Server onCompleted()");
      }
    };
  }

  private void addUser(AddJoinedUserToChatChatRequest addJoinedUserToChatChatRequest) {

    /**
     * Let imagine that we do something with usedId from request
     */
    countOfUser++;
  }
}
