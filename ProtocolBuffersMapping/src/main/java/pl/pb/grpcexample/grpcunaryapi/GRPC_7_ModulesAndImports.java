package pl.pb.grpcexample.grpcunaryapi;

import pl.pb.grpcexample.grpcmapping.Customer;
import pl.pb.grpcexample.grpcmapping.CustomerDetails;

public class GRPC_7_ModulesAndImports {

  public static void main(String[] args) {

    Customer customer = Customer.newBuilder()
        .setFirstname("Iker")
        .setLastname("Casillas")
        .setUsername("ikercasillas")
        .build();

    CustomerDetails customerDetails = CustomerDetails.newBuilder()
        .setCustome(customer)
        .setPhone("999999999")
        .build();

    System.out.println(customerDetails);
    /**
     * custome {
     *   username: "ikercasillas"
     *   firstname: "Iker"
     *   lastname: "Casillas"
     * }
     * phone: "999999999"
     */

  }

}
