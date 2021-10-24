package Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_commerce.HomePageActivity;
import com.example.e_commerce.R;
import java.util.ArrayList;
import java.util.List;
import Model.ProductListModelClass;

public class RecycleAdapterProductList extends RecyclerView.Adapter<RecycleAdapterProductList.MyViewHolder> {

    private final List<ProductListModelClass> moviesList;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static int addCart = 0;
    public static String chosenProduct = "";
    public static ArrayList<String> chosenProducts = new ArrayList<>();

    public RecycleAdapterProductList(Context context, ArrayList<ProductListModelClass> moviesList) {
        this.moviesList = moviesList;
        RecycleAdapterProductList.context = context;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image,like;
        TextView title;
        TextView price;

        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.product_image);
            like = view.findViewById(R.id.like);
            title = view.findViewById(R.id.title);
            price = view.findViewById(R.id.product_price);
            like.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == like.getId()){
                Intent intent = new Intent(context, HomePageActivity.class);
                addCart += 1;
                chosenProduct = title.getText().toString();
                chosenProducts.add(chosenProduct);
                context.startActivity(intent);
            }
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product_list, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder,final int position) {
        ProductListModelClass movie = moviesList.get(position);
        holder.image.setImageResource(movie.getImage());
        holder.like.setImageResource(movie.getLike());
        holder.title.setText(movie.getTitle());
        holder.price.setText(movie.getPrice());
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}


