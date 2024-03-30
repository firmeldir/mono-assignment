package com.monotest

data class MeaningfulUiEntity(
    val uid: String,
    val imageUrl: String
)

val meaningfulEntities = listOf(
    "https://images.unsplash.com/photo-1566438480900-0609be27a4be?q=80&w=3694&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "https://images.unsplash.com/photo-1488372759477-a7f4aa078cb6?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NHx8aW1hZ2V8ZW58MHx8MHx8fDA%3D",
    "https://images.unsplash.com/photo-1633621412960-6df85eff8c85?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTR8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1628784230353-5bee16e2f005?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTZ8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1621155346337-1d19476ba7d6?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MTh8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1579781354199-1ffd36bd7d30?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjN8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1635468872214-8d30953f0057?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjJ8fGltYWdlfGVufDB8fDB8fHww",
    "https://yt3.googleusercontent.com/ZJGwKd4H-lsmPo6cZ2WJ7aaU6uRJYOAmj-MDIDy_Se0sUu3iM41hG3KXgVz690DeEPRqxaKx=s900-c-k-c0x00ffffff-no-rj",
    "https://images.unsplash.com/photo-1547219469-75c19c0bd220?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MjR8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1578589318433-39b5de440c3f?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzF8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1521727696769-849d83dd59db?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8MzJ8fGltYWdlfGVufDB8fDB8fHww",
    "https://plus.unsplash.com/premium_photo-1664304102989-6048cbdc2051?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mzd8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1588627123518-51d01930af2d?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mzl8fGltYWdlfGVufDB8fDB8fHww",
    "https://plus.unsplash.com/premium_photo-1698007840834-aac767242716?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NDl8fGltYWdlfGVufDB8fDB8fHww",
    "https://images.unsplash.com/photo-1623504248122-747b98df5f81?w=600&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8NTJ8fGltYWdlfGVufDB8fDB8fHww"
).map { MeaningfulUiEntity(uid = it, imageUrl = it) }