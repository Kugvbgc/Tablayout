package com.khair.tablayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

public class pdf_view extends AppCompatActivity {
    PDFView pdfView;
    ProgressBar progressBar;

    public static String my_pdf="";
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);
        pdfView=findViewById(R.id.pdfView);
        progressBar=findViewById(R.id.progressBar);
        imageView=findViewById(R.id.imageBack);



        progressBar.setVisibility(View.VISIBLE);
        pdfView.fromAsset(my_pdf)
                .onLoad(new OnLoadCompleteListener() {
                    @Override
                    public void loadComplete(int nbPages) {
                        progressBar.setVisibility(View.GONE);

                    }
                })
                .load();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }
}