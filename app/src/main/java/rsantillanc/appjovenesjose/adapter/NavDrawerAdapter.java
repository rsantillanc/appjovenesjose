package rsantillanc.appjovenesjose.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import rsantillanc.appjovenesjose.R;
import rsantillanc.appjovenesjose.moldel.NavDrawerModel;

/**
 * Created by RenzoD on 29/04/2015.
 */
public class NavDrawerAdapter extends RecyclerView.Adapter<NavDrawerAdapter.MyViewHolder>{
    private LayoutInflater layIn;
    private List<NavDrawerModel> navItems = Collections.EMPTY_LIST;
    private Context ctx;

    public NavDrawerAdapter(List<NavDrawerModel> navItems, Context ctx) {
        this.layIn = LayoutInflater.from(ctx);
        this.navItems = navItems;
        this.ctx = ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = layIn.inflate(R.layout.nav_drawer_row, viewGroup, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        NavDrawerModel current = navItems.get(position);
        myViewHolder.tvTitle.setText(current.getTitle());
        myViewHolder.ivIcon.setImageResource(current.getIcon());
    }

    @Override
    public int getItemCount() {
        return navItems.size();
    }

    public void delete(int position) {
        navItems.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivIcon;
        TextView tvCount;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title);
            ivIcon = (ImageView) itemView.findViewById(R.id.iv_icons_nav);
            tvCount = (TextView)itemView.findViewById(R.id.tv_count);
        }
    }

}
