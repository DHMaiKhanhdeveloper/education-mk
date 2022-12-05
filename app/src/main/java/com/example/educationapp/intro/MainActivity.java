package com.example.educationapp.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.educationapp.R;
import com.example.educationapp.fragments.AssignmentFragment;
import com.example.educationapp.fragments.CameraFragment;
import com.example.educationapp.fragments.CommunityFragment;
import com.example.educationapp.fragments.CoursesFragment;
import com.example.educationapp.fragments.ProfileFragment;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView bottomNavigationView;
    private AssignmentFragment assignmentFragment = new AssignmentFragment();
    private CoursesFragment coursesFragment = new CoursesFragment();
    private CommunityFragment communityFragment = new CommunityFragment();
    private ProfileFragment profileFragment = new ProfileFragment();
    private CameraFragment cameraFragment = new CameraFragment();
    private FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.BottomAppBar);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        floatingActionButton = findViewById(R.id.fab);


        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.Community);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);
        // replace(new CoursesFragment());
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,coursesFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.Courses:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,coursesFragment).commit();
                        return true;
                    //replace(new CoursesFragment());
                    //break;
                    case R.id.Assignment:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,assignmentFragment).commit();
                        return true;
                    //break;
                    case R.id.fab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,cameraFragment).commit();
                        return true;
                    //break;
                    case R.id.Community:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,communityFragment).commit();
                        return true;
                    //break;
                    case R.id.Profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,profileFragment).commit();
                        return true;
                    //break;
                }
                return false;
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.frame,cameraFragment).commit();
            }
        });

    }
}