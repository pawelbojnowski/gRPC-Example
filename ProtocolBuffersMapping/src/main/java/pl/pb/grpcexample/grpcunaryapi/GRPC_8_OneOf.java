package pl.pb.grpcexample.grpcunaryapi;

import pl.pb.grpcexample.grpcmapping.ConnectionConfiguration;
import pl.pb.grpcexample.grpcmapping.GRPCConnectionConfiguration;
import pl.pb.grpcexample.grpcmapping.RestConnectionConfiguration;

public class GRPC_8_OneOf {

  public static void main(String[] args) {

    RestConnectionConfiguration restConfig = RestConnectionConfiguration.newBuilder()
        .setHost("http://example.com")
        .setPort("80")
        .build();

    GRPCConnectionConfiguration grpcConfig = GRPCConnectionConfiguration.newBuilder()
        .setContractUrl("http://example.com:5000/contact")
        .setHost("http://example.com")
        .setPort("80")
        .build();

    ConnectionConfiguration config1 = ConnectionConfiguration.newBuilder()
        .setRestConnectionConfiguration(restConfig)
        .setGrpcConnectionConfiguration(grpcConfig) //<----
        .build();

    System.out.println(config1);
    /**
     * grpcConnectionConfiguration {
     *   host: "http://example.com"
     *   Port: "80"
     *   contractUrl: "http://example.com:5000/contact"
     * }
     */

    System.out.println(config1.getConfigCase());
    /**
     * GRPCCONNECTIONCONFIGURATION
     */

    ConnectionConfiguration config2 = ConnectionConfiguration.newBuilder()
        .setGrpcConnectionConfiguration(grpcConfig)
        .setRestConnectionConfiguration(restConfig) //<----
        .build();

    System.out.println(config2);
    /**
     *   restConnectionConfiguration {
     *             host: "http://example.com"
     *             Port: "80"
     *         }
     */

    System.out.println(config2.getConfigCase());
    /**
     * RESTCONNECTIONCONFIGURATION
     */
  }

}
