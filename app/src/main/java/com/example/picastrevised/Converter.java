package com.example.picastrevised;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Converter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Converter extends Fragment implements View.OnClickListener{
    Button btnImg2Pdf;
    int buttonIdPressed;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public Converter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Converter.
     */
    // TODO: Rename and change types and number of parameters
    public static Converter newInstance(String param1, String param2) {
        Converter fragment = new Converter();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_converter, container, false);

        Button img2Pdf = view.findViewById(R.id.btnImgToPdf);
        img2Pdf.setOnClickListener(this);
        Button jpg2Png = view.findViewById(R.id.btnJpgToPng);
        jpg2Png.setOnClickListener(this);
        Button png2Jpg = view.findViewById(R.id.btnPngToJpg);
        png2Jpg.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);
        int Img2pdfID = R.id.btnImgToPdf;
        int Jpg2PngID = R.id.btnJpgToPng;
        int Png2JpgID = R.id.btnPngToJpg;
        switch (view.getId()){
            case R.id.btnImgToPdf:
                Fragment toFragment = Converter_Conversion.newInstance(R.id.btnImgToPdf, Img2pdfID, Jpg2PngID, Png2JpgID);
                transaction.replace(((ViewGroup)getView().getParent()).getId(), toFragment, null);
                transaction.commit();
                break;
            case R.id.btnJpgToPng:
                toFragment = Converter_Conversion.newInstance(R.id.btnJpgToPng, Img2pdfID, Jpg2PngID, Png2JpgID);
                transaction.replace(((ViewGroup)getView().getParent()).getId(), toFragment, null);
                transaction.commit();
                break;
            case R.id.btnPngToJpg:
                toFragment = Converter_Conversion.newInstance(R.id.btnPngToJpg, Img2pdfID, Jpg2PngID, Png2JpgID);
                transaction.replace(((ViewGroup)getView().getParent()).getId(), toFragment, null);
                transaction.commit();
                break;
        }
    }
}