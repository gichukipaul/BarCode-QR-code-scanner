package io.alienlabs.gichukipaul.barcodescanner;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Scannr extends AppCompatActivity {
    private static IntentIntegrator integrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scannr);

        init();
    }

    private void init() {
        integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt(" For Flash, use volume up key ");
        integrator.setCaptureActivity(CaptActivity.class);
        integrator.setOrientationLocked(true);
        integrator.setBeepEnabled(true);
        integrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled by user ", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Diallog(result.getContents());
            }

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void Diallog(String contents) {
        new AlertDialog.Builder(Scannr.this)
                .setTitle(" Results ")
                .setMessage(contents)
                .setCancelable(false)
                .setNeutralButton(" CLOSE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Scan Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        init();
                    }
                })
                .show();
    }
}