package com.example.qrdolgozat;

import android.icu.util.Calendar;
import android.os.Build;
import android.os.Environment;

import androidx.annotation.RequiresApi;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Naplozas {

    public static void kiir(String szoveg) throws IOException {
        Date datum = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            datum = Calendar.getInstance().getTime();
        }

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formazottDatum = df.format(datum);

        String sor = String.format("%s,%s", szoveg, formazottDatum);

        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {

            File file = new File(Environment.getExternalStorageDirectory(), "scannedCodes.csv");
            BufferedWriter bw = new BufferedWriter(new FileWriter(file, true));
            bw.append(sor);

            bw.append(System.lineSeparator());
            bw.close();
        }
    }
}
