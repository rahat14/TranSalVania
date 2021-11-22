package com.metacoder.transalvania.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.metacoder.transalvania.R;
import com.metacoder.transalvania.databinding.ActivityMainBinding;
import com.metacoder.transalvania.ui.fragments.fragment_adapter;
import com.metacoder.transalvania.ui.services.HotelPage;

public class MainActivity extends AppCompatActivity {
    FirebaseUser firebaseUser;
    private ActivityMainBinding binding;
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =

            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()) {
                        case R.id.home_fragment:
                            binding.viewpager.setCurrentItem(0, false);
                            break;

                        case R.id.search_fragment:
                            binding.viewpager.setCurrentItem(1, false);
                            break;

                        case R.id.profile_fragment:
                            firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                            if (firebaseUser != null) {
                                binding.viewpager.setCurrentItem(2, false);
                            } else {
                                Toast.makeText(getApplicationContext(), "You Are Not Logged In", Toast.LENGTH_LONG).show();
                            }


                            break;

                    }

                    return true;

                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ActionBarDrawerToggle drawerToggle = setupDrawerToggle();

        binding.viewpager.setAdapter(new fragment_adapter(this));
        binding.viewpager.setUserInputEnabled(false);
        binding.bottomNav.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        binding.viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        binding.bottomNav.getMenu().findItem(R.id.home_fragment).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNav.getMenu().findItem(R.id.search_fragment).setChecked(true);
                        break;
                    case 2:
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        if (firebaseUser != null) {
                            binding.bottomNav.getMenu().findItem(R.id.profile_fragment).setChecked(true);
                        } else {
                            Toast.makeText(getApplicationContext(), "You Are Not Logged In", Toast.LENGTH_LONG).show();
                        }

                        break;


                }

                super.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });

        // Setup toggle to display hamburger icon with nice animation
        drawerToggle.setDrawerIndicatorEnabled(true);
        drawerToggle.syncState();

        // Tie DrawerLayout events to the ActionBarToggle
        binding.drawer.addDrawerListener(drawerToggle);


        binding.navigation.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId(); // get selected menu item's id
// check selected menu item's id and replace a Fragment Accordingly
            if (itemId == R.id.places) {
                startActivity(new Intent(getApplicationContext(), PlacesCategory.class));

            } else if (itemId == R.id.hotels) {

                startActivity(new Intent(getApplicationContext(), HotelPage.class));
            } else if (itemId == R.id.log_out) {
                FirebaseUser firebase = FirebaseAuth.getInstance().getCurrentUser();
                if (firebase != null) {
                    FirebaseAuth.getInstance().signOut();

                    Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }

            }

            binding.drawer.closeDrawer(GravityCompat.START);

            return true;
        });


        FirebaseUser firebase = FirebaseAuth.getInstance().getCurrentUser();
        if (firebase == null) {
            Menu menu = binding.navigation.getMenu();
            MenuItem nav_dashboard = menu.findItem(R.id.log_out);
            nav_dashboard.setVisible(false);
        }


    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.  ActionBarDrawToggle() does not require it
        // and will not render the hamburger icon without it.
        return new ActionBarDrawerToggle(this, binding.drawer, binding.toolbar, R.string.open, R.string.close);
    }


}