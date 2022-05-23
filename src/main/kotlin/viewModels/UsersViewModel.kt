package viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    fun getUsersList() {
        GlobalScope.launch {
            val apiServices = ApiServices.getInstance()
            try {
                val usersList = apiServices.getUsers()
                usersModelListResponse = usersList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun get1User(username: String, onComplete: (usersModel: UsersModel?) -> Unit) {
        GlobalScope.launch {
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
    }

    fun login(
        username: String,
        password: String,
        onComplete: (usersModel: UsersModel?, cause: String) -> Unit
    ) {
        GlobalScope.launch {
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
    }

    fun postUser(user: UsersModel) {
        GlobalScope.launch {
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
}