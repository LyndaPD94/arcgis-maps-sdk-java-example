package arc.gis.exampleActivity;


import static arc.gis.exAmpleactivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.esri.arcgisruntime.ArcGISRuntimeEnvironment;
import com.esri.arcgisruntime.mapping.ArcGISMap;
import com.esri.arcgisruntime.mapping.Basemap;
import com.esri.arcgisruntime.mapping.BasemapStyle;
import com.esri.arcgisruntime.mapping.view.MapView;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.time.MonthDay;
import java.util.Calendar;
import java.util.Locale;


@SuppressWarnings("ALL")
public class ArcGISActivity extends AppCompatActivity {

    EditText date, month, year, id;
    String arcapikey="YOUR_API_KEY";
 
   private MapView mapView;

    public void request() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.policy_message)).setPositiveButton(getString(R.string.accept), (dialogInterface, i) -> {
                    final boolean fl = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                    final boolean cl = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
                    Toast.makeText(ArcGISActivity.this, getString(R.string.touchscreen), Toast.LENGTH_LONG).show();
                })
                .setNegativeButton(getString(R.string.deny), (dialogInterface, which) -> {
                    final boolean fl = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED;
                    final boolean cl = ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED;
                    Intent intent = new Intent(ArcGISActivity.this, MainActivity.class);
                    startActivity(intent);
                }).setCancelable(false).show();
    }

    public static boolean checkPermission(final Context context) {
        return ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arcgis);
        ArcGISRuntimeEnvironment.setApiKey(arcapikey);
        MapView mapView=findViewById(R.id.arcid1);
        ArcGISMap map = new ArcGISMap(new Basemap(BasemapStyle.ARCGIS_OCEANS));
        mapView.setMap(map);

        request();

        date = findViewById(R.id.date11a);
        month = findViewById(R.id.month8a);
        year = findViewById(R.id.year8a);
      
       
        EditText date = findViewById(R.id.date11a);
        EditText month = findViewById(R.id.month8a);
        EditText year = findViewById(R.id.year8a);
        Calendar calendar = Calendar.getInstance(Locale.US);
        String Date = String.valueOf(calendar.get(Calendar.DATE));
        String Month = String.valueOf(MonthDay.now().getMonth().getValue());
        String Year = String.valueOf(calendar.get(Calendar.YEAR));
        date.setText(Date);
        month.setText(Month);
        year.setText(Year);
        ImageButton btnZoomIn = findViewById(R.id.btnZoomIna)
    
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mapView != null) {
          
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mapView != null) {
            mapView.dispose();
        }
    }

}
