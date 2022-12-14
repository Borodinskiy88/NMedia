package ru.netology.nmedia.utils

object ReformatValues {
    fun reformatCount(count: Int): String {
        val formatCount = when {
            count in 1000..9999 -> {
                String.format("%.1fK", count / 1000.0)
            }
            count in 10000..999999 -> {
                String.format("%dK", count / 1000)
            }
            count >= 1000000 -> {
                String.format("%.1fM", count / 1000000.0)
            }
            else -> count.toString()
        }
        return formatCount
    }

    fun reformatWebLink(url: String): String? {
        val removeHttps = url.substringAfterLast("https://")
        return removeHttps.substringBeforeLast("/")
    }
}
