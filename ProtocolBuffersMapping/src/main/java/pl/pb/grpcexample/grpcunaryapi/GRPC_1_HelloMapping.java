package pl.pb.grpcexample.grpcunaryapi;

import java.util.Arrays;
import pl.pb.grpcexample.grpcmapping.HelloGrpc;

public class GRPC_1_HelloMapping {

  public static void main(String[] args) {

    HelloGrpc build = HelloGrpc.newBuilder()
        .setFirstMessage("Hello")
        .setSecondMessage("gRPC")
        .build();

    System.out.println(build);
    /**
     * firstMessage: "Hello"
     * secondMessage: "gRPC"
     */

    System.out.println(Arrays.toString(build.toByteArray()));
    /**
     * [10, 5, 72, 101, 108, 108, 111, 18, 4, 103, 82, 80, 67]
     */

  }

}
