package Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_commerce.R;
import java.util.ArrayList;
import java.util.List;
import Model.CartListModel;

public class RecycleAdapterCartList extends RecyclerView.Adapter<Adapters.RecycleAdapterCartList.MyViewHolder> {

    private static List<CartListModel> moviesList;
    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static final String QUANTITY_LIMIT = "No more items from this product";
    private static final String QUANTITY_LIMIT_ZERO = "You should remove the item";
    private static ArrayList<String> originalPrices;
    public static ArrayList<String> modifiedPrices;
    @SuppressLint("StaticFieldLeak")

    public RecycleAdapterCartList(Context context, List<CartListModel> moviesList) {
        RecycleAdapterCartList.moviesList = moviesList;
        RecycleAdapterCartList.context = context;
        originalPrices = new ArrayList<>();
        modifiedPrices = new ArrayList<>();
        for(int i = 0;i < moviesList.size();i++){
            originalPrices.add(moviesList.get(i).getPrice());
            modifiedPrices.add(moviesList.get(i).getPrice());
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image, add, minus, delete;
        TextView title, quantity, price, cartNumber;
        public MyViewHolder(View view) {
            super(view);
            image = view.findViewById(R.id.cart_image);
            add = view.findViewById(R.id.plus);
            title = view.findViewById(R.id.cart_title);
            price = view.findViewById(R.id.total_price);
            minus = view.findViewById(R.id.minus);
            delete = view.findViewById(R.id.delete);
            quantity = view.findViewById(R.id.cart_quantity);
            cartNumber = view.findViewById(R.id.cart_number);
            add.setOnClickListener(this);
            minus.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == add.getId()){
                String q = quantity.getText().toString().substring(9);
                String cartNum = cartNumber.getText().toString();
                int qua = Integer.parseInt(q);
                int quan = Integer.parseInt(cartNum);
                if(quan + 1 > qua){
                    Toast.makeText(context, QUANTITY_LIMIT, Toast.LENGTH_LONG).show();
                }
                else{
                    quan += 1;
                    cartNumber.setText(String.valueOf(quan));
                    price.setText(String.valueOf(quan * Integer.parseInt(originalPrices.get(getAdapterPosition()))));
                    modifiedPrices.set(getAdapterPosition(), price.getText().toString());
                }
            }
            else if(v.getId() == minus.getId()){
                String cartNum = cartNumber.getText().toString();
                int quan = Integer.parseInt(cartNum);
                if(quan - 1 < 0){
                    Toast.makeText(context, QUANTITY_LIMIT_ZERO, Toast.LENGTH_LONG).show();
                }
                else{
                    quan -= 1;
                    cartNumber.setText(String.valueOf(quan));
                    price.setText(String.valueOf(quan * Integer.parseInt(originalPrices.get(getAdapterPosition()))));
                    modifiedPrices.set(getAdapterPosition(), price.getText().toString());
                }
            }
        }
    }

    @NonNull
    @Override
    public Adapters.RecycleAdapterCartList.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_my_cart, parent, false);
        return new Adapters.RecycleAdapterCartList.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final Adapters.RecycleAdapterCartList.MyViewHolder holder, final int position) {
        CartListModel movie = moviesList.get(position);
        holder.image.setImageResource(movie.getImage());
        holder.add.setImageResource(movie.getAdd());
        holder.minus.setImageResource(movie.getMinus());
        holder.delete.setImageResource(movie.getDelete());
        holder.title.setText(movie.getTitle());
        holder.price.setText(movie.getPrice());
        holder.quantity.setText(movie.getQuantity());
        holder.cartNumber.setText(movie.getCartNumber());

        holder.delete.setOnClickListener(v -> {
            int position1 = holder.getAdapterPosition();
            moviesList.remove(position1);
            originalPrices.remove(position1);
            modifiedPrices.remove(position1);
            notifyItemRemoved(position1);
        });
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}


