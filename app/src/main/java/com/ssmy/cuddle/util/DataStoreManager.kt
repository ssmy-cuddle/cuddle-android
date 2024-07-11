package com.ssmy.cuddle.util

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import android.util.Base64


val Context.dataStore by preferencesDataStore(name = "settings")

/**
 * doc 주석
 * @author wookjin
 * @since 7/11/24
 **/
object DataStoreManager {

    const val PREFIX_JSON_OBJECT = "json_object_"
    const val PREFIX_JSON_ARRAY = "json_array_"
    const val PREFIX_LIST = "list_"
    const val PREFIX_MAP = "map_"
    const val PREFIX_BYTE_ARRAY = "byte_array_"

    suspend fun <T> putData(context: Context, key: String, value: T) {
        withContext(Dispatchers.IO) {
            context.dataStore.edit { preferences ->
                when (value) {
                    is String -> preferences[stringPreferencesKey(key)] = value
                    is Int -> preferences[intPreferencesKey(key)] = value
                    is Boolean -> preferences[booleanPreferencesKey(key)] = value
                    is Float -> preferences[floatPreferencesKey(key)] = value
                    is Long -> preferences[longPreferencesKey(key)] = value
                    is JSONObject -> preferences[stringPreferencesKey(PREFIX_JSON_OBJECT + key)] = value.toString()
                    is JSONArray -> preferences[stringPreferencesKey(PREFIX_JSON_ARRAY + key)] = value.toString()
                    is List<*> -> preferences[stringPreferencesKey(PREFIX_LIST + key)] = JSONArray(value).toString()
                    is Map<*, *> -> {
                        val jsonObject = JSONObject()
                        for ((mapKey, mapValue) in value) {
                            jsonObject.put(mapKey.toString(), mapValue)
                        }
                        preferences[stringPreferencesKey(PREFIX_MAP + key)] = jsonObject.toString()
                    }
                    is ByteArray -> preferences[stringPreferencesKey(PREFIX_BYTE_ARRAY + key)] = Base64.encodeToString(value, Base64.DEFAULT)
                    else -> throw IllegalArgumentException("Unsupported data type")
                }
            }
        }
    }

    inline fun <reified T> getData(context: Context, key: String, defaultValue: T? = null): Flow<T?> {
        return context.dataStore.data.map { preferences ->
            when (T::class) {
                String::class -> preferences[stringPreferencesKey(key)] as? T ?: defaultValue
                Int::class -> preferences[intPreferencesKey(key)] as? T ?: defaultValue
                Boolean::class -> preferences[booleanPreferencesKey(key)] as? T ?: defaultValue
                Float::class -> preferences[floatPreferencesKey(key)] as? T ?: defaultValue
                Long::class -> preferences[longPreferencesKey(key)] as? T ?: defaultValue
                JSONObject::class -> preferences[stringPreferencesKey(PREFIX_JSON_OBJECT + key)]?.let { JSONObject(it) as T } ?: defaultValue
                JSONArray::class -> preferences[stringPreferencesKey(PREFIX_JSON_ARRAY + key)]?.let { JSONArray(it) as T } ?: defaultValue
                List::class -> preferences[stringPreferencesKey(PREFIX_LIST + key)]?.let {
                    val jsonArray = JSONArray(it)
                    val list = mutableListOf<Any>()
                    for (i in 0 until jsonArray.length()) {
                        list.add(jsonArray.get(i))
                    }
                    list as T
                } ?: defaultValue
                Map::class -> preferences[stringPreferencesKey(PREFIX_MAP + key)]?.let {
                    val jsonObject = JSONObject(it)
                    val map = mutableMapOf<String, Any>()
                    val keys = jsonObject.keys()
                    while (keys.hasNext()) {
                        val mapKey = keys.next()
                        map[mapKey] = jsonObject.get(mapKey)
                    }
                    map as T
                } ?: defaultValue
                ByteArray::class -> preferences[stringPreferencesKey(PREFIX_BYTE_ARRAY + key)]?.let { Base64.decode(it, Base64.DEFAULT) as T } ?: defaultValue
                else -> throw IllegalArgumentException("Unsupported data type")
            }
        }
    }

    suspend fun remove(context: Context, key: String) {
        val dataStoreKey = stringPreferencesKey(key)
        context.dataStore.edit { preferences ->
            preferences.remove(dataStoreKey)
        }
    }

    suspend fun clear(context: Context) {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}