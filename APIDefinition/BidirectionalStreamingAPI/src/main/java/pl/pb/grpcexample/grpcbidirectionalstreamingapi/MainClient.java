package pl.pb.grpcexample.grpcbidirectionalstreamingapi;

import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class MainClient {

  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    ChatServiceGrpc.ChatServiceStub chatServiceStub = ChatServiceGrpc.newStub(managedChannel);

    //Example stub request

    //Define response action
    StreamObserver<AddJoinedUserToChatChatResponse> responseObserver = new StreamObserver<>() {
      @Override
      public void onNext(AddJoinedUserToChatChatResponse response) {
        System.out.println(String.format("Client onNext() with count: %s at %s", response.getCountOfJoinedUser(), new Date()));
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("Client onError(): " + throwable);
      }

      @Override
      public void onCompleted() {
        System.out.println("Client onCompleted(): ");
      }
    };

    StreamObserver<AddJoinedUserToChatChatRequest> aRequestStreamObserver = chatServiceStub.addJoinedUserToChatChat(responseObserver);

    for (int i = 0; i < 10; i++) {
      AddJoinedUserToChatChatRequest request = AddJoinedUserToChatChatRequest.newBuilder().setUserId(i).build();
      //send next request/message
      aRequestStreamObserver.onNext(request);

      Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
    }
    aRequestStreamObserver.onCompleted();


  }

}
