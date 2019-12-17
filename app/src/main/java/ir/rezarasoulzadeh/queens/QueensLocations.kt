package ir.rezarasoulzadeh.queens

class QueensLocations(queensLocationsString: ArrayList<String>, queensCount: Int) {

    private val queensLocations = ArrayList<Int>()

    init {
        for (i in queensLocationsString.indices) {
            val section = queensLocationsString[i].split(" ")
            queensLocations.add((section[0].toInt() * queensCount) - (queensCount % section[1].toInt()))
        }
    }

    fun getQueensLocations(): ArrayList<Int> {
        return queensLocations
    }

}