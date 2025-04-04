package easv.dk.eventticketsystem.bll;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.UUID;

public class QRCodeManager {



    public static String generateAndSaveQRCode(int width, int height) throws IOException {
        String uniqueCode = UUID.randomUUID().toString();

        String qrDirectory = System.getProperty("user.dir") + "/qr_codes/";
        File dir = new File(qrDirectory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String filePath = qrDirectory + uniqueCode + ".png";

        try {
            generateQRCode(uniqueCode, filePath, width, height);
            System.out.println("Saving QR to: " + filePath);
        } catch (WriterException e) {
            throw new IOException("Failed to generate QR code: " + e.getMessage(), e);
        }

        return uniqueCode; // return the string stored in DB
    }



    public static void generateQRCode(String data, String filePath, int width, int height) throws WriterException, IOException {
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }
}

