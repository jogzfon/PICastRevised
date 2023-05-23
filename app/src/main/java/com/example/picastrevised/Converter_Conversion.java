package com.example.picastrevised;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.graphics.pdf.PdfRenderer;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Converter_Conversion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Converter_Conversion extends Fragment implements View.OnClickListener{
    ImageView imageView;
    Button startConversion, selectImage;
    Bitmap bitmap;
    boolean toPdf, toPng, toJpg, PdfToPng;
    File pdfFile;
    private final ActivityResultLauncher<Intent> launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
       if(result.getResultCode() == Activity.RESULT_OK){
               Uri photoUri = result.getData().getData();
               String[] filePathColumn = {MediaStore.Images.Media.DATA};

               Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(photoUri, filePathColumn, null, null, null);
               cursor.moveToFirst();

               int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
               String filePath = cursor.getString(columnIndex);
               cursor.close();

               bitmap = BitmapFactory.decodeFile(filePath);
               imageView.setImageBitmap(bitmap);
               startConversion.setClickable(true);
               startConversion.setEnabled(true);
       }
    });

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    // TODO: Rename and change types of parameters
    private int buttonIdPressed, btnImg2PdfID, btnJpg2PngID, btnPng2JpgID, btnPdf2PngID;

    public Converter_Conversion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Converter_Conversion.
     */
    // TODO: Rename and change types and number of parameters
    public static Converter_Conversion newInstance(int param1, int param2, int param3, int param4) {
        Converter_Conversion fragment = new Converter_Conversion();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        args.putInt(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            buttonIdPressed = getArguments().getInt(ARG_PARAM1);
            btnImg2PdfID = getArguments().getInt(ARG_PARAM2);
            btnJpg2PngID = getArguments().getInt(ARG_PARAM3);
            btnPng2JpgID = getArguments().getInt(ARG_PARAM4);
            if(buttonIdPressed == btnImg2PdfID) {
                toPdf = true;
                Toast.makeText(getActivity(), "PDF Conversion", Toast.LENGTH_SHORT).show();
            }
            else if(buttonIdPressed == btnJpg2PngID) {
                toPng = true;
                Toast.makeText(getActivity(), "PNG Conversion", Toast.LENGTH_SHORT).show();
            }
            else{
                toJpg = true;
                Toast.makeText(getActivity(), "JPG Conversion", Toast.LENGTH_SHORT).show();
            }
        }
        else
            Toast.makeText(getActivity(), "Nothing passed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_converter__conversion, container, false);

        imageView = view.findViewById(R.id.imgViewToBeConverted);
        startConversion = view.findViewById(R.id.btnStartConversion);
        startConversion.setOnClickListener(this);
        selectImage = view.findViewById(R.id.btnSelectImage);
        selectImage.setOnClickListener(this);
        return view;
    }

    /*-----Conversion functions start-----*/
    private void createJpg(){
        int num = 0;
        File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "PICasT_PNG_converts");
        if(!root.exists())
            root.mkdir();
        FileOutputStream out;
        try {
            File jpgFile = new File(root, "PICasT_Conversion.jpeg");
            while(jpgFile.exists()){
                num++;
                String filename = "PICasT_Conversion"+num;
                jpgFile = new File(root, filename+".jpeg");
            }
            out = new FileOutputStream(jpgFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(getActivity(), "Conversion successful. Please check your Downloads folder", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    private void convertPng(){
        int num = 0;
        File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "PICasT_PNG_converts");
        if(!root.exists())
            root.mkdir();
        FileOutputStream out;
        try {
            File pngFile = new File(root, "PICasT_Conversion.png");
            while(pngFile.exists()){
                num++;
                String filename = "PICasT_Conversion"+num;
                pngFile = new File(root, filename+".png");
            }
            out = new FileOutputStream(pngFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
            Toast.makeText(getActivity(), "Conversion successful. Please check your Downloads folder", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
    private void createPdf(){
        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pageInfo);

        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawPaint(paint);

        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);

        pdfDocument.finishPage(page);

        //save to device the converted file
        int num = 0;
        File root = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "PICasT_PDF_converts");
        if(!root.exists())
            root.mkdir();
        File file = new File(root, "PICasT_conversion.pdf");
        while(file.exists()){
            num++;
            String filename = "PICasT_conversion"+num;
            file = new File(root, filename+".pdf");
        }
        try{
            pdfDocument.writeTo(new FileOutputStream(file));
            Toast.makeText(getActivity(), "Conversion successful. Please check your Downloads folder", Toast.LENGTH_LONG).show();
        }catch(IOException e){
            e.printStackTrace();
            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        pdfDocument.close();
    }

    private boolean bitmapIsBlankOrWhite(Bitmap bm){ //helper function to determine if there really is a bitmap or not
        if(bm == null)
            return true;

        int w = bm.getWidth();
        int h = bm.getHeight();
        for(int i=0; i<w; i++){
            for(int j=0; j<h; i++){
                int pixel = bm.getPixel(i, j);
                if(pixel!=Color.WHITE)
                    return false;
            }
        }
        return true;
    }
    /*-----Conversion functions end-----*/

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnSelectImage:
                Intent intent;
                if(PdfToPng){
                    intent = new Intent();
                    intent.setType("application/pdf");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                }else
                    intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                launcher.launch(intent);
                break;
            case R.id.btnStartConversion:
                if(toPdf)
                    createPdf();
                else if(toPng)
                    convertPng();
                else if(toJpg)
                    createJpg();
        }
    }
}