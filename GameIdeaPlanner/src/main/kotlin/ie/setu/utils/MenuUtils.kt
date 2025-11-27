package ie.setu.main.menu

fun readInt(prompt: String): Int 
{
    print(prompt)
    val input = readLine()
    return input?.toIntOrNull() ?: 0
}

fun readDouble(prompt: String): Double 
{
    print(prompt)
    val input = readLine()
    return input?.toDoubleOrNull() ?: 0.0
}

fun readString(prompt: String): String 
{
    print(prompt)
    return readLine() ?: ""
}

