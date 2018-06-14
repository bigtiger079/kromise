package com.bigtiger.ksample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bigtiger.kromise.*
import com.bigtiger.kromise.impl.DeferredObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val deferred = DeferredObject<String, String, String>()
        deferred.kromise().then(object : DoneFilter<String, Int>{
            override fun filterDone(result: String): Int {
                return 1
            }
        }).then(donePipe = object : DonePipe<Int, String, String, String>{
            override fun pipeDone(result: Int): Kromise<String, String, String> {
                return DeferredObject<String, String, String>().resolve("a")
            }
        },failPipe = object : FailPipe<String, Char, Int, String>{
            override fun pipeFail(result: String): Kromise<Char, Int, String> {
                return DeferredObject<Char, Int, String>().resolve(result[0])
            }
        }).done(object :DoneCallback<Any>{
            override fun onDone(result: Any?) {

            }
        })

        deferred.then {
            1
        }.then {
            "${it + 1}"
        }.then {
            1L
        }.done {

        }
    }
}
