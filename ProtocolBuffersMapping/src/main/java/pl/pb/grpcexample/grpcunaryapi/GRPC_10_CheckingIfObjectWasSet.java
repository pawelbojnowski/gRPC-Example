package pl.pb.grpcexample.grpcunaryapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.protobuf.InvalidProtocolBufferException;
import pl.pb.grpcexample.grpcmapping.Car;
import pl.pb.grpcexample.grpcmapping.Engine;

public class GRPC_10_CheckingIfObjectWasSet {

  public static void main(String[] args) throws InvalidProtocolBufferException, JsonProcessingException {

    Engine engine = Engine.newBuilder().getDefaultInstanceForType();

    Car carWithEngine = Car.newBuilder()
        .setBrand("BMW")
        .setModel("G30")
        .setEngine(engine)
        .build();

    System.out.println("\nCar has engine:" + carWithEngine.hasEngine());
    /**
     * Car has engine:true
     */

    Car carWithoutEngine = Car.newBuilder()
        .setBrand("BMW")
        .setModel("G30")
        .build();

    System.out.println("Car has engine:" + carWithoutEngine.hasEngine());
    /**
     * Car has engine:false
     */

  }


}
