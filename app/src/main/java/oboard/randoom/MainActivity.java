//祖传代码，别动
package oboard.randoom;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import java.util.Random;


public class MainActivity extends Activity {

    public final Random random = new Random();
    public EditText editText_length, editText_out, editText_min, editText_max;
    public LinearLayout linearLayout_length, linearLayout_size;
    public Spinner spinner;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_length = findViewById(R.id.main_edit_length);
        editText_min = findViewById(R.id.main_edit_min);
        editText_max = findViewById(R.id.main_edit_max);
        editText_out = findViewById(R.id.main_edit_out);
        linearLayout_length = findViewById(R.id.main_length);
        linearLayout_size = findViewById(R.id.main_size);
        spinner = findViewById(R.id.main_spinner);

        //EditText只读
        editText_out.setKeyListener(null);

        editText_length.setText("10");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
        adapter.add("按范围生成数字");
        adapter.add("按位数生成数字");
        adapter.add("按位数生成小写字母");
        adapter.add("按位数生成大写字母");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                linearLayout_length.setVisibility(i == 0 ? View.GONE : View.VISIBLE);
                linearLayout_size.setVisibility(i == 0 ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            editText_out.setElevation(10);
    }

    public void onClick(View view) {
        if (spinner.getSelectedItemPosition() == 0) {
            int min = 0, max = 10;
            if (!editText_min.getText().toString().equals(""))
                min = Integer.valueOf(editText_min.getText().toString());
            if (!editText_max.getText().toString().equals(""))
                max = Integer.valueOf(editText_max.getText().toString());
            out(min + random.nextInt(max) + "");
        } else {
            long length = 10;
            if (!editText_length.getText().toString().equals(""))
                length = Long.valueOf(editText_length.getText().toString());

            //输出
            out(RandomUtils.getRandom(RandomUtils.NUMBERS.toCharArray(), length) + "");
        }

    }

    public void out(String s) {
        editText_out.setText(String.format("%s%s%s", editText_out.getText().toString(), "\n", s));
    }
}
