package com.example.entendendoasthread;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    private Button botaoIniciar;
    private Handler handler=new Handler();
    private boolean apertoStop =  false;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoIniciar = findViewById(R.id.buttonIniciar);



    }
    public void encerraThread(View view){
       apertoStop=true;
    }

    public void iniciaThread(View view) {

        //for aqui Iria sobrecarregar a thread Principal

        /*MyThread  thread=new MyThread();
           thread.start();*/
        apertoStop=false; //Com isso acaba o erro de quando ele para ao clicka
                        // dnovo contar , conta des do 0 Corretamente , sem isso fica travado meu contador
        MyRunnable Runnable = new MyRunnable();
        new Thread(Runnable).start();

    }


    //Implementar
    class MyRunnable implements Runnable {

        @Override
        public void run() {
            for ( i = 0; i <= 15; i++) {
                Log.i("Thread ", " Contador" + i);

                if(apertoStop)
                    return;  // pois é void , só return da certo

                //passando atraves do runOnUiThread
               runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText( " Contador" + i); // 1 Vai dar erro, pois tenho
                                                                // que  definir um contador la em cima


                    }
                });

               /*
                //passando atraves do handle
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        botaoIniciar.setText("Contador"+i);
                    }
                });*/


                try {
                    Thread.sleep(1000); //tempo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


        //Extends
        class MyThread extends Thread {

            @Override
            public void run() {
                for (int i = 0; i <= 15; i++) {
                    Log.i("Thread ", " Contador" + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}



