package com.example.quanteq.white;


import android.app.Dialog;
import android.content.Context;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainDishStart.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainDishStart#newInstance} factory method to
 * create an instance of this fragment.
 *
 *
 *
 */public class MainDishStart extends Fragment {

    private RecyclerView recApps;
    private DatabaseReference mDatabase;
    private Dialog mDialog;

    ProgressBar progress;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainDishStart.OnFragmentInteractionListener mListener;

    public MainDishStart() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainDishStart.
     */
    // TODO: Rename and change types and number of parameters
    public static MainDishStart newInstance(String param1, String param2) {
        MainDishStart fragment = new MainDishStart();
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
        //Intent tap = new Intent(getActivity(),Menupage.class);
        // startActivity(tap);
        View view = inflater.inflate(R.layout.fragment_main_dish, container, false);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recApps= (RecyclerView)getActivity().findViewById(R.id.recAppM);
        recApps.setHasFixedSize(true);
        recApps.setLayoutManager(new LinearLayoutManager(getActivity()));
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Main");
        //  progress=new ProgressBar(getActivity());
        // progress.setTitle("Loading");
        // progress.setMessage("Syncing");
        //progress.setCancelable(false);
        //  progress.isShown();
        mDialog = new Dialog(getActivity());
        // progress = (ProgressBar)getActivity().findViewById(R.id.progre);
        //  progress.setVisibility(View.VISIBLE);



    }




    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        //  progress.setVisibility(View.GONE);

        // firebaseAuth.removeAuthStateListener(authStateListener);
        FirebaseRecyclerAdapter<Foot,Nenupage.FoodViewHolder> FRBA = new FirebaseRecyclerAdapter<Foot, Nenupage.FoodViewHolder>(

                Foot.class,
                R.layout.singlemenuitem,
                Nenupage.FoodViewHolder.class,
                mDatabase
        ) {
            @Override
            protected void populateViewHolder(Nenupage.FoodViewHolder viewHolder, Foot model, int position) {

                viewHolder.setName(model.getName());
                viewHolder.setPrice(model.getPrice());
                viewHolder.setDesc(model.getDesc());
                viewHolder.setImage(getActivity().getApplicationContext(),model.getImage());

                final String food_Key = getRef(position).getKey();
                viewHolder.apans.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView close;
                        Button ord,car;

                        mDialog.setContentView(R.layout.pop_up);
                        close = (TextView)mDialog.findViewById(R.id.close);
                        ord = (Button)mDialog.findViewById(R.id.request);
                        car = (Button)mDialog.findViewById(R.id.carter);


                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mDialog.dismiss();
                            }
                        });

                        ord.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "This feature is available for registered users only!", Toast.LENGTH_SHORT).show();
                            }
                        });
                        car.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), "This feature is available for registered users only!", Toast.LENGTH_SHORT).show();
                            }
                        });

                        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mDialog.show();
                    }
                });

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Please login to view content", Toast.LENGTH_SHORT).show();
                        //Intent single = new Intent(getActivity(), orderpageM.class);
                        //single.putExtra("FoodID",food_Key);
                       // startActivity(single);
                    }
                });


            }
        };
        recApps.setAdapter(FRBA);
    }
    public static class FoodViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView apans;
        public FoodViewHolder(View itemView){
            super(itemView);
            mView = itemView;
            apans = (TextView)mView.findViewById(R.id.OPTIONpp);

        }
        public void setName(String name){
            TextView food_name = (TextView)mView.findViewById(R.id.foodName);
            food_name.setText(name);

        }
        public void setDesc(String desc){
            TextView food_desc = (TextView)mView.findViewById(R.id.foodDesc);
            food_desc.setText(desc);

        }
        public void setPrice(String price){
            TextView food_price = (TextView)mView.findViewById(R.id.foodPrice);
            food_price.setText(price);

        }
        public void setImage(Context ctx, String image) {
            ImageView food_image = (ImageView) mView.findViewById(R.id.foodImage);
            Picasso.with(ctx).load(image).into(food_image);


        }

    }
}





