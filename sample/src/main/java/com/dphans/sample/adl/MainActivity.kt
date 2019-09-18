package com.dphans.sample.adl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dphans.lib.dynamic_layout.ADL
import com.dphans.lib.dynamic_layout.parsers.setLayoutJson


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this@MainActivity.setContentView(ADL.Builder(this@MainActivity).build {
            this.setLayoutJson(
                assets = "layouts/main_activity.json"
            )
        })
    }

}
