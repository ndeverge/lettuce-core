/*
 * Copyright 2018-2020 the original author or authors.
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
import io.lettuce.core.XReadArgs.StreamOffset
import io.lettuce.core.models.stream.PendingMessage
import io.lettuce.core.models.stream.PendingMessages
import kotlinx.coroutines.flow.Flow

/**
 * Coroutine executed commands for Streams.
 *
 * @param <K> Key type.
 * @param <V> Value type.
 * @author Mikhael Sokolov
 * @since 5.1
 * @generated by io.lettuce.apigenerator.CreateKotlinCoroutinesApi
 */
@ExperimentalLettuceCoroutinesApi
interface RedisStreamSuspendableCommands<K : Any, V : Any> {

    /**
     * Acknowledge one or more messages as processed.
     *
     * @param key the stream key.
     * @param group name of the consumer group.
     * @param messageIds message Id's to acknowledge.
     * @return simple-reply the lenght of acknowledged messages.
     */
    suspend fun xack(key: K, group: K, vararg messageIds: String): Long?

    /**
     * Append a message to the stream `key`.
     *
     * @param key the stream key.
     * @param body message body.
     * @return simple-reply the message Id.
     */
    suspend fun xadd(key: K, body: Map<K, V>): String?

    /**
     * Append a message to the stream `key`.
     *
     * @param key the stream key.
     * @param args
     * @param body message body.
     * @return simple-reply the message Id.
     */
    suspend fun xadd(key: K, args: XAddArgs, body: Map<K, V>): String?

    /**
     * Append a message to the stream `key`.
     *
     * @param key the stream key.
     * @param keysAndValues message body.
     * @return simple-reply the message Id.
     */
    suspend fun xadd(key: K, vararg keysAndValues: Any): String?

    /**
     * Append a message to the stream `key`.
     *
     * @param key the stream key.
     * @param args
     * @param keysAndValues message body.
     * @return simple-reply the message Id.
     */
    suspend fun xadd(key: K, args: XAddArgs, vararg keysAndValues: Any): String?

    /**
     * Gets ownership of one or multiple messages in the Pending Entries List of a given stream consumer group.
     *
     * @param key the stream key.
     * @param consumer consumer identified by group name and consumer key.
     * @param minIdleTime
     * @param messageIds message Id's to claim.
     * @return simple-reply the [StreamMessage].
     */
    fun xclaim(key: K, consumer: Consumer<K>, minIdleTime: Long, vararg messageIds: String): Flow<StreamMessage<K, V>>

    /**
     * Gets ownership of one or multiple messages in the Pending Entries List of a given stream consumer group.
     *<p>
     *Note that setting the `JUSTID` flag (calling this method with [XClaimArgs#justid()]) suppresses the message
     *bode and [StreamMessage#getBody()] is `null`.
     *
     * @param key the stream key.
     * @param consumer consumer identified by group name and consumer key.
     * @param args
     * @param messageIds message Id's to claim.
     * @return simple-reply the [StreamMessage].
     */
    fun xclaim(key: K, consumer: Consumer<K>, args: XClaimArgs, vararg messageIds: String): Flow<StreamMessage<K, V>>

    /**
     * Removes the specified entries from the stream. Returns the number of items deleted, that may be different from the number
     *of IDs passed in case certain IDs do not exist.
     *
     * @param key the stream key.
     * @param messageIds stream message Id's.
     * @return simple-reply number of removed entries.
     */
    suspend fun xdel(key: K, vararg messageIds: String): Long?

    /**
     * Create a consumer group.
     *
     * @param streamOffset name of the stream containing the offset to set.
     * @param group name of the consumer group.
     * @return simple-reply `true` if successful.
     */
    suspend fun xgroupCreate(streamOffset: StreamOffset<K>, group: K): String?

    /**
     * Create a consumer group.
     *
     * @param streamOffset name of the stream containing the offset to set.
     * @param group name of the consumer group.
     * @param args
     * @return simple-reply `true` if successful.
     * @since 5.2
     */
    suspend fun xgroupCreate(streamOffset: StreamOffset<K>, group: K, args: XGroupCreateArgs): String?

    /**
     * Delete a consumer from a consumer group.
     *
     * @param key the stream key.
     * @param consumer consumer identified by group name and consumer key.
     * @return Long integer-reply number of pending messages.
     */
    suspend fun xgroupDelconsumer(key: K, consumer: Consumer<K>): Long?

    /**
     * Destroy a consumer group.
     *
     * @param key the stream key.
     * @param group name of the consumer group.
     * @return simple-reply `true` if successful.
     */
    suspend fun xgroupDestroy(key: K, group: K): Boolean?

    /**
     * Set the current `group` id.
     *
     * @param streamOffset name of the stream containing the offset to set.
     * @param group name of the consumer group.
     * @return simple-reply OK.
     */
    suspend fun xgroupSetid(streamOffset: StreamOffset<K>, group: K): String?

    /**
     * Retrieve information about the stream at `key`.
     *
     * @param key the stream key.
     * @return List<Object> array-reply.
     * @since 5.2
     */
    suspend fun xinfoStream(key: K): List<Any>

    /**
     * Retrieve information about the stream consumer groups at `key`.
     *
     * @param key the stream key.
     * @return List<Object> array-reply.
     * @since 5.2
     */
    suspend fun xinfoGroups(key: K): List<Any>

    /**
     * Retrieve information about consumer groups of group `group` and stream at `key`.
     *
     * @param key the stream key.
     * @param group name of the consumer group.
     * @return List<Object> array-reply.
     * @since 5.2
     */
    suspend fun xinfoConsumers(key: K, group: K): List<Any>

    /**
     * Get the length of a steam.
     *
     * @param key the stream key.
     * @return simple-reply the lenght of the stream.
     */
    suspend fun xlen(key: K): Long?

    /**
     * Read pending messages from a stream for a `group`.
     *
     * @param key the stream key.
     * @param group name of the consumer group.
     * @return List<Object> array-reply list pending entries.
     */
    suspend fun xpending(key: K, group: K): PendingMessages?

    /**
     * Read pending messages from a stream within a specific [Range].
     *
     * @param key the stream key.
     * @param group name of the consumer group.
     * @param range must not be `null`.
     * @param limit must not be `null`.
     * @return List<Object> array-reply list with members of the resulting stream.
     */
    fun xpending(key: K, group: K, range: Range<String>, limit: Limit): Flow<PendingMessage>

    /**
     * Read pending messages from a stream within a specific [Range].
     *
     * @param key the stream key.
     * @param consumer consumer identified by group name and consumer key.
     * @param range must not be `null`.
     * @param limit must not be `null`.
     * @return List<Object> array-reply list with members of the resulting stream.
     */
    fun xpending(key: K, consumer: Consumer<K>, range: Range<String>, limit: Limit): Flow<PendingMessage>

    /**
     * Read messages from a stream within a specific [Range].
     *
     * @param key the stream key.
     * @param range must not be `null`.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xrange(key: K, range: Range<String>): Flow<StreamMessage<K, V>>

    /**
     * Read messages from a stream within a specific [Range] applying a [Limit].
     *
     * @param key the stream key.
     * @param range must not be `null`.
     * @param limit must not be `null`.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xrange(key: K, range: Range<String>, limit: Limit): Flow<StreamMessage<K, V>>

    /**
     * Read messages from one or more [StreamOffset]s.
     *
     * @param streams the streams to read from.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xread(vararg streams: StreamOffset<K>): Flow<StreamMessage<K, V>>

    /**
     * Read messages from one or more [StreamOffset]s.
     *
     * @param args read arguments.
     * @param streams the streams to read from.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xread(args: XReadArgs, vararg streams: StreamOffset<K>): Flow<StreamMessage<K, V>>

    /**
     * Read messages from one or more [StreamOffset]s using a consumer group.
     *
     * @param consumer consumer/group.
     * @param streams the streams to read from.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xreadgroup(consumer: Consumer<K>, vararg streams: StreamOffset<K>): Flow<StreamMessage<K, V>>

    /**
     * Read messages from one or more [StreamOffset]s using a consumer group.
     *
     * @param consumer consumer/group.
     * @param args read arguments.
     * @param streams the streams to read from.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xreadgroup(consumer: Consumer<K>, args: XReadArgs, vararg streams: StreamOffset<K>): Flow<StreamMessage<K, V>>

    /**
     * Read messages from a stream within a specific [Range] in reverse order.
     *
     * @param key the stream key.
     * @param range must not be `null`.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xrevrange(key: K, range: Range<String>): Flow<StreamMessage<K, V>>

    /**
     * Read messages from a stream within a specific [Range] applying a [Limit] in reverse order.
     *
     * @param key the stream key.
     * @param range must not be `null`.
     * @param limit must not be `null`.
     * @return List<StreamMessage> array-reply list with members of the resulting stream.
     */
    fun xrevrange(key: K, range: Range<String>, limit: Limit): Flow<StreamMessage<K, V>>

    /**
     * Trims the stream to `count` elements.
     *
     * @param key the stream key.
     * @param count length of the stream.
     * @return simple-reply number of removed entries.
     */
    suspend fun xtrim(key: K, count: Long): Long?

    /**
     * Trims the stream to `count` elements.
     *
     * @param key the stream key.
     * @param approximateTrimming @code true} to trim approximately using the `~` flag.
     * @param count length of the stream.
     * @return simple-reply number of removed entries.
     */
    suspend fun xtrim(key: K, approximateTrimming: Boolean, count: Long): Long?

}

