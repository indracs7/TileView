package com.indra.developer.tileview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Handler m_handler = new Handler();
    AnimatorSet animatorSet;
    private int mDurationMsec = 1000;
    int images[]={R.drawable.t1,R.drawable.t2,R.drawable.t3,R.drawable.t4};
    private final TypeEvaluator<Float> mFloatEval = new FloatEvaluator();
    private final TypeEvaluator<Integer> mIntEval = new IntEvaluator();
    int max=1,min=0,index,count=1,tmp=0;



   private Runnable m_updateElapsedTimeTask = new Runnable() {
        public void run() {
            chooseAnimation();
            //frontview.setImageResource(images[count]);
            m_handler.postDelayed(this, mDurationMsec+1000);   // Re-execute after msec.
        }
    };

    private void chooseAnimation() {

        Random r = new Random();
        index = r.nextInt(max - min + 1) + min;
       // animate3DRightLeft();

       if(index==1) {
           //  slideUp(frontview,backview);
           /*if (animatorSet != null) {
               animatorSet.addListener(new AnimatorListenerAdapter() {

                   @Override
                   public void onAnimationEnd(Animator animation) {
                       animate3DRightLeft();
                   }
               });


               //animatorSet.end();
           }*
           else*/
               animate3DRightLeft();
       }

      else {
          /* if (animatorSet != null) {
               //slideUp(frontview,backview);
               animatorSet.addListener(new AnimatorListenerAdapter() {

                   @Override
                   public void onAnimationEnd(Animator animation) {
                       animate3DUpDown();
                   }
               });

               //animatorSet.end();
           }
           else*/
               animate3DUpDown();
       }

       //slideUp();

    }

    ImageView frontview,backview;
    boolean front=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       frontview=findViewById(R.id.front);
        frontview.setImageResource(images[0]);
        backview=findViewById(R.id.back);
        frontview.setImageResource(images[1]);
        //backview.setImageResource(images[0]);
        //backview.setImageResource(images[1]);



       // m_handler.postDelayed(m_updateElapsedTimeTask, mDurationMsec);


    }

    public void onClick(View view) {
      /*  if(front==true)
            showback();
        else
            showfront();*/
        //showfront3D();
        m_handler.postDelayed(m_updateElapsedTimeTask, mDurationMsec);
        //animate3DRightLeft(frontview,backview);


    }
    public void animate3DUpDown() {

        frontview.bringToFront();
        frontview.setImageResource(images[tmp]);
        backview.setImageResource(images[count]);
        tmp=count;
        if(count==images.length-1)
            count=0;
        else
            count++;




        final float END_ANGLE = 90.0f;
        float beg1 = 0;
        float beg2 = END_ANGLE;
        float rot = -END_ANGLE;
        final float pivotPos = 0.5f;
        frontview.setPivotX(frontview.getWidth() * pivotPos);
        backview.setPivotX(backview.getWidth() * pivotPos);
        frontview.setPivotY(0);
        backview.setPivotY(backview.getHeight());
        animatorSet = new AnimatorSet();
        String rotParm = "RotationX";
        String tranParm = "TranslationY";
        animatorSet
                .play(ObjectAnimator.ofObject(frontview, rotParm, mFloatEval, beg1, beg1 + rot).setDuration(mDurationMsec))
                .with(ObjectAnimator.ofObject(frontview, tranParm, mIntEval, 0, frontview.getHeight()).setDuration(mDurationMsec))
                .with(ObjectAnimator.ofObject(backview, rotParm, mFloatEval, beg2, beg2 + rot).setDuration(mDurationMsec))
                .with(ObjectAnimator.ofObject(backview, tranParm, mIntEval, -backview.getHeight(), 0).setDuration(mDurationMsec));
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                return;
            }
        });


    }
    public void animate3DRightLeft() {

        frontview.setImageResource(images[tmp]);
        frontview.bringToFront();
        backview.setImageResource(images[count]);
        tmp=count;
        if(count==images.length-1)
            count=0;
        else
            count++;

        final float END_ANGLE = 90.0f;
        float beg1 = 0;
        float beg2 = END_ANGLE;
        float rot = -END_ANGLE;

        final float pivotPos = 0.5f;
        frontview.setPivotY(frontview.getHeight() * pivotPos);
        backview.setPivotY(backview.getHeight() * pivotPos);
        frontview.setPivotX(0);
        backview.setPivotX(backview.getWidth());
        animatorSet = new AnimatorSet();
        String rotParm = "RotationY";
        String tranParm = "TranslationX";
        animatorSet
                .play(ObjectAnimator.ofObject(frontview, rotParm, mFloatEval, beg1, beg2).setDuration(mDurationMsec))
                .with(ObjectAnimator.ofObject(frontview, tranParm, mIntEval, 0, frontview.getWidth()).setDuration(mDurationMsec))
                .with(ObjectAnimator.ofObject(backview, rotParm, mFloatEval, rot, beg1).setDuration(mDurationMsec))
                .with(ObjectAnimator.ofObject(backview, tranParm, mIntEval, -backview.getWidth(), 0).setDuration(mDurationMsec));
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                animate3DUpDown();
            }
        });
    }




    public void slideUp(){
        frontview.setImageResource(images[tmp]);
        backview.bringToFront();
        backview.setImageResource(images[count]);
        tmp=count;
        if(count==images.length-1)
            count=0;
        else
            count++;
        Random r = new Random();
        index = r.nextInt(1 - 0 + 1);

        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(backview, "alpha", 0f, 1f);
        animator2.setDuration(1);
        ObjectAnimator animator,animator1;
        if(index==0){

             animator = ObjectAnimator.ofFloat(backview,"translationY", backview.getHeight(), 0f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000);
         animator1 = ObjectAnimator.ofFloat(frontview,"translationY", 0f, -backview.getHeight());
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.setDuration(1000);
        }
        else {
            animator = ObjectAnimator.ofFloat(backview,"translationY", -backview.getHeight(), 0f);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(1000);
             animator1 = ObjectAnimator.ofFloat(frontview,"translationY", 0f, frontview.getHeight());
            animator1.setInterpolator(new AccelerateDecelerateInterpolator());
            animator1.setDuration(1000);
        }

        /*ObjectAnimator animator3 = ObjectAnimator.ofFloat(img2, "alpha", 1f, 0f);
        animator3.setDuration(1);*/
         r = new Random();
        index = r.nextInt(1 - 0 + 1);
        animatorSet.play(animator2);
        //if(index==0)
        animatorSet.play(animator).with(animator1);
       /* else
            set.play(animator);*/




        animatorSet.start();
    }


    public void slideDown(ImageView v1,ImageView v2) {
        front=false;
        v2.bringToFront();
         animatorSet = new AnimatorSet();

        ObjectAnimator animator2 = ObjectAnimator.ofFloat(v2, "alpha", 0f, 1f);
        animator2.setDuration(1);
        ObjectAnimator animator = ObjectAnimator.ofFloat(v2,"translationY", -v2.getWidth(), 0f);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1000);
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(v1,"translationY", 0f, v1.getWidth());
        animator1.setInterpolator(new AccelerateDecelerateInterpolator());
        animator1.setDuration(1000);
       /* ObjectAnimator animator3 = ObjectAnimator.ofFloat(img1, "alpha", 1f, 0f);
        animator3.setDuration(1);*/
        animatorSet.play(animator2);
        animatorSet.play(animator);
        animatorSet.start();
    }
    public class FloatEvaluator implements TypeEvaluator<Float> {
        public Float evaluate(float fraction, Float startValue,  Float endValue) {
            return startValue + ((endValue - startValue) * fraction);
        }
    }


    public class IntEvaluator implements TypeEvaluator<Integer> {
        public Integer evaluate(float fraction, Integer startValue,  Integer endValue) {
            float numF = startValue + ((endValue - startValue) * fraction);
            return (int)numF;
        }
    }

}
