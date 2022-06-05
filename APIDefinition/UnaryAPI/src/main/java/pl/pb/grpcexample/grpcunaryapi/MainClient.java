package pl.pb.grpcexample.grpcunaryapi;

import static pl.pb.grpcexample.grpcunary.CalculatorServiceGrpc.CalculatorServiceBlockingStub;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.List;
import pl.pb.grpcexample.grpcunary.CalculatorServiceGrpc;
import pl.pb.grpcexample.grpcunary.SumOperationRequest;
import pl.pb.grpcexample.grpcunary.SumOperationResponse;

public class MainClient {

  public static void main(String[] args) {

    //Create connection
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 6000)
        .usePlaintext()
        .build();

    CalculatorServiceBlockingStub calculatorServiceBlockingStub = CalculatorServiceGrpc.newBlockingStub(managedChannel);

    //Example stub request
    SumOperationRequest request = SumOperationRequest.newBuilder()
        .addAllNumbers(List.of(1, 2, 3))
        .build();
    SumOperationResponse response = calculatorServiceBlockingStub.sumOperation(request);
    System.out.println("Response: " + response.getResult());


  }

}
