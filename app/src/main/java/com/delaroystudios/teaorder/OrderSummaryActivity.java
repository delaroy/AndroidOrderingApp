package com.delaroystudios.teaorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * Created by delaroy on 6/14/17.
 */
public class OrderSummaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        Toolbar menuToolbar = (Toolbar) findViewById(R.id.order_summary_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.order_summary_title));

        Intent intent = getIntent();
        String teaName = intent.getStringExtra(OrderActivity.EXTRA_TEA_NAME);
        int price = intent.getIntExtra(OrderActivity.EXTRA_TOTAL_PRICE, 0);
        String size = intent.getStringExtra(OrderActivity.EXTRA_SIZE);
        String milkType = intent.getStringExtra(OrderActivity.EXTRA_MILK_TYPE);
        String sugarType = intent.getStringExtra(OrderActivity.EXTRA_SUGAR_TYPE);
        int quantity = intent.getIntExtra(OrderActivity.EXTRA_QUANTITY, 0);

        displayOrderSummary(teaName, price, size, milkType, sugarType, quantity);
    }

    private void displayOrderSummary(String teaName, int price, String size, String milkType, String sugarType, int quantity){

        TextView teaNameTextView = (TextView) findViewById(
                R.id.summary_tea_name);
        teaNameTextView.setText(teaName);

        TextView quantityTextView = (TextView) findViewById(
                R.id.summary_quantity);
        quantityTextView.setText(String.valueOf(quantity));

        TextView sizeTextView = (TextView) findViewById(
                R.id.summary_tea_size);
        sizeTextView.setText(size);

        TextView milkTextView = (TextView) findViewById(
                R.id.summary_milk_type);
        milkTextView.setText(milkType);

        TextView sugarTextView = (TextView) findViewById(
                R.id.summary_sugar_amount);
        sugarTextView.setText(sugarType);

        TextView priceTextView = (TextView) findViewById(
                R.id.summary_total_price);

        String convertPrice = NumberFormat.getCurrencyInstance().format(price);
        priceTextView.setText(convertPrice);

    }

    public void sendEmail(View view){

        String emailMessage = getString(R.string.email_message);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT,
                getString(R.string.order_summary_email_subject));
        intent.putExtra(Intent.EXTRA_TEXT, emailMessage);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

}

