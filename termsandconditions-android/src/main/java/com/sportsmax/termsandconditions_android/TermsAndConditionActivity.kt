package com.sportsmax.termsandconditions_android

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.applicaster.activities.base.APBaseActivity
import com.applicaster.storage.LocalStorage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_terms_and_condition.*


class TermsAndConditionActivity : APBaseActivity() {

    private var toolbar: Toolbar? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_condition)

        initStyles()
    }

    private fun initStyles(){
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val title = findViewById<TextView>(R.id.tv_title)
        val logo = findViewById<ImageView>(R.id.logo_sportsmax)
        val tvTermsAndConditions = findViewById<TextView>(R.id.tv_termsAndConditions)
        tvTermsAndConditions.movementMethod = ScrollingMovementMethod()

        val btnAccept = findViewById<Button>(R.id.btn_agreeAndContinue)

        ConfigurationUiHelper.updateTextViewText(tvTermsAndConditions, TERMS_AND_CONDITIONS_TEXT, true)
        ConfigurationUiHelper.updateTextViewText(title, NAVIGATION_HEADER_TEXT)
        ConfigurationUiHelper.updateButtonStyle(btn_agreeAndContinue, text = AGREE_BUTTON_TEXT, backgroundColor = AGREE_BUTTON_BG_COLOR, textColor = AGREE_BUTTON_TEXT_COLOR)
        ConfigurationUiHelper.updateToolbarBackgroundColor(toolbar = toolbar, backgroundColor = NAVIGATION_HEADER_BACKGROUND)
        val imageUrl = ConfigurationUiHelper.getValue(NAVIGATION_HEADER_IMAGE)
        if(!imageUrl.isNullOrEmpty()){
            title.visibility = View.INVISIBLE
            Picasso.get().load(imageUrl).into(logo)
        }
        btnAccept.setOnClickListener {
            LocalStorage.storageRepository.set(ACCEPTED_TERMS_AND_CONDITIONS, "1", PLUGIN_NAME)
            StartUpAdapter.hookListener?.onHookFinished()
            this.finish()
        }
    }

}

