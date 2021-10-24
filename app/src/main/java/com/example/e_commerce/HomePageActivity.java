package com.example.e_commerce;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import Adapters.RecycleAdapterProductList;
import Database.Database;
import Model.BarcodeModelClass;
import Database.Products;
import Model.ProductListModelClass;
import Database.Categories;

public class HomePageActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_SPEECH_INPUT = 1000;
    private static final String PRODUCT = "Please, provide a product name";
    private static final String CODE_SCANNING = "Scanning code...";
    private static final String MESSAGE = "Hi speak something";
    private static final String RESULT_SCANNING = "Scanning result";
    private static final String POSITIVE_MESSAGE = "Scan again";
    private static final String NEGATIVE_MESSAGE = "finish";
    private static final String NO_RESULT = "No Results";

    ArrayList<ProductListModelClass> productListModelClasses;
    RecyclerView recyclerview;
    RecycleAdapterProductList mAdapter2;
    Integer[] product_images_mobiles = {R.drawable.s9plus,R.drawable.iphnx,R.drawable.googlepixel,R.drawable.vivo11};
    Integer[] product_images_laptops = {R.drawable.rsz_1images_4, R.drawable.rsz_images_3, R.drawable.rsz_images_5, R.drawable.rsz_images_6};
    List<String> titles;
    Integer[] add = {R.drawable.ic_add,R.drawable.ic_add,R.drawable.ic_add,R.drawable.ic_add};
    EditText searchText;


    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        searchText = findViewById(R.id.searchText);
        ImageView barcodeSearch = findViewById(R.id.searchBarcode);
        ImageView voiceSearch = findViewById(R.id.searchVoice);
        ImageView textSearch = findViewById(R.id.searchWrite);
        Database eCommerceDatabase = Database.getInstance(HomePageActivity.this);
        ImageView categoryMenu = findViewById(R.id.navigation_menu);
        TextView cart_num = findViewById(R.id.num_product);
        ImageView shoppingCart = findViewById(R.id.shopping_cart);
        cart_num.setText(String.valueOf(RecycleAdapterProductList.addCart + Integer.parseInt(cart_num.getText().toString())));
        Categories mobile_category = new Categories();
        Categories laptop_category = new Categories();
        Products first_product = new Products();
        Products second_product = new Products();
        Products third_product = new Products();
        Products fourth_product = new Products();
        Products fifth_product = new Products();
        Products sixth_product = new Products();
        Products seventh_product = new Products();
        Products eighth_product = new Products();
        try{
            mobile_category.setCatName("Mobiles");
            eCommerceDatabase.categoriesDAO().insertCategory(mobile_category);
            laptop_category.setCatName("Laptops");
            eCommerceDatabase.categoriesDAO().insertCategory(laptop_category);
            first_product.setPrice("1000");
            first_product.setQuantity(3);
            first_product.setCatID(1);
            first_product.setProName("Samsung 9 Plus");
            second_product.setPrice("2000");
            second_product.setQuantity(3);
            second_product.setCatID(1);
            second_product.setProName("I Phone X");
            third_product.setPrice("3000");
            third_product.setQuantity(3);
            third_product.setCatID(1);
            third_product.setProName("Google Pixel 3");
            fourth_product.setPrice("4000");
            fourth_product.setQuantity(3);
            fourth_product.setCatID(1);
            fourth_product.setProName("Vivo 11");
            fifth_product.setPrice("5000");
            fifth_product.setQuantity(3);
            fifth_product.setCatID(2);
            fifth_product.setProName("Dell");
            sixth_product.setPrice("6000");
            sixth_product.setQuantity(3);
            sixth_product.setCatID(2);
            sixth_product.setProName("HP");
            seventh_product.setPrice("7000");
            seventh_product.setQuantity(3);
            seventh_product.setCatID(2);
            seventh_product.setProName("Apple");
            eighth_product.setPrice("8000");
            eighth_product.setQuantity(3);
            eighth_product.setCatID(2);
            eighth_product.setProName("Acer");
            eCommerceDatabase.productsDAO().insertProduct(first_product);
            eCommerceDatabase.productsDAO().insertProduct(second_product);
            eCommerceDatabase.productsDAO().insertProduct(third_product);
            eCommerceDatabase.productsDAO().insertProduct(fourth_product);
            eCommerceDatabase.productsDAO().insertProduct(fifth_product);
            eCommerceDatabase.productsDAO().insertProduct(sixth_product);
            eCommerceDatabase.productsDAO().insertProduct(seventh_product);
            eCommerceDatabase.productsDAO().insertProduct(eighth_product);

        }catch (Exception e) {
            e.printStackTrace();
        }

        categoryMenu.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(HomePageActivity.this, categoryMenu);
            popupMenu.getMenuInflater().inflate(R.menu.categories, popupMenu.getMenu());
            popupMenu.setOnMenuItemClickListener(item -> {
                switch (item.getItemId()){
                    case R.id.first_category:
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        recyclerview = findViewById(R.id.recyclerview);
                        productListModelClasses = new ArrayList<>();
                        titles = eCommerceDatabase.productsDAO().getProducts(1);

                        for (int i = 0; i < product_images_mobiles.length; i++) {
                            String price = eCommerceDatabase.productsDAO().getPrice(1, titles.get(i));
                            ProductListModelClass beanClassForRecyclerView_contacts =
                                    new ProductListModelClass(product_images_mobiles[i], titles.get(i), add[i], "Price: " + price);
                            productListModelClasses.add(beanClassForRecyclerView_contacts);
                        }

                        mAdapter2 = new RecycleAdapterProductList(HomePageActivity.this,productListModelClasses);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HomePageActivity.this);
                        recyclerview.setLayoutManager(mLayoutManager);
                        recyclerview.setItemAnimator(new DefaultItemAnimator());
                        recyclerview.setAdapter(mAdapter2);
                        break;
                    case R.id.second_category:
                        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                        recyclerview = findViewById(R.id.recyclerview);
                        productListModelClasses = new ArrayList<>();
                        titles = eCommerceDatabase.productsDAO().getProducts(2);

                        for (int i = 0; i < product_images_laptops.length; i++) {
                            String price = eCommerceDatabase.productsDAO().getPrice(2, titles.get(i));
                            ProductListModelClass beanClassForRecyclerView_contacts =
                                    new ProductListModelClass(product_images_laptops[i], titles.get(i), add[i], "Price: " + price);
                            productListModelClasses.add(beanClassForRecyclerView_contacts);
                        }

                        mAdapter2 = new RecycleAdapterProductList(HomePageActivity.this,productListModelClasses);
                        mLayoutManager = new LinearLayoutManager(HomePageActivity.this);
                        recyclerview.setLayoutManager(mLayoutManager);
                        recyclerview.setItemAnimator(new DefaultItemAnimator());
                        recyclerview.setAdapter(mAdapter2);
                        break;
                }
                return true;
            });
            popupMenu.show();
        });


        barcodeSearch.setOnClickListener(view -> scanCode());

        voiceSearch.setOnClickListener(view -> speak());

        textSearch.setOnClickListener(view -> {
            if(searchText.getText().toString().isEmpty()){
                Toast.makeText(HomePageActivity.this, PRODUCT, Toast.LENGTH_LONG).show();
            }
            else{
                Products products = eCommerceDatabase.productsDAO().getProductByVoiceOrText(searchText.getText().toString());
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                recyclerview = findViewById(R.id.recyclerview);
                productListModelClasses = new ArrayList<>();
                ProductListModelClass beanClassForRecyclerView_contacts;
                if(products.getCatID() == 1){
                    beanClassForRecyclerView_contacts = new ProductListModelClass(product_images_mobiles[products.getProID() - 1], products.getProName(), add[0], "Price: " + products.getPrice());
                }
                else{
                    beanClassForRecyclerView_contacts = new ProductListModelClass(product_images_laptops[products.getProID() - 5], products.getProName(), add[0], "Price: " + products.getPrice());
                }
                productListModelClasses.add(beanClassForRecyclerView_contacts);

                mAdapter2 = new RecycleAdapterProductList(HomePageActivity.this,productListModelClasses);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HomePageActivity.this);
                recyclerview.setLayoutManager(mLayoutManager);
                recyclerview.setItemAnimator(new DefaultItemAnimator());
                recyclerview.setAdapter(mAdapter2);
            }
        });

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        recyclerview = findViewById(R.id.recyclerview);
        productListModelClasses = new ArrayList<>();
        titles = eCommerceDatabase.productsDAO().getProducts(1);

        for (int i = 0; i < product_images_mobiles.length; i++) {
            String price = eCommerceDatabase.productsDAO().getPrice(1, titles.get(i));
            ProductListModelClass beanClassForRecyclerView_contacts =
                    new ProductListModelClass(product_images_mobiles[i], titles.get(i), add[i], "Price: " + price);
            productListModelClasses.add(beanClassForRecyclerView_contacts);
        }
        mAdapter2 = new RecycleAdapterProductList(HomePageActivity.this,productListModelClasses);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(HomePageActivity.this);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter2);

        shoppingCart.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), MyCartActivity.class);
            startActivity(intent);
        });
    }

    private void speak(){
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, MESSAGE);

        try{
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
        }
        catch (Exception e){
            Toast.makeText(HomePageActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SPEECH_INPUT) {
                ArrayList<String> result = data != null ? data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) : null;
            assert result != null;
            searchText.setText(result.get(0));
        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() != null) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
                    builder.setMessage(result.getContents());
                    builder.setTitle(RESULT_SCANNING);
                    builder.setPositiveButton(POSITIVE_MESSAGE, (dialogInterface, i) -> scanCode()).setNegativeButton(NEGATIVE_MESSAGE, (dialogInterface, i) -> finish());
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    Toast.makeText(HomePageActivity.this, NO_RESULT, Toast.LENGTH_LONG).show();
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }

    private void scanCode(){
        IntentIntegrator intentIntegrator = new IntentIntegrator(HomePageActivity.this);
        intentIntegrator.setCaptureActivity(BarcodeModelClass.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt(CODE_SCANNING);
        intentIntegrator.initiateScan();
    }
}
