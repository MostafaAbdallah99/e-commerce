package com.example.e_commerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;
import Adapters.EShoppingListRecycleAdapter;
import Model.EShoppingModelClass;

public class EShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eshopping_list);

        ArrayList<EShoppingModelClass> myEShoppingModelClasses = new ArrayList<EShoppingModelClass>();
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.e_shoppingRecyclerView);
        String mainPages[] = {"Sign Up", "Login", "Home Page", "Navigate", "Products List", "Products Grid", "Products Detail", "My Cart", "Add Address", "Payment", "My Orders", "Successful Orders"};

        for(int i = 0;i < mainPages.length;i++){
            EShoppingModelClass shoppingModelClass = new EShoppingModelClass(mainPages[i]);
            myEShoppingModelClasses.add(shoppingModelClass);
        }

        EShoppingListRecycleAdapter shoppingListRecycleAdapter = new EShoppingListRecycleAdapter(EShoppingListActivity.this, myEShoppingModelClasses);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(EShoppingListActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(shoppingListRecycleAdapter);
    }
}