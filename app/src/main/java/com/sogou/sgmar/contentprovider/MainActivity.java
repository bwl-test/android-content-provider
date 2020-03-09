package com.sogou.sgmar.contentprovider;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button initDbButton = (Button)findViewById(R.id.initialize_db);
        initDbButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initalizeDB();
            }
        });
    }

    private void initalizeDB() {
        SQLiteDatabase db = LitePal.getDatabase();
        int count = LitePal.count(Book.class);
        if (count > 0) {
            Toast.makeText(this, "bookstore.db already initialized!", Toast.LENGTH_SHORT).show();
            return;
        }

        Book book1 = new Book("计算机技术与实践", "白文磊", 125, 560, "机械工业出版社");
        Book book2 = new Book("草食动物营养学", "王芬", 56, 356, "农业出版社");
        book1.save();
        book2.save();
        Toast.makeText(this, "bookstore.db initialize successed!", Toast.LENGTH_SHORT).show();
    }
}
