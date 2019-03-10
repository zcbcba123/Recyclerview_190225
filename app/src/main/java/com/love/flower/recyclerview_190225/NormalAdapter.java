package com.love.flower.recyclerview_190225;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NormalAdapter extends RecyclerView.Adapter<NormalAdapter.VH> {

    private OnItemClickListener mOnItemClickListener;

    public static class VH extends RecyclerView.ViewHolder {
        TextView title;
        public VH(View v) {
            super(v);
            title = v.findViewById(R.id.title);
//            v.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    System.out.println();
//                }
//            });
        }

    }

    public void updateData(ArrayList<String>data){
        this.mDatas=data;
        notifyDataSetChanged();
    }

    public void addNewItem(){
        if (mDatas==null)
            mDatas = new ArrayList<>();
        mDatas.add(0, "new Item");
//        notifyDataSetChanged();
        notifyItemInserted(0);
    }

    public void deleteItem(){
        if (mDatas==null||mDatas.isEmpty()){
            return;
        }
        mDatas.remove(0);
//        notifyDataSetChanged();
        notifyItemRemoved(0);
    }

    public void changItem(){
//        mDatas.add(1,"ff");
        mDatas.set(1, "ff1");
        mDatas.set(2, "ff2");
//        notifyDataSetChanged();
//        notifyItemChanged(1);
        notifyItemRangeChanged(1,2);
    }


    public interface OnItemClickListener

    {
        void onClick(View itemView, int position);

        void onLongClick(View itemView,int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    private List<String> mDatas;


    public NormalAdapter(List<String> mDatas,OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener=mOnItemClickListener;
        this.mDatas = mDatas;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_1, parent, false);
//        View v = View.inflate(mContext,R.layout.item_1,null);
        return new VH(v);
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        holder.title.setText(mDatas.get(position));
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("---dian----");
//            }
//        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOnItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onClick(holder.itemView, pos);
                }
            }
        });


//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                if(mOnItemClickListener != null) {
//                    int pos = holder.getLayoutPosition();
//                    mOnItemClickListener.onClick(holder.itemView, pos);
//                }
//                //表示此事件已经消费，不会触发单击事件
//                return true;
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
