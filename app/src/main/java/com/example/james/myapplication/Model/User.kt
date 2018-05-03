package com.example.james.myapplication.Model

import com.google.firebase.database.DataSnapshot

/**
 * Created by adrian on 4/2/18.
 */
class User {

    var name: String? = null
    var email: String? = null
    var uid: String? = null
    var profileImageLink: String? = null

    constructor() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    // constructor take in two parameter
    constructor(username: String?, email: String?) {
        this.name = username
        this.email = email
    }

    // constructor take in three parameter
    constructor(username: String?, email: String?, uid: String?) {
        this.name = username
        this.email = email
        this.uid = uid
    }

    constructor(dataSnapshot: DataSnapshot) {
        this.name = dataSnapshot.child("name").value.toString()
        this.email = dataSnapshot.child("email").value.toString()
    }


}