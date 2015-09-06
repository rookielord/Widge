package com.zhd.widge;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;

import java.util.List;

/**
 * Created by juiz on 2015/9/6.
 */
public class WidgeProvider extends AppWidgetProvider {

    private static final String LOG = "widget";
    //存放所有的图片
    int[] ImageSource = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4, R.drawable.a5};

    //每调用接受广播一次就调用一次
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(LOG, "onReceive");
        super.onReceive(context, intent);
    }

    //每更新一次就调用
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
            views.setImageViewResource(R.id.iv, ImageSource[2]);
            //刷新widget
            appWidgetManager.updateAppWidget(appWidgetIds, views);
    }

    //放上去的时候用
    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    //删除一次用
    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }

    //最后一个这种widget的时候调用
    @Override
    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

}
