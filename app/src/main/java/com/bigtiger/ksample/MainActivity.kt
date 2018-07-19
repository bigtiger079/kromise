package com.bigtiger.ksample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.bigtiger.kromise.*
import com.bigtiger.kromise.impl.DefaultDeferredManager
import com.bigtiger.kromise.impl.DeferredObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val deferred = DeferredObject<String, String>()
        deferred.kromise().then(object : DoneFilter<String, Int>{
            override fun filterDone(result: String): Int {
                return 1
            }
        }).then(donePipe = object : DonePipe<Int, Float, String>{
            override fun pipeDone(result: Int): Kromise<Float, String> {
                return DeferredObject()
            }
        }).then(object : DoneCallback<Float>{
            override fun onDone(result: Float?) {

            }
        }).done {

        }.always(object : AlwaysCallback<Float, String>{
            override fun onAlways(state: State, resolved: Float?, rejected: String?) {

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
        val def2 = DeferredObject<Int, String>()
        DefaultDeferredManager().`when`<Double, String, Int>(deferred, def2).then {
            it.getFirst()
        }
        deferred.resolve("1")
    }
}
