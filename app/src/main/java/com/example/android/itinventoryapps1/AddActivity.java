package com.example.android.itinventoryapps1;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.itinventoryapps1.data.Contract;
import com.example.android.itinventoryapps1.data.DbHelper;

import static com.example.android.itinventoryapps1.data.Contract.ItEntry.COLUMN_NAME;
import static com.example.android.itinventoryapps1.data.Contract.ItEntry.COLUMN_PRICE;
import static com.example.android.itinventoryapps1.data.Contract.ItEntry.COLUMN_QUANTITY;
import static com.example.android.itinventoryapps1.data.Contract.ItEntry.COLUMN_SUPPLIER_NAME;
import static com.example.android.itinventoryapps1.data.Contract.ItEntry.COLUMN_SUPPLIER_PHONE;

public class AddActivity extends AppCompatActivity {

    /**
     * EditText field to enter the it product name
     */
    private EditText mName;

    /**
     * EditText field to enter the price product
     */
    private EditText mPrice;

    /**
     * EditText field to enter the quantity of the product
     */
    private EditText mQuantity;

    /**
     * EditText field to enter the supplier name
     */
    private EditText mSupplierName;

    /**
     * EditText field to enter the supplier phone
     */
    private EditText mSupplierPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        mName = findViewById(R.id.name);
        mPrice = findViewById(R.id.price);
        mQuantity = findViewById(R.id.quantity);
        mSupplierName = findViewById(R.id.supplier_name);
        mSupplierPhone = findViewById(R.id.supplier_phone);

        Button mSaveButton = findViewById(R.id.save_button);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertItProduct();
            }
        });

    }

    /**
     * Get user input from EditText views and save new IT Product into database.
     */
    private void insertItProduct() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String nameString = mName.getText().toString().trim();

        String priceString = mPrice.getText().toString().trim();

        String quantityString = mQuantity.getText().toString().trim();


        String supplierNameString = mSupplierName.getText().toString().trim();

        String supplierPhoneString = mSupplierPhone.getText().toString().trim();

        // Create database helper
        DbHelper mDbHelper = new DbHelper(this);

        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and it products attributes from the editor are the values.
        ContentValues values = new ContentValues();

        //First we check the values from the EditText views, to see if they are not null
        if (nameString.trim().equals("")) {
            mName.setError(getResources().getString(R.string.empty_field_error));
            Toast.makeText(getBaseContext(),COLUMN_NAME + getString(R.string.cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        } else {
            values.put(COLUMN_NAME, nameString);
        }

        if (priceString.trim().equals("")) {
            mPrice.setError(getResources().getString(R.string.empty_field_error));
            Toast.makeText(getBaseContext(),COLUMN_PRICE + " " +getString(R.string.cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        } else {
            float priceFloat = Float.parseFloat(priceString);
            values.put(COLUMN_PRICE, priceFloat);
        }

        if (quantityString.trim().equals("")) {
            mQuantity.setError(getResources().getString(R.string.empty_field_error));
            Toast.makeText(getBaseContext(),COLUMN_QUANTITY + " " + getString(R.string.cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        } else {
            int quantityInt = Integer.parseInt(quantityString);
            values.put(Contract.ItEntry.COLUMN_QUANTITY, quantityInt);
        }

        if (supplierNameString.trim().equals("")) {
            mSupplierName.setError(getResources().getString(R.string.empty_field_error));
            Toast.makeText(getBaseContext(),COLUMN_SUPPLIER_NAME + " " + getString(R.string.cannot_be_empty), Toast.LENGTH_SHORT).show();
            return;
        } else {
            values.put(Contract.ItEntry.COLUMN_SUPPLIER_NAME, supplierNameString);
        }

        if (supplierPhoneString.trim().length() != 10) {
            mSupplierPhone.setError(getResources().getString(R.string.phone_number_error));
            Toast.makeText(getBaseContext(),COLUMN_SUPPLIER_PHONE + " " + getString(R.string.phone_error), Toast.LENGTH_SHORT).show();
            return;
        } else {
            values.put(Contract.ItEntry.COLUMN_SUPPLIER_PHONE, supplierPhoneString);
        }

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(Contract.ItEntry.TABLE_NAME, null, values);

        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, getString(R.string.save_error) + nameString, Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this,getString(R.string.added_msg) + " " + nameString + " " + getString(R.string.row_id) + " " + newRowId, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddActivity.this, MainActivity.class));
        }
    }

}
