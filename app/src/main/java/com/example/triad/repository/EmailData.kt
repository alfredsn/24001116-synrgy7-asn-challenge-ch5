package com.example.triad.repository

data class EmailData(
    val sendTo: String,
    val name: String,
    val replyTo: String,
    val isHtml: Boolean,
    val title: String,
    val body: String
)