package com.example.loginscreen.ui.games;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loginscreen.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class ActivitiesActivity extends AppCompatActivity {
    private ImageView activityExit, probBingoImg, coinTossImg1, coinTossImg2, diceRollImg1, diceRollImg2, psrImg1, psrImg2;
    private Button probBingoBtn, coinTossBtn, diceRollBtn, psrBtn;
    private int width = 595, height = 842;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);

        activityExit = findViewById(R.id.activityExit);
        probBingoImg = findViewById(R.id.probBingoImg);
        coinTossImg1 = findViewById(R.id.coinTossImg1);
        coinTossImg2 = findViewById(R.id.coinTossImg2);
        diceRollImg1 = findViewById(R.id.diceRollImg1);
        diceRollImg2 = findViewById(R.id.diceRollImg2);
        psrImg1 = findViewById(R.id.psrImg1);
        psrImg2 = findViewById(R.id.psrImg2);
        probBingoBtn = findViewById(R.id.probBingoBtn);
        coinTossBtn = findViewById(R.id.coinTossBtn);
        diceRollBtn = findViewById(R.id.diceRollBtn);
        psrBtn = findViewById(R.id.psrBtn);

        activityExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        probBingoBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Bitmap[] bitmaps = {BitmapFactory.decodeResource(getResources(), R.drawable.probability_bingo)};
                String fileName = "probability_bingo.pdf";
                convertToPdf(bitmaps, fileName);
            }
        });

        coinTossBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Bitmap[] bitmaps = {
                        BitmapFactory.decodeResource(getResources(), R.drawable.coin_toss_investigation1),
                        BitmapFactory.decodeResource(getResources(), R.drawable.coin_toss_investigation2)
                };
                String fileName = "coin_toss_investigation.pdf";
                convertToPdf(bitmaps, fileName);
            }
        });

        diceRollBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Bitmap[] bitmaps = {
                        BitmapFactory.decodeResource(getResources(), R.drawable.dice_roll_investigation1),
                        BitmapFactory.decodeResource(getResources(), R.drawable.dice_roll_investigation2)
                };
                String fileName = "dice_roll_investigation.pdf";
                convertToPdf(bitmaps, fileName);
            }
        });

        psrBtn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                Bitmap[] bitmaps = {
                        BitmapFactory.decodeResource(getResources(), R.drawable.paper_scissors_rock1),
                        BitmapFactory.decodeResource(getResources(), R.drawable.paper_scissors_rock2)
                };
                String fileName = "paper_scissors_rock.pdf";
                convertToPdf(bitmaps, fileName);
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void convertToPdf(Bitmap[] bitmaps, String fileName) {
        PdfDocument pdfDocument = new PdfDocument();
        for (int i = 0; i < bitmaps.length; i++) {
            Bitmap bitmap = bitmaps[i];
            PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), i+1).create();
            PdfDocument.Page page = pdfDocument.startPage(pageInfo);

            Canvas canvas = page.getCanvas();
            Paint paint = new Paint();
            paint.setColor(Color.parseColor("#ffffff"));
            canvas.drawPaint(paint);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
            canvas.drawBitmap(bitmap, 0, 0, null);
            pdfDocument.finishPage(page);
        }

        if (ContextCompat.checkSelfPermission(ActivitiesActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ActivitiesActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
            return;
        }

        File sdcard = Environment.getExternalStorageDirectory();
        File dir = new File(sdcard.getAbsolutePath() + "/Download/Probquest Files");
        if (!dir.exists()) {
            dir.mkdirs();
        }

        File file = new File(dir, fileName);
        try {
            pdfDocument.writeTo(Files.newOutputStream(file.toPath()));
            pdfDocument.close();
            Toast.makeText(ActivitiesActivity.this, "PDF saved to: " + file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(ActivitiesActivity.this, "Error in creating file..."+e, Toast.LENGTH_SHORT).show();
        }
    }
}