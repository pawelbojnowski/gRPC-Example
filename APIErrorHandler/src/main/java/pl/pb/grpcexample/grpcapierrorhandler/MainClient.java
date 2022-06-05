package pl.pb.grpcexample.grpcapierrorhandler;

import com.google.common.util.concurrent.Uninterruptibles;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.concurrent.TimeUnit;
import pl.pb.grpcexample.grpcunary.DataRequest;
import pl.pb.grpcexample.grpcunary.DataResponse;
import pl.pb.grpcexample.grpcunary.ErrorHandlerServiceGrpc;

public class MainClient {

  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    ErrorHandlerServiceGrpc.ErrorHandlerServiceBlockingStub dataServiceBlockingStub = ErrorHandlerServiceGrpc.newBlockingStub(managedChannel);
    ErrorHandlerServiceGrpc.ErrorHandlerServiceStub dataServiceStub = ErrorHandlerServiceGrpc.newStub(managedChannel);

    //Example stub request
    unaryRequest(dataServiceBlockingStub);
    serverStreamingRequest(dataServiceBlockingStub);
    clientStreamingRequest(dataServiceStub);
    bidirectionalStreamingRequest(dataServiceStub);
  }

  private static void unaryRequest(ErrorHandlerServiceGrpc.ErrorHandlerServiceBlockingStub dataServiceBlockingStub) {
    System.out.println("*************************** Unary Request ***************************");
    try {
      dataServiceBlockingStub.dataUnary(DataRequest.newBuilder().setData("ERROR").build());
    } catch (Exception e) {
      System.out.println("Unary request return Exception: " + e);
    }
    /**
     * Response: OK
     * Server streaming request return Exception: io.grpc.StatusRuntimeException: INVALID_ARGUMENT: User sent ERROR in request
     */
    System.out.println("*********************************************************************\n");
  }


  private static void serverStreamingRequest(ErrorHandlerServiceGrpc.ErrorHandlerServiceBlockingStub dataServiceBlockingStub) {
    System.out.println("********************* Server Streaming Request **********************");
    try {
      dataServiceBlockingStub.dataServerStreaming(DataRequest.newBuilder().setData("PROCESSING,ERROR,CLOSE").build())
          .forEachRemaining(dataResponse -> {
            System.out.println("Response: " + dataResponse.getData());
          });
    } catch (Exception e) {
      System.out.println("Server streaming request return Exception: " + e);
    }
    /**
     * Unary request return Exception: io.grpc.StatusRuntimeException: INVALID_ARGUMENT: User sent ERROR in request
     */
    System.out.println("*********************************************************************\n");
  }

  private static void clientStreamingRequest(ErrorHandlerServiceGrpc.ErrorHandlerServiceStub dataServiceStub) {
    System.out.println("****************** Client Streaming Request *************************");
    StreamObserver<DataRequest> dataRequestStreamObserver = dataServiceStub.dataClientStreaming(new StreamObserver<DataResponse>() {
      @Override
      public void onNext(DataResponse dataResponse) {
        System.out.println("onNext(): " + dataResponse.getData());
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onNext(): " + throwable);
      }

      @Override
      public void onCompleted() {
        System.out.println("onCompleted()");
      }
    });

    try {
      //1st request
      dataRequestStreamObserver.onNext(DataRequest.newBuilder().setData("PROCESSING").build());

      //error
      Status userSentErrorInRequest = Status.INVALID_ARGUMENT.withDescription("User sent ERROR in request");
      dataRequestStreamObserver.onError(userSentErrorInRequest.asRuntimeException());

      //3rd request
      dataRequestStreamObserver.onNext(DataRequest.newBuilder().setData("CLOSE").build());

      dataRequestStreamObserver.onCompleted();
      Uninterruptibles.sleepUninterruptibly(20, TimeUnit.SECONDS);


    } catch (Exception e) {
      System.out.println("Client streaming request return Exception: " + e);
    }
    /**
     * Client streaming request return Exception: java.lang.IllegalStateException: Stream was terminated by error, no further calls are allowed
     * onNext(): io.grpc.StatusRuntimeException: CANCELLED: Cancelled by client with StreamObserver.onError()
     */
    System.out.println("*********************************************************************\n");
  }

  private static void bidirectionalStreamingRequest(ErrorHandlerServiceGrpc.ErrorHandlerServiceStub dataServiceStub) {
    System.out.println("************** Bidirectional Streaming Request **********************");
    StreamObserver<DataRequest> dataRequestStreamObserver = dataServiceStub.dataBidirectionalStreaming(new StreamObserver<DataResponse>() {
      @Override
      public void onNext(DataResponse dataResponse) {
        System.out.println("onNext(): " + dataResponse.getData());
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onNext(): " + throwable);
      }

      @Override
      public void onCompleted() {
        System.out.println("onCompleted()");
      }
    });

    try {

      //1st request
      dataRequestStreamObserver.onNext(DataRequest.newBuilder().setData("PROCESSING").build());

      //error
      Status userSentErrorInRequest = Status.INVALID_ARGUMENT.withDescription("User sent ERROR in request");
      dataRequestStreamObserver.onError(userSentErrorInRequest.asRuntimeException());

      //3rd request
      dataRequestStreamObserver.onNext(DataRequest.newBuilder().setData("CLOSE").build());

      dataRequestStreamObserver.onCompleted();
      Uninterruptibles.sleepUninterruptibly(20, TimeUnit.SECONDS);

    } catch (Exception e) {
      System.out.println("Bidirectional streaming request return Exception: " + e);
    }
    System.out.println("*********************************************************************\n");
  }

}
