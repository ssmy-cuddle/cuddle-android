package com.ssmy.cuddle.data

import android.content.Context
import android.util.Base64
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject

val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")
val Context.appSettingsDataStore: DataStore<Preferences> by preferencesDataStore(name = "app_settings")

/**
 * DataStoreManager는 여러 DataStore 인스턴스를 관리하여 데이터 저장, 검색, 삭제 및 초기화를 담당합니다.
 *
 * 주 기능:
 * - 다양한 데이터 타입(String, Int, Boolean, Float, Long, JSONObject, JSONArray, List, Map, ByteArray)을 DataStore에 저장.
 * - DataStore에서 데이터를 검색하고 Flow 형태로 반환.
 * - 특정 키 또는 전체 DataStore 내용을 삭제.
 *
 * DataStore 인스턴스:
 * - userPreferencesDataStore: 사용자 선호 설정을 저장.
 * - appSettingsDataStore: 애플리케이션 설정을 저장.
 *
 * 사용 예시:
 * - DataStoreManager.putUserPreference(context, "user_name", "John Doe")
 * - DataStoreManager.getUserPreference(context, "user_name")
 * - DataStoreManager.putAppSetting(context, "theme", "dark")
 * - DataStoreManager.getAppSetting(context, "theme")
 *
 * @author wookjin
 * @since 7/11/24
 **/
object DataStoreKeys {
    const val PREFIX_JSON_OBJECT = "json_object_"
    const val PREFIX_JSON_ARRAY = "json_array_"
    const val PREFIX_LIST = "list_"
    const val PREFIX_MAP = "map_"
    const val PREFIX_BYTE_ARRAY = "byte_array_"
}

object DataStoreManager {
    const val PREFIX_JSON_OBJECT = DataStoreKeys.PREFIX_JSON_OBJECT
    const val PREFIX_JSON_ARRAY = DataStoreKeys.PREFIX_JSON_ARRAY
    const val PREFIX_LIST = DataStoreKeys.PREFIX_LIST
    const val PREFIX_MAP = DataStoreKeys.PREFIX_MAP
    const val PREFIX_BYTE_ARRAY = DataStoreKeys.PREFIX_BYTE_ARRAY

    suspend fun <T> putUserPreference(context: Context, key: String, value: T) {
        putData(context.userPreferencesDataStore, key, value)
    }

    suspend fun <T> putAppSetting(context: Context, key: String, value: T) {
        putData(context.appSettingsDataStore, key, value)
    }

    inline fun <reified T> getUserPreference(context: Context, key: String, defaultValue: T? = null): Flow<T?> {
        return getData(context.userPreferencesDataStore, key, defaultValue)
    }

    inline fun <reified T> getAppSetting(context: Context, key: String, defaultValue: T? = null): Flow<T?> {
        return getData(context.appSettingsDataStore, key, defaultValue)
    }

    suspend fun removeUserPreference(context: Context, key: String) {
        remove(context.userPreferencesDataStore, key)
    }

    suspend fun removeAppSetting(context: Context, key: String) {
        remove(context.appSettingsDataStore, key)
    }

    suspend fun clearUserPreferences(context: Context) {
        clear(context.userPreferencesDataStore)
    }

    suspend fun clearAppSettings(context: Context) {
        clear(context.appSettingsDataStore)
    }

    private suspend fun <T> putData(dataStore: DataStore<Preferences>, key: String, value: T) {
        withContext(Dispatchers.IO) {
            dataStore.edit { preferences ->
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
                        value.forEach { (mapKey, mapValue) -> jsonObject.put(mapKey.toString(), mapValue) }
                        preferences[stringPreferencesKey(PREFIX_MAP + key)] = jsonObject.toString()
                    }
                    is ByteArray -> preferences[stringPreferencesKey(PREFIX_BYTE_ARRAY + key)] = Base64.encodeToString(value, Base64.DEFAULT)
                    else -> throw IllegalArgumentException("Unsupported data type")
                }
            }
        }
    }

    inline fun <reified T> getData(dataStore: DataStore<Preferences>, key: String, defaultValue: T? = null): Flow<T?> {
        return dataStore.data.map { preferences ->
            when (T::class) {
                String::class -> preferences[stringPreferencesKey(key)] as? T ?: defaultValue
                Int::class -> preferences[intPreferencesKey(key)] as? T ?: defaultValue
                Boolean::class -> preferences[booleanPreferencesKey(key)] as? T ?: defaultValue
                Float::class -> preferences[floatPreferencesKey(key)] as? T ?: defaultValue
                Long::class -> preferences[longPreferencesKey(key)] as? T ?: defaultValue
                JSONObject::class -> preferences[stringPreferencesKey(PREFIX_JSON_OBJECT + key)]?.let { JSONObject(it) as T } ?: defaultValue
                JSONArray::class -> preferences[stringPreferencesKey(PREFIX_JSON_ARRAY + key)]?.let { JSONArray(it) as T } ?: defaultValue
                List::class -> preferences[stringPreferencesKey(PREFIX_LIST + key)]?.let { jsonString ->
                    val jsonArray = JSONArray(jsonString)
                    List(jsonArray.length()) { index -> jsonArray.get(index) } as T
                } ?: defaultValue
                Map::class -> preferences[stringPreferencesKey(PREFIX_MAP + key)]?.let { jsonString ->
                    val jsonObject = JSONObject(jsonString)
                    jsonObject.keys().asSequence().associateWith { jsonObject.get(it) } as T
                } ?: defaultValue
                ByteArray::class -> preferences[stringPreferencesKey(PREFIX_BYTE_ARRAY + key)]?.let { Base64.decode(it, Base64.DEFAULT) as T } ?: defaultValue
                else -> throw IllegalArgumentException("Unsupported data type")
            }
        }
    }

    private suspend fun remove(dataStore: DataStore<Preferences>, key: String) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(key))
        }
    }

    private suspend fun clear(dataStore: DataStore<Preferences>) {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}