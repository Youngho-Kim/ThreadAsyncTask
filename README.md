# ThreadAsyncTask
AsyncTask  
###Json
//----------------------------json-----------------------------------------------------------------------  
1. json 기본형json 오브젝트 = { 중괄호와 중괄호 사이}{"변수명" : "값","변수명2" : "값2","변수명3" : "값3"}   
json.변수명 < 값

2. json 서브트리// {"변수명" : 오브젝트}{"변수명" : {"서브명" : "값","서브명2" : "값2","서브명3" : "값3"} }  
json.변수명.서브명 < 값

3. json 배열//   
{ "변수명" : json 오브젝트}{ "변수명" : json 배열}{ "변수명" : [           
                                                              {"서브명" : "값","서브명2" : "값2","서브명3" : "값3"},           
                                                              {"서브명" : "값","서브명2" : "값2","서브명3" : "값3"},            
                                                              {"서브명" : "값","서브명2" : "값2","서브명3" : "값3"}              
                                                            ]}json.변수명[0].서브명                                     
json.변수명[1].서브명  

//------------------------------------------------------------------------------------------------------------

### AsyncTask
[전체소스코드](https://github.com/Youngho-Kim/ThreadAsyncTask/blob/master/app/src/main/java/com/kwave/android/threadasynctask/MainActivity.java)
//--------------------------------Async Task-------------------------------------------------------------------
```java
private void runAsync(){

        new AsyncTask<String, Integer, Float>() {
            // AsyncTask<1, 2, 3>
            // 제네릭 타입
            // 1. doInBackroud의 인자
            // 2. onProgressUpdate의 인자
            // 3. doInBackground의 return 타입 = onPostExecute 인자값
            }

            // doInBackground가 호출 되기 전에 먼저 호출된다.
            @Override
            protected void onPreExecute() {     // - 핸들러가 실행
                progress.show();    // 쓰레드가 시작하기 전에 프로그레스를 먼저 실행한다.
            }


            @Override
            // 데이터를 처리...   // 쓰레드가 실행
            protected Float doInBackground(String... params) { // thread의 run과 같은 역할

                return Float 값;
            }

            // doInBackground가 호출 된 후에 호출된다.
            @Override
            protected void onPostExecute(Float result) {        // 쓰레드가 완료되었을때 실행
                                        // doInBackground의 결과값
                // 결과값을 메인 UI에 세팅하는 로직을 여기에 작성한다.
                progress.dismiss();     // 프로그레스 종료
            }

            @Override
            // 주기적으로 doInBackground에서 호출이 가능한 함수
            // 중간중간  화면에 그리는 작업이 필요할때 publishProgress에 의해서 실행된다.
            protected void onProgressUpdate(Integer... values) {
                progress.setMessage("진행율 = "+values[0] + "%");      // 핸들러의 메세지 큐에 메세지 전달
            }
        }.execute("안녕", "하세요");    // = thread의 start 같은 역할  / doInBackground를 실행
    }
    ```