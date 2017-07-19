package com.demo.mark.mytest1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMMessage;

import java.util.ArrayList;
import java.util.List;

//会话列表
public class MainActivity extends AppCompatActivity {
     private MyAdapter myAdapter;
    // 退出登录
    private Button mSignOutBtn,to_chat;
    private EditText et_name;

    //会话列表
    private ListView list_user;
    private List<Userbean> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 判断sdk是否登录成功过，并没有退出和被踢，否则跳转到登陆界面
        if (!EMClient.getInstance().isLoggedInBefore()) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        initView();
        setContent();
    }

    private void setContent() {

        mList.add(0,new Userbean("18673668974","http://www.zcool.com.cn/work/ZMTI4MDM3MDQ=.html"));
        mList.add(1,new Userbean("18673668988","http://www.zcool.com.cn/work/ZMTI4MDM3MDQ=.html"));
        mList.add(2,new Userbean("15975958401","http://www.zcool.com.cn/work/ZMTI4MDM3MDQ=.html"));
        myAdapter = new MyAdapter(this,mList);
        list_user.setAdapter(myAdapter);
        list_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String chatId = mList.get(i).getUsername();
                if (!TextUtils.isEmpty(chatId)) {
                    // 获取当前登录用户的 username
                    String currUsername = EMClient.getInstance().getCurrentUser();
                    if (chatId.equals(currUsername)) {
                        Toast.makeText(MainActivity.this, "不能和自己聊天", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 跳转到聊天界面，开始聊天
                    Intent intent = new Intent(MainActivity.this, ECChatActivity.class);
                    // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
                    intent.putExtra("userId", chatId);
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Username 不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 初始化界面
     */
    private void initView() {
        list_user = (ListView) findViewById(R.id.list_user);
        mSignOutBtn = (Button) findViewById(R.id.ec_btn_sign_out);
        mSignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
        et_name = (EditText) findViewById(R.id.et_name);
        to_chat = (Button) findViewById(R.id.to_chat);
        to_chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String chatId = et_name.getEditableText().toString();
                if (!TextUtils.isEmpty(chatId)) {
                    // 获取当前登录用户的 username
                    String currUsername = EMClient.getInstance().getCurrentUser();
                    if (chatId.equals(currUsername)) {
                        Toast.makeText(MainActivity.this, "不能和自己聊天", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    // 跳转到聊天界面，开始聊天
                    Intent intent = new Intent(MainActivity.this, ECChatActivity.class);
                    // EaseUI封装的聊天界面需要这两个参数，聊天者的username，以及聊天类型，单聊还是群聊
                    intent.putExtra("userId", chatId);
                    intent.putExtra("chatType", EMMessage.ChatType.Chat);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Username 不能为空", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    /**
     * 退出登录
     */
    private void signOut() {
        // 调用sdk的退出登录方法，第一个参数表示是否解绑推送的token，没有使用推送或者被踢都要传false
        EMClient.getInstance().logout(false, new EMCallBack() {
            @Override
            public void onSuccess() {
                Log.e("mark", "logout success");
                // 调用退出成功，结束app
                finish();
            }

            @Override
            public void onError(int i, String s) {
                Log.e("mark", "logout error " + i + " - " + s);
            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
}
