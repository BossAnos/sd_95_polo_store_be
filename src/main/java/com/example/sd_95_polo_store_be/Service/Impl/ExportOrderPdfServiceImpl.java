package com.example.sd_95_polo_store_be.Service.Impl;

import com.example.sd_95_polo_store_be.Model.Response.OrderDetailPdfResponse;
import com.example.sd_95_polo_store_be.Model.Response.OrderDetailResponse;
import com.example.sd_95_polo_store_be.Model.Response.OrderPdfResponse;
import com.example.sd_95_polo_store_be.Repository.OrderDetailRepository;
import com.example.sd_95_polo_store_be.Repository.OrderRepository;
import com.example.sd_95_polo_store_be.Service.ExportOrderPdfService;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.pdf.BaseFont;

import java.io.File;
import com.itextpdf.text.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ExportOrderPdfServiceImpl implements ExportOrderPdfService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;

    private static BaseFont times;

    static {
        try {
            times = BaseFont.createFont("src/main/resources/font/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Font titleFont = new Font(times, 16, Font.BOLDITALIC);
    private static Font subTitleFont = new Font(times, 13, Font.BOLDITALIC);
    private static Font contentFont = new Font(times, 10, Font.NORMAL);
    private static Font headerTableFont = new Font(times, 9, Font.BOLDITALIC);
    private static Font contentTableFont = new Font(times, 10, Font.NORMAL);
    private static Font contenFooterFont = new Font(times, 11, Font.BOLDITALIC);

    private static Font contentNoteFont = new Font(times, 5, Font.NORMAL);

    @Override
    public Document OrderPdfExport(Integer id, HttpServletResponse response) {
        OrderPdfResponse orderPdf = orderRepository.getOrderByOrderId(id).orElseThrow();
        List<OrderDetailPdfResponse> orderDetailPdf = orderDetailRepository.getListOrderPdf(id);
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        Document document = new Document(PageSize.A6, 20, 10, 0, 0);
        try {
            // Tạo đối tượng PdfWriter
            File file = new File("./ccccc.pdf");


            if (!file.exists()) {
                file.createNewFile();
            }
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

            try {
                PdfWriter.getInstance(document, response.getOutputStream());
            }catch (DocumentException | IOException e){
                e.printStackTrace();
                throw new RuntimeException("Get pdf document fail");
            }

            // Mở file để thực hiện ghi
            document.open();
            // Thêm nội dung sử dụng add function
            String logoPath = "src/main/resources/static/PoloStore.png";
            Image logoImage = Image.getInstance(logoPath);

            logoImage.scaleToFit(150, 150); // Thay đổi kích thước logo nếu cần thiết
            logoImage.setAlignment(Element.ALIGN_CENTER); // Thiết lập vị trí logo trên tra
            document.add(logoImage);


            Paragraph addressShop = new Paragraph("Địa chỉ shop : Giao Long, Giao Thủy, Nam Định", contentFont);

            document.add(addressShop);
            Paragraph phoneShop = new Paragraph("Số điện thoại shop : 0856572786", contentFont);

            document.add(phoneShop);

            Paragraph order = new Paragraph("Hoá đơn thanh toán", subTitleFont);
            order.setAlignment(Element.ALIGN_CENTER);
            document.add(order);

            Paragraph customer = new Paragraph("Khách hàng: " + orderPdf.getUsername(), contentFont);
            document.add(customer);

            Paragraph customerAddress = new Paragraph("Địa chỉ: " + orderPdf.getAddress(), contentFont);
            document.add(customerAddress);

            String phoneNumber = orderPdf.getPhone();
            if (phoneNumber != null && !phoneNumber.equals("0")) {
                Paragraph phoneNumberParagraph = new Paragraph("Số điện thoại: " + phoneNumber, contentFont);
                document.add(phoneNumberParagraph);
            }

            Paragraph date = new Paragraph("Ngày: " + formattedDateTime + "                             Mã hoá đơn: " + orderPdf.getId(), contentFont);
            document.add(date);

            float[] columnWidths = {150f, 50f, 100f, 100f};
            PdfPTable table = new PdfPTable(4);
            table.setTotalWidth(400f);
            table.setSpacingBefore(10);
            table.setSpacingAfter(20);
            table.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.setWidthPercentage(100);
            table.setWidths(columnWidths);

            addDataToTable(table, 0, "Sản phẩm", "Số lượng", "Đơn giá", "Thành tiền");
            for (OrderDetailPdfResponse orderDetail : orderDetailPdf) {
                Float totalPrice = orderDetail.getPrice() * orderDetail.getQuantity();
                addDataToTable(
                        table,
                        1,
                        orderDetail.getName(),
                        orderDetail.getQuantity().toString(),
                        orderDetail.getPrice().toString(),
                        totalPrice.toString());
            }

            document.add(table);

            Float shipCost = orderPdf.getShipCost();
            if (shipCost != null && shipCost != 0) {
                Paragraph fee = new Paragraph("Phí ship:          " + shipCost, contentFont);
                fee.setIndentationLeft(150f);
                document.add(fee);
            }

            Paragraph totalPrice = new Paragraph("Tổng tiền:        " + orderPdf.getTotalPrice(), contentFont);
            totalPrice.setIndentationLeft(150f);
            document.add(totalPrice);

            Paragraph totalPriceAfter = new Paragraph("Tổng thu:          " + (orderPdf.getTotalPrice() + orderPdf.getShipCost()) , contentFont);
            totalPriceAfter.setIndentationLeft(150f);
            document.add(totalPriceAfter);

            Paragraph footer = new Paragraph("Cảm ơn bạn đã tin tưởng và ủng hộ cửa hàng !", contenFooterFont);
            footer.setAlignment(Element.ALIGN_CENTER);
            footer.setPaddingTop(25f);
            document.add(footer);

            // Đóng File
            //response
//            Path path = Paths.get(pdfFile.getURI());
//            byte[] data = Files.readAllBytes(path);

            return document;
        } catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            document.close();
        }
        return null;
    }

    private static void addDataToTable(PdfPTable table, int type, String... rowData) {
        if (type == 0) {
            for (String data : rowData) {
                // Tạo một ô mới trong bảng
                PdfPCell cell = createHeaderCell(data);
                // Thêm ô vào bảng
                table.addCell(cell);
            }
        } else {
            for (String data : rowData) {
                // Tạo một ô mới trong bảng
                PdfPCell cell = createCell(data);
                // Thêm ô vào bảng
                table.addCell(cell);
            }
        }

    }

    private static PdfPCell createCell(String content) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, contentTableFont));

        // Đặt kiểu viền là dashed (đường gạch nối) và đặt kích thước gạch nối
        cell.setBorder(PdfPCell.TOP);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setBorderWidthBottom(0.2f);
        cell.setBorderWidthTop(0f);
        cell.setBorderWidth(0f); // Đặt viền left và right thành 0 để loại bỏ chúng
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        cell.setMinimumHeight(20);
        return cell;
    }

    private static PdfPCell createHeaderCell(String content) {
        PdfPCell cell = new PdfPCell(new Paragraph(content, headerTableFont));

        // Đặt kiểu viền là dashed (đường gạch nối) và đặt kích thước gạch nối
        cell.setBorder(PdfPCell.TOP);
        cell.setBorderColorTop(BaseColor.BLACK);
        cell.setBorderWidthBottom(0.2f);
        cell.setBorderWidthTop(0f);
        cell.setBorderWidth(0f); // Đặt viền left và right thành 0 để loại bỏ chúng
        cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);

        cell.setMinimumHeight(30);
        return cell;
    }
}
