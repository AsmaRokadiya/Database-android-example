package com.example.databaseexample;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText text1,text2,text3,text4,text5;
    Button btnadd,btnupdate,btndelete,btnviewall;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text1=(EditText) findViewById(R.id.editText);
        text2=(EditText) findViewById(R.id.editText2);
        text3=(EditText) findViewById(R.id.editText3);
        text4=(EditText) findViewById(R.id.editText4);
        text5=(EditText) findViewById(R.id.editText5);


        btnadd=(Button)findViewById(R.id.button);
        btnupdate=(Button)findViewById(R.id.button2);
        btndelete=(Button)findViewById(R.id.button3);
        btnviewall=(Button)findViewById(R.id.button4);

        db=new DatabaseHelper(this,"",null,0);
        add_product();
        update_product();
        delete_product();
        view_product();
    }
    private void add_product(){
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_inserted=db.addItems(text2.getText().toString(),text3.getText().toString(),text4.getText().toString(),text5.getText().toString());
                if(is_inserted){

                    Toast.makeText(getApplicationContext(),"Successfully Inserted", Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(getApplicationContext(),"Successfully  Not Inserted", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void update_product(){
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean is_updated=db.updateItems(text1.getText().toString(), text2.getText().toString(),text3.getText().toString(),text4.getText().toString(),text5.getText().toString());
                if(is_updated){

                    Toast.makeText(getApplicationContext(),"Successfully updated", Toast.LENGTH_LONG).show();
                }else{

                    Toast.makeText(getApplicationContext(),"Successfully  Not updated", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void delete_product(){
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer is_deleted = db.deleteItems(text1.getText().toString());
                if(is_deleted>0){
                    Toast.makeText(getApplicationContext(),"Successfully deleted", Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"Successfully not deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void view_product(){
        btnviewall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor red=db.viewAllItems();
                if(red.getCount()==0){
                    showMessage("error","No products found");
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while (red.moveToNext()){
                    buffer.append("id, "+red.getString(0)+"\n");
                    buffer.append("name,"+red.getString(1)+"\n");
                    buffer.append("brand,"+red.getString(2)+"\n");
                    buffer.append("cost,"+red.getString(3)+"\n");
                    buffer.append("qty,"+red.getString(4)+"\n");


                }

                showMessage("data",buffer.toString());
            }
        });
    }

    private void showMessage(String error, String no_products_found) {

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(error);
        builder.setMessage(no_products_found);
        builder.show();
    }


}
