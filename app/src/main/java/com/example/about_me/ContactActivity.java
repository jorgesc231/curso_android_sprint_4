package com.example.about_me;

import static com.example.about_me.ProjectFragment.ARG_PARAM1;
import static com.example.about_me.ProjectFragment.ARG_PARAM2;
import static com.example.about_me.ProjectFragment.ARG_PARAM3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.about_me.databinding.ActivityContactBinding;
import com.example.about_me.databinding.ActivityMainBinding;

public class ContactActivity extends AppCompatActivity {

    private ActivityContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.contactToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        binding.layoutGithub.setOnClickListener(v -> navigateToWebpage("https://github.com/jorgesc231"));
        binding.layoutLinkedin.setOnClickListener(v -> {
            Toast.makeText(this, "No tengo Linkedin", Toast.LENGTH_LONG).show();
            navigateToWebpage("https://linkedin.com/");
        });
        binding.layoutLlamar.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Hola, ");

            intent.setType("text/plain");

            // Hacer que solo whatsapp pueda recibir el intent, pero el proyecto pide que sea implicito.
            //intent.setPackage("com.whatsapp");

            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, "No tienes Whatsapp", Toast.LENGTH_LONG).show();
            }
        });

        binding.btContactarme.setOnClickListener(v -> {
            binding.btContactarme.setVisibility(Button.GONE);
            initFragment();
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void navigateToWebpage(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Error en el Intent", Toast.LENGTH_LONG).show();
        }
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MessageFragment fragment = new MessageFragment();
        fragmentTransaction.add(R.id.MsgFramentContainer, fragment);
        fragmentTransaction.commit();
    }
}