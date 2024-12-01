package ejercicio2.fileReader

data class UserParserResponse(
    var parsedUser: User?,
    var errors: Map<String, String>?
)