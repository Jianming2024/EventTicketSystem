package easv.dk.eventticketsystem.gui.controllers;


import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import easv.dk.eventticketsystem.be.TicketOnOrder;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;


public class TicketController2 {


    @FXML private Label lblEventName;
//    @FXML private Label lblCustomerName;
    @FXML private Label lblDate;
    @FXML private Label lblTime;
    @FXML private Label lblPrice;
    @FXML private Label lblLocation;
    @FXML private Label lblQuantity;
    @FXML private ImageView qrCodeImageView;
    @FXML Button btnPrintPDF;



    @FXML
    public void initialize() {

        System.out.println("Super duper cool ticket displaying!");
    }
    public void setTicketData(TicketOnOrder ticket, String qrFilePath) {

        lblEventName.setText(ticket.getEventName());
//        lblCustomerName.setText(ticket.getCustomerName());  Uncomment if adding the customer name is needed

        lblDate.setText(ticket.getEventDate());
        lblTime.setText(ticket.getEventTime());
//        lblPrice.setText(ticket.getPrice());
        lblLocation.setText(ticket.getLocation());
//        lblQuantity.setText("Qty: " +ticket.getQuantity());

        // Set QR image
        File qrFile = new File(qrFilePath);
        if (qrFile.exists()) {
            Image qrImage = new Image(qrFile.toURI().toString());
            qrCodeImageView.setImage(qrImage);
            System.out.println("QR sucessfully loaded from: " + qrFilePath);

        } else {
            System.out.println("QR code image not found at: " + qrFilePath);
        }
    }
    @FXML
    private void onPrintToPDFClick() {
        try {
            btnPrintPDF.setVisible(false); // Hide print button

            //Chooses where to save the PDF = "Save as" function
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save Ticket PDF");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));

            File file = fileChooser.showSaveDialog(null);

            System.out.println("✅ Ticket PDF generated!");

            if (file == null) return;

            // Snapshot the AnchorPane or qrCodeImageView depending on what you want
            javafx.scene.image.WritableImage snapshot = qrCodeImageView.getScene().snapshot(null);

            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(snapshot, null);
            File tempImage = File.createTempFile("ticket", ".png");
            ImageIO.write(bufferedImage, "png", tempImage);
            // iText PDF logic
            Document document = new Document(PageSize.A4.rotate()); //newdoc + rotated
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();


            com.itextpdf.text.Image pdfImg = com.itextpdf.text.Image.getInstance(tempImage.getAbsolutePath());
            pdfImg.scaleToFit(800, 600); // Set size of image inside pdf

            // Centering logic
            float x = (PageSize.A4.getHeight() - pdfImg.getScaledWidth()) / 2;
            float y = (PageSize.A4.getWidth() - pdfImg.getScaledHeight()) / 2;
            pdfImg.setAbsolutePosition(x, y);
            document.add(pdfImg);
            document.close();

            System.out.println("✅ Ticket exported to PDF: " + file.getAbsolutePath());

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("❌ Failed to export PDF: " + e.getMessage());
        }
        finally {
            btnPrintPDF.setVisible(true);
        }

    }

}
