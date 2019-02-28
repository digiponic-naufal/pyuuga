package pyuuga.digiponic.com.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pyuuga.digiponic.com.R;
import pyuuga.digiponic.com.model.CategoryData;
import pyuuga.digiponic.com.utils.RecyclerViewHelper;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context mContext;
    private List<CategoryData> mDataCategory;
    RecyclerViewHelper.ItemClickListener mClickListener;

    public CategoryAdapter(Context mContext, List<CategoryData> mDataCategory) {
        this.mContext = mContext;
        this.mDataCategory = mDataCategory;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CategoryData data = mDataCategory.get(i);
        viewHolder._categoryName.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return mDataCategory.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView _cardView;
        TextView _categoryName;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            _cardView = v.findViewById(R.id.card_category);
            _categoryName = v.findViewById(R.id.text_name_category);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
