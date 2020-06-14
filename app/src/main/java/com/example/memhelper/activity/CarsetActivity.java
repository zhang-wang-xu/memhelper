package com.example.memhelper.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.memhelper.DBHelper;
import com.example.memhelper.DBUtil;
import com.example.memhelper.R;
import com.example.memhelper.entity.Cardset;
import com.example.memhelper.entity.Passage;
import com.example.memhelper.view.GridViewAdapter;

import java.security.CryptoPrimitive;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CarsetActivity extends AppCompatActivity implements AdapterView.OnItemLongClickListener {
    Button butCardMemory;
    Button butPassageMemory;
    private GridView gridView;
    private List<Map<String, Object>> dataList;
    private GridViewAdapter mAdapter;
    private boolean isShowDelete = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carset);
        gridView = (GridView) findViewById(R.id.gridview);
        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        //初始化数据
        initData();


        mAdapter = new GridViewAdapter(CarsetActivity.this, dataList);

        gridView.setAdapter(mAdapter);

        gridView.setOnItemLongClickListener(this);//长按事件监听

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int arg2, long arg3) {
                //Toast.makeText(getApplicationContext(), ((Cardset)(dataList.get(arg2).get("cardset"))).getPassageId()+"",Toast.LENGTH_LONG).show();
                Cardset cardset = ((Cardset)(dataList.get(arg2).get("cardset")));
                Intent intent = new Intent(CarsetActivity.this, CardListActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("cardsetId", cardset.getPassageId());
                intent.putExtras(bundle);
                startActivity(intent);
            }

        });
        butCardMemory = findViewById(R.id.but_card);
        butCardMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarsetActivity.this, CarsetActivity.class);
                startActivity(intent);
            }
        });
        butPassageMemory = findViewById(R.id.but_passage);
        butPassageMemory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CarsetActivity.this, PassageActivity.class);
                startActivity(intent);
            }
        });

    }

    void initData() {
//        //图标
//        int icno[] = { R.drawable.i1, R.drawable.i2, R.drawable.i3,
//                R.drawable.i4, R.drawable.i5, R.drawable.i6, R.drawable.i7};
        //图标下的文字
        DBUtil dbUtil = new DBUtil(new DBHelper(CarsetActivity.this));
        List<Cardset> Cardsets=dbUtil.getCardsets();
        dataList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i <Cardsets.size(); i++) {
            Map<String, Object> map=new HashMap<String, Object>();
            //map.put("img", icno[i]);
            map.put("cardset",Cardsets.get(i));
            dataList.add(map);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cardset_menu,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_addcardset:
                final EditText et = new EditText(this);
                new AlertDialog.Builder(this).setTitle("请输入卡片集标题")
                        .setIcon(android.R.drawable.sym_def_app_icon)
                        .setView(et)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //按下确定键后的事件
                                DBUtil dbUtil = new DBUtil(new DBHelper(CarsetActivity.this));
                                String title = et.getText() + "";
                                Cardset passage = new Cardset(title);
                                dbUtil.insertCardset(passage);
                                initData();
                                String[] from={"text"};

                                int[] to={R.id.text};

                                mAdapter = new GridViewAdapter(CarsetActivity.this, dataList);//重新绑定一次adapter
                                gridView.setAdapter(mAdapter);
                                mAdapter.notifyDataSetChanged();//刷新gridview
                                Toast.makeText(getApplicationContext(), et.getText().toString(),Toast.LENGTH_LONG).show();
                            }
                        }).setNegativeButton("取消",null).show();
                break;
            case R.id.menu_delcardset:
                isShowDelete = true;
                mAdapter.setIsShowDelete(isShowDelete);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        DBUtil dbUtil = new DBUtil(new DBHelper(CarsetActivity.this));
                        Cardset passage = new Cardset(((Cardset)dataList.get(position).get("cardset")).getTitle());
                        dbUtil.deleteCardset(passage);
                        //Toast.makeText(getApplicationContext(), "删除状态",Toast.LENGTH_SHORT).show();
                        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> arg0, View arg1,
                                                    int arg2, long arg3) {
                                Cardset cardset = ((Cardset)(dataList.get(arg2).get("cardset")));
                                Intent intent = new Intent(CarsetActivity.this, CardListActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putInt("cardsetId", cardset.getPassageId());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                        });
                        isShowDelete=false;
                        mAdapter.setIsShowDelete(isShowDelete);
                        initData();
                        mAdapter = new GridViewAdapter(CarsetActivity.this, dataList);//重新绑定一次adapter
                        gridView.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();//刷新gridview
                    }

                });
                break;
            case android.R.id.home:
                finish();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        if (isShowDelete) {
            isShowDelete = false;

        } else {
            isShowDelete = true;
            mAdapter.setIsShowDelete(isShowDelete);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {
                    DBUtil dbUtil = new DBUtil(new DBHelper(CarsetActivity.this));
                    Cardset passage = new Cardset(((Cardset)dataList.get(position).get("cardset")).getTitle());
                    dbUtil.deleteCardset(passage);
                    //Toast.makeText(getApplicationContext(), "删除状态",Toast.LENGTH_SHORT).show();
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0, View arg1,
                                                int arg2, long arg3) {
                            Cardset cardset = ((Cardset)(dataList.get(arg2).get("cardset")));
                            Intent intent = new Intent(CarsetActivity.this, CardListActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putInt("cardsetId", cardset.getPassageId());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                    });
                    isShowDelete=false;
                    mAdapter.setIsShowDelete(isShowDelete);
                    initData();
                    mAdapter = new GridViewAdapter(CarsetActivity.this, dataList);//重新绑定一次adapter
                    gridView.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();//刷新gridview
                }

            });
        }


        return true;
    }




}
