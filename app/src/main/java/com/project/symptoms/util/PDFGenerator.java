package com.project.symptoms.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
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
import com.project.symptoms.db.model.PressureModel;
import com.project.symptoms.db.model.SymptomModel;
import com.project.symptoms.db.model.SymptomViewModel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

    }

    private void setUpFonts(){
        titlesFont = new Font();
        titlesFont.setSize(36);
        titlesFont.setFamily("Arial");
        titlesFont.setStyle(Font.BOLD);
        titlesFont.setColor(new BaseColor(141,191,65));

        subTitlesFont = new Font();
        subTitlesFont.setSize(24);
        subTitlesFont.setFamily("Arial");
        subTitlesFont.setStyle(Font.BOLD);
        subTitlesFont.setColor(new BaseColor(18,91,167));

        commonTextFont = new Font();
        commonTextFont.setSize(14);
        commonTextFont.setFamily("Arial");
        commonTextFont.setStyle(Font.NORMAL);

        tableHeadersFont = new Font();
        tableHeadersFont.setSize(16);
        tableHeadersFont.setFamily("Arial");
        tableHeadersFont.setStyle(Font.BOLD);
        tableHeadersFont.setColor(new BaseColor(18,91,167));
    }

    public boolean generateCompletePDF(long startDate, long endDate){

        Paragraph title = new Paragraph(appResources.getString(R.string.pdf_title), titlesFont);
        title.setAlignment(Element.ALIGN_CENTER);
        Paragraph subtitle = new Paragraph(appResources.getString(R.string.pdf_subtitle), subTitlesFont);
        subtitle.setAlignment(Element.ALIGN_CENTER);

        mainPDFDocument.open();

        try {
            mainPDFDocument.add(title);
            mainPDFDocument.add(subtitle);
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        drawHorizontalLine();
        insertUserData();
        drawHorizontalLine();

        boolean output = writeGlucoseHistoryToPDF(startDate, endDate) &&
                writeBloodPressureHistoryToPDF(startDate, endDate) &&
                writeSymptomsHistoryToPDF(startDate, endDate);

        mainPDFDocument.close();

        return output;
    }

    public boolean writeGlucoseHistoryToPDF(long startDate, long endDate){
        if (!START_END_DATE_ADDED) addDateRangeToPDF(startDate, endDate);

        Phrase glucoseTitle = new Phrase(appResources.getString(R.string.glucose_title), subTitlesFont);

        try {

            mainPDFDocument.add(NEWLINE);
            mainPDFDocument.add(glucoseTitle);
            mainPDFDocument.add(NEWLINE);

            PdfPTable glucoseData = new PdfPTable(2);

            PdfPCell dateHeaderCell = new PdfPCell();
            dateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateHeaderCell.setUseAscender(true);
            dateHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dateHeaderCell.addElement(new Phrase(appResources.getString(R.string.table_date_column), tableHeadersFont));

            PdfPCell valueHeaderCell = new PdfPCell();
            valueHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            valueHeaderCell.setUseAscender(true);
            valueHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            valueHeaderCell.addElement(new Phrase(appResources.getString(R.string.glucose_table_value_column), tableHeadersFont));

            glucoseData.addCell(dateHeaderCell);
            glucoseData.addCell(valueHeaderCell);

            //List<GlucoseModel> models = glucoseController.select(startDate, endDate);
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
        return true;
    }

    public boolean writeBloodPressureHistoryToPDF(long startDate, long endDate){
        if (!START_END_DATE_ADDED) addDateRangeToPDF(startDate, endDate);

        Phrase pressureTitle = new Phrase(appResources.getString(R.string.pressure_title), subTitlesFont);

        try {

            mainPDFDocument.add(NEWLINE);
            mainPDFDocument.add(pressureTitle);
            mainPDFDocument.add(NEWLINE);

            PdfPTable pressureData = new PdfPTable(3);

            PdfPCell dateHeaderCell = new PdfPCell();
            dateHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateHeaderCell.setUseAscender(true);
            dateHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dateHeaderCell.addElement(new Phrase(appResources.getString(R.string.table_date_column), tableHeadersFont));

            PdfPCell systolicHeaderCell = new PdfPCell();
            systolicHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            systolicHeaderCell.setUseAscender(true);
            systolicHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            systolicHeaderCell.addElement(new Phrase(appResources.getString(R.string.pressure_table_systolic_column), tableHeadersFont));

            PdfPCell diastolicHeaderCell = new PdfPCell();
            diastolicHeaderCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            diastolicHeaderCell.setUseAscender(true);
            diastolicHeaderCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            diastolicHeaderCell.addElement(new Phrase(appResources.getString(R.string.pressure_table_diastolic_column), tableHeadersFont));

            pressureData.addCell(dateHeaderCell);
            pressureData.addCell(systolicHeaderCell);
            pressureData.addCell(diastolicHeaderCell);

            //List<PressureModel> models = pressureController.select(startDate, endDate);
            List<PressureModel> models = pressureController.selectAll();
            Log.e("PDF", "Models list size: " + models.size());
            for (PressureModel model: models) {
                String date = DateTimeUtils.getInstance().getStringDateFromLong(model.getDate());
                pressureData.addCell(new Phrase(date, commonTextFont));
                pressureData.addCell(new Phrase(Integer.toString(model.getSystolic()), commonTextFont));
                pressureData.addCell(new Phrase(Integer.toString(model.getDiastolic()), commonTextFont));
            }

            mainPDFDocument.add(pressureData);

        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean writeSymptomsHistoryToPDF(long startDate, long endDate){

        if (!START_END_DATE_ADDED) addDateRangeToPDF(startDate, endDate);

        Phrase symptomTitle = new Phrase(appResources.getString(R.string.symptom_title), subTitlesFont);

        try {

            mainPDFDocument.add(symptomTitle);
            mainPDFDocument.add(NEWLINE);
            mainPDFDocument.add(NEWLINE);

            //List<SymptomModel> models = symptomController.select(startDate, endDate);
            List<SymptomModel> models = symptomController.listAll();
            Log.e("PDF", "Models list size: " + models.size());
            for (SymptomModel model: models) {
                writeBasicSymptomInfoInPDF(model);
                writeDetailedSymptomDescription(model);
                mainPDFDocument.add(NEWLINE);
                drawHorizontalLine();
                mainPDFDocument.add(NEWLINE);
            }


        } catch (DocumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void writeDetailedSymptomDescription(SymptomModel model) throws DocumentException {
        List<SymptomViewModel> symptomViewModels = symptomController.selectFromView(model.getSymptomId());
        String currentCategoryName = symptomViewModels.get(0).getCategoryName();

        mainPDFDocument.add(NEWLINE);
        Paragraph currentCategoryNameParagraph = new Paragraph(currentCategoryName, tableHeadersFont);
        mainPDFDocument.add(currentCategoryNameParagraph);

        for (SymptomViewModel symptomViewModel: symptomViewModels) {
            if(!symptomViewModel.getCategoryName().equals(currentCategoryName)){
                currentCategoryName = symptomViewModel.getCategoryName();
                currentCategoryNameParagraph = new Paragraph(currentCategoryName, tableHeadersFont);
                mainPDFDocument.add(currentCategoryNameParagraph);
            }
            Paragraph categoryOptionNameParagraph = new Paragraph("- " + symptomViewModel.getCategoryOptionName(), commonTextFont);
            mainPDFDocument.add(categoryOptionNameParagraph);
        }
    }

    private void writeBasicSymptomInfoInPDF(SymptomModel model) throws DocumentException {
        mainPDFDocument.add(new Phrase(appResources.getString(R.string.symptom_description), tableHeadersFont));
        mainPDFDocument.add(new Phrase(" " + model.getDescription(), commonTextFont));
        mainPDFDocument.add(NEWLINE);
        mainPDFDocument.add(new Phrase(appResources.getString(R.string.intensity), tableHeadersFont));
        mainPDFDocument.add(new Phrase(" " + model.getIntensity(), commonTextFont));
        mainPDFDocument.add(NEWLINE);
        mainPDFDocument.add(new Phrase(appResources.getString(R.string.intermittent), tableHeadersFont));
        mainPDFDocument.add(new Phrase(" " + ((model.getIntermittence() == 1) ? "Sí" : "No"), commonTextFont));
        mainPDFDocument.add(NEWLINE);
        mainPDFDocument.add(new Phrase(appResources.getString(R.string.start_symptom_date_time), tableHeadersFont));
        mainPDFDocument.add(new Phrase(" " + DateTimeUtils.getInstance().getStringDateFromLong(model.getStartDate()), commonTextFont));
        mainPDFDocument.add(new Phrase(" " + DateTimeUtils.getInstance().getStringTimeFromLong(model.getStartTime()), commonTextFont));
        mainPDFDocument.add(NEWLINE);
        mainPDFDocument.add(new Phrase(appResources.getString(R.string.symptom_duration), tableHeadersFont));
        mainPDFDocument.add(new Phrase(" " + model.getDuration()));
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
        datesRange.setFont(commonTextFont);
        datesRange.setAlignment(Element.ALIGN_CENTER);
        try {
            mainPDFDocument.add(datesRange);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        START_END_DATE_ADDED = true;
    }

    private void drawHorizontalLine(){
        LineSeparator ls = new LineSeparator();
        ls.setLineColor(new BaseColor(18,91,167));
        try {
            mainPDFDocument.add(new Chunk(ls));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void insertUserData(){
        Paragraph userInfo = new Paragraph("Paciente: Isaac Mena López\nCédula: 402400867\nFecha de nacimiento: 07/11/98");
        userInfo.setFont(commonTextFont);
        userInfo.setAlignment(Element.ALIGN_CENTER);
        try {
            mainPDFDocument.add(userInfo);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
