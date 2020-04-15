package com.example.convert_money;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnClickListener {


    private String inputMoney = "0";
    private String outputMoney = "0";
    //Khai báo các View
    private TextView screenInputMoney;
    private Button btnNumber0;
    private Button btnNumber1;
    private Button btnNumber2;
    private Button btnNumber3;
    private Button btnNumber4;
    private Button btnNumber5;
    private Button btnNumber6;
    private Button btnNumber7;
    private Button btnNumber8;
    private Button btnNumber9;
    private Button btnDot;
    private Button btnBS;
    private Button btnCE;

    List<String> list_view;
    ArrayAdapter<String> adapter;
    Spinner spin1, spin2;
    TextView inputMoneySymbol, conversionUnit, updateNotification, outputMoneySymbol;
    TextView screenOutputMoney;
    Money dollar = new Money("$", 23000.0);
    Money baht = new Money("฿", 718.75);
    Money dong = new Money("₫", 1.0);
    Money nhanDanTe = new Money("¥", 3326.64);
    Money euro = new Money("€", 25731.0);
    Money from = dollar;
    Money to = dong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        setEventClickViews();

        list_view = new ArrayList<>();
        list_view.add("England - Euro");
        list_view.add("United States - Dollar");
        list_view.add("ThaiLand - baht");
        list_view.add("VietNam - Dong");
        list_view.add("China - NhanDanTe");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, list_view);

        spin1 = findViewById(R.id.spinner1);
        spin1.setAdapter(adapter);

        spin2 = findViewById(R.id.spinner2);
        spin2.setAdapter(adapter);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        from = euro;
                        break;
                    case 1:
                        from = dollar;
                        break;
                    case 2:
                        from = baht;
                        break;
                    case 3:
                        from = dong;
                        break;
                    default:
                        from = nhanDanTe;
                        break;
                }
                inputMoneySymbol.setText(from.symbol);
                conversionUnit.setText("1" + from.symbol + " = " + from.getRate(to) + to.symbol);
                outputMoney = String.valueOf((double) Math.round(Double.parseDouble(inputMoney) * from.getRate(to) * 100) / 100);
                screenOutputMoney.setText(outputMoney);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        to = euro;
                        break;
                    case 1:
                        to = dollar;
                        break;
                    case 2:
                        to = baht;
                        break;
                    case 3:
                        to = dong;
                        break;
                    default:
                        to = nhanDanTe;
                        break;
                }
                outputMoneySymbol.setText(to.symbol);
                conversionUnit.setText("1 " + from.symbol + "=" + from.getRate(to) + to.symbol);
                outputMoney = String.valueOf((double) Math.round(Double.parseDouble(inputMoney) * from.getRate(to) * 100) / 100);
                screenOutputMoney.setText(outputMoney);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    // Khởi tạo gán gtri các id cho button tương ứng
    public void initWidget() {
        outputMoneySymbol = findViewById(R.id.outputMoneySymbol);
        inputMoneySymbol = findViewById(R.id.inputMoneySymbol);
        conversionUnit = findViewById(R.id.conversionUnit);
        updateNotification = findViewById(R.id.updateNotification);
        screenInputMoney = findViewById(R.id.screenInputMoney);
        screenOutputMoney = findViewById(R.id.screenOutputMoney);
        btnNumber0 = findViewById(R.id.number0);
        btnNumber1 = findViewById(R.id.number1);
        btnNumber2 = findViewById(R.id.number2);
        btnNumber3 = findViewById(R.id.number3);
        btnNumber4 = findViewById(R.id.number4);
        btnNumber5 = findViewById(R.id.number5);
        btnNumber6 = findViewById(R.id.number6);
        btnNumber7 = findViewById(R.id.number7);
        btnNumber8 = findViewById(R.id.number8);
        btnNumber9 = findViewById(R.id.number9);
        btnDot = findViewById(R.id.btnDot);
        btnBS = findViewById(R.id.nutBS);
        btnCE = findViewById(R.id.nutCE);
    }

    // Lắng nghe sự kiện
    public void setEventClickViews() {
        btnNumber0.setOnClickListener(this);
        btnNumber1.setOnClickListener(this);
        btnNumber2.setOnClickListener(this);
        btnNumber3.setOnClickListener(this);
        btnNumber4.setOnClickListener(this);
        btnNumber5.setOnClickListener(this);
        btnNumber6.setOnClickListener(this);
        btnNumber7.setOnClickListener(this);
        btnNumber8.setOnClickListener(this);
        btnNumber9.setOnClickListener(this);
        btnDot.setOnClickListener(this);
        btnBS.setOnClickListener(this);
        btnCE.setOnClickListener(this);
    }

    //chạy vào hàm onClick khi có sự kiện click
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.nutCE:
                inputMoney = "0";
                outputMoney = "0";
                break;

            // Xoá 1 ký tự vừa nhập
            case R.id.nutBS:
                String newNumber = deleteAString(inputMoney);
                inputMoney = newNumber;
                break;

            // Nối chuỗi các toán hạng và loại bỏ số 0 ở đầu toán hạng
            default:
                if (inputMoney.equals("0")) {
                    inputMoney = "";
                }

                inputMoney += ((Button) v).getText().toString();
                break;
        }
        // xu ly input
        outputMoney = String.valueOf((double) Math.round(Double.parseDouble(inputMoney) * from.getRate(to) * 100) / 100);
        //output
        screenInputMoney.setText(inputMoney);
        screenOutputMoney.setText(outputMoney);

    }

    // Xoá ký tự vừa nhập vào
    public String deleteAString(String number) {
        if (number.length() > 1) {
            String temp = number.substring(0, number.length() - 1);
            return temp;
        } else if (number.length() == 1) {
            return "0";
        }
        return inputMoney;
    }


}
