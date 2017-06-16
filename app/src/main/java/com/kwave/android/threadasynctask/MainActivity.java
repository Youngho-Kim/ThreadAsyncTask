package com.kwave.android.threadasynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int SET_DONE = 1;
    TextView textView;
    ProgressDialog progress;        // 프로그레스 다이얼 생성
    //프로그레스다이얼은 실행시 내부에서 핸들러가 있어 sendMessage가 동작된다.
    // Thread에서 호출하기 위한 handler
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {    // 핸들러는 루퍼의 메세지큐로 아래 메세지를 전달하고 받는다.
            switch (msg.what){      /// what은 무엇을 처리할지에 대한 구분자
        // 메세지큐에서 메세지를 하나 꺼내서 Runnable 객체가 존재하면 run을 실행하고 존재하지 않으면 mHandler의 handleMessage를 호출한다.
                case SET_DONE :
                setDone();
                break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                runAsync();
            }
        });
        // 화면에 진행상태를 표시
        // 메인 액티비티에서 사용할 프로그레스 객체 생성
        progress = new ProgressDialog(this);        // getBaseContext를 하면 오류남
        // 프로그레스 세팅
        progress.setTitle(" 진행중입니다.");
        progress.setMessage("진행중 표시되는 메세지입니다.");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);

    }
    private void setDone() {
        textView.setText("SetDone!!");

        // 프로그레스 창을 해제
        progress.dismiss();
    }
    private void runAsync(){

        new AsyncTask<String, Integer, Float>() {
            // AsyncTask<1, 2, 3>
            // 제네릭 타입
            // 1. doInBackroud의 인자
            // 2. onProgressUpdate의 인자
            // 3. doInBackground의 return 타입 = onPostExecute 인자값


            // doInBackground가 호출 되기 전에 먼저 호출된다.
            @Override
            protected void onPreExecute() {     // - 핸들러가 실행
                progress.show();    // 쓰레드가 시작하기 전에 프로그레스를 먼저 실행한다.
            }


            @Override
            // 데이터를 처리...   // 쓰레드가 실행
            protected Float doInBackground(String... params) { // thread의 run과 같은 역할
                // 10초 후에
                try {
                    Log.e("AsyncTask", "첫번째 값 : "+params[0]);
                    Log.e("AsyncTask", "두번째 값 : "+params[1]);

                    for(int i=0; i<10;i++){
                        publishProgress(i*10);  // <- onProgressUpdate를 실행시킴    // 내부에서 getHandler().obtainMessage가 실행됨
                        Thread.sleep(1000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return 1000.4f;
            }

            // doInBackground가 호출 된 후에 호출된다.
            @Override
            protected void onPostExecute(Float result) {        // 쓰레드가 완료되었을때 실행
                Log.e("AsyncTask", "doInBackground의 결과값" +result);
                // 결과값을 메인 UI에 세팅하는 로직을 여기에 작성한다.
                setDone();
                progress.dismiss();     // 프로그레스 종료
            }

//            @Override
//            protected void onCancelled() {      // 쓰레드가 중단 되었을때 실행
//                super.onCancelled();
//            }

            @Override
            // 주기적으로 doInBackground에서 호출이 가능한 함수
            // 중간중간  화면에 그리는 작업이 필요할때 publishProgress에 의해서 실행된다.
            protected void onProgressUpdate(Integer... values) {
                progress.setMessage("진행율 = "+values[0] + "%");      // 핸들러의 메세지 큐에 메세지 전달
            }
        }.execute("안녕", "하세요");    // = thread의 start 같은 역할  / doInBackground를 실행
    }

    private void runThread() {
        progress.show();
        CustomThread thread = new CustomThread(handler);
        thread.start();

    }
}

class CustomThread extends Thread{
    Handler handler;

    public CustomThread(Handler handler) {
        this.handler = handler;
    }
    @Override
    public void run() {
        // 10초 후에
        try {
            Thread.sleep(1000);
            // Main UI에 현재 thread가 접근 할 수 없으므로
            // handler를 통해 호출해준다.
            handler.sendEmptyMessage(MainActivity.SET_DONE);
            // 핸들러에 post로 시작하는 함수는 Runnuble 객체를 이용하여 메세지를 추가하는 함수이며
            // 핸들러에 send로 시작하는 함수는 handleMessage 재정의 함수까지 사용하는 메세지를 추가하는 함수이다.

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}