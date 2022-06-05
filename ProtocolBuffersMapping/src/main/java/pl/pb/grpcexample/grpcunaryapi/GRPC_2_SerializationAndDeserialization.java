package pl.pb.grpcexample.grpcunaryapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import pl.pb.grpcexample.grpcmapping.HelloGrpc;

public class GRPC_2_SerializationAndDeserialization {

  public static void main(String[] args) throws IOException {

    final HelloGrpc helloGrpc = HelloGrpc.newBuilder()
        .setFirstMessage("Hello")
        .setSecondMessage("gRPC")
        .build();

    System.out.println(helloGrpc);
    /**
     * firstMessage: "Hello"
     * secondMessage: "gRPC"
     */

    // save object to file (See: HelloGrpc.txt)
    final Path path = Paths.get("HelloGrpc.txt");
    Files.write(path, helloGrpc.toByteArray());

    //read object from file
    byte[] bytes = Files.readAllBytes(path);
    HelloGrpc sameHelloGrpc = HelloGrpc.parseFrom(bytes);

    System.out.println(sameHelloGrpc);
    /**
     * firstMessage: "Hello"
     * secondMessage: "gRPC"
     */
    System.out.println("'helloGrpc' and 'sameHelloGrpc' are the same: " + helloGrpc.equals(sameHelloGrpc));
    /**
     * 'helloGrpc' and 'sameHelloGrpc' are the same: true
     */

  }

}
