package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.myapplication.controller.CustomerController;
import com.example.myapplication.db.DBHandler;
import com.example.myapplication.model.Customer;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class AddCustomer extends Activity implements View.OnClickListener{


    private Button cus_add, photo_btn;
    private String Customer_type;
    ImageView cus_img_view;
    CheckBox Check_daily, Check_random;
    private static final int SELECT_PHOTO=1;
    private static  final int CAPTURE_PHOTO=2;
    private ProgressDialog progressBar;
    private int progressBarStatus=0;
    private Handler progressBarHandler=new Handler();
    private boolean hasImageChanged=false;

    Bitmap thumbnail;
    BitmapDrawable drawable;
    Bitmap bitmap;



    private EditText cusidTV, cusnameTV, cusphoneTV, cusageTV;
    private DBHandler dbHandler = new DBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHandler.openDB();
        setContentView(R.layout.activity_main2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager
                .LayoutParams.FLAG_FULLSCREEN);

        cus_add = (Button) findViewById(R.id.cus_add);
        cusidTV = (EditText) findViewById(R.id.cusidTV);
        cusnameTV = (EditText) findViewById(R.id.cusnameTV);
        cusphoneTV = (EditText) findViewById(R.id.cusphoneTV);
        cusageTV = (EditText) findViewById(R.id.cusageTV);
        Check_daily = (CheckBox) findViewById(R.id.checkDaily);
        Check_random = (CheckBox) findViewById(R.id.checkRandom);
        photo_btn = (Button) findViewById(R.id.photo_btn);
        cus_img_view = (ImageView) findViewById(R.id.image_cus);


        photo_btn.setOnClickListener(this);

        if (androidx.core.content.ContextCompat.checkSelfPermission(AddCustomer.this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                == android.content.pm.PackageManager.PERMISSION_DENIED ||
                androidx.core.content.ContextCompat.checkSelfPermission
                        (AddCustomer.this, android.Manifest.permission.
                                WRITE_EXTERNAL_STORAGE) == android
                        .content.pm.PackageManager.PERMISSION_DENIED) {

            androidx.core.app.ActivityCompat.requestPermissions
                    (AddCustomer.this, new String[]
                                    {android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            1001);
        }else{
            cus_img_view.setEnabled(true);
        }

        dbHandler=new DBHandler(this);


        cus_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int cusID = Integer.parseInt(cusidTV.getText().toString());
                String cusName = cusnameTV.getText().toString();
                String cusPhone = cusphoneTV.getText().toString();
                String cusAge = cusageTV.getText().toString();

                dbHandler.openDB();
                cus_img_view.setDrawingCacheEnabled(true);
                cus_img_view.buildDrawingCache();
                bitmap = cus_img_view.getDrawingCache();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                byte[] data = baos.toByteArray();

                ArrayList<Customer> customerArrayList = new ArrayList<>();

                Customer_type = "";
                if (Check_daily.isChecked())
                    Customer_type = "Daily";
                if (Check_random.isChecked())
                    Customer_type = "Random";

                Customer tmp = new Customer();
                tmp.setCustomerId(cusID);
                tmp.setCustomerName(cusName);
                tmp.setCustomerPhone(cusPhone);
                tmp.setCustomerAge(cusAge);
                tmp.setCheck_type(Customer_type);
                tmp.setImage(data);

                customerArrayList.add(tmp);
                CustomerController.insertCustomer(AddCustomer.this, customerArrayList);

                /**
                 * Bellow code i used to save image in the gallery
                 * */

                drawable=(BitmapDrawable)cus_img_view.getDrawable();
                bitmap=drawable.getBitmap();
                FileOutputStream outputStream=null;

                File sdCard= Environment.getExternalStorageDirectory();
                File directory=new File(sdCard.getAbsolutePath()+"/Customers Photos");
                directory.mkdir();

                String filename=String.format("%d.jpg",System.currentTimeMillis());
                File outFile=new File(directory,filename);

                Toast.makeText(AddCustomer.this,"Image Save Granted",Toast.LENGTH_SHORT)
                        .show();
                try {
                    outputStream=new FileOutputStream(outFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

                    Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    intent.setData(Uri.fromFile(outFile));
                    sendBroadcast(intent);

                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });
    }

    @Override
    public void onClick(final View view) {
        switch(view.getId()){
            case R.id.photo_btn:
                new MaterialDialog.Builder(this)
                        .title("see your Image")
                        .items(R.array.uploadImages)
                        .itemsIds(R.array.itemIds)
                        .itemsCallback((new MaterialDialog.ListCallback() {
                            @Override
                            public void onSelection(MaterialDialog dialog, View itemView,
                                                    int which, CharSequence text) {
                                switch (which){
                                    case 0:
                                        Intent photoPickerIntent=new Intent(Intent.ACTION_PICK);
                                        photoPickerIntent.setType("image/*");
                                        startActivityForResult(photoPickerIntent,SELECT_PHOTO);
                                        break;
                                    case 1:
                                        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        startActivityForResult(intent,CAPTURE_PHOTO);
                                        break;
                                    case 2:
                                        cus_img_view.setImageResource(R.drawable.ic_baseline_image_24);
                                        break;

                                }
                            }
                        }))
                        .show();
                break;

        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode==0){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED
                    && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    cus_img_view.setEnabled(true);
            }
        }
    }

    public void setProgressBar(){

        progressBar=new ProgressDialog(this);
        progressBar.setCancelable(true);
        progressBar.setMessage("Please wait");
        progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressBar.setProgress(0);
        progressBar.setMax(100);
        progressBar.show();
        progressBarStatus=0;
        new Thread(new Runnable() {
            @Override
            public void run() {
                    while(progressBarStatus<100){
                    progressBarStatus+=30;
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    progressBarHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressBarStatus);
                        }
                     });
                    }
                if(progressBarStatus>=100){
                    try{
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    progressBar.dismiss();
                }
            }
        }).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

                    if(requestCode==SELECT_PHOTO){
                    if(resultCode==RESULT_OK){
                    try{
                    final Uri imageUri=data.getData();
                    final InputStream imageStream=getContentResolver().openInputStream(imageUri);
                    final Bitmap selectedImage= BitmapFactory.decodeStream(imageStream);
                    setProgressBar();
                    cus_img_view.setImageBitmap(selectedImage);
                    }catch (FileNotFoundException e){
                    e.printStackTrace();
                    }
                    }
                    }else if(requestCode==CAPTURE_PHOTO){
                    if(resultCode==RESULT_OK){
                    onCaptureImageResults(data);
                    }
                    }
                    }

    private void onCaptureImageResults(Intent data){
        thumbnail=(Bitmap) data.getExtras().get("data");
        setProgressBar();
        cus_img_view.setMaxWidth(200);
        cus_img_view.setImageBitmap(thumbnail);
    }



}
