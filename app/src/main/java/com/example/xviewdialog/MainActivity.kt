package com.example.xviewdialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.dialog.safeClickListener


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val vg=findViewById<ViewGroup>(R.id.content)

        findViewById<View>(R.id.btn).safeClickListener {


            Dialog2(this).show()
        }
    }
}