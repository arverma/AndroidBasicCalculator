package in.ac.iiitmanipur.calc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import java.util.StringTokenizer;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import java.math.BigDecimal;

import static android.R.attr.password;

public class MainActivity extends AppCompatActivity {


    int temp=0,num=0,k=0,j=1;
    String op,total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void opr1(View view) {
        num=1;
        display_input(num);
        j=0;
    }
    public void opr2(View view) {
        num=2;
        display_input(num);
        j=0;
    }
    public void opr3(View view) {
        num=3;
        display_input(num);
        j=0;
    }
    public void opr4(View view) {
        num=4;
        display_input(num);
        j=0;
    }
    public void opr5(View view) {
        num=5;
        display_input(num);
        j=0;
    }
    public void opr6(View view) {
        num=6;
        display_input(num);
        j=0;
    }
    public void opr7(View view) {
        num=7;
        display_input(num);
        j=0;
    }
    public void opr8(View view) {
        num=8;
        display_input(num);
        j=0;
    }
    public void opr9(View view) {
        num=9;
        display_input(num);
        j=0;
    }
    public void opr0(View view) {
       int zero= num=0;
        num=1;
        display_input(zero);
        j=0;
    }

    public void opr_i(View view){
        TextView quantityTextView = (TextView) findViewById(R.id.input);
        total = quantityTextView.getText().toString();
        if(total.length()>2)op = total.substring(total.length()-2,total.length()-1);
        else op="1";
        if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/"))
        {
            total = total.substring(0 , total.length()-3);
            quantityTextView.setText(total);
        }
        else{
        total = total.substring(0 , total.length()-1);
        quantityTextView.setText(total);count=count-1;}
        n1=1;
        if(total.length()==0){display_input(0);total = "0";
            num=0;j=1;
            temp=k=0;result =0;n1=1;
            flag=0;
        }
    }

    char sym;
    public void opr_sum(View view) {
        sym = '\u002B';
        display_opr(sym);

    }
    public void oprX(View view) {
        sym = '\u002A';
        display_opr(sym);
    }
    public void opr_divide(View view) {
        sym = '\u002F';
        display_opr(sym);
    }
    public void opr_minus(View view) {
        sym = '\u002D';
        display_opr(sym);
    }


    double result=0.0;
    int v1,flag=0,n1=1;
   // String password="1234";
    public void opr_equal (View view){
        if(n1==0){
            display_output(n1);
            display_input1(result);
            temp=-1;
            if(result==0){total = "0";num=0;j=1;temp=k=0;result =0;n1=1;flag=0;}
        }
        else{
        TextView quantityTextView = (TextView) findViewById(R.id.input);
        total = quantityTextView.getText().toString();


            if(total.equals("1234")){
                Intent i = new Intent(this, security.class);
                startActivity(i);
                //Log.d("pass", new security().taskList);
            }


        int n=total.length();
       // n1=1;
        String v,str=total;
        op = str.substring(0, 1);
        if(op.equals("/") || op.equals("*")){
            display_incorrect();n=0;n1=0;}
        else{
        if(num!=0 && temp!=-1){StringTokenizer st=new StringTokenizer(total);v1=Integer.parseInt(st.nextToken());result=v1;v=String.valueOf(v1);}
        else if(num!=0 && temp==-1 ){StringTokenizer st=new StringTokenizer(total);double v1=Double.parseDouble(st.nextToken());result=v1; v=String.valueOf(v1);}
        else {v1=0; v=String.valueOf(v1);result=v1;}

        if(n<=v.length()+3){display_output(v);n=0;n1=0;}//j=1;
            else{
            int i=0;
        while(n>0){
           // StringTokenizer st=new StringTokenizer(total);
           // if(temp==-1 && i==0){double v1=Double.parseDouble(st.nextToken());}//checking for double after = =
           // else {int v1=Integer.parseInt(st.nextToken());}
            StringTokenizer st=new StringTokenizer(total);
            double v1=Double.parseDouble(st.nextToken());
            op=st.nextToken();
            int v2=Integer.parseInt(st.nextToken());

            v= String.valueOf(v1);
            if(temp==-1 && i==0) i=v.length()+3;
            else i=v.length()+1;
            v= String.valueOf(v2);
            j=v.length()+3;
            n1=i+j;
            if(op.equals("/") && v2==0) {display_infinite();n=0;n1=0;k=0;}
            if(n>n1){
                total = total.substring(i , total.length());
                String op1=st.nextToken();
                total = total.substring(j , total.length());
                total=v2 + " " + op1 + " " + total;
                n=total.length();
            }
            else
                n=0;
        }}  if(str.length()>2){op=str.substring(str.length()-2,str.length()-1);
            if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") ){j=1;}}
        if(n1!=0){
            op=str.substring(str.length()-2,str.length()-1);
            if(op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") ){str=str.substring(0,str.length()-3);}
            Expression e = new ExpressionBuilder(str)
                    .build();
            result = e.evaluate();
            BigDecimal d1 = new BigDecimal(result);
            display_output(d1);
            n1=0;
        }}}}


    public void oprC(View view) {
        total = "0";
        num=0;j=1;
        temp=k=0;result =0;n1=1;
        flag=0;count=0;
        TextView quantityTextView = (TextView) findViewById(R.id.input);
        quantityTextView.setText("" + num);
       // display_output(result);
    }
    int count=0;
    private void display_input(long number) {
        if (count++<9){
        TextView quantityTextView = (TextView) findViewById(R.id.input);
        if(k==0 && flag!=1){
            quantityTextView.setText("" + number);
            k=-1;
        }
        else {quantityTextView.append("" + number);}
            j=0;
    }}
    private void display_input1(double number) {
        while(count++<=9){
        TextView quantityTextView = (TextView) findViewById(R.id.input);
       quantityTextView.setText("" + number);
        j=0;
    }}

    private void display_output(double result) {
        TextView quantityTextView = (TextView) findViewById(R.id.output);
        quantityTextView.setText("" + result);
    }
    private void display_output(BigDecimal result) {
        TextView quantityTextView = (TextView) findViewById(R.id.output);
        quantityTextView.setText("" + result);
    }
    private void display_output(String v) {
        TextView quantityTextView = (TextView) findViewById(R.id.output);
        quantityTextView.setText(v);
    }
    private void display_infinite(){
        TextView quantityTextView = (TextView) findViewById(R.id.output);
        quantityTextView.setText("\u221E");
    }
    private void display_incorrect(){
    TextView quantityTextView = (TextView) findViewById(R.id.output);
        quantityTextView.setText("Incorrect Input");}

    private void display_opr(char symbol) {
        TextView quantityTextView = (TextView) findViewById(R.id.input);
        if(num==0 && j==1){//starting
            total = quantityTextView.getText().toString();
               // total = total.substring(0, total.length() - 1);
                quantityTextView.setText(symbol + "");
            flag=1;
        }
       else if(num!=0 && j==1){//changing from + to - or * to /
            total = quantityTextView.getText().toString();
            total = total.substring(0, total.length() - 2);
            quantityTextView.setText(total + symbol + " ");
        }
        else//between two integer
        quantityTextView.append(" " + symbol + " ");
        n1=1;j=1;count=0;//k=-1;
    }

}
