package com.project.symptoms.db;

import android.provider.BaseColumns;
import android.widget.ScrollView;

public class Contract {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private Contract() {
    }

    public static class Symptom_TMP implements BaseColumns {
        public static final String TABLE_NAME = "symptom_tmp";
        public static final String COLUMN_NAME_ID_PK = "symptom_id";
        public static final String COLUMN_NAME_POS_X = "circle_pos_X";
        public static final String COLUMN_NAME_POS_Y = "circle_pos_Y";
        public static final String COLUMN_NAME_SIDE = "circle_side";
        public static final String COLUMN_NAME_CREATION_DATE = "creation_date";
        public static final String COLUMN_NAME_CREATION_TIME = "creation_time";
        public static final String COLUMN_NAME_CIRCLE_RADIUS = "circle_radius";
    }

    public static final String SQL_CREATE_SYMPTOM_TMP =
            "CREATE TABLE " + Symptom_TMP.TABLE_NAME + " (" +
                    Symptom_TMP.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Symptom_TMP.COLUMN_NAME_POS_X + " REAL," +
                    Symptom_TMP.COLUMN_NAME_POS_Y + " REAL," +
                    Symptom_TMP.COLUMN_NAME_SIDE + " INTEGER," +
                    Symptom_TMP.COLUMN_NAME_CREATION_DATE + " INTEGER," +
                    Symptom_TMP.COLUMN_NAME_CREATION_TIME + " INTEGER," +
                    Symptom_TMP.COLUMN_NAME_CIRCLE_RADIUS + " REAL)";

    public static final String SQL_DELETE_SYMPTOM_TMP =
            "DROP TABLE IF EXISTS " + Symptom_TMP.TABLE_NAME;

    /* Inner class that defines the table contents */
    public static class Symptom implements BaseColumns {
        public static final String TABLE_NAME = "symptom";
        public static final String COLUMN_NAME_ID_PK = "symptom_id";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_INTENSITY = "intensity";
        public static final String COLUMN_NAME_INTERMITTENCE = "intermittence";
        public static final String COLUMN_NAME_START_TIME = "start_time";
        public static final String COLUMN_NAME_END_TIME = "end_time";
        public static final String COLUMN_NAME_CAUSING_DRUG = "causing_drug";
        public static final String COLUMN_NAME_CAUSING_FOOD = "causing_food";

    }

    public static final String SQL_CREATE_SYMPTOM =
            "CREATE TABLE " + Symptom.TABLE_NAME + " (" +
                    Symptom.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Symptom.COLUMN_NAME_DESCRIPTION + " TEXT," +
                    Symptom.COLUMN_NAME_INTENSITY + " TEXT," +
                    Symptom.COLUMN_NAME_INTERMITTENCE + " NUMERIC," +
                    Symptom.COLUMN_NAME_START_TIME + " NUMERIC," +
                    Symptom.COLUMN_NAME_END_TIME + " NUMERIC," +
                    Symptom.COLUMN_NAME_CAUSING_DRUG + " TEXT," +
                    Symptom.COLUMN_NAME_CAUSING_FOOD + " TEXT)";

    public static final String SQL_DELETE_SYMPTOM =
            "DROP TABLE IF EXISTS " + Symptom.TABLE_NAME;

    public static class Category implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_ID_PK = "category_id";
        public static final String COLUMN_NAME_NAME = "name";
    }

    public static final String SQL_CREATE_CATEGORY =
            "CREATE TABLE " + Category.TABLE_NAME + " (" +
                    Category.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Category.COLUMN_NAME_NAME + "TEXT)";

    public static final String SQL_DELETE_CATEGORY =
            "DROP TABLE IF EXISTS " + Category.TABLE_NAME;

    public static class CategoryOption implements BaseColumns {
        public static final String TABLE_NAME = "category_option";
        public static final String COLUMN_NAME_ID_PK = "category_option_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CATEGORY_ID_FK = "category_id";
    }

    public static final String SQL_CREATE_CATEGORY_OPTION =
            "CREATE TABLE " + CategoryOption.TABLE_NAME + " (" +
                    CategoryOption.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CategoryOption.COLUMN_NAME_NAME + " TEXT, " +
                    CategoryOption.COLUMN_NAME_CATEGORY_ID_FK + " INTEGER," +
                    "FOREIGN KEY (" + CategoryOption.COLUMN_NAME_CATEGORY_ID_FK + ") " +
                    "REFERENCES " + Category.TABLE_NAME + "(" + Category.COLUMN_NAME_ID_PK + "))";

    public static final String SQL_DELETE_CATEGORY_OPTION =
            "DROP TABLE IF EXISTS " + CategoryOption.TABLE_NAME;

    public static class SelectedCategoryOption implements BaseColumns {
        public static final String TABLE_NAME = "selected_category_option";
        public static final String COLUMN_NAME_SYMPTOM_ID_FK = "symptom_id";
        public static final String COLUMN_NAME_CATEGORY_OPTION_ID_FK = "category_option_id";
    }

    public static final String SQL_CREATE_SELECTED_CATEGORY_OPTION =
            "CREATE TABLE " + SelectedCategoryOption.TABLE_NAME + " (" +
                    SelectedCategoryOption.COLUMN_NAME_SYMPTOM_ID_FK + " INTEGER, " +
                    SelectedCategoryOption.COLUMN_NAME_CATEGORY_OPTION_ID_FK + " INTEGER, " +
                    "FOREIGN KEY (" + SelectedCategoryOption.COLUMN_NAME_SYMPTOM_ID_FK + ") " +
                    "REFERENCES " + Symptom.TABLE_NAME + "(" + Symptom.COLUMN_NAME_ID_PK + "), " +
                    "FOREIGN KEY (" + SelectedCategoryOption.COLUMN_NAME_CATEGORY_OPTION_ID_FK + ") " +
                    "REFERENCES " + CategoryOption.TABLE_NAME + "(" + CategoryOption.COLUMN_NAME_ID_PK + "))";


    public static final String SQL_DELETE_SELECTED_CATEGORY_OPTION =
            "DROP TABLE IF EXISTS " + SelectedCategoryOption.TABLE_NAME;


    public static class Pressure implements BaseColumns {
        public static final String TABLE_NAME = "pressure";
        public static final String COLUMN_NAME_ID_PK = "pressure_id";
        public static final String COLUMN_NAME_SYSTOLIC = "systolic";
        public static final String COLUMN_NAME_DIASTOLIC = "diastolic";
        public static final String COLUMN_NAME_DATETIME = "datetime";
    }

    public static final String SQL_CREATE_PRESSURE =
            "CREATE TABLE " + Pressure.TABLE_NAME + " (" +
                    Pressure.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Pressure.COLUMN_NAME_SYSTOLIC + " INTEGER," +
                    Pressure.COLUMN_NAME_DIASTOLIC + " INTEGER," +
                    Pressure.COLUMN_NAME_DATETIME + " INTEGER)"; // TODO: Is this an Integer?

    public static final String SQL_DELETE_PRESSURE =
            "DROP TABLE IF EXISTS " + Pressure.TABLE_NAME;

    public static class Glucose implements BaseColumns {
        public static final String TABLE_NAME = "glucose";
        public static final String COLUMN_NAME_ID_PK = "glucose_id";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_DATETIME = "datetime";

    }

    public static final String SQL_CREATE_GLUCOSE =
            "CREATE TABLE " + Glucose.TABLE_NAME + " (" +
                    Glucose.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Glucose.COLUMN_NAME_VALUE + " INTEGER," +
                    Glucose.COLUMN_NAME_DATETIME + " INTEGER)";

    public static final String SQL_DELETE_GLUCOSE =
            "DROP TABLE IF EXISTS " + Pressure.TABLE_NAME;

    public static class GlucoseLevels implements BaseColumns{
        public static final String TABLE_NAME = "glucose_levels";
        public static final String COLUMN_NAME_ID_PK = "glucose_level_id";
        public static final String COLUMN_NAME_LEVEL = "level";
        public static final String COLUMN_NAME_FASTING_PLASMA = "fasting_plasma";
        public static final String COLUMN_NAME_TOLERANCE_TEST = "tolerance_test";
    }

    public static final String SQL_CREATE_GLUCOSE_LEVELS =
            "CREATE TABLE " + GlucoseLevels.TABLE_NAME + " ("+
            GlucoseLevels.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            GlucoseLevels.COLUMN_NAME_LEVEL + " TEXT," +
            GlucoseLevels.COLUMN_NAME_FASTING_PLASMA + " TEXT," +
            GlucoseLevels.COLUMN_NAME_TOLERANCE_TEST + " TEXT)";

    public static final String SQL_DELETE_GLUCOSE_LEVELS =
            "DROP TABLE IF EXISTS " + GlucoseLevels.TABLE_NAME;

    public static class PressureLevel implements BaseColumns{
        public static final String TABLE_NAME = "pressure_levels";
        public static final String COLUMN_NAME_PRESSURE_ID_PK = "pressure_level_id";
        public static final String COLUMN_NAME_CATEGORY = "category";
        public static final String COLUMN_NAME_SYSTOLIC_MAXIMUM = "systolic_maximum";
        public static final String COLUMN_NAME_SYSTOLIC_MINIMUM = "systolic_minimum";
        public static final String COLUMN_NAME_DIASTOLIC_MAXIMUM = "diastolic_maximum";
        public static final String COLUMN_NAME_DIASTOLIC_MINIMUM = "diastolic_minimum";
    }

    public static final String SQL_CREATE_PRESSURE_LEVELS =
            "CREATE TABLE " + PressureLevel.TABLE_NAME + " ("+
                    PressureLevel.COLUMN_NAME_PRESSURE_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    PressureLevel.COLUMN_NAME_CATEGORY + " TEXT," +
                    PressureLevel.COLUMN_NAME_SYSTOLIC_MAXIMUM + " INTEGER," +
                    PressureLevel.COLUMN_NAME_SYSTOLIC_MINIMUM + " INTEGER," +
                    PressureLevel.COLUMN_NAME_DIASTOLIC_MAXIMUM + " INTEGER," +
                    PressureLevel.COLUMN_NAME_DIASTOLIC_MINIMUM + " INTEGER)";

    public static final String SQL_DELETE_PRESSURE_LEVELS =
            "DROP TABLE IF EXISTS " + PressureLevel.TABLE_NAME;
}
