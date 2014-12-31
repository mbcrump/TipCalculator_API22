package michaelcrump.net.tipcalculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;

public class MainActivity extends Activity {

    EditText billAmountEditText;
    TextView percentTextView;
    Button percentUpButton;
    Button percentDownButton;
    TextView tipTextView;
    TextView totalTextView;

    // define an instance variable for tip percent
    private float tipPercent = .15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        calculateAndDisplay();
    }
    private void setupViews() {
        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        percentTextView = (TextView) findViewById(R.id.percentTextView);
        percentUpButton = (Button) findViewById(R.id.percentUpButton);
        percentDownButton = (Button) findViewById(R.id.percentDownButton);
        tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);


        percentDownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPercentDownButtonClick((Button) view);
            }
        });

        percentUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPercentUpButtonClick((Button) view);
            }
        });
    }

    private void onPercentUpButtonClick(Button view) {
        tipPercent = tipPercent + .01f;
        calculateAndDisplay();
    }

    private void onPercentDownButtonClick(Button view) {
        tipPercent = tipPercent - .01f;
        calculateAndDisplay();
    }

    public void calculateAndDisplay() {

        // get the bill amount
        String billAmountString = billAmountEditText.getText().toString();
        float billAmount;
        if (billAmountString.equals("")) {
            billAmount = 0;
        }
        else {
            billAmount = Float.parseFloat(billAmountString);
        }

        // calculate tip and total
        float tipAmount = billAmount * tipPercent;
        float totalAmount = billAmount + tipAmount;

        // display the results with formatting
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipTextView.setText(currency.format(tipAmount));
        totalTextView.setText(currency.format(totalAmount));

        NumberFormat percent = NumberFormat.getPercentInstance();
        percentTextView.setText(percent.format(tipPercent));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
