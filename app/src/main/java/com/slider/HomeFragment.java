package com.slider;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class HomeFragment extends Fragment {


    public static final String ROOT_URL1 = "http://wonderhomes.co/interior/itemlist.php";


    RelatedProductAdapter relatedProductAdapter;


    ArrayList<DataModelRelatedProducts> data1 = new ArrayList<>();
    String iD, imageName, cateGory, imageUrl, imageShareUrl, desCription;
    RecyclerView recyclerView, recyclerviewHorizontal;
    private static RecyclerView.LayoutManager layoutManager;

    ProgressDialog mProgressDialog;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View x = inflater.inflate(R.layout.home_layout, container, false);


        x.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Loading........");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);


        insertUserShop();

        recyclerviewHorizontal = (RecyclerView) x.findViewById(R.id.recyclerviewHorizontal);
        recyclerviewHorizontal.setHasFixedSize(true);

        relatedProductAdapter = new RelatedProductAdapter(data1, getActivity());
        recyclerviewHorizontal.setAdapter(relatedProductAdapter);


        return x;


    }


    @Override
    public void onResume() {

        super.onResume();

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);

                    getActivity().finish();
                    System.exit(0);

                    return true;
                }

                return false;
            }
        });
    }


    private void insertUserShop() {
        mProgressDialog.show();

        String itemlist = "itemlist";

        RestAdapter adapter = new RestAdapter.Builder()

                .setEndpoint(ROOT_URL1) //Setting the Root URL
                .build(); //Finally building the adapter


        RelatedProductAPI api = adapter.create(RelatedProductAPI.class);


        api.insertUser(


                itemlist,

                new Callback<Response>() {
                    @Override
                    public void success(Response result, Response response) {
                        mProgressDialog.dismiss();

                        BufferedReader reader = null;


                        String output = "";


                        try {

                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                            output = reader.readLine();

                            if (output == null) {
                                Toast.makeText(getContext(), "Server error", Toast.LENGTH_SHORT).show();
                                mProgressDialog.dismiss();
                            } else {


                                JSONArray mylist = new JSONArray(output);
                                String ProductId = "", Productname = "", Price = "", ImageUrl = "", DummyPrice = "", Description = "";
                                for (int i = 0; i < mylist.length(); i++) {
                                    JSONObject json = (JSONObject) mylist.get(i);

                                    ProductId = json.optString("ID");

                                    ImageUrl = json.optString("imagPath");

                                    Productname = json.optString("itemName");

                                    Price = json.optString("price");

                                    DummyPrice = json.optString("dummyprice");

                                    imageUrl = ImageUrl.replace("\\", "");


                                    DataModelRelatedProducts dm = new DataModelRelatedProducts(ProductId, imageUrl, Productname, Price, DummyPrice);
                                    data1.add(dm);


                                    layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                                    recyclerviewHorizontal.setLayoutManager(layoutManager);


                                    mProgressDialog.dismiss();

                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error) {
                        mProgressDialog.dismiss();

                    }


                }


        );


    }


}
















