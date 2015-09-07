package com.zhd.widge;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by juiz on 2015/9/6.
 */
public class WidgeProvider extends AppWidgetProvider {

    private static final String TAG = "widget";
    //更新图片时发出的广播的ACTION
    private final String ACTION_UPDATE_ALL = "com.zhd.widget.UPDATE_IMAGE";
    //存放所有的图片资源
    private static final int[] IMAGE_SOURCE =
            {
                    R.drawable.a1,
                    R.drawable.a2,
                    R.drawable.a3,
                    R.drawable.a4,
                    R.drawable.a5
            };
    //创建一个Random对象
    Random r=new Random();
    //启动服务的意图
    Intent widget_service=new Intent("com.zhd.widgetservice");
    //存放创建的Widget对象的ID
    private static Set idSet=new HashSet();
    //按钮信息
    private static int BUTTON_SHOW=1;


    //每调用接受广播一次就调用一次，widget的时候调用，添加
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive");
        final String action=intent.getAction();
        Log.i(TAG,"Action:"+action);
        if (ACTION_UPDATE_ALL.equals(action)){
            updateAllWidget(context,AppWidgetManager.getInstance(context),idSet);
        }
        super.onReceive(context, intent);
    }

    private void updateAllWidget(Context context, AppWidgetManager instance, Set idSet) {
        int appID;
        int sourceID=r.nextInt(5);
        //用于保存所有的Widget的ID
        Iterator it= idSet.iterator();
        while (it.hasNext()){
            appID= ((Integer)it.next()).intValue();
            //每次widget创建时，其对象ID都会被添加进set对象中
            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setImageViewResource(R.id.iv,IMAGE_SOURCE[sourceID]);
            //刷新widget
            instance.updateAppWidget(appID, views);
        }


    }

    //每更新一次就调用，在添加时调用一次
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.i(TAG, "onUpdate");
        for (int WidgetId :appWidgetIds){
            idSet.add(Integer.valueOf(WidgetId));
        }
        ptrSet();

    }

    private void ptrSet() {

    }

    //最初添加这一类widget的时候调用，如果是添加同一类Widget，则只会在第一次添加时调用
    @Override
    public void onEnabled(Context context) {
        Log.i(TAG, "onEnable");
        context.startService(widget_service);
        super.onEnabled(context);
    }

    //删除一次用
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
        Log.i(TAG, "onDelete");
    }

    //最后一个这种widget的时候调用
    @Override
    public void onDisabled(Context context) {
        Log.i(TAG, "onDisabled");
        context.stopService(widget_service);
        super.onDisabled(context);
    }

}
