package com.themaker.fshmo.klassikaplus.data.web.dto.requests

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class GenericRequest private constructor(
    @SerializedName("chain_name")
    @Expose
    var chainName: String,
    @SerializedName("id")
    @Expose
    var id: String = "",
    @SerializedName("method")
    @Expose
    var method: String,
    @SerializedName("params")
    @Expose
    var params: List<String>
) : Serializable {


    companion object Builder {

        private var method: String = "getInfo"
        private var chainName = "chain1"
        private var id = "1FtwaoKQJSn3CtgvFL7pXomC3EzzymCdeJWg3o"
        private var params = arrayListOf<String>()

        fun build(): GenericRequest {
            return GenericRequest(this)
        }

        fun addParam(param: String): Builder {
            params.add(param)
            return this
        }

        fun addId(id: String): Builder {
            this.id = id
            return this
        }

        fun setChain(chain: String): Builder {
            chainName = chain
            return this
        }

        fun setMethod(method: String): Builder {
            this.method = method
            return this
        }
    }

    private constructor(builder: Builder) : this(
        chainName = builder.chainName,
        id = builder.id,
        method = builder.method,
        params = builder.params
    )
}

