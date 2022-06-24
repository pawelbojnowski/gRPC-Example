package pl.pb.grpcexample.grpcunaryapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import java.util.function.Supplier;
import pl.pb.grpcexample.grpcmapping.GrpcObject;

public class GRPC_10_gRPCvsJson {

  public static void main(String[] args) {

    runGRPC();
    /**
     * Total time: 444
     */
    runJSON();
    /**
     * Total time: 1161
     */

  }

  private static void runGRPC() {

    GrpcObject grpcObject = GrpcObject.newBuilder()
        .setTitle("This is a title")
        .setDescription("This is a description")
        .setOneMoreFiled(128)
        .setOtherFiled(true)
        .build();

    run(() -> {
      try {
        byte[] toByte = grpcObject.toByteArray();
        GrpcObject.parseFrom(toByte);
      } catch (InvalidProtocolBufferException e) {
        e.printStackTrace();
      }
      return null;
    });
  }


  private static void runJSON() {

    JsonObject jsonObject = new JsonObject("This is a title", "This is a description", 128, true);
    ObjectMapper objectMapper = new ObjectMapper();

    run(() -> {
      try {
        String toString = objectMapper.writeValueAsString(jsonObject);
        objectMapper.readValue(toString, JsonObject.class);
      } catch (JsonProcessingException e) {
        e.printStackTrace();
      }
      return null;
    });
  }

  private static void run(Supplier consumer) {
    long start = System.currentTimeMillis();
    for (int i = 0; i < 1_000_000; i++) {
      consumer.get();
    }
    long end = System.currentTimeMillis();
    System.out.println("Total time: " + (end - start) + "ms");
  }


  static class JsonObject {

    private String title;
    private String description;
    private int oneMoreFiled;
    private boolean otherFiled;

    public JsonObject() {
    }

    public JsonObject(String title, String description, int oneMoreFiled, boolean otherFiled) {
      this.title = title;
      this.description = description;
      this.oneMoreFiled = oneMoreFiled;
      this.otherFiled = otherFiled;
    }

    public String getTitle() {
      return title;
    }

    public String getDescription() {
      return description;
    }

    public int getOneMoreFiled() {
      return oneMoreFiled;
    }

    public boolean isOtherFiled() {
      return otherFiled;
    }
  }


}
