package pl.pb.grpcexample.grpcapierrorhandler;


import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pl.pb.grpcexample.grpcunary.DataRequest;
import pl.pb.grpcexample.grpcunary.DataResponse;
import pl.pb.grpcexample.grpcunary.ErrorHandlerServiceGrpc;

public class ErrorHandlerService extends ErrorHandlerServiceGrpc.ErrorHandlerServiceImplBase {

  @Override
  public void dataUnary(DataRequest request, StreamObserver<DataResponse> responseObserver) {
    System.out.println("User sent " + request.getData() + " in request");
    if (request.getData().equals("ERROR")) {
      Status userSentErrorInRequest = Status.INVALID_ARGUMENT.withDescription("User sent ERROR in request");
      responseObserver.onError(userSentErrorInRequest.asRuntimeException());
    } else {
      responseObserver.onNext(DataResponse.newBuilder().setData("OK").build());
      responseObserver.onCompleted();
    }
  }

  @Override
  public void dataServerStreaming(DataRequest request, StreamObserver<DataResponse> responseObserver) {
    System.out.println("User sent " + request.getData() + " in request");
    for (String text : Arrays.asList(request.getData().split(","))) {
      if (text.equals("ERROR")) {
        Status userSentErrorInRequest = Status.INVALID_ARGUMENT.withDescription("User sent ERROR in request");
        responseObserver.onError(userSentErrorInRequest.asRuntimeException());
      } else {
        responseObserver.onNext(DataResponse.newBuilder().setData("OK").build());
      }
    }
    responseObserver.onCompleted();
  }

  @Override
  public StreamObserver<DataRequest> dataClientStreaming(StreamObserver<DataResponse> responseObserver) {
    return new StreamObserver<DataRequest>() {

      List<String> text = new ArrayList<>();

      @Override
      public void onNext(DataRequest dataRequest) {
        System.out.println("onNext(): " + dataRequest);
        text.add(dataRequest.getData());
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onError(): " + throwable);
        text.add(throwable.getMessage());
      }

      @Override
      public void onCompleted() {
        System.out.println("onCompleted()");
        String sentText = text.stream().collect(Collectors.joining(", "));
        responseObserver.onNext(DataResponse.newBuilder().setData(sentText).build());
        responseObserver.onCompleted();
      }
    };
  }

  @Override
  public StreamObserver<DataRequest> dataBidirectionalStreaming(StreamObserver<DataResponse> responseObserver) {
    return new StreamObserver<DataRequest>() {
      @Override
      public void onNext(DataRequest dataRequest) {
        System.out.println("onNext(): " + dataRequest.getData());
        responseObserver.onNext(DataResponse.newBuilder().setData(dataRequest.getData()).build());
      }

      @Override
      public void onError(Throwable throwable) {
        System.out.println("onError(): " + throwable);
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }
    };
  }
}
