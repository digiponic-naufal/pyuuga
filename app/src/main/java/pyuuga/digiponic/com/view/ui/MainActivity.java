package pyuuga.digiponic.com.view.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import pyuuga.digiponic.com.R;
import pyuuga.digiponic.com.model.CategoryData;
import pyuuga.digiponic.com.model.InvoiceData;
import pyuuga.digiponic.com.model.MenuData;
import pyuuga.digiponic.com.utils.RecyclerViewHelper;
import pyuuga.digiponic.com.view.adapter.CategoryAdapter;
import pyuuga.digiponic.com.view.adapter.InvoiceAdapter;
import pyuuga.digiponic.com.view.adapter.MenuAdapter;
import pyuuga.digiponic.com.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, RecyclerViewHelper.ItemClickListener {

    // Content
    private RecyclerView category_rv;
    private RecyclerView menu_rv;
    private RecyclerView invoice_rv;
    private TextView total_harga;

    // Adapter
    private CategoryAdapter categoryAdapter;
    private MenuAdapter menuAdapter;
    private InvoiceAdapter invoiceAdapter;

    // Dataset
    private List<CategoryData> mCategoryData = new ArrayList<>();
    private List<MenuData> mMenuData = new ArrayList<>();
    private List<InvoiceData> mInvoiceData = new ArrayList<>();

    // View Model
    private MainActivityViewModel mMainActivityViewModel;

    // Utils
    private int PAGE_STATE = 1;
    private Locale localeID = new Locale("in", "ID");
    private NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        total_harga = findViewById(R.id.text_total);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mMainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        mMainActivityViewModel.init();

        mMainActivityViewModel.getCategoryData().observe(this, new Observer<List<CategoryData>>() {
            @Override
            public void onChanged(@Nullable List<CategoryData> categoryData) {
                categoryAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getInvoiceData().observe(this, new Observer<List<InvoiceData>>() {
            @Override
            public void onChanged(@Nullable List<InvoiceData> invoiceData) {
                invoiceAdapter.notifyDataSetChanged();
            }
        });

        mMainActivityViewModel.getTotalHarga().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                total_harga.setText(String.valueOf(mMainActivityViewModel.getTotalHarga().getValue()));
            }
        });



        // Setup Recyclerview
        setAdapterRV();

    }

    private void setAdapterRV() {
        // Category Adapter
        category_rv = findViewById(R.id.rv_category);
        category_rv.setLayoutManager(new LinearLayoutManager(this));
        categoryAdapter = new CategoryAdapter(this, mMainActivityViewModel.getCategoryData().getValue());
        categoryAdapter.notifyDataSetChanged();
        category_rv.setAdapter(categoryAdapter);

        // Menu Adapter
        menu_rv = findViewById(R.id.menu_rv);
        int ColumnCount = 3;
        menu_rv.setLayoutManager(new GridLayoutManager(this, ColumnCount));
        menuAdapter = new MenuAdapter(this, mMainActivityViewModel.getMenuData().getValue());
        menuAdapter.notifyDataSetChanged();
        menuAdapter.setClickListener(this);
        menu_rv.setAdapter(menuAdapter);

        // Invoice Adapter
        invoice_rv = findViewById(R.id.rv_invoice);
        invoice_rv.setLayoutManager(new LinearLayoutManager(this));
        invoiceAdapter = new InvoiceAdapter(this, mMainActivityViewModel.getInvoiceData().getValue());
        invoiceAdapter.notifyDataSetChanged();
        invoice_rv.setAdapter(invoiceAdapter);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, position + "", Toast.LENGTH_SHORT).show();
        mMainActivityViewModel.addItemToInvoice(new InvoiceData(menuAdapter.getItemID(position), menuAdapter.getItemName(position), menuAdapter.getItemPrice(position)));
        invoiceAdapter.notifyDataSetChanged();
    }
}
