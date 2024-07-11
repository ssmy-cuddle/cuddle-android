package com.ssmy.cuddle.util

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Deprecated

/**
 * doc 주석
 * @author wookjin
 * @since 7/11/24
 **/
@Deprecated
object SharedPreferencesManager {

    private const val PREF_NAME = "com.ssmy.cuddle.preferences"
    private const val PREFIX_JSON_OBJECT = "json_object_"
    private const val PREFIX_JSON_ARRAY = "json_array_"
    private const val PREFIX_LIST = "list_"
    private const val PREFIX_MAP = "map_"
    private const val PREFIX_BYTE_ARRAY = "byte_array_"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String) {
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String? = null): String? {
        return sharedPreferences.getString(key, defaultValue)
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int = 0): Int {
        return sharedPreferences.getInt(key, defaultValue)
    }

    fun putLong(key: String, value: Long) {
        sharedPreferences.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long = 0L): Long {
        return sharedPreferences.getLong(key, defaultValue)
    }

    fun putFloat(key: String, value: Float) {
        sharedPreferences.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String, defaultValue: Float = 0f): Float {
        return sharedPreferences.getFloat(key, defaultValue)
    }

    fun putBoolean(key: String, value: Boolean) {
        sharedPreferences.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sharedPreferences.getBoolean(key, defaultValue)
    }

    fun putJSONObject(key: String, value: JSONObject) {
        sharedPreferences.edit().putString(PREFIX_JSON_OBJECT + key, value.toString()).apply()
    }

    fun getJSONObject(key: String): JSONObject? {
        return sharedPreferences.getString(PREFIX_JSON_OBJECT + key, null)?.let {
            JSONObject(it)
        }
    }

    fun putJSONArray(key: String, value: JSONArray) {
        sharedPreferences.edit().putString(PREFIX_JSON_ARRAY + key, value.toString()).apply()
    }

    fun getJSONArray(key: String): JSONArray? {
        return sharedPreferences.getString(PREFIX_JSON_ARRAY + key, null)?.let {
            JSONArray(it)
        }
    }

    fun putList(key: String, value: List<*>) {
        val jsonString = JSONArray(value).toString()
        sharedPreferences.edit().putString(PREFIX_LIST + key, jsonString).apply()
    }

    fun getList(key: String): List<Any>? {
        return sharedPreferences.getString(PREFIX_LIST + key, null)?.let {
            val jsonArray = JSONArray(it)
            val list = mutableListOf<Any>()
            for (i in 0 until jsonArray.length()) {
                list.add(jsonArray.get(i))
            }
            list
        }
    }

    fun putMap(key: String, value: Map<*, *>) {
        val jsonObject = JSONObject()
        for ((mapKey, mapValue) in value) {
            jsonObject.put(mapKey.toString(), mapValue)
        }
        sharedPreferences.edit().putString(PREFIX_MAP + key, jsonObject.toString()).apply()
    }

    fun getMap(key: String): Map<String, Any>? {
        return sharedPreferences.getString(PREFIX_MAP + key, null)?.let {
            val jsonObject = JSONObject(it)
            val map = mutableMapOf<String, Any>()
            val keys = jsonObject.keys()
            while (keys.hasNext()) {
                val mapKey = keys.next()
                map[mapKey] = jsonObject.get(mapKey)
            }
            map
        }
    }

    fun putByteArray(key: String, value: ByteArray) {
        val base64Value = Base64.encodeToString(value, Base64.DEFAULT)
        sharedPreferences.edit().putString(PREFIX_BYTE_ARRAY + key, base64Value).apply()
    }

    fun getByteArray(key: String): ByteArray? {
        return sharedPreferences.getString(PREFIX_BYTE_ARRAY + key, null)?.let {
            Base64.decode(it, Base64.DEFAULT)
        }
    }

    fun remove(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }
}