package info.developia.taskker.api.util

class Common {
    static boolean isNullOrBlank(String value) {
        value == null || value.isBlank()
    }

    static boolean isPositiveLong(String value){
        try {
            return Long.valueOf(value) > 0
        } catch (NumberFormatException ignored){
            return false
        }
    }
}
