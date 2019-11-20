package be.thomasmore.stockwatch;

import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).addToBackStack(null).commit();
            navigationView.setCheckedItem(R.id.home);
        }


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new HomeFragment()).addToBackStack(null).commit();
                toolbar.setTitle("Home");
                break;
            case R.id.nav_my_stocks:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new MyStocksFragment()).addToBackStack(null).commit();
                toolbar.setTitle("My stocks");
                break;
            case R.id.nav_favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoritesFragment()).addToBackStack(null).commit();
                toolbar.setTitle("Favorites");
                break;
            case R.id.nav_compare:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new CompareFragment()).addToBackStack(null).commit();
                toolbar.setTitle("Compare");
                break;
            case R.id.nav_info:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new InfoFragment()).addToBackStack(null).commit();
                toolbar.setTitle("Info");
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if ((getSupportFragmentManager().getBackStackEntryCount() > 0))
            {
                if (currentFragment instanceof HomeFragment){
                    System.exit(1);
                } else{
                    getSupportFragmentManager().popBackStack();
                }
            } else{
                super.onBackPressed();
            }
        }
    }

}
