package net.lidongdong.bearoil.utils;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import net.lidongdong.bearoil.R;

/**
 * @authorlidongdong(A handsome man)
 * @ date 17/4/27
 * @ explain
 * @ function
 * @ version 1.0
 */

public class RotationUntil  {



    public static void switchRound(Context context, int count, TextView tv, ImageView... imageViews) {

        if (imageViews.length == 4) {
            String[] strings = context.getResources().getStringArray(R.array.cost_chart);
            switch (count) {
                case 0:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 0) {
                            imageViews[0].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }
                    break;

                case 1:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 1) {
                            imageViews[1].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }
                    break;
                case 2:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 2) {
                            imageViews[2].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
                case 3:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 3) {
                            imageViews[3].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }
                    break;

            }


        } else if (imageViews.length == 5) {

            String[] strings = context.getResources().getStringArray(R.array.oil_chart);
            switch (count) {
                case 0:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 0) {
                            imageViews[0].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }
                    break;

                case 1:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 1) {
                            imageViews[1].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }
                    break;
                case 2:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 2) {
                            imageViews[2].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
                case 3:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 3) {
                            imageViews[3].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
                case 4:
                    tv.setText(strings[count]);
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 4) {
                            imageViews[4].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
            }
        }else if (imageViews.length == 7){
            switch (count) {
                case 0:
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 0) {
                            imageViews[0].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }
                    break;

                case 1:
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 1) {
                            imageViews[1].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 2) {
                            imageViews[2].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
                case 3:
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 3) {
                            imageViews[3].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
                case 4:
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 4) {
                            imageViews[4].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
                 case 5:
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 5) {
                            imageViews[5].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;
                 case 6:
                    for (int i = 0; i < imageViews.length; i++) {
                        if (i == 6) {
                            imageViews[6].setImageResource(R.mipmap.baisexiaoyuandian);
                        } else {
                            imageViews[i].setImageResource(R.mipmap.shixinxiaoyuandian);
                        }
                    }

                    break;

            }
        }


    }

}
