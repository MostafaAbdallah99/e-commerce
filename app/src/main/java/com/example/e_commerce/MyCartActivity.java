package com.example.e_commerce;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import Adapters.RecycleAdapterCartList;
import Adapters.RecycleAdapterProductList;
import Database.Database;
import Model.CartListModel;

public class MyCartActivity extends AppCompatActivity {

    private static final String QUANTITY = "Quantity:";

    ArrayList<CartListModel> cartListModels;
    RecyclerView recyclerview;
    RecycleAdapterCartList mAdapter2;
    Integer[] product_images_mobiles = {R.drawable.s9plus,R.drawable.iphnx,R.drawable.googlepixel,R.drawable.vivo11};
    Integer[] product_images_laptops = {R.drawable.rsz_1images_4, R.drawable.rsz_images_3, R.drawable.rsz_images_5, R.drawable.rsz_images_6};
    Integer[] add = {R.drawable.ic_plus,R.drawable.ic_plus,R.drawable.ic_plus,R.drawable.ic_plus};
    Integer[] delete = {R.drawable.ic_delete,R.drawable.ic_delete,R.drawable.ic_delete,R.drawable.ic_delete};
    Integer[] minus = {R.drawable.ic_minus,R.drawable.ic_minus,R.drawable.ic_minus,R.drawable.ic_minus};
    int totalPrice = 0;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cart);
        TextView checkout = findViewById(R.id.checkout);
        Database eCommerceDatabase = Database.getInstance(MyCartActivity.this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        recyclerview = findViewById(R.id.recyclerview33);
        cartListModels = new ArrayList<>();
        for (int i = 0; i < RecycleAdapterProductList.chosenProducts.size(); i++) {
            String title = RecycleAdapterProductList.chosenProducts.get(i);
            int category = eCommerceDatabase.productsDAO().getCategory(title);
            String price = eCommerceDatabase.productsDAO().getPrice(category, title);
            int proID = eCommerceDatabase.productsDAO().getProduct(title);
            int quantity = eCommerceDatabase.productsDAO().getQuantity(title);
            CartListModel beanClassForRecyclerView_contacts;
            if(category == 1){
                beanClassForRecyclerView_contacts = new CartListModel(product_images_mobiles[proID - 1], RecycleAdapterProductList.chosenProducts.get(i), price, add[i], minus[i], delete[i], QUANTITY + quantity, "1");
            }
            else{
                beanClassForRecyclerView_contacts = new CartListModel(product_images_laptops[proID - 5], RecycleAdapterProductList.chosenProducts.get(i), price, add[i], minus[i], delete[i], QUANTITY + quantity, "1");
            }
            cartListModels.add(beanClassForRecyclerView_contacts);
        }
        mAdapter2 = new RecycleAdapterCartList(this, cartListModels);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MyCartActivity.this);
        recyclerview.setLayoutManager(mLayoutManager);
        recyclerview.setItemAnimator(new DefaultItemAnimator());
        recyclerview.setAdapter(mAdapter2);

        checkout.setOnClickListener(v -> {
            totalPrice = 0;
            AlertDialog.Builder builder
                    = new AlertDialog
                    .Builder(MyCartActivity.this);
            for(int i = 0 ;i<RecycleAdapterCartList.modifiedPrices.size();i++){
                totalPrice += Integer.parseInt(RecycleAdapterCartList.modifiedPrices.get(i));
            }
            builder.setMessage("Do you want to checkout ? \n Your total price is " + totalPrice);
            builder.setTitle("Checkout");
            builder.setCancelable(false);
            builder.setPositiveButton(
                            "Yes",
                    (dialog, which) -> {
                        Intent intent = new Intent(MyCartActivity.this, MapsActivity.class);
                        startActivity(intent);
                    });

            builder.setNegativeButton(
                            "No",
                    (dialog, which) -> dialog.cancel());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });

    }
}
