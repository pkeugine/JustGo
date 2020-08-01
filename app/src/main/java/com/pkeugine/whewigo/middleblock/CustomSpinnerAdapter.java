package com.pkeugine.whewigo.middleblock;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pkeugine.whewigo.R;

import java.util.List;

public class CustomSpinnerAdapter extends BaseAdapter {
    public interface ISpinnerListener {
        void onItemSelected(BaseAdapter adapter, int position);
    }

    public static class CustomSpinnerItems {
        private final List<String> itemList;
        public CustomSpinnerItems(List<String> itemList) { this.itemList = itemList; }
        public String getTitle(int position) {
            return itemList.get(position);
        }
        public int getSize() {
            return itemList.size();
        }
    }
    private final CustomSpinnerItems items;
    private final ISpinnerListener listener;
    private int selectedItemPosition = 0;

    public CustomSpinnerAdapter() {
        this.items = null;
        this.listener = null;
    }

    public CustomSpinnerAdapter(CustomSpinnerItems items, ISpinnerListener listener) {
        this.items = items;
        this.listener = listener;
    }

    @Override
    public int getCount() {
        return items.getSize();
    }

    @Override
    public Object getItem(int i) {
        return items.getTitle(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View item = view;
        if(item == null) {
            LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
            item = inflater.inflate(R.layout.view_spinner_item,viewGroup,false);
        }

        TextView title = item.findViewById(R.id.menu_title);
        title.setText(items.getTitle(i));

        if(listener != null) {
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedItemPosition = i;
                    listener.onItemSelected(CustomSpinnerAdapter.this, i);
                }
            });
        }
        return item;
    }

}
