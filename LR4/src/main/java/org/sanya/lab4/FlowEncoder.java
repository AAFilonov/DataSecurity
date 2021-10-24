package org.sanya.lab4;

import org.sanya.utils.ByteConverter;
import org.sanya.utils.ByteUtils;
import org.sanya.utils.IOUtils;

public class FlowEncoder {
    private String inputFile = "src/main/resources/input.txt";
    private String outputFile = "src/main/resources/output.txt";
    private String inputBytesFile = "src/main/resources/inputBytes.txt";
    private String outputBytesFile = "src/main/resources/outputBytes.txt";

    ByteEncoder encoder;
    ByteConverter converter;

    public FlowEncoder(ByteEncoder encoder, ByteConverter converter) {
        this.encoder = encoder;
        this.converter = converter;
    }

    public void runTranslation() {

        String input_data = IOUtils.read(inputFile);
        System.out.println("Input data:\n"+input_data);

        byte[] input_bytes = converter.toBytes(input_data);
        doLog(ByteUtils.toBinaryString(input_bytes), inputBytesFile, "Input bytes:\n");

        byte[] encoded_bytes = encoder.encode(input_bytes);
        doLog(ByteUtils.toBinaryString(encoded_bytes), outputBytesFile, "Encoded bytes:\n");

        String encoded_string = converter.fromBytes(encoded_bytes);
        doLog(encoded_string, outputFile, "Encoded data:\n");
    }

    private void doLog(String data, String outputFile, String commentary) {
        IOUtils.write(outputFile, data);
        System.out.println(commentary + data);
    }


}
