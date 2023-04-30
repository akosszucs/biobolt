package com.example.shop;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;

public class ProductAdapter
        extends RecyclerView.Adapter<ProductAdapter.ViewHolder>
        implements Filterable {
    // Member variables.
    private ArrayList<Product> mProductsData;
    private ArrayList<Product> mProductsDataAll;
    private Context mContext;
    private int lastPosition = -1;

    ProductAdapter(Context context, ArrayList<Product> itemsData) {
        this.mProductsData = itemsData;
        this.mProductsDataAll = itemsData;
        this.mContext = context;
    }

    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ProductAdapter.ViewHolder holder, int position) {
        // Get current product.
        Product currentItem = mProductsData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentItem);


        if(holder.getAdapterPosition() > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_row);
            holder.itemView.startAnimation(animation);
            lastPosition = holder.getAdapterPosition();
        }
    }

    @Override
    public int getItemCount() {
        return mProductsData.size();
    }


    /**
     * RecycleView filter
     * **/
    @Override
    public Filter getFilter() {
        return productFilter;
    }

    private Filter productFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<Product> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0) {
                results.count = mProductsDataAll.size();
                results.values = mProductsDataAll;
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();
                for(Product item : mProductsDataAll) {
                    if(item.getName().toLowerCase().contains(filterPattern) || item.getDescription().toLowerCase().contains(filterPattern)){
                        filteredList.add(item);
                    }
                }

                results.count = filteredList.size();
                results.values = filteredList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mProductsData = (ArrayList)filterResults.values;
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {
        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mInfoText;
        private TextView mPriceText;
        private ImageView mItemImage;
        private RatingBar mRatingBar;

        ViewHolder(View itemView) {
            super(itemView);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.itemTitle);
            mInfoText = itemView.findViewById(R.id.subTitle);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mRatingBar = itemView.findViewById(R.id.ratingBar);
            mPriceText = itemView.findViewById(R.id.price);
        }

        void bindTo(Product currentItem){
            mTitleText.setText(currentItem.getName());
            mInfoText.setText(currentItem.getDescription());
            mPriceText.setText(currentItem.getPrice());
            mRatingBar.setRating(currentItem.getRating());

            // Load the images into the ImageView using the Glide library.
            Glide.with(mContext).load(currentItem.getImageResource()).into(mItemImage);

            itemView.findViewById(R.id.add_to_cart).setOnClickListener(view -> {
                Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
                view.startAnimation(animation);
                ((ProductListActivity)mContext).updateAlertIcon(currentItem);
            });
            itemView.findViewById(R.id.delete).setOnClickListener(view -> ((ProductListActivity)mContext).deleteItem(currentItem));
        }
    }
}
