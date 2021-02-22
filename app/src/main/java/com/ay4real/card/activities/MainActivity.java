package com.ay4real.card.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ay4real.card.R;
import com.ay4real.card.databinding.ActivityMainBinding;
import com.ay4real.card.remote.request.ApiClient;
import com.ay4real.card.remote.request.ApiInterface;
import com.ay4real.card.remote.response.FetchResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        pd = new ProgressDialog(MainActivity.this);
        pd.setCancelable(false);

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate()){
                    pd.setMessage("loading...");
                    pd.show();

                    ApiClient.getClient().create(ApiInterface.class).getCardDetails(Integer.parseInt(binding.editText.getText().toString())).enqueue(new Callback<FetchResponse>() {
                        @Override
                        public void onResponse(Call<FetchResponse> call, Response<FetchResponse> response) {
                            if (response.isSuccessful()){
                                binding.scheme.setText(response.body().getScheme());
                                binding.type.setText(response.body().getType());
                                binding.bank.setText(response.body().getBank().get("name"));
                                binding.country.setText(response.body().getCountry().get("name"));
                                binding.length.setText(response.body().getNumber().get("length"));
                                if(response.body().getPrepaid()){
                                    binding.mode.setText("Prepaid");
                                }else{
                                    binding.mode.setText("Postpaid");
                                }
                                binding.scrollView.setVisibility(View.VISIBLE);
                            }else{
                                binding.scrollView.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext(), "Ooops... Card not found.",Toast.LENGTH_LONG).show();
                            }
                            pd.dismiss();
                        }

                        @Override
                        public void onFailure(Call<FetchResponse> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Ooops... Something went wrong.",Toast.LENGTH_LONG).show();
                            binding.scrollView.setVisibility(View.GONE);
                            pd.dismiss();
                        }
                    });
                }
            }
        });
    }

    private boolean validate() {
        if(binding.editText.getText().toString().isEmpty()){
            Toast.makeText(this, "Card number cannot be empty",Toast.LENGTH_LONG).show();
            return false;
        }
        if(binding.editText.getText().toString().length() < 6 || binding.editText.getText().toString().length() > 9){
            Toast.makeText(this, "Card number length must be between 6 and 9 characters",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }
}
