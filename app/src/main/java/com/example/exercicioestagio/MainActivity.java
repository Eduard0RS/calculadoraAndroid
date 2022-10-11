package com.example.exercicioestagio;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;


class Employee{
    static String id="";
    static String expression="";
    static int flag=0;
}

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Employee.id="";
        Employee.expression="";

    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_info:
                Intent x= new Intent(MainActivity.this,MainActivity2.class);
                startActivity(x);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    private int verify(){
        if (Employee.id.isEmpty()){
            return 0;
        }else{
            return 1;
        }
    }

    public void add_list(double args){
            final TextView expression=findViewById(R.id.expression);
            expression.setText(Employee.expression+args);
    }
    public void add_id(String args){
        Employee.id+=args;
    }
    public void clear(View view){

        Employee.expression="";
        Employee.id="";
        final TextView expression=findViewById(R.id.expression);
        final TextView currentNumber=findViewById(R.id.currentNumber);

        expression.setText("0");
        currentNumber.setText("0");
    }
    public void bracket(View view){
            final TextView currentNumber = findViewById(R.id.currentNumber);
            add_id("(");
            currentNumber.setText(Employee.id);
    }
    public void bracket2(View view){
        final TextView currentNumber = findViewById(R.id.currentNumber);
        add_id(")");
        currentNumber.setText(Employee.id);
    }
    public void back(View view){
        int x=verify();
        if (x==1){
            final TextView currentNumber=findViewById(R.id.currentNumber);
            Employee.id = Employee.id.substring(0,Employee.id.length() - 1);
            currentNumber.setText(Employee.id);
        }
    }
    public void div(View view){
        final TextView currentNumber=findViewById(R.id.currentNumber);
        add_id("/");
        currentNumber.setText(Employee.id);
    }
    public void number(View view){
        int id = view.getId();
        String idStr=getResources().getResourceName(id);
        final TextView currentNumber=findViewById(R.id.currentNumber);
        String lastCharacter = idStr.substring(idStr.length() - 1);
        Employee.id += lastCharacter;
        currentNumber.setText(Employee.id);
    }
    public void mult(View view){
        final TextView currentNumber=findViewById(R.id.currentNumber);
        add_id("*");
        currentNumber.setText(Employee.id);
    }
    public void sub(View view){
        final TextView currentNumber=findViewById(R.id.currentNumber);
        add_id("-");
        currentNumber.setText(Employee.id);
    }
    public void add(View view){
        final TextView currentNumber=findViewById(R.id.currentNumber);
        add_id("+");
        currentNumber.setText(Employee.id);
        //add_list();
    }
    public void result(View view){
        int x=verify();
        final TextView currentNumber = findViewById(R.id.currentNumber);
        try {
            if (x == 1) {

                Expression expression = new ExpressionBuilder(Employee.id).build();
                double resultado = expression.evaluate();
                Employee.expression += Employee.id + "=";
                Employee.id = Double.toString(resultado);
                currentNumber.setText(Employee.id);
                add_list(resultado);
            }
        } catch (ArithmeticException e) {
            currentNumber.setText("Error");
            Employee.id="";
            Employee.expression="";
        }
        catch(Exception e){
            currentNumber.setText("Error");

            Employee.id="";
            Employee.expression="";
        }
    }
    public void sep(View view){
        final TextView currentNumber=findViewById(R.id.currentNumber);
        add_id(".");
        currentNumber.setText(Employee.id);
    }

}