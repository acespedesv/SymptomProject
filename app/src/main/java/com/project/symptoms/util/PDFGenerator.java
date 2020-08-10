package com.project.symptoms.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Environment;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.project.symptoms.R;
import com.project.symptoms.db.controller.GlucoseController;
import com.project.symptoms.db.controller.PressureController;
import com.project.symptoms.db.controller.SymptomCategoryController;
import com.project.symptoms.db.controller.SymptomCategoryOptionController;
import com.project.symptoms.db.controller.SymptomController;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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
        try { PdfWriter.getInstance(mainPDFDocument, new FileOutputStream(documentPath)); }
        catch (FileNotFoundException | DocumentException e) {
            e.printStackTrace();
        }

        mainPDFDocument.open();

        Paragraph title = new Paragraph(appResources.getString(R.string.pdf_title));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setFont(titlesFont);
        Paragraph subtitle = new Paragraph(appResources.getString(R.string.pdf_subtitle));
        subtitle.setFont(subTitlesFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);

        try {
            mainPDFDocument.add(title);
            mainPDFDocument.add(subtitle);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        mainPDFDocument.close();

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
        commonTextFont.setSize(18);
        commonTextFont.setFamily("Arial");
        commonTextFont.setStyle(Font.NORMAL);

        tableHeadersFont = new Font();
        tableHeadersFont.setSize(20);
        tableHeadersFont.setFamily("Arial");
        tableHeadersFont.setStyle(Font.BOLD);
    }

    public boolean generateCompletePDF(long startDate, long endDate){
        return true;
    }

    public boolean writeGlucoseHistoryToPDF(long startDate, long endDate){
        mainPDFDocument.open();

        if (!START_END_DATE_ADDED) addDateRangeToPDF(startDate, endDate);
        drawHorizontalLine();
        insertUserDate();
        drawHorizontalLine();

        Paragraph glucoseTitle = new Paragraph(appResources.getString(R.string.glucose_title));
        glucoseTitle.setFont(titlesFont);

        try {
            mainPDFDocument.add(glucoseTitle);
            PdfPTable glucoseData = new PdfPTable(2);
            glucoseData.setHeaderRows(1);
            glucoseData.addCell(appResources.getString(R.string.glucose_table_date_column));
            glucoseData.addCell(appResources.getString(R.string.glucose_table_value_column));
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
        if (!mainPDFDocument.isOpen()) mainPDFDocument.open();
        String startDateAsString = DateTimeUtils.getInstance().getStringDateFromLong(startDate);
        String EndDateAsString = DateTimeUtils.getInstance().getStringDateFromLong(endDate);
        Paragraph datesRange = new Paragraph(appResources.getString(R.string.date_range)
                + "\n"
                + appResources.getString(R.string.start_date)
                + startDateAsString
                + " "
                + appResources.getString(R.string.end_date)
                + endDate);
        datesRange.setFont(subTitlesFont);
        datesRange.setAlignment(Element.ALIGN_CENTER);
        try {
            mainPDFDocument.add(datesRange);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (mainPDFDocument.isOpen()) mainPDFDocument.close();
    }

    private void drawHorizontalLine(){
        if (!mainPDFDocument.isOpen()) mainPDFDocument.open();
        LineSeparator ls = new LineSeparator();
        try {
            mainPDFDocument.add(new Chunk(ls));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (mainPDFDocument.isOpen()) mainPDFDocument.close();
    }

    private void insertUserDate(){
        if (!mainPDFDocument.isOpen()) mainPDFDocument.open();
        Paragraph userInfo = new Paragraph("Paciente: Isaac Mena López\nCédula: 402400867\nFecha de nacimient: 07/11/98");
        userInfo.setFont(subTitlesFont);
        userInfo.setAlignment(Element.ALIGN_CENTER);
        try {
            mainPDFDocument.add(userInfo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        if (mainPDFDocument.isOpen()) mainPDFDocument.close();
    }

}