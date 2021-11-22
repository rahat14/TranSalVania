package com.metacoder.transalvania.ui.services;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.metacoder.transalvania.R;
import com.metacoder.transalvania.databinding.ActivityBikeDetailsBinding;
import com.metacoder.transalvania.models.BikeModel;
import com.metacoder.transalvania.ui.Events.EventDetails;
import com.metacoder.transalvania.ui.SuccessPage;
import com.metacoder.transalvania.utils.Utils;

public class BikeDetails extends AppCompatActivity {
    private ActivityBikeDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBikeDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        BikeModel model = (BikeModel) getIntent().getSerializableExtra("MODEL");

        binding.titleTV.setText(model.getName());
        binding.addressTv.setText(model.getAddress());
        binding.descTv.setText(model.getDesc() + "\n Price Details \n" + model.getRate() + "\n Contact Us : \n"+ model.getContact() );

        Glide.with(getApplicationContext())
                .load(model.getImage())
                .error(R.drawable.placeholder)
                .placeholder(R.drawable.placeholder)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(binding.imageSlider);
        binding.backBtn.setOnClickListener(v -> finish());

        binding.bookNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                if(firebaseUser != null){
                    ProgressDialog dialog = new ProgressDialog(BikeDetails.this);
                    dialog.setMessage("Processing...");
                    dialog.setCancelable(false);
                    dialog.show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dialog.dismiss();
                            startActivity(new Intent(getApplicationContext() , SuccessPage.class));
                        }
                    }, 1200) ;
                }else {
                    Utils.showLOginError(BikeDetails.this);
                }
            }
        });

    }
}