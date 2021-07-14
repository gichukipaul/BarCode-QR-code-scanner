package io.alienlabs.gichukipaul.barcodescanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    private static final int CODDE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scan();
            }
        });
    }

    private void Scan() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                Toast.makeText(this, "Enable permissions", Toast.LENGTH_SHORT).show();
            }
            request();
        } else {
            Intent intent = new Intent(this, Scannr.class);
            startActivity(intent);
        }
    }

    private void request() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CODDE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODDE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Scan();
        } else {
            Toast.makeText(this, "Enable permissions", Toast.LENGTH_SHORT).show();
            request();
        }
    }
}