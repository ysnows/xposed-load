/*
 * Copyright (c) 2018 The sky Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.sky.xposed.load.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.preference.Preference
import android.preference.PreferenceFragment
import com.sky.xposed.app.R
import com.sky.xposed.load.Constant
import com.sky.xposed.load.ui.service.PluginService

/**
 * Created by sky on 18-1-9.
 */
class SettingsFragment : PreferenceFragment(), Preference.OnPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        addPreferencesFromResource(R.xml.setting_preferences)

        findPreference(Constant.Preference.AUTO_KILL_APP).onPreferenceChangeListener = this
        findPreference(Constant.Preference.ROOT_KILL_APP).onPreferenceChangeListener = this
    }

    override fun onPreferenceChange(preference: Preference, newValue: Any): Boolean {

        when(preference.key) {
            Constant.Preference.AUTO_KILL_APP -> {
                // 自动Kill
                if (newValue as Boolean) {
                    // 启动监听服务
                    activity.startService(Intent(activity, PluginService::class.java))
                } else {
                    // 停止监听服务
                    activity.stopService(Intent(activity, PluginService::class.java))
                }
            }
        }
        return true
    }
}