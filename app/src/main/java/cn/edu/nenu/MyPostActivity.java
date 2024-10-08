package cn.edu.nenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import cn.edu.nenu.adapter.MyPostBaseAdapter;
import cn.edu.nenu.dao.PostDao;
import cn.edu.nenu.entity.Post;
import cn.edu.nenu.util.SessionUtil;
import cn.edu.nenu.util.ToastUtil;

public class MyPostActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private MyApplication myApp;
    private PostDao postDao;

    private String author;
    private List<Post> postList;
    private ListView lv_post;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post);
        Log.d("execute log", "执行了MyPostActivity类的onCreate()方法...");

        myApp = MyApplication.getInstance();
        postDao = myApp.getCampusInfoDB().postDao();
        author = myApp.infoMap.get("cur_id");
        postList = postDao.queryByAuthor(author);
        MyPostBaseAdapter adapter = new MyPostBaseAdapter(this, postList);

        lv_post = findViewById(R.id.lv_post);
        lv_post.setAdapter(adapter);
        lv_post.setOnItemClickListener(this);

        findViewById(R.id.iv_back).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;

        if (view.getId() == R.id.iv_back) {
            intent = new Intent(this, MyActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        /* 获取某一条帖子的数据 */
        Post post = postList.get(i);
        SessionUtil sUtil = new SessionUtil();
        sUtil.SessionSetPost(post);

        /* 跳转到相应页面，展示数据 */
        Intent intent = new Intent(this, MyOneActivity.class);
        startActivity(intent);
    }
}