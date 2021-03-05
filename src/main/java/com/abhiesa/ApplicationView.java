package com.abhiesa;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.xobject.PdfImageXObject;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.UnitValue;
import org.apache.commons.io.FileUtils;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author abhiesa
 * */
public class ApplicationView {

    String directory = "";
    private final String title;

    private final JFrame frame;
    final Font font1 = new Font("Courier", Font.BOLD,18);
    Border border = BorderFactory.createCompoundBorder(new EmptyBorder(10,10,10,10),
            new EtchedBorder());

    public  ApplicationView(final String title, final int width, final int height) {
        this.title = title;
        frame = new ApplicationFrame(title, width, height);
    }

    public void createView() {
        final JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        final JLabel label1 = new JLabel("Output Directory: ");
        label1.setFont(font1.deriveFont(font1.getStyle() | Font.BOLD));
        label1.setBounds(10,25, 225, 25);
        panel.add(label1);

        final JButton button1 = new JButton("Click me to select");
        button1.setBounds(230,10,150,50);
        panel.add(button1);

        final JTextArea label2 = new JTextArea(2, 450);
        label2.setBounds(10,70,475,50);
        label2.setEditable(false);
        label2.setLineWrap(true);
        label2.setWrapStyleWord(true);
        panel.add(label2);

        final JButton button2 = new JButton("Clear Directory");
        button2.setBounds(490,80,120,30);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label2.setText("");
                directory = "";
            }
        });
        panel.add(button2);

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(frame);
                if(option == JFileChooser.APPROVE_OPTION){
                    File file = fileChooser.getSelectedFile();
                    label2.setText("Folder Selected: " + file.getAbsolutePath());
                    directory = file.getAbsolutePath();
                }else{
                    label2.setText("");
                    directory = "";
                }
            }
        });

        // ======================

        final JLabel label3 = new JLabel("File name: ");
        label3.setFont(font1.deriveFont(font1.getStyle() | Font.BOLD));
        label3.setBounds(10,150, 225, 25);
        panel.add(label3);

        final JTextField jTextField1 = new JTextField();
        jTextField1.setBounds(130,145,350,35);
        panel.add(jTextField1);

        // ==================================

        final JLabel fieldLbl1 = new JLabel("Field1: ");
        fieldLbl1.setBounds(10,205, 50, 25);
        panel.add(fieldLbl1);

        final JTextField fieldTxt1 = new JTextField();
        fieldTxt1.setBounds(60,200,220,35);
        panel.add(fieldTxt1);

        final JLabel fieldLbl2 = new JLabel("Field2: ");
        fieldLbl2.setBounds(330,205, 50, 25);
        panel.add(fieldLbl2);

        final JTextField fieldTxt2 = new JTextField();
        fieldTxt2.setBounds(380,200,220,35);
        panel.add(fieldTxt2);

        // ==================================

        final JLabel fieldLbl3 = new JLabel("Field3: ");
        fieldLbl3.setBounds(10,255, 50, 25);
        panel.add(fieldLbl3);

        final JTextField fieldTxt3 = new JTextField();
        fieldTxt3.setBounds(60,250,220,35);
        panel.add(fieldTxt3);

        final JLabel fieldLbl4 = new JLabel("Field4: ");
        fieldLbl4.setBounds(330,255, 50, 25);
        panel.add(fieldLbl4);

        final JTextField fieldTxt4 = new JTextField();
        fieldTxt4.setBounds(380,250,220,35);
        panel.add(fieldTxt4);

        // ==================================

        final JLabel fieldLbl5 = new JLabel("Field5: ");
        fieldLbl5.setBounds(10,305, 50, 25);
        panel.add(fieldLbl5);

        final JTextField fieldTxt5 = new JTextField();
        fieldTxt5.setBounds(60,300,220,35);
        panel.add(fieldTxt5);

        final JLabel fieldLbl6 = new JLabel("Field6: ");
        fieldLbl6.setBounds(330,305, 50, 25);
        panel.add(fieldLbl6);

        final JTextField fieldTxt6 = new JTextField();
        fieldTxt6.setBounds(380,300,220,35);
        panel.add(fieldTxt6);

        // ==================================

        final JLabel fieldLbl7 = new JLabel("Field7: ");
        fieldLbl7.setBounds(10,355, 50, 25);
        panel.add(fieldLbl7);

        final JTextField fieldTxt7 = new JTextField();
        fieldTxt7.setBounds(60,350,220,35);
        panel.add(fieldTxt7);

        final JLabel fieldLbl8 = new JLabel("Field8: ");
        fieldLbl8.setBounds(330,355, 50, 25);
        panel.add(fieldLbl8);

        final JTextField fieldTxt8 = new JTextField();
        fieldTxt8.setBounds(380,350,220,35);
        panel.add(fieldTxt8);

        // ==================================


        final JButton button3 = new JButton("Generate PDF");
        button3.setBounds(110,395,120,50);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                final String fileName = directory + File.separator +jTextField1.getText() + ".pdf";
                final String data1 = defaultString(fieldTxt1.getText()) +
                        defaultString(fieldTxt2.getText()) +
                        defaultString(fieldTxt3.getText()) +
                        defaultString(fieldTxt4.getText()) +
                        defaultString(fieldTxt5.getText()) +
                        defaultString(fieldTxt6.getText()) +
                        defaultString(fieldTxt7.getText()) +
                        defaultString(fieldTxt8.getText());
                File file = new File(fileName);
                if (file.exists()) {
                    JOptionPane.showMessageDialog(frame, "File Already Exists!");
                } else {
                    try{
                        try(final PdfWriter writer = new PdfWriter(file);
                            final PdfDocument pdf = new PdfDocument(writer);
                            final Document document = new Document(pdf, PageSize.A4.rotate());
                        ) {
                            document.setMargins(20, 20, 20, 20);
                            document.add(new Paragraph("QR CODE"));

                            Map<EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap<>();
                            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                            final BitMatrix matrix = new MultiFormatWriter().encode(
                                    new String(data1.getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8),
                                    BarcodeFormat.QR_CODE, 100, 100, hintMap);

                            final File f = new File(directory + File.separator+"zxing.jpg");
                            if (f.exists()) {
                                f.delete();
                            }
                            MatrixToImageWriter.writeToPath(matrix,"jpg", f.toPath());
                            final ImageData imageData = ImageDataFactory.create(FileUtils.readFileToByteArray(f));
                            PdfImageXObject pdfImageXObject = new PdfImageXObject(imageData);
                            final com.itextpdf.layout.element.Image image =
                                    new com.itextpdf.layout.element.Image(pdfImageXObject);
                            image.setHeight(200);
                            image.setWidth(200);
                            document.add(image);
                            f.delete();
                        }
                        JOptionPane.showMessageDialog(frame, "PDF file created.", title, JOptionPane.INFORMATION_MESSAGE);
                    }catch (final IOException | WriterException ex ) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), title, JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                    }
                }
            }
        });
        panel.add(button3);

        final JButton button4 = new JButton("Clear All");
        button4.setBounds(430,410,120,30);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label2.setText("");
                jTextField1.setText("");
                fieldTxt1.setText("");
                fieldTxt2.setText("");
                fieldTxt3.setText("");
                fieldTxt4.setText("");
                fieldTxt5.setText("");
                fieldTxt6.setText("");
                fieldTxt7.setText("");
                fieldTxt8.setText("");
                directory = "";
            }
        });
        panel.add(button4);

        frame.getContentPane().add(panel, BorderLayout.CENTER);

    }

    private String defaultString(final String str) {
        return null == str ? "" : str.trim();
    }

}
