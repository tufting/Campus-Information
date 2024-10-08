package cn.edu.nenu.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import cn.edu.nenu.R;
import cn.edu.nenu.entity.Post;

public class HomeBaseAdapter extends BaseAdapter {

    private Context context;
    private List<Post> postList;
    private Map<Integer, String> userMap;
    private Collection<Integer> collection;

    public HomeBaseAdapter(Context context, List<Post> postList, Map<Integer, String> userMap, Collection<Integer> collection) {
        this.context = context;
        this.postList = postList;
        this.userMap = userMap;
        this.collection = collection;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Object getItem(int i) {
        return postList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
//        一页视图足够展示数据时
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_home, null);
            holder = new ViewHolder();
            holder.tv_author = convertView.findViewById(R.id.tv_author);
            holder.tv_content = convertView.findViewById(R.id.tv_content);
            holder.tv_block = convertView.findViewById(R.id.tv_block);
            holder.tv_time = convertView.findViewById(R.id.tv_time);
            holder.tv_collects_num = convertView.findViewById(R.id.tv_collects_num);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

//        给holder设置数据
        Post post = postList.get(i);
        holder.tv_author.setText(userMap.get(Integer.parseInt(post.getAuthor())));
        holder.tv_content.setText(post.getTitle() + "。" + post.getContent());
        holder.tv_block.setText("#" + post.getBlock() + "#");
        holder.tv_time.setText(post.getTime());

        String collect_text;
        if (collection.contains(post.getId())) {
            collect_text = String.valueOf(post.getCollects_num()) + "人收藏 (已收藏)";
        } else {
            collect_text = String.valueOf(post.getCollects_num()) + "人收藏 (点击收藏)";
        }
        holder.tv_collects_num.setText(collect_text);

        return convertView;
    }

    public final class ViewHolder {
        public TextView tv_author;  /* author_name */
        public TextView tv_content; /* tv_content = title + content */
        public TextView tv_block;   /* #block# */
        public TextView tv_time;
        public TextView tv_collects_num;
    }
}
