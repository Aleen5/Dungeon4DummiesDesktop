package viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import models.UsersModel
import services.ApiServices

class UsersViewModel {
    var usersModelListResponse: List<UsersModel> by mutableStateOf(listOf())
    var usersModel: UsersModel by mutableStateOf(
        UsersModel(
            "idprueba",
            "uwusername",
            "uwupassword",
            "uwuname",
            "uwusurname",
            "uwuemail",
            mutableListOf("uwu", "uwu")
        )
    )
    private var errorMessage: String by mutableStateOf("")
    var loginFailures by mutableStateOf(3)
    var user = UsersModel("", "", "", "", "", "", mutableListOf("", ""))

    suspend fun getUsersList() {

        val apiServices = ApiServices.getInstance()
        try {
            val usersList = apiServices.getUsers()
            usersModelListResponse = usersList
        } catch (e: Exception) {
            errorMessage = e.message.toString()
        }
    }

    suspend fun get1User(username: String, onComplete: (usersModel: UsersModel?) -> Unit) {
        val apiServices = ApiServices.getInstance()
        try {
            val result = apiServices.getUser(username)
            if (result.isSuccessful) {
                user = result.body()!!
                onComplete(result.body()!!)
            } else {
                onComplete(null)
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
            onComplete(null)
            loginFailures--
        }
    }

    suspend fun login(
        username: String,
        password: String,
        onComplete: (usersModel: UsersModel?, cause: String) -> Unit
    ) {
        val apiServices = ApiServices.getInstance()
        try {
            val user = apiServices.getLogin(username, password)
            if (user.isSuccessful) {
                onComplete(user.body()!!, "good")

            } else {
                onComplete(null, "bad")
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
            onComplete(null, "catch")
            loginFailures--
        }
    }

    suspend fun postUser(user: UsersModel) {
        val apiServices = ApiServices.getInstance()
        try {
            val userP = apiServices.postUser(user)
            if (userP.isSuccessful) {
                usersModel = userP.body()!!
            } else {
                // LOG
            }
        } catch (e: Exception) {
            errorMessage = e.message.toString()
            // LOG
        }
    }
}