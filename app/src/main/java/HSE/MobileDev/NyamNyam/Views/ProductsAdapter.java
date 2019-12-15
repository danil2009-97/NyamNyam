package HSE.MobileDev.NyamNyam.Views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import HSE.MobileDev.NyamNyam.Database.Model.Product;
import HSE.MobileDev.NyamNyam.R;

public class ProductsAdapter extends BaseAdapter {

    private final Context mContext;
    private final Product[] products;

    // 1
    public ProductsAdapter(Context context, Product[] products) {
        this.mContext = context;
        this.products = products;
    }

    // 2
    @Override
    public int getCount() {
        return products.length;
    }

    // 3
    @Override
    public long getItemId(int position) {
        return 0;
    }

    // 4
    @Override
    public Object getItem(int position) {
        return null;
    }

    // 5
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Product product = products[position];

        // view holder pattern
        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.linearlayout_product, null);

            final ImageView imageViewCoverArt = (ImageView)convertView.findViewById(R.id.imageview_product);
            final TextView nameTextView = (TextView)convertView.findViewById(R.id.textview_product_name);

            final ViewHolder viewHolder = new ViewHolder(nameTextView, imageViewCoverArt);
            convertView.setTag(viewHolder);
        }

        final ViewHolder viewHolder = (ViewHolder)convertView.getTag();
        Picasso.get().load(product.getImageSource()).into(viewHolder.imageViewCoverArt);
        viewHolder.nameTextView.setText(mContext.getString(product.getName()));
        return convertView;
    }

    private class ViewHolder {
        private final TextView nameTextView;
        private final ImageView imageViewCoverArt;

        public ViewHolder(TextView nameTextView, ImageView imageViewCoverArt) {
            this.nameTextView = nameTextView;
            this.imageViewCoverArt = imageViewCoverArt;
        }
    }

}
