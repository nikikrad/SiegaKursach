package com.example.siegakursach.view.game.match.svg

import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class SampleGlideModule: AppGlideModule() {

    override fun isManifestParsingEnabled(): Boolean {
        return false
    }
}