package com.fa.ecommerce;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AdminCatActivity extends AppCompatActivity {
    private StorageReference ProductImageRef;
    private DatabaseReference productDatabaseRef;

    List<String> list = new ArrayList<>();
    private Button addNew;
    private EditText productName,productHarga,productDesc;
    private ImageView productPict;

    String selectedCategory,saveCurrDate,saveCurrTime;
    String Price,Desc,Name,productKey,downloadImageUrl;

    private ProgressDialog loadingBar;

    private static final int GalleryPick=1;
    private Uri ImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_cat);
        final Spinner chl=findViewById(R.id.chkb_listview);

        list = getCategories(chl);
        ProductImageRef = FirebaseStorage.getInstance().getReference().child("Product Images");
        productDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Products");

        addNew = (Button) findViewById(R.id.AddProduct);
        productName = (EditText) findViewById(R.id.nama_produk);
        productHarga = (EditText) findViewById(R.id.harga_product);
        productDesc = (EditText) findViewById(R.id.desc_product);
        productPict = (ImageView)findViewById(R.id.upload_image);
        loadingBar = new ProgressDialog(this);



        productPict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidateData();
            }
        });

        chl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCategory = chl.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(),selectedCategory,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
         });
    }

    private void ValidateData() {
        Desc = productDesc.getText().toString();
        Name = productName.getText().toString();
        Price = productName.getText().toString();

        if(ImageUri == null){
            Toast.makeText(this,"Product Image is Empty",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(Name)){
            Toast.makeText(this,"Product Name is Empty",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(Price)){
            Toast.makeText(this,"Product Price is Empty",Toast.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(Desc)){
            Toast.makeText(this,"Product Description is Empty",Toast.LENGTH_SHORT).show();
        }else{
            StoreProductInfo();
        }
    }

    private void StoreProductInfo() {

        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Please Wait...");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currDate = new SimpleDateFormat("DD MM YYYY");
        saveCurrDate = currDate.format(calendar.getTime());

        SimpleDateFormat currTime = new SimpleDateFormat("HH:MM:ss a");
        saveCurrTime = currDate.format(calendar.getTime());

        productKey = saveCurrDate + saveCurrTime;

        final StorageReference filePath = ProductImageRef.child(ImageUri.getLastPathSegment()  + productKey +".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);

        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AdminCatActivity.this,"Upload Failed",Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Toast.makeText(AdminCatActivity.this,"Upload Success",Toast.LENGTH_SHORT).show();

                Task<Uri> uriTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if(!task.isSuccessful())
                            throw task.getException();
                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        downloadImageUrl = task.getResult().toString();

                        Toast.makeText(AdminCatActivity.this,"Get Download URL Success",Toast.LENGTH_SHORT).show();
                        SaveProductInfoToFirebase();
                    }
                });
            }
        });
    }

    private void SaveProductInfoToFirebase() {
        HashMap<String,Object> productMap = new HashMap<>();
        productMap.put("pid",productKey);
        productMap.put("date",saveCurrDate);
        productMap.put("time",saveCurrTime);
        productMap.put("description",Desc);
        productMap.put("pimage",downloadImageUrl);
        productMap.put("category",selectedCategory);
        productMap.put("price",productHarga);
        productMap.put("name",productName);

        productDatabaseRef.child(productKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){

                            Intent intent = new Intent(AdminCatActivity.this,AdminDasboadActivity.class);
                            startActivity(intent);
                            finish();

                            loadingBar.dismiss();
                            Toast.makeText(AdminCatActivity.this,"Product Added success",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(AdminCatActivity.this,"error" + message,Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void OpenGallery(){

        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(Intent.createChooser(galleryIntent,"select picture..."),GalleryPick);

        /*
        Intent getIntent = new Intent(Intent.ACTION_GET_CONTENT);
        getIntent.setType("image/*");

        Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        Intent chooserIntent = Intent.createChooser(getIntent,"select image");
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, new Intent[] {pickIntent});
        startActivityForResult(chooserIntent,GalleryPick);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==GalleryPick && resultCode == RESULT_OK && data != null){
            ImageUri = data.getData();
            productPict.setImageURI(ImageUri);
        }
    }

    private List<String> getCategories(final Spinner chl){
        final List<String> items = new ArrayList<String>();

        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();

        RootRef.child("Categories").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot:dataSnapshot.getChildren()){
                    items.add( postSnapshot.getValue(String.class));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(AdminCatActivity.this,android.R.layout.simple_spinner_item,list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                chl.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return items;
    }
}
