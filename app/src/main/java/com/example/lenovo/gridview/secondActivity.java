package com.example.lenovo.gridview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class secondActivity extends Activity {
    private GridView gv;
    private List<Map<String,Object>> list;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gv);
        gv = (GridView)findViewById(R.id.gv);

        //1.获取数据源
        final int[] images = {R.drawable.bird,R.drawable.cat1,R.drawable.dog1,R.drawable.pet1,R.drawable.pet2,R.drawable.pet3,R.drawable.pet4};              //获取图片资源id
        list = new ArrayList<Map<String,Object>>();
        for(int i = 0;i<7;i++){
            Log.i("i------------->","i="+i);
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",images[i]);
            map.put("itemNum","item"+i);
            list.add(map);
        }
        MyAdapter myAdapter = new MyAdapter();
        gv.setAdapter(myAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent();
                    intent.putExtra("imageID",images[position]);
                    Log.i("imageID-->", String.valueOf(images[position]));
                    setResult(Activity.RESULT_OK,intent);
                    secondActivity.this.finish();
            }
        });
    }
    //2.自定义适配器
     class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            Log.i("------------->", "listsizi="+String.valueOf(list.size()));
            return list.size();     //获取总item数
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);//根据position获取item
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            myholder holder = null;
            if(convertView==null){
                //设置加载器，将布局资源加载到converView中
                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.gv_item,null);

                //获取布局文件中控件
                holder = new myholder();
                holder.iv = (ImageView)convertView.findViewById(R.id.iv);
                holder.text = (TextView)convertView.findViewById(R.id.text);

                //convertView设置标签
                convertView.setTag(holder);
            }else{
                holder = (myholder) convertView.getTag();
            }
            holder.iv.setImageResource((Integer) list.get(position).get("image"));
            holder.text.setText((CharSequence) list.get(position).get("itemNum"));
            Log.i("-------------->", "converView"+String.valueOf(convertView));
            return convertView;
        }
    }
    static class myholder{
        ImageView iv;
        TextView text;
    }
}
