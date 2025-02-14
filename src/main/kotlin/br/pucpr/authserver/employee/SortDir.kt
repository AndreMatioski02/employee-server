package br.pucpr.authserver.employee

enum class SortDir {
    ASC, DESC;

    companion object {
        fun findOrNull(sortDir: String) = entries.find { it.name.uppercase() == sortDir.uppercase() }
    }
}