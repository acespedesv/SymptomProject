package com.project.symptoms.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.project.symptoms.R;
import com.project.symptoms.db.controller.GlucoseController;
import com.project.symptoms.db.controller.PressureController;
import com.project.symptoms.db.controller.SymptomCategoryController;
import com.project.symptoms.db.controller.SymptomCategoryOptionController;
import com.project.symptoms.db.controller.SymptomController;
import com.project.symptoms.db.model.GlucoseModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {

    private PressureController pressureController;
    private GlucoseController glucoseController;
    private SymptomController symptomController;
    private SymptomCategoryOptionController symptomCategoryOptionController;
    private SymptomCategoryController symptomCategoryController;

    private Document mainPDFDocument;
    private String documentName;
    private String documentPath;
    private Font titlesFont;
    private Font subTitlesFont;
    private Font commonTextFont;
    private Font tableHeadersFont;

    private Resources appResources;

    private boolean START_END_DATE_ADDED = false;

    public static final Chunk NEWLINE = new Chunk("\n");

    public PDFGenerator(Context context){
        pressureController = PressureController.getInstance(context);
        glucoseController = GlucoseController.getInstance(context);
        symptomController = SymptomController.getInstance(context);
        symptomCategoryController = SymptomCategoryController.getInstance(context);
        symptomCategoryOptionController = SymptomCategoryOptionController.getInstance(context);

        appResources = context.getResources();

        setUpFonts();

        long currentDateTime = DateTimeUtils.getInstance().getCurrentDateTimeAsLong();
        documentName = appResources.getString(R.string.pdf_document_name) + currentDateTime + '.' + appResources.getString(R.string.pdf_extension);
        documentPath = Environment.getExternalStorageDirectory() + "/" + documentName;

        mainPDFDocument = new Document();
        try { PdfWriter.getInstance(mainPDFDocument, new FileOutputStream(documentPath)).setInitialLeading(20); }
        catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        Paragraph title = new Paragraph(appResources.getString(R.string.pdf_title), titlesFont);
        title.setAlignment(Element.ALIGN_CENTER);
        Paragraph subtitle = new Paragraph(appResources.getString(R.string.pdf_subtitle), subTitlesFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);

        try {
            mainPDFDocument.open();
            mainPDFDocument.add(title);
            mainPDFDocument.add(subtitle);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }

    private void setUpFonts(){
        titlesFont = new Font();
        titlesFont.setSize(36);
        titlesFont.setFamily("Arial");
        titlesFont.setStyle(Font.NORMAL);

        subTitlesFont = new Font();
        subTitlesFont.setSize(24);
        subTitlesFont.setFamily("Arial");
        subTitlesFont.setStyle(Font.NORMAL);

        commonTextFont = new Font();
        commonTextFont.setSize(14);
        commonTextFont.setFamily("Arial");
        commonTextFont.setStyle(Font.NORMAL);

        tableHeadersFont = new Font();
        tableHeadersFont.setSize(16);
        tableHeadersFont.setFamily("Arial");
        tableHeadersFont.setStyle(Font.BOLD);
    }

    public boolean generateCompletePDF(long startDate, long endDate){
        return true;
    }

    public boolean writeGlucoseHistoryToPDF(long startDate, long endDate){

        if (!START_END_DATE_ADDED) addDateRangeToPDF(startDate, endDate);

        drawHorizontalLine();
        insertUserDate();
        drawHorizontalLine();

        Phrase glucoseTitle = new Phrase(appResources.getString(R.string.glucose_title), subTitlesFont);

        try {

            mainPDFDocument.add(glucoseTitle);
            mainPDFDocument.add(NEWLINE);

            PdfPTable glucoseData = new PdfPTable(2);

            PdfPCell dateHeaderCell = new PdfPCell();
            dateHeaderCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            dateHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dateHeaderCell.addElement(new Phrase(appResources.getString(R.string.glucose_table_date_column), tableHeadersFont));

            PdfPCell valueHeaderCell = new PdfPCell();
            valueHeaderCell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            valueHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            valueHeaderCell.addElement(new Phrase(appResources.getString(R.string.glucose_table_value_column), tableHeadersFont));

            glucoseData.addCell(dateHeaderCell);
            glucoseData.addCell(valueHeaderCell);

            List<GlucoseModel> models = glucoseController.listAll();
            Log.e("PDF", "Models list size: " + models.size());
            for (GlucoseModel model: models) {
                String date = DateTimeUtils.getInstance().getStringDateFromLong(model.getDate());
                glucoseData.addCell(new Phrase(date, commonTextFont));
                glucoseData.addCell(new Phrase(Integer.toString(model.getValue()), commonTextFont));
            }

            mainPDFDocument.add(glucoseData);

        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
        mainPDFDocument.close();
        return true;
    }

    public boolean writeBloodPressureHistoryToPDF(long startDate, long endDate){

        return true;
    }

    public boolean writeSymptomsHistoryToPDF(long startDate, long endDate){

        return true;
    }

    private void addDateRangeToPDF(long startDate, long endDate) {
        String startDateAsString = DateTimeUtils.getInstance().getStringDateFromLong(startDate);
        String endDateAsString = DateTimeUtils.getInstance().getStringDateFromLong(endDate);
        Paragraph datesRange = new Paragraph(appResources.getString(R.string.date_range)
                + "\n"
                + appResources.getString(R.string.start_date)
                + startDateAsString
                + " "
                + appResources.getString(R.string.end_date)
                + endDateAsString);
        datesRange.setFont(subTitlesFont);
        datesRange.setAlignment(Element.ALIGN_CENTER);
        try {
            mainPDFDocument.add(datesRange);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void drawHorizontalLine(){
        LineSeparator ls = new LineSeparator();
        try {
            mainPDFDocument.add(new Chunk(ls));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void insertUserDate(){
        Paragraph userInfo = new Paragraph("Paciente: Isaac Mena López\nCédula: 402400867\nFecha de nacimiento: 07/11/98");
        userInfo.setFont(subTitlesFont);
        userInfo.setAlignment(Element.ALIGN_CENTER);
        try {
            mainPDFDocument.add(userInfo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
