package com.example.edlyas.laboras_2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class NewIndication extends View {

    public NewIndication (Context context) {super(context);}
    public NewIndication(Context context, AttributeSet attrs)
    {super(context,attrs);}

    public NewIndication(Context context, AttributeSet attrs,
                          int defStyleAttr){
        super(context, attrs, defStyleAttr);
    }



@Override
    protected void onDraw(Canvas canvas){
    super.onDraw(canvas);

    int width = getWidth();
    int height = getHeight();
    Paint paint;
    paint = new Paint();

    paint.setColor(Color.BLACK);
    canvas.drawCircle(width/2, height/2, 200,paint);
    paint.setColor(Color.WHITE);
    paint.setTextSize(150);
   canvas.drawText("3", width/3, height/2, paint);

}
}
