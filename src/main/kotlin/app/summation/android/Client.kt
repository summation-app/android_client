package app.summation.android

import com.beust.klaxon.Klaxon
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

class Client(
    var gatewayUrl: String,
    var token: String,
    var gatewayToken: String,
    var defaultDatabase: String)
{
    val client = OkHttpClient()

    /* DATABASE METHODS */

    fun query(sql: String, parameters: HashMap<String, Any>, databaseName: String? = null): String?
    {
        data class Params(
            var token: String,
            var gateway_token: String,
            var parameters: HashMap<String, Any>,
            var sql: String,
            var database_name: String,
        )
        val json    = Klaxon().toJsonString(Params(
            token = this.token,
            gateway_token = this.gatewayToken,
            parameters = parameters,
            sql = sql,
            database_name = if (databaseName != null) databaseName else this.defaultDatabase
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/database")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    fun create(table: String, parameters: HashMap<String, Any>, databaseName: String? = null): String?
    {
        data class Params(
            var table: String,
            var method: String,
            var parameters: HashMap<String, Any>,
            var token: String,
            var gateway_token: String,
            var database_name: String,
        )
        val json    = Klaxon().toJsonString(Params(
            table = table,
            method = "create",
            parameters = parameters,
            token = this.token,
            gateway_token = this.gatewayToken,
            database_name = if (databaseName != null) databaseName else this.defaultDatabase
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/database")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    fun read(table: String, parameters: HashMap<String, Any>, databaseName: String? = null): String?
    {
        data class Params(
            var table: String,
            var method: String,
            var parameters: HashMap<String, Any>,
            var token: String,
            var gateway_token: String,
            var database_name: String,
        )
        val json    = Klaxon().toJsonString(Params(
            table = table,
            method = "read",
            parameters = parameters,
            token = this.token,
            gateway_token = this.gatewayToken,
            database_name = if (databaseName != null) databaseName else this.defaultDatabase
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/database")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    fun update(table: String, parameters: HashMap<String, Any>, databaseName: String? = null): String?
    {
        data class Params(
            var table: String,
            var method: String,
            var parameters: HashMap<String, Any>,
            var token: String,
            var gateway_token: String,
            var database_name: String,
        )
        val json    = Klaxon().toJsonString(Params(
            table = table,
            method = "update",
            parameters = parameters,
            token = this.token,
            gateway_token = this.gatewayToken,
            database_name = if (databaseName != null) databaseName else this.defaultDatabase
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/database")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    fun upsert(table: String, parameters: HashMap<String, Any>, databaseName: String? = null): String?
    {
        data class Params(
            var table: String,
            var method: String,
            var parameters: HashMap<String, Any>,
            var token: String,
            var gateway_token: String,
            var database_name: String,
        )
        val json    = Klaxon().toJsonString(Params(
            table = table,
            method = "upsert",
            parameters = parameters,
            token = this.token,
            gateway_token = this.gatewayToken,
            database_name = if (databaseName != null) databaseName else this.defaultDatabase
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/database")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }


    /* BOTH DATABASE & API METHODS */

    fun delete(param_1: String, param_2: HashMap<String, Any>, param_3: Any?): String?
    {
        val pattern = "https?://".toRegex()
        //could be either an API call or a database call
        //parse URL to see if it's a valid URL, or the name of a database class
        if(pattern.containsMatchIn(param_1))
        {
            //is an API request
            val url = param_1;
            val data = param_2;
            val headers = param_3;

            data class Params1(
                var url: String,
                var method: String,
                var data: HashMap<String, Any>,
                var token: String,
                var gateway_token: String,
                var headers: Any?,
            )
            val json    = Klaxon().toJsonString(Params1(
                url = url,
                method = "DELETE",
                data = data,
                token = this.token,
                gateway_token = this.gatewayToken,
                headers = headers
            ))
            val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
            val request = Request.Builder()
                .url(this.gatewayUrl + "/api")
                .post(body)
                .build()

            val response = this.client.newCall(request).execute()

            return response.body?.string()
        }
        else
        {
            //is a database query
            val table        = param_1;
            val parameters   = param_2;
            val databaseName = param_3;

            data class Params2(
                var table: String,
                var method: String,
                var parameters: HashMap<String, Any>,
                var token: String,
                var gateway_token: String,
                var database_name: Any,
            )
            val json    = Klaxon().toJsonString(Params2(
                table = table,
                method = "delete",
                parameters = parameters,
                token = this.token,
                gateway_token = this.gatewayToken,
                database_name = if (databaseName != null) databaseName else this.defaultDatabase
            ))
            val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
            val request = Request.Builder()
                .url(this.gatewayUrl + "/database")
                .post(body)
                .build()

            val response = this.client.newCall(request).execute()

            return response.body?.string()
        }
    }

    /* API METHODS */

    fun get(url: String, parameters: HashMap<String, Any>?, headers: HashMap<String, Any>?): String?
    {
        data class Params(
            var url: String,
            var method: String,
            var parameters: HashMap<String, Any>?,
            var token: String,
            var gateway_token: String,
            var headers: HashMap<String, Any>?,
        )
        val json    = Klaxon().toJsonString(Params(
            url = url,
            method = "GET",
            parameters = parameters,
            token = this.token,
            gateway_token = this.gatewayToken,
            headers = headers
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/api")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    fun post(url: String, data: HashMap<String, Any>, headers: HashMap<String, Any>): String?
    {
        data class Params(
            var url: String,
            var method: String,
            var data: HashMap<String, Any>,
            var token: String,
            var gateway_token: String,
            var headers: Any?,
        )
        val json    = Klaxon().toJsonString(Params(
            url = url,
            method = "POST",
            data = data,
            token = this.token,
            gateway_token = this.gatewayToken,
            headers = headers
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/api")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    fun put(url: String, data: HashMap<String, Any>, headers: HashMap<String, Any>): String?
    {
        data class Params(
            val url: String,
            var method: String,
            var data: HashMap<String, Any>,
            var token: String,
            var gateway_token: String,
            var headers: HashMap<String, Any>,
        )
        val initParams: Params = Params(url, "PUT", data, this.token, this.gatewayToken, headers)
        val json: String = Klaxon().toJsonString(initParams)
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/api")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    fun patch(url: String, data: HashMap<String, Any>, headers: HashMap<String, Any>): String?
    {
        data class Params(
            var url: String,
            var method: String,
            var data: HashMap<String, Any>,
            var token: String,
            var gateway_token: String,
            var headers: Any?,
        )
        val json    = Klaxon().toJsonString(Params(
            url = url,
            method = "PATCH",
            data = data,
            token = this.token,
            gateway_token = this.gatewayToken,
            headers = headers
        ))
        val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
        val request = Request.Builder()
            .url(this.gatewayUrl + "/api")
            .post(body)
            .build()

        val response = this.client.newCall(request).execute()

        return response.body?.string()
    }

    inner class Chain {

        var queue: ArrayList<Any> = ArrayList()

        fun query(sql: String, parameters: HashMap<String, Any>, databaseName: String): Chain {
            data class Params(
                    var method: String,
                    var sql: String,
                    var parameters: HashMap<String, Any>,
                    var database_name: String,
            )
            val data = Params(
                    method = "query",
                    sql = sql,
                    parameters = parameters,
                    database_name = databaseName,
            )
            this.queue.add(data)
            return this
        }

        fun create(table: String, parameters: HashMap<String, Any>, databaseName: String): Chain {
            data class Params(
                    var method: String,
                    var table: String,
                    var parameters: HashMap<String, Any>,
                    var database_name: String,
            )
            val data = Params(
                    method = "create",
                    table = table,
                    parameters = parameters,
                    database_name = databaseName,
            )
            this.queue.add(data)
            return this
        }

        fun read(table: String, parameters: HashMap<String, Any>, databaseName: String): Chain {
            data class Params(
                    var method: String,
                    var table: String,
                    var parameters: HashMap<String, Any>,
                    var database_name: String,
            )
            val data = Params(
                    method = "read",
                    table = table,
                    parameters = parameters,
                    database_name = databaseName,
            )
            this.queue.add(data)
            return this
        }

        fun update(table: String, parameters: HashMap<String, Any>, databaseName: String): Chain {
            data class Params(
                    var method: String,
                    var table: String,
                    var parameters: HashMap<String, Any>,
                    var database_name: String,
            )
            val data = Params(
                    method = "update",
                    table = table,
                    parameters = parameters,
                    database_name = databaseName,
            )
            this.queue.add(data)
            return this
        }

        fun upsert(table: String, parameters: HashMap<String, Any>, databaseName: String): Chain {
            data class Params(
                    var method: String,
                    var table: String,
                    var parameters: HashMap<String, Any>,
                    var database_name: String,
            )
            val data = Params(
                    method = "upsert",
                    table = table,
                    parameters = parameters,
                    database_name = databaseName,
            )
            this.queue.add(data)
            return this
        }

        fun get(url: String, parameters: HashMap<String, Any>, headers: HashMap<String, Any>): Chain {
            data class Params(
                    var method: String,
                    var url: String,
                    var parameters: HashMap<String, Any>,
                    var headers: HashMap<String, Any>,
            )
            val data = Params(
                    method = "GET",
                    url = url,
                    parameters = parameters,
                    headers = headers,
            )
            this.queue.add(data)
            return this
        }

        fun post(url: String, data: HashMap<String, Any>, headers: HashMap<String, Any>): Chain {
            data class Params(
                    var method: String,
                    var url: String,
                    var data: HashMap<String, Any>,
                    var headers: HashMap<String, Any>,
            )
            val params = Params(
                    method = "POST",
                    url = url,
                    data = data,
                    headers = headers,
            )
            this.queue.add(params)
            return this
        }

        fun patch(url: String, data: HashMap<String, Any>, headers: HashMap<String, Any>): Chain {
            data class Params(
                    var method: String,
                    var url: String,
                    var data: HashMap<String, Any>,
                    var headers: HashMap<String, Any>,
            )
            val params = Params(
                    method = "PATCH",
                    url = url,
                    data = data,
                    headers = headers,
            )
            this.queue.add(params)
            return this
        }

        fun put(url: String, data: HashMap<String, Any>, headers: HashMap<String, Any>): Chain {
            data class Params(
                    var method: String,
                    var url: String,
                    var data: HashMap<String, Any>,
                    var headers: HashMap<String, Any>,
            )
            val params = Params(
                    method = "PUT",
                    url = url,
                    data = data,
                    headers = headers,
            )
            this.queue.add(params)
            return this
        }

        fun delete(url: String, data: HashMap<String, Any>, headers: HashMap<String, Any>): Chain {
            data class Params(
                    var method: String,
                    var url: String,
                    var data: HashMap<String, Any>,
                    var headers: HashMap<String, Any>,
            )
            val params = Params(
                    method = "DELETE",
                    url = url,
                    data = data,
                    headers = headers,
            )
            this.queue.add(params)
            return this
        }

        fun run(): String? {
            data class Params(
                    var token: String,
                    var gateway_token: String,
                    var default_database: String,
                    var queue: ArrayList<Any>,
            )
            val json = Klaxon().toJsonString(Params(
                    token = this@Client.token,
                    gateway_token = this@Client.gatewayToken,
                    default_database = this@Client.defaultDatabase,
                    queue = this.queue
            ))
            val body    = RequestBody.create("application/json; charset=utf-8".toMediaTypeOrNull(), json)
            val request = Request.Builder()
                    .url(this@Client.gatewayUrl + "/chain")
                    .post(body)
                    .build()

            val response = this@Client.client.newCall(request).execute()

            return response.body?.string()
        }
    }
}