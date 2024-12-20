package com.buccbracu.bucc.backend.remote.firebase

fun topics(department: String, designation: String): List<String> {
    val allDepartments = listOf(
        "governing_body",
        "communication_and_marketing",
        "creative",
        "event_management",
        "finance",
        "human_resources",
        "press_release_and_publications",
        "research_and_development"
    )
    val normalizedDept = department.lowercase().replace(" ", "_")
    val normalizedDes = designation.lowercase().replace(" ", "_")
    val topics = mutableListOf<String>()

    when (normalizedDes) {
        "senior_executive" -> {
            // Add task for Senior Executive in their department
            topics.add("task_${normalizedDept}_senior_executive")
        }
        "director", "assistant_director" -> {
            // Add tasks for Director, Assistant Director, and Senior Executive in their department
            topics.add("task_${normalizedDept}_director")
            topics.add("task_${normalizedDept}_assistant_director")
            topics.add("task_${normalizedDept}_senior_executive")
        }
        "president", "vice_president", "general_secretary", "treasurer" -> {
            // Add tasks for Director, Assistant Director, and Senior Executive for all departments
            allDepartments.forEach { dept ->
                topics.add("task_${dept}_director")
                topics.add("task_${dept}_assistant_director")
                topics.add("task_${dept}_senior_executive")
            }
            // Add tasks for President, Vice President, General Secretary, and Treasurer in their department
            topics.add("task_${normalizedDept}_president")
            topics.add("task_${normalizedDept}_vice_president")
            topics.add("task_${normalizedDept}_general_secretary")
            topics.add("task_${normalizedDept}_treasurer")
        }
        else -> {
            // For other designations, no additional tasks
        }
    }

    return topics
}
