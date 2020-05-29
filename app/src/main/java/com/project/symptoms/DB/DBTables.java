package com.project.symptoms.DB;

import android.provider.BaseColumns;

public class DBTables {

    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DBTables() {
    }

    /* Inner class that defines the table contents */
    public static class Symptom implements BaseColumns {
        public static final String TABLE_NAME = "Symptom";
        public static final String COLUMN_NAME_ID_PK = "symptom_id";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_INTENSITY = "intensity";
        public static final String COLUMN_NAME_INTERMITTENCE = "intermittence";
        public static final String COLUMN_NAME_START_TIME = "start_time";
        public static final String COLUMN_NAME_END_TIME = "end_time";
        public static final String COLUMN_NAME_CAUSING_DUG = "causing_dug";
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
                    Symptom.COLUMN_NAME_CAUSING_DUG + " TEXT," +
                    Symptom.COLUMN_NAME_CAUSING_FOOD + " TEXT)";

    public static final String SQL_DELETE_SYMPTOM =
            "DROP TABLE IF EXISTS " + Symptom.TABLE_NAME;

    public static class Category implements BaseColumns {
        public static final String TABLE_NAME = "Category";
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
        public static final String TABLE_NAME = "categoryOption";
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

    public static class selectedCategoryOption implements BaseColumns {
        public static final String TABLE_NAME = "selectedCategoryOption";
        public static final String COLUMN_NAME_SYMPTOM_ID_FK = "symptom_id";
        public static final String COLUMN_NAME_CATEGORY_OPTION_ID_FK = "category_option_id";
    }

    public static final String SQL_CREATE_SELECTED_CATEGORY_OPTION =
            "CREATE TABLE " + selectedCategoryOption.TABLE_NAME + " (" +
                    selectedCategoryOption.COLUMN_NAME_SYMPTOM_ID_FK + " INTEGER, " +
                    selectedCategoryOption.COLUMN_NAME_CATEGORY_OPTION_ID_FK + " INTEGER, " +
                    "FOREIGN KEY (" + selectedCategoryOption.COLUMN_NAME_SYMPTOM_ID_FK + ") " +
                    "REFERENCES " + Symptom.TABLE_NAME + "(" + Symptom.COLUMN_NAME_ID_PK + "), " +
                    "FOREIGN KEY (" + selectedCategoryOption.COLUMN_NAME_CATEGORY_OPTION_ID_FK + ") " +
                    "REFERENCES " + CategoryOption.TABLE_NAME + "(" + CategoryOption.COLUMN_NAME_ID_PK + "))";


    public static final String SQL_DELETE_SELECTED_CATEGORY_OPTION =
            "DROP TABLE IF EXISTS " + selectedCategoryOption.TABLE_NAME;


    public static class Pressure implements BaseColumns {
        public static final String TABLE_NAME = "Pressure";
        public static final String COLUMN_NAME_ID_PK = "pressure_id";
        public static final String COLUMN_NAME_SYSTOLIC = "systolic";
        public static final String COLUMN_NAME_DIASTOLIC = "diastolic";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
    }

    public static final String SQL_CREATE_PRESSURE =
            "CREATE TABLE " + Pressure.TABLE_NAME + " (" +
                    Pressure.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Pressure.COLUMN_NAME_SYSTOLIC + " INTEGER," +
                    Pressure.COLUMN_NAME_DIASTOLIC + " INTEGER," +
                    Pressure.COLUMN_NAME_DATE + " NUMERIC," +
                    Pressure.COLUMN_NAME_TIME + " NUMERIC)";

    public static final String SQL_DELETE_PRESSURE =
            "DROP TABLE IF EXISTS " + Pressure.TABLE_NAME;

    public static class Glucose implements BaseColumns {
        public static final String TABLE_NAME = "Glucose";
        public static final String COLUMN_NAME_ID_PK = "glucose_id";
        public static final String COLUMN_NAME_VALUE = "value";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
    }

    public static final String SQL_CREATE_GLUCOSE =
            "CREATE TABLE " + Glucose.TABLE_NAME + " (" +
                    Glucose.COLUMN_NAME_ID_PK + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Glucose.COLUMN_NAME_VALUE + " INTEGER," +
                    Glucose.COLUMN_NAME_DATE + " TEXT," +
                    Glucose.COLUMN_NAME_TIME + " TEXT)";

    public static final String SQL_DELETE_GLUCOSE =
            "DROP TABLE IF EXISTS " + Pressure.TABLE_NAME;


}
