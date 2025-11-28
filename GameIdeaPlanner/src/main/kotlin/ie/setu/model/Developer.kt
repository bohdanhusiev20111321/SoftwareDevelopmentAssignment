package ie.setu.model

import kotlinx.serialization.Serializable

/**
 * Developer - a person who works on game ideas.
 *
 * Simple fields:
 * - developerId: unique id
 * - name: full name
 * - jobTitle: job role (e.g. developer)
 * - salary: money paid
 * - yearsExperience: how many years of work
 * - isRetired: true if the person is retired
 */
@Serializable
data class Developer(

    val developerId: Int,
    var name: String,
    var jobTitle: String,
    var salary: Double,
    var yearsExperience: Int,
    var isRetired: Boolean = false
)