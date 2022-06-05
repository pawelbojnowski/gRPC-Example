package pl.pb.grpcexample.grpcunaryapi;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.util.Arrays;
import pl.pb.grpcexample.grpcmapping.Types;

public class GRPC_3_Types {

  public static void main(String[] args) throws IOException {
    byte bytesArray[] = {98, 121, 116, 101, 115};
    final Types types = Types.newBuilder()
        .setIntValue(1)
        .setLongValue(2L)
        .setFloatValue(3.4f)
        .setDoubleValue(5.6d)
        .setBoolValue(true)
        .setStringValue("Types")
        .setBytesValue(ByteString.copyFrom(bytesArray))
        .build();

    System.out.println(types);
    /**
     * int_value: 1
     * long_value: 2
     * float_value: 3.4
     * double_value: 5.6
     * bool_value: true
     * string_value: "Types"
     * bytes_value: "bytes"
     */

    //------------------Default Values------------------------------------------------------------------------------

    final Types typesDefaultValues = Types.getDefaultInstance();

    // method for toSting() for object typesDefaultValues will be empty
    System.out.println(typesDefaultValues);
    System.out.println("typesDefaultValues.toString().isEmpty(): " + typesDefaultValues.toString().isEmpty());
    System.out.println("BUT...");
    System.out.println("typesDefaultValues.getIntValue(): " + typesDefaultValues.getIntValue());
    System.out.println("typesDefaultValues.getLongValue(): " + typesDefaultValues.getLongValue());
    System.out.println("typesDefaultValues.getFloatValue(): " + typesDefaultValues.getFloatValue());
    System.out.println("typesDefaultValues.getDoubleValue(): " + typesDefaultValues.getDoubleValue());
    System.out.println("typesDefaultValues.getBoolValue(): " + typesDefaultValues.getBoolValue());
    System.out.println("typesDefaultValues.getStringValue(): " + typesDefaultValues.getStringValue());
    System.out.println("typesDefaultValues.getBytesValue().toByteArray(): " + Arrays.toString(typesDefaultValues.getBytesValue().toByteArray()));
    /**
     * typesDefaultValues.toString().isEmpty(): true
     * BUT...
     * typesDefaultValues.getIntValue(): 0
     * typesDefaultValues.getLongValue(): 0
     * typesDefaultValues.getFloatValue(): 0.0
     * typesDefaultValues.getDoubleValue(): 0.0
     * typesDefaultValues.getBoolValue(): false
     * typesDefaultValues.getStringValue():
     * typesDefaultValues.getBytesValue().toByteArray(): []
     */
  }

}
