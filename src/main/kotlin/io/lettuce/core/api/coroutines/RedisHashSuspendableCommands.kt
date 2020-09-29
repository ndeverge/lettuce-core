/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:Suppress("unused")

package io.lettuce.core.api.coroutines

import io.lettuce.core.*
import kotlinx.coroutines.flow.Flow

/**
 * Coroutine executed commands for Hashes (Key-Value pairs).
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mikhael Sokolov
 * @since 6.0
 * @generated by io.lettuce.apigenerator.CreateKotlinCoroutinesApi
 */
@ExperimentalLettuceCoroutinesApi
interface RedisHashSuspendableCommands<K : Any, V : Any> {

    /**
     * Delete one or more hash fields.
     *
     * @param key the key.
     * @param fields the field type: key.
     * @return Long integer-reply the number of fields that were removed from the hash, not including specified but non existing
     *        fields.
     */
    suspend fun hdel(key: K, vararg fields: K): Long?

    /**
     * Determine if a hash field exists.
     *
     * @param key the key.
     * @param field the field type: key.
     * @return Boolean integer-reply specifically:
     *
     *        `true` if the hash contains `field`. `false` if the hash does not contain `field`, or
     *        `key` does not exist.
     */
    suspend fun hexists(key: K, field: K): Boolean?

    /**
     * Get the value of a hash field.
     *
     * @param key the key.
     * @param field the field type: key.
     * @return V bulk-string-reply the value associated with `field`, or `null` when `field` is not present in
     *        the hash or `key` does not exist.
     */
    suspend fun hget(key: K, field: K): V?

    /**
     * Increment the integer value of a hash field by the given number.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param amount the increment type: long.
     * @return Long integer-reply the value at `field` after the increment operation.
     */
    suspend fun hincrby(key: K, field: K, amount: Long): Long?

    /**
     * Increment the float value of a hash field by the given amount.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param amount the increment type: double.
     * @return Double bulk-string-reply the value of `field` after the increment.
     */
    suspend fun hincrbyfloat(key: K, field: K, amount: Double): Double?

    /**
     * Get all the fields and values in a hash.
     *
     * @param key the key.
     * @return Map<K,V> array-reply list of fields and their values stored in the hash, or an empty list when `key`
     *        does not exist.
     */
    fun hgetall(key: K): Flow<KeyValue<K, V>>

    /**
     * Get all the fields in a hash.
     *
     * @param key the key.
     * @return List<K> array-reply list of fields in the hash, or an empty list when `key` does not exist.
     */
    fun hkeys(key: K): Flow<K>

    /**
     * Get the number of fields in a hash.
     *
     * @param key the key.
     * @return Long integer-reply number of fields in the hash, or `0` when `key` does not exist.
     */
    suspend fun hlen(key: K): Long?

    /**
     * Get the values of all the given hash fields.
     *
     * @param key the key.
     * @param fields the field type: key.
     * @return List<V> array-reply list of values associated with the given fields, in the same.
     */
    fun hmget(key: K, vararg fields: K): Flow<KeyValue<K, V>>

    /**
     * Set multiple hash fields to multiple values.
     *
     * @param key the key.
     * @param map the null.
     * @return String simple-string-reply.
     */
    suspend fun hmset(key: K, map: Map<K, V>): String?

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K): MapScanCursor<K, V>?

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @param scanArgs scan arguments.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K, scanArgs: ScanArgs): MapScanCursor<K, V>?

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @param scanArgs scan arguments.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K, scanCursor: ScanCursor, scanArgs: ScanArgs): MapScanCursor<K, V>?

    /**
     * Incrementally iterate hash fields and associated values.
     *
     * @param key the key.
     * @param scanCursor cursor to resume from a previous scan, must not be `null`.
     * @return MapScanCursor<K, V> map scan cursor.
     */
    suspend fun hscan(key: K, scanCursor: ScanCursor): MapScanCursor<K, V>?

    /**
     * Set the string value of a hash field.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param value the value.
     * @return Boolean integer-reply specifically:
     *
     *        `true` if `field` is a new field in the hash and `value` was set. `false` if
     *        `field` already exists in the hash and the value was updated.
     */
    suspend fun hset(key: K, field: K, value: V): Boolean?

    /**
     * Set multiple hash fields to multiple values.
     *
     * @param key the key of the hash.
     * @param map the field/value pairs to update.
     * @return Long integer-reply: the number of fields that were added.
     * @since 5.3
     */
    suspend fun hset(key: K, map: Map<K, V>): Long?

    /**
     * Set the value of a hash field, only if the field does not exist.
     *
     * @param key the key.
     * @param field the field type: key.
     * @param value the value.
     * @return Boolean integer-reply specifically:
     *
     *        `1` if `field` is a new field in the hash and `value` was set. `0` if `field`
     *        already exists in the hash and no operation was performed.
     */
    suspend fun hsetnx(key: K, field: K, value: V): Boolean?

    /**
     * Get the string length of the field value in a hash.
     *
     * @param key the key.
     * @param field the field type: key.
     * @return Long integer-reply the string length of the `field` value, or `0` when `field` is not present
     *        in the hash or `key` does not exist at all.
     */
    suspend fun hstrlen(key: K, field: K): Long?

    /**
     * Get all the values in a hash.
     *
     * @param key the key.
     * @return List<V> array-reply list of values in the hash, or an empty list when `key` does not exist.
     */
    fun hvals(key: K): Flow<V>

}

