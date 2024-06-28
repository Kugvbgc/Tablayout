package com.khair.tablayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Home_Fragment extends Fragment {

   ListView listView;
   ArrayList<HashMap<String,String>>arrayList=new ArrayList<>();
   HashMap<String,String>hashMap;
   ProgressBar progressBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView=inflater.inflate(R.layout.fragment_home_, container, false);
        if (container!=null){
            container.removeAllViews();
        }
        listView=myView.findViewById(R.id.listView);
        progressBar=myView.findViewById(R.id.progressBar);




        lodeData("https://abulk77912.000webhostapp.com/apps/e-book%20view.php");





        return myView;
    }
   ///==============================================================================================
public class MyAdapter extends BaseAdapter{

       @Override
       public int getCount() {
           return arrayList.size();
       }

       @Override
       public Object getItem(int position) {
           return null;
       }

       @Override
       public long getItemId(int position) {
           return 0;
       }

       @Override
       public View getView(int position, View convertView, ViewGroup parent) {
           LayoutInflater inflater= (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
           View view=inflater.inflate(R.layout.home_layout,parent,false);
           TextView textView1=view.findViewById(R.id.textView1);
           TextView textView2=view.findViewById(R.id.textView2);
           TextView textView3=view.findViewById(R.id.textView3);
           CardView cardView=view.findViewById(R.id.cadView);



           HashMap<String,String>hashMap1=arrayList.get(position);
           String name=hashMap1.get("righter");
           String tv_tittle=hashMap1.get("tittle");
           String tv_des=hashMap1.get("des");


           textView1.setText(tv_tittle);
           textView2.setText(name);
           textView3.setText(tv_des);

           cardView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (position==0){
                       pdf_view.my_pdf="my_pdf1.pdf";
                       startActivity(new Intent(getActivity(), pdf_view.class));
                   } else if (position==1) {
                       pdf_view.my_pdf="dosshu.pdf.pdf";
                       startActivity(new Intent(getActivity(), pdf_view.class));
                   } else if (position==2) {
                       pdf_view.my_pdf="mypdf1.pdf.pdf";
                       startActivity(new Intent(getActivity(), pdf_view.class));
                   } else if (position==3) {
                       pdf_view.my_pdf="my_pdf3.pdf";
                       startActivity(new Intent(getActivity(), pdf_view.class));
                   }

                   //***********
               }
           });





           return view;
       }
   }

public  void lodeData(String url){

progressBar.setVisibility(View.VISIBLE);
    JsonArrayRequest request=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            Log.d("error",""+response);
            progressBar.setVisibility(View.GONE);

            for (int x=0;x<response.length();x++){
                try {
                    JSONObject jsonObject=response.getJSONObject(x);
                    String tittle=jsonObject.getString("tittle");
                    String righter=jsonObject.getString("righter");
                    String des=jsonObject.getString("des");


                    hashMap=new HashMap<>();
                    hashMap.put("tittle",tittle);
                    hashMap.put("righter",righter);
                    hashMap.put("des",des);
                    arrayList.add(hashMap);

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            MyAdapter myadapter=new MyAdapter();
            listView.setAdapter(myadapter);
            

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            progressBar.setVisibility(View.GONE);
            new AlertDialog.Builder(getActivity())
                    .setTitle("sever error")
                    .setMessage(error.getMessage())
                    .show();

        }
    });

    RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
    requestQueue.add(request);
}





 //==================================================================================================
}