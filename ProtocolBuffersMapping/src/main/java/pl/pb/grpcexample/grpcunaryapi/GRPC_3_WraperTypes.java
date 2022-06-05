package pl.pb.grpcexample.grpcunaryapi;

import com.google.protobuf.BoolValue;
import com.google.protobuf.ByteString;
import com.google.protobuf.BytesValue;
import com.google.protobuf.DoubleValue;
import com.google.protobuf.FloatValue;
import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import java.io.IOException;
import java.util.Arrays;
import pl.pb.grpcexample.grpcmapping.WrappersTypes;

public class GRPC_3_WraperTypes {

  public static void main(String[] args) throws IOException {
    byte bytesArray[] = {98, 121, 116, 101, 115};
    final WrappersTypes wrappersTypes = WrappersTypes.newBuilder()
        .setIntValue(Int32Value.of(1))
        .setLongValue(Int64Value.of(2L))
        .setFloatValue(FloatValue.of(3.4f))
        .setDoubleValue(DoubleValue.of(5.6d))
        .setBoolValue(BoolValue.of(true))
        .setStringValue(StringValue.of("Types"))
        .setBytesValue(BytesValue.of(ByteString.copyFrom(bytesArray)))
        .build();

    System.out.println(wrappersTypes);
    /**
     * int_value {
     *   value: 1
     * }
     * long_value {
     *   value: 2
     * }
     * float_value {
     *   value: 3.4
     * }
     * double_value {
     *   value: 5.6
     * }
     * bool_value {
     *   value: true
     * }
     * string_value {
     *   value: "Types"
     * }
     * bytes_value {
     *   value: "bytes"
     * }
     */

    final WrappersTypes typesDefaultValues = WrappersTypes.getDefaultInstance();

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
     *
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
