package easv.dk.eventticketsystem.bll;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

public class UUIDGenerator {
    private String GenerateUUID(){
        try {
            // Generate a random UUID
            String uuid = UUID.randomUUID().toString();
            System.out.println("Generated UUID: " + uuid);

            // Set file path for the QR Code image
            String filePath = "uuid_qr.png";

            // Generate and save the QR Code
            generateQRCode(uuid, filePath, 300, 300);
            System.out.println("QR Code generated and saved as: " + filePath);
            System.out.println("⚠️ Saving QR Code to: " + filePath);
            return filePath; // Return the file path
        } catch (Exception e) {
            System.err.println("❌ Failed to save QR code!");
            e.printStackTrace();
        }
        return null;
    }


    public String startQRCodeGeneration() {
        return GenerateUUID();
    }

    public static void generateQRCode(String data, String filePath, int width, int height) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }
}

