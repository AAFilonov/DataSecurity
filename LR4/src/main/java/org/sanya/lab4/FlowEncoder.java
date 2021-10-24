package org.sanya.lab4;

import org.sanya.utils.ByteConverter;
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

        byte[] input_bytes = converter.toBytes(input_data);
        IOUtils.write(inputBytesFile, IOUtils.toBinaryString(input_bytes));

        byte[] encoded_bytes = encoder.encode(input_bytes);
        IOUtils.write(outputBytesFile, IOUtils.toBinaryString(input_bytes));

        String encoded_string = converter.fromBytes(encoded_bytes);
        IOUtils.write(outputFile, encoded_string);

    }
}
