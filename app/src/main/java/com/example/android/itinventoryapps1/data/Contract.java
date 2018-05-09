package com.example.android.itinventoryapps1.data;

import android.provider.BaseColumns;

/**
 * Created by lfotache on 08.05.2018.
 */

public final class Contract {

    /**
     * In order to prevent someone from accidentally instantiating the contract class,
     * we should create an empty constructor.
     */
    private Contract() {
    }

    /**
     * Inner class that defines constant values for the it products database table.
     * Each entry in the table represents a single it product.
     */
    public static final class ItEntry implements BaseColumns {


        // Name of the database table for it products
        public static final String TABLE_NAME = "it";

        /**
         * Unique ID number for the it product (only for use in the database table).
         * Type: INTEGER
         */
        public final static String COLUMN_ID = "_id";

        /**
         * Name of the it product.
         * Type: TEXT
         */
        public static final String COLUMN_NAME = "name";

        /**
         * Price of the it product.
         * Type: REAL
         */
        public static final String COLUMN_PRICE = "price";

        /**
         * Quantity of the it product.
         * Type: INTEGER
         */
        public static final String COLUMN_QUANTITY = "quantity";

        /**
         * Name of the it product supplier.
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";

        /**
         * Phone of the it product supplier.
         * Type: TEXT
         */
        public static final String COLUMN_SUPPLIER_PHONE = "supplier_phone";

    }
}
