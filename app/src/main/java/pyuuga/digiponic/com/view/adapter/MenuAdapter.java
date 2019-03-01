package pyuuga.digiponic.com.view.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import pyuuga.digiponic.com.R;
import pyuuga.digiponic.com.model.MenuData;
import pyuuga.digiponic.com.utils.RecyclerViewHelper;

public class MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> {

    private Context mContext;
    private List<MenuData> mDatamenu;
    RecyclerViewHelper.ItemClickListener mClickListener;

    public MenuAdapter(Context mContext, List<MenuData> mDatamenu) {
        this.mContext = mContext;
        this.mDatamenu = mDatamenu;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MenuData data = mDatamenu.get(i);
        viewHolder._menuName.setText(data.getName());
        viewHolder._menuPrice.setText(data.getPrice());
        viewHolder._cardView.setElevation(5f);
        viewHolder._cardView.setTranslationZ(5f);
    }

    @Override
    public int getItemCount() {
        return mDatamenu.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView _cardView;
        TextView _menuName, _menuPrice;
        ImageView _menuImage;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            _cardView = v.findViewById(R.id.card_menu_item);
            _menuImage = v.findViewById(R.id.image_menu_item);
            _menuName = v.findViewById(R.id.name_menu_item);
            _menuPrice = v.findViewById(R.id.price_menu_item);
        }

        @Override
        public void onClick(View v) {
            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
        }
    }
}
