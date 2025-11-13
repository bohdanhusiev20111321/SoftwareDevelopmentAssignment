package ie.setu.model

data class Developer(

    val developerId: Int,
    var name: String,
    var jobTitle: String,
    var salary: Double,
    var yearsExperience: Int,
    var isRetired: Boolean = false 
)