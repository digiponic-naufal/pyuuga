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
import pyuuga.digiponic.com.model.InvoiceData;
import pyuuga.digiponic.com.utils.RecyclerViewHelper;

public class InvoiceAdapter extends RecyclerView.Adapter<InvoiceAdapter.ViewHolder> {

    private Context mContext;
    private List<InvoiceData> mDataInvoice;

    public InvoiceAdapter(Context mContext, List<InvoiceData> mDataInvoice) {
        this.mContext = mContext;
        this.mDataInvoice = mDataInvoice;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.invoice_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InvoiceData data = mDataInvoice.get(i);
        viewHolder._invoiceName.setText(data.getName());
        viewHolder._invoicePrice.setText(data.getPrice());
        viewHolder._invoiceCount.setText(String.valueOf(data.getCount()));
    }

    @Override
    public int getItemCount() {
        return mDataInvoice.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView _cardView;
        TextView _invoiceName, _invoicePrice, _invoiceCount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _cardView = itemView.findViewById(R.id.card_invoice);
            _invoiceCount = itemView.findViewById(R.id.text_count_invoice);
            _invoiceName = itemView.findViewById(R.id.name_invoice_item);
            _invoicePrice = itemView.findViewById(R.id.price_invoice_item);
        }
    }
}
